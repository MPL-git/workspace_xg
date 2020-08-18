package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ShopModulePicMapDraftMapper;
import com.jf.entity.ShopModulePicMapDraft;
import com.jf.entity.ShopModulePicMapDraftExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ShopModulePicMapDraftService extends BaseService<ShopModulePicMapDraft, ShopModulePicMapDraftExample> {

    @Autowired
    private ShopModulePicMapDraftMapper dao;

    @Autowired
    public void setShopModulePicMapDraftMapper(ShopModulePicMapDraftMapper shopModulePicMapDraftMapper) {
        super.setDao(shopModulePicMapDraftMapper);
        this.dao = shopModulePicMapDraftMapper;
    }

    /**
     * 保存 店铺模块图片热区表(草稿表)
     *
     * @param mchtId
     * @param shopModuleId
     * @param userId
     * @param moduleMapJsonStr
     */
    public void saveModulePicMapDrafr(Integer mchtId, Integer shopModuleId, Integer userId, String moduleMapJsonStr) throws ArgException {
        // 先根据商城id和模块id删除 然后再新增
        this.deleteByMchtIdAndShopModuleId(mchtId, shopModuleId, userId);
        // 解析json 新增 店铺模块图片热区表(草稿表)
        if (!StringUtil.isEmpty(moduleMapJsonStr)) {
            JSONArray ja = JSONArray.fromObject(moduleMapJsonStr);
            for (int i = 0; i < ja.size(); i++) {
                ShopModulePicMapDraft picMap = new ShopModulePicMapDraft();
                JSONObject jo = (JSONObject) ja.get(i);
                String x1 = jo.getString("x1");
                String y1 = jo.getString("y1");
                String x2 = jo.getString("x2");
                String y2 = jo.getString("y2");
                String coords = x1 + "," + y1 + "," + x2 + "," + y2;
                String linkType = jo.getString("linkType");
                String linkValue = jo.getString("linkValue");
                picMap.setDelFlag("0");
                picMap.setCreateBy(userId);
                picMap.setCreateDate(new Date());
                picMap.setCoords(coords);
                picMap.setLinkType(linkType);
                picMap.setLinkValue(linkValue);
                picMap.setShopModuleId(shopModuleId);
                picMap.setMchtId(mchtId);
                dao.insertSelective(picMap);
            }
        }
    }

    /**
     * 根据商家id以及shopModuleId删除
     *
     * @param mchtId
     * @param shopModuleId
     * @param userId
     */
    public void deleteByMchtIdAndShopModuleId(Integer mchtId, Integer shopModuleId, Integer userId) throws ArgException {
        ShopModulePicMapDraftExample draftExample = new ShopModulePicMapDraftExample();
        ShopModulePicMapDraftExample.Criteria draftExampleCriteria = draftExample.createCriteria();
        draftExampleCriteria.andDelFlagEqualTo("0");
        draftExampleCriteria.andShopModuleIdEqualTo(shopModuleId);
        draftExampleCriteria.andMchtIdEqualTo(mchtId);
        ShopModulePicMapDraft picMapDraft = new ShopModulePicMapDraft();
        picMapDraft.setDelFlag("1");
        picMapDraft.setUpdateDate(new Date());
        picMapDraft.setUpdateBy(userId);
        dao.updateByExampleSelective(picMapDraft, draftExample);
    }

    /**
     * 根据商家id以及shopModuleId删除
     *
     * @param mchtId
     * @param userId
     */
    public void deleteByMchtId(Integer mchtId, Integer userId) throws ArgException {
        ShopModulePicMapDraftExample draftExample = new ShopModulePicMapDraftExample();
        ShopModulePicMapDraftExample.Criteria draftExampleCriteria = draftExample.createCriteria();
        draftExampleCriteria.andDelFlagEqualTo("0");
        draftExampleCriteria.andMchtIdEqualTo(mchtId);
        ShopModulePicMapDraft picMapDraft = new ShopModulePicMapDraft();
        picMapDraft.setDelFlag("1");
        picMapDraft.setUpdateDate(new Date());
        picMapDraft.setUpdateBy(userId);
        dao.updateByExampleSelective(picMapDraft, draftExample);
    }
}
