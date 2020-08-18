package com.jf.controller;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/violateOrder")
public class ViolateOrderController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ViolateOrderController.class);

	@Resource
	private ViolateOrderService violateOrderService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private ViolateComplainOrderService violateComplainOrderService;
	
	@Resource
	private ComplainOrderPicService complainOrderPicService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private MchtDepositService mchtDepositService;
	
	@Resource
	private MchtDepositDtlService mchtDepositDtlService;
	
	
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 我的违规首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/violateOrderIndex")
	public String violateOrderIndex(Model model, HttpServletRequest request) {
		List<String> violateTypes = violateOrderService.getViolateTypesByMchtId(this.getMchtInfo().getId());
		model.addAttribute("violateTypes", violateTypes);
		model.addAttribute("violateOrderStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "STATUS"));
		model.addAttribute("againAuditStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "AGAIN_AUDIT_STATUS"));
		return "violateOrder/violateOrderIndex";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getViolateOrderList")
	@ResponseBody
	public ResponseMsg getViolateOrderList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
		ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
		violateOrderCustomExample.setOrderByClause("t.violate_date desc");
		violateOrderCustomCriteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		violateOrderCustomCriteria.andAuditStatusEqualTo("1");
		if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
			violateOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		}else{
			violateOrderCustomCriteria.andDelFlagEqualTo("0");
		}
		String searchOrderCode = request.getParameter("search_orderCode");
		String searchViolateType = request.getParameter("search_violateType");
		String searchActivity = request.getParameter("search_activity");
		String searchStatus = request.getParameter("search_status");
		if(!StringUtil.isEmpty(searchOrderCode)){
			violateOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode.trim());
		}
		if(!StringUtil.isEmpty(searchViolateType)){
			violateOrderCustomCriteria.andViolateTypeEqualTo(searchViolateType);
		}
		if(!StringUtil.isEmpty(searchActivity)){
			violateOrderCustomCriteria.andActivityIdEqualTo(Integer.parseInt(searchActivity));
		}
		if(!StringUtil.isEmpty(searchStatus)){
			if(searchStatus.indexOf("999")>=0){
				violateOrderCustomCriteria.andAgainAuditStatusEqualTo(searchStatus.substring(3));
			}else{
				violateOrderCustomCriteria.andStatusEqualTo(searchStatus);
			}
		}
		String violateDateBegin = request.getParameter("violateDateBegin");
		String violateDateEnd = request.getParameter("violateDateEnd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtil.isEmpty(violateDateBegin)){
			violateOrderCustomCriteria.andViolateDateGreaterThanOrEqualTo(sdf.parse((violateDateBegin+" 00:00:00")));
		}
		if(!StringUtil.isEmpty(violateDateBegin)){
			violateOrderCustomCriteria.andViolateDateLessThanOrEqualTo(sdf.parse((violateDateEnd+" 23:59:59")));
		}
		int totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
		violateOrderCustomExample.setLimitStart(page.getLimitStart());
		violateOrderCustomExample.setLimitSize(page.getLimitSize());
		List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
		returnData.put("Rows", violateOrderCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}
	
	/**
	 * 违规详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/violateOrderView")
	public String violateOrderView(Model model,HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(id);
		String statusDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "STATUS", violateOrder.getStatus());
		SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
		String violateTypeDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType());
		String violateActionDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction());
		model.addAttribute("mchtInfo", this.getMchtInfo());
		model.addAttribute("violateOrder", violateOrder);
		if(subOrder!=null){
			model.addAttribute("subOrderCode", subOrder.getSubOrderCode());
		}
		model.addAttribute("statusDesc", statusDesc);
		model.addAttribute("violateTypeDesc", violateTypeDesc);
		model.addAttribute("violateActionDesc", violateActionDesc);
		boolean allowComplain = false;
		if(violateOrder.getStatus().equals("1") || violateOrder.getStatus().equals("3")){
			if(StringUtil.isEmpty(violateOrder.getComplainInfoStatus()) || violateOrder.getComplainInfoStatus().equals("1")){
				if(violateOrder.getComplainEndDate()!=null && violateOrder.getComplainEndDate().after(new Date())){
					allowComplain = true;
				}
			}
		}
		model.addAttribute("allowComplain", allowComplain);
		boolean showComplain = true;
		if(violateOrder.getStatus().equals("2") || violateOrder.getStatus().equals("4")){//不可申诉，超期未申诉
			showComplain = false;
		}
		model.addAttribute("showComplain", showComplain);
		ViolateComplainOrderCustomExample example = new ViolateComplainOrderCustomExample();
		example.setOrderByClause("t.id desc");
		ViolateComplainOrderCustomExample.ViolateComplainOrderCustomCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andViolateOrderIdEqualTo(id);
		List<ViolateComplainOrderCustom> violateComplainOrderCustoms = violateComplainOrderService.selectByViolateComplainOrderCustomExample(example);
		for(ViolateComplainOrderCustom violateComplainOrderCustom:violateComplainOrderCustoms){
			ComplainOrderPicExample cpe = new ComplainOrderPicExample();
			ComplainOrderPicExample.Criteria c = cpe.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andComplainOrderIdEqualTo(violateComplainOrderCustom.getId());
			c.andOperTypeEqualTo("2");//商家
			List<ComplainOrderPic> complainOrderPics = complainOrderPicService.selectByExample(cpe);
			violateComplainOrderCustom.setComplainOrderPics(complainOrderPics);
			
			ComplainOrderPicExample ce = new ComplainOrderPicExample();
			ComplainOrderPicExample.Criteria ca = ce.createCriteria();
			ca.andDelFlagEqualTo("0");
			ca.andComplainOrderIdEqualTo(violateComplainOrderCustom.getId());
			ca.andOperTypeEqualTo("1");//平台
			List<ComplainOrderPic> platformPics = complainOrderPicService.selectByExample(ce);
			violateComplainOrderCustom.setPlatformPics(platformPics);
		}
		model.addAttribute("violateComplainOrderCustoms", violateComplainOrderCustoms);
		if(!StringUtil.isEmpty(violateOrder.getEnclosure())){
			model.addAttribute("enclosureName", violateOrder.getEnclosure().substring(violateOrder.getEnclosure().lastIndexOf("/")+1));
		}
		return "violateOrder/violateOrderView";
	}
	
	/**
	 * 保存商家提交的申诉
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/saveComplain")
	@ResponseBody
	public ResponseMsg saveComplain(HttpServletRequest request) {
		Integer violateOrderId = Integer.parseInt(request.getParameter("violateOrderId"));
		ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(violateOrderId);
		String content = request.getParameter("content");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String imgs = request.getParameter("imgs");
		ViolateComplainOrder violateComplainOrder = new ViolateComplainOrder();
		violateComplainOrder.setViolateOrderId(violateOrderId);
		violateComplainOrder.setContent(content);
		violateComplainOrder.setPhone(phone);
		violateComplainOrder.setEmail(email);
		violateComplainOrder.setStatus(status);
		if(violateOrder.getOrderSource().equals("2")){//人工
			violateComplainOrder.setProcesBy(violateOrder.getCreateBy());
		}
		violateComplainOrder.setCreateBy(this.getMchtInfo().getId());
		violateComplainOrder.setCreateDate(new Date());
		violateComplainOrder.setDelFlag("0");
		
		violateOrder.setComplainInfoStatus("2");//待审核
		violateOrder.setComplainDate(new Date());
		violateOrder.setStatus(ViolateOrderCustom.VIOLATE_STATUS_COMPLAIN_ING);//申诉中
		violateOrder.setUpdateBy(this.getMchtInfo().getId());
		violateOrder.setUpdateDate(new Date());
		//保存申诉单资料图
		List<ComplainOrderPic> complainOrderPics = new ArrayList<ComplainOrderPic>();
		if(!StringUtil.isEmpty(imgs)){
			String[] imgsArray = imgs.split(",");
			for(int i=0;i<imgsArray.length;i++){
				ComplainOrderPic complainOrderPic = new ComplainOrderPic();
				complainOrderPic.setCreateDate(new Date());
				complainOrderPic.setCreateBy(this.getMchtInfo().getId());
				complainOrderPic.setOperType("2");
				complainOrderPic.setDelFlag("0");
				complainOrderPic.setPic(imgsArray[i]);
				complainOrderPics.add(complainOrderPic);
			}
		}
		violateOrderService.saveViolateComplainOrder(violateOrder,violateComplainOrder,complainOrderPics);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("violateComplainOrderId", violateComplainOrder.getId());
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 扣款记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/debitRecordIndex")
	public String debitRecordIndex(Model model, HttpServletRequest request) {
		return "violateOrder/debitRecordIndex";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getDebitRecordList")
	@ResponseBody
	public ResponseMsg getDebitRecordList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtDepositExample example = new MchtDepositExample();
		MchtDepositExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		MchtDeposit mchtDeposit = new MchtDeposit();
		List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(example);
		if(mchtDeposits!=null && mchtDeposits.size()>0){
			mchtDeposit = mchtDeposits.get(0);
		}
		MchtDepositDtlCustomExample e = new MchtDepositDtlCustomExample();
		MchtDepositDtlCustomExample.MchtDepositDtlCustomCriteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andDepositIdEqualTo(mchtDeposit.getId());
		List<String> txnTypes = new ArrayList<String>();
		txnTypes.add("E");
		txnTypes.add("F");
		c.andTxnTypeIn(txnTypes);
		c.andBizTypeEqualTo("2");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String violateDateBegin = request.getParameter("violateDateBegin");
		if(!StringUtil.isEmpty(violateDateBegin)){
			c.andCreateDateGreaterThanOrEqualTo(sdf.parse((violateDateBegin+" 00:00:00")));
		}
		String violateDateEnd = request.getParameter("violateDateEnd");
		if(!StringUtil.isEmpty(violateDateEnd)){
			c.andCreateDateLessThanOrEqualTo(sdf.parse((violateDateEnd+" 23:59:59")));
		}
		int totalCount = mchtDepositDtlService.countMchtDepositDtlCustomByExample(e);
		e.setLimitStart(page.getLimitStart());
		e.setLimitSize(page.getLimitSize());
		List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectMchtDepositDtlCustomByExample(e);
		returnData.put("Rows", mchtDepositDtlCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 扣款记录（详情）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/debitRecordView")
	public String debitRecordView(Model model,HttpServletRequest request,Page page) {
		Integer activityId = Integer.valueOf(request.getParameter("activityId"));
		Activity activity = activityService.findById(activityId);
		String totalDeductions = request.getParameter("totalDeductions");
		model.addAttribute("mchtInfo", this.getMchtInfo());
		model.addAttribute("activityId", activityId);
		model.addAttribute("activityName", activity.getName());
		model.addAttribute("totalDeductions", totalDeductions);
		return "violateOrder/debitRecordView";
	}
	
	/**
	 * 扣款记录（详情）数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getDebitRecordViewData")
	@ResponseBody
	public ResponseMsg getDebitRecordViewData(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Integer activityId = Integer.parseInt(request.getParameter("activityId"));
		ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
		ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
		violateOrderCustomCriteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
			violateOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		}else{
			violateOrderCustomCriteria.andDelFlagEqualTo("0");
		}
		List<Integer> subOrderIds = orderDtlService.getSubOrderIdsByActivityId(activityId);
		violateOrderCustomCriteria.andSubOrderIdIn(subOrderIds);
		int totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
		violateOrderCustomExample.setLimitStart(page.getLimitStart());
		violateOrderCustomExample.setLimitSize(page.getLimitSize());
		List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
		returnData.put("Rows", violateOrderCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 商家申诉页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/toComplain")
	public String toComplain(Model model,HttpServletRequest request) {
		String violateOrderId = request.getParameter("violateOrderId");
		model.addAttribute("violateOrderId", violateOrderId);
		return "violateOrder/toComplain";
	}
}
