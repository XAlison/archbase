package org.soho.order.service;

import org.soho.common.dao.BaseDao;
import org.soho.common.service.BaseService;
import org.soho.order.repository.ExOrderDao;
import org.soho.order.domain.ExOrder;
import org.soho.order.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhuozl on 17-4-27.
 */
@Service
public class ExOrderService extends BaseService<ExOrder> {

    @Autowired
    private ExOrderDao exOrderDao;
    @Override
    public BaseDao<ExOrder> getDao() {
        return exOrderDao;
    }

    /**
     * 获得Pay信息
     * @param id
     * @return
     */
    public OrderVo getExOrderById(String id){
        OrderVo vo = new OrderVo();
        ExOrder exOrder = exOrderDao.getByID(id);
        BeanUtils.copyProperties(exOrder,vo);
        return vo;
    }


    /**
     * 新增Order
     * @param vo
     * @return
     */
    public OrderVo insertExPay(OrderVo vo){
        exOrderDao.insert(vo);
        return vo;
    }

    /**
     * 修改Order
     * @param vo
     * @return
     */
    public OrderVo updateExPay(OrderVo vo){
        exOrderDao.update(vo);
        return vo;
    }

}
