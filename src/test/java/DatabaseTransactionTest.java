import com.neighbor21.ggits.common.entity.LOpPgmLogn;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.mapper.LOpPgmLognMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.transaction.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/egovframework/springmvc/dispatcher-servlet.xml",
        "file:src/main/resources/spring/*.xml"}
)
@WebAppConfiguration
public class DatabaseTransactionTest {

    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    LOpPgmLognMapper lOpPgmLognMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional
    public void test() {
        LOpPgmLogn lOpPgmLogn = new LOpPgmLogn();
        lOpPgmLogn.setLogId(String.valueOf((int)(Math.random()*1000)));
        lOpPgmLogn.setOprtrId((int)(Math.random()*1000));
        lOpPgmLogn.setGrpId("test");
        lOpPgmLogn.setPrgrmSesnId("email@email.com");
        lOpPgmLogn.setRqstrNm("김철민");
        lOpPgmLogn.setLgnIp(null);
        // 사용자 로그 유형(ULC001: 로그인, ULC002: 로그아웃)
        lOpPgmLogn.setLogType("ULC001");
//		try {
        lOpPgmLognMapper.saveLOpPgmLogn(lOpPgmLogn);

        lOpPgmLogn = new LOpPgmLogn();
        lOpPgmLognMapper.saveLOpPgmLogn(lOpPgmLogn);
    }
}
