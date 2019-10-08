package com.wg.demo.po;

import lombok.Data;

/**
 * @Author: wanggang.io
 * @Date: 2019/9/11 16:30
 * @todo
 */
@Data
public class FileInfo {
    public FileInfo(){}
    public FileInfo(String fileName, String fileType, String fileUrl, String oldFileName){
        this.fileName = fileName;
        this.fileType= fileType;
        this.fileUrl = fileUrl;
        this.originFileName = oldFileName;
    }
    public String fileName;
    public String fileType;
    public String fileUrl;
    public String originFileName;
}
