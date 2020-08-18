package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ShopDecorateInfoMapper;
import com.jf.entity.ShopDecorateInfo;
import com.jf.entity.ShopDecorateInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopDecorateInfoService extends BaseService<ShopDecorateInfo, ShopDecorateInfoExample> {

    @Autowired
    private ShopDecorateInfoMapper dao;

    @Autowired
    public void setShopDecorateInfoMapper(ShopDecorateInfoMapper shopDecorateInfoMapper) {
        super.setDao(shopDecorateInfoMapper);
        this.dao = shopDecorateInfoMapper;
    }
}
