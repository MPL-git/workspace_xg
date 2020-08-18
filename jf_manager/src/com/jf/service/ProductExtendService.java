package com.jf.service;

import com.jf.dao.ProductExtendMapper;
import com.jf.entity.ProductExtend;
import com.jf.entity.ProductExtendExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductExtendService extends  BaseService<ProductExtend, ProductExtendExample> {

    @Autowired
    private ProductExtendMapper productExtendMapper;

    @Autowired
    public void setProductDescPicMapper(ProductExtendMapper productExtendMapper) {
        super.setDao(productExtendMapper);
        this.productExtendMapper = productExtendMapper;
    }
}
