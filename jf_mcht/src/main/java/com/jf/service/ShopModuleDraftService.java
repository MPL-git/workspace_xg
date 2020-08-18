package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.ShopModuleDraftMapper;
import com.jf.entity.ShopModule;
import com.jf.entity.ShopModuleDraft;
import com.jf.entity.ShopModuleDraftExample;
import com.jf.entity.ShopModuleExample;
import com.jf.entity.ShopModulePicMap;
import com.jf.entity.ShopModulePicMapDraft;
import com.jf.entity.ShopModulePicMapDraftExample;
import com.jf.entity.ShopModulePicMapExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopModuleDraftService extends BaseService<ShopModuleDraft, ShopModuleDraftExample> {

    @Autowired
    private ShopModuleDraftMapper dao;

    @Autowired
    private ShopModulePicMapDraftService shopModulePicMapDraftService;

    @Autowired
    private ShopModuleService shopModuleService;

    @Autowired
    private ShopModulePicMapService shopModulePicMapService;

    @Autowired
    public void setShopModuleDraftMapper(ShopModuleDraftMapper shopModuleDraftMapper) {
        super.setDao(shopModuleDraftMapper);
        this.dao = shopModuleDraftMapper;
    }

    /**
     * 新增/编辑模块
     *
     * @param shopModuleDraft
     * @param userId
     * @param moduleMapJsonStr
     */
    public void saveModuleDraft(ShopModuleDraft shopModuleDraft, Integer userId, String moduleMapJsonStr) throws ArgException {
        if (shopModuleDraft.getId() == null) {
            // 新增
            shopModuleDraft.setCreateBy(userId);
            shopModuleDraft.setCreateDate(new Date());
            shopModuleDraft.setDelFlag("0");
            shopModuleDraft.setModuleType("1");

            // 根据商城id和装修分区id查询 最后一条记录的seq_no
            ShopModuleDraftExample draftExample = new ShopModuleDraftExample();
            ShopModuleDraftExample.Criteria draftExampleCriteria = draftExample.createCriteria();
            draftExampleCriteria.andMchtIdEqualTo(shopModuleDraft.getMchtId());
            draftExampleCriteria.andShopDecorateAreaIdEqualTo(shopModuleDraft.getShopDecorateAreaId());
            draftExample.setOrderByClause("seq_no desc");
            List<ShopModuleDraft> shopModuleDrafts = dao.selectByExample(draftExample);
            Integer seqNo = 1;
            if (!shopModuleDrafts.isEmpty()) {
                ShopModuleDraft shopModuleDraftForSeq = shopModuleDrafts.get(0);
                seqNo += shopModuleDraftForSeq.getSeqNo();
            }

            shopModuleDraft.setSeqNo(seqNo);
            dao.insertSelective(shopModuleDraft);
        } else {
            // 编辑
            shopModuleDraft.setUpdateBy(userId);
            shopModuleDraft.setUpdateDate(new Date());
            dao.updateByPrimaryKeySelective(shopModuleDraft);
        }

        // 保存 店铺模块图片热区表(草稿表)
        shopModulePicMapDraftService.saveModulePicMapDrafr(shopModuleDraft.getMchtId(), shopModuleDraft.getId(), userId, moduleMapJsonStr);
    }

    /**
     * 上下移
     *
     * @param firstId
     * @param secondId
     * @param userId
     */
    public void changeSeqNo(Integer firstId, Integer secondId, Integer userId) throws ArgException {
        ShopModuleDraft firstModule = dao.selectByPrimaryKey(firstId);
        ShopModuleDraft secondModule = dao.selectByPrimaryKey(secondId);
        Integer firstSeqNo = firstModule.getSeqNo();
        Integer secondSeqNo = secondModule.getSeqNo();
        Date date = new Date();

        // 更新第一条记录seq_no
        ShopModuleDraft firstUpdate = new ShopModuleDraft();
        firstUpdate.setId(firstModule.getId());
        firstUpdate.setSeqNo(secondSeqNo);
        firstUpdate.setUpdateBy(userId);
        firstUpdate.setUpdateDate(date);

        // 更新第二条记录seq_no
        ShopModuleDraft secondUpdate = new ShopModuleDraft();
        secondUpdate.setId(secondModule.getId());
        secondUpdate.setSeqNo(firstSeqNo);
        secondUpdate.setUpdateBy(userId);
        secondUpdate.setUpdateDate(date);

        dao.updateByPrimaryKeySelective(firstUpdate);
        dao.updateByPrimaryKeySelective(secondUpdate);
    }

    /**
     * 删除模块
     *
     * @param moduleId
     * @param userId
     * @throws ArgException
     */
    public void deleteModule(Integer mchtId, Integer moduleId, Integer userId) throws ArgException {
        ShopModuleDraft shopModuleDraft = new ShopModuleDraft();
        shopModuleDraft.setId(moduleId);
        shopModuleDraft.setDelFlag("1");
        shopModuleDraft.setUpdateBy(userId);
        shopModuleDraft.setUpdateDate(new Date());
        dao.updateByPrimaryKeySelective(shopModuleDraft);

        shopModulePicMapDraftService.deleteByMchtIdAndShopModuleId(mchtId, moduleId, userId);
    }

    /**
     * 根据商城id删除模块
     *
     * @param mchtId
     * @param userId
     * @throws ArgException
     */
    public void deleteByMchtId(Integer mchtId, Integer userId) throws ArgException {
        ShopModuleDraftExample shopModuleDraftExample = new ShopModuleDraftExample();
        ShopModuleDraftExample.Criteria moduleExampleCriteria = shopModuleDraftExample.createCriteria();
        moduleExampleCriteria.andMchtIdEqualTo(mchtId);
        moduleExampleCriteria.andDelFlagEqualTo("0");
        ShopModuleDraft shopModuleDraft = new ShopModuleDraft();
        shopModuleDraft.setUpdateBy(userId);
        shopModuleDraft.setUpdateDate(new Date());
        shopModuleDraft.setDelFlag("1");
        dao.updateByExampleSelective(shopModuleDraft, shopModuleDraftExample);
    }

    /**
     * 保存店铺模块以及模块图片热区表
     *
     * @param mchtId
     * @param userId
     * @param date
     * @param shopDecorateAreaId
     */
    public void restoreModuleAndPicMap(Integer mchtId, Integer userId, Date date, Integer shopDecorateAreaId) {
        // 先删除店铺模块图片热区表中的数据 然后删除店铺模块表中的数据
        shopModulePicMapDraftService.deleteByMchtId(mchtId, userId);
        this.deleteByMchtId(mchtId, userId);
        // 从草稿表批量copy数据到正式表
        ShopModuleExample shopModuleExample = new ShopModuleExample();
        ShopModuleExample.Criteria moduleExampleCriteria = shopModuleExample.createCriteria();
        moduleExampleCriteria.andMchtIdEqualTo(mchtId);
        moduleExampleCriteria.andDelFlagEqualTo("0");
        List<ShopModule> shopModules = shopModuleService.selectByExample(shopModuleExample);
        for (ShopModule shopModule : shopModules) {
            // 店铺模块表
            ShopModuleDraft shopModuleDraft = new ShopModuleDraft();
            shopModuleDraft.setMchtId(mchtId);
            shopModuleDraft.setShopDecorateAreaId(shopDecorateAreaId);
            shopModuleDraft.setModuleType(shopModule.getModuleType());
            shopModuleDraft.setPic(shopModule.getPic());
            shopModuleDraft.setSeqNo(shopModule.getSeqNo());
            shopModuleDraft.setCreateBy(userId);
            shopModuleDraft.setCreateDate(date);
            shopModuleDraft.setUpdateBy(shopModule.getUpdateBy());
            shopModuleDraft.setUpdateDate(shopModule.getUpdateDate());
            shopModuleDraft.setRemarks(shopModule.getRemarks());
            shopModuleDraft.setDelFlag("0");
            dao.insertSelective(shopModuleDraft);

            // 店铺模块图片热区表
            ShopModulePicMapExample shopModulePicMapExample = new ShopModulePicMapExample();
            ShopModulePicMapExample.Criteria picMapExampleCriteria = shopModulePicMapExample.createCriteria();
            picMapExampleCriteria.andMchtIdEqualTo(mchtId);
            picMapExampleCriteria.andShopModuleIdEqualTo(shopModule.getId());
            picMapExampleCriteria.andDelFlagEqualTo("0");
            List<ShopModulePicMap> shopModulePicMaps = shopModulePicMapService.selectByExample(shopModulePicMapExample);
            for (ShopModulePicMap shopModulePicMap: shopModulePicMaps) {
                ShopModulePicMapDraft shopModulePicMapDraft = new ShopModulePicMapDraft();
                shopModulePicMapDraft.setMchtId(mchtId);
                shopModulePicMapDraft.setShopModuleId(shopModuleDraft.getId());
                shopModulePicMapDraft.setCoords(shopModulePicMap.getCoords());
                shopModulePicMapDraft.setLinkType(shopModulePicMap.getLinkType());
                shopModulePicMapDraft.setLinkValue(shopModulePicMap.getLinkValue());
                shopModulePicMapDraft.setCreateBy(userId);
                shopModulePicMapDraft.setCreateDate(date);
                shopModulePicMapDraft.setUpdateBy(shopModulePicMap.getUpdateBy());
                shopModulePicMapDraft.setUpdateDate(shopModulePicMap.getUpdateDate());
                shopModulePicMapDraft.setRemarks(shopModulePicMap.getRemarks());
                shopModulePicMapDraft.setDelFlag("0");
                shopModulePicMapDraftService.insertSelective(shopModulePicMapDraft);
            }
        }
    }

    /**
     * 获取装修模块数据
     *
     * @param shopModuleId
     */
    public Map<String, Object> getShopModuleAndPicMap(Integer shopModuleId) throws ArgException {
        Map<String, Object> resultMap = new HashMap<>();
        ShopModuleDraft shopModuleDraft = dao.selectByPrimaryKey(shopModuleId);
        if (shopModuleDraft == null) {
            throw new ArgException("模块数据错误");
        }
        // 模块图片
        resultMap.put("moduleId", shopModuleDraft.getId());
        resultMap.put("areaId", shopModuleDraft.getShopDecorateAreaId());
        resultMap.put("modulePic", shopModuleDraft.getPic());

        ShopModulePicMapDraftExample shopModulePicMapDraftExample = new ShopModulePicMapDraftExample();
        ShopModulePicMapDraftExample.Criteria picMapDraftExampleCriteria = shopModulePicMapDraftExample.createCriteria();
        picMapDraftExampleCriteria.andShopModuleIdEqualTo(shopModuleId);
        picMapDraftExampleCriteria.andDelFlagEqualTo("0");
        List<ShopModulePicMapDraft> shopModulePicMapDrafts = shopModulePicMapDraftService.selectByExample(shopModulePicMapDraftExample);
        JSONArray ja = new JSONArray();
        for (ShopModulePicMapDraft modulePicMapDraft : shopModulePicMapDrafts) {
            JSONObject jo = new JSONObject();
            String[] coordsArray = modulePicMapDraft.getCoords().trim().split(",");
            jo.put("x1", coordsArray[0]);
            jo.put("y1", coordsArray[1]);
            jo.put("x2", coordsArray[2]);
            jo.put("y2", coordsArray[3]);
            jo.put("linkType", modulePicMapDraft.getLinkType());
            jo.put("linkValue", modulePicMapDraft.getLinkValue());
            jo.put("width", Double.valueOf(coordsArray[2]) - Double.valueOf(coordsArray[0]));
            jo.put("height", Double.valueOf(coordsArray[3]) - Double.valueOf(coordsArray[1]));
            ja.add(jo);
        }
        // 模块热区
        resultMap.put("moduleMapJSONArray", ja);
        return resultMap;
    }
}
