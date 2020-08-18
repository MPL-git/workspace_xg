package com.jf.service;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.SvipBindProductMapper;
import com.jf.entity.SvipBindProduct;
import com.jf.entity.SvipBindProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author luoyb
 * Created on 2020/7/10
 */
@Service
public class SvipBindProductService extends AppBaseService<SvipBindProduct, SvipBindProductExample> {

    @Autowired
    private SvipBindProductMapper svipBindProductMapper;

    @Autowired
    public void setMapper() {
        super.setDao(svipBindProductMapper);
    }


    public List<SvipBindProduct> findByProductIds(List<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) return Collections.emptyList();

        SvipBindProductExample example = new SvipBindProductExample();
        example.createCriteria()
                .andProductIdIn(productIds)
                .andDelFlagEqualTo(StateConst.FALSE);
        return this.selectByExample(example);
    }

    public boolean isProductBind(int productId) {
        SvipBindProductExample example = new SvipBindProductExample();
        example.createCriteria()
                .andProductIdEqualTo(productId)
                .andDelFlagEqualTo(StateConst.FALSE);
        return this.countByExample(example) > 0;
    }
}
