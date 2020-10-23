package com.ww.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-22 18:14
 * @describe:
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        //把所有日志都打印出来
        return Logger.Level.FULL;
    }
}
