package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.exception.SystemException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 */
@Slf4j
@RestController
@Api(value = "/common" ,tags = "文件上传和下载")
@RequestMapping("/common")
public class CommonController {

    @Value("${takeout.path}")
    private String baseDir;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = UUID.randomUUID() + substring;

        String path = CommonController.class.getResource("/").getPath();
        File dir = new File(path + baseDir);
        //判断当前目录是否存在
        if (!dir.exists()) {
            //目录不存在，需要创建
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(dir, fileName));
        } catch (IOException e) {
            throw new SystemException("服务器写入磁盘文件失败", e);
        }
        log.info("保存至———>" + path + baseDir);
        return R.success(fileName);
    }


    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @ApiOperation("文件下载")
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        //获取上传的地址
        String path = CommonController.class.getResource("/").getPath();
        log.info(path);
        String filePath = path + baseDir + "/" + name;

        //获取文件传入流
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            IOUtils.copy(fileInputStream,response.getOutputStream());
        } catch (IOException e) {
            throw new SystemException("获取文件失败", e);
        }
    }
}
