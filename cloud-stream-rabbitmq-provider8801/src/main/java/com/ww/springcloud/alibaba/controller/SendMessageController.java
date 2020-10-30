package com.ww.springcloud.alibaba.controller;

import com.ww.springcloud.service.IMessageProvideService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-29 14:34
 * @describe:
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvideService messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage()
    {
        return messageProvider.send();
    }

}
