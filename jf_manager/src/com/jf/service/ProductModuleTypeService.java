package com.jf.service;

import com.jf.dao.ProductModuleTypeMapper;
import com.jf.entity.ProductModuleType;
import com.jf.entity.ProductModuleTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductModuleTypeService extends BaseService<ProductModuleType, ProductModuleTypeExample>{

    @Autowired
    private ProductModuleTypeMapper productModuleTypeMapper;

    @Autowired
    public void setProductModuleTypeMapper(ProductModuleTypeMapper productModuleTypeMapper) {
        super.setDao(productModuleTypeMapper);
        this.productModuleTypeMapper = productModuleTypeMapper;
    }
}
