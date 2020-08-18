package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ShopDecoratePageDraftMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.Product;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductExample;
import com.jf.entity.ShopDecorateArea;
import com.jf.entity.ShopDecorateAreaDraft;
import com.jf.entity.ShopDecorateAreaDraftExample;
import com.jf.entity.ShopDecorateInfo;
import com.jf.entity.ShopDecorateInfoDraft;
import com.jf.entity.ShopDecoratePage;
import com.jf.entity.ShopDecoratePageDraft;
import com.jf.entity.ShopDecoratePageDraftExample;
import com.jf.entity.ShopDecoratePageExample;
import com.jf.entity.ShopModuleDraft;
import com.jf.entity.ShopModuleDraftExample;
import com.jf.entity.ShopPreferentialInfoCustom;
import com.jf.entity.ShopPreferentialInfoExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopDecoratePageDraftService extends BaseService<ShopDecoratePageDraft, ShopDecoratePageDraftExample> {

    @Autowired
    private ShopDecoratePageDraftMapper dao;

    @Autowired
    private MchtInfoService mchtInfoService;

    @Autowired
    private ShopDecorateInfoDraftService shopDecorateInfoDraftService;

    @Autowired
    private ShopDecorateAreaDraftService shopDecorateAreaDraftService;

    @Autowired
    private ShopModuleDraftService shopModuleDraftService;

    @Autowired
    private ShopDecoratePageService shopDecoratePageService;

    @Autowired
    private ShopDecorateInfoService shopDecorateInfoService;

    @Autowired
    private ShopDecorateAreaService shopDecorateAreaService;

    @Autowired
    private ShopModuleService shopModuleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopPreferentialInfoService shopPreferentialInfoService;

    @Autowired
    public void setShopDecoratePageDraftMapper(ShopDecoratePageDraftMapper shopDecoratePageDraftMapper) {
        super.setDao(shopDecoratePageDraftMapper);
        this.dao = shopDecoratePageDraftMapper;
    }

    /**
     * 查询商家是否存在装修数据 不存在则初始化数据
     *
     * @param mchtId
     * @param userId
     * @throws ArgException
     */
    public Map<String, Object> findData(Integer mchtId, Integer userId) throws ArgException {
        List<ShopDecoratePageDraft> shopDecoratePageDraftList = this.getShopDecoratePageDrafts(mchtId);
        // 数据不存在 则初始化数据
        if (shopDecoratePageDraftList.isEmpty()) {
            return null;
        }

        // 数据存在 则获取数据
        ShopDecoratePageDraft shopDecoratePageDraft = shopDecoratePageDraftList.get(0);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("decoratePageId", shopDecoratePageDraft.getId());
        if (!StringUtil.isEmpty(shopDecoratePageDraft.getBanner())) {
            resultMap.put("banner", shopDecoratePageDraft.getBanner());
        }

        // 如果装修数据存在logo 则展示其logo
        if (!StringUtil.isEmpty(shopDecoratePageDraft.getShopLogo())) {
            resultMap.put("shopLogo", shopDecoratePageDraft.getShopLogo());
        }

        // 获取装修分区数据
        ShopDecorateAreaDraftExample shopDecorateAreaDraftExample = new ShopDecorateAreaDraftExample();
        ShopDecorateAreaDraftExample.Criteria areaDraftExampleCriteria = shopDecorateAreaDraftExample.createCriteria();
        areaDraftExampleCriteria.andMchtIdEqualTo(mchtId);
        areaDraftExampleCriteria.andShopDecorateInfoIdEqualTo(shopDecoratePageDraft.getShopDecorateInfoId());
        List<ShopDecorateAreaDraft> shopDecorateAreaDraftList = shopDecorateAreaDraftService.selectByExample(shopDecorateAreaDraftExample);
        ShopDecorateAreaDraft shopDecorateAreaDraft = shopDecorateAreaDraftList.get(0);
        resultMap.put("decorateAreaId", shopDecorateAreaDraft.getId());

        // 获取装修模块数据
        ShopModuleDraftExample shopModuleDraftExample = new ShopModuleDraftExample();
        ShopModuleDraftExample.Criteria moduleDraftExampleCriteria = shopModuleDraftExample.createCriteria();
        moduleDraftExampleCriteria.andMchtIdEqualTo(mchtId);
        moduleDraftExampleCriteria.andShopDecorateAreaIdEqualTo(shopDecorateAreaDraft.getId());
        moduleDraftExampleCriteria.andDelFlagEqualTo("0");
        shopModuleDraftExample.setOrderByClause("seq_no asc");
        resultMap.put("moduleList", shopModuleDraftService.selectByExample(shopModuleDraftExample));

        List<ProductCustom> productList = productService.selectShopDecorateList(mchtId);
        for (ProductCustom productCustom: productList) {
            productCustom.setMallPrice(formatDecimal(productCustom.getMinMallPrice()));
            productCustom.setTagPrice(formatDecimal(productCustom.getMinTagPrice()));
        }
        resultMap.put("productList", productList);

        ShopPreferentialInfoExample shopPreferentialInfoExample = new ShopPreferentialInfoExample();
        ShopPreferentialInfoExample.Criteria shopPreferentialInfoCriteria = shopPreferentialInfoExample.createCriteria();
        shopPreferentialInfoCriteria.andMchtIdEqualTo(mchtId);
        shopPreferentialInfoCriteria.andTypeEqualTo("2");
        shopPreferentialInfoCriteria.andStatusEqualTo("1");
        shopPreferentialInfoCriteria.andBeginDateLessThan(new Date());
        shopPreferentialInfoCriteria.andEndDateGreaterThan(new Date());
        shopPreferentialInfoExample.setOrderByClause("id desc");
        List<ShopPreferentialInfoCustom> shopPreferentialInfoCustomList = shopPreferentialInfoService.selectCustomByExample(shopPreferentialInfoExample);
        if (!shopPreferentialInfoCustomList.isEmpty()) {
            ShopPreferentialInfoCustom shopPreferentialInfoCustom = shopPreferentialInfoCustomList.get(0);
            String rules = shopPreferentialInfoCustom.getRules();
            if (!StringUtil.isEmpty(rules)) {
                if (rules.contains("|")) {
                    rules = StringUtils.substringAfterLast(rules, "|");
                }

                String[] finalRuleArr = rules.split(",");
                String finalRule = "满" + finalRuleArr[0] + "元减" + finalRuleArr[1] + "元";
                resultMap.put("rule", finalRule);
            }
        }

        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
        if (mchtInfo != null) {
            resultMap.put("shopName", mchtInfo.getShopName());
        }

        return resultMap;
    }

    public List<ShopDecoratePageDraft> getShopDecoratePageDrafts(Integer mchtId) {
        ShopDecoratePageDraftExample shopDecoratePageDraftExample = new ShopDecoratePageDraftExample();
        ShopDecoratePageDraftExample.Criteria pageDraftExampleCriteria = shopDecoratePageDraftExample.createCriteria();
        pageDraftExampleCriteria.andMchtIdEqualTo(mchtId);
        return dao.selectByExample(shopDecoratePageDraftExample);
    }

    /**
     * 转换数值
     *
     * @param value
     * @return
     */
    private String formatDecimal(BigDecimal value) {
        // 整数
        BigDecimal salePriceRound = new BigDecimal(value.intValue());
        // 小数
        BigDecimal salePriceDec = value.subtract(salePriceRound);
        if (salePriceDec.compareTo(BigDecimal.ZERO) > 0) {
            return value.toPlainString();
        }
        return value.intValue() + "";
    }

    /**
     * 商家初始化装修数据
     *
     * @param mchtId
     */
    public Map<String, Object> initDecoratePageDraft(Integer mchtId, Integer userId) throws ArgException {
        // 初始化店铺装修信息表(草稿表)
        ShopDecorateInfoDraft shopDecorateInfoDraft = new ShopDecorateInfoDraft();
        shopDecorateInfoDraft.setCreateBy(userId);
        shopDecorateInfoDraft.setUpdateBy(userId);
        shopDecorateInfoDraftService.saveDecorateInfoDraft(shopDecorateInfoDraft);

        // 初始化店铺装修页面表(草稿表)
        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
        if (mchtInfo == null) {
            throw new ArgException("商家信息错误");
        }
        Date date = new Date();

        ShopDecoratePageDraft shopDecoratePageDraft = new ShopDecoratePageDraft();
        shopDecoratePageDraft.setMchtId(mchtId);
        shopDecoratePageDraft.setShopDecorateInfoId(shopDecorateInfoDraft.getId());
        shopDecoratePageDraft.setStatus("0");
        shopDecoratePageDraft.setCreateBy(userId);
        shopDecoratePageDraft.setCreateDate(date);
        shopDecoratePageDraft.setUpdateBy(userId);
        shopDecoratePageDraft.setUpdateDate(date);
        shopDecoratePageDraft.setDelFlag("0");
        shopDecoratePageDraft.setShopLogo(mchtInfo.getShopLogo());
        this.insertSelective(shopDecoratePageDraft);

        // 店铺装修分区表(草稿表)
        ShopDecorateAreaDraft shopDecorateAreaDraft = new ShopDecorateAreaDraft();
        shopDecorateAreaDraft.setMchtId(mchtId);
        shopDecorateAreaDraft.setCreateBy(userId);
        shopDecorateAreaDraft.setUpdateBy(userId);
        shopDecorateAreaDraft.setShopDecorateInfoId(shopDecorateInfoDraft.getId());
        shopDecorateAreaDraftService.saveDecorateAreaDraft(shopDecorateAreaDraft);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("decoratePageId", shopDecoratePageDraft.getId());
        resultMap.put("decorateAreaId", shopDecorateAreaDraft.getId());
        return resultMap;
    }

    /**
     * 保存头部背景
     *
     * @param shopDecoratePageDraft
     */
    public void saveBanner(ShopDecoratePageDraft shopDecoratePageDraft) throws ArgException {
        // 校验参数
        this.checkParam(shopDecoratePageDraft);
        if (StringUtil.isEmpty(shopDecoratePageDraft.getBanner())) {
            throw new ArgException("请上传图片");
        }

        shopDecoratePageDraft.setUpdateDate(new Date());
        this.dao.updateByPrimaryKeySelective(shopDecoratePageDraft);
    }

    /**
     * 保存头部图片
     *
     * @param shopDecoratePageDraft
     */
    public void saveShopLogo(ShopDecoratePageDraft shopDecoratePageDraft) throws ArgException {
        // 校验参数
        this.checkParam(shopDecoratePageDraft);
        if (StringUtil.isEmpty(shopDecoratePageDraft.getShopLogo())) {
            throw new ArgException("请上传图片");
        }

        shopDecoratePageDraft.setUpdateDate(new Date());
        this.dao.updateByPrimaryKeySelective(shopDecoratePageDraft);
    }

    /**
     * 新增/编辑模块
     *
     * @param shopModuleDraft
     * @param userId
     * @param moduleMapJsonStr
     * @throws ArgException
     */
    public void saveModuleDraft(ShopModuleDraft shopModuleDraft, Integer userId, String moduleMapJsonStr) throws ArgException {
        shopModuleDraftService.saveModuleDraft(shopModuleDraft, userId, moduleMapJsonStr);
    }

    /**
     * 上下移
     *
     * @param firstId
     * @param secondId
     * @param userId
     * @throws ArgException
     */
    public void changeSeqNo(Integer firstId, Integer secondId, Integer userId) throws ArgException {
        shopModuleDraftService.changeSeqNo(firstId, secondId, userId);
    }

    /**
     * 删除模块
     *
     * @param mchtId
     * @param moduleId
     * @param userId
     */
    public void deleteModule(Integer mchtId, Integer moduleId, Integer userId) throws ArgException {
        shopModuleDraftService.deleteModule(mchtId, moduleId, userId);
    }

    /**
     * 参数校验
     *
     * @param shopDecoratePageDraft
     */
    private void checkParam(ShopDecoratePageDraft shopDecoratePageDraft) throws ArgException {
        if (shopDecoratePageDraft == null || shopDecoratePageDraft.getId() == null) {
            throw new ArgException("数据错误,请刷新页面");
        }
        if (shopDecoratePageDraft.getMchtId() == null) {
            throw new ArgException("请登录");
        }
    }

    /**
     * 发布装修到正式表
     *
     * @param decoratePageId
     * @param mchtId
     * @param userId
     * @throws ArgException
     */
    public void announceDecorate(Integer decoratePageId, Integer mchtId, Integer userId) throws ArgException {
        ShopDecoratePageDraft shopDecoratePageDraft = dao.selectByPrimaryKey(decoratePageId);
        if (shopDecoratePageDraft.getMchtId() == null || !mchtId.equals(shopDecoratePageDraft.getMchtId())) {
            throw new ArgException("商家信息错误，不能执行此操作");
        }

        // 通过商家id查询装修正式表是否有此商家的装修数据存在  不存在则insert数据 存在则更新
        // 正式表新增的数据createBy均为发布者的id;updateBy均为草稿表中updateBy的数据
        ShopDecoratePageExample decoratePageExample = new ShopDecoratePageExample();
        ShopDecoratePageExample.Criteria decoratePageExampleCriteria = decoratePageExample.createCriteria();
        decoratePageExampleCriteria.andMchtIdEqualTo(mchtId);
        List<ShopDecoratePage> shopDecoratePages = shopDecoratePageService.selectByExample(decoratePageExample);
        ShopDecoratePage shopDecoratePage = new ShopDecoratePage();
        ShopDecorateInfo shopDecorateInfo = new ShopDecorateInfo();
        Date date = new Date();
        if (!shopDecoratePages.isEmpty()) {
            shopDecoratePage = shopDecoratePages.get(0);
            shopDecorateInfo = shopDecorateInfoService.selectByPrimaryKey(shopDecoratePage.getShopDecorateInfoId());
        }

        // 查询ShopDecorateInfo草稿表信息 复制到正式表中
        ShopDecorateInfoDraft shopDecorateInfoDraft = shopDecorateInfoDraftService.selectByPrimaryKey(shopDecoratePageDraft.getShopDecorateInfoId());
        shopDecorateInfo.setCreateBy(userId);
        shopDecorateInfo.setCreateDate(date);
        shopDecorateInfo.setDelFlag("0");
        shopDecorateInfo.setUpdateBy(shopDecorateInfoDraft.getUpdateBy());
        shopDecorateInfo.setUpdateDate(shopDecorateInfoDraft.getUpdateDate());
        shopDecorateInfo.setRemarks(shopDecorateInfoDraft.getRemarks());
        // 存在主键则更新 否则新增
        if (shopDecorateInfo.getId() != null) {
            shopDecorateInfoService.updateByPrimaryKeySelective(shopDecorateInfo);
        } else {
            shopDecorateInfoService.insertSelective(shopDecorateInfo);
        }

        // 发布shopDecoratePage草稿表信息到正式表
        shopDecoratePage.setMchtId(mchtId);
        shopDecoratePage.setShopDecorateInfoId(shopDecorateInfo.getId());
        shopDecoratePage.setBanner(shopDecoratePageDraft.getBanner());
        shopDecoratePage.setShopLogo(shopDecoratePageDraft.getShopLogo());
        shopDecoratePage.setCreateBy(userId);
        shopDecoratePage.setCreateDate(date);
        shopDecoratePage.setStatus("1");
        shopDecoratePage.setUpdateBy(shopDecoratePageDraft.getUpdateBy());
        shopDecoratePage.setUpdateDate(shopDecoratePageDraft.getUpdateDate());
        shopDecoratePage.setRemarks(shopDecoratePageDraft.getRemarks());
        shopDecoratePage.setDelFlag("0");
        // 存在主键则更新 否则新增
        if (shopDecoratePage.getId() != null) {
            shopDecoratePageService.updateByPrimaryKeySelective(shopDecoratePage);
        } else {
            shopDecoratePageService.insertSelective(shopDecoratePage);
        }

        // 保存店铺装修分区信息
        ShopDecorateArea shopDecorateArea = shopDecorateAreaService.saveShopDecorateArea(mchtId, userId, shopDecorateInfoDraft.getId(), shopDecorateInfo.getId(), date);

        // 保存模块以及模块图片热区信息
        shopModuleService.saveModuleAndPicMap(mchtId, userId, date, shopDecorateArea.getId());

        // 更新店铺logo
        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
        mchtInfo.setId(mchtId);
        mchtInfo.setShopLogo(shopDecoratePageDraft.getShopLogo());
        mchtInfo.setUpdateDate(new Date());
        mchtInfo.setUpdateBy(userId);
        mchtInfoService.updateByPrimaryKeySelective(mchtInfo);

    }

    /**
     * 将草稿表的数据还原成正式表的数据
     *
     * @param mchtId
     * @param userId
     * @throws ArgException
     */
    public void restoreDecorate(Integer mchtId, Integer userId) throws ArgException {
        // 获取该商家正式装修数据
        ShopDecoratePageExample shopDecoratePageExample = new ShopDecoratePageExample();
        ShopDecoratePageExample.Criteria decoratePageExampleCriteria = shopDecoratePageExample.createCriteria();
        decoratePageExampleCriteria.andMchtIdEqualTo(mchtId);
        decoratePageExampleCriteria.andDelFlagEqualTo("0");
        List<ShopDecoratePage> shopDecoratePages = shopDecoratePageService.selectByExample(shopDecoratePageExample);
        if (shopDecoratePages.isEmpty()) {
            throw new ArgException("装修暂无正式数据，不能执行此操作");
        }
        ShopDecoratePage shopDecoratePage = shopDecoratePages.get(0);

        if (shopDecoratePage.getMchtId() == null || !mchtId.equals(shopDecoratePage.getMchtId())) {
            throw new ArgException("商家信息错误，不能执行此操作");
        }

        // 通过商家id查询装修草稿表是否有此商家的装修数据存在
        // 草稿表新增的数据createBy均为发布者的id;updateBy均为正式表中updateBy的数据
        ShopDecoratePageDraftExample pageDraftExample = new ShopDecoratePageDraftExample();
        ShopDecoratePageDraftExample.Criteria pageDraftExampleCriteria = pageDraftExample.createCriteria();
        pageDraftExampleCriteria.andMchtIdEqualTo(mchtId);
        pageDraftExampleCriteria.andDelFlagEqualTo("0");
        List<ShopDecoratePageDraft> shopDecoratePageDraftList = dao.selectByExample(pageDraftExample);
        Date date = new Date();
        if (shopDecoratePageDraftList.isEmpty()) {
            throw new ArgException("商家装修信息有误，不能执行此操作");
        }
        ShopDecoratePageDraft shopDecoratePageDraft = shopDecoratePageDraftList.get(0);
        ShopDecorateInfoDraft shopDecorateInfoDraft = shopDecorateInfoDraftService.selectByPrimaryKey(shopDecoratePageDraft.getShopDecorateInfoId());

        // 查询ShopDecorateInfo正式表信息 复制到草稿表中
        ShopDecorateInfo shopDecorateInfo = shopDecorateInfoService.selectByPrimaryKey(shopDecoratePage.getShopDecorateInfoId());
        shopDecorateInfoDraft.setCreateBy(userId);
        shopDecorateInfoDraft.setCreateDate(date);
        shopDecorateInfoDraft.setDelFlag("0");
        shopDecorateInfoDraft.setUpdateBy(shopDecorateInfo.getUpdateBy());
        shopDecorateInfoDraft.setUpdateDate(shopDecorateInfo.getUpdateDate());
        shopDecorateInfoDraft.setRemarks(shopDecorateInfo.getRemarks());
        // 更新草稿表
        shopDecorateInfoDraftService.updateByPrimaryKeySelective(shopDecorateInfoDraft);

        // 发布shopDecoratePage草稿表信息到正式表
        shopDecoratePageDraft.setMchtId(mchtId);
        shopDecoratePageDraft.setShopDecorateInfoId(shopDecorateInfoDraft.getId());
        shopDecoratePageDraft.setBanner(shopDecoratePage.getBanner());
        shopDecoratePageDraft.setShopLogo(shopDecoratePage.getShopLogo());
        shopDecoratePageDraft.setCreateBy(userId);
        shopDecoratePageDraft.setCreateDate(date);
        shopDecoratePageDraft.setStatus("0");
        shopDecoratePageDraft.setUpdateBy(shopDecoratePage.getUpdateBy());
        shopDecoratePageDraft.setUpdateDate(shopDecoratePage.getUpdateDate());
        shopDecoratePageDraft.setRemarks(shopDecoratePage.getRemarks());
        shopDecoratePageDraft.setDelFlag("0");
        dao.updateByPrimaryKeySelective(shopDecoratePageDraft);

        // 还原店铺装修分区信息
        ShopDecorateAreaDraft shopDecorateAreaDraft = shopDecorateAreaDraftService.restoreShopDecorateArea(mchtId, userId, shopDecorateInfoDraft.getId(), shopDecorateInfo.getId(), date);

        // 保存模块以及模块图片热区信息
        shopModuleDraftService.restoreModuleAndPicMap(mchtId, userId, date, shopDecorateAreaDraft.getId());

    }

    /**
     * 商家初始化装修数据
     *
     * @param shopModuleId
     */
    public Map<String, Object> getShopModuleAndPicMap(Integer shopModuleId) throws ArgException {
        return shopModuleDraftService.getShopModuleAndPicMap(shopModuleId);
    }

    /**
     * 校验链接填写的商家code或者商品code是否存在对应的数据
     *
     * @param linkType
     * @param linkValue
     */
    public void checkLink(Integer linkType, String linkValue) {
        if (linkType == 1) {
            ProductExample productExample = new ProductExample();
            ProductExample.Criteria productExampleCriteria = productExample.createCriteria();
            productExampleCriteria.andCodeEqualTo(linkValue);
            List<Product> products = productService.selectByExample(productExample);
            if (products.isEmpty()) {
                throw new ArgException("该商品不存在");
            }
        } else if (linkType == 3) {
            MchtInfoExample mchtInfoExample = new MchtInfoExample();
            MchtInfoExample.Criteria mchtInfoExampleCriteria = mchtInfoExample.createCriteria();
            mchtInfoExampleCriteria.andMchtCodeEqualTo(linkValue);
            // 判断商家状态是否正常 店铺状态是否开通
            mchtInfoExampleCriteria.andStatusEqualTo("1");
            mchtInfoExampleCriteria.andShopStatusEqualTo("1");
            List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
            if (mchtInfos.isEmpty()) {
                throw new ArgException("请输入正确的商家序号！");
            }
        }
    }
}
