package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.MchtInfoService;
import com.jf.service.PayToMchtLogService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PayToMchtLogController extends BaseController {
	
	@Resource
	private PayToMchtLogService payToMchtLogService;
	
	@Resource
	private MchtInfoService mchtInfoService;

	@Resource
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 向商家付款记录列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payToMchtLog/list.shtml")
	public ModelAndView payToMchtLogList(HttpServletRequest request) {
		String rtPage = "/payToMchtLog/payToMchtLogList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("pay_date_begin", request.getParameter("pay_date_begin"));
		resMap.put("pay_date_end", request.getParameter("pay_date_end"));
		resMap.put("mchtId", request.getParameter("mchtId"));
		resMap.put("typeList", DataDicUtil.getStatusList("BU_PAY_TO_MCHT_LOG", "TYPE"));
		resMap.put("searchType", request.getParameter("type"));
		resMap.put("mchtCode", request.getParameter("mchtCode"));
		
		 ProductTypeExample productTypeExample = new ProductTypeExample();
		 ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		 productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		 List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		 resMap.put("productTypeList", productTypeList); //1级类目
		 
		 String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		 if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			  String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			   if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 向商家付款记录列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payToMchtLog/data.shtml")
	@ResponseBody
	public Map<String, Object> payToMchtLogData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			PayToMchtLogCustomExample example = new PayToMchtLogCustomExample();
			example.setOrderByClause("t.pay_date desc");
			PayToMchtLogCustomExample.PayToMchtLogCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			String payCode = request.getParameter("payCode");
			String searchType = request.getParameter("type");
			String searchCompanyName = request.getParameter("companyName");
			String searchMchtId = request.getParameter("mchtId");
			String settleAmount = request.getParameter("settleAmount");
			String mchtCode = request.getParameter("mchtCode");
			String confirmStatus = request.getParameter("confirmStatus");
			String productTypeId=request.getParameter("productTypeId");
			String mchtName=request.getParameter("mchtName");
			String productBrandName=request.getParameter("productBrandName");
			if(!StringUtil.isEmpty(payCode)){
				criteria.andPayCodeEqualTo(payCode);
			}
			if(!StringUtil.isEmpty(searchType)){
				criteria.andTypeEqualTo(searchType);
			}
			if(!StringUtil.isEmpty(searchMchtId)){
				criteria.andMchtIdEqualTo(Integer.parseInt(searchMchtId));
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("pay_date_begin")) ){
				criteria.andPayDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("pay_date_end")) ){
				criteria.andPayDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(settleAmount) ){
				if(settleAmount.equals("0")){
					criteria.andSettleAmountEqualTo(new BigDecimal(0));
					criteria.andPayAmountEqualTo(new BigDecimal(0));
				}else{
					criteria.andSettleAmountOrPayAmountGreaterThanZero();
					//criteria.andSettleAmountGreaterThan(new BigDecimal(0));
					//PayToMchtLogCustomExample.PayToMchtLogCustomCriteria criteria2 = example.createCriteria();
					//criteria2.andPayAmountGreaterThan(new BigDecimal(0));
					//example.or(criteria2);
				}
			}
			if(!StringUtil.isEmpty(confirmStatus)){
				criteria.andConfirmStatusEqualTo(confirmStatus);
			}
			if(!StringUtil.isEmpty(productTypeId)){
				criteria.andProductTypeIdEqualTo(productTypeId);
			}
			if(!StringUtil.isEmpty(mchtName)){
				criteria.andMchtNameLikeTo(mchtName);
			}
			if(!StringUtil.isEmpty(productBrandName)){
				criteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				criteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			totalCount = payToMchtLogService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<PayToMchtLogCustom> payToMchtLogCustoms = payToMchtLogService.selectByExample(example);
			resMap.put("Rows", payToMchtLogCustoms);
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
	@RequestMapping("/payToMchtLog/export.shtml")
	public void exportPayToMchtLog(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			PayToMchtLogCustomExample example = new PayToMchtLogCustomExample();
			example.setOrderByClause("t.pay_date desc");
			PayToMchtLogCustomExample.PayToMchtLogCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			String payCode = request.getParameter("payCode");
			String searchType = request.getParameter("type");
			String searchCompanyName = request.getParameter("companyName");
			String searchMchtId = request.getParameter("mchtId");
			String settleAmount = request.getParameter("settleAmount");
			String mchtCode = request.getParameter("mchtCode");
			String confirmStatus = request.getParameter("confirmStatus");
			String productTypeId=request.getParameter("productTypeId");
			String mchtName=request.getParameter("mchtName");
			String productBrandName=request.getParameter("productBrandName");
			if(!StringUtil.isEmpty(payCode)){
				criteria.andPayCodeEqualTo(payCode);
			}
			if(!StringUtil.isEmpty(searchType)){
				criteria.andTypeEqualTo(searchType);
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				criteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(searchMchtId)){
				criteria.andMchtIdEqualTo(Integer.parseInt(searchMchtId));
			}
			if(!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("pay_date_begin")) ){
				criteria.andPayDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("pay_date_end")) ){
				criteria.andPayDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(settleAmount) ){
				if(settleAmount.equals("0")){
					criteria.andSettleAmountEqualTo(new BigDecimal(0));
					criteria.andPayAmountEqualTo(new BigDecimal(0));
				}else{
					criteria.andSettleAmountGreaterThan(new BigDecimal(0));
					PayToMchtLogCustomExample.PayToMchtLogCustomCriteria criteria2 = example.createCriteria();
					criteria2.andPayAmountGreaterThan(new BigDecimal(0));
					example.or(criteria2);
				}
			}
			if(!StringUtil.isEmpty(confirmStatus)){
				criteria.andConfirmStatusEqualTo(confirmStatus);
			}
			if(!StringUtil.isEmpty(productTypeId)){
				criteria.andProductTypeIdEqualTo(productTypeId);
			}

			if(!StringUtil.isEmpty(mchtName)){
				criteria.andMchtNameLikeTo(mchtName);
			}
			if(!StringUtil.isEmpty(productBrandName)){
				criteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			List<PayToMchtLogCustom> payToMchtLogCustoms = payToMchtLogService.selectByExample(example);
			String[] titles = {"付款ID","类型","结算单ID","商家编号","店铺名","公司名称","付款日期","结算单日期","结算金额(元)","转保证金抵缴(元)","以前实付金额(元)","还需支付(元)","本次实付金额(元)","付款人","是否确认","主营类目"};
			ExcelBean excelBean = new ExcelBean("导出向商家付款记录.xls",
					"导出向商家付款记录", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(PayToMchtLogCustom payToMchtLogCustom:payToMchtLogCustoms){
				String date = "";
				if(StringUtils.isEmpty(payToMchtLogCustom.getBeginDate()) || StringUtils.isEmpty(payToMchtLogCustom.getEndDate())){
					date = "";
				}else{
					date = df.format(payToMchtLogCustom.getBeginDate())+"到"+df.format(payToMchtLogCustom.getEndDate());
				}
				String[] data = {
					payToMchtLogCustom.getId().toString(),
					payToMchtLogCustom.getTypeDesc(),
					payToMchtLogCustom.getMchtSettleOrderId()==null?"":payToMchtLogCustom.getMchtSettleOrderId().toString(),
					payToMchtLogCustom.getMchtCode(),
					payToMchtLogCustom.getShopName()==null?"":payToMchtLogCustom.getShopName(),
					payToMchtLogCustom.getCompanyName(),
					df.format(payToMchtLogCustom.getPayDate()),
					date,
					payToMchtLogCustom.getSettleAmount()==null?"":payToMchtLogCustom.getSettleAmount().toString(),
					payToMchtLogCustom.getDepositAmount()==null?"":payToMchtLogCustom.getDepositAmount().toString(),
					payToMchtLogCustom.getBeforePayAmount()==null?"":payToMchtLogCustom.getBeforePayAmount().toString(),
					payToMchtLogCustom.getNeedPayAmount()==null?"":payToMchtLogCustom.getNeedPayAmount().toString(),
					payToMchtLogCustom.getPayAmount()==null?"":payToMchtLogCustom.getPayAmount().toString(),
					payToMchtLogCustom.getPayStaffName(),
					payToMchtLogCustom.getConfirmStatusDesc(),
					payToMchtLogCustom.getProductName()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 确认付款状态
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payToMchtLog/toConfirm.shtml")
	public ModelAndView toConfirm(HttpServletRequest request) {
		String rtPage = "/payToMchtLog/toConfirm";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String idsStr = request.getParameter("ids");
		BigDecimal totalSettleAmount;
		BigDecimal totalDepositAmount;
		BigDecimal totalPayAmount;
		String[] idsArray = idsStr.split(",");
		int totalCount;
		if(idsArray.length>1){
			totalCount = idsArray.length;
			totalSettleAmount = new BigDecimal(request.getParameter("totalSettleAmount"));
			totalDepositAmount = new BigDecimal(request.getParameter("totalDepositAmount"));
			totalPayAmount = new BigDecimal(request.getParameter("totalPayAmount"));
		}else{
			PayToMchtLog payToMchtLog = payToMchtLogService.selectByPrimaryKey(Integer.parseInt(idsStr));
			totalCount = 1;
			totalSettleAmount = payToMchtLog.getSettleAmount();
			totalDepositAmount = payToMchtLog.getDepositAmount();
			totalPayAmount = payToMchtLog.getPayAmount();
		}
		resMap.put("ids", idsStr);
		resMap.put("totalSettleAmount", totalSettleAmount);
		resMap.put("totalDepositAmount", totalDepositAmount);
		resMap.put("totalPayAmount", totalPayAmount);
		resMap.put("totalCount", totalCount);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 更新状态
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payToMchtLog/updateConfirmStatus.shtml")
	@ResponseBody
	public Map<String, Object> updateConfirmStatus(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String idsStr = request.getParameter("ids");
			String[] idsArray = idsStr.split(",");
			if(idsArray.length>1){
				for(int i=0;i<idsArray.length;i++){
					PayToMchtLog payToMchtLog = payToMchtLogService.selectByPrimaryKey(Integer.parseInt(idsArray[i]));
					payToMchtLog.setConfirmStatus("1");
					payToMchtLogService.updateByPrimaryKey(payToMchtLog);
				}
			}else{
				PayToMchtLog payToMchtLog = payToMchtLogService.selectByPrimaryKey(Integer.parseInt(idsStr));
				payToMchtLog.setConfirmStatus("1");
				payToMchtLogService.updateByPrimaryKey(payToMchtLog);
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 手工添加付款记录
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payToMchtLog/toAdd.shtml")
	public ModelAndView toAdd(HttpServletRequest request) {
		String rtPage = "/payToMchtLog/add";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("typeList", DataDicUtil.getStatusList("BU_PAY_TO_MCHT_LOG", "TYPE"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 获取商家信息
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/payToMchtLog/mchtInfoData.shtml")
	public Map<String, Object> mchtInfoData(HttpServletRequest request,HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap, Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		String shortName = "";
		String mchtCode = "";
		try {
			if (paramMap.get("condition") != null) {
				JSONArray fromObject = JSONArray.fromObject(paramMap.get("condition"));
				for(int i=0;i<fromObject.size();i++){
					JSONObject jo = (JSONObject)fromObject.get(i);
					if(jo.getString("field").equals("shortName")){
						shortName = jo.getString("value");
					}
					if(jo.getString("field").equals("mchtCode")){
						mchtCode = jo.getString("value");
					}
				}
			}
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			MchtInfoExample.Criteria criteria = mchtInfoExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			mchtInfoExample.setLimitStart(page.getLimitStart());
			mchtInfoExample.setLimitSize(page.getLimitSize());
			if (!StringUtil.isEmpty(shortName)) {
				criteria.andShortNameLike("%"+shortName+"%");
			}
			if (!StringUtil.isEmpty(mchtCode)) {
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			totalCount = mchtInfoService.countByExample(mchtInfoExample);
			List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
			resMap.put("Rows", mchtInfos);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payToMchtLog/save.shtml")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String mchtId = request.getParameter("mchtId");
			String type = request.getParameter("type");
			String payAmount = request.getParameter("payAmount");
			String payCode = request.getParameter("payCode");
			String payDate = request.getParameter("payDate");
			PayToMchtLog payToMchtLog = new PayToMchtLog();
			payToMchtLog.setDelFlag("0");
			payToMchtLog.setCreateDate(new Date());
			payToMchtLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			payToMchtLog.setMchtId(Integer.parseInt(mchtId));
			payToMchtLog.setConfirmStatus("1");
			payToMchtLog.setType(type);
			if(type.equals("4")){//退回保证金
				payToMchtLog.setPayAmount(new BigDecimal(payAmount).negate());
			}else{
				payToMchtLog.setPayAmount(new BigDecimal(payAmount));
			}
			payToMchtLog.setPayCode(payCode);
			payToMchtLog.setPayDate(DateUtil.getDateByFormat(payDate));
			payToMchtLog.setPayStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			payToMchtLog.setSettleAmount(new BigDecimal(payAmount));
			payToMchtLogService.insertSelective(payToMchtLog);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
}
