package com.jf.service;

import com.jf.dao.ProductUpperLowerLogMapper;
import com.jf.entity.ProductUpperLowerLog;
import com.jf.entity.ProductUpperLowerLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductUpperLowerLogService extends BaseService<ProductUpperLowerLog, ProductUpperLowerLogExample> {
    @Autowired
    private ProductUpperLowerLogMapper productUpperLowerLogMapper;

    @Autowired
    public void setactivityAreaMapper(ProductUpperLowerLogMapper productUpperLowerLogMapper) {
        super.setDao(productUpperLowerLogMapper);
        this.productUpperLowerLogMapper = productUpperLowerLogMapper;
    }
}