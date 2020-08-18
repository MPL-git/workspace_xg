package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.MchtInfoChangeLogCustom;
import com.jf.entity.MchtInfoChangeLogCustomExample;
import com.jf.entity.MchtInfoChangeLogExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.service.MchtInfoChangeLogService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@Controller
@SuppressWarnings("serial")
public class MchtInfoChangeLogController extends BaseController {

	@Autowired
	private MchtInfoChangeLogService mchtInfoChangeLogService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 
	 * @Title mchtInfoChangeLogManager   
	 * @Description TODO(法务操作日志)   
	 * @author pengl
	 * @date 2018年3月14日 上午11:26:47
	 */
	@RequestMapping("/mchtInfoChangeLog/mchtInfoChangeLogManager.shtml")
	public ModelAndView mchtInfoChangeLogManager(HttpServletRequest request, String statusPage) {
		ModelAndView m = new ModelAndView();
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //法务操作日志
			MchtInfoChangeLogExample mchtInfoChangeLogExample = new MchtInfoChangeLogExample();
			MchtInfoChangeLogExample.Criteria mchtInfoChangeLogCriteria = mchtInfoChangeLogExample.createCriteria();
			List<String> logTypeList = new ArrayList<String>();
			logTypeList.add("资质总审核");
			logTypeList.add("线上合同状态");
			logTypeList.add("品牌审核");
			logTypeList.add("公司信息");
			logTypeList.add("公司信息更新");
			logTypeList.add("品牌信息更新");
			logTypeList.add("法务品牌确认");
			mchtInfoChangeLogCriteria.andDelFlagEqualTo("0").andLogTypeIn(logTypeList);
			List<Map<String, Object>> createByList = mchtInfoChangeLogService.selectCreatorByListExample(mchtInfoChangeLogExample);
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
			productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
			m.addObject("isCwOrgStatus", isCwOrgStatus);
			
			productTypeExample.setOrderByClause(" seq_no");
			List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList); //1级类目
			m.addObject("mchtStatusList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "STATUS")); //商家合作状态
			m.addObject("createByList", createByList); //操作人
			m.addObject("logTypeList", logTypeList); //状态类型
			m.setViewName("/mchtInfoChangeLog/lawAuditLogList");
		}
		
