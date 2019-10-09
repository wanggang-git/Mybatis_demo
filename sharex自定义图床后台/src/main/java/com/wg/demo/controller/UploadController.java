package com.wg.demo.controller;

import com.wg.demo.common.ResultMsg;
import com.wg.demo.common.config.LocalConfig;
import com.wg.demo.po.FileInfo;
import com.wg.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: wanggang.io
 * @Date: 2019/10/8 14:05
 * @todo
 */
@RestController
@RequestMapping("pic")
public class UploadController {

    @Autowired
    private LocalConfig localConfig;
    @Autowired
    private FileUploadService fileUploadService;


    @PostMapping("upload")
    public ResultMsg uploadFile(String jessionids , MultipartFile file) {
        if(jessionids == null || jessionids.isEmpty()){
            return ResultMsg.getFailedMsg("jessionids 不能为空");
        }
        if(!jessionids.equals("9be51d2f-bb1a-4c61-b989-c3d126211d661570588000594"))
            return ResultMsg.getFailedMsg("jessionids 错误");

        long size = (long) file.getSize();
        if (size > localConfig.getMaxFileSize()) {
            return ResultMsg.getMsg("上传文件过大，请上传小于100MB大小的文件");
        }

        ResultMsg resultMsg = fileUploadService.uploadFile("shareX", file);

        if (resultMsg.getResult() != "SUCCESS") {
            return ResultMsg.getFailedMsg("保存文件失败");
        }

        FileInfo fileInfo = (FileInfo) resultMsg.getData();

        resultMsg.setData(fileInfo);
        return resultMsg;
    }
}
