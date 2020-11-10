import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.ManageCmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

//@SpringBootTest(classes = ManageCmsApplication.class)
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
@SuppressWarnings({"rawtypes", "unchecked"})
public class TestRestTemplate {
    @Autowired
    private RestTemplate restTemplate;

@Autowired
    private  RestTemplate restTemplate1;
    @Test
    public void testRestTemplate() {
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f",
                Map.class);
        System.out.println(forEntity);

    }
@Test
    public void testRebbin(){
        String serviceId="XC-SERVICE-MANAGE-CMS";
    ResponseEntity<CmsPage> forEntity = restTemplate.getForEntity("http://" + serviceId
            + "/cms/page/get/5a754adf6abb500ad05688d9", CmsPage.class);
    CmsPage body = forEntity.getBody();
    System.out.println(body);
}

}
