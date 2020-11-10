package com.xuecheng.test.fdfsdfs;

import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestfastDfs {
    @Test
    public void testUpload() {
        try {
            //加载配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties.properties");
            //定义trackerClient，用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //连接trackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取storeStorage
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            //创建storageClient1
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storeStorage);
            //本地路径
            String file = "F:\\图片\\绿色图片01.jpg";
            //向stroger服务器上穿文件,拿到文件路径ID
            String fileId = storageClient1.upload_appender_file1(file, "jpg", null);
            System.out.println(fileId);//group1/M00/00/01/wKgZmV6JWSaERgpoAAAAAIX6QEY000.jpg
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDownload_file() {
        try {
            //加载配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties.properties");
            //定义trackerClient，用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //连接trackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取storeStorage
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            //创建storageClient1
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storeStorage);
            String fileId = "group1/M00/00/01/wKgZmV6JWSaERgpoAAAAAIX6QEY000.jpg";
            byte[] bytes = storageClient1.download_file1(fileId);
            FileOutputStream fileOutputStream = new FileOutputStream("e:/logo.jpg");
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
