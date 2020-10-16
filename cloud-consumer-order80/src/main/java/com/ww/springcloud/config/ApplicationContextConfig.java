package com.ww.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-12 15:45
 * @describe:
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * LoadBalanced实现负载均衡，轮询去调用服务端8001和8002服务
     *              就是去自动选择调用哪个服务
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    // 乱码问题
    //@Bean
    //public RestTemplate restTemplate() {
    //    RestTemplate restTemplate = new RestTemplate();
    //    List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
    //    for (HttpMessageConverter<?> httpMessageConverter : list) {
    //        if(httpMessageConverter instanceof StringHttpMessageConverter) {
    //            ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("UTF-8"));
    //            break;
    //        }
    //    }
    //    return restTemplate;
    //}
}
