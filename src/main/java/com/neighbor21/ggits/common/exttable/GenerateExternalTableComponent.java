package com.neighbor21.ggits.common.exttable;

import com.neighbor21.ggits.common.mapper.GenerateExternalTableMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.BDDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  GenerateExternalTableComponent
 * @since : 2023-11-15
 */
@Component
public class GenerateExternalTableComponent {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("#{commonProperties['hive.driver']}")
    public String driver;

    @Value("#{commonProperties['hive.url']}")
    public String url;

    @Value("#{commonProperties['hive.username']}")
    public String username;

    @Value("#{commonProperties['hive.passwd']}")
    public String passwd;

    final static String GGITS_LINK_STD_1M_QUERY = "select * from ggits.ggits_link_std_1m where proc_date >= to_timestamp('%s')";
    final static String GGITS_LINK_STD_1H_QUERY = "select * from ggits_link_std_1h where '%s' <= etl_dt AND  etl_dt <= '%s'";
    final static String GIMS_MNG_INCI_MASTER = "";
    final static String GIMS_MNG_INCI_DETAIL = "";

    final static String ADSI_VDS_COLCT_INFO_QUERY = "SELECT * FROM  ggits.adsi_vds_colct_info  where etl_dt >= '%s'  and etl_dt <= '%s'";
    final static String ADSI_DSRC_COLCT_INFO_QUERY = "SELECT * FROM  ggits.adsi_dsrc_colct_info where etl_dt >= '%s'  and etl_dt <= '%s'";

    @Autowired
    GenerateExternalTableMapper generateExternalTableMapper;

    public void generate(String... externalTables) throws ClassNotFoundException, SQLException {
        for(String table : externalTables) {
            generatePQ(table);
            //generateExternalTable(table);
        }
    }

    private void generateExternalTable(String table) {
        logger.info("EXTERNAL TABLE 생성 " + table);
        long start = System.currentTimeMillis(); // 작동 시간 측정용
        String result = generateExternalTableMapper.generateExternalTable(table.toLowerCase());
        logger.info(result);
        long end = System.currentTimeMillis(); // 작동시간 측정용
        double prsTime = (end - start) / 1000.0;;
        logger.info("테이블명 : {} , 실행시간 : {}" , table, prsTime);
    }


    private void generatePQ(String table) throws ClassNotFoundException, SQLException {
        logger.info("PQ 생성 : {} ", table);
        long start = System.currentTimeMillis(); // 작동 시간 측정용
        Class.forName(driver);
        String strTable = table.toLowerCase();
        Connection connect = null;
        Statement st = null;
        try
        {

            connect = DriverManager.getConnection(url, username, passwd);
            if(connect == null) {
                throw new SQLException("no connection...");
            }
            st = connect.createStatement();
            st.setQueryTimeout(60*6);

            logger.info("[PQ] Drop Table Start: {} ", table);
            boolean isResut = false;

            String systemName = System.getProperty("system.name") != null ? System.getProperty("system.name") : "undefined";
            isResut = st.execute("SET spark.ndap.query.invoker="+systemName);

            String sql = "";
	        sql = String.format("drop table if exists ggits.pq_%s purge", strTable);
            isResut =  st.execute(sql);
            logger.info("[PQ] Drop Table End: {} Stat : {}", table, isResut);
            sql = "set spark.sql.legacy.allowNonEmptyLocationInCTAS=true";
            isResut = st.execute(sql);

            /*logger.info("[PQ] Refresh Table Start: {} ", table);
            sql = "refresh table ggits.pq_"+strTable;
            isResut = st.execute(sql);
            logger.info("[PQ] Refresh Table End: {} {}", table, isResut);*/

            String query = "select * from ggits."+strTable;
            Date endDt = new Date();
            Date startDt = BDDateUtil.addMinutes(endDt, -5);
            String durationSt = BDDateFormatUtil.format(startDt, "yyyyMMddHHmmss");
            String durationEd = BDDateFormatUtil.format(endDt, "yyyyMMddHHmmss");
            switch(table.toUpperCase()){
                case "GGITS_LINK_STD_1M" :
                    /*ResultSet rs = st.executeQuery("SELECT  max(coll_date) as coll_date from ggits_link_std_1m");
                    Timestamp maxTime = null;
                    while(rs.next()) {
                        maxTime = rs.getTimestamp("coll_date");
                    }*/
                    Date collDt = BDDateUtil.addMinutes(new Date(), -1);
                    /*if(maxTime != null){*/
                        String maxTimeStr = BDDateFormatUtil.format(collDt, "yyyy-MM-dd HH:mm:ss");
                        query = String.format(GGITS_LINK_STD_1M_QUERY, maxTimeStr);
                    /*}*/
                    break;
                case "GGITS_LINK_STD_1H" :
                    query =  String.format(GGITS_LINK_STD_1H_QUERY,durationSt, durationEd);
                break;
                case "ADSI_VDS_COLCT_INFO" :
                    query = String.format(ADSI_VDS_COLCT_INFO_QUERY, durationSt, durationEd);
                break;
                case "ADSI_DSRC_COLCT_INFO" :
                    query = String.format(ADSI_DSRC_COLCT_INFO_QUERY, durationSt, durationEd);
                break;
            }


            sql = String.format("create table if not exists  ggits.pq_%s using parquet as %s", strTable, query);
            logger.info("[PQ] Create parquet Start: {} , sql : {}", table, sql);
            isResut = st.execute(sql);
            logger.info("[PQ] Create parquet End: {} Sts : {}", table, isResut);

            logger.info("[PQ] Refresh Table Start: {} ", table);
            sql = "refresh table ggits.pq_"+strTable;
            isResut = st.execute(sql);
            logger.info("[PQ] Refresh Table End: {} {}", table, isResut);

            connect.close();
        } catch (SQLException ex) {
            logger.error("SQLException {}" , strTable);
            throw ex;
        } finally {
            try {
                if (connect != null)
                    connect.close();
            }catch(NullPointerException e) {
                logger.info("close 실패");
            }
        }

        long end = System.currentTimeMillis(); // 작동시간 측정용
        double prsTime = ((end - start) / 1000.0);
        logger.info("pq생성 테이블명 : {} , 실행시간 : {}" , table, prsTime);
    }
}
