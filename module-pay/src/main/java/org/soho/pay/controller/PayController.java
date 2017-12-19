package org.soho.pay.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soho.pay.service.ExPayService;
import org.soho.pay.vo.PayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhuozl on 17-4-26.
 * @RequestMapping(value = "/api/v1")
 */
@RestController
@Api(tags = "Pay模块")
public class PayController {
    private final static Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private ExPayService exPayService;

    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询付款")
    public PayVo getPay(@PathVariable String id) {
        PayVo vo = exPayService.getExPayById(id);
        return vo;
    }

    /**
     * 新增付款
     * @param vo
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ApiOperation(value = "新增付款")
    public PayVo postPay(@RequestBody PayVo vo) {
        exPayService.insertExPay(vo);
        return vo;
    }


    /**
     * 修改付款
     * @param vo
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.PUT)
    @ApiOperation(value = "修改付款")
    public PayVo putPay(@RequestBody PayVo vo) {
        exPayService.updateExPay(vo);
        return vo;
    }

    /**
     * 支付付款
     * 支付付款
     * @param userId 用户ID
     * @param money 　支付money
     * @return
     */
    @RequestMapping(value = "/paymoney", method = RequestMethod.GET)
    @ApiOperation(value = "支付付款")
    public String putPaymoney(@RequestParam(value = "userid") String userId, @RequestParam double money) {
        logger.info("用户支付中........");
        try {
           exPayService.updatePayMoney(userId, money);
       }catch (Exception ex) {
           return "failure";
       }
        return "success";
    }
}
