package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class FlowReportController extends BaseController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private SysStatusService sysStatusService;

    @Autowired
    private MemberPvService memberPvService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MchtInfoService mchtInfoService;

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private MchtProductBrandService mchtProductBrandService;

    @Autowired
    private TypeColumnPvStatisticalService typeColumnPvStatisticalService;

    @Autowired
    private ProductPicService productPicService;


    /**
     * @Title
     * @Description
     * 商品流量报表
     */
    @RequestMapping("/flowReport/commodityFlowReport.shtml")
    public ModelAndView helpItemManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/flowReport/commodityFlowReportList");

        //昨天的日期
        String yesterday = getYesterday();
        m.addObject("yesterday",yesterday);

        //九大类目
        List<ProductType> productTypes = getProductType();
        m.addObject("productTypes",productTypes);

        //报表栏目
        List<SysStatus> reportColumnList = getReportColumn();
        m.addObject("reportColumnList",reportColumnList);
        return m;
    }

    /**
     * @Title
     * @Description
     * 商品流量报表的数据
     */
    @RequestMapping("/flowReport/commodityFlowReportData.shtml")
    @ResponseBody
    public Map<String,Object> commodityFlowReportData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");
        String reportColumnId = request.getParameter("reportColumnId");
        String productTypeId = request.getParameter("productTypeId");
        String payBegin = request.getParameter("payBegin");
        String payEnd = request.getParameter("payEnd");

        List<TypeColumnPvStatisticalCustom> dataList = null;
        int totalCount = 0;

        try {
            //数量统计条件,分页
            ProductCustomExample productExample = new ProductCustomExample();
            productExample.setOrderByClause("id");
            ProductCustomExample.ProductCustomCriteria criteria = productExample.createCriteria();
            criteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //查询条件
            Map<String, Object> paraMap = new HashMap<String, Object>();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            String beginDates = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());

            if(!StringUtil.isEmpty(productCode)){
                paraMap.put("productCode",productCode);
                criteria.andCodeEqualTo(productCode);
            }
            if(!StringUtil.isEmpty(productName)){
                paraMap.put("productName","%"+productName+"%");
                criteria.andNameLike(productName);
            }
            if(!StringUtil.isEmpty(reportColumnId)){
                paraMap.put("reportColumnId",reportColumnId);
                //criteria.andReportColumnIdEqualTo(Integer.parseInt(reportColumnId));
            }
            if(!StringUtil.isEmpty(productTypeId)){
                paraMap.put("productTypeId",productTypeId);
                criteria.andProductType1IdEqualTo(Integer.parseInt(productTypeId));
            }
            //只有数据取时间
            if(!StringUtil.isEmpty(payBegin)){
                paraMap.put("payBegin",payBegin+" 00:00:00");
            }
            if(!StringUtil.isEmpty(payEnd)){
                paraMap.put("payEnd",payEnd+" 23:59:59");
            }

            Integer limitSize = page.getLimitSize();
            Integer limitStart = page.getLimitStart();
            if(limitSize!=null && limitStart!=null){
                paraMap.put("limitSize",limitSize);
                paraMap.put("limitStart",limitStart);
            }

             // productService.getCommodityFlowReportData(paraMap);
            dataList=  typeColumnPvStatisticalService.getCommodityFlowReportData(paraMap);
            productExample.setLimitSize(page.getLimitSize());
            productExample.setLimitStart(page.getLimitStart());
            totalCount = productService.countProductCustomByExample(productExample);

            //商品主图集合
            List<Integer> productIds = new ArrayList();
            //商品店铺名称集合
            List<Integer> mchtIds = new ArrayList<>();

            //获取商品的主图和店铺名称，将主图for插入到dataList中
            if(dataList!=null && dataList.size()>0){
                for (TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom : dataList) {
                    productIds.add(typeColumnPvStatisticalCustom.getProductId());
                    mchtIds.add(typeColumnPvStatisticalCustom.getMchtId());
                }
                //商品主图
                ProductPicExample picExample  = new ProductPicExample();
                picExample.setDistinct(true);
                picExample.createCriteria().andProductIdIn(productIds).andIsDefaultEqualTo("1").andDelFlagEqualTo("0");
                List<ProductPic> productPics = productPicService.selectByExample(picExample);
                for (TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom : dataList) {
                    for (ProductPic productPic : productPics) {
                        if(typeColumnPvStatisticalCustom.getProductId().equals(productPic.getProductId()) ){
                            typeColumnPvStatisticalCustom.setPic(productPic.getPic());
                        }
                    }
                }
                //商品店铺名称
                MchtInfoExample mchtExample = new MchtInfoExample();
                mchtExample.createCriteria().andDelFlagEqualTo("0").andIdIn(mchtIds);
                List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtExample);
                for (TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom : dataList) {
                    for (MchtInfo mchtInfo : mchtInfos) {
                        if(typeColumnPvStatisticalCustom.getMchtId().equals(mchtInfo.getId()) ){
                            typeColumnPvStatisticalCustom.setShopName(mchtInfo.getShopName());
                        }
                    }
                }
            }
            resMap.put("Total", totalCount);
            resMap.put("Rows", dataList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }



    /**
     * @Title
     * @Description
     * 商家流量报表
     */
    @RequestMapping("/flowReport/shopFlowReport.shtml")
    public ModelAndView shopFlowReport(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/flowReport/shopFlowReportList");
        //昨天的日期
        String yesterday = getYesterday();
        m.addObject("yesterday",yesterday);

        //九大类目
        List<ProductType> productTypes = getProductType();
        m.addObject("productTypes",productTypes);

        //报表栏目
        List<SysStatus> reportColumnList = getReportColumn();
        m.addObject("reportColumnList",reportColumnList);
        return m;
    }



    /**
     * @Title
     * @Description
     * 商家流量报表的数据
     */
    @RequestMapping("/flowReport/shopFlowReportData.shtml")
    @ResponseBody
    public Map<String,Object> shopFlowReportData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String mchtCode = request.getParameter("mchtCode");
        String mchtName = request.getParameter("mchtName");
        String reportColumnId = request.getParameter("reportColumnId");
        String productTypeId = request.getParameter("productTypeId");
        String payBegin = request.getParameter("payBegin");
        String payEnd = request.getParameter("payEnd");

        List<TypeColumnPvStatisticalCustom> dataList = null;
        int totalCount = 0;
        try {
            //数据查询条件集合
            Map<String,Object> paraMap = new HashMap<>();

            //昨天日期
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            String beginDates = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());

            //分页的查询条件
            MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
            mchtInfoExample.setOrderByClause("id");
            MchtInfoCustomExample.MchtInfoCustomCriteria criteria = mchtInfoExample.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andStatusEqualTo("1");

            if(!StringUtil.isEmpty(mchtCode)){
                paraMap.put("mchtCode",mchtCode);
                criteria.andMchtCodeEqualTo(mchtCode);
            }
            if(!StringUtil.isEmpty(mchtName)){
                paraMap.put("mchtName","%"+mchtName+"%");
                criteria.andMchtNameLike(mchtName);

            }
            if(!StringUtil.isEmpty(reportColumnId)){
                paraMap.put("reportColumnId",reportColumnId);
                //criteria.andReportColumnIdEqualTo(Integer.parseInt(reportColumnId));
            }
            if(!StringUtil.isEmpty(productTypeId)){//商家主营类目
                paraMap.put("productTypeId",productTypeId);
                criteria.andMchtMainTypeIdEqualTo(productTypeId);
            }
            //只有数据取时间,
            if(!StringUtil.isEmpty(payBegin)){
                paraMap.put("payBegin",payBegin+" 00:00:00");
            }
            if(!StringUtil.isEmpty(payEnd)){
                paraMap.put("payEnd",payEnd+" 23:59:59");
            }

            Integer limitSize = page.getLimitSize();
            Integer limitStart = page.getLimitStart();
            if(limitSize!=null && limitStart!=null){
                paraMap.put("limitSize",limitSize);
                paraMap.put("limitStart",limitStart);
            }

            //mchtInfoService.getShopFlowReportData(paraMap);
            dataList=  typeColumnPvStatisticalService.getShopFlowReportData(paraMap);
            mchtInfoExample.setLimitSize(page.getLimitSize());
            mchtInfoExample.setLimitStart(page.getLimitStart());
            totalCount = mchtInfoService.countByExample(mchtInfoExample);

            resMap.put("Total", totalCount);
            resMap.put("Rows", dataList);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }







    /**
     * @Title
     * @Description
     * 品牌流量报表
     */
    @RequestMapping("/flowReport/brandFlowReport.shtml")
    public ModelAndView brandFlowReport(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/flowReport/brandFlowReportList");
        //昨天的日期
        String yesterday = getYesterday();
        m.addObject("yesterday",yesterday);

        //九大类目
        List<ProductType> productTypes = getProductType();
        m.addObject("productTypes",productTypes);

        //报表栏目
        List<SysStatus> reportColumnList = getReportColumn();
        m.addObject("reportColumnList",reportColumnList);

        return m;
    }


    /**
     * @Title
     * @Description
     * 品牌流量报表的数据
     */
    @RequestMapping("/flowReport/brandFlowReportData.shtml")
    @ResponseBody
    public Map<String,Object> brandFlowReportData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();

        String brandName = request.getParameter("brandName");
        String reportColumnId = request.getParameter("reportColumnId");
        String productTypeId = request.getParameter("productTypeId");
        String payBegin = request.getParameter("payBegin");
        String payEnd = request.getParameter("payEnd");

        List<TypeColumnPvStatisticalCustom> dataList = null;
        int totalCount = 0;
        try {
            //数据查询条件集合
            Map<String,Object> paraMap = new HashMap<>();

            //昨天日期
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            String beginDates = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());

            //分页的查询条件
            ProductBrandCustomExample productBrandExamle = new ProductBrandCustomExample();
            productBrandExamle.setOrderByClause("id");
            ProductBrandCustomExample.ProductBrandExampleCriteria criteria = productBrandExamle.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andStatusEqualTo("1");

            if(!StringUtil.isEmpty(brandName)){
                paraMap.put("brandName","%"+brandName+"%");
                criteria.andNameLike(brandName);
            }

            //报表栏目
            if(!StringUtil.isEmpty(reportColumnId)){
                paraMap.put("reportColumnId",reportColumnId);
            }

            //该品牌下,商品的一级类目
            if(!StringUtil.isEmpty(productTypeId)){//商家主营类目
                paraMap.put("productTypeId",productTypeId);
            }
            //只有数据取时间
            if(!StringUtil.isEmpty(payBegin)){
                paraMap.put("payBegin",payBegin+" 00:00:00");
            }
            if(!StringUtil.isEmpty(payEnd)){
                paraMap.put("payEnd",payEnd+" 23:59:59");
            }

            Integer limitSize = page.getLimitSize();
            Integer limitStart = page.getLimitStart();
            if(limitSize!=null && limitStart!=null){
                paraMap.put("limitSize",limitSize);
                paraMap.put("limitStart",limitStart);
            }

            //productBrandService.getBrandFlowReportData(paraMap);
            dataList = typeColumnPvStatisticalService.getBrandFlowReportData(paraMap);
            productBrandExamle.setLimitSize(page.getLimitSize());
            productBrandExamle.setLimitStart(page.getLimitStart());

            totalCount = productBrandService.countByExample(productBrandExamle);

            resMap.put("Total", totalCount);
            resMap.put("Rows", dataList);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }



    /**
     * @Title
     * @Description
     * 类目流量报表
     */
    @RequestMapping("/flowReport/categoryFlowReport.shtml")
    public ModelAndView categoryFlowReport(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/flowReport/categoryFlowReportList");
        //昨天的日期
        String yesterday = getYesterday();
        m.addObject("yesterday",yesterday);

        //九大类目
        List<ProductType> productTypes = getProductType();
        m.addObject("productTypes",productTypes);

        //报表栏目
        List<SysStatus> reportColumnList = getReportColumn();
        m.addObject("reportColumnList",reportColumnList);

        return m;
    }


    /**
     * @Title
     * @Description
     * 类目流量报表的数据
     */
    @RequestMapping("/flowReport/categoryFlowReportData.shtml")
    @ResponseBody
    public Map<String,Object> categoryFlowReportData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();

        String productTypeName = request.getParameter("productTypeName");
        String reportColumnId = request.getParameter("reportColumnId");
        String productTypeId = request.getParameter("productTypeId");
        String payBegin = request.getParameter("payBegin");
        String payEnd = request.getParameter("payEnd");

        List<TypeColumnPvStatisticalCustom> dataList = null;
        int totalCount = 0;
        try {
            //数据查询条件集合
            Map<String,Object> paraMap = new HashMap<>();

            //昨天日期
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            String beginDates = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());

            //分页的查询条件
            ProductTypeExample productTypeExamle = new ProductTypeExample() ;
            productTypeExamle.setOrderByClause("id");
            ProductTypeExample.Criteria criteria = productTypeExamle.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andStatusEqualTo("1");

            if(!StringUtil.isEmpty(productTypeName)){
                paraMap.put("productTypeName","%"+productTypeName+"%");
                criteria.andNameLike(productTypeName);
            }

            //报表栏目
            if(!StringUtil.isEmpty(reportColumnId)){
                paraMap.put("reportColumnId",reportColumnId);
            }

            //该品牌下,商品的一级类目
            if(!StringUtil.isEmpty(productTypeId)){//商家主营类目
                paraMap.put("productTypeId",productTypeId);
                criteria.andParentIdEqualTo(Integer.parseInt(productTypeId));
            }
            //只有数据取时间
            if(!StringUtil.isEmpty(payBegin)){
                paraMap.put("payBegin",payBegin+" 00:00:00");
            }
            if(!StringUtil.isEmpty(payEnd)){
                paraMap.put("payEnd",payEnd+" 23:59:59");
            }

            Integer limitSize = page.getLimitSize();
            Integer limitStart = page.getLimitStart();
            if(limitSize!=null && limitStart!=null){
                paraMap.put("limitSize",limitSize);
                paraMap.put("limitStart",limitStart);
            }

            // productTypeService.getcategoryFlowReportData(paraMap);
            dataList =typeColumnPvStatisticalService.getcategoryFlowReportData(paraMap);
            productTypeExamle.setLimitSize(page.getLimitSize());
            productTypeExamle.setLimitStart(page.getLimitStart());
            totalCount = productTypeService.countByExample(productTypeExamle);

            resMap.put("Total", totalCount);
            resMap.put("Rows", dataList);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }


    /**
     * @Title
     * @Description
     * 获取昨天日期
     */
    private   String  getYesterday(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date yestertime = calendar.getTime();
        String yesterday = sdf.format(yestertime);
        return yesterday;
    }

    /**
     * @Title
     * @Description
     * 九大类目
     */
    private  List<ProductType> getProductType(){
        ProductTypeExample productTypeExample = new ProductTypeExample();
        productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
        List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample );
        return productTypes;
    }


    /**
     * @Title
     * @Description
     * 类目报表
     */
    private List<SysStatus> getReportColumn(){
        SysStatusExample sysStatusExample = new SysStatusExample();
        sysStatusExample.setOrderByClause("ABS(STATUS_VALUE)");
        sysStatusExample.createCriteria().andTableNameEqualTo("BU_MEMBER_PV").andColNameEqualTo("COLUMN_TYPE");
        List<SysStatus> reportColumnList = sysStatusService.selectByExample(sysStatusExample);
        return reportColumnList;
    }


    /**
     * @Title
     * @Description
     * 获取店铺的名称
     */
    @RequestMapping("/flowReport/getTitleName.shtml")
    @ResponseBody
    public Map<String,Object> getTitleName(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();

        try {
            String reportColumnId = request.getParameter("reportColumnId");
            SysStatusExample sysStatusExample = new SysStatusExample();
            SysStatusExample.Criteria criteria = sysStatusExample.createCriteria();
            criteria.andTableNameEqualTo("BU_MEMBER_PV");
            criteria.andColNameEqualTo("COLUMN_TYPE");
            criteria.andStatusValueEqualTo(reportColumnId);
            List<SysStatus> sysStatuses = sysStatusService.selectByExample(sysStatusExample);
            if(sysStatuses!=null && sysStatuses.size()>0){
                resMap.put("colName",sysStatuses.get(0).getStatusDesc());
                resMap.put("reportColumnId",reportColumnId);
            }else {
                resMap.put("colName"," ");
                resMap.put("reportColumnId",reportColumnId);
            }
            resMap.put("returnCode", "0000");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "操作失败，请稍后重试");
        }
        return resMap;
    }



    /**
     * @Title
     * @Description
     * 流量报表的查看商品
     */
    @RequestMapping("/flowReport/shopProductShow.shtml")
    public ModelAndView shopProductShow(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/flowReport/shopProductShowList");
        String id = request.getParameter("id");
        m.addObject("id",id);

        //String yesterday = getYesterday();
        //m.addObject("yesterday",yesterday);

        String type = request.getParameter("type");
        m.addObject("type",type);

        String reportColumnId = request.getParameter("reportColumnId");
        m.addObject("reportColumnId",reportColumnId);

        String payBegin = request.getParameter("payBegin");
        m.addObject("payBegin",payBegin);

        String payEnd = request.getParameter("payEnd");
        m.addObject("payEnd",payEnd);

        return m;
    }



    /**
     * @Title
     * @Description
     * 流量报表的查看商品的数据
     */
    @RequestMapping("/flowReport/shopProductShowData.shtml")
    @ResponseBody
    public Map<String,Object> shopProductShowData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String type = request.getParameter("type");//1, 店铺 2,品牌 3,类目
        String id = request.getParameter("id");//type==1,对应商家id  2,对应品牌id  3,对应一级类目id
        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");
        String reportColumnId = request.getParameter("reportColumnId");
        String payBegin = request.getParameter("payBegin");
        String payEnd = request.getParameter("payEnd");


        List<TypeColumnPvStatisticalCustom> dataList = null;
        int totalCount = 0;
        try {
            //数据查询条件集合
            Map<String,Object> paraMap = new HashMap<>();

            //昨天日期
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            String beginDates = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());

            //分页的查询条件
            ProductCustomExample productExample = new ProductCustomExample();
            productExample.setOrderByClause("id");
            ProductCustomExample.ProductCustomCriteria criteria = productExample.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andStatusEqualTo("1");

            if(!StringUtil.isEmpty(id) && !StringUtil.isEmpty(type)){
                if("1".equals(type)){//店铺
                    paraMap.put("mchtId",id);
                    criteria.andMchtIdEqualTo(Integer.parseInt(id));
                }else if("2".equals(type)){//品牌
                    paraMap.put("brandId",id);
                    criteria.andBrandIdEqualTo(Integer.parseInt(id));
                }else if("3".equals(type)){//类目
                    paraMap.put("productTypeId",id);
                    criteria.andProductType1IdEqualTo(Integer.parseInt(id));
                }

            }

            if(!StringUtil.isEmpty(reportColumnId)){
                paraMap.put("reportColumnId",reportColumnId);
                //criteria.andReportColumnIdEqualTo(Integer.parseInt(reportColumnId));
            }

            if(!StringUtil.isEmpty(productCode)){
                paraMap.put("productCode",productCode);
                criteria.andCodeEqualTo(productCode);
            }
            if(!StringUtil.isEmpty(productName)){
                paraMap.put("productName","%"+productName+"%");
                criteria.andNameLike(productName);
            }

            //只有数据取时间
            if(!StringUtil.isEmpty(payBegin)){
                paraMap.put("payBegin",payBegin+" 00:00:00");
            }
            if(!StringUtil.isEmpty(payEnd)){
                paraMap.put("payEnd",payEnd+" 23:59:59");
            }
            Integer limitSize = page.getLimitSize();
            Integer limitStart = page.getLimitStart();
            if(limitSize!=null && limitStart!=null){
                paraMap.put("limitSize",limitSize);
                paraMap.put("limitStart",limitStart);
            }

            //productService.getCommodityFlowReportData(paraMap);
            dataList = typeColumnPvStatisticalService.getCommodityFlowReportData(paraMap);
            productExample.setLimitSize(page.getLimitSize());
            productExample.setLimitStart(page.getLimitStart());
            totalCount =  productService.countProductCustomByExample(productExample);

            //商品主图集合
            List<Integer> productIds = new ArrayList();
            //商品店铺名称集合
            List<Integer> mchtIds = new ArrayList<>();

            //获取商品的主图和店铺名称，将主图for插入到dataList中
            if(dataList!=null && dataList.size()>0){
                for (TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom : dataList) {
                    productIds.add(typeColumnPvStatisticalCustom.getProductId());
                    mchtIds.add(typeColumnPvStatisticalCustom.getMchtId());
                }
                //商品主图
                ProductPicExample picExample  = new ProductPicExample();
                picExample.setDistinct(true);
                picExample.createCriteria().andProductIdIn(productIds).andIsDefaultEqualTo("1").andDelFlagEqualTo("0");
                List<ProductPic> productPics = productPicService.selectByExample(picExample);
                for (TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom : dataList) {
                    for (ProductPic productPic : productPics) {
                        if(typeColumnPvStatisticalCustom.getProductId() == productPic.getProductId()){
                            typeColumnPvStatisticalCustom.setPic(productPic.getPic());
                        }
                    }
                }
                //商品店铺名称
                MchtInfoExample mchtExample = new MchtInfoExample();
                mchtExample.createCriteria().andDelFlagEqualTo("0").andIdIn(mchtIds);
                List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtExample);
                for (TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom : dataList) {
                    for (MchtInfo mchtInfo : mchtInfos) {
                        if(typeColumnPvStatisticalCustom.getMchtId() == mchtInfo.getId()){
                            typeColumnPvStatisticalCustom.setShopName(mchtInfo.getShopName());
                        }
                    }
                }
            }



            resMap.put("Total", totalCount);
            resMap.put("Rows", dataList);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }


    /**
     * @Title
     * @Description
     * 流量报表的查看品牌
     */
    @RequestMapping("/flowReport/shopBrandShow.shtml")
    public ModelAndView shopBrandShow(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/flowReport/shopBrandShowList");
        String id = request.getParameter("id");
        m.addObject("id",id);

        String type = request.getParameter("type");
        m.addObject("type",type);

        if("1".equals(type)){
            m.addObject("innerMchtId",id);
        }
        if("3".equals(type)){
            m.addObject("innerTypeId",id);
        }

        String reportColumnId = request.getParameter("reportColumnId");
        m.addObject("reportColumnId",reportColumnId);

        String payBegin = request.getParameter("payBegin");
        m.addObject("payBegin",payBegin);

        String payEnd = request.getParameter("payEnd");
        m.addObject("payEnd",payEnd);


        return m;
    }



    /**
     * @Title
     * @Description
     * 流量报表的查看品牌的数据
     */
    @RequestMapping("/flowReport/shopBrandShowData.shtml")
    @ResponseBody
    public Map<String,Object> shopBrandShowData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String type = request.getParameter("type");//1,店铺 3,类目
        String id = request.getParameter("id");//type== 1对应店铺id  3,对应一级类目id
        String reportColumnId = request.getParameter("reportColumnId");
        String brandName = request.getParameter("brandName");
        String payBegin = request.getParameter("payBegin");
        String payEnd = request.getParameter("payEnd");

        List<TypeColumnPvStatisticalCustom> dataList = null;
        int totalCount = 0;
        try {
            //数据查询条件集合
            Map<String,Object> paraMap = new HashMap<>();

            //昨天日期
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            String beginDates = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());

            //分页的查询条件
            ProductBrandCustomExample productBrandExample = new ProductBrandCustomExample();
            productBrandExample.setOrderByClause("id");
            ProductBrandCustomExample.ProductBrandExampleCriteria criteria = productBrandExample.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andStatusEqualTo("1");

            if("1".equals(type)){ //1,店铺
                paraMap.put("mchtId",id);
                criteria.andSearchBrandInMchtIdEqualTo(Integer.parseInt(id));
            }else if ("3".equals(type)){//3,类目
                paraMap.put("productTypeId",id);
                criteria.andSearchBrandInProductType1IdEqualTo(Integer.parseInt(id));
            }
            //报表栏目
            if(!StringUtil.isEmpty(reportColumnId)){
                paraMap.put("reportColumnId",reportColumnId);
                //criteria.andReportColumnIdEqualTo(Integer.parseInt(reportColumnId));
            }

            //品牌名称
            if(!StringUtil.isEmpty(brandName)){
                paraMap.put("brandName","%"+brandName+"%");
                criteria.andNameLike(brandName);
            }

            //只有数据取时间
            if(!StringUtil.isEmpty(payBegin)){
                paraMap.put("payBegin",payBegin+" 00:00:00");
            }
            if(!StringUtil.isEmpty(payEnd)){
                paraMap.put("payEnd",payEnd+" 23:59:59");
            }

            Integer limitSize = page.getLimitSize();
            Integer limitStart = page.getLimitStart();
            if(limitSize!=null && limitStart!=null){
                paraMap.put("limitSize",limitSize);
                paraMap.put("limitStart",limitStart);
            }

          //productBrandService.getBrandFlowReportData(paraMap);
            dataList = typeColumnPvStatisticalService.getBrandFlowReportData(paraMap);
            productBrandExample.setLimitSize(page.getLimitSize());
            productBrandExample.setLimitStart(page.getLimitStart());

            totalCount = productBrandService.countByCustomExample(productBrandExample);
            resMap.put("Total", totalCount);
            resMap.put("Rows", dataList);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }


    /**
     * @Title
     * @Description
     * 流量报表的查看店铺
     */
    @RequestMapping("/flowReport/shopShow.shtml")
    public ModelAndView shopShow(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/flowReport/shopShowList");
        String id = request.getParameter("id");
        m.addObject("id",id);

        String type = request.getParameter("type");
        m.addObject("type",type);

        if("2".equals(type)){
            m.addObject("innerBrandId",id);
        }
        if("3".equals(type)){
            m.addObject("innerTypeId",id);
        }

        String reportColumnId = request.getParameter("reportColumnId");
        m.addObject("reportColumnId",reportColumnId);

        String payBegin = request.getParameter("payBegin");
        m.addObject("payBegin",payBegin);

        String payEnd = request.getParameter("payEnd");
        m.addObject("payEnd",payEnd);
        return m;
    }



    /**
     * @Title
     * @Description
     * 流量报表的查看店铺的数据
     */
    @RequestMapping("/flowReport/shopShowData.shtml")
    @ResponseBody
    public Map<String,Object> shopShowData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String type = request.getParameter("type");//2,品牌 3,类目
        String id = request.getParameter("id");//type==2对应品牌id  3,类目id
        String reportColumnId = request.getParameter("reportColumnId");//栏目报表
        String payBegin = request.getParameter("payBegin");
        String payEnd = request.getParameter("payEnd");
        String mchtCode = request.getParameter("mchtCode");
        String mchtName = request.getParameter("mchtName");

        List<TypeColumnPvStatisticalCustom> dataList = null;
        int totalCount = 0;
        try {
            //数据查询条件集合
            Map<String,Object> paraMap = new HashMap<>();

            //昨天日期
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            String beginDates = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());

            //分页的查询条件
            MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
            mchtInfoCustomExample.setOrderByClause("id");
            MchtInfoCustomExample.MchtInfoCustomCriteria criteria = mchtInfoCustomExample.createCriteria();

            criteria.andStatusEqualTo("1");

            if("2".equals(type)){ //品牌
                paraMap.put("brandId",id);
                criteria.andBrandIdEqualTo(id);
            }else if ("3".equals(type)){//类目
                paraMap.put("productTypeId",id);
                criteria.andProductTypeIdEqualTo(id);
            }
            //报表栏目
            if(!StringUtil.isEmpty(reportColumnId)){
                paraMap.put("reportColumnId",reportColumnId);
            }

            //商家序号
            if(!StringUtil.isEmpty(mchtCode)){
                paraMap.put("mchtCode",mchtCode);
                criteria.andMchtCodeEqualTo(mchtCode);
            }

            //店铺名称
            if(!StringUtil.isEmpty(mchtName)){
                paraMap.put("mchtName","%"+mchtName+"%");
                criteria.andShopNameLike(mchtName);
            }


            //只有数据取时间
            if(!StringUtil.isEmpty(payBegin)){
                paraMap.put("payBegin",payBegin+" 00:00:00");
            }
            if(!StringUtil.isEmpty(payEnd)){
                paraMap.put("payEnd",payEnd+" 23:59:59");
            }

            Integer limitSize = page.getLimitSize();
            Integer limitStart = page.getLimitStart();
            if(limitSize!=null && limitStart!=null){
                paraMap.put("limitSize",limitSize);
                paraMap.put("limitStart",limitStart);
            }

            dataList = typeColumnPvStatisticalService.getShopFlowReportData(paraMap);
            mchtInfoCustomExample.setLimitSize(page.getLimitSize());
            mchtInfoCustomExample.setLimitStart(page.getLimitStart());


            totalCount =mchtInfoService.countByCustomExample(mchtInfoCustomExample);

            resMap.put("Total", totalCount);
            resMap.put("Rows", dataList);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        return resMap;
    }

    /**
     *
     * @Title
     * @Description (获取品牌或类目或店铺会员访客数)
     * @author
     * @date
     */
    @RequestMapping(value = "/flowReport/getVisitorCount.shtml")
    @ResponseBody
    public Map<String, Object> getVisitorCount(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            Map<String,Object> paramMap = new HashMap<String,Object>();

            String payBegin = request.getParameter("payBegin");
            String payEnd = request.getParameter("payEnd");

            if(!StringUtil.isEmpty(payBegin)){
                paramMap.put("payBegin",payBegin+" 00:00:00");
            }
            if(!StringUtil.isEmpty(payEnd)){
                paramMap.put("payEnd",payEnd+" 23:59:59");
            }
            if(!StringUtil.isEmpty(request.getParameter("reportColumnId"))){
                paramMap.put("reportColumnId", request.getParameter("reportColumnId"));
            }
            if(!StringUtil.isEmpty(request.getParameter("productTypeId"))){
                paramMap.put("productTypeId", request.getParameter("productTypeId"));
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtId"))){
                paramMap.put("mchtId", request.getParameter("mchtId"));
            }
            if(!StringUtil.isEmpty(request.getParameter("productBrandId"))){
                paramMap.put("productBrandId", request.getParameter("productBrandId"));
            }

            paramMap.put("flagId", request.getParameter("flagId"));
            paramMap.put("type", request.getParameter("type"));
            Integer allVisitorsNum=0;
            Integer allVisitorTouristNum=0;

            TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom = typeColumnPvStatisticalService.getVisitorCount(paramMap);

            if(typeColumnPvStatisticalCustom!=null){
                if(typeColumnPvStatisticalCustom.getAllVisitorsNum()!=null){
                    allVisitorsNum = typeColumnPvStatisticalCustom.getAllVisitorsNum();
                }
                if(typeColumnPvStatisticalCustom.getAllVisitorTouristNum()!=null){
                    allVisitorTouristNum = typeColumnPvStatisticalCustom.getAllVisitorTouristNum();
                }
            }
            resMap.put("allVisitorsNum", allVisitorsNum);
            resMap.put("allVisitorTouristNum", allVisitorTouristNum);
            resMap.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("code", "4004");
            resMap.put("msg", "获取访客数失败");
        }
        return resMap;
    }


}



