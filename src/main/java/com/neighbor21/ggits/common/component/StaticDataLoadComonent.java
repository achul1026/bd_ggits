package com.neighbor21.ggits.common.component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 정적데이터 불러오는 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  StaticDataLoadComonent
 * @since : 2023-12-08
 */
@Component
public class StaticDataLoadComonent {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    List<AdsiSmcrsrdCrsrdAcsRoadInfo> gmSmcrdAcsrdList = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        try {
            CsvMapper cm = new CsvMapper();
            cm.enable(CsvParser.Feature.WRAP_AS_ARRAY);

            ClassPathResource classPathResource = new ClassPathResource("files/gwangmyung_smcrsrd_acsroad_info.csv");
            /*File file = new File(resource.getURL().getPath());*/

            if(!classPathResource.exists()){
                throw new IllegalArgumentException();
            }

            try (InputStream is = new BufferedInputStream(classPathResource.getInputStream())) {
                MappingIterator<List<String>> csvdata = cm.readerFor(List.class)
                        .readValues(is);
                gmSmcrdAcsrdList = csvdata.readAll().stream()
                        .filter(x -> !x.isEmpty())
                        .map(AdsiSmcrsrdCrsrdAcsRoadInfo::new)
                        .collect(Collectors.toList());
            }
        }catch(IllegalArgumentException | IOException e) {
            e.printStackTrace();
            logger.info("광명 스마트 교차로 접근도로 Static 정보 불러오기 실패");
        }
    }

    public List<AdsiSmcrsrdCrsrdAcsRoadInfo> getGmSmcrdAcsrdList() {
        return gmSmcrdAcsrdList;
    }
}
