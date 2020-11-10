package com.xuecheng.manage_course;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_course.client.CmsPageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRestTemplate {
    @Autowired
    private RestTemplate restTemplate;
@Autowired
private CmsPageClient cmsPageClient;
    @Test
    public void testRabbion() {

        CmsPage byId = cmsPageClient.findById("5a754adf6abb500ad05688d9");
        System.out.println(byId);
    }
    @Test
    public void testCourseBaseRepository() {
        String serviceId="XC-SERVICE-MANAGE-CMS";
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://" + serviceId
                + "/cms/page/get/5a754adf6abb500ad05688d9", Map.class);
        Map<String,Object> map= forEntity.getBody();
        System.out.println(map);
    }
}
