package com.jf.controller;

import com.jf.bean.ExcelBean;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ExportExcelRecordMapper;
import com.jf.dao.InformationMapper;
import com.jf.entity.*;
import com.jf.service.SubDepositOrderService;
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
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SubDepositOrderController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SubDepositOrderController.class);

	@Resource
	private SubDepositOrderService subDepositOrderService;
	
	@Resource
	private ExportExcelRecordMapper exportExcelRecordMapper;
	
	@Resource
	private InformationMapper informationMapper;
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 订单管理首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subDepositOrder/subDepositOrderIndex")
	public String subDepositOrderIndex(Model model, HttpServletRequest request) {
		List<String> brandNames = subDepositOrderService.getBrandNamesByMchtId(this.getSessionMchtInfo(request).getId());
		model.addAttribute("brandNames", brandNames);
		model.addAttribute("statusList", DataDicUtil.getStatusList("BU_SUB_DEPOSIT_ORDER", "STATUS"));
		return "subDepositOrder/subDepositOrderIndex";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/subDepositOrder/getSubDepositOrderList")
	@ResponseBody
	public ResponseMsg getSubDepositOrderList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SubDepositOrderCustomExample example = new SubDepositOrderCustomExample();
		SubDepositOrderCustomExample.SubDepositOrderCustomCriteria  criteria = example.createCriteria();
		example.setOrderByClause("t.id desc");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		criteria.andDelFlagEqualTo("0");
		if (!StringUtil.isEmpty(request.getParameter("search_subDepositOrderCode"))) {
			criteria.andSubDepositOrderCodeEqualTo(request.getParameter("search_subDepositOrderCode").trim());
		}
		if (!StringUtil.isEmpty(request.getParameter("search_productCode"))) {
			criteria.andProductCodeEqualTo(request.getParameter("search_productCode").trim());
		}
		if (!StringUtil.isEmpty(request.getParameter("search_brandName"))) {
			criteria.andBrandNameEqualTo(request.getParameter("search_brandName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_status"))) {
			criteria.andStatusEqualTo(request.getParameter("search_status"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
			criteria.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
		}
		if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
			criteria.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
		}
		
		int totalCount = subDepositOrderService.countSubDepositOrderCustomByExample(example);
		example.setLimitStart(page.getLimitStart());
		example.setLimitSize(page.getLimitSize());
		List<SubDepositOrderCustom> subDepositOrderCustoms = subDepositOrderService.selectSubDepositOrderCustomByExample(example);
		returnData.put("Rows", subDepositOrderCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 导出订单excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/subDepositOrder/exportSubDepositOrder")
	public void exportSubDepositOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SubDepositOrderCustomExample example = new SubDepositOrderCustomExample();
		SubDepositOrderCustomExample.SubDepositOrderCustomCriteria  criteria = example.createCriteria();
		example.setOrderByClause("t.id desc");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		criteria.andDelFlagEqualTo("0");
		if (!StringUtil.isEmpty(request.getParameter("search_subDepositOrderCode"))) {
			criteria.andSubDepositOrderCodeEqualTo(request.getParameter("search_subDepositOrderCode"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_productCode"))) {
			criteria.andProductCodeEqualTo(request.getParameter("search_productCode"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_brandName"))) {
			criteria.andBrandNameEqualTo(request.getParameter("search_brandName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_status"))) {
			criteria.andStatusEqualTo(request.getParameter("search_status"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
			criteria.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
		}
		if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
			criteria.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
		}
		
		List<SubDepositOrderCustom> subDepositOrderCustoms = subDepositOrderService.selectSubDepositOrderCustomByExample(example);
		String[] titles = { "预售订单编号", "商品名称", "货号","品牌名称","商品属性描述","sku","预售价","数量","定金","抵用金额","定金状态","实付金额","下单时间","付款时间"};
		ExcelBean excelBean = new ExcelBean("导出订单" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
				"导出预售定金订单", titles);
		List<String[]> datas = new ArrayList<>();
		for (SubDepositOrderCustom subDepositOrderCustom : subDepositOrderCustoms) {
			String[] data = {
					subDepositOrderCustom.getSubDepositOrderCode(),
					subDepositOrderCustom.getProductName(),
					subDepositOrderCustom.getArtNo(),
					subDepositOrderCustom.getBrandName(),
					subDepositOrderCustom.getProductPropDesc(),
					subDepositOrderCustom.getSku()==null?"":subDepositOrderCustom.getSku(),
					subDepositOrderCustom.getSalePrice().toString(),
					subDepositOrderCustom.getQuantity().toString(),
					subDepositOrderCustom.getDeposit().toString(),
					subDepositOrderCustom.getDeductAmount().toString(),
					subDepositOrderCustom.getStatusDesc(),
					subDepositOrderCustom.getPayAmount()==null?"":subDepositOrderCustom.getPayAmount().toString(),
					sdf.format(subDepositOrderCustom.getCreateDate()),
					subDepositOrderCustom.getPayDate()==null?"":sdf.format(subDepositOrderCustom.getPayDate())
			};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
		ExportExcelRecordExample e = new ExportExcelRecordExample();
		ExportExcelRecordExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andTypeEqualTo("2");//预售定金导出
		List<ExportExcelRecord> exportExcelRecords = exportExcelRecordMapper.selectByExample(e);
		if(exportExcelRecords!=null && exportExcelRecords.size()>0){
			ExportExcelRecord exportExcelRecord = exportExcelRecords.get(0);
			exportExcelRecord.setLastExportDate(new Date());
			exportExcelRecord.setExportCount(exportExcelRecord.getExportCount()+1);
			exportExcelRecord.setUpdateBy(this.getSessionMchtInfo(request).getId());
			exportExcelRecord.setUpdateDate(new Date());
			exportExcelRecordMapper.updateByPrimaryKeySelective(exportExcelRecord);
		}else{
			ExportExcelRecord eer = new ExportExcelRecord();
			eer.setDelFlag("0");
			eer.setMchtId(this.getSessionMchtInfo(request).getId());
			eer.setType("2");//预售定金导出
			eer.setLastExportDate(new Date());
			eer.setExportCount(1);
			eer.setCreateBy(this.getSessionMchtInfo(request).getId());
			eer.setCreateDate(new Date());
			exportExcelRecordMapper.insertSelective(eer);
		}
	}
	
	/**
	 * 醒购预售商家协议页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subDepositOrder/toAgreement")
	public String toAgreement(Model model, HttpServletRequest request) {
		InformationExample e = new InformationExample();
//		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTitleEqualTo("醒购预售规则");
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdEqualTo(193);
		List<Information> informations = informationMapper.selectByExampleWithBLOBs(e);
		if(informations!=null && informations.size()>0){
			model.addAttribute("content", informations.get(0).getContent());
		}
		return "subDepositOrder/toAgreement";
	}
}
