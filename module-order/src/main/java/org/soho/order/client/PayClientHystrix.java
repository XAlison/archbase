package org.soho.order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhuozl on 17-3-8.
 */
@Component
public class PayClientHystrix implements PayClient {
    private final static Logger logger = LoggerFactory.getLogger(PayClientHystrix.class);

    @Override
    public String postPurchaseOrder(@RequestParam(value = "userid") String userId, @RequestParam(value = "money") Double money) {
        logger.info("error: 调用MODULE-PAY失败............");
        return "调用MODULE-PAY失败";
    }
}