package com.wg.demo.common.config;

import com.wg.demo.common.util.IdWorker;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "util")
@Data
public class CustomerConfig {
    private Long workerId;
    private Long datacenterId;

    @Bean
    public IdWorker createIdWorker() {
        IdWorker worker = new IdWorker(workerId, datacenterId);
        return worker;
    }

}
