package com.ww.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-09 11:06
 * @describe: FallbackHandler与主业务解耦已经全局统一指定
 */
public class CustomerFallbackHandler {

    //fallback方法记得是静态方法
    public static CommonResult fallbackHandler(@PathVariable  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }

}
