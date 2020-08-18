package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CustomPageController extends BaseController {

    @Resource
    private CustomPageService customPageService;

    @Resource
    private DecorateInfoService decorateInfoService;

    @Resource
    private DecorateProductPoolService decorateProductPoolService;

    @Resource
    private DecorateAreaService decorateAreaService;

    @Resource
    private DecorateModuleService decorateModuleService;

    @Resource
    private ProductPicService productPicService;

    @Resource
    private ActivityAreaService activityAreaService;

    @Resource
    private ActivityService activityService;

    @Resource
    private ModuleMapService moduleMapService;

    @Resource
    private ModuleItemService moduleItemService;

    @Resource
    private ProductService productService;

    @Resource
    private ProductTypeService productTypeService;

    @Resource
    private ProductBrandService productBrandService;

    @Resource
    private CouponService couponService;

    @Resource
    private MallCategoryService mallCategoryService;

    @Resource
    private MchtInfoService mchtInfoService;

    @Resource
    private BrandteamTypeService brandteamTypeService;

    @Resource
    private WeitaoChannelService weitaoChannelService;

    @Resource
    private ProductModuleTypeService productModuleTypeService;

    private static final long serialVersionUID = 1L;

    /**
     * 运营自建页面列表
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/list.shtml")
    public ModelAndView customPageList(HttpServletRequest request) {
        String rtPage = "/customPage/customPageList";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("pageTypeList", DataDicUtil.getStatusList("BU_CUSTOM_PAGE", "PAGE_TYPE"));
        resMap.put("statusList", DataDicUtil.getStatusList("BU_CUSTOM_PAGE", "STATUS"));
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 运营自建页面列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/data.shtml")
    @ResponseBody
    public Map<String, Object> customPageData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer totalCount = 0;
        try {
            CustomPageExample example = new CustomPageExample();
            example.setOrderByClause("id desc");
            CustomPageExample.Criteria criteria = example.createCriteria();
            if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
                criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
            } else {
                criteria.andDelFlagEqualTo("0");
            }
            String pageType = request.getParameter("pageType");
            String pageName = request.getParameter("pageName");
            String remarks = request.getParameter("remarks");
            String status = request.getParameter("status");
            if (!StringUtil.isEmpty(pageType)) {
                criteria.andPageTypeEqualTo(pageType);
            }
            if (!StringUtil.isEmpty(pageName)) {
                criteria.andPageNameLike("%" + pageName + "%");
            }
            if (!StringUtil.isEmpty(remarks)) {
                criteria.andRemarksLike("%" + remarks + "%");
            }
            if (!StringUtil.isEmpty(status)) {
                criteria.andStatusEqualTo(status);
            }
            totalCount = customPageService.countCustomPageCustomByExample(example);
            example.setLimitStart(page.getLimitStart());
            example.setLimitSize(page.getLimitSize());
            List<CustomPageCustom> customPageCustoms = customPageService.selectCustomPageCustomByExample(example);
            resMap.put("Rows", customPageCustoms);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 添加/编辑运营自建页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/editCustomPage.shtml")
    public ModelAndView editCustomPage(HttpServletRequest request) {
        String rtPage = "/customPage/editCustomPage";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(id));
            resMap.put("customPage", customPage);
        }
        resMap.put("pageTypeList", DataDicUtil.getStatusList("BU_CUSTOM_PAGE", "PAGE_TYPE"));
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 保存运营自建页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/saveCustomPage.shtml")
    @ResponseBody
    public Map<String, Object> saveCustomPage(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            String id = request.getParameter("id");
            String pageType = request.getParameter("pageType");
            String pageName = request.getParameter("pageName");
            String remarks = request.getParameter("remarks");
            CustomPage customPage = new CustomPage();
            DecorateInfo decorateInfo = null;
            DecorateArea decorateArea = null;
            if (!StringUtils.isEmpty(id)) {
                customPage = customPageService.selectByPrimaryKey(Integer.parseInt(id));
            } else {
                decorateInfo = new DecorateInfo();
                decorateInfo.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                decorateInfo.setCreateDate(new Date());
                decorateInfo.setDelFlag("0");
                decorateInfo.setDecorateName(pageName);
                customPage.setDelFlag("0");
                customPage.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                customPage.setCreateDate(new Date());
                decorateArea = new DecorateArea();
                decorateArea.setDelFlag("0");
                decorateArea.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                decorateArea.setCreateDate(new Date());
//				decorateArea.setAreaName(pageName);
                decorateArea.setSeqNo(1);
            }
            customPage.setPageType(pageType);
            customPage.setPageName(pageName);
            customPage.setRemarks(remarks);
            customPage.setUpdateDate(new Date());
            customPage.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            customPageService.save(customPage, decorateInfo, decorateArea);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 上下架
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/changeStatus.shtml")
    @ResponseBody
    public Map<String, Object> changeStatus(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String id = request.getParameter("id");
            CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(id));
            if (customPage.getStatus().equals("0")) {
                customPage.setStatus("1");
            } else if (customPage.getStatus().equals("1")) {
                customPage.setStatus("0");
            }
            customPage.setUpdateDate(new Date());
            customPage.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            customPageService.updateByPrimaryKeySelective(customPage);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 自建装修预览页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/previewDecorateInfo.shtml")
    public ModelAndView previewDecorateInfo(HttpServletRequest request) {
        String rtPage = "/customPage/previewDecorateInfo";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        String decorateInfoId = request.getParameter("decorateInfoId");
        if (!StringUtils.isEmpty(id)) {
            CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(id));
            decorateInfoId = customPage.getDecorateInfoId().toString();
        }
        resMap.put("decorateInfoId", decorateInfoId);
        DecorateAreaExample dae = new DecorateAreaExample();
        dae.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
        DecorateAreaExample.Criteria daec = dae.createCriteria();
        daec.andDelFlagEqualTo("0");
        daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
        List<DecorateAreaCustom> decorateAreaCustoms = decorateAreaService.selectDecorateAreaCustomByExample(dae);
        for (DecorateAreaCustom decorateAreaCustom : decorateAreaCustoms) {
            DecorateModuleExample dme = new DecorateModuleExample();
            dme.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
            DecorateModuleExample.Criteria dmec = dme.createCriteria();
            dmec.andDelFlagEqualTo("0");
            dmec.andDecorateAreaIdEqualTo(decorateAreaCustom.getId());
            List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
            if (decorateModuleCustoms != null && decorateModuleCustoms.size() > 0) {
                for (DecorateModuleCustom decorateModuleCustom : decorateModuleCustoms) {
                    List<String> pics = new ArrayList<String>();
                    HashMap<String, Object> paramMap = new HashMap<String, Object>();
                    if (decorateModuleCustom.getModuleType().equals("1") || decorateModuleCustom.getModuleType().equals("8") || decorateModuleCustom.getModuleType().equals("9")) {//图片
                        pics.add(decorateModuleCustom.getPic());
                        decorateModuleCustom.setPics(pics);
                    } else if (decorateModuleCustom.getModuleType().equals("2")) {//商品
                        ModuleItemCustomExample mice = new ModuleItemCustomExample();
                        mice.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
                        ModuleItemCustomExample.ModuleItemCustomCriteria micc = mice.createCriteria();
                        micc.andDelFlagEqualTo("0");
                        micc.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
                        micc.andItemTypeEqualTo("1");
                        micc.andProductActivityStatusEqualTo("4");//活动中
                        List<ModuleItemCustom> moduleItemCustoms = moduleItemService.selectModuleItemCustomByExample(mice);
                        decorateModuleCustom.setModuleItemCustoms(moduleItemCustoms);
                    } else if (decorateModuleCustom.getModuleType().equals("3")) {//特卖
                        List<Integer> activityIds = moduleItemService.getIdsByModuleId(decorateModuleCustom.getId());
                        ActivityExample ae = new ActivityExample();
                        ActivityExample.Criteria aec = ae.createCriteria();
                        aec.andDelFlagEqualTo("0");
                        aec.andIdIn(activityIds);
                        aec.andStatusEqualTo("6");
                        List<ActivityCustom> activityCustoms = activityService.selectActivityCustomByExample(ae);
                        decorateModuleCustom.setActivityCustoms(activityCustoms);
                    } else if (decorateModuleCustom.getModuleType().equals("4")) {//商品列表
                        List<Integer> productType1Ids = new ArrayList<Integer>();
                        if (!StringUtils.isEmpty(decorateModuleCustom.getProductType1Ids())) {
                            String[] productType1IdsArray = decorateModuleCustom.getProductType1Ids().trim().split(",");
                            for (int i = 0; i < productType1IdsArray.length; i++) {
                                productType1Ids.add(Integer.parseInt(productType1IdsArray[i]));
                            }
                        }
                        List<Integer> productType2Ids = new ArrayList<Integer>();
                        if (!StringUtils.isEmpty(decorateModuleCustom.getProductType2Ids())) {
                            String[] productType2IdsArray = decorateModuleCustom.getProductType2Ids().trim().split(",");
                            for (int i = 0; i < productType2IdsArray.length; i++) {
                                productType2Ids.add(Integer.parseInt(productType2IdsArray[i]));
                            }
                        }
                        if (!StringUtils.isEmpty(decorateModuleCustom.getProductBrandIds())) {
                            List<Integer> productBrandIds = new ArrayList<Integer>();
                            String[] productBrandIdsArray = decorateModuleCustom.getProductBrandIds().trim().split(",");
                            for (int i = 0; i < productBrandIdsArray.length; i++) {
                                productBrandIds.add(Integer.parseInt(productBrandIdsArray[i]));
                            }
                            paramMap.put("productBrandIds", productBrandIds);
                        }
                        if (productType1Ids != null && productType1Ids.size() > 0) {
                            paramMap.put("productType1Ids", productType1Ids);
                        }
                        if (productType2Ids != null && productType2Ids.size() > 0) {
                            paramMap.put("productType2Ids", productType2Ids);
                        }
                        List<Integer> productIds = productService.getProductIds(paramMap);
                        ProductCustomExample pce = new ProductCustomExample();
                        ProductCustomExample.ProductCustomCriteria pcec = pce.createCriteria();
                        pcec.andDelFlagEqualTo("0");
                        if (productIds != null && productIds.size() > 0) {
                            pcec.andIdIn(productIds);
                        }
                        pcec.andStatusEqualTo("1");
                        pcec.andProductActivityStatusEqualTo("4");//活动中
                        List<ProductCustom> productCustoms = productService.selectProductCustomByExample(pce);
                        decorateModuleCustom.setProductCustoms(productCustoms);
                    } else if (decorateModuleCustom.getModuleType().equals("5")) {//特卖列表
                        List<Integer> productType1Ids = new ArrayList<Integer>();
                        if (!StringUtils.isEmpty(decorateModuleCustom.getProductType1Ids())) {
                            String[] productType1IdsArray = decorateModuleCustom.getProductType1Ids().trim().split(",");
                            for (int i = 0; i < productType1IdsArray.length; i++) {
                                productType1Ids.add(Integer.parseInt(productType1IdsArray[i]));
                            }
                        }
                        List<Integer> productType2Ids = new ArrayList<Integer>();
                        if (!StringUtils.isEmpty(decorateModuleCustom.getProductType2Ids())) {
                            String[] productType2IdsArray = decorateModuleCustom.getProductType2Ids().trim().split(",");
                            for (int i = 0; i < productType2IdsArray.length; i++) {
                                productType2Ids.add(Integer.parseInt(productType2IdsArray[i]));
                            }
                        }
                        ActivityExample ae = new ActivityExample();
                        ActivityExample.Criteria aec = ae.createCriteria();
                        aec.andDelFlagEqualTo("0");
                        if (productType1Ids != null && productType1Ids.size() > 0) {
                            aec.andProductTypeIdIn(productType1Ids);
                        }
                        if (productType2Ids != null && productType2Ids.size() > 0) {
                            aec.andProductTypeSecondIdIn(productType2Ids);
                        }
                        if (!StringUtils.isEmpty(decorateModuleCustom.getProductBrandIds())) {
                            List<Integer> productBrandIds = new ArrayList<Integer>();
                            String[] productBrandIdsArray = decorateModuleCustom.getProductBrandIds().trim().split(",");
                            for (int i = 0; i < productBrandIdsArray.length; i++) {
                                productBrandIds.add(Integer.parseInt(productBrandIdsArray[i]));
                            }
                            aec.andProductBrandIdIn(productBrandIds);
                        }
                        aec.andStatusEqualTo("6");
                        List<ActivityCustom> activityCustoms = activityService.selectActivityCustomByExample(ae);
                        decorateModuleCustom.setActivityCustoms(activityCustoms);
                    }
                }
            }
            decorateAreaCustom.setDecorateModuleCustoms(decorateModuleCustoms);
        }
        resMap.put("decorateAreaCustoms", decorateAreaCustoms);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 装修页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/designDecorateArea.shtml")
    public ModelAndView designDecorateArea(HttpServletRequest request) {
        String rtPage = "/customPage/designDecorateArea";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String decorateInfoId = request.getParameter("decorateInfoId");
        DecorateAreaExample dae = new DecorateAreaExample();
        DecorateAreaExample.Criteria daec = dae.createCriteria();
        daec.andDelFlagEqualTo("0");
        daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
        List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
        if (decorateAreas != null && decorateAreas.size() > 0) {
            resMap.put("decorateAreaId", decorateAreas.get(0).getId());
            DecorateModuleExample dme = new DecorateModuleExample();
            dme.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
            DecorateModuleExample.Criteria dmec = dme.createCriteria();
            dmec.andDelFlagEqualTo("0");
            dmec.andDecorateAreaIdEqualTo(decorateAreas.get(0).getId());
            List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
            resMap.put("decorateModuleCustoms", decorateModuleCustoms);
            for (DecorateModuleCustom decorateModuleCustom : decorateModuleCustoms) {
                if (decorateModuleCustom.getModuleType().equals("2")) {//商品
                    ModuleItemExample mie = new ModuleItemExample();
                    mie.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
                    ModuleItemExample.Criteria miec = mie.createCriteria();
                    miec.andDelFlagEqualTo("0");
                    miec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
                    miec.andItemTypeEqualTo("1");//1.商品 2.特卖
                    int count = moduleItemService.countByExample(mie);
                    decorateModuleCustom.setCount(count);
                } else if (decorateModuleCustom.getModuleType().equals("3")) {//特卖
                    ModuleItemExample mie = new ModuleItemExample();
                    mie.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
                    ModuleItemExample.Criteria miec = mie.createCriteria();
                    miec.andDelFlagEqualTo("0");
                    miec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
                    miec.andItemTypeEqualTo("2");//1.商品 2.特卖
                    int count = moduleItemService.countByExample(mie);
                    decorateModuleCustom.setCount(count);
                } else if (decorateModuleCustom.getModuleType().equals("4") || decorateModuleCustom.getModuleType().equals("5")) {//商品列表,特卖列表
                    if (!StringUtils.isEmpty(decorateModuleCustom.getProductType1Ids())) {
                        String firstLevelName = productTypeService.getNamesByIds(decorateModuleCustom.getProductType1Ids());
                        decorateModuleCustom.setFirstLevelName(firstLevelName);
                    } else {
                        decorateModuleCustom.setFirstLevelName("不限");
                    }
                    if (!StringUtils.isEmpty(decorateModuleCustom.getProductType2Ids())) {
                        String secondLevelName = productTypeService.getNamesByIds(decorateModuleCustom.getProductType2Ids());
                        decorateModuleCustom.setSecondLevelName(secondLevelName);
                    } else {
                        decorateModuleCustom.setSecondLevelName("不限");
                    }
                    if (!StringUtils.isEmpty(decorateModuleCustom.getProductBrandIds())) {
                        String brandNames = productBrandService.getNamesByIds(decorateModuleCustom.getProductBrandIds());
                        decorateModuleCustom.setBrandNames(brandNames);
                    } else {
                        decorateModuleCustom.setBrandNames("不限");
                    }
                }
            }
        }
        return new ModelAndView(rtPage, resMap);
    }


    /**
     * 删除自建页面
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/customPage/delDecorateArea.shtml")
    public Map<String, Object> delDecorateArea(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
            Integer id=Integer.valueOf(request.getParameter("id"));
            customPageService.delBatch(staffId,id);
        }catch (Exception e) {
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
            e.printStackTrace();
        }

        return resMap;
    }

    /**
     * 添加/编辑装修模块
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/editDecorateModule.shtml")
    public ModelAndView editDecorateModule(HttpServletRequest request) {
        String rtPage = "/customPage/editDecorateModule";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String decorateInfoId = request.getParameter("decorateInfoId");
        String decorateAreaId = request.getParameter("decorateAreaId");
        String moduleType = request.getParameter("moduleType");
        String seqNo = request.getParameter("seqNo");
        String decorateModuleId = request.getParameter("decorateModuleId");
        String pageType = request.getParameter("pageType");
        String isPreSell = request.getParameter("isPreSell");
        String showType = request.getParameter("showType");
        resMap.put("pageType", pageType);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resMap.put("isPreSell", isPreSell);
        resMap.put("showType", showType);

        if (!StringUtils.isEmpty(decorateModuleId)) {
            DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
            resMap.put("decorateModule", decorateModule);
            if (decorateModule.getModuleType().equals("1") || decorateModule.getModuleType().equals("8") || decorateModule.getModuleType().equals("9")) {//1.图片8.固定底部栏9.滑动图片
                ModuleMapExample mme = new ModuleMapExample();
                ModuleMapExample.Criteria criteria = mme.createCriteria();
                criteria.andDelFlagEqualTo("0");
                criteria.andDecorateModuleIdEqualTo(decorateModule.getId());
                List<ModuleMap> moduleMaps = moduleMapService.selectByExample(mme);
                JSONArray ja = new JSONArray();
                for (ModuleMap moduleMap : moduleMaps) {
                    JSONObject jo = new JSONObject();
                    String[] coordsArray = moduleMap.getCoords().trim().split(",");
                    jo.put("x1", coordsArray[0]);
                    jo.put("y1", coordsArray[1]);
                    jo.put("x2", coordsArray[2]);
                    jo.put("y2", coordsArray[3]);
                    jo.put("linkType", moduleMap.getLinkType());
                    if (moduleMap.getLinkType().equals("3")) {//商品
                        Product product = productService.selectByPrimaryKey(moduleMap.getLinkValue());
                        if (product != null) {
                            jo.put("linkValue", product.getCode());
                        }
                    } else if (moduleMap.getLinkType().equals("13")) {//商家店铺
                        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(moduleMap.getLinkValue());
                        if (mchtInfo != null) {
                            jo.put("linkValue", mchtInfo.getMchtCode());
                        }
                    } else if (moduleMap.getLinkType().equals("33")) {//商家店铺
                        jo.put("fontSize", moduleMap.getFontSize());
                        jo.put("fontColor", moduleMap.getFontColor());
                        jo.put("countDownBeginDate", sdf.format(moduleMap.getCountDownBeginDate()));
                        jo.put("countDownEndDate", sdf.format(moduleMap.getCountDownEndDate()));
                    } else {//非商品,非商家店铺
                        if (moduleMap.getLinkType().equals("9")) {
                            jo.put("linkValue", moduleMap.getLinkUrl());
                        } else {
                            jo.put("linkValue", moduleMap.getLinkValue());
                        }
                    }
                    jo.put("width", Double.valueOf(coordsArray[2]) - Double.valueOf(coordsArray[0]));
                    jo.put("height", Double.valueOf(coordsArray[3]) - Double.valueOf(coordsArray[1]));
                    ja.add(jo);
                }
                resMap.put("moduleMapJSONArray", ja);

                ProductTypeExample pte = new ProductTypeExample();
                ProductTypeExample.Criteria ptec = pte.createCriteria();
                ptec.andDelFlagEqualTo("0");
                ptec.andTLevelEqualTo(1);
                ptec.andStatusEqualTo("1");
                List<ProductType> productTypes = productTypeService.selectByExample(pte);
                resMap.put("productTypes", productTypes);

                BrandteamTypeExample e = new BrandteamTypeExample();
                e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
                List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(e);
                resMap.put("brandteamTypes", brandteamTypes);

                //商城一级分类
                MallCategoryExample mallCategoryExample = new MallCategoryExample();
                mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
                List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample);
                resMap.put("mallCategorys", mallCategorys);
            } else if (decorateModule.getModuleType().equals("2")) {//2.商品
                String category = request.getParameter("category");
                ModuleItemExample mie = new ModuleItemExample();
                mie.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
                ModuleItemExample.Criteria miec = mie.createCriteria();
                miec.andDelFlagEqualTo("0");
                miec.andDecorateModuleIdEqualTo(decorateModule.getId());
                miec.andItemTypeEqualTo("1");
                if (StringUtils.isEmpty(category)) {
                    miec.andProductModuleTypeIdIsNull();
                } else {
                    miec.andProductModuleTypeIdEqualTo(Integer.parseInt(category));
                }
                List<ModuleItemCustom> moduleItemCustoms = moduleItemService.selectModuleItemCustomByExample(mie);
                resMap.put("moduleItemCustoms", moduleItemCustoms);
                resMap.put("categoryTag", category);
                //商品模块类目
                ProductModuleTypeExample productModuleTypeExample = new ProductModuleTypeExample();
                productModuleTypeExample.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
                productModuleTypeExample.setOrderByClause("seq_no asc");
                List<ProductModuleType> productModuleTypes = productModuleTypeService.selectByExample(productModuleTypeExample);
                resMap.put("productModuleTypes", productModuleTypes);
            } else if (decorateModule.getModuleType().equals("3")) {//3.特卖
                List<Integer> activityIds = moduleItemService.getIdsByModuleId(decorateModule.getId());
                String tmpStr = "";
                for (int i = 0; i < activityIds.size(); i++) {
                    if (i != activityIds.size() - 1) {
                        tmpStr += activityIds.get(i) + ",";
                    } else {
                        tmpStr += activityIds.get(i);
                    }
                }
                resMap.put("activityIds", tmpStr);
            } else if (decorateModule.getModuleType().equals("4") || decorateModule.getModuleType().equals("5")) {//4.商品列表 5.特卖列表
                ProductTypeExample pte = new ProductTypeExample();
                ProductTypeExample.Criteria ptec = pte.createCriteria();
                ptec.andDelFlagEqualTo("0");
                ptec.andTLevelEqualTo(1);
                ptec.andStatusEqualTo("1");
                List<ProductType> productTypes = productTypeService.selectByExample(pte);
                resMap.put("productTypes", productTypes);
                if (!StringUtils.isEmpty(decorateModule.getProductType1Ids())) {
                    ProductTypeExample secondPte = new ProductTypeExample();
                    ProductTypeExample.Criteria secondPtec = secondPte.createCriteria();
                    secondPtec.andDelFlagEqualTo("0");
                    secondPtec.andParentIdEqualTo(Integer.parseInt(decorateModule.getProductType1Ids()));
                    secondPtec.andStatusEqualTo("1");
                    List<ProductType> secondProductTypes = productTypeService.selectByExample(secondPte);
                    resMap.put("secondProductTypes", secondProductTypes);
                }
                if (!StringUtils.isEmpty(decorateModule.getProductBrandIds())) {
                    String[] productBrandIdsArray = decorateModule.getProductBrandIds().split(",");
                    ArrayList<Integer> productBrandIdList = new ArrayList<Integer>();
                    for (String productBrandIdStr : productBrandIdsArray) {
                        productBrandIdList.add(Integer.parseInt(productBrandIdStr));
                    }
                    ProductBrandExample pbe = new ProductBrandExample();
                    ProductBrandExample.Criteria pbc = pbe.createCriteria();
                    pbc.andDelFlagEqualTo("0");
                    pbc.andIdIn(productBrandIdList);
                    List<ProductBrand> productBrands = productBrandService.selectByExample(pbe);
                    resMap.put("productBrands", productBrands);
                }
            }
        } else {
            if (moduleType.equals("1") || moduleType.equals("4") || moduleType.equals("5") || moduleType.equals("13") || moduleType.equals("9")) {
                ProductTypeExample pte = new ProductTypeExample();
                ProductTypeExample.Criteria ptec = pte.createCriteria();
                ptec.andDelFlagEqualTo("0");
                ptec.andTLevelEqualTo(1);
                ptec.andStatusEqualTo("1");
                List<ProductType> productTypes = productTypeService.selectByExample(pte);
                resMap.put("productTypes", productTypes);

                BrandteamTypeExample e = new BrandteamTypeExample();
                e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
                List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(e);
                resMap.put("brandteamTypes", brandteamTypes);

                //商城一级分类
                MallCategoryExample mallCategoryExample = new MallCategoryExample();
                mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
                List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample);
                resMap.put("mallCategorys", mallCategorys);

            }
        }
        resMap.put("decorateInfoId", decorateInfoId);
        resMap.put("decorateAreaId", decorateAreaId);
        resMap.put("decorateModuleId", decorateModuleId);
        resMap.put("moduleType", moduleType);
        resMap.put("seqNo", seqNo);

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 初始化保存图片模块(插入图片模块)
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/insertPicModule.shtml")
    @ResponseBody
    public Map<String, Object> insertPicModule(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "保存成功");
        try {
            String decorateAreaId = request.getParameter("decorateAreaId");
            String moduleType = request.getParameter("moduleType");
            DecorateModule decorateModule = new DecorateModule();
            decorateModule.setDelFlag("0");
            decorateModule.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            decorateModule.setCreateDate(new Date());
            decorateModule.setDecorateAreaId(Integer.parseInt(decorateAreaId));
            decorateModule.setModuleType(moduleType);
            decorateModuleService.insertSelective(decorateModule);
            resMap.put("decorateModuleId", decorateModule.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "插入失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 初始化保存滑动图片模块(插入滑动图片模块)
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/insertSlidePicModule.shtml")
    @ResponseBody
    public Map<String, Object> insertSlidePicModule(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String decorateAreaId = request.getParameter("decorateAreaId");
        String moduleType = request.getParameter("moduleType");
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "保存成功");
        try {
            DecorateModule decorateModule = new DecorateModule();
            decorateModule.setDelFlag("0");
            decorateModule.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            decorateModule.setCreateDate(new Date());
            decorateModule.setDecorateAreaId(Integer.parseInt(decorateAreaId));
            decorateModule.setModuleType(moduleType);
            decorateModuleService.insertSelective(decorateModule);
            resMap.put("decorateModuleId", decorateModule.getId());

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "插入失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 初始化保存商品模块(插入商品模块)
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/insertProductModule.shtml")
    @ResponseBody
    public Map<String, Object> insertProductModule(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "保存成功");
        try {
            String decorateAreaId = request.getParameter("decorateAreaId");
            String moduleType = request.getParameter("moduleType");
            String seqNo = request.getParameter("seqNo");
            String showNum = request.getParameter("showNum");
            DecorateModule decorateModule = new DecorateModule();
            decorateModule.setDelFlag("0");
            decorateModule.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            decorateModule.setCreateDate(new Date());
            decorateModule.setDecorateAreaId(Integer.parseInt(decorateAreaId));
            decorateModule.setModuleType(moduleType);
            decorateModule.setShowNum(Integer.parseInt(showNum));
            if (!StringUtils.isEmpty(seqNo)) {
                decorateModule.setSeqNo(Integer.parseInt(seqNo));
            }
            decorateModuleService.insertSelective(decorateModule);
            resMap.put("decorateModuleId", decorateModule.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "插入失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 初始化保存特卖模块(插入特卖模块)
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/insertActivityModule.shtml")
    @ResponseBody
    public Map<String, Object> insertActivityModule(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "保存成功");
        try {
            String decorateAreaId = request.getParameter("decorateAreaId");
            String moduleType = request.getParameter("moduleType");
            String seqNo = request.getParameter("seqNo");
            String showNum = request.getParameter("showNum");
            DecorateModule decorateModule = new DecorateModule();
            decorateModule.setDelFlag("0");
            decorateModule.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            decorateModule.setCreateDate(new Date());
            decorateModule.setDecorateAreaId(Integer.parseInt(decorateAreaId));
            decorateModule.setModuleType(moduleType);
            decorateModule.setShowNum(Integer.parseInt(showNum));
            if (!StringUtils.isEmpty(seqNo)) {
                decorateModule.setSeqNo(Integer.parseInt(seqNo));
            }
            decorateModuleService.insertSelective(decorateModule);
            resMap.put("decorateModuleId", decorateModule.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "插入失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 保存装修模块
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/saveDecorateModule.shtml")
    @ResponseBody
    public Map<String, Object> saveDecorateModule(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "保存成功");
        try {
            String decorateInfoId = request.getParameter("decorateInfoId");
            String decorateAreaId = request.getParameter("decorateAreaId");
            String decorateModuleId = request.getParameter("decorateModuleId");
            String moduleType = request.getParameter("moduleType");
            String seqNo = request.getParameter("seqNo");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DecorateModule decorateModule = new DecorateModule();
            if (!StringUtils.isEmpty(decorateModuleId)) {
                decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
                moduleType = decorateModule.getModuleType();
            } else {
                decorateModule.setDelFlag("0");
                decorateModule.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                decorateModule.setCreateDate(new Date());
                decorateModule.setDecorateAreaId(Integer.parseInt(decorateAreaId));
                decorateModule.setModuleType(moduleType);

                DecorateModuleExample dme = new DecorateModuleExample();
                dme.setOrderByClause("seq_no desc");
                dme.createCriteria().andDelFlagEqualTo("0").andDecorateAreaIdEqualTo(Integer.parseInt(decorateAreaId)).andSeqNoIsNotNull();
                List<DecorateModule> decorateModules = decorateModuleService.selectByExample(dme);
                if (decorateModules != null && decorateModules.size() > 0) {
                    decorateModule.setSeqNo(decorateModules.get(0).getSeqNo() + 1);
                } else {
                    decorateModule.setSeqNo(1);
                }
            }
            if (moduleType.equals("1") || moduleType.equals("8") || moduleType.equals("9")) {//1.图片
                String pic = request.getParameter("pic");
                decorateModule.setPic(pic);
                String moduleMapJsonStr = request.getParameter("moduleMapJsonStr");
                List<ModuleMap> moduleMaps = new ArrayList<ModuleMap>();
                if (!StringUtils.isEmpty(moduleMapJsonStr)) {
                    JSONArray ja = JSONArray.fromObject(moduleMapJsonStr);
                    for (int i = 0; i < ja.size(); i++) {
                        ModuleMap moduleMap = new ModuleMap();
                        JSONObject jo = (JSONObject) ja.get(i);
                        String x1 = jo.getString("x1");
                        String y1 = jo.getString("y1");
                        String x2 = jo.getString("x2");
                        String y2 = jo.getString("y2");
                        String coords = x1 + "," + y1 + "," + x2 + "," + y2;
                        String linkType = jo.getString("linkType");
                        if (!linkType.equals("3")) {//非商品
                            if (!linkType.equals("9") && !linkType.equals("13") && !linkType.equals("14") && !linkType.equals("16") && !linkType.equals("18")
                                    && !linkType.equals("19") && !linkType.equals("20") && !linkType.equals("21") && !linkType.equals("22")
                                    && !linkType.equals("23") && !linkType.equals("24") && !linkType.equals("25") && !linkType.equals("26")
                                    && !linkType.equals("27") && !linkType.equals("28") && !linkType.equals("29") && !linkType.equals("30")
                                    && !linkType.equals("31") && !linkType.equals("33")) {//非url链接,非商家店铺,非关键字,非二级分类
                                int linkValue = jo.getInt("linkValue");
                                moduleMap.setLinkValue(linkValue);
                            } else if (linkType.equals("9") || linkType.equals("14") || linkType.equals("16") || linkType.equals("18")
                                    || linkType.equals("19") || linkType.equals("20") || linkType.equals("21") || linkType.equals("22")
                                    || linkType.equals("23") || linkType.equals("24") || linkType.equals("25") || linkType.equals("26")
                                    || linkType.equals("27") || linkType.equals("28") || linkType.equals("29") || linkType.equals("30")
                                    || linkType.equals("31")) {
                                moduleMap.setLinkUrl(jo.getString("linkValue"));
                            } else if (linkType.equals("13")) {
                                MchtInfoExample e = new MchtInfoExample();
                                e.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(jo.getString("linkValue"));
                                List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(e);
                                if (mchtInfos != null && mchtInfos.size() > 0) {
                                    moduleMap.setLinkValue(mchtInfos.get(0).getId());
                                } else {
                                    resMap.put("returnCode", "4004");
                                    resMap.put("returnMsg", "保存失败，商家编号不存在");
                                    return resMap;
                                }
                            } else if (linkType.equals("33")) {
                                int fontSize = jo.getInt("fontSize");
                                moduleMap.setFontSize(fontSize);//字体

                                moduleMap.setFontColor(jo.getString("fontColor"));//颜色

                                String countDownBeginDate = jo.getString("countDownBeginDate");
                                moduleMap.setCountDownBeginDate(sdf.parse(countDownBeginDate));//开始时间

                                String countDownEndDate = jo.getString("countDownEndDate");
                                moduleMap.setCountDownEndDate(sdf.parse(countDownEndDate));//结束时间

                            }
                        } else {//商品
                            String linkValue = jo.getString("linkValue");
                            ProductExample pe = new ProductExample();
                            ProductExample.Criteria pec = pe.createCriteria();
                            pec.andDelFlagEqualTo("0");
                            pec.andCodeEqualTo(linkValue);
                            List<Product> products = productService.selectByExample(pe);
                            if (products == null || products.size() == 0) {
                                continue;
                            } else {
                                moduleMap.setLinkValue(products.get(0).getId());
                            }
                        }
                        moduleMap.setDelFlag("0");
                        moduleMap.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                        moduleMap.setCreateDate(new Date());
                        moduleMap.setCoords(coords);
                        moduleMap.setLinkType(linkType);
                        moduleMaps.add(moduleMap);
                    }
                }
                decorateModuleService.savePicModule(decorateModule, moduleMaps);
            } else if (moduleType.equals("2")) {//2.商品
                int showNum = Integer.parseInt(request.getParameter("showNum"));
                String productIds = request.getParameter("productIds");
                String[] productIdsArray = productIds.trim().split(",");
                List<ModuleItem> moduleItems = new ArrayList<ModuleItem>();
                for (int i = 0; i < productIdsArray.length; i++) {
                    int productId = Integer.parseInt(productIdsArray[i]);
                    if (!StringUtils.isEmpty(decorateModuleId)) {
                        HashMap<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("productId", productId);
                        paramMap.put("decorateInfoId", Integer.parseInt(decorateInfoId));
                        int count = moduleItemService.getCountByProductId(paramMap);
                        if (count == 0) {
                            ModuleItem moduleItem = new ModuleItem();
                            moduleItem.setDelFlag("0");
                            moduleItem.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                            moduleItem.setCreateDate(new Date());
                            moduleItem.setItemId(productId);
                            moduleItem.setItemType("1");
                            moduleItems.add(moduleItem);
                        } else {
                            resMap.put("returnCode", "4004");
                            resMap.put("returnMsg", "保存失败，该商品已存在于别的商品模块中，无法添加");
                            return resMap;
                        }
                    } else {
                        ModuleItem moduleItem = new ModuleItem();
                        moduleItem.setDelFlag("0");
                        moduleItem.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                        moduleItem.setCreateDate(new Date());
                        moduleItem.setItemId(productId);
                        moduleItem.setItemType("1");
                        moduleItems.add(moduleItem);
                    }
                }
                decorateModule.setShowNum(showNum);
                decorateModuleService.save(decorateModule, moduleItems);
            } else if (moduleType.equals("3")) {//3.特卖
                int showNum = Integer.parseInt(request.getParameter("showNum"));
                String activityIds = request.getParameter("activityIds");
                String[] activityIdsArray = activityIds.trim().split(",");
                boolean error = false;
                String errorActivityIds = "";
                for (int i = 0; i < activityIdsArray.length; i++) {
                    if (!StringUtils.isEmpty(activityIdsArray[i])) {
                        Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(activityIdsArray[i]));
                        if (activity == null) {
                            error = true;
                            errorActivityIds += activityIdsArray[i] + ",";
                        }
                    }
                }
                if (error) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "错误的特卖ID为" + errorActivityIds);
                    return resMap;
                }
                HashMap<String, Object> paramMap = new HashMap<String, Object>();
                if (decorateModule.getId() != null) {
                    paramMap.put("decorateModuleId", decorateModule.getId());
                }
                paramMap.put("decorateInfoId", Integer.parseInt(decorateInfoId));
                List<Integer> hasActivityIds = moduleItemService.getItemIdsByInfoId(paramMap);
                List<ModuleItem> moduleItems = new ArrayList<ModuleItem>();
                for (int i = 0; i < activityIdsArray.length; i++) {
                    if (!hasActivityIds.contains(Integer.parseInt(activityIdsArray[i]))) {
                        ModuleItem moduleItem = new ModuleItem();
                        moduleItem.setDelFlag("0");
                        moduleItem.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                        moduleItem.setCreateDate(new Date());
                        moduleItem.setItemId(Integer.parseInt(activityIdsArray[i]));
                        moduleItem.setItemType("2");
                        moduleItem.setSeqNo(i + 1);
                        moduleItems.add(moduleItem);
                    } else {
                        error = true;
                        errorActivityIds += activityIdsArray[i] + ",";
                    }
                }
                if (error) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "同一特卖ID不可以出现在不同的特卖模块中,重复的特卖ID为" + errorActivityIds);
                    return resMap;
                }
                decorateModule.setShowNum(showNum);
                decorateModuleService.save(decorateModule, moduleItems);
            } else if (moduleType.equals("4") || moduleType.equals("5")) {//4.商品列表 5.特卖列表
                String productType1Ids = request.getParameter("productType1Ids");
                String productType2Ids = request.getParameter("productType2Ids");
                String productBrandIds = request.getParameter("productBrandIds");
                boolean error = false;
                String errorProductBrandIds = "";
                if (!StringUtils.isEmpty(productBrandIds)) {
                    String[] productBrandIdsArray = productBrandIds.trim().split(",");
                    List<String> list = new ArrayList<String>();
                    list.add(productBrandIdsArray[0]);
                    for (int i = 0; i < productBrandIdsArray.length; i++) {
                        if (!StringUtils.isEmpty(productBrandIdsArray[i])) {
                            if (list.toString().indexOf(productBrandIdsArray[i]) == -1) {//去除重复id
                                list.add(productBrandIdsArray[i]);
                            }
                            ProductBrand productBrand = productBrandService.selectByPrimaryKey(Integer.parseInt(productBrandIdsArray[i]));
                            if (productBrand == null) {
                                error = true;
                                if (i != productBrandIdsArray.length - 1) {
                                    errorProductBrandIds += productBrandIdsArray[i] + ",";
                                } else {
                                    errorProductBrandIds += productBrandIdsArray[i];
                                }
                            }
                        }
                    }
                    String tmp = "";
                    for (String productBrandId : list) {
                        tmp += productBrandId + ",";
                    }
                    productBrandIds = tmp.substring(0, tmp.length() - 1);
                }
                if (error) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "错误的品牌ID为" + errorProductBrandIds);
                    return resMap;
                }
                decorateModule.setProductType1Ids(productType1Ids);
                decorateModule.setProductType2Ids(productType2Ids);
                decorateModule.setProductBrandIds(productBrandIds);
                decorateModule.setSeqNo(99999);
                decorateModuleService.save(decorateModule);
            } else if (moduleType.equals("10")) {//固定顶部栏目模块
                decorateModule.setFieldFontColor(request.getParameter("fieldFontColor"));
                decorateModule.setFieldSelectFontColor(request.getParameter("fieldSelectFontColor"));
                decorateModule.setFieldBgColor(request.getParameter("fieldBgColor"));
                if (!StringUtils.isEmpty(request.getParameter("fieldBgPic"))) {
                    decorateModule.setFieldBgPic(request.getParameter("fieldBgPic"));
                }
                decorateModule.setOpenFontColor(request.getParameter("openFontColor"));
                decorateModule.setOpenFieldBgColor(request.getParameter("openFieldBgColor"));
                decorateModule.setOpenBtnBgColor(request.getParameter("openBtnBgColor"));
                decorateModule.setOpenBtnArrowColor(request.getParameter("openBtnArrowColor"));
                decorateModuleService.save(decorateModule);
            } else if (moduleType.equals("13")) {//视频模块
                decorateModule.setPic(request.getParameter("pic"));
                decorateModule.setVideoPath(request.getParameter("videoPath"));
                decorateModuleService.save(decorateModule);
            } else if (moduleType.equals("14")) {//图文模块
                decorateModule.setGraphicContent(request.getParameter("graphicContent"));
                decorateModuleService.save(decorateModule);
            }
            resMap.put("decorateModuleId", decorateModule.getId());
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 删除装修模块
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/deleteDecorateModule.shtml")
    @ResponseBody
    public Map<String, Object> deleteDecorateModule(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "删除成功");
        try {
            String id = request.getParameter("id");
            DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(id));
            decorateModule.setDelFlag("1");
            decorateModule.setUpdateDate(new Date());
            decorateModule.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            decorateModuleService.delete(decorateModule);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "删除失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 上移下移装修模块
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/moveDecorateModule.shtml")
    @ResponseBody
    public Map<String, Object> moveDecorateModule(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String prevId = request.getParameter("prevId");
            String nextId = request.getParameter("nextId");
            if (!StringUtils.isEmpty(prevId) && !StringUtils.isEmpty(nextId)) {
                DecorateModule prevDecorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(prevId));
                DecorateModule nextDecorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(nextId));
                prevDecorateModule.setSeqNo(Integer.parseInt(request.getParameter("prevSeqNo")));
                nextDecorateModule.setSeqNo(Integer.parseInt(request.getParameter("nextSeqNo")));
                decorateModuleService.update(prevDecorateModule, nextDecorateModule);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "操作失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 删除商品
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/deleteModuleItem.shtml")
    @ResponseBody
    public Map<String, Object> deleteModuleItem(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "删除成功");
        try {
            String moduleItemId = request.getParameter("moduleItemId");
            ModuleItem moduleItem = moduleItemService.selectByPrimaryKey(Integer.parseInt(moduleItemId));
            moduleItem.setDelFlag("1");
            moduleItemService.updateByPrimaryKeySelective(moduleItem);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "删除失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 排序商品
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/sortModuleItem.shtml")
    @ResponseBody
    public Map<String, Object> sortModuleItem(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "排序成功");
        try {
            String moduleItemId = request.getParameter("moduleItemId");
            String seqNo = request.getParameter("seqNo");
            ModuleItem moduleItem = moduleItemService.selectByPrimaryKey(Integer.parseInt(moduleItemId));
            moduleItem.setSeqNo(Integer.parseInt(seqNo));
            moduleItemService.updateByPrimaryKeySelective(moduleItem);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "排序失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 选择商品列表
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/chooseProductList.shtml")
    public ModelAndView chooseProductList(HttpServletRequest request) {
        String rtPage = "/customPage/chooseProductList";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("decorateInfoId", request.getParameter("decorateInfoId"));
        resMap.put("decorateAreaId", request.getParameter("decorateAreaId"));
        resMap.put("decorateModuleId", request.getParameter("decorateModuleId"));
        resMap.put("showNum", request.getParameter("showNum"));
        resMap.put("seqNo", request.getParameter("seqNo"));
        resMap.put("pageNo", 1);
        ProductTypeExample pte = new ProductTypeExample();
        ProductTypeExample.Criteria ptec = pte.createCriteria();
        ptec.andDelFlagEqualTo("0");
        ptec.andTLevelEqualTo(1);
        ptec.andStatusEqualTo("1");
        List<ProductType> productTypes = productTypeService.selectByExample(pte);
        resMap.put("productTypes", productTypes);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 获取商品分类
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/getProductTypes.shtml")
    @ResponseBody
    public Map<String, Object> getProductTypes(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String productTypeId = request.getParameter("productTypeId");
            if (!StringUtil.isEmpty(productTypeId)) {
                ProductTypeExample pte = new ProductTypeExample();
                ProductTypeExample.Criteria ptec = pte.createCriteria();
                ptec.andDelFlagEqualTo("0");
                ptec.andParentIdEqualTo(Integer.parseInt(productTypeId));
                ptec.andStatusEqualTo("1");
                List<ProductType> productTypes = productTypeService.selectByExample(pte);
                resMap.put("productTypes", productTypes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "操作失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 获取商品列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/getProductData.shtml")
    @ResponseBody
    public Map<String, Object> getProductData(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String decorateInfoId = request.getParameter("decorateInfoId");
            String decorateModuleId = request.getParameter("decorateModuleId");
            String productTypeId = request.getParameter("productType");
            String productTypeId2 = request.getParameter("productType2");
            String productTypeId3 = request.getParameter("productType3");
            String salePriceMin = request.getParameter("salePriceMin");
            String salePriceMax = request.getParameter("salePriceMax");
            String productName = request.getParameter("productName");
            String pageNo = request.getParameter("pageNo");
            ProductCustomExample pce = new ProductCustomExample();
            ProductCustomExample.ProductCustomCriteria pcc = pce.createCriteria();
            pcc.andDelFlagEqualTo("0");
            pcc.andStatusEqualTo("1");//上架
            pcc.andSaleTypeEqualTo("1");//1.品牌款
            if (!StringUtils.isEmpty(productTypeId)) {
                pcc.andProductTypeAll(productService.getProductTypeAllChild(Integer.parseInt(productTypeId)));
            }
            if (!StringUtils.isEmpty(productTypeId2)) {
                pcc.andProductTypeAll(productService.getProductTypeAllChild(Integer.parseInt(productTypeId2)));
            }
            if (!StringUtils.isEmpty(productTypeId3)) {
                pcc.andProductTypeIdEqualTo(Integer.parseInt(productTypeId3));
            }
            if (!StringUtils.isEmpty(salePriceMin)) {
                pcc.andSalePriceGreaterThanOrEqualTo(new BigDecimal(salePriceMin));
            }
            if (!StringUtils.isEmpty(salePriceMax)) {
                pcc.andSalePriceLessThanOrEqualTo(new BigDecimal(salePriceMax));
            }
            if (!StringUtils.isEmpty(productName)) {
                pcc.andNameLike("%" + productName + "%");
            }
            if (!StringUtils.isEmpty(decorateModuleId)) {
                pcc.andIdsNotIn(Integer.parseInt(decorateModuleId));
            }
            DecorateProductPoolExample example = new DecorateProductPoolExample();
            DecorateProductPoolExample.Criteria c = example.createCriteria();
            c.andDelFlagEqualTo("0");
            c.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
            List<DecorateProductPool> decorateProductPools = decorateProductPoolService.selectByExample(example);
            if (decorateProductPools != null && decorateProductPools.size() > 0) {
                DecorateProductPool decorateProductPool = decorateProductPools.get(0);
                if (StringUtils.isEmpty(decorateProductPool.getActivityAreaIds()) && StringUtils.isEmpty(decorateProductPool.getActivityIds()) && StringUtils.isEmpty(decorateProductPool.getProductIds())) {
                    return resMap;
                }
                List<Integer> productIds = new ArrayList<Integer>();
                if (!StringUtils.isEmpty(decorateProductPool.getActivityAreaIds())) {
                    productIds = activityAreaService.getProductIdsByActivityAreaIds(decorateProductPool.getActivityAreaIds());
                }
                if (!StringUtils.isEmpty(decorateProductPool.getActivityIds())) {
                    List<Integer> productIds2 = activityService.getProductIdsByActivityIds(decorateProductPool.getActivityIds());
                    for (Integer productId : productIds2) {
                        if (!productIds.contains(productId)) {
                            productIds.add(productId);
                        }
                    }
                }
                if (!StringUtils.isEmpty(decorateProductPool.getProductIds())) {
                    String codeStr = decorateProductPool.getProductIds();
                    if (!StringUtils.isEmpty(codeStr)) {
                        List<Integer> productIds3 = productService.getProductIdsByCode(codeStr);
                        for (Integer productId : productIds3) {
                            if (!productIds.contains(productId)) {
                                productIds.add(productId);
                            }
                        }
                    }
                }
                if (productIds != null && productIds.size() > 0) {
                    pcc.andIdIn(productIds);
                } else {
                    return resMap;
                }
            } else {
                return resMap;
            }
            List<Integer> existsProductIds = moduleItemService.getItemIdsByItemType(Integer.parseInt(decorateInfoId));
            if (existsProductIds != null && existsProductIds.size() > 0) {
                pcc.andIdNotIn(existsProductIds);
            }
            if (StringUtils.isEmpty(pageNo)) {
                pageNo = "1";
                pce.setLimitStart(0);
            } else {
                pce.setLimitStart((Integer.parseInt(pageNo) - 1) * 10);
            }
            pce.setLimitSize(10);
            int count = productService.countProductCustomByExample(pce);
            List<ProductCustom> productCustoms = productService.selectProductCustomByExample(pce);
            resMap.put("productCustoms", productCustoms);
            resMap.put("pageNo", pageNo);
            if (count % 10 == 0) {
                resMap.put("maxPage", count / 10);
            } else {
                resMap.put("maxPage", count / 10 + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "操作失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 编辑装修商品池 页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/editDecorateProductPool.shtml")
    public ModelAndView editDecorateProductPool(HttpServletRequest request) {
        String rtPage = "/customPage/editDecorateProductPool";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String decorateInfoId = request.getParameter("decorateInfoId");
        DecorateProductPoolExample example = new DecorateProductPoolExample();
        DecorateProductPoolExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
        List<DecorateProductPool> decorateProductPools = decorateProductPoolService.selectByExample(example);
        if (decorateProductPools != null && decorateProductPools.size() > 0) {
            resMap.put("decorateProductPool", decorateProductPools.get(0));
        }
        resMap.put("decorateInfoId", decorateInfoId);
        String pageType = request.getParameter("pageType");
        resMap.put("pageType", pageType);
        String isPreSell = request.getParameter("isPreSell");
        resMap.put("isPreSell", isPreSell);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 保存装修商品池
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/saveDecorateProductPool.shtml")
    @ResponseBody
    public Map<String, Object> saveDecorateProductPool(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "保存成功");
        try {
            String decorateInfoId = request.getParameter("decorateInfoId");
            String activityAreaIds = request.getParameter("activityAreaIds");
            String activityIds = request.getParameter("activityIds");
            String productIds = request.getParameter("productIds");//商品长ID，即code字段
            String isPreSell = request.getParameter("isPreSell");//是否预付会场:0.不是 1.是
            String[] activityAreaIdsArray = activityAreaIds.trim().split(",");
            String[] activityIdsArray = activityIds.trim().split(",");
            String[] productIdsArray = productIds.trim().split(",");
            boolean error = false;
            String errorActivityAreaIds = "";
            String errorActivityIds = "";
            String errorProductIds = "";
            String errorAuditStatusProductIds = "";
            String errorStatusProductIds = "";
            String errorMchtStatusProductIds = "";
            String errorMchtProductBrandStatusProductIds = "";
            String errorMchtProductBrandAuditStatusProductIds = "";
            String errorSingleProductActivityStatusProductIds = "";
            for (int i = 0; i < activityAreaIdsArray.length; i++) {
                if (!StringUtils.isEmpty(activityAreaIdsArray[i])) {
                    ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaIdsArray[i]));
                    if ("1".equals(isPreSell)) {//预付会场
                        if (activityArea == null || !"1".equals(activityArea.getIsPreSell())) {
                            error = true;
                            errorActivityAreaIds += activityAreaIdsArray[i] + ",";
                        }
                    } else {
                        if (activityArea == null) {
                            error = true;
                            errorActivityAreaIds += activityAreaIdsArray[i] + ",";
                        }
                    }
                }
            }
            for (int i = 0; i < activityIdsArray.length; i++) {
                if (!StringUtils.isEmpty(activityIdsArray[i])) {
                    Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(activityIdsArray[i]));
                    if (activity == null) {
                        error = true;
                        errorActivityIds += activityIdsArray[i] + ",";
                    }
                }
            }
            for (int i = 0; i < productIdsArray.length; i++) {
                if (!StringUtils.isEmpty(productIdsArray[i])) {
                    List<HashMap<String, Object>> productStatusList = productService.selectProductStatusByCode(productIdsArray[i]);
                    if (productStatusList == null || productStatusList.size() == 0) {//商品ID是否存在
                        error = true;
                        errorProductIds += productIdsArray[i] + ",";
                    } else {
                        HashMap<String, Object> productStatusMap = productStatusList.get(0);
                        for (HashMap<String, Object> map : productStatusList) {
                            if ("1".equals(map.get("MchtProductBrandStatus")) && "3".equals(map.get("MchtProductBrandAuditStatus"))) {//如果有一个商家品牌是状态正常的,就使用该条结果
                                productStatusMap = map;
                            }
                        }
                        if (!"2".equals(productStatusMap.get("ProductAuditStatus"))) {//商品法务审核状态
                            error = true;
                            errorAuditStatusProductIds += productIdsArray[i] + ",";
                        } else if (!"1".equals(productStatusMap.get("ProductStatus"))) {//上架状态
                            error = true;
                            errorStatusProductIds += productIdsArray[i] + ",";
                        } else if (!"1".equals(productStatusMap.get("MchtStatus"))) {//商家的合作状态
                            error = true;
                            errorMchtStatusProductIds += productIdsArray[i] + ",";
                        } else if (!"1".equals(productStatusMap.get("MchtProductBrandStatus"))) {//商家品牌运营状态
                            error = true;
                            errorMchtProductBrandStatusProductIds += productIdsArray[i] + ",";
                        } else if (!"3".equals(productStatusMap.get("MchtProductBrandAuditStatus"))) {//商家品牌法务审核状态
                            error = true;
                            errorMchtProductBrandAuditStatusProductIds += productIdsArray[i] + ",";
                        } else if ("2".equals(productStatusMap.get("ProductSaleType"))) {//商品为单品款
                            //pageType不存在(入口为任务精选) 且 单品活动状态为下架
                            if (StringUtils.isEmpty(request.getParameter("pageType")) && "0".equals(productStatusMap.get("singleProductActivityStatus"))) {
                                error = true;
                                errorSingleProductActivityStatusProductIds += productIdsArray[i] + ",";
                            } else if (!StringUtils.isEmpty(request.getParameter("pageType"))) {
                                error = true;
                                errorSingleProductActivityStatusProductIds += productIdsArray[i] + ",";
                            }
                        }

						/*//pageType不存在时走任务精选的条件
						if(StringUtils.isEmpty(request.getParameter("pageType"))){
							if("0".equals(productStatusMap.get("ProductStatus")) || "0".equals(productStatusMap.get("ProductAuditStatus"))){
								error = true;
								errorProductIds+=productIdsArray[i]+",";
							}
						}else{//0.下架 2.单品款
							 if("0".equals(productStatusMap.get("ProductStatus")) || "0".equals(productStatusMap.get("ProductSaleType"))){
								error = true;
								errorProductIds+=productIdsArray[i]+",";
							 }
						}*/
                    }
                }
            }
            if (error) {
                String errorMsg = "";
                if (!StringUtils.isEmpty(errorActivityAreaIds)) {
                    errorMsg += "错误的会场ID为" + errorActivityAreaIds;
                }
                if (!StringUtils.isEmpty(errorActivityIds)) {
                    errorMsg += "错误的特卖ID为" + errorActivityIds;
                }
                if (!StringUtils.isEmpty(errorProductIds)) {
                    errorMsg += "错误的商品ID为" + errorProductIds;
                }
                if (!StringUtils.isEmpty(errorAuditStatusProductIds)) {
                    errorMsg += "商品法务审核不通过的商品ID为" + errorAuditStatusProductIds;
                }
                if (!StringUtils.isEmpty(errorStatusProductIds)) {
                    errorMsg += "商品已下架的商品ID为" + errorStatusProductIds;
                }
                if (!StringUtils.isEmpty(errorMchtStatusProductIds)) {
                    errorMsg += "商品商家合作状态不等正常的商品ID为" + errorMchtStatusProductIds;
                }
                if (!StringUtils.isEmpty(errorMchtProductBrandStatusProductIds)) {
                    errorMsg += "商品商家品牌运营状态不等正常的商品ID为" + errorMchtProductBrandStatusProductIds;
                }
                if (!StringUtils.isEmpty(errorMchtProductBrandAuditStatusProductIds)) {
                    errorMsg += "商品商家品牌法务资质审核错误的商品ID为" + errorMchtProductBrandAuditStatusProductIds;
                }
                if (!StringUtils.isEmpty(errorSingleProductActivityStatusProductIds)) {
                    errorMsg += "单品款状态错误的商品ID为" + errorSingleProductActivityStatusProductIds;
                }
                resMap.put("returnCode", "4004");
                resMap.put("returnMsg", errorMsg);
                return resMap;
            }
            DecorateProductPoolExample example = new DecorateProductPoolExample();
            DecorateProductPoolExample.Criteria c = example.createCriteria();
            c.andDelFlagEqualTo("0");
            c.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
            List<DecorateProductPool> decorateProductPools = decorateProductPoolService.selectByExample(example);
            DecorateProductPool dpp = new DecorateProductPool();
            if (decorateProductPools != null && decorateProductPools.size() > 0) {
                dpp = decorateProductPools.get(0);
                dpp.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                dpp.setUpdateDate(new Date());
            } else {
                dpp.setDelFlag("0");
                dpp.setDecorateInfoId(Integer.parseInt(decorateInfoId));
                dpp.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                dpp.setCreateDate(new Date());
            }
            dpp.setActivityAreaIds(activityAreaIds);
            dpp.setActivityIds(activityIds);
            dpp.setProductIds(productIds);
            if (dpp.getId() != null) {
                decorateProductPoolService.updateByPrimaryKeySelective(dpp);
            } else {
                decorateProductPoolService.insertSelective(dpp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 检测品牌
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/checkBrand.shtml")
    public ModelAndView checkBrand(HttpServletRequest request) {
        String rtPage = "/customPage/checkBrand";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String productBrandIds = request.getParameter("productBrandIds");
        String errorIds = "";
        String rightNames = "";
        if (!StringUtils.isEmpty(productBrandIds)) {
            String[] productBrandIdsArray = productBrandIds.trim().split(",");
            for (int i = 0; i < productBrandIdsArray.length; i++) {
                if (!StringUtils.isEmpty(productBrandIdsArray[i])) {
                    try {
                        ProductBrand productBrand = productBrandService.selectByPrimaryKey(Integer.parseInt(productBrandIdsArray[i]));
                        if (productBrand == null) {
                            if (i != productBrandIdsArray.length - 1) {
                                errorIds += productBrandIdsArray[i] + ",";
                            } else {
                                errorIds += productBrandIdsArray[i];
                            }
                        } else {
                            if (i != productBrandIdsArray.length - 1) {
                                rightNames += productBrand.getName() + ",";
                            } else {
                                rightNames += productBrand.getName();
                            }
                        }
                    } catch (Exception e) {
                        if (i != productBrandIdsArray.length - 1) {
                            errorIds += productBrandIdsArray[i] + ",";
                        } else {
                            errorIds += productBrandIdsArray[i];
                        }
                        continue;
                    }
                }
            }
        }
        resMap.put("rightNames", rightNames);
        resMap.put("errorIds", errorIds);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 添加品牌
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/addBrand.shtml")
    public ModelAndView addBrand(HttpServletRequest request) {
        String rtPage = "/customPage/addBrand";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("decorateModuleId", request.getParameter("decorateModuleId"));
        String productType1Ids = request.getParameter("productType1Ids");
        String productType2Ids = request.getParameter("productType2Ids");
        if (!StringUtils.isEmpty(productType1Ids)) {
            ProductType productType1 = productTypeService.selectByPrimaryKey(Integer.parseInt(productType1Ids));
            resMap.put("productType1", productType1);
        }
        if (!StringUtils.isEmpty(productType2Ids)) {
            String[] productType2IdsArray = productType2Ids.split(",");
            List<Integer> productType2IdList = new ArrayList<Integer>();
            for (String productType2Id : productType2IdsArray) {
                if (!StringUtils.isEmpty(productType2Id)) {
                    productType2IdList.add(Integer.parseInt(productType2Id));
                }
            }
            ProductTypeExample example = new ProductTypeExample();
            ProductTypeExample.Criteria c = example.createCriteria();
            c.andDelFlagEqualTo("0");
            c.andIdIn(productType2IdList);
            List<ProductType> productTypes = productTypeService.selectByExample(example);
            resMap.put("productTypes", productTypes);
        }
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 搜索品牌
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/searchBrand.shtml")
    @ResponseBody
    public Map<String, Object> searchBrand(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String productType1Ids = request.getParameter("productType1Ids");
            String productType2Ids = request.getParameter("productType2Ids");
            String productType3Ids = request.getParameter("productType3Ids");
            String brandIds = request.getParameter("brandIds");
            String searchBrandName = request.getParameter("searchBrandName");
            List<Integer> productBrandIds = new ArrayList<Integer>();
            if (!StringUtils.isEmpty(brandIds)) {
                String[] productBrandIdsArray = brandIds.split(",");
                for (String productBrandId : productBrandIdsArray) {
                    productBrandIds.add(Integer.parseInt(productBrandId));
                }
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            if (productBrandIds != null && productBrandIds.size() > 0) {
                paramMap.put("productBrandIds", productBrandIds);
            }
            if (!StringUtils.isEmpty(productType1Ids)) {
                paramMap.put("productType1Ids", Integer.parseInt(productType1Ids));
            }
            if (!StringUtils.isEmpty(productType2Ids)) {
                List<Integer> productTypeIds = new ArrayList<Integer>();
                String[] productType2IdsArray = productType2Ids.split(",");
                for (String productTypeId : productType2IdsArray) {
                    productTypeIds.add(Integer.parseInt(productTypeId));
                }
                paramMap.put("productType2Ids", productTypeIds);
            }
            if (!StringUtils.isEmpty(productType3Ids)) {
                List<Integer> productTypeIds = new ArrayList<Integer>();
                String[] productType3IdsArray = productType3Ids.split(",");
                for (String productTypeId : productType3IdsArray) {
                    productTypeIds.add(Integer.parseInt(productTypeId));
                }
                paramMap.put("productType3Ids", productTypeIds);
            }
            if (!StringUtils.isEmpty(searchBrandName)) {
                paramMap.put("searchBrandName", searchBrandName);
            }
            List<HashMap<String, Object>> brandList = productBrandService.searchBrand(paramMap);
            resMap.put("brandList", brandList);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 更新品牌id集合（列表模块时有值，可为空）
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/updateProductBrandIds.shtml")
    @ResponseBody
    public Map<String, Object> updateProductBrandIds(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String decorateModuleId = request.getParameter("decorateModuleId");
            String brandId = request.getParameter("brandId");
            DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
            if (!StringUtils.isEmpty(decorateModule.getProductBrandIds())) {
                decorateModule.setProductBrandIds(decorateModule.getProductBrandIds() + "," + brandId);
            } else {
                decorateModule.setProductBrandIds(brandId);
            }
            decorateModuleService.updateByPrimaryKeySelective(decorateModule);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 保存显示个数
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/updateShowNum.shtml")
    @ResponseBody
    public Map<String, Object> updateShowNum(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "保存成功");
        try {
            String decorateModuleId = request.getParameter("decorateModuleId");
            String showNum = request.getParameter("showNum");
            DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
            decorateModule.setShowNum(Integer.parseInt(showNum));
            decorateModuleService.updateByPrimaryKeySelective(decorateModule);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 检验热区id是否存在
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/customPage/checkExists.shtml")
    @ResponseBody
    public Map<String, Object> checkExists(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String linkType = request.getParameter("linkType");
            String linkValue = request.getParameter("linkValue");
            if (linkType.equals("1")) {//1.会场
                ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(linkValue));
                if (activityArea == null) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "会场ID有误，请输入正确的ID");
                }
            } else if (linkType.equals("2")) {//2.特卖
                Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(linkValue));
                if (activity == null) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "特卖ID有误，请输入正确的ID");
                }
            } else if (linkType.equals("3")) {//3.商品
                ProductExample pe = new ProductExample();
                ProductExample.Criteria pec = pe.createCriteria();
                pec.andDelFlagEqualTo("0");
                pec.andCodeEqualTo(linkValue);
                List<Product> products = productService.selectByExample(pe);
                if (products == null || products.size() == 0) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "商品ID有误，请输入正确的ID");
                }
            } else if (linkType.equals("4")) {//4.自建页面
                CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(linkValue));
                if (customPage == null) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "ID有误，请输入正确的ID");
                }
            } else if (linkType.equals("10")) {//10.分区锚点
                DecorateArea decorateArea = decorateAreaService.selectByPrimaryKey(Integer.parseInt(linkValue));
                if (decorateArea == null) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "分区锚点ID有误，请输入正确的ID");
                }
            } else if (linkType.equals("11")) {//11.优惠券ID
                CouponExample example = new CouponExample();
                CouponExample.Criteria c = example.createCriteria();
                c.andDelFlagEqualTo("0");
                /*c.andStatusEqualTo("1");*/
                c.andIdEqualTo(Integer.parseInt(linkValue));
                List<Coupon> coupons = couponService.selectByExample(example);
                if (coupons == null || coupons.size() == 0) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "优惠券ID不可用");
                }/*else{
					Coupon coupon = coupons.get(0);
					Date now = new Date();
					if(coupon.getExpiryType().equals("1")){//1.绝对时间
						if(now.after(coupon.getExpiryEndDate())){
							resMap.put("returnCode", "4004");
							resMap.put("returnMsg", "优惠券已失效");
						}
					}else if(coupon.getExpiryType().equals("2")){//2.相对时间
						Date last = DateUtil.getDateAfter(coupon.getRecEndDate(), coupon.getExpiryDays());
						if(now.after(last)){
							resMap.put("returnCode", "4004");
							resMap.put("returnMsg", "优惠券已失效");
						}
					}

				}*/
            } else if (linkType.equals("12")) {//12.商城一级分类
                MallCategory mallCategory = mallCategoryService.selectByPrimaryKey(Integer.parseInt(linkValue));
                if (mallCategory == null) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "ID有误，请输入正确的ID");
                } else {
                    if (mallCategory.getStatus().equals("0")) {//下架
                        resMap.put("returnCode", "4004");
                        resMap.put("returnMsg", "该商场类目已经被下架，请先上架该类目");
                    }
                }
            } else if (linkType.equals("13")) {//13.商家店铺
                MchtInfoExample mie = new MchtInfoExample();
                /*mie.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkValue).andStatusEqualTo("1");*/
                mie.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkValue);
                List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mie);
                if (mchtInfos == null || mchtInfos.size() == 0) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "商家序号有误，请输入正确序号");
                }
            } else if (linkType.equals("16")) {//16.二级分类
                String[] productTypeIdArray = linkValue.split(",");
                List<Integer> productTypeIdList = new ArrayList<Integer>();
                for (String productTypeId : productTypeIdArray) {
                    productTypeIdList.add(Integer.parseInt(productTypeId));
                }
                ProductTypeExample pte = new ProductTypeExample();
                pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(2).andStatusEqualTo("1").andIdIn(productTypeIdList);
                int count = productTypeService.countByExample(pte);
                if (count != productTypeIdList.size()) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "类目ID有误，请重新填写");
                }
            } else if (linkType.equals("19") || linkType.equals("21") || linkType.equals("23") || linkType.equals("25") || linkType.equals("27") || linkType.equals("29") || linkType.equals("31")) {//品牌ID
                String[] productBrandArray = linkValue.split(",");
                List<Integer> productBrandList = new ArrayList<Integer>();
                for (String productBrandID : productBrandArray) {
                    productBrandList.add(Integer.valueOf(productBrandID));
                }

                ProductBrandExample productBrandExampl = new ProductBrandExample();
                productBrandExampl.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(productBrandList);
                int count = productBrandService.countByExample(productBrandExampl);
                if (count != productBrandList.size()) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "品牌ID有误，请重新填写");
                }
            } else if (linkType.equals("17")) {//17.微淘频道
                WetaoChannelExample example = new WetaoChannelExample();
                example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
                List<WetaoChannel> wetaoChannels = weitaoChannelService.selectByExample(example);
                if (wetaoChannels == null || wetaoChannels.size() == 0) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "ID有误，请输入正确的ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     * 类目管理
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/customPage/managementCategory.shtml")
    public ModelAndView managementCategory(HttpServletRequest request) {
        String rtPage = "/customPage/category";
        String decorateModuleId = request.getParameter("decorateModuleId");
        Map<String, Object> resMap = new HashMap<String, Object>();
        ProductModuleTypeExample where = new ProductModuleTypeExample();
        where.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
        where.setOrderByClause("seq_no asc");
        List<ProductModuleType> productModuleTypes = productModuleTypeService.selectByExample(where);
        resMap.put("decorateModuleId", decorateModuleId);
        resMap.put("productModuleTypes", productModuleTypes);
        if (productModuleTypes.size() > 0) {
            resMap.put("firstId", productModuleTypes.get(0).getId());
            resMap.put("lastId", productModuleTypes.get(productModuleTypes.size() - 1).getId());
        }
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 添加和修改
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/customPage/saveCategory.shtml")
    @ResponseBody
    public Map<String, Object> saveCategory(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String decorateModuleId = request.getParameter("decorateModuleId");
            String name = request.getParameter("name");
            //String seqNo = request.getParameter("seqNo");
            String id = request.getParameter("id");
            ProductModuleType record = new ProductModuleType();
            if (!StringUtils.isEmpty(id)) {//存在id即修改操作
                record = productModuleTypeService.selectByPrimaryKey(Integer.parseInt(id));
                if (!StringUtils.isEmpty(decorateModuleId)) {
                    record.setDecorateModuleId(Integer.parseInt(decorateModuleId));
                }
                if (!StringUtils.isEmpty(name)) {
                    record.setName(name);
                }
                productModuleTypeService.updateByPrimaryKeySelective(record);
            } else {//不存在则第一次添加
                if (!StringUtils.isEmpty(decorateModuleId)) {
                    record.setDecorateModuleId(Integer.parseInt(decorateModuleId));
                }
                if (!StringUtils.isEmpty(name)) {
                    record.setName(name);
                }
                ProductModuleTypeExample where = new ProductModuleTypeExample();
                where.createCriteria().andDelFlagEqualTo("0");
                where.setOrderByClause("seq_no desc");
                List<ProductModuleType> productModuleTypes = productModuleTypeService.selectByExample(where);
                //查出当前排序最高的数据,+1
                if (productModuleTypes.size() < 1) {
                    record.setSeqNo(0);
                } else {
                    record.setSeqNo(productModuleTypes.get(0).getSeqNo() + 1);
                }
                record.setCreateDate(new Date());
                record.setDelFlag("0");
                productModuleTypeService.insertSelective(record);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
        }
        return resMap;
    }

    /**
     * 删除,下移,上移
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/customPage/updateCategory.shtml")
    @ResponseBody
    public Map<String, Object> updateCategory(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            String decorateModuleId = request.getParameter("decorateModuleId");
            ProductModuleType productModuleType = productModuleTypeService.selectByPrimaryKey(Integer.parseInt(id));
            Integer seqNo = productModuleType.getSeqNo();
            if ("0".equals(type)) {
                ModuleItemExample ex = new ModuleItemExample();
                ex.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId)).andProductModuleTypeIdEqualTo(Integer.parseInt(id));
                List<ModuleItem> moduleItems = moduleItemService.selectByExample(ex);
                for (ModuleItem moduleItem : moduleItems) {
                    moduleItem.setProductModuleTypeId(null);
                    moduleItemService.updateByPrimaryKey(moduleItem);
                }
                productModuleType.setDelFlag("1");//设置为删除
                productModuleTypeService.updateByPrimaryKeySelective(productModuleType);
            }
            if ("1".equals(type)) {
                //ProductModuleType productModuleTypeUP = getProductModuleTypeUpOrDown(resMap, seqNo, Integer.parseInt(decorateModuleId),type);
                ProductModuleTypeExample where = new ProductModuleTypeExample();
                where.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
                where.setOrderByClause("seq_no Asc");
                List<ProductModuleType> productModuleTypes = productModuleTypeService.selectByExample(where);
                for (int i = 0; i < productModuleTypes.size(); i++) {
                    if (productModuleTypes.get(i).getId() == Integer.parseInt(id)) {
                        ProductModuleType moduleType = productModuleTypes.get(i - 1);
                        productModuleType.setSeqNo(moduleType.getSeqNo());
                        moduleType.setSeqNo(seqNo);
                        productModuleTypeService.updateByPrimaryKeySelective(moduleType);
                        productModuleTypeService.updateByPrimaryKeySelective(productModuleType);
                    }
                }
            }
            if ("2".equals(type)) {
                //ProductModuleType productModuleTypeDown = getProductModuleTypeUpOrDown(resMap, seqNo, Integer.parseInt(decorateModuleId),type);
                ProductModuleTypeExample where = new ProductModuleTypeExample();
                where.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
                where.setOrderByClause("seq_no Asc");
                List<ProductModuleType> productModuleTypes = productModuleTypeService.selectByExample(where);
                for (int i = 0; i < productModuleTypes.size(); i++) {
                    if (productModuleTypes.get(i).getId() == Integer.parseInt(id)) {
                        ProductModuleType moduleType = productModuleTypes.get(i + 1);
                        productModuleType.setSeqNo(moduleType.getSeqNo());
                        moduleType.setSeqNo(seqNo);
                        productModuleTypeService.updateByPrimaryKeySelective(productModuleType);
                        productModuleTypeService.updateByPrimaryKeySelective(moduleType);
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
        }
        return resMap;
    }

    ////查询出上一个或者下一个,和要上移下移的数据替换
    //private ProductModuleType getProductModuleTypeUpOrDown(Map<String, Object> resMap, Integer seqNo, int id, String type) {
    //    ProductModuleTypeExample where = new ProductModuleTypeExample();
    //    where.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(id);
    //    where.setOrderByClause("seq_no desc");
    //    List<ProductModuleType> productModuleTypes = productModuleTypeService.selectByExample(where);
    //    if (productModuleTypes.size() != 1) {
    //        resMap.put("returnCode", "4004");
    //        resMap.put("returnMsg", "数据排序错误,请联系管理员");
    //    }
    //    ProductModuleType productModuleTypeDown = productModuleTypes.get(0);
    //    productModuleTypeDown.setSeqNo(seqNo);
    //    return productModuleTypeDown;
    //}

    /**
     * 设置背景颜色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/customPage/background.shtml")
    public ModelAndView background(HttpServletRequest request) {
        String rtPage = "/customPage/background";
        String decorateModuleId = request.getParameter("decorateModuleId");
        Map<String, Object> resMap = new HashMap<String, Object>();
        DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
        resMap.put("decorateModule", decorateModule);
        resMap.put("decorateModuleId", decorateModuleId);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 保存背景颜色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/customPage/saveColor.shtml")
    @ResponseBody
    public Map<String, Object> saveColor(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String decorateModuleId = request.getParameter("decorateModuleId");
            String bgColor = request.getParameter("bgColor");
            String fieldFontColor = request.getParameter("fieldFontColor");
            String fieldBgColor = request.getParameter("fieldBgColor");
            String fieldSelectFontColor = request.getParameter("fieldSelectFontColor");
            DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
            //无论是第一次新增还是修改,decorateModuleId已经存在,使用update
            decorateModule.setBgColor(bgColor);
            decorateModule.setFieldFontColor(fieldFontColor);
            decorateModule.setFieldBgColor(fieldBgColor);
            decorateModule.setFieldSelectFontColor(fieldSelectFontColor);
            decorateModuleService.updateByPrimaryKeySelective(decorateModule);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
        }
        return resMap;
    }

    /**
     * 选择类目页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/customPage/selectCategory.shtml")
    public ModelAndView selectCategory(HttpServletRequest request) {
        String rtPage = "/customPage/selectCategory";
        String decorateModuleId = request.getParameter("decorateModuleId");
        String productIds = request.getParameter("productIds");
        String moduleItemId = request.getParameter("moduleItemId");
        Map<String, Object> resMap = new HashMap<String, Object>();
        ProductModuleTypeExample where = new ProductModuleTypeExample();
        where.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
        where.setOrderByClause("seq_no asc");
        List<ProductModuleType> productModuleTypes = productModuleTypeService.selectByExample(where);
        if (productModuleTypes.size() > 0) {
            resMap.put("decorateModuleId", decorateModuleId);
            resMap.put("productIds", productIds);
            resMap.put("moduleItemId", moduleItemId);
            resMap.put("productModuleTypes", productModuleTypes);
        }
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 保存选择的类目
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/customPage/saveSelectCategory.shtml")
    @ResponseBody
    public Map<String, Object> saveSelectCategory(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        try {
            String decorateModuleId = request.getParameter("decorateModuleId");
            String categorys = request.getParameter("categorys");
            String moduleItemId = request.getParameter("moduleItemId");
            String productIds = request.getParameter("productIds");
            if (!StringUtils.isEmpty(moduleItemId)) {
                ModuleItem moduleItem = moduleItemService.selectByPrimaryKey(Integer.parseInt(moduleItemId));
                moduleItem.setDecorateModuleId(Integer.parseInt(decorateModuleId));
                moduleItem.setProductModuleTypeId(Integer.parseInt(categorys));
                moduleItem.setUpdateDate(new Date());
                moduleItemService.updateByPrimaryKeySelective(moduleItem);
            } else if (!StringUtils.isEmpty(productIds)) {//如果全部添加就需要ids有多个id,其他时候ids就一个id
                String[] split = productIds.split(",");
                for (String id : split) {
                    ModuleItemExample where = new ModuleItemExample();
                    ModuleItemExample.Criteria criteria = where.createCriteria();
                    criteria.andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId)).andDelFlagEqualTo("0");
                    criteria.andItemIdEqualTo(Integer.parseInt(id)).andItemTypeEqualTo("1");
                    //先查询商品id在表里有没有数据,如果有就up
                    List<ModuleItem> moduleItems = moduleItemService.selectByExample(where);
                    if (moduleItems.size() > 0) {
                        ModuleItem moduleItem = moduleItems.get(0);
                        moduleItem.setDecorateModuleId(Integer.parseInt(decorateModuleId));
                        moduleItem.setProductModuleTypeId(Integer.parseInt(categorys));
                        moduleItem.setUpdateDate(new Date());
                        moduleItemService.updateByPrimaryKeySelective(moduleItem);
                    } else {
                        ModuleItem moduleItem = new ModuleItem();
                        moduleItem.setDecorateModuleId(Integer.parseInt(decorateModuleId));
                        moduleItem.setProductModuleTypeId(Integer.parseInt(categorys));
                        moduleItem.setItemId(Integer.parseInt(id));
                        moduleItem.setItemType("1");//1是代表上面那个itemId是商品id
                        moduleItem.setCreateDate(new Date());
                        moduleItem.setDelFlag("0");
                        moduleItemService.insertSelective(moduleItem);
                    }
                }
            } else {
                resMap.put("returnCode", "4004");
                resMap.put("returnMsg", "设置失败,找不到模块明细id和商品id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
        }
        return resMap;
    }

    @RequestMapping(value = "/customPage/updateShowModel.shtml")
    @ResponseBody
    public Map<String, Object> updateShowModel(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "保存成功");
        try {
            String decorateModuleId = request.getParameter("decorateModuleId");
            String showModel = request.getParameter("showModel");
            DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
            decorateModule.setShowModel(Integer.parseInt(showModel));
            decorateModuleService.updateByPrimaryKeySelective(decorateModule);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

}
