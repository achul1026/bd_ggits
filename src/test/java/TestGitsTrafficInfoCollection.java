import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.neighbor21.ggits.api.module.monitoring.dto.Itemlist;
import com.neighbor21.ggits.api.module.monitoring.dto.Serviceresult;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  TestGitsTrafficInfoCollection
 * @since : 2023-08-29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/egovframework/springmvc/dispatcher-servlet.xml",
        "file:src/main/resources/spring/*.xml"}
)
@WebAppConfiguration
public class TestGitsTrafficInfoCollection {

    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    DataSource dataSource;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    String path = "C:/gitsdata/traffic";

    @Test
    public void test() throws Exception {
        // 1. 타임아웃 설정시 HttpComponentsClientHttpRequestFactory 객체를 생성합니다.
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 타임아웃 설정 5초
        factory.setReadTimeout(5000); // 타임아웃 설정 5초

        //Apache HttpComponents : 각 호스트(IP와 Port의 조합)당 커넥션 풀에 생성가능한 커넥션 수
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(50)//최대 커넥션 수
                .setMaxConnPerRoute(20).build();
        factory.setHttpClient(httpClient);

        ObjectMapper objectMapper = new ObjectMapper();;

        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.NamingBase() {
            @Override
            public String translate(String s) {
                return s;
            }
        });

        // 2. RestTemplate 객체를 생성합니다.
        RestTemplate restTemplate = new RestTemplate(factory);
        List<HttpMessageConverter<?>> httpMessageConverters =  new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConveter = new MappingJackson2HttpMessageConverter();
        jsonMessageConveter.setObjectMapper(objectMapper);
        httpMessageConverters.add(jsonMessageConveter);
        restTemplate.setMessageConverters(httpMessageConverters);
        // 3. header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text", "xml", Charset.forName("UTF-8"));
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        // 4. 요청 URL을 정의해줍니다.
        String url = "https://openapigits.gg.go.kr/api/rest/getRoadInfoList";
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("serviceKey", "72c03919776b2db8e4dd25aaebc1ae7f37bcf49").build(false);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 5. exchange() 메소드로 api를 호출합니다.
        ResponseEntity<String> response = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);

        XmlMapper mapper = new XmlMapper();
        mapper.setDefaultUseWrapper(false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        System.out.println(response.getBody());
        Serviceresult serviceresult= mapper.readValue(response.getBody(), Serviceresult.class);


        List<Itemlist> trafficInfoList = new ArrayList<>();
        for(Itemlist item : serviceresult.getMsgbody().getItemlistList()) {
            url = "https://openapigits.gg.go.kr/api/rest/getRoadLinkCongestInfo";
            uri = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("serviceKey", "72c03919776b2db8e4dd25aaebc1ae7f37bcf49")
                    .queryParam("routeId", item.getRouteid())
                    .build(false);

            ResponseEntity<String> res = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);
            Serviceresult trafficServiceResult= mapper.readValue(res.getBody(), Serviceresult.class);

            trafficInfoList.addAll(trafficServiceResult.getMsgbody().getItemlistList());
            System.out.println(res.getBody());
        }
        System.out.println("traffic info count : " + trafficInfoList.size());


        System.out.println("success");
    }
}
