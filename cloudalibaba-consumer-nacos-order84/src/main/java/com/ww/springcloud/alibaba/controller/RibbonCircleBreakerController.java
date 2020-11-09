package com.ww.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ww.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.ww.springcloud.alibaba.myhandler.CustomerFallbackHandler;
import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-09 10:07
 * @describe: 本工程就是用ribbon和feign(负载均衡)去结合sentinel
 *            以及blockHandler和fallbackHandler的全局处理
 */

@RestController
@Slf4j
public class RibbonCircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    public String SERVICE_URL;

    @Autowired
    public RestTemplate restTemplate;

    //测试blockHandler和fallback
    @GetMapping("/consumer/ribbon_sentinel_blockHandler_fallback/{id}")
    @SentinelResource(value = "ribbon_sentinel_blockHandler_fallback",
                        blockHandlerClass = CustomerBlockHandler.class,
                        blockHandler = "blockHandler",
                        fallbackClass = CustomerFallbackHandler.class,
                        fallback = "fallbackHandler")
    public CommonResult<Package> ribbon_sentinel_blockHandler_fallback(@PathVariable("id") Long id) {
        CommonResult template = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (template.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return template;
    }
}
