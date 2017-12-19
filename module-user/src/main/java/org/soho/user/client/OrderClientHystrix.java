package org.soho.user.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soho.user.vo.OrderVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhuozl on 17-3-8.
 */
@Component
public class OrderClientHystrix implements OrderClient {
    private final static Logger logger = LoggerFactory.getLogger(OrderClientHystrix.class);

    @Override
    public String postPurchaseOrder(@RequestHeader(value = "Access-Token", required = false) String token,@RequestParam(value = "userid") String userId, @RequestParam(value = "money") Double money) {
        logger.info("error: 调用MODULE-ORDER失败............");
        return "调用MODULE-ORDER失败";
    }

    @Override
    public String postPurchaseOrderVo(@RequestBody OrderVo vo){
        logger.info("error: Purchase vo............");
        return "error";
    }

}