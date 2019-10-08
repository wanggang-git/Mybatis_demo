package com.wg.demo.service;

import com.wg.demo.common.ResultMsg;
import com.wg.demo.common.config.LocalConfig;
import com.wg.demo.common.util.DateUtils;
import com.wg.demo.po.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: wanggang.io
 * @Date: 2019/8/8 14:01
 * @todo
 */
@Service
public class FileUploadService {

    @Autowired
    private LocalConfig localConfig;

    public ResultMsg uploadFile(String sendId, MultipartFile file){
        String sepa = File.separator;
        String fileName = file.getOriginalFilename();
        String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileNamePrefix = fileName.substring(0 , fileName.lastIndexOf("."));
        String timeStr = ""+ DateUtils.getDayStr(new Date());
        String newFileName = fileNamePrefix + "-"+ sendId +"-"+ DateUtils.getDateStr(new Date())  + "." + fileNameSuffix;
        String baseFilePath = localConfig.getUploadFilePath();
        String path = baseFilePath + sendId+ sepa + timeStr;

        createDirectory(path);

        File targetFile = new File(path + sepa + newFileName);
        try {
            //将上传文件写到服务器上指定的文件
            file.transferTo(targetFile);
            String saveUrl =localConfig.getBaseurl()+ sendId+ "/" + timeStr + "/" + newFileName;
            FileInfo fileInfo = new FileInfo(newFileName,fileNameSuffix,saveUrl,fileName);
            return ResultMsg.getMsg(fileInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultMsg.getFailedMsg(e.getMessage());
        }
    }

    public void createDirectory(String directoryPath){
        File targetFile = new File(directoryPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
    }
}
