package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.CollectionUtil;
import com.jf.dao.OrderDtlExtendMapper;
import com.jf.entity.OrderDtlExtend;
import com.jf.entity.OrderDtlExtendExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2020/8/6
 */
@Service
public class OrderDtlExtendService extends BaseService<OrderDtlExtend, OrderDtlExtendExample> {

    @Autowired
    private OrderDtlExtendMapper orderDtlExtendMapper;

    @Autowired
    public void setDao() {
        super.setDao(orderDtlExtendMapper);
    }

    @Transactional
    public OrderDtlExtend getByOrderDtlId(Integer orderDtlId, Integer memberId) {
        OrderDtlExtendExample example = new OrderDtlExtendExample();
        example.createCriteria()
                .andOrderDtlIdEqualTo(orderDtlId)
                .andDelFlagEqualTo(StateConst.FALSE);
        List<OrderDtlExtend> list = this.selectByExample(example);
        if (CollectionUtil.isEmpty(list)) {
            OrderDtlExtend extend = new OrderDtlExtend();
            extend.setCreateBy(memberId);
            extend.setCreateDate(new Date());
            extend.setDelFlag(StateConst.FALSE);
            extend.setManageSelfFreight(BigDecimal.ZERO);
            extend.setOrderDtlId(orderDtlId);
            this.insertSelective(extend);
            return extend;
        }
        return list.get(0);
    }
}
