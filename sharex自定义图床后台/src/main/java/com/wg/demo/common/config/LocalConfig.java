package com.wg.demo.common.config;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: wanggang.io
 * @Date: 2018/9/5 10:06
 * @todo
 */
@Component
@Data
public class LocalConfig {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Value("${serverInfo.uploadpath}")
    private String uploadFilePath;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxfilesize;

    @Value("${serverInfo.baseurl}")
    private String baseurl;

    public Long getMaxFileSize(){
        String str = maxfilesize.substring(0,maxfilesize.length()-2);
        Long maxSize = 1024 * 1024 * Long.parseLong(str);
        return maxSize;
    }
    public LocalConfig() {
    }


}
