import com.xuecheng.manage_cms.ManageCmsApplication;
import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
@SuppressWarnings({"rawtypes", "unchecked"})
public class PageServiceTest {


    @Autowired
    private PageService pageService;


    @Test
    public void testGetPageHtml() {
        String pageHtml = pageService.getPageHtml("5e10aca2d1161c1924f9212d");
        System.out.println(pageHtml);
    }


}
