package com.ww.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ww.springcloud.entities.CommonResult;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-06 15:41
 * @describe:   sentinel的blockHandler自定义限流方法（就是替代sentinel默认的），不仅是限流  写法是这样写的
 *              卧槽被坑了，如果 blockHandler的方法在同一个的处理类中一定要死静态的启动的的时候才会加载，
 *              如果不是静态的会找不到，但是如果是和业务类放一块就不用，还有就是异常参数BlockException一定一定要写。
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception)
    {
        return new CommonResult(4444,"按客戶自定义限流方法,global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException exception)
    {
        return new CommonResult(4444,"按客戶自定义限流方法,global handlerException----2");
    }

}
