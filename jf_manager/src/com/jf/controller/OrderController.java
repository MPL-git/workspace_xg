package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.*;
import com.jf.dao.KdnWuliuInfoMapper;
import com.jf.dao.OrderDtlCustomMapper;
import com.jf.entity.*;
import com.jf.entity.CombineOrderCustomExample.CombineOrderCustomCriteria;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderController extends BaseController {
    private static final Log logger = LogFactory.getLog(IntellectualPropertyRightService.class);

    @Resource
    private CombineOrderService combineOrderService;

    @Resource
    private SysPaymentService sysPaymentService;

    @Resource
    private OrderDtlService orderDtlService;

    @Resource
    private SubOrderService subOrderService;

    @Resource
    private OrderPreferentialInfoService orderPreferentialInfoService;

    @Resource
    private RemarksLogService remarksLogService;

    @Resource
    private OrderLogService orderLogService;

    @Resource
    private ExpressService expressService;

    @Resource
    private CustomerServiceOrderService customerServiceOrderService;

    @Resource
    private CustomerServicePicService customerServicePicService;

    @Resource
    private CustomerServiceLogService customerServiceLogService;

    @Resource
    private ServiceLogPicService serviceLogPicService;

    @Resource
    private SysStatusService sysStatusService;

    @Resource
    private AppealOrderService appealOrderService;

    @Resource
    private AppealLogService appealLogService;

    @Resource
    private KdnWuliuInfoMapper kdnWuliuInfoMapper;

    @Resource
    private ProductTypeService productTypeService;

    @Resource
    private OrderDtlCustomMapper orderDtlCustomMapper;

    @Resource
    private PlatformContactService platformContactService;

    @Resource
    private OrderAbnormalLogService orderAbnormalLogService;

    @Resource
    private SysStaffInfoService sysStaffInfoService;

    @Resource
    private SysParamCfgService sysParamCfgService;

    @Resource
    private MchtRemarksLogService mchtRemarksLogService;

    @Resource
    private SysStaffRoleService sysStaffRoleService;

    @Resource
    private SubOrderAttachmentService subOrderAttachmentService;

    @Resource
    private SysStaffProductTypeService sysstaffproductTypeService;

    @Autowired
    private OrderProductSnapshotService orderProductSnapshotService;

    @Autowired
    private ProductPicService productPicService;

    @Autowired
    private ProductDescPicService productDescPicService;

    @Resource
    private SysOrganizationService sysOrganizationService;

    @Resource
    private WoRkService woRkService;

    @Resource
    private WoRkHistoryService woRkHistoryService;

    @Resource
    private WorkRecordService workRecordService;

    @Resource
    private AttachmentHistoryService attachmentHistoryService;

    @Resource
    private AttachmentService attachmentService;

    @Resource
    private InterventionOrderService interventionOrderService;

    @Resource
    private SubDepositOrderService subDepositOrderService;

    @Resource
    private MchtPlatformContactService mchtPlatformContactService;

    @Resource
    private MchtProductTypeService mchtProductTypeService;

    @Resource
    private MemberInfoService memberInfoService;

    @Resource
    private OrderViewlogService orderViewlogService;

    @Resource
    private CommonService commonService;

    @Resource
    private MchtInfoService mchtInfoService;

    @Resource
    private PlatformPvStatisticalService platformPvStatisticalService;

    @Resource
    private MemberCollegeStudentCertificationService memberCollegeStudentCertificationService;

    @Resource
    private CombineOrderInvoiceService combineOrderInvoiceService;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 母订单列表
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/combine/list.shtml")
    public ModelAndView orderCombineList(HttpServletRequest request) {
        String rtPage = "/order/combine/list";
        Map<String, Object> resMap = new HashMap<String, Object>();

        //设置下单日期默认是当天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = sdf.format(date);
        resMap.put("today", today);

        SysPaymentExample sysPaymentExample = new SysPaymentExample();
        sysPaymentExample.createCriteria().andDelFlagEqualTo("0").andIsEffectEqualTo("1");
        List<SysPayment> sysPayments = sysPaymentService.selectByExample(sysPaymentExample);
        resMap.put("sysPayments", sysPayments);

        resMap.put("statusList", DataDicUtil.getStatusList("BU_COMBINE_ORDER", "STATUS"));
        resMap.put("financialStatus", DataDicUtil.getStatusList("BU_COMBINE_ORDER", "FINANCIAL_STATUS"));
        resMap.put("sprChnls", DataDicUtil.getStatusList("BU_MEMBER_INFO", "SPR_CHNL"));
        resMap.put("type", request.getParameter("type"));
        resMap.put("orderTypes", DataDicUtil.getStatusList("BU_COMBINE_ORDER", "ORDER_TYPE"));
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 母订单列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/combine/data.shtml")
    @ResponseBody
    public Map<String, Object> orderCombineData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer totalCount = 0;
        try {
            CombineOrderCustomExample combineOrderExample = new CombineOrderCustomExample();
            CombineOrderCustomCriteria combineOrderCriteria = combineOrderExample.createCriteria();
            combineOrderCriteria.andDelFlagEqualTo("0");
            combineOrderExample.setOrderByClause("a.id desc");

            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                String status = request.getParameter("status");
                combineOrderCriteria.andStatusEqualTo(status);
            }

            if (!StringUtil.isEmpty(request.getParameter("paymentId"))) {
                int paymentId = Integer.valueOf(request.getParameter("paymentId"));
                combineOrderCriteria.andPaymentIdEqualTo(paymentId);
            }

            if (!StringUtil.isEmpty(request.getParameter("financialStatus"))) {
                String financialStatus = request.getParameter("financialStatus");
                combineOrderCriteria.andFinancialStatusEqualTo(financialStatus);
            }

            if (!StringUtil.isEmpty(request.getParameter("combineOrderCode"))) {
                String combineOrderCode = request.getParameter("combineOrderCode");
                combineOrderCriteria.andCombineOrderCodeEqualTo(combineOrderCode);
            }

            if (!StringUtil.isEmpty(request.getParameter("paymentNo"))) {
                String paymentNo = request.getParameter("paymentNo");
                combineOrderCriteria.andPaymentNoEqualTo(paymentNo);
            }

            if (!StringUtil.isEmpty(request.getParameter("financialNo"))) {
                String financialNo = request.getParameter("financialNo");
                combineOrderCriteria.andFinancialNoEqualTo(financialNo);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("create_date_begin"))) {
                combineOrderCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin") + " 00:00:00"));
            }

            if (!StringUtil.isEmpty(request.getParameter("create_date_end"))) {
                combineOrderCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end") + " 23:59:59"));
            }

            if (!StringUtil.isEmpty(request.getParameter("pay_date_begin"))) {
                combineOrderCriteria.andPayDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_begin") + " 00:00:00"));
            }

            if (!StringUtil.isEmpty(request.getParameter("pay_date_end"))) {
                combineOrderCriteria.andPayDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_end") + " 23:59:59"));
            }

            if (!StringUtil.isEmpty(request.getParameter("register_date_begin"))) {
                combineOrderCriteria.andCollectionRegisterDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("register_date_begin") + " 00:00:00"));
            }

            if (!StringUtil.isEmpty(request.getParameter("register_date_end"))) {
                combineOrderCriteria.andCollectionRegisterDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("register_date_end") + " 23:59:59"));
            }

            if (!StringUtil.isEmpty(request.getParameter("memberId"))) {
                int memberId = Integer.valueOf(request.getParameter("memberId"));
                combineOrderCriteria.andMemberIdEqualTo(memberId);
            }
            if (!StringUtil.isEmpty(request.getParameter("promotionTypes"))) {
                String promotionTypes = request.getParameter("promotionTypes");
                combineOrderCriteria.andPromotionTypeEqualTo(promotionTypes);
            }

            String sprChnl = null;
            if (!StringUtil.isEmpty(request.getParameter("sprChnl"))) {
                sprChnl = request.getParameter("sprChnl");
                if (sprChnl.equals("10000")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_GDT_YK%");
                } else if (sprChnl.equals("10001")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_GDT_LS%");
                } else if (sprChnl.equals("10002")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_GDT_YC%");
                } else if (sprChnl.equals("10003")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_GDT_YR%");
                } else if (sprChnl.equals("10004")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_GDT_WS%");
                } else if (sprChnl.equals("10005")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_momo_LS%");
                } else if (sprChnl.equals("10006")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_JX%");
                } else if (sprChnl.equals("10007")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DMZY%");
                } else if (sprChnl.equals("10008")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DMZY_A002%");
                } else if (sprChnl.equals("10009")) {
                    combineOrderCriteria.andSprChnlNameLike("XXG_toutiao_DMZY_A004%");
                } else if (sprChnl.equals("10010")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DMZY_A006%");
                } else if (sprChnl.equals("10011")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_weixin_YK%");
                } else if (sprChnl.equals("10012")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_weixin_LD%");
                } else if (sprChnl.equals("10013")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_GDT_DX%");
                } else if (sprChnl.equals("10014")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DMZY_A008%");
                } else if (sprChnl.equals("10015")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DMZY_A0010%");
                } else if (sprChnl.equals("10016")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DMZY_A012%");
                } else if (sprChnl.equals("10017")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_bilibili%");
                } else if (sprChnl.equals("10018")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_iqiyi_MZ%");
                } else if (sprChnl.equals("10019")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao-PZZY%");
                } else if (sprChnl.equals("10020")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_weixin_LS%");
                } else if (sprChnl.equals("10021")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_kuobi%");
                } else if (sprChnl.equals("10022")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_WSZY_A016%");
                } else if (sprChnl.equals("10023")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_WSZY_A020%");
                } else if (sprChnl.equals("10024")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_WSZY_A022%");
                } else if (sprChnl.equals("10025")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_WSZY%");
                } else if (sprChnl.equals("10026")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_SX_sougou%");
                } else if (sprChnl.equals("10027")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_LD_baidu%");
                } else if (sprChnl.equals("10028")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DXZY_B011%");
                } else if (sprChnl.equals("10029")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DXZY_B012%");
                } else if (sprChnl.equals("10030")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DXZY_B013%");
                } else if (sprChnl.equals("10031")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DXZY_B014%");
                } else if (sprChnl.equals("10032")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_DXZY_B015%");
                } else if (sprChnl.equals("10033")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_ZSZY_C011%");
                } else if (sprChnl.equals("10034")) {
                    combineOrderCriteria.andSprChnlNameLike("XG_toutiao_ZSZY_C015%");
                } else if (sprChnl.equals("20000")) { //微信小程序
                    if (!StringUtil.isEmpty(request.getParameter("channel"))) {
                        combineOrderCriteria.andWeixinChannelEqualTo(request.getParameter("channel"));
                    } else {
                        combineOrderCriteria.andRegClientEqualTo("5");
                    }
                } else if (sprChnl.equals("20001")) {//抖音
                    if (!StringUtil.isEmpty(request.getParameter("channel"))) {
                        combineOrderCriteria.andDouyinEqualTo(request.getParameter("channel"));
                    } else {
                        combineOrderCriteria.andRegClientEqualTo("6");
                    }
                } else {
                    combineOrderCriteria.andSprChnlEqualTo(sprChnl);
                }
            }

            if (!StringUtil.isEmpty(request.getParameter("regClient")) && "1".equals(request.getParameter("regClient"))
                    && (StringUtil.isEmpty(sprChnl) || !sprChnl.equals("20000"))) {
                if (!StringUtil.isEmpty(request.getParameter("channel")) || !StringUtil.isEmpty(request.getParameter("spreadname")) || !StringUtil.isEmpty(request.getParameter("activityname"))) {
                    combineOrderCriteria.andTrackDataEqualTo(request.getParameter("channel"), request.getParameter("spreadname"), request.getParameter("activityname"));
                }
            }
            if (!StringUtil.isEmpty(request.getParameter("orderType"))) {
                combineOrderCriteria.andOrderTypeEqualTo(request.getParameter("orderType"));
            }

            /*String notMchtCode="P193103,P193093,P193076";*/
            /*String notMchtCode="";*/
            List<String> listSpecMchtCode = commonService.listSpecMchtCode();
			/*for (String listSpecMchtCodeS : listSpecMchtCode) {
				 if (notMchtCode=="") {
					 notMchtCode+=listSpecMchtCodeS;
				}else {
					 notMchtCode+=","+listSpecMchtCodeS;
				}
			}*/
            List<String> list = new ArrayList<String>();
            for (String listSpecMchtCodeS : listSpecMchtCode) {
                list.add(listSpecMchtCodeS);
            }
            String notMchtid = "";
            MchtInfoExample mchtInfoExample = new MchtInfoExample();
            MchtInfoExample.Criteria criteria = mchtInfoExample.createCriteria();
            criteria.andDelFlagEqualTo("0");
            if(!listSpecMchtCode.isEmpty()){
                criteria.andMchtCodeIn(listSpecMchtCode);
            }
            List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
            for (MchtInfo mchtInfo : mchtInfos) {
                if (notMchtid == "") {
                    notMchtid += mchtInfo.getId();
                } else {
                    notMchtid += "," + mchtInfo.getId();
                }
            }


            combineOrderCriteria.andNotMchtCodeEqualTo(notMchtid);

            totalCount = combineOrderService.countCombineOrderCustomByExample(combineOrderExample);

            combineOrderExample.setLimitStart(page.getLimitStart());
            combineOrderExample.setLimitSize(page.getLimitSize());
            List<CombineOrderCustom> combineOrderCustoms = combineOrderService.selectCombineOrderCustomByExample(combineOrderExample);

            resMap.put("Rows", combineOrderCustoms);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 母订单详情
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/combine/detail.shtml")
    public ModelAndView orderCombineDetail(HttpServletRequest request) {
        String rtPage = "/order/combine/detail";
        Map<String, Object> resMap = new HashMap<String, Object>();

        int combineOrderId = Integer.valueOf(request.getParameter("id"));
        CombineOrderCustom combineOrderCustom = combineOrderService.selectCombineOrderCustomByPrimaryKey(combineOrderId);
        resMap.put("combineOrderCustom", combineOrderCustom);

		/*OrderDtlCustomExample orderDtlCustomExample=new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria=orderDtlCustomExample.createCriteria();
		orderDtlCustomCriteria.andDelFlagEqualTo("0");
		orderDtlCustomCriteria.andCombineOrderIdEqualTo(combineOrderId);
		orderDtlCustomCriteria.andMchtTypeEqualTo("1");
		orderDtlCustomExample.setOrderByClause("a.sub_order_id, a.id");
		List<OrderDtlCustom> orderDtlCustoms=orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
		for (OrderDtlCustom orderDtlCustom:orderDtlCustoms) {
			orderDtlCustom.setSkuPic(FileUtil.getSmallImageName(orderDtlCustom.getSkuPic()));
		}
		resMap.put("orderDtlCustoms", orderDtlCustoms);*/

        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("combineOrderId", combineOrderId);
        mapA.put("mchtType", "1");
        List<Map<String, Object>> orderDtlCustoms = orderDtlService.getSubOrderDtlList(mapA);
        for (Map<String, Object> map : orderDtlCustoms) {
            if (map.get("sku_pic") != null && "".equals(map.get("sku_pic"))) {
                map.put("sku_pic", FileUtil.getSmallImageName(map.get("sku_pic").toString()));
            }
        }
        resMap.put("orderDtlCustoms", orderDtlCustoms);

		/*OrderDtlCustomExample orderDtlCustomExample_P=new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria_P=orderDtlCustomExample_P.createCriteria();
		orderDtlCustomCriteria_P.andDelFlagEqualTo("0");
		orderDtlCustomCriteria_P.andCombineOrderIdEqualTo(combineOrderId);
		orderDtlCustomCriteria_P.andMchtTypeEqualTo("2");
		orderDtlCustomExample_P.setOrderByClause("a.sub_order_id, a.id");
		List<OrderDtlCustom> orderDtlCustoms_P=orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample_P);
		for (OrderDtlCustom orderDtlCustom_P:orderDtlCustoms_P) {
			orderDtlCustom_P.setSkuPic(FileUtil.getSmallImageName(orderDtlCustom_P.getSkuPic()));
		}
		resMap.put("orderDtlCustoms_P", orderDtlCustoms_P);*/

        Map<String, Object> mapB = new HashMap<String, Object>();
        mapB.put("combineOrderId", combineOrderId);
        mapB.put("mchtType", "2");
        List<Map<String, Object>> orderDtlCustoms_P = orderDtlService.getSubOrderDtlList(mapB);
        for (Map<String, Object> map : orderDtlCustoms_P) {
            if (map.get("sku_pic") != null && "".equals(map.get("sku_pic"))) {
                map.put("sku_pic", FileUtil.getSmallImageName(map.get("sku_pic").toString()));
            }
        }
        resMap.put("orderDtlCustoms_P", orderDtlCustoms_P);

        List<OrderPreferentialInfoCustom> orderPreferentialInfoCustoms = orderPreferentialInfoService.selectOrderPreferentialInfoCustomByCombineOrder(combineOrderId);
        resMap.put("orderPreferentialInfoCustoms", orderPreferentialInfoCustoms);

        String staffID = this.getSessionStaffBean(request).getStaffID();//插入一条订单查看日志
        OrderViewlog orderViewlog = new OrderViewlog();
        orderViewlog.setCreateBy(Integer.valueOf(staffID));
        orderViewlog.setCreateDate(new Date());
        orderViewlog.setOrderType("1");
        orderViewlog.setOrderId(combineOrderId);
        orderViewlog.setDelFlag("0");
        orderViewlogService.insert(orderViewlog);

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 子订单列表
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/sub/list.shtml")
    public ModelAndView orderSubList(HttpServletRequest request, String statusPage) {
        String rtPage = "/order/sub/list";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("statusList", DataDicUtil.getStatusList("BU_SUB_ORDER", "STATUS"));
        SysPaymentExample e = new SysPaymentExample();
        SysPaymentExample.Criteria c = e.createCriteria();
        c.andDelFlagEqualTo("0");
        List<SysPayment> sysPaymentList = sysPaymentService.selectByExample(e);
        resMap.put("sysPaymentList", sysPaymentList);
        String staffID = this.getSessionStaffBean(request).getStaffID();

		/*ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = example.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1);
		//钟表运营部状态，只获取主营类目为钟表
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		List<ProductType> productTypeList = productTypeService.selectByExample(example);
		resMap.put("productTypeList", productTypeList);*/
		
		SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
		SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
		sysstaffProductTypeexCriteria.andProductTypeStatus().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));

		//钟表运营部状态，只获取主营类目为钟表
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				sysstaffProductTypeexCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		
		List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
		resMap.put("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));
		resMap.put("statusPage", statusPage); //1.子订单列表	2.子订单导出
		
		/*Integer isContact = 0; //默认不是招商、运营对接人

		PlatformContactExample platformContactExamples = new PlatformContactExample();//当用户是招商、运营时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID).andContactTypeBetween("1", "2");
		List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExamples);
		if (platformContacts != null && platformContacts.size() > 0) {
			isContact = 1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是招商、运营时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}

		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是招商、运营对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);
		resMap.put("platformMchtContacts", platformMchtContact);

		resMap.put("isContact", isContact);*/

        //对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
        String isManagement = this.getSessionStaffBean(request).getIsManagement();//管理层
        resMap.put("isManagement", isManagement);
        resMap.put("staffID", staffID);
        SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
        SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
        sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
        List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
        resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();//上个月1号0点
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Calendar calendars = Calendar.getInstance();//本月明天0点
        calendars.set(Calendar.HOUR_OF_DAY, 23);
        calendars.set(Calendar.MINUTE, 59);
        calendars.set(Calendar.SECOND, 59);

        String dateBegin = df.format(calendar.getTime());
        String dateEnd = df.format(calendars.getTime());

        resMap.put("date_end", dateEnd);
        resMap.put("date_begin", dateBegin);

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 子订单列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/sub/data.shtml")
    @ResponseBody
    public Map<String, Object> orderSubData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<SubOrderCustom> listMap = null;
        Integer totalCount = 0;
        try {

			/*//如当本人为招商对接人，只能查看他本人对接的商家的数据。
			//如果本人不是对接人，那么就可以看全部数据。
			//如果本人为售后 、客服的对接人，那么就可以看全部数据。
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
	        SysStaffInfoExample.Criteria sysStaffInfoExampleCriteri=sysStaffInfoExample.createCriteria();
	        sysStaffInfoExampleCriteri.andStatusEqualTo("A").andStaffIdEqualTo(staffId);
	        List<SysStaffInfo> sysStaffInfos=sysStaffInfoService.selectByExample(sysStaffInfoExample);

			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId);
			List<PlatformContact> platformContactList = platformContactService.selectByExample(platformContactExample);*/
            Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
            SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
            SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
            subOrderCustomCriteria.andDelFlagEqualTo("0");

            //角色id为60的不能看到特价订单
            SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
            sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(60).andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
            if (sysStaffRoleService.countByExample(sysStaffRoleExample) > 0) {
                subOrderCustomCriteria.andIsSpecialEqualTo("0");
            }

            subOrderCustomExample.setOrderByClause("a.id desc");
            /*subOrderCustomCriteria.andPlatformContactsEqualTo(staffID);*/

 			/*if(!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				subOrderCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}*/
            if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {//对接人的商家
                /*subOrderCustomCriteria.andplatContactStaffIdtEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));*/

                MchtPlatformContactCustomExample mchtPlatformContactCustomExample = new MchtPlatformContactCustomExample();
                MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria mchtPlatformContactCustomCriteria = mchtPlatformContactCustomExample.createCriteria();
                mchtPlatformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
                mchtPlatformContactCustomCriteria.andplatContactStaffId(Integer.valueOf(request.getParameter("platContactStaffId")));
                List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactCustomExample);
                List<Integer> mchtIdlist = new ArrayList<Integer>();
                for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
                    mchtIdlist.add(mchtPlatformContactCustom.getMchtId());
                }
                if (mchtIdlist != null && mchtIdlist.size() > 0) {

                    subOrderCustomCriteria.andMchtIdIn(mchtIdlist);
                } else {
                    subOrderCustomCriteria.andMchtIdEqualTo(0);
                }

            }

            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                String status = request.getParameter("status");
                subOrderCustomCriteria.andStatusEqualTo(status);
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                String mchtCode = request.getParameter("mchtCode");
                subOrderCustomCriteria.andMchtCodeEqualTo(mchtCode);
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
                String mchtName = request.getParameter("mchtName");
                subOrderCustomCriteria.andMchtNameLikeTo(mchtName);
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMin"))) {
                BigDecimal amountMin = new BigDecimal(request.getParameter("amountMin"));
                subOrderCustomCriteria.andAmountGreaterThanOrEqualTo(amountMin);
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMax"))) {
                BigDecimal amountMax = new BigDecimal(request.getParameter("amountMax"));
                subOrderCustomCriteria.andAmountLessThanOrEqualTo(amountMax);
            }
            if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
                String subOrderCode = request.getParameter("subOrderCode");
                subOrderCustomCriteria.andSubOrderCodeEqualTo(subOrderCode);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("orderCreateDateBegin"))) {
                subOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("orderCreateDateBegin") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderCreateDateEnd"))) {
                subOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("orderCreateDateEnd") + ":59"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderPayDateBegin"))) {
                subOrderCustomCriteria.andOrderPayDateGreaterThanOrEqualTo(request.getParameter("orderPayDateBegin") + ":00");
            }
            if (!StringUtil.isEmpty(request.getParameter("orderPayDateEnd"))) {
                subOrderCustomCriteria.andOrderPayDateLessThanOrEqualTo(request.getParameter("orderPayDateEnd") + ":59");
            }

            if (!StringUtil.isEmpty(request.getParameter("deliveryDateBegin"))) {
                subOrderCustomCriteria.andDeliveryDateGreaterThanOrEqualTo(sdf.parse((request.getParameter("deliveryDateBegin") + ":00")));
            }
            if (!StringUtil.isEmpty(request.getParameter("deliveryDateEnd"))) {
                subOrderCustomCriteria.andDeliveryDateLessThanOrEqualTo(sdf.parse((request.getParameter("deliveryDateEnd") + ":59")));
            }
            if (!StringUtil.isEmpty(request.getParameter("phone"))) {
                subOrderCustomCriteria.andPhoneOrMobileEqualTo(request.getParameter("phone"));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberInfoId"))) {
                subOrderCustomCriteria.andMemberInfoIdEqualTo(request.getParameter("memberInfoId"));
            }
            if (!StringUtil.isEmpty(request.getParameter("brandName"))) {
                subOrderCustomCriteria.andBrandNameEqualTo(request.getParameter("brandName").trim());
            }
            if (!StringUtil.isEmpty(request.getParameter("artNo"))) {
                subOrderCustomCriteria.andArtNoLikeTo("%" + request.getParameter("artNo").trim() + "%");
            }
            if (!StringUtil.isEmpty(request.getParameter("paymentId"))) {
                subOrderCustomCriteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("paymentId").trim()));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberNick"))) {
                subOrderCustomCriteria.andMemberNickEqualTo(request.getParameter("memberNick").trim());
            }
            if (!StringUtil.isEmpty(request.getParameter("receiverName"))) {
                subOrderCustomCriteria.andReceiverNameLikeTo("%" + request.getParameter("receiverName").trim() + "%");
            }

            //钟表运营部状态，只获取主营类目为钟表
            String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
            if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
                String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
                if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                    subOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
                }
            } else {
                if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
                    subOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
                }
            }

            if (!StringUtil.isEmpty(request.getParameter("wuliu")) && request.getParameter("wuliu").equals("1")) {
                subOrderCustomCriteria.andNotExisitWuliuInfo();
            }
            if (!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
                subOrderCustomCriteria.andMemberStatus(request.getParameter("memberStatus"));
            }
            if (!StringUtil.isEmpty(request.getParameter("promotionType"))) {
                subOrderCustomCriteria.andPromotionTypeTo(request.getParameter("promotionType"));
            }
            totalCount = subOrderService.countSubOrderCustomByExample(subOrderCustomExample);
            subOrderCustomExample.setLimitStart(page.getLimitStart());
            subOrderCustomExample.setLimitSize(page.getLimitSize());
            listMap = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);

            resMap.put("Rows", listMap);
            resMap.put("Total", totalCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 子订单列表数据导出
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/sub/subExport.shtml")
    public void subExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

			/*//如当本人为招商对接人，只能查看他本人对接的商家的数据。
			//如果本人不是对接人，那么就可以看全部数据。
			//如果本人为售后 、客服的对接人，那么就可以看全部数据。
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId);
			List<PlatformContact> platformContactList = platformContactService.selectByExample(platformContactExample);
			List<SubOrderCustom> subOrderCustoms = null;
			SubOrderCustomExample subOrderCustomExample=new SubOrderCustomExample();
			SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria=subOrderCustomExample.createCriteria();
			subOrderCustomCriteria.andDelFlagEqualTo("0");
			subOrderCustomExample.setOrderByClause("a.id desc");
			if(platformContactList != null && platformContactList.size() > 0
					&& !"4".equals(platformContactList.get(0).getContactType())
					&& !"6".equals(platformContactList.get(0).getContactType()) ) {
				subOrderCustomCriteria.andPlatformContactEqualTo(platformContactList.get(0).getId());
			}*/

            Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
            List<SubOrderCustom> subOrderCustoms = null;
            SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
            SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
            subOrderCustomCriteria.andDelFlagEqualTo("0");

            //角色id为60的不能看到特价订单
            SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
            sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(60).andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
            if (sysStaffRoleService.countByExample(sysStaffRoleExample) > 0) {
                subOrderCustomCriteria.andIsSpecialEqualTo("0");
            }


            subOrderCustomExample.setOrderByClause("a.id desc");
            /*subOrderCustomCriteria.andPlatformContactsEqualTo(staffID);*/

			/*if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				subOrderCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}*/
            if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {//对接人的商家
                /*subOrderCustomCriteria.andplatContactStaffIdtEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));*/
                MchtPlatformContactCustomExample mchtPlatformContactCustomExample = new MchtPlatformContactCustomExample();
                MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria mchtPlatformContactCustomCriteria = mchtPlatformContactCustomExample.createCriteria();
                mchtPlatformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
                mchtPlatformContactCustomCriteria.andplatContactStaffId(Integer.valueOf(request.getParameter("platContactStaffId")));
                List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactCustomExample);
                List<Integer> mchtIdlist = new ArrayList<Integer>();
                for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
                    mchtIdlist.add(mchtPlatformContactCustom.getMchtId());
                }
                if (mchtIdlist != null && mchtIdlist.size() > 0) {

                    subOrderCustomCriteria.andMchtIdIn(mchtIdlist);
                } else {
                    subOrderCustomCriteria.andMchtIdEqualTo(0);
                }

            }
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                String status = request.getParameter("status");
                subOrderCustomCriteria.andStatusEqualTo(status);
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                String mchtCode = request.getParameter("mchtCode");
                subOrderCustomCriteria.andMchtCodeEqualTo(mchtCode);
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
                String mchtName = request.getParameter("mchtName");
                subOrderCustomCriteria.andMchtNameLikeTo("%" + mchtName + "%");
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMin"))) {
                BigDecimal amountMin = new BigDecimal(request.getParameter("amountMin"));
                subOrderCustomCriteria.andAmountGreaterThanOrEqualTo(amountMin);
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMax"))) {
                BigDecimal amountMax = new BigDecimal(request.getParameter("amountMax"));
                subOrderCustomCriteria.andAmountLessThanOrEqualTo(amountMax);
            }
            if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
                String subOrderCode = request.getParameter("subOrderCode");
                subOrderCustomCriteria.andSubOrderCodeEqualTo(subOrderCode);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("orderCreateDateBegin"))) {
                subOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("orderCreateDateBegin") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderCreateDateEnd"))) {
                subOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("orderCreateDateEnd") + ":59"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderPayDateBegin"))) {
                subOrderCustomCriteria.andOrderPayDateGreaterThanOrEqualTo(request.getParameter("orderPayDateBegin") + ":00");
            }
            if (!StringUtil.isEmpty(request.getParameter("orderPayDateEnd"))) {
                subOrderCustomCriteria.andOrderPayDateLessThanOrEqualTo(request.getParameter("orderPayDateEnd") + ":59");
            }
            if (!StringUtil.isEmpty(request.getParameter("deliveryDateBegin"))) {
                subOrderCustomCriteria.andDeliveryDateGreaterThanOrEqualTo(sdf.parse((request.getParameter("deliveryDateBegin") + ":00")));
            }
            if (!StringUtil.isEmpty(request.getParameter("deliveryDateEnd"))) {
                subOrderCustomCriteria.andDeliveryDateLessThanOrEqualTo(sdf.parse((request.getParameter("deliveryDateEnd") + ":59")));
            }
            if (!StringUtil.isEmpty(request.getParameter("phone"))) {
                subOrderCustomCriteria.andPhoneOrMobileEqualTo(request.getParameter("phone"));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberInfoId"))) {
                subOrderCustomCriteria.andMemberInfoIdEqualTo(request.getParameter("memberInfoId"));
            }
            if (!StringUtil.isEmpty(request.getParameter("brandName"))) {
                subOrderCustomCriteria.andBrandNameEqualTo(request.getParameter("brandName").trim());
            }
            if (!StringUtil.isEmpty(request.getParameter("artNo"))) {
                subOrderCustomCriteria.andArtNoLikeTo("%" + request.getParameter("artNo").trim() + "%");
            }
            if (!StringUtil.isEmpty(request.getParameter("paymentId"))) {
                subOrderCustomCriteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("paymentId").trim()));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberNick"))) {
                subOrderCustomCriteria.andMemberNickEqualTo(request.getParameter("memberNick").trim());
            }
            if (!StringUtil.isEmpty(request.getParameter("receiverName"))) {
                subOrderCustomCriteria.andReceiverNameLikeTo("%" + request.getParameter("receiverName").trim() + "%");
            }
            //钟表运营部状态，只获取主营类目为钟表
            String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
            if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
                String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
                if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                    subOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
                }
            } else {
                if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
                    subOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
                }
            }
            if (!StringUtil.isEmpty(request.getParameter("wuliu")) && request.getParameter("wuliu").equals("1")) {
                subOrderCustomCriteria.andNotExisitWuliuInfo();
            }
            if (!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
                subOrderCustomCriteria.andMemberStatus(request.getParameter("memberStatus"));
            }
            if (!StringUtil.isEmpty(request.getParameter("promotionType"))) {
                subOrderCustomCriteria.andPromotionTypeTo(request.getParameter("promotionType"));
            }
            subOrderCustoms = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
            String[] titles = {"子订单编号", "类目", "供应商", "商品ID", "品牌/货号", "商品数量", "实付金额", "付款渠道", "会员ID", "收货人", "联系电话", "收货地址",
                    "订单状态", "下单时间", "付款时间", "发货时间", "快递", "是否有物流信息", "售后单", "备注"};
            ExcelBean excelBean = new ExcelBean("导出子订单列表.xls", "导出子订单列表", titles);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String[]> datas = new ArrayList<String[]>();
            for (SubOrderCustom subOrderCustom : subOrderCustoms) {
                String express = "-";
                if (!StringUtil.isEmpty(subOrderCustom.getExpressName())) {
                    express = subOrderCustom.getExpressName();
                }
                if (!StringUtil.isEmpty(subOrderCustom.getExpressNo())) {
                    if (express.equals("-")) {
                        express = subOrderCustom.getExpressNo();
                    } else {
                        express += subOrderCustom.getExpressNo();
                    }
                }
                String desc = "";
				/*if(!StringUtil.isEmpty(subOrderCustom.getIsManageSelfDesc())) {
					desc = subOrderCustom.getIsManageSelfDesc();
				}
				if(!StringUtil.isEmpty(subOrderCustom.getMchtTypeDesc())) {
					desc += subOrderCustom.getMchtTypeDesc();
				}*/
                if (!StringUtil.isEmpty(subOrderCustom.getProductTypename())) {
                    desc += subOrderCustom.getProductTypename();
                }
                StringBuffer customerServiceOrderStr = new StringBuffer();
                if (!StringUtil.isEmpty(subOrderCustom.getCustomerServiceOrderConcat())) {
                    String[] customerServiceOrders = subOrderCustom.getCustomerServiceOrderConcat().split(",");
                    for (int i = 0; i < customerServiceOrders.length; i++) {
                        String[] customerServiceOrder = customerServiceOrders[i].split("\\|");
                        if (i != 0) {
                            customerServiceOrderStr.append("\n");
                        }
                        customerServiceOrderStr.append("【" + customerServiceOrder[0] + "】");
                        customerServiceOrderStr.append(customerServiceOrder[1]);
                    }
                }
                String hasWuliu = "";
                if (StringUtils.isEmpty(subOrderCustom.getKdnId())) {
                    hasWuliu = "无";
                } else {
                    hasWuliu = "有";
                }
                StringBuffer subOrderCode = new StringBuffer("");
                if (!StringUtil.isEmpty(subOrderCustom.getSubOrderCode())) {
                    subOrderCode.append(subOrderCustom.getSubOrderCode());
                    if ("P".equals(subOrderCustom.getMemberInfoStatus())) {
                        subOrderCode.append("\n异常");
                    }
                }
                String[] data = {
                        subOrderCode.toString(),
                        desc,
                        subOrderCustom.getShortName() == null ? "" : subOrderCustom.getShortName(),
                        subOrderCustom.getProductid() == null ? "" : subOrderCustom.getProductid().toString(),
                        subOrderCustom.getArtBrandGroup() == null ? "" : subOrderCustom.getArtBrandGroup().replaceAll(",", "\n"),
                        subOrderCustom.getProductQuantity() == null ? "" : subOrderCustom.getProductQuantity().toString(),
                        subOrderCustom.getPayAmount() == null ? "" : subOrderCustom.getPayAmount().toString(),
                        subOrderCustom.getPaymentName() == null ? "" : subOrderCustom.getPaymentName(),
                        subOrderCustom.getMemberId() == null ? "" : subOrderCustom.getMemberId().toString(),
//						subOrderCustom.getMemberNick()==null?"":subOrderCustom.getMemberNick(),
                        subOrderCustom.getReceiverName() == null ? "" : subOrderCustom.getReceiverName(),
                        subOrderCustom.getReceiverPhone() == null ? "" : subOrderCustom.getReceiverPhone(),
                        subOrderCustom.getReceiverAddress() == null ? "" : subOrderCustom.getReceiverAddress(),
                        subOrderCustom.getStatusDesc() == null ? "" : subOrderCustom.getStatusDesc(),
                        subOrderCustom.getOrderCreateDate() == null ? "" : df.format(subOrderCustom.getOrderCreateDate()),
                        subOrderCustom.getOrderPayDate() == null ? "" : df.format(subOrderCustom.getOrderPayDate()),
                        subOrderCustom.getDeliveryDate() == null ? "" : df.format(subOrderCustom.getDeliveryDate()),
                        express,
                        hasWuliu,
                        customerServiceOrderStr.toString(),
                        subOrderCustom.getRemarks() == null ? "" : subOrderCustom.getRemarks()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 子订单详情
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/sub/detail.shtml")
    public ModelAndView orderSubDetail(HttpServletRequest request) {
        String rtPage = "order/sub/detail";
        Map<String, Object> resMap = new HashMap<String, Object>();

        StaffBean staffBean = this.getSessionStaffBean(request);
        Integer staffId = Integer.valueOf(staffBean.getStaffID());

        int subOrderId = Integer.valueOf(request.getParameter("id"));
        SubOrderCustom subOrderCustom = subOrderService.selectSubOrderCustomByPrimaryKey(subOrderId);
        resMap.put("subOrderCustom", subOrderCustom);

        CombineOrder combineOrder1 = combineOrderService.selectByPrimaryKey(subOrderCustom.getCombineOrderId());
        resMap.put("promotionType", combineOrder1.getPromotionType());

		/*PlatformContactCustomExample platformContactCustomExample = new PlatformContactCustomExample();
		PlatformContactCustomExample.PlatformContactCustomCriteria platformContactCustomCriteria = platformContactCustomExample.createCriteria();
		platformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId);
		List<String> contactTypeList = new ArrayList<String>();
		contactTypeList.add("4"); //售后对接人
		contactTypeList.add("6"); //客服对接人
		platformContactCustomCriteria.andContactTypeIn(contactTypeList);
		platformContactCustomCriteria.andMchtIdEqualTo(subOrderCustom.getMchtId());
		List<PlatformContactCustom> platformContactCustomList = platformContactService.selectPlatformContactCustomByExample(platformContactCustomExample);
		if(platformContactCustomList != null && platformContactCustomList.size() > 0) {
			resMap.put("platformContactCustomList", platformContactCustomList); //标记异常订单权限
		}*/

        SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
        SysParamCfgExample.Criteria sysParamCfgCriteria = sysParamCfgExample.createCriteria();
        sysParamCfgCriteria.andParamCodeEqualTo("AFTER_SALE_GROUP_ROLE");
        sysParamCfgExample.setLimitSize(1);
        List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
        SysParamCfg sysParamCfg = sysParamCfgs.get(0);


        SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
        SysStaffRoleExample.Criteria sysStaffRoleCriteria = sysStaffRoleExample.createCriteria();
        sysStaffRoleCriteria.andStatusEqualTo("A").andStaffIdEqualTo(staffId);
        List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
        for (SysStaffRole sysStaffRole : sysStaffRoleList) {
            if (!StringUtil.isEmpty(sysParamCfg.getParamValue()) && sysStaffRole.getRoleId().toString().equals(sysParamCfg.getParamValue())) {
                resMap.put("afterSaleGroupRole", sysParamCfg.getParamCode());
                break;
            }
        }
        OrderAbnormalLogExample orderAbnormalLogExample = new OrderAbnormalLogExample();
        OrderAbnormalLogExample.Criteria orderAbnormalLogCriteria = orderAbnormalLogExample.createCriteria();
        orderAbnormalLogCriteria.andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderCustom.getId());
        List<OrderAbnormalLogCustom> orderAbnormalLogCustomList = orderAbnormalLogService.selectByCustomExample(orderAbnormalLogExample);
        resMap.put("orderAbnormalLogCustomList", orderAbnormalLogCustomList);

        List<OrderPreferentialInfoCustom> orderPreferentialInfoCustoms = orderPreferentialInfoService.selectOrderPreferentialInfoCustomBySubOrder(subOrderId);
        resMap.put("orderPreferentialInfoCustoms", orderPreferentialInfoCustoms);

        OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
        OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
        orderDtlCustomCriteria.andDelFlagEqualTo("0");
        orderDtlCustomCriteria.andSubOrderIdEqualTo(subOrderId);

        List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExampleSource(orderDtlCustomExample);
        for (OrderDtlCustom orderDtlCustom : orderDtlCustoms) {
            CustomerServiceOrderExample example = new CustomerServiceOrderExample();
            example.setOrderByClause("id desc");
            CustomerServiceOrderExample.Criteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andSubOrderIdEqualTo(subOrderId);
            criteria.andOrderDtlIdEqualTo(orderDtlCustom.getId());
            List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(example);
            if (customerServiceOrders != null && customerServiceOrders.size() > 0) {
                orderDtlCustom.setCustomerServiceId(customerServiceOrders.get(0).getId());
            }
            orderDtlCustom.setSkuPic(FileUtil.getSmallImageName(orderDtlCustom.getSkuPic()));
        }

        resMap.put("orderDtlCustoms", orderDtlCustoms);

        RemarksLogExample remarksLogExample = new RemarksLogExample();
        remarksLogExample.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderId);
        remarksLogExample.setOrderByClause("a.id desc");
        List<RemarksLogCustom> remarksLogCustoms = remarksLogService.selectRemarksLogCustomByExample(remarksLogExample);
        resMap.put("remarksLogCustoms", remarksLogCustoms);

        OrderLogExample orderLogExample = new OrderLogExample();
        orderLogExample.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderId);
        List<OrderLogCustom> orderLogCustoms = orderLogService.selectOrderLogCustomByExample(orderLogExample);
        resMap.put("orderLogCustoms", orderLogCustoms);

        MchtRemarksLogExample mrle = new MchtRemarksLogExample();
        mrle.setOrderByClause("a.id desc");
        MchtRemarksLogExample.Criteria c = mrle.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andSubOrderIdEqualTo(subOrderId);
        List<MchtRemarksLogCustom> mchtRemarksLogs = mchtRemarksLogService.selectByCustomExample(mrle);
        resMap.put("mchtRemarksLogs", mchtRemarksLogs);

        SubOrderAttachmentExample soae = new SubOrderAttachmentExample();
        SubOrderAttachmentExample.Criteria soaec = soae.createCriteria();
        soaec.andDelFlagEqualTo("0");
        soaec.andSubOrderIdEqualTo(subOrderId);
        List<SubOrderAttachmentCustom> subOrderAttachmentCustoms = subOrderAttachmentService.selectSubOrderAttachmentCustomByExample(soae);
        resMap.put("subOrderAttachmentCustoms", subOrderAttachmentCustoms);
        if (subOrderCustom.getReceiptDate() != null) {
            resMap.put("receiptDateTime", subOrderCustom.getReceiptDate().getTime());
            resMap.put("receiptEndDateTime", subOrderCustom.getReceiptDate().getTime() + 7 * 24 * 60 * 60 * 1000);
        }

        WoRkExample woRkExample = new WoRkExample();
        woRkExample.createCriteria().andDelFlagEqualTo("0").andRelevantIdEqualTo(subOrderId);
        List<WoRk> woRkCustoms = woRkService.selectByExample(woRkExample);
        if (woRkCustoms.size() <= 0) {
            resMap.put("woRkCustoms", true);
        }

        //获取控建角色
        SysStaffRoleExample sysStaffRoleExample1 = new SysStaffRoleExample();
        sysStaffRoleExample1.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(staffId).andRoleIdEqualTo(89);
        List<SysStaffRole> sysStaffRolelist = sysStaffRoleService.selectByExample(sysStaffRoleExample1);
        if (sysStaffRolelist != null && sysStaffRolelist.size() > 0) {
            resMap.put("role89", sysStaffRolelist.get(0).getRoleId());
        }


        MemberInfo memberCouponCustom = memberInfoService.selectByPrimaryKey(subOrderCustom.getMemberId());
        if (memberCouponCustom.getIsSvip() != null && memberCouponCustom.getIsSvip().equals("1")) {//是否SVIP会员
            Date date1 = new Date();
            Date date2 = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date1 = df.parse(df.format(new Date()));
                date2 = df.parse(df.format(memberCouponCustom.getSvipExpireDate()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (date1.before(date2)) {
                resMap.put("memberCouponCustom", true);
            }
        }


        String staffID = this.getSessionStaffBean(request).getStaffID();//插入一条订单查看日志
        OrderViewlog orderViewlog = new OrderViewlog();
        orderViewlog.setCreateBy(Integer.valueOf(staffID));
        orderViewlog.setCreateDate(new Date());
        orderViewlog.setOrderType("2");
        orderViewlog.setOrderId(subOrderId);
        orderViewlog.setDelFlag("0");
        orderViewlogService.insert(orderViewlog);
        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrderCustom.getCombineOrderId());
        String orderTypeDesc = DataDicUtil.getStatusDesc("BU_COMBINE_ORDER", "ORDER_TYPE", combineOrder.getOrderType());
        resMap.put("orderTypeDesc", orderTypeDesc);
        return new ModelAndView(rtPage, resMap);
    }


    /**
     * 大学生认证批量审核和驳回弹窗
     *
     * @param request
     * @param statusPage
     * @return
     */
    @RequestMapping(value = "/order/sub/batchAuditOrRetrialPage.shtml")
    public ModelAndView batchAuditOrRetrialPage(HttpServletRequest request, String statusPage) {
        String rtPage = "/order/sub/batchAuditOrRetrial";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        String type = request.getParameter("type");
        resMap.put("ids", ids);
        resMap.put("type", type);
        SysStatusExample sysStatusExample = new SysStatusExample();
        sysStatusExample.createCriteria().andTableNameEqualTo("COLLEGE_STUDENT_CERTIFICATION").andColNameEqualTo("AUDIT_REASON_STATUS");
        sysStatusExample.setOrderByClause("status_order");
        List<SysStatus> sysStatusLst = sysStatusService.selectByExample(sysStatusExample);
        resMap.put("sysStatusLst", sysStatusLst);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 大学生认证订单批量审核或驳回
     *
     * @param request
     * @param statusPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order/sub/batchAuditOrRetrial.shtml")
    public Map<String, Object> batchAuditOrRetrial(HttpServletRequest request, String statusPage) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            String ids = request.getParameter("ids");
            String auditStatus = request.getParameter("auditStatus");
            if (!StringUtil.isEmpty(ids)) {
                String[] idsplit = ids.split(",");
                for (String id : idsplit) {
                    if ("3".equals(auditStatus)) {//批量审核通过
                        resMap.put("returnCode", "0000");
                        resMap.put("returnMsg", "审核成功");
                        resMap = passOrReject(request, id, "3", "2", resMap);
                    } else {//驳回或者批量驳回
                        resMap.put("returnCode", "0000");
                        resMap.put("returnMsg", "驳回成功");
                        resMap = passOrReject(request, id, "4", "3", resMap);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "给用户发送信息失败");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "操作失败,请联系管理员");
        }
        return resMap;
    }

    //提取审核过程和驳回过程的方法
    private Map<String, Object> passOrReject(HttpServletRequest request, String id, String auditStatus, String status, Map<String, Object> resMap) {
        Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
        SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(id));
        Integer combineOrderId = subOrder.getCombineOrderId();
        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(combineOrderId);//关联母订单

        //查找大学生认证数据,如果有则审核,如果没有就报错,前端不应该能请求过来
        Integer memberId = combineOrder.getMemberId();
        MemberCollegeStudentCertificationExample ex = new MemberCollegeStudentCertificationExample();
        ex.createCriteria().andMemberIdEqualTo(memberId);
        List<MemberCollegeStudentCertification> memberCollegeStudentCertifications = memberCollegeStudentCertificationService.selectByExample(ex);
        if (memberCollegeStudentCertifications.size() > 0) {
            for (MemberCollegeStudentCertification memberCollegeStudentCertification : memberCollegeStudentCertifications) {
                //String studentStatus = memberCollegeStudentCertification.getStatus();//大学生认证表的状态不为待审的不修改
                //if ("1".equals(studentStatus)) {
                    Date payDate = combineOrder.getPayDate();
                    Date now = new Date();//承诺发货时间=认证审核通过时间(now)-订单付款时间
                    subOrder.setDeliveryLastDate(new Date(subOrder.getDeliveryLastDate().getTime() + now.getTime() - payDate.getTime()));
                    subOrder.setAuditDate(now);
                    subOrder.setAuditStatus(auditStatus);
                    subOrder.setUpdateBy(staffID);
                    subOrder.setUpdateDate(now);
                    //根据用户id查找到的大学生认证表数据
                    memberCollegeStudentCertification.setStatus(status);//全部修改为成功(2)然后修改
                    memberCollegeStudentCertification.setAuditBy(staffID);
                    memberCollegeStudentCertification.setAuditDate(now);
                    memberCollegeStudentCertification.setUpdateBy(staffID);
                    memberCollegeStudentCertification.setUpdateDate(now);
                    if ("4".equals(auditStatus)) {//如果是驳回,还要加上驳回理由
                        String reason = request.getParameter("reason");
                        memberCollegeStudentCertification.setAuditReasonStatus(reason);
                        subOrder.setAuditReasonStatus(reason);
                    }
                    memberCollegeStudentCertificationService.updateByPrimaryKeySelective(memberCollegeStudentCertification);
                    subOrderService.updateByPrimaryKeySelective(subOrder);//修改子订单表的审核状态
                //}
            }
        } else {
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "没有上传大学生认证信息,请联系管理员");
        }
        if ("4".equals(auditStatus)) {
            /*MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);*/
            String receiverPhone = combineOrder.getReceiverPhone();//获取收货人联系电话
            JSONObject param = new JSONObject();
            JSONObject reqData = new JSONObject();
            reqData.put("mobile", receiverPhone);
            reqData.put("content", "【醒购】您好，您在【大学生1分领口罩】活动中提交的认证信息未通过审核，请确保资料合规且清晰，您可前往认证页面进行修改并重新提交。");
            reqData.put("smsType", "4");
            param.put("reqData", reqData);
            JSONObject result = JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl + "/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
            if (!"0000".equals(result.getString("returnCode"))) {
                logger.info("大学生认证审核发送用户失败！！！！！");
            }
        }
        return resMap;
    }

    /**
     * 大学生认证子订单审核通过
     *
     * @param request
     * @param statusPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order/sub/auditPass.shtml")
    public Map<String, Object> auditPass(HttpServletRequest request, String statusPage) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            String id = request.getParameter("id");
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "审核成功");
            passOrReject(request, id, "3", "2", resMap);//审核操作
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "审核失败,请联系管理员");
        }
        return resMap;
    }

    /**
     * 大学生认证订单审核页面
     *
     * @param request
     * @param statusPage
     * @return
     */
    @RequestMapping(value = "/order/sub/orderReview.shtml")
    public ModelAndView orderReview(HttpServletRequest request, String statusPage) {
        String rtPage = "/order/sub/orderReview";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("statusList", DataDicUtil.getStatusList("BU_SUB_ORDER", "STATUS"));
        SysPaymentExample e = new SysPaymentExample();
        SysPaymentExample.Criteria c = e.createCriteria();
        c.andDelFlagEqualTo("0");
        List<SysPayment> sysPaymentList = sysPaymentService.selectByExample(e);
        resMap.put("sysPaymentList", sysPaymentList);
        String staffID = this.getSessionStaffBean(request).getStaffID();

        SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
        SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
        sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));

        //钟表运营部状态，只获取主营类目为钟表
        String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
        if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
            String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
            if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                sysstaffProductTypeexCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
            }
        }
        resMap.put("isCwOrgStatus", isCwOrgStatus);

        List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
        resMap.put("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));
        resMap.put("statusPage", statusPage); //1.子订单列表	2.子订单导出

        //对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
        String isManagement = this.getSessionStaffBean(request).getIsManagement();//管理层
        resMap.put("isManagement", isManagement);
        resMap.put("staffID", staffID);
        SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
        SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
        sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
        List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
        resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();//上个月1号0点
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Calendar calendars = Calendar.getInstance();//本月明天0点
        calendars.set(Calendar.HOUR_OF_DAY, 23);
        calendars.set(Calendar.MINUTE, 59);
        calendars.set(Calendar.SECOND, 59);

        String dateBegin = df.format(calendar.getTime());
        String dateEnd = df.format(calendars.getTime());

        resMap.put("date_end", dateEnd);
        resMap.put("date_begin", dateBegin);

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 大学生认证订单审核列表数据
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/order/sub/orderReviewData.shtml")
    @ResponseBody
    public Map<String, Object> orderReviewData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<SubOrderCustom> listMap = null;
        Integer totalCount = 0;
        try {

			/*//如当本人为招商对接人，只能查看他本人对接的商家的数据。
			//如果本人不是对接人，那么就可以看全部数据。
			//如果本人为售后 、客服的对接人，那么就可以看全部数据。
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
	        SysStaffInfoExample.Criteria sysStaffInfoExampleCriteri=sysStaffInfoExample.createCriteria();
	        sysStaffInfoExampleCriteri.andStatusEqualTo("A").andStaffIdEqualTo(staffId);
	        List<SysStaffInfo> sysStaffInfos=sysStaffInfoService.selectByExample(sysStaffInfoExample);

			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId);
			List<PlatformContact> platformContactList = platformContactService.selectByExample(platformContactExample);*/
            Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
            SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
            SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
            subOrderCustomCriteria.andDelFlagEqualTo("0");

            //角色id为60的不能看到特价订单
            SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
            sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(60).andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
            if (sysStaffRoleService.countByExample(sysStaffRoleExample) > 0) {
                subOrderCustomCriteria.andIsSpecialEqualTo("0");
            }

            subOrderCustomExample.setOrderByClause("a.id desc");
            /*subOrderCustomCriteria.andPlatformContactsEqualTo(staffID);*/

 			/*if(!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				subOrderCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}*/
            if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {//对接人的商家
                /*subOrderCustomCriteria.andplatContactStaffIdtEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));*/

                MchtPlatformContactCustomExample mchtPlatformContactCustomExample = new MchtPlatformContactCustomExample();
                MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria mchtPlatformContactCustomCriteria = mchtPlatformContactCustomExample.createCriteria();
                mchtPlatformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
                mchtPlatformContactCustomCriteria.andplatContactStaffId(Integer.valueOf(request.getParameter("platContactStaffId")));
                List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactCustomExample);
                List<Integer> mchtIdlist = new ArrayList<Integer>();
                for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
                    mchtIdlist.add(mchtPlatformContactCustom.getMchtId());
                }
                if (mchtIdlist != null && mchtIdlist.size() > 0) {

                    subOrderCustomCriteria.andMchtIdIn(mchtIdlist);
                } else {
                    subOrderCustomCriteria.andMchtIdEqualTo(0);
                }

            }
            /*//审核状态,未付款不能审核
            String status = request.getParameter("status");
            if (!StringUtil.isEmpty(status)) {
                if (!"0".equals(status)&&!"4".equals(status)) {
                    subOrderCustomCriteria.andStatusEqualTo(status);
                } else {
                    subOrderCustomCriteria.andStatusEqualTo("*");//排除所有未付款的,选择未付款应该是不存在的状态值
                }
            } else {
                List<String> list = new ArrayList<>();
                //list.add("0");
                list.add("4");
                subOrderCustomCriteria.andStatusNotIn(list);
            }*/

            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                subOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
            }

            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                String mchtCode = request.getParameter("mchtCode");
                subOrderCustomCriteria.andMchtCodeEqualTo(mchtCode);
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
                String mchtName = request.getParameter("mchtName");
                subOrderCustomCriteria.andMchtNameLikeTo(mchtName);
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMin"))) {
                BigDecimal amountMin = new BigDecimal(request.getParameter("amountMin"));
                subOrderCustomCriteria.andAmountGreaterThanOrEqualTo(amountMin);
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMax"))) {
                BigDecimal amountMax = new BigDecimal(request.getParameter("amountMax"));
                subOrderCustomCriteria.andAmountLessThanOrEqualTo(amountMax);
            }
            if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
                String subOrderCode = request.getParameter("subOrderCode");
                subOrderCustomCriteria.andSubOrderCodeEqualTo(subOrderCode);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("orderCreateDateBegin"))) {
                subOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("orderCreateDateBegin") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderCreateDateEnd"))) {
                subOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("orderCreateDateEnd") + ":59"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderPayDateBegin"))) {
                subOrderCustomCriteria.andOrderPayDateGreaterThanOrEqualTo(request.getParameter("orderPayDateBegin") + ":00");
            }
            if (!StringUtil.isEmpty(request.getParameter("orderPayDateEnd"))) {
                subOrderCustomCriteria.andOrderPayDateLessThanOrEqualTo(request.getParameter("orderPayDateEnd") + ":59");
            }

            if (!StringUtil.isEmpty(request.getParameter("deliveryDateBegin"))) {
                subOrderCustomCriteria.andDeliveryDateGreaterThanOrEqualTo(sdf.parse((request.getParameter("deliveryDateBegin") + ":00")));
            }
            if (!StringUtil.isEmpty(request.getParameter("deliveryDateEnd"))) {
                subOrderCustomCriteria.andDeliveryDateLessThanOrEqualTo(sdf.parse((request.getParameter("deliveryDateEnd") + ":59")));
            }
            if (!StringUtil.isEmpty(request.getParameter("phone"))) {
                subOrderCustomCriteria.andPhoneOrMobileEqualTo(request.getParameter("phone"));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberInfoId"))) {
                subOrderCustomCriteria.andMemberInfoIdEqualTo(request.getParameter("memberInfoId"));
            }
            //订单审核状态
            if (!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
                subOrderCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
            } else {
                subOrderCustomCriteria.andAuditStatusNotEqualTo("1");
            }
            //订单审核日期
            if (!StringUtil.isEmpty(request.getParameter("auditDateBegin"))) {
                subOrderCustomCriteria.andAuditDateGreaterThanOrEqualTo(sdf.parse((request.getParameter("auditDateBegin") + " 00:00:00")));
            }
            if (!StringUtil.isEmpty(request.getParameter("auditDateEnd"))) {
                subOrderCustomCriteria.andAuditDateLessThanOrEqualTo(sdf.parse((request.getParameter("auditDateEnd") + " 23:59:59")));
            }

            if (!StringUtil.isEmpty(request.getParameter("brandName"))) {
                subOrderCustomCriteria.andBrandNameEqualTo(request.getParameter("brandName").trim());
            }
            if (!StringUtil.isEmpty(request.getParameter("artNo"))) {
                subOrderCustomCriteria.andArtNoLikeTo("%" + request.getParameter("artNo").trim() + "%");
            }
            if (!StringUtil.isEmpty(request.getParameter("paymentId"))) {
                subOrderCustomCriteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("paymentId").trim()));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberNick"))) {
                subOrderCustomCriteria.andMemberNickEqualTo(request.getParameter("memberNick").trim());
            }
            if (!StringUtil.isEmpty(request.getParameter("receiverName"))) {
                subOrderCustomCriteria.andReceiverNameLikeTo("%" + request.getParameter("receiverName").trim() + "%");
            }

            //钟表运营部状态，只获取主营类目为钟表
            String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
            if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
                String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
                if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                    subOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
                }
            } else {
                if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
                    subOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
                }
            }

            if (!StringUtil.isEmpty(request.getParameter("wuliu")) && request.getParameter("wuliu").equals("1")) {
                subOrderCustomCriteria.andNotExisitWuliuInfo();
            }
            if (!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
                subOrderCustomCriteria.andMemberStatus(request.getParameter("memberStatus"));
            }
            if (!StringUtil.isEmpty(request.getParameter("promotionType"))) {
                subOrderCustomCriteria.andPromotionTypeTo(request.getParameter("promotionType"));
            }
            //验证图片
            if (!StringUtil.isEmpty(request.getParameter("checkPic"))) {
                if ("1".equals(request.getParameter("checkPic"))) {
                    subOrderCustomCriteria.andPicIsUpload();
                }
                if ("2".equals(request.getParameter("checkPic"))) {
                    subOrderCustomCriteria.andPicIsNotUpload();
                }
            }

            totalCount = subOrderService.countSubOrderCustomByExample(subOrderCustomExample);
            subOrderCustomExample.setLimitStart(page.getLimitStart());
            subOrderCustomExample.setLimitSize(page.getLimitSize());
            //listMap = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
            //if(!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
            //listMap = subOrderService.selectSubOrderByExample(subOrderCustomExample);
            //}else {
            Date date = new Date(new Date().getTime() - 2592000000l);//2592000=30*24*60*60,30天的毫秒值
            subOrderCustomExample.setDate(date);
            listMap = subOrderService.selectSubOrderByExampleAndDate(subOrderCustomExample);
            //}

            resMap.put("Rows", listMap);
            resMap.put("Total", totalCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }


    /**
     * 大学生认证订单审核列表数据导出
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/order/sub/orderReviewExport.shtml")
    public void orderReviewExport(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        try {

			/*//如当本人为招商对接人，只能查看他本人对接的商家的数据。
			//如果本人不是对接人，那么就可以看全部数据。
			//如果本人为售后 、客服的对接人，那么就可以看全部数据。
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId);
			List<PlatformContact> platformContactList = platformContactService.selectByExample(platformContactExample);
			List<SubOrderCustom> subOrderCustoms = null;
			SubOrderCustomExample subOrderCustomExample=new SubOrderCustomExample();
			SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria=subOrderCustomExample.createCriteria();
			subOrderCustomCriteria.andDelFlagEqualTo("0");
			subOrderCustomExample.setOrderByClause("a.id desc");
			if(platformContactList != null && platformContactList.size() > 0
					&& !"4".equals(platformContactList.get(0).getContactType())
					&& !"6".equals(platformContactList.get(0).getContactType()) ) {
				subOrderCustomCriteria.andPlatformContactEqualTo(platformContactList.get(0).getId());
			}*/

            Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
            List<SubOrderCustom> subOrderCustoms = null;
            SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
            SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
            subOrderCustomCriteria.andDelFlagEqualTo("0");

            //角色id为60的不能看到特价订单
            SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
            sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(60).andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
            if (sysStaffRoleService.countByExample(sysStaffRoleExample) > 0) {
                subOrderCustomCriteria.andIsSpecialEqualTo("0");
            }


            subOrderCustomExample.setOrderByClause("a.id desc");
            /*subOrderCustomCriteria.andPlatformContactsEqualTo(staffID);*/

			/*if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				subOrderCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}*/
            if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {//对接人的商家
                /*subOrderCustomCriteria.andplatContactStaffIdtEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));*/
                MchtPlatformContactCustomExample mchtPlatformContactCustomExample = new MchtPlatformContactCustomExample();
                MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria mchtPlatformContactCustomCriteria = mchtPlatformContactCustomExample.createCriteria();
                mchtPlatformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
                mchtPlatformContactCustomCriteria.andplatContactStaffId(Integer.valueOf(request.getParameter("platContactStaffId")));
                List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactCustomExample);
                List<Integer> mchtIdlist = new ArrayList<Integer>();
                for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
                    mchtIdlist.add(mchtPlatformContactCustom.getMchtId());
                }
                if (mchtIdlist != null && mchtIdlist.size() > 0) {

                    subOrderCustomCriteria.andMchtIdIn(mchtIdlist);
                } else {
                    subOrderCustomCriteria.andMchtIdEqualTo(0);
                }

            }
            /*//审核状态,未付款不能审核
            String status = request.getParameter("status");
            if (!StringUtil.isEmpty(status)) {
                if (!"0".equals(status)&&!"4".equals(status)) {
                    subOrderCustomCriteria.andStatusEqualTo(status);
                } else {
                    subOrderCustomCriteria.andStatusEqualTo("*");//排除所有未付款的,选择未付款应该是不存在的状态值
                }
            } else {
                List<String> list = new ArrayList<>();
                //list.add("0");
                list.add("4");
                subOrderCustomCriteria.andStatusNotIn(list);
            }*/

            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                subOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
            }

            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                String mchtCode = request.getParameter("mchtCode");
                subOrderCustomCriteria.andMchtCodeEqualTo(mchtCode);
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
                String mchtName = request.getParameter("mchtName");
                subOrderCustomCriteria.andMchtNameLikeTo("%" + mchtName + "%");
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMin"))) {
                BigDecimal amountMin = new BigDecimal(request.getParameter("amountMin"));
                subOrderCustomCriteria.andAmountGreaterThanOrEqualTo(amountMin);
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMax"))) {
                BigDecimal amountMax = new BigDecimal(request.getParameter("amountMax"));
                subOrderCustomCriteria.andAmountLessThanOrEqualTo(amountMax);
            }
            if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
                String subOrderCode = request.getParameter("subOrderCode");
                subOrderCustomCriteria.andSubOrderCodeEqualTo(subOrderCode);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("orderCreateDateBegin"))) {
                subOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("orderCreateDateBegin") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderCreateDateEnd"))) {
                subOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("orderCreateDateEnd") + ":59"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderPayDateBegin"))) {
                subOrderCustomCriteria.andOrderPayDateGreaterThanOrEqualTo(request.getParameter("orderPayDateBegin") + ":00");
            }
            if (!StringUtil.isEmpty(request.getParameter("orderPayDateEnd"))) {
                subOrderCustomCriteria.andOrderPayDateLessThanOrEqualTo(request.getParameter("orderPayDateEnd") + ":59");
            }
            if (!StringUtil.isEmpty(request.getParameter("deliveryDateBegin"))) {
                subOrderCustomCriteria.andDeliveryDateGreaterThanOrEqualTo(sdf.parse((request.getParameter("deliveryDateBegin") + ":00")));
            }
            if (!StringUtil.isEmpty(request.getParameter("deliveryDateEnd"))) {
                subOrderCustomCriteria.andDeliveryDateLessThanOrEqualTo(sdf.parse((request.getParameter("deliveryDateEnd") + ":59")));
            }
            if (!StringUtil.isEmpty(request.getParameter("phone"))) {
                subOrderCustomCriteria.andPhoneOrMobileEqualTo(request.getParameter("phone"));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberInfoId"))) {
                subOrderCustomCriteria.andMemberInfoIdEqualTo(request.getParameter("memberInfoId"));
            }
            //订单审核状态
            if (!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
                subOrderCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
            } else {
                subOrderCustomCriteria.andAuditStatusNotEqualTo("1");
            }
            //订单审核日期
            if (!StringUtil.isEmpty(request.getParameter("auditDateBegin"))) {
                subOrderCustomCriteria.andAuditDateGreaterThanOrEqualTo(sdf.parse((request.getParameter("auditDateBegin") + " 00:00:00")));
            }
            if (!StringUtil.isEmpty(request.getParameter("auditDateEnd"))) {
                subOrderCustomCriteria.andAuditDateLessThanOrEqualTo(sdf.parse((request.getParameter("auditDateEnd") + " 23:59:59")));
            }
            if (!StringUtil.isEmpty(request.getParameter("brandName"))) {
                subOrderCustomCriteria.andBrandNameEqualTo(request.getParameter("brandName").trim());
            }
            if (!StringUtil.isEmpty(request.getParameter("artNo"))) {
                subOrderCustomCriteria.andArtNoLikeTo("%" + request.getParameter("artNo").trim() + "%");
            }
            if (!StringUtil.isEmpty(request.getParameter("paymentId"))) {
                subOrderCustomCriteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("paymentId").trim()));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberNick"))) {
                subOrderCustomCriteria.andMemberNickEqualTo(request.getParameter("memberNick").trim());
            }
            if (!StringUtil.isEmpty(request.getParameter("receiverName"))) {
                subOrderCustomCriteria.andReceiverNameLikeTo("%" + request.getParameter("receiverName").trim() + "%");
            }
            //钟表运营部状态，只获取主营类目为钟表
            String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
            if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
                String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
                if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                    subOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
                }
            } else {
                if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
                    subOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
                }
            }
            if (!StringUtil.isEmpty(request.getParameter("wuliu")) && request.getParameter("wuliu").equals("1")) {
                subOrderCustomCriteria.andNotExisitWuliuInfo();
            }
            if (!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
                subOrderCustomCriteria.andMemberStatus(request.getParameter("memberStatus"));
            }
            if (!StringUtil.isEmpty(request.getParameter("promotionType"))) {
                subOrderCustomCriteria.andPromotionTypeTo(request.getParameter("promotionType"));
            }
            //验证图片
            if (!StringUtil.isEmpty(request.getParameter("checkPic"))) {
                if ("1".equals(request.getParameter("checkPic"))) {
                    subOrderCustomCriteria.andPicIsUpload();
                }
                if ("2".equals(request.getParameter("checkPic"))) {
                    subOrderCustomCriteria.andPicIsNotUpload();
                }
            }
            /*subOrderCustomExample.setLimitStart(page.getLimitStart());
            subOrderCustomExample.setLimitSize(page.getLimitSize());*/
            subOrderCustoms = subOrderService.selectSubOrderByExample(subOrderCustomExample);
            String[] titles = {"子订单编号", "店铺名称", "下单用户ID", "认证图片", "订单状态", "下单时间", "审核状态", "审核通过时间","收货人联系电话"};
            ExcelBean excelBean = new ExcelBean("导出子订单列表.xls", "导出子订单列表", titles);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String[]> datas = new ArrayList<String[]>();
            for (SubOrderCustom subOrderCustom : subOrderCustoms) {
                String express = "-";
                if (!StringUtil.isEmpty(subOrderCustom.getExpressName())) {
                    express = subOrderCustom.getExpressName();
                }
                if (!StringUtil.isEmpty(subOrderCustom.getExpressNo())) {
                    if (express.equals("-")) {
                        express = subOrderCustom.getExpressNo();
                    } else {
                        express += subOrderCustom.getExpressNo();
                    }
                }
                String desc = "";
                if (!StringUtil.isEmpty(subOrderCustom.getIsManageSelfDesc())) {
                    desc = subOrderCustom.getIsManageSelfDesc();
                }
                if (!StringUtil.isEmpty(subOrderCustom.getMchtTypeDesc())) {
                    desc += subOrderCustom.getMchtTypeDesc();
                }
                if (!StringUtil.isEmpty(subOrderCustom.getProductTypename())) {
                    desc += subOrderCustom.getProductTypename();
                }
                StringBuffer customerServiceOrderStr = new StringBuffer();
                if (!StringUtil.isEmpty(subOrderCustom.getCustomerServiceOrderConcat())) {
                    String[] customerServiceOrders = subOrderCustom.getCustomerServiceOrderConcat().split(",");
                    for (int i = 0; i < customerServiceOrders.length; i++) {
                        String[] customerServiceOrder = customerServiceOrders[i].split("\\|");
                        if (i != 0) {
                            customerServiceOrderStr.append("\n");
                        }
                        customerServiceOrderStr.append("【" + customerServiceOrder[0] + "】");
                        customerServiceOrderStr.append(customerServiceOrder[1]);
                    }
                }
                String hasWuliu = "";
                if (StringUtils.isEmpty(subOrderCustom.getKdnId())) {
                    hasWuliu = "无";
                } else {
                    hasWuliu = "有";
                }
                StringBuffer subOrderCode = new StringBuffer("");
                if (!StringUtil.isEmpty(subOrderCustom.getSubOrderCode())) {
                    subOrderCode.append(subOrderCustom.getSubOrderCode());
                    if ("P".equals(subOrderCustom.getMemberInfoStatus())) {
                        subOrderCode.append("\n异常");
                    }
                }
                String auditStatus = "";
                if ("1".equals(subOrderCustom.getAuditStatus())) {
                    auditStatus = "无需审核";
                }
                if ("2".equals(subOrderCustom.getAuditStatus())) {
                    auditStatus = "待审";
                }
                if ("3".equals(subOrderCustom.getAuditStatus())) {
                    auditStatus = "通过";
                }
                if ("4".equals(subOrderCustom.getAuditStatus())) {
                    auditStatus = "不通过";
                }
                String[] data = {
                        subOrderCode.toString(),
                        //desc,
                        subOrderCustom.getShortName() == null ? "" : subOrderCustom.getShopName(),
                        //subOrderCustom.getProductid()==null?"":subOrderCustom.getProductid().toString(),
                        //subOrderCustom.getArtBrandGroup()==null?"":subOrderCustom.getArtBrandGroup().replaceAll(",", "\n"),
                        //subOrderCustom.getProductQuantity()==null?"":subOrderCustom.getProductQuantity().toString(),
                        //subOrderCustom.getPayAmount()==null?"":subOrderCustom.getPayAmount().toString(),
                        //subOrderCustom.getPaymentName()==null?"":subOrderCustom.getPaymentName(),
                        subOrderCustom.getMemberId() == null ? "" : subOrderCustom.getMemberId().toString(),
//						subOrderCustom.getMemberNick()==null?"":subOrderCustom.getMemberNick(),
                        subOrderCustom.getPic() == null ? "未上传" : "已上传",
                        //subOrderCustom.getReceiverName()==null?"":subOrderCustom.getReceiverName(),
                        //subOrderCustom.getReceiverPhone()==null?"":subOrderCustom.getReceiverPhone(),
                        //subOrderCustom.getReceiverAddress()==null?"":subOrderCustom.getReceiverAddress(),
                        subOrderCustom.getStatusDesc() == null ? "" : subOrderCustom.getStatusDesc(),
                        subOrderCustom.getOrderCreateDate() == null ? "" : df.format(subOrderCustom.getOrderCreateDate()),
                        auditStatus,
                        subOrderCustom.getAuditDate() == null ? "" : df.format(subOrderCustom.getAuditDate()),
                        subOrderCustom.getReceiverPhone() == null ? "" : subOrderCustom.getReceiverPhone()	
                        //subOrderCustom.getOrderPayDate()==null?"":df.format(subOrderCustom.getOrderPayDate()),
                        //subOrderCustom.getDeliveryDate()==null?"":df.format(subOrderCustom.getDeliveryDate()),
                        //express,
                        //hasWuliu,
                        //customerServiceOrderStr.toString(),
                        //subOrderCustom.getRemarks()==null?"":subOrderCustom.getRemarks()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 平台人员添加订单备注
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/sub/editRemark.shtml")
    public String orderSubEditRemark(Model model, HttpServletRequest request) {
        Integer subOrderId = Integer.valueOf(request.getParameter("subOrderId"));
        model.addAttribute("subOrderId", subOrderId);
        String mchtRemark = request.getParameter("mchtRemark");
        if (!StringUtils.isEmpty(mchtRemark)) {
            model.addAttribute("mchtRemark", mchtRemark);
            SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
            model.addAttribute("remarks", subOrder.getRemarks());
            model.addAttribute("remarksColor", subOrder.getRemarksColor());
        }
        return "/order/sub/editRemark";
    }

    @RequestMapping(value = "/order/sub/remarkSubmit.shtml")
    @ResponseBody
    public Map<String, Object> orderSubRemarkSubmit(Model model, HttpServletRequest request, RemarksLog remarksLog) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            String subOrderId = request.getParameter("subOrderId");
            String mchtRemark = request.getParameter("mchtRemark");
            String remarksColor = request.getParameter("remarksColor");
            String remarks = request.getParameter("remarks");
            if (!StringUtils.isEmpty(mchtRemark)) {
                SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
                subOrder.setRemarksColor(remarksColor);
                subOrder.setRemarks(remarks);
                remarksLog.setCreateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
                remarksLog.setCreateDate(new Date());
                subOrderService.updateRemarks(subOrder, remarksLog);

                MchtRemarksLog mchtRemarksLog = new MchtRemarksLog();
                mchtRemarksLog.setDelFlag("0");
                mchtRemarksLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                mchtRemarksLog.setCreateDate(new Date());
                mchtRemarksLog.setSubOrderId(Integer.parseInt(subOrderId));
                mchtRemarksLog.setOperatorId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                mchtRemarksLog.setOperatorType("2");
                mchtRemarksLog.setRemarksColor(remarksColor);
                mchtRemarksLog.setRemarks(remarks);
                mchtRemarksLogService.insertSelective(mchtRemarksLog);
            } else {
                remarksLog.setCreateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
                remarksLog.setCreateDate(new Date());
                remarksLogService.insertSelective(remarksLog);
            }
        } catch (Exception e) {
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 售后列表
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/afterService/list.shtml")
    public ModelAndView orderAfterServiceList(HttpServletRequest request) {
        String rtPage = "/order/afterService/list";
        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("serviceTypeList", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "SERVICE_TYPE"));
        resMap.put("statusList", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "STATUS"));
        resMap.put("proStatusList", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "PRO_STATUS"));


        ExpressExample expressExample = new ExpressExample();
        expressExample.createCriteria().andDelFlagEqualTo("0");
        List<Express> expresss = expressService.selectByExample(expressExample);
        resMap.put("expresssList", expresss);
        if (!StringUtil.isEmpty(request.getParameter("mchtId"))) {
            String mchtId = request.getParameter("mchtId");
            resMap.put("mchtId", mchtId);
        }
        if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
            resMap.put("date_begin", request.getParameter("date_begin"));
        }
        if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
            resMap.put("date_end", request.getParameter("date_end"));
        }
        if (!StringUtil.isEmpty(request.getParameter("serviceType"))) {
            resMap.put("serviceType", request.getParameter("serviceType"));
        }

		/*Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人

		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}

		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);
		resMap.put("platformMchtContacts", platformMchtContact);

		resMap.put("isContact", isContact);*/

        String staffID = this.getSessionStaffBean(request).getStaffID();
        /*Integer isContact = 0;*/ //默认不是招商、运营对接人

        SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
        SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
        sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));

        //钟表运营部状态，只获取主营类目为钟表
        String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
        if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
            String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
            if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                sysstaffProductTypeexCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
            }
        }
        resMap.put("isCwOrgStatus", isCwOrgStatus);
        List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
        resMap.put("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));

		/*PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是招商、运营时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID).andContactTypeBetween("1", "2");
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是招商、运营时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}

		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是招商、运营对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);
		resMap.put("platformMchtContacts", platformMchtContact);*/

        /*resMap.put("isContact", isContact);*/

        //对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
        String isManagement = this.getSessionStaffBean(request).getIsManagement();//管理层
        resMap.put("isManagement", isManagement);
        resMap.put("staffID", staffID);
        SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
        SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
        sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
        List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
        resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateEnd = df.format(new Date());
        String dateBegin = dateEnd.substring(0, 7) + "-01";
        resMap.put("date_end", dateEnd);
        resMap.put("date_begin", dateBegin);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 售后列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/afterService/data.shtml")
    @ResponseBody
    public Map<String, Object> orderAfterServiceData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
        Integer totalCount = 0;
        try {
            HashMap<String,Object> paramMap = new HashMap<String,Object>();
            if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
                paramMap.put("createDateGe", request.getParameter("date_begin") + " 00:00:00");
            }
            if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
                paramMap.put("createDateLe", request.getParameter("date_end") + " 23:59:59");
            }
            if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {//对接人的商家
                paramMap.put("platContactStaffId", Integer.valueOf(request.getParameter("platContactStaffId")));
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtId"))) {
                paramMap.put("mchtId", request.getParameter("mchtId"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
                paramMap.put("mchtNameLike", request.getParameter("mchtName"));
            }
            if (!StringUtil.isEmpty(request.getParameter("activityId"))) {
                paramMap.put("activityId", Integer.valueOf(request.getParameter("activityId")));
            }
            if (!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
                paramMap.put("productBrandName", request.getParameter("productBrandName"));
            }
            if (!StringUtil.isEmpty(request.getParameter("productId"))) {
                paramMap.put("productId", Integer.valueOf(request.getParameter("productId")));
            }
            if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
                paramMap.put("subOrderCode", request.getParameter("subOrderCode"));
            }
            if (!StringUtil.isEmpty(request.getParameter("receiverName"))) {
                paramMap.put("receiverName", request.getParameter("receiverName"));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberExpressNo"))) {
                paramMap.put("memberExpressNo", request.getParameter("memberExpressNo"));
            }
            if (!StringUtil.isEmpty(request.getParameter("contactPhone"))) {
                paramMap.put("contactPhone", request.getParameter("contactPhone"));
            }
            if (!StringUtil.isEmpty(request.getParameter("serviceType"))) {
                paramMap.put("serviceType", request.getParameter("serviceType"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderCode"))) {
                paramMap.put("orderCode", request.getParameter("orderCode"));
            }
            if (!StringUtil.isEmpty(request.getParameter("refund_date_begin"))) {
                paramMap.put("refundDateBeginGe", request.getParameter("refund_date_begin") + " 00:00:00");
            }
            if (!StringUtil.isEmpty(request.getParameter("refund_date_end"))) {
                paramMap.put("refundDateBeginLe", request.getParameter("refund_date_end") + " 23:59:59");
            }
            if (!StringUtil.isEmpty(request.getParameter("log_date_begin"))) {
                paramMap.put("logDateBeginGe", request.getParameter("log_date_begin") + " 00:00:00");
            }
            if (!StringUtil.isEmpty(request.getParameter("log_date_end"))) {
                paramMap.put("logDateEndLe", request.getParameter("log_date_end") + " 23:59:59");
            }
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                paramMap.put("status", request.getParameter("status"));
            }
            if (!StringUtil.isEmpty(request.getParameter("proStatus"))) {
                paramMap.put("proStatus", request.getParameter("proStatus"));
            }
            if (!StringUtil.isEmpty(request.getParameter("waitOperator"))) {
                String waitOperator = request.getParameter("waitOperator");
                List<String> proStatusList = new ArrayList<String>();
                if (waitOperator.equals("1")) {//1.等待商家操作:A1客户已申请（退款）B1客户已申请（退货）B4客户已寄件（退货）C1客户已申请（换货）C4客户已寄件（换货）C5商家同意换货（换货）
                    proStatusList.add("A1");
                    proStatusList.add("B1");
                    proStatusList.add("B4");
                    proStatusList.add("C1");
                    proStatusList.add("C4");
                    proStatusList.add("C5");
                } else if (waitOperator.equals("2")) {//2.等待客户操作:B2商家同意申请（退货）C2商家同意申请（换货）
                    proStatusList.add("B2");
                    proStatusList.add("C2");
                }
                paramMap.put("proStatusList", proStatusList);
            }
            //钟表运营部状态，只获取主营类目为钟表
            String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
            if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
                String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
                if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                    paramMap.put("isCwOrgProductTypeId", Integer.parseInt(isCwOrgProductTypeId));
                }
            } else {
                if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
                    paramMap.put("productTypeIds", request.getParameter("productTypeIds").split(";"));
                }
            }
            if (!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
                paramMap.put("memberStatus", request.getParameter("memberStatus"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                paramMap.put("mchtCode", request.getParameter("mchtCode"));
            }
            if (!StringUtil.isEmpty(request.getParameter("isSvip"))) {
                String isSvip = request.getParameter("isSvip");
                if ("2".equals(isSvip)) {
                    paramMap.put("isSvip", isSvip);
                }

            }
            //查看负责类目
            paramMap.put("platformContactId", staffId);
            paramMap.put("orderBy", "a.id desc");
            paramMap.put("limitStart", page.getLimitStart());
            paramMap.put("limitSize", page.getLimitSize());

            totalCount = customerServiceOrderService.countCustomerOrderList(paramMap);
            List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomerOrderList(paramMap);

            for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
                customerServiceOrderCustom.setSkuPic(FileUtil.getSmallImageName(customerServiceOrderCustom.getSkuPic()));
            }
            resMap.put("Rows", customerServiceOrderCustoms);
            resMap.put("Total", totalCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 导出excel
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/order/afterService/export.shtml")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
            HashMap<String,Object> paramMap = new HashMap<String,Object>();
            if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
                paramMap.put("createDateGe", request.getParameter("date_begin") + " 00:00:00");
            }
            if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
                paramMap.put("createDateLe", request.getParameter("date_end") + " 23:59:59");
            }
            if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {//对接人的商家
                paramMap.put("platContactStaffId", Integer.valueOf(request.getParameter("platContactStaffId")));
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtId"))) {
                paramMap.put("mchtId", request.getParameter("mchtId"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
                paramMap.put("mchtNameLike", request.getParameter("mchtName"));
            }
            if (!StringUtil.isEmpty(request.getParameter("activityId"))) {
                paramMap.put("activityId", Integer.valueOf(request.getParameter("activityId")));
            }
            if (!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
                paramMap.put("productBrandName", request.getParameter("productBrandName"));
            }
            if (!StringUtil.isEmpty(request.getParameter("productId"))) {
                paramMap.put("productId", Integer.valueOf(request.getParameter("productId")));
            }
            if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
                paramMap.put("subOrderCode", request.getParameter("subOrderCode"));
            }
            if (!StringUtil.isEmpty(request.getParameter("receiverName"))) {
                paramMap.put("receiverName", request.getParameter("receiverName"));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberExpressNo"))) {
                paramMap.put("memberExpressNo", request.getParameter("memberExpressNo"));
            }
            if (!StringUtil.isEmpty(request.getParameter("contactPhone"))) {
                paramMap.put("contactPhone", request.getParameter("contactPhone"));
            }
            if (!StringUtil.isEmpty(request.getParameter("serviceType"))) {
                paramMap.put("serviceType", request.getParameter("serviceType"));
            }
            if (!StringUtil.isEmpty(request.getParameter("orderCode"))) {
                paramMap.put("orderCode", request.getParameter("orderCode"));
            }
            if (!StringUtil.isEmpty(request.getParameter("refund_date_begin"))) {
                paramMap.put("refundDateBeginGe", request.getParameter("refund_date_begin") + " 00:00:00");
            }
            if (!StringUtil.isEmpty(request.getParameter("refund_date_end"))) {
                paramMap.put("refundDateBeginLe", request.getParameter("refund_date_end") + " 23:59:59");
            }
            if (!StringUtil.isEmpty(request.getParameter("log_date_begin"))) {
                paramMap.put("logDateBeginGe", request.getParameter("log_date_begin") + " 00:00:00");
            }
            if (!StringUtil.isEmpty(request.getParameter("log_date_end"))) {
                paramMap.put("logDateEndLe", request.getParameter("log_date_end") + " 23:59:59");
            }
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                paramMap.put("status", request.getParameter("status"));
            }
            if (!StringUtil.isEmpty(request.getParameter("proStatus"))) {
                paramMap.put("proStatus", request.getParameter("proStatus"));
            }
            if (!StringUtil.isEmpty(request.getParameter("waitOperator"))) {
                String waitOperator = request.getParameter("waitOperator");
                List<String> proStatusList = new ArrayList<String>();
                if (waitOperator.equals("1")) {//1.等待商家操作:A1客户已申请（退款）B1客户已申请（退货）B4客户已寄件（退货）C1客户已申请（换货）C4客户已寄件（换货）C5商家同意换货（换货）
                    proStatusList.add("A1");
                    proStatusList.add("B1");
                    proStatusList.add("B4");
                    proStatusList.add("C1");
                    proStatusList.add("C4");
                    proStatusList.add("C5");
                } else if (waitOperator.equals("2")) {//2.等待客户操作:B2商家同意申请（退货）C2商家同意申请（换货）
                    proStatusList.add("B2");
                    proStatusList.add("C2");
                }
                paramMap.put("proStatusList", proStatusList);
            }
            //钟表运营部状态，只获取主营类目为钟表
            String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
            if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
                String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
                if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                    paramMap.put("isCwOrgProductTypeId", Integer.parseInt(isCwOrgProductTypeId));
                }
            } else {
                if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
                    paramMap.put("productTypeIds", request.getParameter("productTypeIds").split(";"));
                }
            }
            if (!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
                paramMap.put("memberStatus", request.getParameter("memberStatus"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                paramMap.put("mchtCode", request.getParameter("mchtCode"));
            }
            if (!StringUtil.isEmpty(request.getParameter("isSvip"))) {
                String isSvip = request.getParameter("isSvip");
                if ("2".equals(isSvip)) {
                    paramMap.put("isSvip", isSvip);
                }

            }
            //查看负责类目
            paramMap.put("platformContactId", staffId);
            paramMap.put("orderBy", "a.id desc");

            List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomerOrderList(paramMap);

            String[] titles = {"售后编号", "申请时间", "子订单编号", "商品名称", "规格", "商品ID", "商家简称", "购买数量", "售后数量", "售后类型", "售后状态", "实收金额", "申请金额/换货", "退款日期", "商家序号", "公司名称", "操作记录"};
            ExcelBean excelBean = new ExcelBean("导出售后列表.xls",
                    "导出售后列表", titles);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String[]> datas = new ArrayList<String[]>();
            for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
                String[] data = {
                        customerServiceOrderCustom.getOrderCode(),
                        df.format(customerServiceOrderCustom.getCreateDate()),
                        customerServiceOrderCustom.getSubOrderCode(),
                        customerServiceOrderCustom.getProductName() == null ? "" : customerServiceOrderCustom.getProductName(),
                        customerServiceOrderCustom.getProductPropDesc() == null ? "" : customerServiceOrderCustom.getProductPropDesc(),
                        customerServiceOrderCustom.getProductId() == null ? "" : customerServiceOrderCustom.getProductId().toString(),
                        customerServiceOrderCustom.getShortName(),
                        customerServiceOrderCustom.getProductQuantity() == null ? "" : customerServiceOrderCustom.getProductQuantity().toString(),
                        customerServiceOrderCustom.getQuantity() == null ? "" : customerServiceOrderCustom.getQuantity().toString(),
                        customerServiceOrderCustom.getServiceTypeDesc(),
                        customerServiceOrderCustom.getStatusDesc(),
                        customerServiceOrderCustom.getPayAmount() == null ? "" : customerServiceOrderCustom.getPayAmount().toString(),
                        customerServiceOrderCustom.getAmount() == null ? "" : customerServiceOrderCustom.getAmount().toString(),
                        customerServiceOrderCustom.getRefundDate() == null ? "" : df.format(customerServiceOrderCustom.getRefundDate()),
                        customerServiceOrderCustom.getMchtCode(),
                        customerServiceOrderCustom.getCompanyName(),
                        customerServiceOrderCustom.getLogContent() == null ? "" : StringUtils.removeHtmlTag(customerServiceOrderCustom.getLogContent())
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/order/afterService/getProStatusList.shtml")
    @ResponseBody
    public List<SysStatus> getProStatusList(HttpServletRequest request) {
        String serviceType = request.getParameter("serviceType");

        SysStatusExample sysStatusExample = new SysStatusExample();
        SysStatusExample.Criteria sysStatusCriteria = sysStatusExample.createCriteria();
        if (!StringUtil.isEmpty(serviceType)) {
            sysStatusCriteria.andTableNameEqualTo("BU_CUSTOMER_SERVICE_ORDER");
            sysStatusCriteria.andColNameEqualTo("PRO_STATUS");
            sysStatusCriteria.andStatusValueLike(serviceType + "%");
            sysStatusExample.setOrderByClause("status_order");
        }
        List<SysStatus> sysStatus = sysStatusService.selectByExample(sysStatusExample);
        return sysStatus;
    }

    /**
     * 售后详情
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/afterService/detail.shtml")
    public ModelAndView orderAfterServiceDetail(HttpServletRequest request) {
        String rtPage = "/order/afterService/detail";
        Map<String, Object> resMap = new HashMap<String, Object>();

        int id = Integer.valueOf(request.getParameter("id"));
        CustomerServiceOrderCustom customerServiceOrderCustom = customerServiceOrderService.selectCustomerServiceOrderCustomByPrimaryKey(id);
        resMap.put("customerServiceOrderCustom", customerServiceOrderCustom);

        String serviceType = customerServiceOrderCustom.getServiceType();
        if ("B".equals(serviceType) || "C".equals(serviceType)) {
            CustomerServicePicExample customerServicePicExample = new CustomerServicePicExample();
            customerServicePicExample.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdEqualTo(id);
            List<CustomerServicePic> customerServicePics = customerServicePicService.selectByExample(customerServicePicExample);
            resMap.put("customerServicePics", customerServicePics);
        }

        CustomerServiceLogExample customerServiceLogExample = new CustomerServiceLogExample();
        customerServiceLogExample.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdEqualTo(id);
        List<CustomerServiceLogCustom> customerServiceLogCustoms = customerServiceLogService.selectCustomerServiceLogCustomByExample(customerServiceLogExample);

        for (CustomerServiceLogCustom customerServiceLogCustom : customerServiceLogCustoms) {
            ServiceLogPicExample serviceLogPicExample = new ServiceLogPicExample();
            serviceLogPicExample.createCriteria().andDelFlagEqualTo("0").andServiceLogIdEqualTo(customerServiceLogCustom.getId());
            List<ServiceLogPic> serviceLogPics = serviceLogPicService.selectByExample(serviceLogPicExample);
            customerServiceLogCustom.setServiceLogPics(serviceLogPics);
        }

        WoRkExample woRkExample = new WoRkExample();
        woRkExample.createCriteria().andDelFlagEqualTo("0").andRelevantIdEqualTo(id);
        List<WoRk> woRks = woRkService.selectByExample(woRkExample);
        if (woRks.size() <= 0) {
            resMap.put("woRks", true);
        }

        resMap.put("customerServiceLogCustoms", customerServiceLogCustoms);

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 查看物流动态
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/afterService/viewWuliu.shtml")
    public ModelAndView viewWuliu(HttpServletRequest request) {
        String rtPage = "/order/afterService/viewWuliu";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String subOrderId = request.getParameter("subOrderId");
        SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
        if (!StringUtil.isEmpty(subOrder.getExpressId()) && !StringUtil.isEmpty(subOrder.getExpressNo())) {
            KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
            KdnWuliuInfoExample.Criteria kdnWuliuInfoCriteria = kdnWuliuInfoExample.createCriteria();
            kdnWuliuInfoCriteria.andDelFlagEqualTo("0");
            kdnWuliuInfoCriteria.andExpressIdEqualTo(Integer.parseInt(subOrder.getExpressId()));
            kdnWuliuInfoCriteria.andLogisticCodeEqualTo(subOrder.getExpressNo());
            List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
            if (kdnWuliuInfos != null && kdnWuliuInfos.size() > 0) {
                if (!StringUtil.isEmpty(kdnWuliuInfos.get(0).getTractInfo())) {
                    JSONArray wuliuInfos = JSONArray.fromObject(kdnWuliuInfos.get(0).getTractInfo());
                    resMap.put("hasWuliu", true);
                    resMap.put("wuliuInfos", wuliuInfos);
                } else {
                    resMap.put("hasWuliu", false);
                }
            } else {
                resMap.put("hasWuliu", false);
            }
        } else {
            resMap.put("hasWuliu", false);
        }
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 发起投诉
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/complain/add.shtml")
    public String orderComplainAdd(Model model, HttpServletRequest request) {
        String rtPage = "/order/complain/add";// 重定向

        int orderDtlId = Integer.valueOf(request.getParameter("orderDtlId"));
        OrderDtlCustom orderDtlCustom = orderDtlService.selectOrderDtlCustomByPrimaryKey(orderDtlId);
        model.addAttribute("orderDtlCustom", orderDtlCustom);

        SysStatusExample sysStatusExample = new SysStatusExample();
        sysStatusExample.createCriteria().andTableNameEqualTo("BU_APPEAL_ORDER").andColNameEqualTo("APPEAL_TYPE").andStatusValueLike("B%");
        sysStatusExample.setOrderByClause("status_order");
        List<SysStatus> sysStatusLst = sysStatusService.selectByExample(sysStatusExample);
        model.addAttribute("sysStatusLst", sysStatusLst);

        return rtPage;
    }

    /**
     * 投诉单保存
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/complain/addSubmit.shtml")
    @ResponseBody
    public Map<String, Object> orderComplainAddSubmit(HttpServletRequest request, AppealOrder appealOrder) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");

        int staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
        String staffName = this.getSessionStaffBean(request).getStaffName();

        try {
            appealOrder.setOrderCode("TEST001");
            appealOrder.setUserType("2");
            appealOrder.setUserId(staffId);
            appealOrder.setUserName(staffName);
            appealOrder.setStatus("2");
            appealOrder.setServiceStatus("1");
            appealOrder.setPlatformProcessor(staffId);
            appealOrder.setCreateBy(staffId);
            appealOrder.setCreateDate(new Date());
            appealOrderService.insertSelective(appealOrder);

            AppealLog appealLog = new AppealLog();
            appealLog.setAppealOrderId(appealOrder.getId());
            appealLog.setUserType("2");
            appealLog.setUserId(staffId);
            appealLog.setUserName(staffName);
            appealLog.setContent(request.getParameter("content"));
            appealLog.setCreateBy(staffId);
            appealLog.setCreateDate(new Date());
            appealLogService.insertSelective(appealLog);
        } catch (Exception e) {
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
            e.printStackTrace();
        }

        return resMap;
    }

    /**
     * @Title addRefundOrderManager
     * @Description TODO(退款申请)
     * @author pengl
     * @date 2018年1月3日 上午11:18:31
     */
    @RequestMapping(value = "/order/complain/addRefundOrderManager.shtml")
    public ModelAndView addRefundOrderManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/order/sub/addRefundOrder");
        m.addObject("subOrderStatus", request.getParameter("subOrderStatus")); // 1 待发货	   2 待收货
        m.addObject("orderDtlId", request.getParameter("orderDtlId")); // 订单明细ID
        m.addObject("payAmount", request.getParameter("payAmount")); // 实付金额
        m.addObject("reasonList", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "REASON")); // 售后原因
        m.addObject("serviceTypeList", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "SERVICE_TYPE")); // A 退款单	B 退货单 	 C 换货单
        return m;
    }

    /**
     * @Title addRefundOrder
     * @Description TODO(保存退款申请)
     * @author pengl
     * @date 2018年1月3日 下午6:28:41
     */
    @ResponseBody
    @RequestMapping(value = "/order/complain/addRefundOrder.shtml")
    public Map<String, Object> addRefundOrder(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String returnCode = "0000";
        String returnMsg = "操作成功！";
        try {
            String orderDtlId = request.getParameter("orderDtlId");
            if (!StringUtil.isEmpty(orderDtlId)) {
                CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
                CustomerServiceOrderExample.Criteria customerServiceOrderCriteria = customerServiceOrderExample.createCriteria();
                customerServiceOrderCriteria.andDelFlagEqualTo("0").andOrderDtlIdEqualTo(Integer.parseInt(orderDtlId));
                List<CustomerServiceOrder> customerServiceOrderList = customerServiceOrderService.selectByExample(customerServiceOrderExample);
                if (customerServiceOrderList != null && customerServiceOrderList.size() > 0) {
                    returnCode = "9999";
                    returnMsg = "售后单已存在，不能发起售后！";
                } else {
                    customerServiceOrderService.saveCustomerServiceOrder(request);
                }
            }
        } catch (Exception e) {
            returnCode = "9999";
            returnMsg = "系统错误！";
            e.printStackTrace();
        }
        resMap.put("returnCode", returnCode);
        resMap.put("returnMsg", returnMsg);
        return resMap;
    }

    /**
     * @Title updateCustomerServiceOrderManager
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年1月4日 下午5:40:44
     */
    @RequestMapping(value = "/order/complain/updateCustomerServiceOrderManager.shtml")
    public ModelAndView updateCustomerServiceOrderManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/order/afterService/updateCustomerServiceOrder");
        m.addObject("customerServiceOrderId", request.getParameter("customerServiceOrderId"));
        return m;
    }

    /**
     * @Title updateCustomerServiceOrder
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年1月4日 下午5:48:12
     */
    @ResponseBody
    @RequestMapping(value = "/order/complain/updateCustomerServiceOrder.shtml")
    public Map<String, Object> updateCustomerServiceOrder(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String returnCode = "0000";
        String returnMsg = "操作成功！";
        try {
            customerServiceOrderService.updateCustomerServiceOrder(request);
        } catch (Exception e) {
            returnCode = "9999";
            returnMsg = "系统错误！";
            e.printStackTrace();
        }
        resMap.put("returnCode", returnCode);
        resMap.put("returnMsg", returnMsg);
        return resMap;
    }

    /**
     * @Title addCustomerServiceOrder
     * @Description TODO(退款申请)
     * @author yjc
     * @date 2018年1月3日 上午11:18:31
     */
    @RequestMapping(value = "/order/afterService/addCustomerServiceOrder.shtml")
    public ModelAndView addCustomerServiceOrder(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/order/sub/addCustomerServiceOrder");
        m.addObject("subOrderStatus", request.getParameter("subOrderStatus")); // 1 待发货	2 待收货
        m.addObject("customerServiceOrderId", request.getParameter("customerServiceOrderId"));
        m.addObject("payAmount", request.getParameter("payAmount")); // 实付金额
        m.addObject("reasonList", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "REASON")); // 售后原因
        m.addObject("serviceTypeList", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "SERVICE_TYPE")); // A 退款单	B 退货单 	 C 换货单
        return m;
    }

    /**
     * @Title saveCustomerServiceOrder
     * @Description TODO(保存售后申请)
     * @author yjc
     * @date 2018年1月3日 下午6:28:41
     */
    @ResponseBody
    @RequestMapping(value = "/order/afterService/saveCustomerServiceOrder.shtml")
    public Map<String, Object> saveCustomerServiceOrder(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String returnCode = "0000";
        String returnMsg = "操作成功！";
        try {
            customerServiceOrderService.againUpdateCustomerServiceOrder(request);
        } catch (Exception e) {
            returnCode = "9999";
            returnMsg = "系统错误！";
            e.printStackTrace();
        }
        resMap.put("returnCode", returnCode);
        resMap.put("returnMsg", returnMsg);
        return resMap;
    }

    /**
     * @Title addOrUpdateOrderAbnormal
     * @Description TODO(标记异常订单)
     * @author pengl
     * @date 2018年3月21日 上午10:41:30
     */
    @RequestMapping(value = "/order/afterService/addOrUpdateOrderAbnormalManager.shtml")
    public ModelAndView addOrUpdateOrderAbnormalManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/order/sub/addOrUpdateOrderAbnormal");
        Integer subOrderId = Integer.parseInt(request.getParameter("subOrderId"));
        SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
        OrderAbnormalLogExample orderAbnormalLogExample = new OrderAbnormalLogExample();
        OrderAbnormalLogExample.Criteria orderAbnormalLogCriteria = orderAbnormalLogExample.createCriteria();
        orderAbnormalLogCriteria.andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderId);
        List<OrderAbnormalLog> orderAbnormalLogList = orderAbnormalLogService.selectByExample(orderAbnormalLogExample);
        m.addObject("subOrderId", subOrderId);
        m.addObject("subOrder", subOrder);
        m.addObject("orderAbnormalLogList", orderAbnormalLogList);
        return m;
    }

    /**
     * @Title addOrUpdateOrderAbnormal
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年3月21日 下午3:11:42
     */
    @ResponseBody
    @RequestMapping("/order/afterService/addOrUpdateOrderAbnormal.shtml")
    public Map<String, Object> addOrUpdateOrderAbnormal(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String returnCode = "200";
        String returnMsg = "操作成功！";
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            orderAbnormalLogService.addOrUpdateOrderAbnormal(request, staffBean);
        } catch (Exception e) {
            returnCode = "9999";
            returnMsg = "系统错误！";
            e.printStackTrace();
        }
        resMap.put("returnCode", returnCode);
        resMap.put("returnMsg", returnMsg);
        return resMap;
    }

    /**
     * @Title updateOrderAbnormalLogManager
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年3月21日 下午6:19:26
     */
    @RequestMapping("/order/sub/updateOrderAbnormalLogManager.shtml")
    public ModelAndView updateOrderAbnormalLogManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/order/sub/updateOrderAbnormalLog");
        m.addObject("subOrderId", request.getParameter("subOrderId"));
        m.addObject("orderAbnormalLogId", request.getParameter("orderAbnormalLogId"));
        m.addObject("followStatusList", DataDicUtil.getStatusList("BU_ORDER_ABNORMAL_LOG", "FOLLOW_STATUS"));
        return m;
    }

    /**
     * @Title updateOrderAbnormalLog
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年3月21日 下午6:19:29
     */
    @ResponseBody
    @RequestMapping("/order/sub/updateOrderAbnormalLog.shtml")
    public Map<String, Object> updateOrderAbnormalLog(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "200");
        resMap.put("returnMsg", "成功");
        try {
            Date date = new Date();
            String subOrderId = request.getParameter("subOrderId");
            String orderAbnormalLogId = request.getParameter("orderAbnormalLogId");
            String followStatus = request.getParameter("followStatus");
            String remarksColor = request.getParameter("remarksColor");
            String remarks = request.getParameter("remarks");
            String staffID = this.getSessionStaffBean(request).getStaffID();
            SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
            if (subOrder.getFollowBy() == null) {
                subOrder.setFollowBy(Integer.parseInt(staffID));
            }
            //子订单异常记录
            OrderAbnormalLog orderAbnormalLog = new OrderAbnormalLog();
            orderAbnormalLog.setId(Integer.parseInt(orderAbnormalLogId));
            orderAbnormalLog.setFollowBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            orderAbnormalLog.setFollowStatus(followStatus);
            orderAbnormalLog.setRemarks(remarks);
            orderAbnormalLog.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            orderAbnormalLog.setUpdateDate(date);
            //子订单备注
            RemarksLog remarksLog = new RemarksLog();
            remarksLog.setCreateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
            remarksLog.setCreateDate(date);
            remarksLog.setDelFlag("0");
            remarksLog.setSubOrderId(Integer.parseInt(subOrderId));
            remarksLog.setRemarksColor(remarksColor);
            remarksLog.setRemarks(remarks);
            orderAbnormalLogService.updateRemarks(subOrder, orderAbnormalLog, remarksLog);
        } catch (Exception e) {
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * @Title addShamFollowBy
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年5月16日 下午5:17:47
     */
    @ResponseBody
    @RequestMapping("/order/sub/addShamFollowBy.shtml")
    public Map<String, Object> addShamFollowBy(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "200");
        map.put("msg", "操作成功！");
        try {
            String subOrderId = request.getParameter("id");
            if (!StringUtil.isEmpty(subOrderId)) {
                String staffID = this.getSessionStaffBean(request).getStaffID();
                String staffName = this.getSessionStaffBean(request).getStaffName();
                SubOrder subOrder = new SubOrder();
                subOrder.setId(Integer.parseInt(subOrderId));
                subOrder.setFollowBy(Integer.parseInt(staffID));
                subOrderService.updateByPrimaryKeySelective(subOrder);
                map.put("followByName", staffName);
            }
        } catch (Exception e) {
            map.put("code", "9999");
            map.put("msg", "操作失败！");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/order/sub/saveSubOrderAttachment")
    @ResponseBody
    public Map<String, Object> saveSubOrderAttachment(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "200");
        map.put("msg", "操作成功！");
        String id = request.getParameter("id");
        String filePath = request.getParameter("filePath");
        String fileName = request.getParameter("fileName");
        SubOrderAttachment subOrderAttachment = new SubOrderAttachment();
        subOrderAttachment.setDelFlag("0");
        subOrderAttachment.setCreateDate(new Date());
        subOrderAttachment.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
        subOrderAttachment.setSubOrderId(Integer.parseInt(id));
        subOrderAttachment.setName(fileName);
        subOrderAttachment.setAttachmentPath(filePath);
        subOrderAttachment.setCreatorType("1");//1.平台
        subOrderAttachmentService.insertSelective(subOrderAttachment);
        return map;
    }

    @RequestMapping("/order/sub/checkFileExists")
    @ResponseBody
    public Map<String, Object> checkFileExists(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "200");
        map.put("msg", "操作成功！");
        String subOrderAttachmentId = request.getParameter("subOrderAttachmentId");
        SubOrderAttachment subOrderAttachment = subOrderAttachmentService.selectByPrimaryKey(Integer.parseInt(subOrderAttachmentId));
        if (subOrderAttachment != null) {
            InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
            String defaultPath = null;
            try {
                Properties properties = new Properties();
                properties.load(stream);
                defaultPath = properties.getProperty("annex.filePath");
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (defaultPath == null) {
                map.put("code", "4004");
                map.put("msg", "文件目录不存在");
                return map;
            }
            File file = new File(defaultPath + subOrderAttachment.getAttachmentPath());
            //如果文件不存在
            if (!file.exists()) {
                map.put("code", "4004");
                map.put("msg", "附件不存在或已被删除");
                return map;
            }
        } else {
            map.put("code", "4004");
            map.put("msg", "附件不存在或已被删除");
            return map;
        }
        return map;
    }

    @RequestMapping("/order/sub/downLoadSubOrderAttachment")
    public void downLoadSubOrderAttachment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath");
        String fileName = request.getParameter("fileName");
        InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
        String defaultPath = null;
        try {
            Properties properties = new Properties();
            properties.load(stream);
            defaultPath = properties.getProperty("annex.filePath");
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (defaultPath == null) {
            return;
        }
        File file = new File(defaultPath + filePath);
        //如果文件不存在
        if (!file.exists()) {
            return;
        }
        //设置响应头，控制浏览器下载该文件
        String downloadFileName = "";
        String agent = request.getHeader("USER-AGENT");
        if (agent != null && agent.toLowerCase().indexOf("firefox") > 0) {
            downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
        } else {
            downloadFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        }
        response.setHeader("content-disposition", "attachment;filename=" + downloadFileName);
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(defaultPath + filePath);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //缓存区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区中
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        //关闭
        in.close();
        out.flush();
        out.close();
    }

    /**
     * @Title showDetail
     * @Description TODO(快照详情)
     * @author pengl
     * @date 2018年9月12日 下午4:28:02
     */
    @RequestMapping("/order/combine/showDetail.shtml")
    public ModelAndView showDetail(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/order/sub/showDetail");
        if (!StringUtil.isEmpty(request.getParameter("orderDtlId"))) {
            Integer orderDtlId = Integer.parseInt(request.getParameter("orderDtlId"));
            OrderProductSnapshotCustomExample orderProductSnapshotCustomExample = new OrderProductSnapshotCustomExample();
            orderProductSnapshotCustomExample.createCriteria().andDelFlagEqualTo("0").andOrderDtlIdEqualTo(orderDtlId);
            orderProductSnapshotCustomExample.setLimitSize(1);
            List<OrderProductSnapshotCustom> orderProductSnapshotCustomList = orderProductSnapshotService.selectByCustomExampleWithBLOBs(orderProductSnapshotCustomExample);
            if (orderProductSnapshotCustomList != null && orderProductSnapshotCustomList.size() > 0) {
                OrderProductSnapshotCustom orderProductSnapshotCustom = orderProductSnapshotCustomList.get(0);
                m.addObject("orderProductSnapshotCustom", orderProductSnapshotCustom);
                if (!StringUtil.isEmpty(orderProductSnapshotCustom.getMainPicGroup())) {
                    List<Integer> mainPicGroupIdList = new ArrayList<Integer>();
                    String[] mainPicGroupIds = orderProductSnapshotCustom.getMainPicGroup().split(",");
                    for (String mainPicGroupId : mainPicGroupIds) {
                        mainPicGroupIdList.add(Integer.parseInt(mainPicGroupId));
                    }
                    ProductPicExample productPicExample = new ProductPicExample();
                    productPicExample.createCriteria().andDelFlagEqualTo("0").andIdIn(mainPicGroupIdList);
                    List<ProductPic> productPicList = productPicService.selectByExample(productPicExample);
                    m.addObject("productPicList", productPicList);
                }
                if (!StringUtil.isEmpty(orderProductSnapshotCustom.getDescPicGroup())) {
                    List<Integer> descPicGroupIdList = new ArrayList<Integer>();
                    String[] descPicGroupIds = orderProductSnapshotCustom.getDescPicGroup().split(",");
                    for (String descPicGroupId : descPicGroupIds) {
                        descPicGroupIdList.add(Integer.parseInt(descPicGroupId));
                    }
                    ProductDescPicExample productDescPicExample = new ProductDescPicExample();
                    productDescPicExample.createCriteria().andDelFlagEqualTo("0").andIdIn(descPicGroupIdList);
                    List<ProductDescPic> productDescPicList = productDescPicService.selectByExample(productDescPicExample);
                    m.addObject("productDescPicList", productDescPicList);
                }
                if (!StringUtil.isEmpty(orderProductSnapshotCustom.getFreightDesc())) {
                    List<String> freightDescList = new ArrayList<String>();
                    String[] freightDescs = orderProductSnapshotCustom.getFreightDesc().split(",");
                    for (String freightDesc : freightDescs) {
                        freightDescList.add(freightDesc);
                    }
                    m.addObject("freightDescList", freightDescList);
                }
                if (!StringUtil.isEmpty(orderProductSnapshotCustom.getProductDesc())) {
                    List<String> productDescList = new ArrayList<String>();
                    String[] productDescs = orderProductSnapshotCustom.getProductDesc().split("&&&");
                    for (String productDesc : productDescs) {
                        productDescList.add(productDesc);
                    }
                    m.addObject("productDescList", productDescList);
                }
            }
            OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(orderDtlId);
            SubOrder subOrder = subOrderService.selectByPrimaryKey(orderDtl.getSubOrderId());
            m.addObject("orderDtl", orderDtl);
            m.addObject("subOrder", subOrder);
            m.addObject("skuPic", orderDtl.getSkuPic());
            Integer shoppingCartId = platformPvStatisticalService.getShoppingCartId(orderDtl.getId());
            String columnTypeDesc = "";
            if (shoppingCartId != null) {
                String columnType = platformPvStatisticalService.getColumnType(shoppingCartId);
                if (!StringUtil.isEmpty(columnType)) {
                    columnTypeDesc = DataDicUtil.getStatusDesc("BU_MEMBER_PV", "COLUMN_TYPE", columnType);
                }
            }
            m.addObject("columnTypeDesc", columnTypeDesc);
        }
        return m;
    }


    //创建子订单工单
    @RequestMapping(value = "/order/sub/addOrderWork.shtml")
    public ModelAndView addWork(HttpServletRequest request) {
        String rtPage = "/order/sub/addOrderWork";
        Map<String, Object> resMap = new HashMap<String, Object>();

        int subOrderId = Integer.valueOf(request.getParameter("id"));
        SubOrderCustom subOrderCustom = subOrderService.selectSubOrderCustomByPrimaryKey(subOrderId);
        resMap.put("subOrderCustom", subOrderCustom);

        SysOrganizationExample sysOrganizationExample = new SysOrganizationExample();
        sysOrganizationExample.createCriteria().andStatusEqualTo("A");
        List<SysOrganization> sysOrganizationlist = sysOrganizationService.selectByExample(sysOrganizationExample);
        resMap.put("sysOrganizationlist", sysOrganizationlist);
        return new ModelAndView(rtPage, resMap);
    }


    //添加子订单工单
    @ResponseBody
    @RequestMapping("/order/sub/addOrderWorklist.shtml")
    public ModelAndView addOrderWorklist(HttpServletRequest request, WoRk work, String attachmentName, String attachmentPath, String id) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String code = "";
        String msg = "";
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            Integer staffId = Integer.valueOf(staffBean.getStaffID());
            Date date = new Date();

            work.setStatus("0");
            work.setStatusBehavior("1");
            work.setDelFlag("0");
            work.setRelevantId(Integer.valueOf(id));
            work.setCreateBy(staffId);
            work.setCreateDate(date);

            if (work.getRelevantType().equals("1")) {
                InterventionOrderCustomExample interventionOrderCustomExample = new InterventionOrderCustomExample();
                interventionOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andInterventionCodeEqualTo(work.getRelevantCode());
                List<InterventionOrderCustom> interventionOrderCustom = interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
                work.setRelevantId(interventionOrderCustom.get(0).getId());

            }
            if (work.getRelevantType().equals("2")) {
                AppealOrderExample appealOrderExample = new AppealOrderExample();
                appealOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
                List<AppealOrder> appealOrder = appealOrderService.selectByExample(appealOrderExample);
                work.setRelevantId(appealOrder.get(0).getId());
            }
            if (work.getRelevantType().equals("4")) {
                SubOrderExample subOrderExample = new SubOrderExample();
                subOrderExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeEqualTo(work.getRelevantCode());
                List<SubOrder> subOrder = subOrderService.selectByExample(subOrderExample);
                work.setRelevantId(subOrder.get(0).getId());
            }
            if (work.getRelevantType().equals("5")) {
                CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
                customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
                List<CustomerServiceOrder> customerServiceOrder = customerServiceOrderService.selectByExample(customerServiceOrderExample);
                work.setRelevantId(customerServiceOrder.get(0).getId());
            }

            woRkService.insertSelective(work);

            Attachment attachment = new Attachment();
            attachment.setWorkId(work.getId());
            attachment.setAttachmentName(attachmentName);
            attachment.setAttachmentPath(attachmentPath);
            attachment.setDelFlag("0");
            attachment.setCreateBy(staffId);
            attachment.setCreateDate(date);
            attachmentService.insertSelective(attachment);


            WorkHistory workHistory = new WorkHistory();
            workHistory.setWorkId(work.getId());
            workHistory.setOrgId(work.getOrgId());
            workHistory.setStaffId(work.getStaffId());
            workHistory.setWorkType(work.getWorkType());
            workHistory.setStatus(work.getStatus());
            workHistory.setStatusBehavior(work.getStatusBehavior());
            workHistory.setUrgentDegree(work.getUrgentDegree());
            workHistory.setCloseReason(work.getCloseReason());
            workHistory.setTitleContent(work.getTitleContent());
            workHistory.setRelevantType(work.getRelevantType());
            workHistory.setRelevantCode(work.getRelevantCode());
            workHistory.setRelevantId(work.getRelevantId());
            workHistory.setDescribeContent(work.getDescribeContent());
            workHistory.setCreateBy(work.getCreateBy());
            workHistory.setCreateDate(work.getCreateDate());
            workHistory.setDelFlag("0");
            woRkHistoryService.insertSelective(workHistory);

            SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(staffId);
            SysStaffInfo sysStaffInfos = sysStaffInfoService.selectByPrimaryKey(work.getStaffId());

            WorkRecord workRecord = new WorkRecord();
            workRecord.setWorkHistoryId(workHistory.getId());
            workRecord.setWorkId(work.getId());
            workRecord.setOrgId(work.getOrgId());
            workRecord.setStaffId(work.getStaffId());
            workRecord.setRecordStatus("1");
            workRecord.setRecordTitle("由" + sysStaffInfo.getStaffName() + "创建工单并指派给" + sysStaffInfos.getStaffName());
            workRecord.setCreateBy(staffId);
            workRecord.setCreateDate(date);
            workRecord.setDelFlag("0");
            workRecordService.insertSelective(workRecord);


            AttachmentHistory attachmentHistory = new AttachmentHistory();
            attachmentHistory.setWorkHistoryId(workHistory.getId());
            attachmentHistory.setAttachmentName(attachmentName);
            attachmentHistory.setAttachmentPath(attachmentPath);
            attachmentHistory.setCreateBy(staffId);
            attachmentHistory.setCreateDate(date);
            attachmentHistory.setDelFlag("0");
            attachmentHistoryService.insertSelective(attachmentHistory);

            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        } catch (Exception e) {
            e.printStackTrace();
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);
        return new ModelAndView(rtPage, resMap);
    }

    //创建售后工单
    @RequestMapping(value = "/order/afterService/addcustomerServiceOrderWork.shtml")
    public ModelAndView addcustomerServiceOrderWork(HttpServletRequest request) {
        String rtPage = "/order/afterService/addcustomerServiceOrderWork";
        Map<String, Object> resMap = new HashMap<String, Object>();

        int id = Integer.valueOf(request.getParameter("id"));
        CustomerServiceOrderCustom customerServiceOrderCustom = customerServiceOrderService.selectCustomerServiceOrderCustomByPrimaryKey(id);
        resMap.put("customerServiceOrderCustom", customerServiceOrderCustom);

        SysOrganizationExample sysOrganizationExample = new SysOrganizationExample();
        sysOrganizationExample.createCriteria().andStatusEqualTo("A");
        List<SysOrganization> sysOrganizationlist = sysOrganizationService.selectByExample(sysOrganizationExample);
        resMap.put("sysOrganizationlist", sysOrganizationlist);
        return new ModelAndView(rtPage, resMap);
    }

    //添加售后工单
    @ResponseBody
    @RequestMapping("/order/afterService/addcustomerServiceOrderWorkList.shtml")
    public ModelAndView addcustomerServiceOrderWorkList(HttpServletRequest request, WoRk work, String attachmentName, String attachmentPath, String id) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String code = "";
        String msg = "";
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            Integer staffId = Integer.valueOf(staffBean.getStaffID());
            Date date = new Date();

            work.setStatus("0");
            work.setStatusBehavior("1");
            work.setDelFlag("0");
            work.setRelevantId(Integer.valueOf(id));
            work.setCreateBy(staffId);
            work.setCreateDate(date);

            if (work.getRelevantType().equals("1")) {
                InterventionOrderCustomExample interventionOrderCustomExample = new InterventionOrderCustomExample();
                interventionOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andInterventionCodeEqualTo(work.getRelevantCode());
                List<InterventionOrderCustom> interventionOrderCustom = interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
                work.setRelevantId(interventionOrderCustom.get(0).getId());

            }
            if (work.getRelevantType().equals("2")) {
                AppealOrderExample appealOrderExample = new AppealOrderExample();
                appealOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
                List<AppealOrder> appealOrder = appealOrderService.selectByExample(appealOrderExample);
                work.setRelevantId(appealOrder.get(0).getId());
            }
            if (work.getRelevantType().equals("4")) {
                SubOrderExample subOrderExample = new SubOrderExample();
                subOrderExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeEqualTo(work.getRelevantCode());
                List<SubOrder> subOrder = subOrderService.selectByExample(subOrderExample);
                work.setRelevantId(subOrder.get(0).getId());
            }
            if (work.getRelevantType().equals("5")) {
                CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
                customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
                List<CustomerServiceOrder> customerServiceOrder = customerServiceOrderService.selectByExample(customerServiceOrderExample);
                work.setRelevantId(customerServiceOrder.get(0).getId());
            }

            woRkService.insertSelective(work);

            Attachment attachment = new Attachment();
            attachment.setWorkId(work.getId());
            attachment.setAttachmentName(attachmentName);
            attachment.setAttachmentPath(attachmentPath);
            attachment.setDelFlag("0");
            attachment.setCreateBy(staffId);
            attachment.setCreateDate(date);
            attachmentService.insertSelective(attachment);


            WorkHistory workHistory = new WorkHistory();
            workHistory.setWorkId(work.getId());
            workHistory.setOrgId(work.getOrgId());
            workHistory.setStaffId(work.getStaffId());
            workHistory.setWorkType(work.getWorkType());
            workHistory.setStatus(work.getStatus());
            workHistory.setStatusBehavior(work.getStatusBehavior());
            workHistory.setUrgentDegree(work.getUrgentDegree());
            workHistory.setCloseReason(work.getCloseReason());
            workHistory.setTitleContent(work.getTitleContent());
            workHistory.setRelevantType(work.getRelevantType());
            workHistory.setRelevantCode(work.getRelevantCode());
            workHistory.setRelevantId(work.getRelevantId());
            workHistory.setDescribeContent(work.getDescribeContent());
            workHistory.setCreateBy(work.getCreateBy());
            workHistory.setCreateDate(work.getCreateDate());
            workHistory.setDelFlag("0");
            woRkHistoryService.insertSelective(workHistory);

            SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(staffId);
            SysStaffInfo sysStaffInfos = sysStaffInfoService.selectByPrimaryKey(work.getStaffId());

            WorkRecord workRecord = new WorkRecord();
            workRecord.setWorkHistoryId(workHistory.getId());
            workRecord.setWorkId(work.getId());
            workRecord.setOrgId(work.getOrgId());
            workRecord.setStaffId(work.getStaffId());
            workRecord.setRecordStatus("1");
            workRecord.setRecordTitle("由" + sysStaffInfo.getStaffName() + "创建工单并指派给" + sysStaffInfos.getStaffName());
            workRecord.setCreateBy(staffId);
            workRecord.setCreateDate(date);
            workRecord.setDelFlag("0");
            workRecordService.insertSelective(workRecord);


            AttachmentHistory attachmentHistory = new AttachmentHistory();
            attachmentHistory.setWorkHistoryId(workHistory.getId());
            attachmentHistory.setAttachmentName(attachmentName);
            attachmentHistory.setAttachmentPath(attachmentPath);
            attachmentHistory.setCreateBy(staffId);
            attachmentHistory.setCreateDate(date);
            attachmentHistory.setDelFlag("0");
            attachmentHistoryService.insertSelective(attachmentHistory);

            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        } catch (Exception e) {
            e.printStackTrace();
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 预售定金子订单列表
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/subDepositOrder/list.shtml")
    public ModelAndView subDepositOrderList(HttpServletRequest request, String statusPage) {
        String rtPage = "/order/subDepositOrder/list";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("statusList", DataDicUtil.getStatusList("BU_SUB_DEPOSIT_ORDER", "STATUS"));
        SysPaymentExample e = new SysPaymentExample();
        SysPaymentExample.Criteria c = e.createCriteria();
        c.andDelFlagEqualTo("0");
        List<SysPayment> sysPaymentList = sysPaymentService.selectByExample(e);
        resMap.put("sysPaymentList", sysPaymentList);
        String staffID = this.getSessionStaffBean(request).getStaffID();

        SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
        SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
        sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));

        //钟表运营部状态，只获取主营类目为钟表
        String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
        if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
            String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
            if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                sysstaffProductTypeexCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
            }
        }
        resMap.put("isCwOrgStatus", isCwOrgStatus);

        List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
        resMap.put("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));

        //对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
        String isManagement = this.getSessionStaffBean(request).getIsManagement();//管理层
        resMap.put("isManagement", isManagement);
        resMap.put("staffID", staffID);
        SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
        SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
        sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
        List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
        resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();//上个月1号0点
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Calendar calendars = Calendar.getInstance();//本月明天0点
        calendars.set(Calendar.HOUR_OF_DAY, 23);
        calendars.set(Calendar.MINUTE, 59);
        calendars.set(Calendar.SECOND, 59);

        String dateBegin = df.format(calendar.getTime());
        String dateEnd = df.format(calendars.getTime());

        resMap.put("date_end", dateEnd);
        resMap.put("date_begin", dateBegin);

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 预售定金子订单列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/order/subDepositOrder/data.shtml")
    @ResponseBody
    public Map<String, Object> subDepositOrderData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<SubDepositOrderCustom> listMap = null;
        Integer totalCount = 0;
        try {

            SubDepositOrderCustomExample subDepositOrderCustomExample = new SubDepositOrderCustomExample();
            subDepositOrderCustomExample.setOrderByClause("t.id desc");
            SubDepositOrderCustomExample.SubDepositOrderCustomCriteria subDepositOrderCustomCriteria = subDepositOrderCustomExample.createCriteria();
            subDepositOrderCustomCriteria.andDelFlagEqualTo("0");
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                String status = request.getParameter("status");
                subDepositOrderCustomCriteria.andStatusEqualTo(status);
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                String mchtCode = request.getParameter("mchtCode");
                subDepositOrderCustomCriteria.andMchtCodeEqualTo(mchtCode);
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtType"))) {
                subDepositOrderCustomCriteria.andMchtTypeEqualTo(request.getParameter("mchtType"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
                String mchtName = request.getParameter("mchtName");
                subDepositOrderCustomCriteria.andMchtNameLikeTo(mchtName);
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMin"))) {
                BigDecimal amountMin = new BigDecimal(request.getParameter("amountMin"));
                subDepositOrderCustomCriteria.andDepositGreaterThanOrEqualTo(amountMin);
            }
            if (!StringUtil.isEmpty(request.getParameter("amountMax"))) {
                BigDecimal amountMax = new BigDecimal(request.getParameter("amountMax"));
                subDepositOrderCustomCriteria.andDepositLessThanOrEqualTo(amountMax);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("createDateBegin"))) {
                subDepositOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("createDateEnd"))) {
                subDepositOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd") + ":59"));
            }
            if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
                subDepositOrderCustomCriteria.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
                subDepositOrderCustomCriteria.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd") + ":59"));
            }
            if (!StringUtil.isEmpty(request.getParameter("combineDepositOrderCode"))) {
                String combineDepositOrderCode = request.getParameter("combineDepositOrderCode");
                subDepositOrderCustomCriteria.andCombineDepositOrderCodeEqualTo(combineDepositOrderCode);
            }

            if (!StringUtil.isEmpty(request.getParameter("subDepositOrderCode"))) {
                String subDepositOrderCode = request.getParameter("subDepositOrderCode");
                subDepositOrderCustomCriteria.andSubDepositOrderCodeEqualTo(subDepositOrderCode);
            }

            if (!StringUtil.isEmpty(request.getParameter("memberInfoId"))) {
                subDepositOrderCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberInfoId")));
            }
            if (!StringUtil.isEmpty(request.getParameter("brandName"))) {
                subDepositOrderCustomCriteria.andBrandNameEqualTo(request.getParameter("brandName").trim());
            }

            if (!StringUtil.isEmpty(request.getParameter("paymentId"))) {
                subDepositOrderCustomCriteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("paymentId").trim()));
            }
            if (!StringUtil.isEmpty(request.getParameter("memberNick"))) {
                subDepositOrderCustomCriteria.andMemberNickEqualTo(request.getParameter("memberNick").trim());
            }

            //钟表运营部状态，只获取主营类目为钟表
            String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
            if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
                String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
                if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
                    subDepositOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
                }
            } else {
                if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
                    subDepositOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
                }
            }

            if (!StringUtil.isEmpty(request.getParameter("completeDateBegin"))) {
                subDepositOrderCustomCriteria.andCompleteDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("completeDateBegin") + ":00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("completeDateEnd"))) {
                subDepositOrderCustomCriteria.andCompleteDateLessThanOrEqualTo(sdf.parse(request.getParameter("completeDateEnd") + ":59"));
            }
            if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {//对接人的商家
                subDepositOrderCustomCriteria.andplatContactStaffIdtEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
            }
            totalCount = subDepositOrderService.countSubDepositOrderCustomByExample(subDepositOrderCustomExample);
            subDepositOrderCustomExample.setLimitStart(page.getLimitStart());
            subDepositOrderCustomExample.setLimitSize(page.getLimitSize());
            listMap = subDepositOrderService.selectSubDepositOrderCustomByExample(subDepositOrderCustomExample);

            resMap.put("Rows", listMap);
            resMap.put("Total", totalCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 编辑开票信息页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/editCombineOrderInvoiceView.shtml")
    public ModelAndView editCombineOrderInvoiceView(HttpServletRequest request) {
        String rtPage = "/order/invoice/editCombineOrderInvoiceView";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("combineOrderId", request.getParameter("combineOrderId"));
        if (!StringUtils.isEmpty(request.getParameter("id"))) {
            CombineOrderInvoice combineOrderInvoice = combineOrderInvoiceService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
            resMap.put("combineOrderInvoice", combineOrderInvoice);
        }
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 保存开票信息
     * @param request
     * @param combineOrderInvoice
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order/saveCombineOrderInvoice.shtml")
    public Map<String, Object> saveCombineOrderInvoice(HttpServletRequest request, CombineOrderInvoice combineOrderInvoice) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "审核成功");
        try {
            Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
            Date date = new Date();
            combineOrderInvoice.setStatus("0");
            combineOrderInvoice.setStatusDate(date);
            combineOrderInvoice.setOperator(staffID);
            combineOrderInvoice.setDelFlag("0");
            if (combineOrderInvoice.getId() == null) {
                combineOrderInvoice.setCreateBy(staffID);
                combineOrderInvoice.setCreateDate(date);
                combineOrderInvoiceService.insertSelective(combineOrderInvoice);
            }else {
                CombineOrderInvoice combineOrderInvoiceOld = combineOrderInvoiceService.selectByPrimaryKey(combineOrderInvoice.getId());
                combineOrderInvoice.setCreateBy(combineOrderInvoiceOld.getCreateBy());
                combineOrderInvoice.setCreateDate(combineOrderInvoiceOld.getCreateDate());
                combineOrderInvoice.setRejectReason(combineOrderInvoiceOld.getRejectReason());
                combineOrderInvoice.setRemarks(combineOrderInvoiceOld.getRemarks());
                combineOrderInvoice.setUpdateBy(staffID);
                combineOrderInvoice.setUpdateDate(date);
                combineOrderInvoiceService.updateByPrimaryKey(combineOrderInvoice);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "操作失败,请联系管理员");
        }
        return resMap;
    }

    /**
     * 编辑开票信息页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/combineOrderInvoiceListView.shtml")
    public ModelAndView combineOrderInvoiceListView(HttpServletRequest request) {
        return new ModelAndView("/order/invoice/combineOrderInvoiceListView");
    }

    /**
     * 母订单开票列表
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/order/combineOrderInvoiceList.shtml")
    @ResponseBody
    public Map<String, Object> combineOrderInvoiceList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<CombineOrderInvoiceCustom> listMap = null;
        Integer totalCount = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            CombineOrderInvoiceExample example = new CombineOrderInvoiceExample();
            CombineOrderInvoiceExample.Criteria criteria = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("company"))) {
                criteria.andCompanyEqualTo(request.getParameter("company"));
            }
            if(!StringUtils.isEmpty(request.getParameter("dutyParagraph"))) {
                criteria.andDutyParagraphEqualTo(request.getParameter("dutyParagraph"));
            }
            if(!StringUtils.isEmpty(request.getParameter("status"))) {
                criteria.andStatusEqualTo(request.getParameter("status"));
            }
            if(!StringUtils.isEmpty(request.getParameter("type"))) {
                criteria.andTypeEqualTo(request.getParameter("type"));
            }
            if(!StringUtils.isEmpty(request.getParameter("createDateBegin"))) {
                criteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin") + " 00:00:00"));
            }
            if(!StringUtils.isEmpty(request.getParameter("createDateEnd"))) {
                criteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd") + " 23:59:59"));
            }
            if(!StringUtils.isEmpty(request.getParameter("statusDateBegin"))) {
                criteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("statusDateBegin") + " 00:00:00"));
            }
            if(!StringUtils.isEmpty(request.getParameter("statusDateEnd"))) {
                criteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("statusDateEnd") + " 23:59:59"));
            }
            if(!StringUtils.isEmpty(request.getParameter("statusDateBegin")) || !StringUtils.isEmpty(request.getParameter("statusDateEnd"))) {
                criteria.andStatusIn(Arrays.asList("1,2".split(",")));
            }
            totalCount = combineOrderInvoiceService.countByCustomExample(example);
            example.setLimitStart(page.getLimitStart());
            example.setLimitSize(page.getLimitSize());
            example.setOrderByClause(" id DESC");
            listMap = combineOrderInvoiceService.selectByCustomExample(example);

            resMap.put("Rows", listMap);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 处理开票信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order/auditCombineOrderInvoice.shtml")
    public Map<String, Object> auditCombineOrderInvoice(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "审核成功");
        try {
            Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
            Date date = new Date();
            Integer id = Integer.valueOf(request.getParameter("id"));
            String status = request.getParameter("status");
            CombineOrderInvoice combineOrderInvoice = new CombineOrderInvoice();
            combineOrderInvoice.setId(id);
            if("2".equals(status)){
                if (StringUtils.isEmpty(request.getParameter("rejectReason"))) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "驳回原因必填!");
                    return resMap;
                }
                combineOrderInvoice.setRejectReason(request.getParameter("rejectReason"));
            }
            combineOrderInvoice.setStatus(status);
            combineOrderInvoice.setStatusDate(date);
            combineOrderInvoice.setOperator(staffID);
            combineOrderInvoice.setUpdateBy(staffID);
            combineOrderInvoice.setUpdateDate(date);
            combineOrderInvoiceService.updateByPrimaryKeySelective(combineOrderInvoice);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "操作失败,请联系管理员");
        }
        return resMap;
    }

    /**
     * 批量处理开票信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order/batchAuditCombineOrderInvoice.shtml")
    public Map<String, Object> batchAuditCombineOrderInvoice(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "审核成功");
        try {
            Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
            Date date = new Date();
            List<Integer> ids = Arrays.asList((Integer[]) ConvertUtils.convert(request.getParameter("ids").split(","), Integer.class));
            CombineOrderInvoice combineOrderInvoice = new CombineOrderInvoice();
            combineOrderInvoice.setStatus("1");
            combineOrderInvoice.setStatusDate(date);
            combineOrderInvoice.setOperator(staffID);
            combineOrderInvoice.setUpdateBy(staffID);
            combineOrderInvoice.setUpdateDate(date);
            CombineOrderInvoiceExample example = new CombineOrderInvoiceExample();
            CombineOrderInvoiceExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            criteria.andStatusEqualTo("0");
            criteria.andDelFlagEqualTo("0");
            combineOrderInvoiceService.updateByExampleSelective(combineOrderInvoice, example);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "操作失败,请联系管理员");
        }
        return resMap;
    }

}
