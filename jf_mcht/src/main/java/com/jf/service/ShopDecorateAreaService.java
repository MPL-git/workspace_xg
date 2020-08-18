package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.ShopDecorateAreaMapper;
import com.jf.entity.ShopDecorateArea;
import com.jf.entity.ShopDecorateAreaDraft;
import com.jf.entity.ShopDecorateAreaDraftExample;
import com.jf.entity.ShopDecorateAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShopDecorateAreaService extends BaseService<ShopDecorateArea, ShopDecorateAreaExample> {

    @Autowired
    private ShopDecorateAreaMapper dao;

    @Autowired
    private ShopDecorateAreaDraftService shopDecorateAreaDraftService;

    @Autowired
    public void setShopDecorateAreaMapper(ShopDecorateAreaMapper shopDecorateAreaMapper) {
        super.setDao(shopDecorateAreaMapper);
        this.dao = shopDecorateAreaMapper;
    }

    /**
     * 保存店铺装修分区表
     *
     * @param mchtId
     * @param userId
     * @param shopDecorateInfoDraftId shopDecorateInfo草稿表id
     * @param shopDecorateInfoId shopDecorateInfo正式表id
     * @param date
     * @return
     */
    public ShopDecorateArea saveShopDecorateArea(Integer mchtId, Integer userId, Integer shopDecorateInfoDraftId, Integer shopDecorateInfoId, Date date) throws ArgException {
        // 通过商家id以及店铺装修信息id查询店铺装修分区表正式表是否有此商家的装修数据存在  不存在则insert数据 存在则更新
        ShopDecorateAreaExample decorateAreaExample = new ShopDecorateAreaExample();
        ShopDecorateAreaExample.Criteria decorateAreaExampleCriteria = decorateAreaExample.createCriteria();
        decorateAreaExampleCriteria.andMchtIdEqualTo(mchtId);
        decorateAreaExampleCriteria.andShopDecorateInfoIdEqualTo(shopDecorateInfoId);
        List<ShopDecorateArea> shopDecorateAreas = dao.selectByExample(decorateAreaExample);
        ShopDecorateArea shopDecorateArea = new ShopDecorateArea();
        if (!shopDecorateAreas.isEmpty()) {
            shopDecorateArea = shopDecorateAreas.get(0);
        }
        // 查询草稿表数据 复制到正式表中
        ShopDecorateAreaDraftExample decorateAreaDraftExample = new ShopDecorateAreaDraftExample();
        ShopDecorateAreaDraftExample.Criteria decorateAreaDraftExampleCriteria = decorateAreaDraftExample.createCriteria();
        decorateAreaDraftExampleCriteria.andMchtIdEqualTo(mchtId);
        decorateAreaDraftExampleCriteria.andShopDecorateInfoIdEqualTo(shopDecorateInfoDraftId);
        List<ShopDecorateAreaDraft> shopDecorateAreaDrafts = shopDecorateAreaDraftService.selectByExample(decorateAreaDraftExample);
        if (shopDecorateAreaDrafts.isEmpty()) {
            throw new ArgException("店铺装修分区数据错误");
        }
        ShopDecorateAreaDraft shopDecorateAreaDraft = shopDecorateAreaDrafts.get(0);
        shopDecorateArea.setShopDecorateInfoId(shopDecorateInfoId);
        shopDecorateArea.setMchtId(mchtId);
        shopDecorateArea.setAreaName(shopDecorateAreaDraft.getAreaName());
        shopDecorateArea.setSeqNo(shopDecorateAreaDraft.getSeqNo());
        shopDecorateArea.setCreateBy(userId);
        shopDecorateArea.setCreateDate(date);
        shopDecorateArea.setUpdateBy(shopDecorateAreaDraft.getUpdateBy());
        shopDecorateArea.setUpdateDate(shopDecorateAreaDraft.getUpdateDate());
        shopDecorateArea.setRemarks(shopDecorateAreaDraft.getRemarks());
        shopDecorateArea.setDelFlag("0");
        if (shopDecorateArea.getId() != null) {
            dao.updateByPrimaryKeySelective(shopDecorateArea);
        } else {
            dao.insertSelective(shopDecorateArea);
        }
        return shopDecorateArea;
    }

}
