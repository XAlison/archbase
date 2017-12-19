package org.soho.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhuozl on 17-3-8.
 */
@FeignClient(value = "MODULE-PAY", fallback = PayClientHystrix.class)
public interface PayClient {
    @RequestMapping(value = "/pay/paymoney" , method = RequestMethod.GET)
    public String postPurchaseOrder(@RequestParam(value = "userid") String userId,
            @RequestParam(value = "money") Double money);
}
