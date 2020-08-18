package com.jf.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface SingleProductActivityCustomMapper {

    /**
     * 下架单品活动已结束的单品款
     */
    int offlineClosedSingleProduct();

}