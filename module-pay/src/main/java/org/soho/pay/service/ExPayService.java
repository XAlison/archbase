package org.soho.pay.service;

import org.soho.common.dao.BaseDao;
import org.soho.common.service.BaseService;
import org.soho.pay.repository.ExPayDao;
import org.soho.pay.domain.ExPay;
import org.soho.pay.vo.PayVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhuozl on 17-4-27.
 */
@Service
public class ExPayService extends BaseService<ExPay> {

    @Autowired
    private ExPayDao payDao;
    @Override
    public BaseDao<ExPay> getDao() {
        return payDao;
    }

    /**
     * 获得Pay信息
     * @param id
     * @return
     */
    public PayVo getExPayById(String id){
        PayVo vo = new PayVo();
        ExPay exPay = payDao.getByID(id);
        BeanUtils.copyProperties(exPay,vo);
        return vo;
    }


    /**
     * 新增Pay
     * @param vo
     * @return
     */
    public PayVo insertExPay(PayVo vo){
        payDao.insert(vo);
        return vo;
    }

    /**
     * 修改Pay
     * @param vo
     * @return
     */
    public PayVo updateExPay(PayVo vo){
        payDao.update(vo);
        return vo;
    }


    /**
     * 支付付款
     * @param userId 用户ID
     * @param money 　支付money
     * @return
     */
    public PayVo updatePayMoney(String userId,double money){
        PayVo vo = new PayVo();
        ExPay expay = payDao.getPayByUserId(userId);
        double amount = expay.getAmount() - money;
        expay.setAmount(amount);
        payDao.update(expay);
        BeanUtils.copyProperties(expay,vo);
        return vo;
    }

}
