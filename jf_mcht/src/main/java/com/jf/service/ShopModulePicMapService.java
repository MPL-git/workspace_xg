package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.ShopModulePicMapMapper;
import com.jf.entity.ShopModulePicMap;
import com.jf.entity.ShopModulePicMapExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ShopModulePicMapService extends BaseService<ShopModulePicMap, ShopModulePicMapExample> {

    @Autowired
    private ShopModulePicMapMapper dao;

    @Autowired
    public void setShopModulePicMapMapper(ShopModulePicMapMapper shopModulePicMapMapper) {
        super.setDao(shopModulePicMapMapper);
        this.dao = shopModulePicMapMapper;
    }

    /**
     * 根据商家id以及shopModuleId删除
     *
     * @param mchtId
     * @param userId
     */
    public void deleteByMchtId(Integer mchtId, Integer userId) throws ArgException {
        ShopModulePicMapExample example = new ShopModulePicMapExample();
        ShopModulePicMapExample.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.andDelFlagEqualTo("0");
        exampleCriteria.andMchtIdEqualTo(mchtId);
        ShopModulePicMap picMap = new ShopModulePicMap();
        picMap.setDelFlag("1");
        picMap.setUpdateDate(new Date());
        picMap.setUpdateBy(userId);
        dao.updateByExampleSelective(picMap, example);
    }
}
