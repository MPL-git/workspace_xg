package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.common.constant.Const;
import com.jf.entity.ProductUpperLowerLog;
import com.jf.service.ProductUpperLowerLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.Activity;
import com.jf.entity.ActivityAuth;
import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductAuditLog;
import com.jf.entity.ActivityProductAuditLogCustom;
import com.jf.entity.ActivityProductCustom;
import com.jf.entity.ActivityProductCustomExample;
import com.jf.entity.Product;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductDescPic;
import com.jf.entity.ProductDescPicExample;
import com.jf.entity.ProductItemCustom;
import com.jf.entity.ProductItemExample;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import com.jf.entity.ProductProp;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysStatus;
import com.jf.service.ActivityAuthService;
import com.jf.service.ActivityProductAuditLogService;
import com.jf.service.ActivityProductService;
import com.jf.service.ActivityService;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductDescPicService;
import com.jf.service.ProductItemService;
import com.jf.service.ProductPicService;
import com.jf.service.ProductService;
import com.jf.vo.Page;

/**
 * 活动商品表controller
 * @author Administrator
 *
 */
@Controller
public class ActivityProductController extends BaseController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1267545862588966835L;
	@Autowired
	private ActivityProductService activityProductService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private PlatformContactService platformContactService;
	
	@Autowired
	private ActivityAuthService activityAuthService;
	
	@Autowired
	private ActivityProductAuditLogService activityProductAuditLogService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductPicService productPicService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private ProductDescPicService productDescPicService;
	
	@Resource
	private ProductUpperLowerLogService productUpperLowerLogService;
	@RequestMapping(value="/activity/allActivityProductList.shtml")
	public String getAllActivityProductList(Model model,Integer activityId,HttpServletRequest request,HttpServletResponse response){
		Integer type=Integer.valueOf(request.getParameter("type"));
		int staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		Integer myself=0;
		model.addAttribute("activityId", activityId);
		model.addAttribute("refuseFlag", 0);
		model.addAttribute("type", type);
		if(type.intValue()==3||type.intValue()==4){
			Activity activity=activityService.selectByPrimaryKey(activityId);
			if ((type.intValue()==3 && activity.getDesignAuditBy()!=null && activity.getDesignAuditBy()==staffId) || (type.intValue()==4 && activity.getLawAuditBy()!=null && activity.getLawAuditBy()==staffId)){
				myself=1;
			}
			model.addAttribute("myself", myself);
			return "/activity/designAndLawActivityProductList";
		}else{
			if(type.intValue()==1){
				Integer staffIds=activityProductService.getStaffIdByActivity(activityId);
				if (staffIds!=null && staffIds==staffId){
					myself=1;
				}
			}
			model.addAttribute("myself", myself);
			return "/activity/allActivityProductList";
		}
	}

	
	@RequestMapping(value="/activity/allActivityProductListData.shtml")
	@ResponseBody
	public Map<String, Object> getAllActivityProductListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<ActivityProductCustom> dataList = null;
		Integer totalCount =0;
		
		Integer type=Integer.valueOf(request.getParameter("type"));
		
		Integer activityId=Integer.valueOf(request.getParameter("activityId"));
		StaffBean staffBean = this.getSessionStaffBean(request);
		int staffId=Integer.valueOf(staffBean.getStaffID());
		//已提报商品总数
