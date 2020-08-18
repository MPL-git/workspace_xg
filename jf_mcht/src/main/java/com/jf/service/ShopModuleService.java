package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.ShopModuleMapper;
import com.jf.entity.ShopModule;
import com.jf.entity.ShopModuleDraft;
import com.jf.entity.ShopModuleDraftExample;
import com.jf.entity.ShopModuleExample;
import com.jf.entity.ShopModulePicMap;
import com.jf.entity.ShopModulePicMapDraft;
import com.jf.entity.ShopModulePicMapDraftExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShopModuleService extends BaseService<ShopModule, ShopModuleExample> {

    @Autowired
    private ShopModuleMapper dao;

    @Autowired
    private ShopModulePicMapService shopModulePicMapService;

    @Autowired
    private ShopModuleDraftService shopModuleDraftService;

    @Autowired
    private ShopModulePicMapDraftService shopModulePicMapDraftService;

    @Autowired
    public void setShopModuleMapper(ShopModuleMapper shopModuleMapper) {
        super.setDao(shopModuleMapper);
        this.dao = shopModuleMapper;
    }

    /**
     * 根据商城id删除模块
     *
     * @param mchtId
     * @param userId
     * @throws ArgException
     */
    public void deleteByMchtId(Integer mchtId, Integer userId) throws ArgException {
        ShopModuleExample shopModuleExample = new ShopModuleExample();
        ShopModuleExample.Criteria moduleExampleCriteria = shopModuleExample.createCriteria();
        moduleExampleCriteria.andMchtIdEqualTo(mchtId);
        moduleExampleCriteria.andDelFlagEqualTo("0");
        ShopModule shopModule = new ShopModule();
        shopModule.setUpdateBy(userId);
        shopModule.setUpdateDate(new Date());
        shopModule.setDelFlag("1");
        dao.updateByExampleSelective(shopModule, shopModuleExample);
    }

    /**
     * 保存店铺模块以及模块图片热区表
     *
     * @param mchtId
     * @param userId
     * @param date
     * @param shopDecorateAreaId
     */
    public void saveModuleAndPicMap(Integer mchtId, Integer userId, Date date, Integer shopDecorateAreaId) {
        // 先删除店铺模块图片热区表中的数据 然后删除店铺模块表中的数据
        shopModulePicMapService.deleteByMchtId(mchtId, userId);
        this.deleteByMchtId(mchtId, userId);
        // 从草稿表批量copy数据到正式表
        ShopModuleDraftExample shopModuleDraftExample = new ShopModuleDraftExample();
        ShopModuleDraftExample.Criteria draftExampleCriteria = shopModuleDraftExample.createCriteria();
        draftExampleCriteria.andMchtIdEqualTo(mchtId);
        draftExampleCriteria.andDelFlagEqualTo("0");
        List<ShopModuleDraft> shopModuleDrafts = shopModuleDraftService.selectByExample(shopModuleDraftExample);
        for (ShopModuleDraft shopModuleDraft : shopModuleDrafts) {
            // 店铺模块表
            ShopModule shopModule = new ShopModule();
            shopModule.setMchtId(mchtId);
            shopModule.setShopDecorateAreaId(shopDecorateAreaId);
            shopModule.setModuleType(shopModuleDraft.getModuleType());
            shopModule.setPic(shopModuleDraft.getPic());
            shopModule.setSeqNo(shopModuleDraft.getSeqNo());
            shopModule.setCreateBy(userId);
            shopModule.setCreateDate(date);
            shopModule.setUpdateBy(shopModuleDraft.getUpdateBy());
            shopModule.setUpdateDate(shopModuleDraft.getUpdateDate());
            shopModule.setRemarks(shopModuleDraft.getRemarks());
            shopModule.setDelFlag("0");
            dao.insertSelective(shopModule);

            // 店铺模块图片热区表
            ShopModulePicMapDraftExample shopModulePicMapDraftExample = new ShopModulePicMapDraftExample();
            ShopModulePicMapDraftExample.Criteria picMapDraftExampleCriteria = shopModulePicMapDraftExample.createCriteria();
            picMapDraftExampleCriteria.andMchtIdEqualTo(mchtId);
            picMapDraftExampleCriteria.andShopModuleIdEqualTo(shopModuleDraft.getId());
            picMapDraftExampleCriteria.andDelFlagEqualTo("0");
            List<ShopModulePicMapDraft> shopModulePicMapDrafts = shopModulePicMapDraftService.selectByExample(shopModulePicMapDraftExample);
            for (ShopModulePicMapDraft shopModulePicMapDraft: shopModulePicMapDrafts) {
                ShopModulePicMap shopModulePicMap = new ShopModulePicMap();
                shopModulePicMap.setMchtId(mchtId);
                shopModulePicMap.setShopModuleId(shopModule.getId());
                shopModulePicMap.setCoords(shopModulePicMapDraft.getCoords());
                shopModulePicMap.setLinkType(shopModulePicMapDraft.getLinkType());
                shopModulePicMap.setLinkValue(shopModulePicMapDraft.getLinkValue());
                shopModulePicMap.setCreateBy(userId);
                shopModulePicMap.setCreateDate(date);
                shopModulePicMap.setUpdateBy(shopModulePicMapDraft.getUpdateBy());
                shopModulePicMap.setUpdateDate(shopModulePicMapDraft.getUpdateDate());
                shopModulePicMap.setRemarks(shopModulePicMapDraft.getRemarks());
                shopModulePicMap.setDelFlag("0");
                shopModulePicMapService.insertSelective(shopModulePicMap);
            }
        }
    }
}