		return m;
	}
	
	/**
	 * 
	 * @Title lawAuditLog   
	 * @Description TODO(法务操作日志)   
	 * @author pengl
	 * @date 2018年3月14日 下午12:03:14
	 */
	@ResponseBody
	@RequestMapping("/mchtInfoChangeLog/lawAuditLogList.shtml")
	public Map<String, Object> lawAuditLogList(HttpServletRequest request, HttpServletResponse response, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtInfoChangeLogCustom> dataList = null;
		Integer totalCount =0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MchtInfoChangeLogCustomExample mchtInfoChangeLogCustomExample = new MchtInfoChangeLogCustomExample();
			MchtInfoChangeLogCustomExample.MchtInfoChangeLogCustomCriteria mchtInfoChangeLogCustomCriteria = mchtInfoChangeLogCustomExample.createCriteria();
			
			mchtInfoChangeLogCustomCriteria.andDelFlagEqualTo("0");
			mchtInfoChangeLogCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoChangeLogCustomExample.setLimitSize(page.getLimitSize());
			mchtInfoChangeLogCustomExample.setOrderByClause(" t.create_date desc");
			if(!StringUtil.isEmpty(request.getParameter("logType"))) { //状态类型
				mchtInfoChangeLogCustomCriteria.andLogTypeEqualTo(request.getParameter("logType"));
			}else {
				List<String> logTypeList = new ArrayList<String>();
				logTypeList.add("资质总审核");
				logTypeList.add("店铺名审核");
				logTypeList.add("线上合同状态");
				logTypeList.add("品牌审核");
				logTypeList.add("公司信息");
				logTypeList.add("公司信息更新");
				logTypeList.add("品牌信息更新");
				logTypeList.add("法务品牌确认");
				mchtInfoChangeLogCustomCriteria.andLogTypeIn(logTypeList);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					mchtInfoChangeLogCustomCriteria.andProductTypeIdEqualTo(isCwOrgProductTypeId);
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) { //主营类目
					mchtInfoChangeLogCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtStatus"))) { //合作状态
				mchtInfoChangeLogCustomCriteria.andMchtStatusEqualTo(request.getParameter("mchtStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("companyName"))) { //公司名称
				mchtInfoChangeLogCustomCriteria.andCompanyNameEqualTo(request.getParameter("companyName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin"))) { //操作时间
				String createDateBegin = request.getParameter("createDateBegin");
				mchtInfoChangeLogCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(createDateBegin));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd"))) {
				String createDateEnd = request.getParameter("createDateEnd");
				mchtInfoChangeLogCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(createDateEnd));
			}
			if(!StringUtil.isEmpty(request.getParameter("createBy"))) { //操作人
				mchtInfoChangeLogCustomCriteria.andCreateByEqualTo(Integer.parseInt(request.getParameter("createBy")));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) { //商家序号
				mchtInfoChangeLogCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			dataList = mchtInfoChangeLogService.selectMchtInfoChangeLogCustomByExample(mchtInfoChangeLogCustomExample);
			totalCount = mchtInfoChangeLogService.countByExample(mchtInfoChangeLogCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title lawAuditLogExport   
	 * @Description TODO(导出法务日志)   
	 * @author pengl
	 * @date 2018年3月14日 下午1:56:05
	 */
	@RequestMapping("/mchtInfoChangeLog/lawAuditLogListExport.shtml")
	public void lawAuditLogListExport(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MchtInfoChangeLogCustomExample mchtInfoChangeLogCustomExample = new MchtInfoChangeLogCustomExample();
			MchtInfoChangeLogCustomExample.MchtInfoChangeLogCustomCriteria mchtInfoChangeLogCustomCriteria = mchtInfoChangeLogCustomExample.createCriteria();
			mchtInfoChangeLogCustomCriteria.andDelFlagEqualTo("0");
			mchtInfoChangeLogCustomExample.setOrderByClause(" t.create_date desc");
			if(!StringUtil.isEmpty(request.getParameter("logType"))) { //状态类型
				mchtInfoChangeLogCustomCriteria.andLogTypeEqualTo(request.getParameter("logType"));
			}else {
				List<String> logTypeList = new ArrayList<String>();
				logTypeList.add("资质总审核");
				logTypeList.add("店铺名审核");
				logTypeList.add("线上合同状态");
				logTypeList.add("品牌审核");
				logTypeList.add("公司信息");
				mchtInfoChangeLogCustomCriteria.andLogTypeIn(logTypeList);
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) { //主营类目
				mchtInfoChangeLogCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtStatus"))) { //合作状态
				mchtInfoChangeLogCustomCriteria.andMchtStatusEqualTo(request.getParameter("mchtStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("companyName"))) { //公司名称
				mchtInfoChangeLogCustomCriteria.andCompanyNameEqualTo(request.getParameter("companyName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin"))) { //操作时间
				String createDateBegin = request.getParameter("createDateBegin");
				mchtInfoChangeLogCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(createDateBegin));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd"))) {
				String createDateEnd = request.getParameter("createDateEnd");
				mchtInfoChangeLogCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(createDateEnd));
			}
			if(!StringUtil.isEmpty(request.getParameter("createBy"))) { //操作人
				mchtInfoChangeLogCustomCriteria.andCreateByEqualTo(Integer.parseInt(request.getParameter("createBy")));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) { //商家序号
				mchtInfoChangeLogCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			List<MchtInfoChangeLogCustom> dataList = mchtInfoChangeLogService.selectMchtInfoChangeLogCustomByExample(mchtInfoChangeLogCustomExample);
			String[] titles = { "操作时间", "操作人", "商家序号", "店铺名称", "公司名称", "主营类目", "合作状态", "状态类型", "名称", "变更前", "变更后", "备注/驳回原因"};
			ExcelBean excelBean = new ExcelBean("导出法务操作日志列表.xls", "导出法务操作日志列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtInfoChangeLogCustom mchtInfoChangeLogCustom : dataList) {
				String[] data = {
					mchtInfoChangeLogCustom.getCreateDate() == null?"":sdf.format(mchtInfoChangeLogCustom.getCreateDate()),
					mchtInfoChangeLogCustom.getCreatorName(),
					mchtInfoChangeLogCustom.getMchtCode(),
					mchtInfoChangeLogCustom.getShopName(),
					mchtInfoChangeLogCustom.getCompanyName(),
					mchtInfoChangeLogCustom.getProductTypeName(),
					mchtInfoChangeLogCustom.getMchtStatusDeac(),
					mchtInfoChangeLogCustom.getLogType(),
					mchtInfoChangeLogCustom.getLogName(),
					mchtInfoChangeLogCustom.getBeforeChange(),
					mchtInfoChangeLogCustom.getAfterChange(),
					mchtInfoChangeLogCustom.getRemarks()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
