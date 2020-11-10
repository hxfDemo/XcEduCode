package com.xuecheng.api.filesystem;

import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Api(value = "文件系统管理接口",description = "文件系统管理接口，提供文件的增，删，改，查")
public interface FilesystemControllerApi {
    @ApiOperation("上传文件的接口")
    public UploadFileResult upload(@RequestParam("multipartFile") MultipartFile multipartFile,@RequestParam("filetag") String filetag,@RequestParam("businesskey") String businesskey, @RequestParam("metadata")String metadata);
}
