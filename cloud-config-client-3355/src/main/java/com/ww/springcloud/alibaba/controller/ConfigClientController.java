package com.ww.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-27 10:07
 * @describe:
 */

@RestController
@RefreshScope   //Spring Cloud Bus消息总线会触发RefreshScope刷新配置文件
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    //这里访问的是http://localhost:3344/main/config-dev.yml里面的内容，即github上面文件里的内容
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String configInfo()
    {
        return "serverPort: "+serverPort+"\t\n\n configInfo: "+configInfo;
    }
}
