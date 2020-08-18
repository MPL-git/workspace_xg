package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.ShopDecorateAreaDraftMapper;
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
public class ShopDecorateAreaDraftService extends BaseService<ShopDecorateAreaDraft, ShopDecorateAreaDraftExample> {

    @Autowired
    private ShopDecorateAreaDraftMapper dao;

    @Autowired
    private ShopDecorateAreaService shopDecorateAreaService;

    @Autowired
    public void setShopDecorateAreaDraftMapper(ShopDecorateAreaDraftMapper shopDecorateAreaDraftMapper) {
        super.setDao(shopDecorateAreaDraftMapper);
        this.dao = shopDecorateAreaDraftMapper;
    }

    /**
     * 初始化店铺装修分区表(草稿表)
     *
     * @param shopDecorateAreaDraft
     * @throws ArgException
     */
    public void saveDecorateAreaDraft(ShopDecorateAreaDraft shopDecorateAreaDraft) throws ArgException {
        Date date = new Date();
        shopDecorateAreaDraft.setCreateDate(date);
        shopDecorateAreaDraft.setUpdateDate(date);
        shopDecorateAreaDraft.setDelFlag("0");
        dao.insertSelective(shopDecorateAreaDraft);
    }

    /**
     * 还原店铺装修分区表
     *
     * @param mchtId
     * @param userId
     * @param shopDecorateInfoDraftId shopDecorateInfo草稿表id
     * @param shopDecorateInfoId shopDecorateInfo正式表id
     * @param date
     * @return
     */
    public ShopDecorateAreaDraft restoreShopDecorateArea(Integer mchtId, Integer userId, Integer shopDecorateInfoDraftId, Integer shopDecorateInfoId, Date date) throws ArgException {
        // 通过商家id以及店铺装修信息id查询店铺装修分区表正式表是否有此商家的装修数据存在  不存在则insert数据 存在则更新
        ShopDecorateAreaDraftExample decorateAreaDraftExample = new ShopDecorateAreaDraftExample();
        ShopDecorateAreaDraftExample.Criteria areaDraftExampleCriteria = decorateAreaDraftExample.createCriteria();
        areaDraftExampleCriteria.andMchtIdEqualTo(mchtId);
        areaDraftExampleCriteria.andShopDecorateInfoIdEqualTo(shopDecorateInfoDraftId);
        List<ShopDecorateAreaDraft> shopDecorateAreaDrafts = dao.selectByExample(decorateAreaDraftExample);
        ShopDecorateAreaDraft shopDecorateAreaDraft = shopDecorateAreaDrafts.get(0);

        // 查询正式表数据 复制到草稿表中
        ShopDecorateAreaExample decorateAreaExample = new ShopDecorateAreaExample();
        ShopDecorateAreaExample.Criteria decorateAreaExampleCriteria = decorateAreaExample.createCriteria();
        decorateAreaExampleCriteria.andMchtIdEqualTo(mchtId);
        decorateAreaExampleCriteria.andShopDecorateInfoIdEqualTo(shopDecorateInfoId);
        decorateAreaExampleCriteria.andDelFlagEqualTo("0");
        List<ShopDecorateArea> shopDecorateAreaList = shopDecorateAreaService.selectByExample(decorateAreaExample);
        if (shopDecorateAreaList.isEmpty()) {
            throw new ArgException("店铺装修分区数据错误");
        }
        ShopDecorateArea shopDecorateArea = shopDecorateAreaList.get(0);
        shopDecorateAreaDraft.setShopDecorateInfoId(shopDecorateInfoDraftId);
        shopDecorateAreaDraft.setMchtId(mchtId);
        shopDecorateAreaDraft.setAreaName(shopDecorateArea.getAreaName());
        shopDecorateAreaDraft.setSeqNo(shopDecorateArea.getSeqNo());
        shopDecorateAreaDraft.setCreateBy(userId);
        shopDecorateAreaDraft.setCreateDate(date);
        shopDecorateAreaDraft.setUpdateBy(shopDecorateArea.getUpdateBy());
        shopDecorateAreaDraft.setUpdateDate(shopDecorateArea.getUpdateDate());
        shopDecorateAreaDraft.setRemarks(shopDecorateArea.getRemarks());
        shopDecorateAreaDraft.setDelFlag("0");
        dao.updateByPrimaryKeySelective(shopDecorateAreaDraft);
        return shopDecorateAreaDraft;
    }
}
