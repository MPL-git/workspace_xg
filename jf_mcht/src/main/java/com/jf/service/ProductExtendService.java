package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductExtendMapper;
import com.jf.entity.ProductExtend;
import com.jf.entity.ProductExtendExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductExtendService extends BaseService<ProductExtend, ProductExtendExample> {
    @Autowired
    private ProductExtendMapper dao;

    @Autowired
    public void setProductExtendMapper(ProductExtendMapper productExtendMapper) {
        super.setDao(productExtendMapper);
        this.dao = productExtendMapper;
    }
}
