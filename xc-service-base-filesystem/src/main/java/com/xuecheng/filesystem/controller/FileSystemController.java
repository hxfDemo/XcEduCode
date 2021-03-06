package com.xuecheng.filesystem.controller;

import com.xuecheng.api.filesystem.FilesystemControllerApi;
import com.xuecheng.filesystem.service.FileSystemService;
import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/filesystem")
public class FileSystemController implements FilesystemControllerApi {
    @Autowired

    private FileSystemService fileSystemService;
    @Override
    @PostMapping("/upload")
    public UploadFileResult upload(MultipartFile multipartFile, String filetag, String businesskey, String metadata) {
        UploadFileResult upload = fileSystemService.upload(multipartFile, filetag, businesskey, metadata);
        return upload;
    }
}
