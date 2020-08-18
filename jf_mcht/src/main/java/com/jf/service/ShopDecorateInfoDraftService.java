package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ShopDecorateInfoDraftMapper;
import com.jf.entity.ShopDecorateInfoDraft;
import com.jf.entity.ShopDecorateInfoDraftExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ShopDecorateInfoDraftService extends BaseService<ShopDecorateInfoDraft, ShopDecorateInfoDraftExample> {

    @Autowired
    private ShopDecorateInfoDraftMapper dao;

    @Autowired
    public void setShopDecorateInfoDraftMapper(ShopDecorateInfoDraftMapper shopDecorateInfoDraftMapper) {
        super.setDao(shopDecorateInfoDraftMapper);
        this.dao = shopDecorateInfoDraftMapper;
    }

    /**
     * 初始化店铺装修信息表(草稿表)
     *
     * @param shopDecorateInfoDraft
     */
    public void saveDecorateInfoDraft(ShopDecorateInfoDraft shopDecorateInfoDraft) {
        Date date = new Date();
        shopDecorateInfoDraft.setCreateDate(date);
        shopDecorateInfoDraft.setUpdateDate(date);
        shopDecorateInfoDraft.setDelFlag("0");
        dao.insertSelective(shopDecorateInfoDraft);
    }

}
