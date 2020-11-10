import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.manage_cms.ManageCmsApplication;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CmsPageRepositoryTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll() {
        List<CmsPage> all = cmsPageRepository.findAll();
        for (CmsPage cmsPage : all) {

            System.out.println(cmsPage);
        }
    }
    @Test
    public void testFindAllByExample() {
        int page =0;
        int size=10;

        Pageable pageable= PageRequest.of(page, size);

        CmsPage cmsPage = new CmsPage();//条件值对象
//        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
//        cmsPage.setTemplateId("5ad9a24d68db5239b8fef199");
        cmsPage.setPageAliase("轮播");
        //条件匹配器。
        ExampleMatcher matching = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<CmsPage> example = Example.of(cmsPage, matching);

        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        List<CmsPage> list = all.getContent();
        for (CmsPage cmsPage1 : list) {
            System.out.println(cmsPage1);
        }

    }
    @Test
    public void testFindPage() {


        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<CmsPage> all = cmsPageRepository.findAll(pageRequest);
        for (CmsPage cmsPage : all) {

            System.out.println(cmsPage);
        }
    }

    @Test
    public void testUpdate() {
        Optional<CmsPage> optional = cmsPageRepository.findById("5ae1979f0e6618644cd7a6fd");
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            cmsPage.setPageAliase("test01");
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);

        }
        ;
    }

    @Test
    public void findBypageAlise() {
        CmsPage byPageAliase = cmsPageRepository.findByPageAliase("test01");
        System.out.println(byPageAliase);
    }

    @Test
    public void deletebyId() {
        cmsPageRepository.deleteById("5d496538f00e0b374c25b748");

    }

    @Test
    public void insertInto() {
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);

    }

}