//		Integer already=activityProductService.getActivityIdByProductCount(activityId, 0);
//		//已驳回商品总数
//		Integer reject=activityProductService.getActivityIdByProductCount(activityId, 1);
		ActivityProductCustomExample activityProductCustomExample=new ActivityProductCustomExample();
		ActivityProductCustomExample.ActivityProductCustomCriteria activityProductCustomCriteria=activityProductCustomExample.createCriteria();
		activityProductCustomCriteria.andDelFlagEqualTo("0");
		Integer refuseFlag=Integer.valueOf(request.getParameter("refuseFlag"));
		Integer refuse=Integer.valueOf(request.getParameter("refuse"));
		if(refuse==1){
			refuseFlag=refuse;
		}
		if(!StringUtils.isEmpty(request.getParameter("productValue"))){
			String productName=request.getParameter("productValue");
			activityProductCustomCriteria.andMchtNameOrCodeOrProductId("%"+productName+"%");
		}
		
		
		activityProductCustomCriteria.andRefuseFlagEqualTo(refuseFlag.toString());
		activityProductCustomCriteria.andActivityIdEqualTo(activityId);
		if(refuseFlag.intValue()==0){
			if(type.intValue()==3){
				activityProductCustomCriteria.andOperateAuditStatusEqualTo("2");
			}
			if(type.intValue()==4){
				activityProductCustomCriteria.andDesignAuditStatusEqualTo("2");
			}
			if(type.intValue()==5){
				activityProductCustomCriteria.andLawAuditStatusEqualTo("2");
			}
		}
		
		activityProductCustomExample.setLimitStart(page.getLimitStart());
		activityProductCustomExample.setLimitSize(page.getLimitSize());
		dataList=activityProductService.selectByCustomExample(activityProductCustomExample);
		totalCount=activityProductService.countByCustomExample(activityProductCustomExample);
		
		for (ActivityProductCustom activityProductCustom:dataList) {
			activityProductCustom.setProductPic(FileUtil.getMiddleImageName(activityProductCustom.getProductPic()));
		}
		
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 批量驳回
	 * @param request
	 * @param id
	 * @param batchName
	 * @return
	 */
	@RequestMapping(value="/activity/allActivityProductBatchReject.shtml")
	@ResponseBody
	public Map<String, Object> batchReject(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		Integer type=Integer.valueOf(request.getParameter("type"));
		try {
			String ids=request.getParameter("activityProductId");
			String[] split=ids.split(",");
			StaffBean staffBean =this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			for (int i = 0; i < split.length; i++) {
//				商品审核流水线
				ActivityProductAuditLog activityProductAuditLog=new ActivityProductAuditLog();
				activityProductAuditLog.setCreateBy(staffId);
				activityProductAuditLog.setCreateDate(new Date());
				activityProductAuditLog.setDelFlag("0");
				activityProductAuditLog.setStatus("3");
				activityProductAuditLog.setRemarks(request.getParameter("batchName"));
				
				ActivityProduct activityProduct=activityProductService.selectByPrimaryKey(Integer.valueOf(split[i]));
				activityProduct.setUpdateBy(staffId);
				activityProduct.setUpdateDate(new Date());
				//运营
				if(type.intValue()==0||type.intValue()==1||type.intValue()==2){
					if("1".equals(activityProduct.getOperateAuditStatus())||StringUtils.isEmpty(activityProduct.getOperateAuditStatus())){
						activityProduct.setOperateAuditBy(staffId);
						activityProduct.setOperateAuditStatus("3");
						activityProduct.setRemarks(request.getParameter("batchName"));
						activityProduct.setRefuseFlag("1");
						activityProductService.updateByPrimaryKeySelective(activityProduct);
						activityProductAuditLog.setType(type.toString());
						activityProductAuditLog.setActivityProductId(activityProduct.getId());
						activityProductAuditLogService.insertSelective(activityProductAuditLog);
					}
				}
				//设计
				if(type.intValue()==3){
					if("1".equals(activityProduct.getDesignAuditStatus())){
						activityProduct.setDesignAuditBy(staffId);
						activityProduct.setDesignAuditStatus("3");
						activityProduct.setRemarks(request.getParameter("batchName"));
						activityProduct.setRefuseFlag("1");
						activityProductService.updateByPrimaryKeySelective(activityProduct);
						activityProductAuditLog.setType("2");
						activityProductAuditLog.setActivityProductId(activityProduct.getId());
						activityProductAuditLogService.insertSelective(activityProductAuditLog);
					}
				}
				//法务
				if(type.intValue()==4){
					if("1".equals(activityProduct.getLawAuditStatus())){
						activityProduct.setLawAuditBy(staffId);
						activityProduct.setLawAuditStatus("3");
						activityProduct.setRemarks(request.getParameter("batchName"));
						activityProduct.setRefuseFlag("1");
						activityProductService.updateByPrimaryKeySelective(activityProduct);
						activityProductAuditLog.setType("3");
						activityProductAuditLog.setActivityProductId(activityProduct.getId());
						activityProductAuditLogService.insertSelective(activityProductAuditLog);
						
						//法务驳回时，同时更新商品状态
						Product product=new Product();
						product.setId(activityProduct.getProductId());
						product.setStatus("0");
						product.setOffReason("2");
						product.setAuditStatus("3");
						product.setAuditRemarks(request.getParameter("batchName"));
						product.setUpdateBy(staffId);
						product.setUpdateDate(new Date());
						productService.updateByPrimaryKeySelective(product);

						//商品上下架日志
						ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
						productUpperLowerLog.setProductId(product.getId());
						productUpperLowerLog.setStatus(product.getStatus());
						productUpperLowerLog.setType(Const.PLATFORM);
						productUpperLowerLog.setOffReason(product.getOffReason());
						productUpperLowerLog.setCreateBy(staffId);
						productUpperLowerLog.setCreateDate(new Date());
						productUpperLowerLogService.insertSelective(productUpperLowerLog);
					}
				}
				//总监
				if(type.intValue()==5){
					if("1".equals(activityProduct.getCooAuditStatus())){
						activityProduct.setCooAuditBy(staffId);
						activityProduct.setCooAuditStatus("3");
						activityProduct.setRemarks(request.getParameter("batchName"));
						activityProduct.setRefuseFlag("1");
						activityProductService.updateByPrimaryKeySelective(activityProduct);
						activityProductAuditLog.setType("4");
						activityProductAuditLog.setActivityProductId(activityProduct.getId());
						activityProductAuditLogService.insertSelective(activityProductAuditLog);
					}
				}
				
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}

	/**
	 * 单品商品列表批量审核通过入口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/singleAuditAdopt.shtml")
	@ResponseBody
	public Map<String, Object> singleAuditAdopt(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		Integer type=Integer.valueOf(request.getParameter("type"));
		try {
			String ids=request.getParameter("activityProductId");
			String[] split=ids.split(",");
			StaffBean staffBean =this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			for (int i = 0; i < split.length; i++) {
				ActivityProduct activityProduct=activityProductService.selectByPrimaryKey(Integer.valueOf(split[i]));
				activityProduct.setRefuseFlag("0");
				activityProduct.setUpdateBy(staffId);
				activityProduct.setUpdateDate(new Date());
				
				//商品审核流水
				ActivityProductAuditLog activityProductAuditLog=new ActivityProductAuditLog();
				activityProductAuditLog.setCreateBy(staffId);
				activityProductAuditLog.setCreateDate(new Date());
				activityProductAuditLog.setType(type.toString());
				activityProductAuditLog.setDelFlag("0");
				activityProductAuditLog.setStatus("2");
				
				//运营
				if(type.intValue()==1){
					if("1".equals(activityProduct.getOperateAuditStatus())||StringUtils.isEmpty(activityProduct.getOperateAuditStatus())){
						activityProduct.setOperateAuditBy(staffId);
						activityProduct.setOperateAuditStatus("2");
						activityProduct.setDesignAuditStatus("1");
						activityProductService.updateByPrimaryKeySelective(activityProduct);
						activityProductAuditLog.setActivityProductId(activityProduct.getId());
						activityProductAuditLogService.insertSelective(activityProductAuditLog);
					}
				}
				//设计审核
				if(type.intValue()==3){
					if("1".equals(activityProduct.getDesignAuditStatus())){
						activityProduct.setDesignAuditBy(staffId);
						activityProduct.setDesignAuditStatus("2");
						activityProduct.setLawAuditStatus("1");
						activityProductService.updateByPrimaryKeySelective(activityProduct);
						activityProductAuditLog.setActivityProductId(activityProduct.getId());
						activityProductAuditLogService.insertSelective(activityProductAuditLog);
					}
				}
				//法务审核
				if(type.intValue()==4){
					if("1".equals(activityProduct.getLawAuditStatus())){
						activityProduct.setLawAuditBy(staffId);
						activityProduct.setLawAuditStatus("2");
						activityProduct.setCooAuditStatus("1");
						activityProductService.updateByPrimaryKeySelective(activityProduct);
						activityProductAuditLog.setActivityProductId(activityProduct.getId());
						activityProductAuditLogService.insertSelective(activityProductAuditLog);
						
						//法务通过时，同时更新商品状态
						Product product=new Product();
						product.setId(activityProduct.getProductId());
						product.setStatus("1");
						product.setOffReason("");
						product.setAuditStatus("2");
						product.setUpdateBy(staffId);
						product.setUpdateDate(new Date());
						productService.updateByPrimaryKeySelective(product);
					}
				}
				//总监
				if(type.intValue()==5){
					if("1".equals(activityProduct.getCooAuditStatus())){
						activityProduct.setCooAuditBy(staffId);
						activityProduct.setCooAuditStatus("2");
						activityProductService.updateByPrimaryKeySelective(activityProduct);
						activityProductAuditLog.setActivityProductId(activityProduct.getId());
						activityProductAuditLogService.insertSelective(activityProductAuditLog);
					}
				}
			}
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	
	
	
	/**
	 * 活动商品驳回
	 * @param model
	 * @param request
	 * @param activityProductId
	 * @return
	 */
	@RequestMapping(value="/activity/allActivityProductReject.shtml")
	public String reject(Model model,HttpServletRequest request,Integer activityProductId,Integer type){
		model.addAttribute("activityProductId", activityProductId);
		model.addAttribute("type", type);
		return "/activity/allActivityProductReject";
	}
	
	/**
	 * 单品查看商品列表
	 * @param model
	 * @param request
	 * @param activityAreaId
	 * @return
	 */
	@RequestMapping(value="/activity/singleActivityProductGoodList.shtml")
	public String singleActivityProductGoodList(Model model,HttpServletRequest request,Integer activityAreaId){
		model.addAttribute("activityAreaId", activityAreaId);
		Integer type=Integer.valueOf(request.getParameter("type"));
		model.addAttribute("type", type);
		return "/activity/singleActivityProductGoodList";
	}
	
	@RequestMapping(value="/activity/singleActivityProductGoodListData.shtml")
	@ResponseBody
	public Map<String, Object> singleActivityProductGoodListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<ActivityProductCustom> dataList = null;
		Integer totalCount =0;
		StaffBean staffBean =this.getSessionStaffBean(request);
		Integer staffId=Integer.valueOf(staffBean.getStaffID());
		
		
		ActivityProductCustomExample activityProductCustomExample=new ActivityProductCustomExample();
		ActivityProductCustomExample.ActivityProductCustomCriteria activityProductCustomCriteria=activityProductCustomExample.createCriteria();
		activityProductCustomCriteria.andDelFlagEqualTo("0");
		
		//单品列表过滤6、7
		activityProductCustomCriteria.andActivityAreaTypeEqualToSixOrSeven();
		
		if(!StringUtils.isEmpty(request.getParameter("activityAreaId"))){
			Integer activityAreaId=Integer.valueOf(request.getParameter("activityAreaId"));
			List<Integer> activityId=activityService.getActivityByIdList(activityAreaId);
			if(activityId.size()>0){
				activityProductCustomCriteria.andActivityIdIn(activityId);
			}else{
				activityId.add(0);
				activityProductCustomCriteria.andActivityIdIn(activityId);
			}
		}
		
		if(!StringUtils.isEmpty(request.getParameter("productId"))){
			Integer productId=Integer.valueOf(request.getParameter("productId"));
			activityProductCustomCriteria.andProductIdEqualTo(productId);
		}
		if(!StringUtils.isEmpty(request.getParameter("shortName"))){
			activityProductCustomCriteria.andSingleMchtNameLike(request.getParameter("shortName"));
		}
		if(!StringUtils.isEmpty(request.getParameter("mchtCode"))){
			activityProductCustomCriteria.andSingleMchtCodeLike(request.getParameter("mchtCode"));
		}
		
		//活动类型、报名活动：两者使用联动，两者必选才能搜索
		if(!"-1".equals(request.getParameter("activityType"))){
			activityProductCustomCriteria.andActivityAreaIdByEqualTo(request.getParameter("activityType"));
		}
		
		
		//运营专员审核
		if(!StringUtils.isEmpty(request.getParameter("operateAuditStatus"))){
			activityProductCustomCriteria.andOperateAuditStatusEqualTo(request.getParameter("operateAuditStatus").toString());
		}
			
		//设计部审核
		if(!StringUtils.isEmpty(request.getParameter("designAuditStatus"))){
			activityProductCustomCriteria.andDesignAuditStatusEqualTo(request.getParameter("designAuditStatus").toString());
		}
			
		//法务审核
		if(!StringUtils.isEmpty(request.getParameter("lawAuditStatus"))){
			activityProductCustomCriteria.andLawAuditStatusEqualTo(request.getParameter("lawAuditStatus").toString());
		}
			
		//运营总监审核
		if(!StringUtils.isEmpty(request.getParameter("cooAuditStatus"))){
			activityProductCustomCriteria.andCooAuditStatusEqualTo(request.getParameter("cooAuditStatus").toString());
		}
		
		if(!StringUtils.isEmpty(request.getParameter("type"))){
			Integer type=Integer.valueOf(request.getParameter("type"));
			if(type.intValue()!=0){
				activityProductCustomCriteria.andActivityStatusNotEqualTo("1");
			}
			if(type.intValue()==1){
				//商家对接运营专员，只列出该运营专员的所有商品
				activityProductCustomCriteria.andStaffIdEqualTo(staffId);
				activityProductCustomExample.setOrderByClause("ap.operate_audit_status, ap.id desc");
			}
			if(type.intValue()==3){
				//设计部审核，过滤设计部状态不能为空的商品，才能证明该商品在运营专员已审核通过，设计部才能看的到该商品，否则不显示
				activityProductCustomCriteria.andDesignAuditStatusIsNotNull();
				activityProductCustomExample.setOrderByClause("ap.design_audit_status, ap.id desc");
			}
			if(type.intValue()==4){
				//法务部审核，过滤法务部状态不能为空的商品，才能证明该商品在设计部已审核通过，法务部才能看的到该商品，否则不显示
				activityProductCustomCriteria.andLawAuditStatusIsNotNull();
				activityProductCustomExample.setOrderByClause("ap.law_audit_status, ap.id desc");
			}
			if(type.intValue()==5){
				//运营总监审核，过滤运营总监部状态不能为空的商品，才能证明该商品在法务部已审核通过，运营总监才能看的到该商品，否则不显示
				activityProductCustomCriteria.andCooAuditStatusIsNotNull();
				activityProductCustomExample.setOrderByClause("ap.coo_audit_status, ap.id desc");
			}
		}
		activityProductCustomExample.setLimitSize(page.getLimitSize());
		activityProductCustomExample.setLimitStart(page.getLimitStart());
		dataList=activityProductService.selectByCustomExample(activityProductCustomExample);
		totalCount=activityProductService.countByCustomExample(activityProductCustomExample);
		
		for (ActivityProductCustom activityProductCustom:dataList) {
			activityProductCustom.setProductPic(FileUtil.getMiddleImageName(activityProductCustom.getProductPic()));
		}
		
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	@RequestMapping(value="/activity/allSingleActivityProductAuditList.shtml")
	public String allSingleProduct(Model model,HttpServletRequest request,Integer type){
		model.addAttribute("type", type);
		//运营专员审核状态
		List<SysStatus> operateAuditStatus = DataDicUtil.getTableStatus("BU_ACTIVITY_PRODUCT", "OPERATE_AUDIT_STATUS");
		//设计部审核状态
		List<SysStatus> designAuditStatus = DataDicUtil.getTableStatus("BU_ACTIVITY_PRODUCT", "DESIGN_AUDIT_STATUS");
		//法务审核状态
		List<SysStatus> lawAuditStatus = DataDicUtil.getTableStatus("BU_ACTIVITY_PRODUCT", "LAW_AUDIT_STATUS");
		//运营总监审核状态
		List<SysStatus> cooAuditStatus = DataDicUtil.getTableStatus("BU_ACTIVITY_PRODUCT", "COO_AUDIT_STATUS");
		model.addAttribute("operateAuditStatus", operateAuditStatus);
		model.addAttribute("designAuditStatus", designAuditStatus);
		model.addAttribute("lawAuditStatus", lawAuditStatus);
		model.addAttribute("cooAuditStatus", cooAuditStatus);
		if(!StringUtils.isEmpty(request.getParameter("activityAreaId"))){
			model.addAttribute("activityAreaId", request.getParameter("activityAreaId"));
		}
		if(type.intValue()==0){
			return "/activity/allSingleActivityProductList";
		}else if(type.intValue()==1){
			return "/activity/operateSingleActivityProductList";
		}else if(type.intValue()==3||type.intValue()==4){
			return "/activity/designAndLawSingleActivityProductList";
		}else if(type.intValue()==5){
			return "/activity/cooSingleActivityProductList";
		}else{return "";}
	}
	
	/**
	 * 查看单品详情
	 * @param model
	 * @param activityProductId
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping("/activity/allSeeSingleActivityProductInfo.shtml")
	public String seeSingleActivityProductInfo(Model model,Integer activityProductId,Integer productId,Integer type,HttpServletRequest request){
		
		StaffBean staffBean =this.getSessionStaffBean(request);
		Integer staffId=Integer.valueOf(staffBean.getStaffID());
		//获得商家对接运营专员staffId
		//staffType:1、是，0、否
		if(type.intValue()!=4){
			Integer staffIds=activityProductService.getStaffId(productId);
			if(staffIds!=null && staffId.intValue()==staffIds.intValue()){
				model.addAttribute("staffType", 1);
			}else{
				model.addAttribute("staffType", 0);
			}
		}
		
		ActivityProductCustom activityProductCustom=activityProductService.selectByActivityProductInfo(activityProductId);
		model.addAttribute("activityProductCustom", activityProductCustom);
		model.addAttribute("activityProductId", activityProductId);
		model.addAttribute("type", type);
		
		List<ActivityProductAuditLogCustom> apalc=activityProductAuditLogService.selectByExample(activityProductId);
		model.addAttribute("ActivityProductAuditLogCustom", apalc);
		
		return "/activity/allSeeSingleActivityProductInfo";
	}
	
	//设计与法务审核页面
	@RequestMapping(value="/activity/seeDesignAndLawSingleProduct.shtml")
	public String seeDesignAndLawSingleProduct(Model model,HttpServletRequest request){
		model.addAttribute("type", request.getParameter("type"));
		
		Integer activityProductId=Integer.valueOf(request.getParameter("activityProductId"));
		Integer productId=Integer.valueOf(request.getParameter("productId"));
		
		ActivityProductCustom activityProductCustom=activityProductService.selectByActivityProductInfo(activityProductId);
		model.addAttribute("activityProductCustom", activityProductCustom);
		model.addAttribute("activityProductId", activityProductId);
		
		ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(productId);
		model.addAttribute("productCustom", productCustom);
		
		ProductPicExample productPicExample=new ProductPicExample();
		productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
		productPicExample.setOrderByClause("seq_no asc");
		List<ProductPic> productPics=productPicService.selectByExample(productPicExample);
		model.addAttribute("pics", productPics);
		
		
		ProductItemExample productItemExample=new ProductItemExample();
		productItemExample.createCriteria().andProductIdEqualTo(productId);
		List<ProductItemCustom> productItemCustoms=productItemService.selectProductItemCustomByExample(productItemExample);
		model.addAttribute("productItems", productItemCustoms);
		
		
		List<ProductProp> productProps=productService.getProductPropByProductId(productId);
		model.addAttribute("productProps", productProps);
		
		List<?> productPropValueStr=productService.getProductPropNameByProductId(productId);
		model.addAttribute("productPropValueStr", productPropValueStr);
		
		//商品详情描述信息
		if(productCustom.getProductDesc()!=null){
			String[] productDesc=productCustom.getProductDesc().split("&&&");
			model.addAttribute("productDesc", productDesc);
		}
		
		//商品详情图
		ProductDescPicExample productDescPicExample=new ProductDescPicExample();
		productDescPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
		productDescPicExample.setOrderByClause("seq_no asc");
		List<ProductDescPic> productDescPics=productDescPicService.selectByExample(productDescPicExample);
		model.addAttribute("productDescPics", productDescPics);
		
		return "/activity/seeDesignAndLawSingleProduct";
	}
	
	/**
	 * 单品商品审核入口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/activity/activityProductAudit.shtml")
	@ResponseBody
	public Map<String, Object> productAudit(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			StaffBean staffBean =this.getSessionStaffBean(request);
			Integer staffId=Integer.valueOf(staffBean.getStaffID());
			Integer type=Integer.valueOf(request.getParameter("type"));
			String status=request.getParameter("status");
			ActivityProduct activityProduct=activityProductService.selectByPrimaryKey(Integer.valueOf(request.getParameter("activityProductId")));
			
			//运营专员审核  -1:是全部活动单品审核列表的查看进入、1是运营专员列表的查看进入
			if(type.intValue()==-1 || type.intValue()==1){
				activityProduct.setOperateAuditStatus(status);
				activityProduct.setOperateAuditBy(staffId);
				if("2".equals(status)){
					activityProduct.setDesignAuditStatus("1");
				}
			}
			//设计部审核
			if(type.intValue()==3){
				activityProduct.setDesignAuditStatus(status);
				activityProduct.setDesignAuditBy(staffId);
				if("2".equals(status)){
					activityProduct.setLawAuditStatus("1");
				}
			}
			//法务部
			if(type.intValue()==4){
				activityProduct.setLawAuditStatus(status);
				activityProduct.setLawAuditBy(staffId);
				Product product=new Product();
				product.setId(activityProduct.getProductId());
				product.setUpdateBy(staffId);
				product.setUpdateDate(new Date());
				//法务通过时，同时更新商品状态
				if("2".equals(status)){
					activityProduct.setCooAuditStatus("1");
					product.setStatus("1");
					product.setOffReason("");
					product.setAuditStatus("2");
				}
				//法务驳回时，同时更新商品状态
				if("3".equals(status)){
					product.setStatus("0");
					product.setOffReason("2");
					product.setAuditStatus("3");
					product.setAuditRemarks(request.getParameter("remarks"));
				}
				productService.updateByPrimaryKeySelective(product);

				//商品上下架日志
				ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
				productUpperLowerLog.setProductId(product.getId());
				productUpperLowerLog.setStatus(product.getStatus());
				productUpperLowerLog.setType(Const.PLATFORM);
				productUpperLowerLog.setOffReason(product.getOffReason());
				productUpperLowerLog.setCreateBy(staffId);
				productUpperLowerLog.setCreateDate(new Date());
				productUpperLowerLogService.insertSelective(productUpperLowerLog);
			}
			//运营总监
			if(type.intValue()==5){
				activityProduct.setCooAuditStatus(status);
				activityProduct.setCooAuditBy(staffId);
			}
			
			if("3".equals(status)){
				activityProduct.setRefuseFlag("1");
				activityProduct.setRemarks(request.getParameter("remarks"));
			}
			activityProduct.setUpdateBy(staffId);
			activityProduct.setUpdateDate(new Date());
			activityProductService.updateByPrimaryKeySelective(activityProduct);
			
			//保存单品商品审核流水表
			ActivityProductAuditLog apal=new ActivityProductAuditLog();
			apal.setActivityProductId(activityProduct.getId());
			if(type.intValue()==-1){
				apal.setType("1");
			}else{
				apal.setType(type.toString());
			}
			apal.setStatus(status);
			apal.setCreateBy(staffId);
			apal.setCreateDate(new Date());
			apal.setDelFlag("0");
			apal.setRemarks(request.getParameter("remarks"));
			activityProductAuditLogService.insertSelective(apal);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 单品权限控制入口
	 * @param model
	 * @param request
	 * @param activityProductId
	 * @return
	 */
	@RequestMapping(value="/activity/operateAndCooSingleProductAuth.shtml")
	public String operateAndCooSingleProductAuth(Model model,HttpServletRequest request,
			Integer activityProductId){
		ActivityProductCustom activityProductCustom=activityProductService.selectByActivityProductInfo(activityProductId);
		model.addAttribute("activityProductCustom", activityProductCustom);
		ActivityAuth activityAuth= activityAuthService.selectByActivityAuthCustomExample(activityProductCustom.getActivityId());
		model.addAttribute("activityAuth", activityAuth);
		Integer productAll=activityAuthService.selectByActivityAuthProductAll(activityProductCustom.getActivityId());
		if(productAll==null||productAll==0){
			model.addAttribute("productAuthAll", 0);
		}else{
			model.addAttribute("productAuthAll", 1);
		}
		return "/activity/singleActivityProductAuth";
	}
}
