package test.freemarker.test;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import test.freemarker.model.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTemplates {

    @Test
    public void testGenerateHtml() throws IOException, TemplateException {
        Configuration configuration=new Configuration(Configuration.getVersion());

        String path = this.getClass().getResource("/").getPath();

        configuration.setDirectoryForTemplateLoading(new File(path+"/templates/"));
        Template template = configuration.getTemplate("test.ftl","utf-8");
        Map<String, Object> map = getMap();
        String intoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        InputStream inputStream= IOUtils.toInputStream(intoString);
        FileOutputStream outputStream = new FileOutputStream(new File("E:\\XcEduCode\\test_freemarker\\src\\main\\resources\\templates\\test.html"));
        IOUtils.copy(inputStream,outputStream);

    }

    @Test
    public void testGeneratetoString() throws IOException, TemplateException {
        Configuration configuration=new Configuration(Configuration.getVersion());
        String templateString="" +
                "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                " 名称：${name}\n" +
                " </body>\n" +
                "</html>";
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate("template",templateString);
        configuration.setTemplateLoader(templateLoader);
        Template template = configuration.getTemplate("template", "utf-8");

        Map<String, Object> map = getMap();
        String intoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        InputStream inputStream= IOUtils.toInputStream(intoString);
        FileOutputStream outputStream = new FileOutputStream(new File("E:\\XcEduCode\\test_freemarker\\src\\main\\resources\\templates\\test1.html"));
        IOUtils.copy(inputStream,outputStream);


    }
    public Map<String,Object> getMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","黑马程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
//向数据模型放数据
        map.put("stus",stus);
//准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
//向数据模型放数据
        map.put("stu1",stu1);
//向数据模型放数据
        map.put("stuMap",stuMap);
        map.put("point", 102920122);
        return map;
    }
}
