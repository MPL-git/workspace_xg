package com.jf.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.CouponExchangeCode;
import com.jf.entity.CouponExchangeCodeCustom;
import com.jf.entity.CouponExchangeCodeCustomExample;
import com.jf.entity.CouponExchangeCodeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.CommonService;
import com.jf.service.CouponExchangeCodeService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class CouponExchangeCodeController extends BaseController {

	@Autowired
	private CouponExchangeCodeService couponExchangeCodeService;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 
	 * @Title couponExchangeCodeManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年2月9日 下午5:39:58
	 */
	@RequestMapping("/couponExchangeCode/couponExchangeCodeManager.shtml")
	public ModelAndView couponExchangeCodeManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String statusPage = request.getParameter("statusPage");
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //游离码管理（查看）
			m.addObject("channelList", DataDicUtil.getTableStatus("BU_COUPON_EXCHANGE_CODE", "CHANNEL")); //推广渠道
			m.addObject("exchangeLimitList", DataDicUtil.getTableStatus("BU_COUPON_EXCHANGE_CODE", "EXCHANGE_LIMIT")); //兑换限制
			m.addObject("isExchangeList", DataDicUtil.getTableStatus("BU_COUPON_EXCHANGE_CODE", "IS_EXCHANGE")); //是否兑换
			m.addObject("statusList", DataDicUtil.getTableStatus("BU_MEMBER_COUPON", "STATUS")); //是否使用
			m.addObject("couponId", request.getParameter("couponId")); //优惠券ID
			m.setViewName("/activityAreaNew/promotionActivityArea/couponExchangeCodeList");
		}else if(!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) { //游离码管理-查看（操作作废）
			m.addObject("couponId", request.getParameter("couponId")); //优惠券ID
			m.setViewName("/activityAreaNew/promotionActivityArea/deleteCouponExchangeCode");
		}else if(!StringUtil.isEmpty(statusPage) && "3".equals(statusPage)) { //游离码管理（生成码）
			m.addObject("channelList", DataDicUtil.getTableStatus("BU_COUPON_EXCHANGE_CODE", "CHANNEL")); //推广渠道
			m.addObject("exchangeLimitList", DataDicUtil.getTableStatus("BU_COUPON_EXCHANGE_CODE", "EXCHANGE_LIMIT")); //兑换限制
			m.addObject("couponId", request.getParameter("couponId")); //优惠券ID
			m.addObject("notNum", request.getParameter("notNum")); //未生成数
			m.setViewName("/activityAreaNew/promotionActivityArea/updateCouponExchangeCode");
		}
		return m;
	}
	
	/**
	 * 
	 * @Title getCouponExchangeCodeList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年2月9日 下午6:04:07
	 */
	@ResponseBody
	@RequestMapping("/couponExchangeCode/getCouponExchangeCodeList.shtml")
	public Map<String, Object> getCouponExchangeCodeList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CouponExchangeCodeCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CouponExchangeCodeCustomExample couponExchangeCodeCustomExample = new CouponExchangeCodeCustomExample();
			CouponExchangeCodeCustomExample.CouponExchangeCodeCustomCriteria couponExchangeCodeCustomCriteria = couponExchangeCodeCustomExample.createCriteria();
			couponExchangeCodeCustomCriteria.andDelFlagEqualTo("0");
			couponExchangeCodeCustomExample.setOrderByClause(" id asc");
			couponExchangeCodeCustomExample.setLimitSize(page.getLimitSize());
			couponExchangeCodeCustomExample.setLimitStart(page.getLimitStart());
			if(!StringUtil.isEmpty(request.getParameter("couponId"))) { //优惠券ID
				couponExchangeCodeCustomCriteria.andCouponIdEqualTo(Integer.parseInt(request.getParameter("couponId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("batchNum"))) { //批号
				couponExchangeCodeCustomCriteria.andBatchNumEqualTo(request.getParameter("batchNum"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateStart"))) { //生成时间
				couponExchangeCodeCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateStart")));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd"))) { 
				couponExchangeCodeCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")));
			}
			if(!StringUtil.isEmpty(request.getParameter("channel"))) { //渠道
				couponExchangeCodeCustomCriteria.andChannelEqualTo(request.getParameter("channel"));
			}
			if(!StringUtil.isEmpty(request.getParameter("code"))) { //号码
				couponExchangeCodeCustomCriteria.andCodeEqualTo(request.getParameter("code"));
			}
			if(!StringUtil.isEmpty(request.getParameter("isExchange"))) { //是否兑换
				couponExchangeCodeCustomCriteria.andIsExchangeEqualTo(request.getParameter("isExchange"));
			}
			if(!StringUtil.isEmpty(request.getParameter("memberCouponStatus"))) { //是否使用
				couponExchangeCodeCustomCriteria.andMemberCouponStatusByEqualTo(request.getParameter("memberCouponStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("exchangeLimit"))) { //兑换限制
				couponExchangeCodeCustomCriteria.andExchangeLimitEqualTo(request.getParameter("exchangeLimit"));
			}
			totalCount = couponExchangeCodeService.countByCustomExample(couponExchangeCodeCustomExample);
			dataList = couponExchangeCodeService.selectByCustomExample(couponExchangeCodeCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title exportCouponExchangeCodeList   
	 * @Description TODO(导出excel)   
	 * @author pengl
	 * @date 2018年2月9日 下午6:36:13
	 */
	@RequestMapping("/couponExchangeCode/exportCouponExchangeCodeList.shtml")
	public void exportCouponExchangeCodeList(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CouponExchangeCodeCustomExample couponExchangeCodeCustomExample = new CouponExchangeCodeCustomExample();
			CouponExchangeCodeCustomExample.CouponExchangeCodeCustomCriteria couponExchangeCodeCustomCriteria = couponExchangeCodeCustomExample.createCriteria();
			couponExchangeCodeCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("couponId"))) { //优惠券ID
				couponExchangeCodeCustomCriteria.andCouponIdEqualTo(Integer.parseInt(request.getParameter("couponId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("batchNum"))) { //批号
				couponExchangeCodeCustomCriteria.andBatchNumEqualTo(request.getParameter("batchNum"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateStart"))) { //生成时间
				couponExchangeCodeCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateStart")));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd"))) { 
				couponExchangeCodeCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")));
			}
			if(!StringUtil.isEmpty(request.getParameter("channel"))) { //渠道
				couponExchangeCodeCustomCriteria.andChannelEqualTo(request.getParameter("channel"));
			}
			if(!StringUtil.isEmpty(request.getParameter("code"))) { //号码
				couponExchangeCodeCustomCriteria.andCodeEqualTo(request.getParameter("code"));
			}
			if(!StringUtil.isEmpty(request.getParameter("isExchange"))) { //是否兑换
				couponExchangeCodeCustomCriteria.andIsExchangeEqualTo(request.getParameter("isExchange"));
			}
			if(!StringUtil.isEmpty(request.getParameter("memberCouponStatus"))) { //是否使用
				couponExchangeCodeCustomCriteria.andMemberCouponStatusByEqualTo(request.getParameter("memberCouponStatus"));
			}
			List<CouponExchangeCodeCustom> dataList = couponExchangeCodeService.selectByCustomExample(couponExchangeCodeCustomExample);
			String[] titles = {"游离码ID","号码","是否兑换","是否使用","生成批次","生成时间","备注"};
			ExcelBean excelBean = new ExcelBean("导出游离码明细.xls","导出游离码明细", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(CouponExchangeCodeCustom couponExchangeCodeCustom : dataList) {
				String[] data = {
						couponExchangeCodeCustom.getId().toString(),
						couponExchangeCodeCustom.getCode(),
						couponExchangeCodeCustom.getIsExchangeDesc(),
						couponExchangeCodeCustom.getMemberCouponStatusDesc(),
						couponExchangeCodeCustom.getBatchNum(),
						couponExchangeCodeCustom.getCreateDate()==null?"":sdf.format(couponExchangeCodeCustom.getCreateDate()),
						couponExchangeCodeCustom.getRemarks()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title updateCouponExchangeCode   
	 * @Description TODO(游离码作废)     , MultipartFile file
	 * @author pengl    
	 * @date 2018年2月10日 上午10:52:46
	 */  
	@ResponseBody
	@RequestMapping("/couponExchangeCode/updateDelCouponExchangeCode.shtml")
	public ModelAndView updateDelCouponExchangeCode(HttpServletRequest request, MultipartFile file) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String status = request.getParameter("status");
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			Date date = new Date();
			if(!StringUtil.isEmpty(status) && !StringUtil.isEmpty(request.getParameter("couponId"))) {
				if("1".equals(status)) { //号码
					if(!StringUtil.isEmpty(request.getParameter("code"))) {
						CouponExchangeCodeExample couponExchangeCodeExample = new CouponExchangeCodeExample();
						CouponExchangeCodeExample.Criteria couponExchangeCodeCriteria = couponExchangeCodeExample.createCriteria();
						couponExchangeCodeCriteria.andDelFlagEqualTo("0").andCouponIdEqualTo(Integer.parseInt(request.getParameter("couponId")))
							.andCodeEqualTo(request.getParameter("code"));
						List<CouponExchangeCode> couponExchangeCodeList = couponExchangeCodeService.selectByExample(couponExchangeCodeExample);
						if(couponExchangeCodeList != null && couponExchangeCodeList.size() > 0) {
							CouponExchangeCode couponExchangeCode = couponExchangeCodeList.get(0);
							if(!StringUtil.isEmpty(couponExchangeCode.getIsExchange()) && "1".equals(couponExchangeCode.getIsExchange())) {
								code = StateCode.JSON_AJAX_ERROR.getStateCode();
								msg = "此号码已经兑换，不能作废！";
							}else {
								CouponExchangeCode couponExchangeCodeNew = new CouponExchangeCode();
								couponExchangeCodeNew.setId(couponExchangeCode.getId());
								couponExchangeCodeNew.setDelFlag("1");
								couponExchangeCodeNew.setUpdateBy(staffId);
								couponExchangeCodeNew.setUpdateDate(date);
								couponExchangeCodeService.updateByPrimaryKeySelective(couponExchangeCodeNew);
							}
						}else {
							code = StateCode.JSON_AJAX_ERROR.getStateCode();
							msg = "此号码不存在，请重新输入！";
						}
					}
				}else if("2".equals(status)) { //生成批次
					if(!StringUtil.isEmpty(request.getParameter("batchNum"))) {
						CouponExchangeCodeExample couponExchangeCodeExample = new CouponExchangeCodeExample();
						CouponExchangeCodeExample.Criteria couponExchangeCodeCriteria = couponExchangeCodeExample.createCriteria();
						couponExchangeCodeCriteria.andDelFlagEqualTo("0").andCouponIdEqualTo(Integer.parseInt(request.getParameter("couponId")))
							.andBatchNumEqualTo(request.getParameter("batchNum")).andIsExchangeEqualTo("0");
						List<CouponExchangeCode> couponExchangeCodeList = couponExchangeCodeService.selectByExample(couponExchangeCodeExample);
						if(couponExchangeCodeList != null && couponExchangeCodeList.size() > 0) {
							CouponExchangeCode couponExchangeCodeNew = new CouponExchangeCode();
							couponExchangeCodeNew.setDelFlag("1");
							couponExchangeCodeNew.setUpdateBy(staffId);
							couponExchangeCodeNew.setUpdateDate(date);
							couponExchangeCodeService.updateByExampleSelective(couponExchangeCodeNew, couponExchangeCodeExample);
						}else {
							code = StateCode.JSON_AJAX_ERROR.getStateCode();
							msg = "此生成批次未兑换游离码不存在，请重新输入！";
						}
					}
				}else if("3".equals(status)) { //文件
					List<String> codeList = new ArrayList<String>();
					InputStreamReader reader = new InputStreamReader(file.getInputStream());
					BufferedReader br = new BufferedReader(reader); 
					String lineTxt = null;
					while ((lineTxt = br.readLine()) != null) {
						if(!"".equals(lineTxt)) {
							codeList.add(lineTxt);
						}
					}
					br.close(); 
					CouponExchangeCodeExample couponExchangeCodeExample = new CouponExchangeCodeExample();
					CouponExchangeCodeExample.Criteria couponExchangeCodeCriteria = couponExchangeCodeExample.createCriteria();
					couponExchangeCodeCriteria.andDelFlagEqualTo("0").andCouponIdEqualTo(Integer.parseInt(request.getParameter("couponId")))
					.andIsExchangeEqualTo("0");
					if(codeList.size() > 0) {
						couponExchangeCodeCriteria.andCodeIn(codeList);
						CouponExchangeCode couponExchangeCodeNew = new CouponExchangeCode();
						couponExchangeCodeNew.setDelFlag("1");
						couponExchangeCodeNew.setUpdateBy(staffId);
						couponExchangeCodeNew.setUpdateDate(date);
						couponExchangeCodeService.updateByExampleSelective(couponExchangeCodeNew, couponExchangeCodeExample);
					}else {
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = "文件内容为空，请重新上传！";
					}
				}
			}
			if("".equals(code)) {
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title updateAddCouponExchangeCode   
	 * @Description TODO(生成码)   
	 * @author pengl
	 * @date 2018年2月10日 上午11:22:39
	 */
	@ResponseBody
	@RequestMapping("/couponExchangeCode/updateAddCouponExchangeCode.shtml")
	public ModelAndView updateAddCouponExchangeCode(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg = null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Integer exchangeCode = commonService.getSequence("exchangeCode"); //批号自增序列
			String createNum = request.getParameter("createNum"); //生成数量
			String channel = request.getParameter("channel"); //渠道
			String exchangeLimit = request.getParameter("exchangeLimit"); //兑换条件
			String remarks = request.getParameter("remarks"); //备注
			String couponId = request.getParameter("couponId"); //优惠券id
			CouponExchangeCodeExample couponExchangeCodeExample = new CouponExchangeCodeExample();
			CouponExchangeCodeExample.Criteria couponExchangeCodeCriteria = couponExchangeCodeExample.createCriteria();
			couponExchangeCodeCriteria.andDelFlagEqualTo("0").andCouponIdIsNull();
			couponExchangeCodeExample.setOrderByClause(" id asc");
			if(!StringUtil.isEmpty(createNum)) {
				couponExchangeCodeExample.setLimitStart(0);
				couponExchangeCodeExample.setLimitSize(Integer.parseInt(createNum));
			}
			List<CouponExchangeCode> couponExchangeCodeList = couponExchangeCodeService.selectByExample(couponExchangeCodeExample);
			List<Integer> couponExchangeCodeIdList = new ArrayList<Integer>();
			if(couponExchangeCodeList != null && couponExchangeCodeList.size() >= Integer.parseInt(createNum)) {
				for(CouponExchangeCode couponExchangeCode : couponExchangeCodeList) {
					couponExchangeCodeIdList.add(couponExchangeCode.getId());
				}
				couponExchangeCodeCriteria.andIdIn(couponExchangeCodeIdList);
				CouponExchangeCode couponExchangeCodeNew = new CouponExchangeCode();
				couponExchangeCodeNew.setBatchNum(sdf.format(date)+exchangeCode);
				couponExchangeCodeNew.setChannel(channel);
				couponExchangeCodeNew.setExchangeLimit(exchangeLimit);
				couponExchangeCodeNew.setCouponId(Integer.parseInt(couponId));
				couponExchangeCodeNew.setIsExchange("0");
				couponExchangeCodeNew.setCreateBy(staffId);
				couponExchangeCodeNew.setCreateDate(date);
				couponExchangeCodeNew.setUpdateBy(staffId);
				couponExchangeCodeNew.setUpdateDate(date);
				couponExchangeCodeNew.setRemarks(remarks);
				couponExchangeCodeNew.setDelFlag("0");
				couponExchangeCodeService.updateByExampleSelective(couponExchangeCodeNew, couponExchangeCodeExample);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}else {
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = "游离码未生成数量不够，请先创建未生成的游离码！";
			}
		} catch (NumberFormatException e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
}
