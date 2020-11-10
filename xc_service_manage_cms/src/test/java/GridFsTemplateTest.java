import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.manage_cms.ManageCmsApplication;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GridFsTemplateTest {
    @Autowired
    private GridFSBucket gridFSBucket;
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Test
    public void  storeFile() throws FileNotFoundException {
        File file = new File("E:\\XcEduCode\\test_freemarker\\src\\main\\resources\\templates\\course.ftl");
        FileInputStream inputStream = new FileInputStream(file);
        ObjectId store = gridFsTemplate.store(inputStream, "course.ftl");
        System.out.println(store.toString());
    }
    @Test
    public void QueryFile() throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5e1963bcd1161c394c0d5e45")));
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        String content = IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
        System.out.println(content);
    }
    @Test
    public void testDelFile(){
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is("5e1950ded1161c0f3ce45941")));
    }
}
