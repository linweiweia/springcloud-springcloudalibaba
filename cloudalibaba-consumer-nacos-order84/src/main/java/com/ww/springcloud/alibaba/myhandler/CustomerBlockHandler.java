package com.ww.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-09 11:04
 * @describe: BlockHandler与主业务解耦已经全局统一指定
 */
public class CustomerBlockHandler {

    //blockHandler方法
    //卧槽被坑了，如果 blockHandler的方法在同一个的处理类中一定要死静态的启动的的时候才会加载，如果不是静态的会找不到，但是如果是和业务类放一块就不用，
    // 还有就是异常参数BlockException一定一定要写
    public static CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }

}
