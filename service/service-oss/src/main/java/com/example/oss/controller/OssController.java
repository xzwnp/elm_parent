package com.example.oss.controller;

import com.example.entity.R;
import com.example.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * com.example.oss.controller
 *
 * @author xzwnp
 * 2022/1/28
 * 19:07
 * Steps：
 */
@RestController
@RequestMapping("oss")
@CrossOrigin
public class OssController {
    @Autowired
    OssService ossService;
    //在这里可以上传文件
    //ResponseBodyAdvice会自动包装,所以返回String类型就行
    @PostMapping("saveFile")
    public String uploadOssFile(MultipartFile file){
        String url = ossService.uploadFile(file);
        return url;
    }
}
