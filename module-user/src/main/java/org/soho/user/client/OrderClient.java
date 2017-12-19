package org.soho.user.client;

import org.soho.user.vo.OrderVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhuozl on 17-3-8.
 */
@FeignClient(value = "MODULE-ORDER", fallback = OrderClientHystrix.class)
public interface OrderClient {
    @RequestMapping(value = "/order/purchase" , method = RequestMethod.GET)
    public String postPurchaseOrder(@RequestHeader(value = "Access-Token", required = false) String token,@RequestParam(value = "userid") String userId, @RequestParam(value = "money") Double money);

    @RequestMapping(value = "/order/purchasevo" , method = RequestMethod.POST)
    public String postPurchaseOrderVo(@RequestBody OrderVo vo);

}
