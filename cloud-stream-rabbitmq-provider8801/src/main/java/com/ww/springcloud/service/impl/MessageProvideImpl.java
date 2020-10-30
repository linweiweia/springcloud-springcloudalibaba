package com.ww.springcloud.service.impl;

import com.ww.springcloud.service.IMessageProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-29 14:20
 * @describe:  交换机8801和8802、8804在配置文件已经配置好了都是studyExchange，所以这里是配置是提供发还是消费方还有通道
 */
@EnableBinding(Source.class)    //允许绑定通道，参数为Source提供方， sink为消费方
public class MessageProvideImpl implements IMessageProvideService {

    @Autowired
    private MessageChannel output;  //定义消息通道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "+serial);
        return null;
    }
}
