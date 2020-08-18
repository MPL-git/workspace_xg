package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryExample;
import com.jf.entity.MallCategoryModule;
import com.jf.entity.MallCategoryModuleCustom;
import com.jf.entity.MallCategoryModuleCustomExample;
import com.jf.entity.MallCategoryModuleExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StateCode;
import com.jf.service.MallCategoryModuleService;
import com.jf.service.MallCategoryService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class MallCategoryModuleController extends BaseController {

	@Autowired
	private MallCategoryModuleService mallCategoryModuleService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private MallCategoryService mallCategoryService;
	
	/**
	 * 
	 * @Title mallCategoryModuleManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月19日 下午6:13:22
	 */
	@RequestMapping("/mallCategoryModule/mallCategoryModuleManager.shtml")
	public ModelAndView mallCategoryModuleManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.addObject("moduleTypeList", DataDicUtil.getTableStatus("BU_MALL_CATEGORY_MODULE", "MODULE_TYPE")); //模块类型
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_MALL_CATEGORY_MODULE", "STATUS")); //状态
		m.addObject("mallCategoryId", request.getParameter("mallCategoryId")); //商城类目ID
		m.setViewName("/mallCategoryModule/getMallCategoryModuleList");
		return m;
	}
	
	/**
	 * 
	 * @Title getMallCategoryModuleList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月20日 上午10:46:13
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryModule/getMallCategoryModuleList.shtml")
	public Map<String, Object> getMallCategoryModuleList(HttpServletRequest request, Page page, Integer mallCategoryModuleId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MallCategoryModuleCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MallCategoryModuleCustomExample mallCategoryModuleCustomExample = new MallCategoryModuleCustomExample();
			MallCategoryModuleCustomExample.MallCategoryModuleCustomCriteria mallCategoryModuleCustomCriteria = mallCategoryModuleCustomExample.createCriteria();
			mallCategoryModuleCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("mallCategoryId")) ) {
				mallCategoryModuleCustomCriteria.andMallCategoryIdEqualTo(Integer.parseInt(request.getParameter("mallCategoryId")));
			}
			if(mallCategoryModuleId != null ) {
				mallCategoryModuleCustomCriteria.andIdEqualTo(mallCategoryModuleId);
			}
			if(!StringUtil.isEmpty(request.getParameter("categoryModuleName")) ) {
				mallCategoryModuleCustomCriteria.andCategoryModuleNameLike("%"+request.getParameter("categoryModuleName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("moduleType")) ) {
				mallCategoryModuleCustomCriteria.andModuleTypeEqualTo(request.getParameter("moduleType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ) {
				mallCategoryModuleCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate")) ) {
				mallCategoryModuleCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate")) ) {
				mallCategoryModuleCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			mallCategoryModuleCustomExample.setOrderByClause(" t.seq_no desc, t.create_date desc");
			mallCategoryModuleCustomExample.setLimitStart(page.getLimitStart());
			mallCategoryModuleCustomExample.setLimitSize(page.getLimitSize());
			totalCount = mallCategoryModuleService.countByCustomExample(mallCategoryModuleCustomExample);
			dataList = mallCategoryModuleService.selectByCustomExample(mallCategoryModuleCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateStatus   
	 * @Description TODO(上下架)   
	 * @author pengl
	 * @date 2018年7月20日 上午11:00:05
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryModule/updateStatus.shtml")
	public Map<String, Object> updateStatus(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryModuleId = request.getParameter("mallCategoryModuleId");
			if(!StringUtil.isEmpty(mallCategoryModuleId)) {
				MallCategoryModuleExample mallCategoryModuleExample = new MallCategoryModuleExample();
				mallCategoryModuleExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryModuleId));
				MallCategoryModule mallCategoryModule = new MallCategoryModule();
				mallCategoryModule.setStatus(request.getParameter("status"));
				mallCategoryModule.setUpdateBy(Integer.parseInt(staffID));
				mallCategoryModule.setUpdateDate(date);
				mallCategoryModuleService.updateByExampleSelective(mallCategoryModule, mallCategoryModuleExample);
			}else {
				code = "9999";
				msg = "ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title updateSeqNo   
	 * @Description TODO(排序)   
	 * @author pengl
	 * @date 2018年7月20日 上午11:00:36
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryModule/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryModuleId = request.getParameter("mallCategoryModuleId");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(mallCategoryModuleId) && !StringUtil.isEmpty(seqNo) ) {
				MallCategoryModuleExample mallCategoryModuleExample = new MallCategoryModuleExample();
				mallCategoryModuleExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryModuleId));
				MallCategoryModule mallCategoryModule = new MallCategoryModule();
				mallCategoryModule.setSeqNo(Integer.parseInt(seqNo));
				mallCategoryModule.setUpdateBy(Integer.parseInt(staffID));
				mallCategoryModule.setUpdateDate(date);
				mallCategoryModuleService.updateByExampleSelective(mallCategoryModule, mallCategoryModuleExample);
			}else {
				code = "9999";
				msg = "ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title delMallCategory   
	 * @Description TODO(删除)   
	 * @author pengl
	 * @date 2018年7月20日 上午11:01:42
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryModule/delMallCategoryModule.shtml")
	public Map<String, Object> delMallCategory(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryModuleId = request.getParameter("mallCategoryModuleId");
			if(!StringUtil.isEmpty(mallCategoryModuleId) ) {
				MallCategoryModuleExample mallCategoryModuleExample = new MallCategoryModuleExample();
				mallCategoryModuleExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryModuleId));
				MallCategoryModule mallCategoryModule = new MallCategoryModule();
				mallCategoryModule.setDelFlag("1");
				mallCategoryModule.setUpdateBy(Integer.parseInt(staffID));
				mallCategoryModule.setUpdateDate(date);
				mallCategoryModuleService.updateByExampleSelective(mallCategoryModule, mallCategoryModuleExample);
			}else {
				code = "9999";
				msg = "ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title addOrUpdateMallCategoryManager   
	 * @Description TODO(添加编辑)   
	 * @author pengl
	 * @date 2018年7月20日 下午2:31:54
	 */
	@RequestMapping("/mallCategoryModule/addOrUpdateMallCategoryModuleManager.shtml")
	public ModelAndView addOrUpdateMallCategoryManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		if(!StringUtil.isEmpty(request.getParameter("mallCategoryModuleId")) ) {
			MallCategoryModuleCustom mallCategoryModuleCustom = mallCategoryModuleService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("mallCategoryModuleId")));
			m.addObject("mallCategoryModuleCustom", mallCategoryModuleCustom);
			m.addObject("productType2Ids", mallCategoryModuleCustom.getProductType2Ids().replaceAll(",", ";"));
		}
		if(!StringUtil.isEmpty(request.getParameter("mallCategoryId")) ) {
			m.addObject("mallCategoryId", request.getParameter("mallCategoryId"));
			MallCategory mallCategory = mallCategoryService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mallCategoryId")));
			if(!StringUtil.isEmpty(mallCategory.getProductType1Ids())) {
				String[] strs = mallCategory.getProductType1Ids().split(",");
				List<Integer> idList = new ArrayList<Integer>();
				for(String str : strs) {
					idList.add(Integer.parseInt(str));
				}
				ProductTypeExample productTypeAExample = new ProductTypeExample();
				productTypeAExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andIdIn(idList);
				productTypeAExample.setOrderByClause(" id asc");
				List<ProductType> productTypeAList = productTypeService.selectByExample(productTypeAExample);
				m.addObject("productTypeAList", JSONArray.fromObject(productTypeAList));
				ProductTypeExample productTypeBExample = new ProductTypeExample();
				productTypeBExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andParentIdIn(idList);
				productTypeBExample.setOrderByClause(" id asc");
				List<ProductType> productTypeBList = productTypeService.selectByExample(productTypeBExample);
				m.addObject("productTypeBList", JSONArray.fromObject(productTypeBList));
			}
		}
		m.setViewName("/mallCategoryModule/addOrUpdateMallCategoryModule");
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateMallCategoryModule   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月20日 下午2:48:57
	 */
	@RequestMapping("/mallCategoryModule/addOrUpdateMallCategoryModule.shtml")
	public ModelAndView addOrUpdateMallCategoryModule(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg = null;
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			MallCategoryModule mallCategoryModule = new MallCategoryModule();
			if("2".equals(request.getParameter("moduleType")) ) {
				mallCategoryModule.setProductType2Ids(request.getParameter("productType2Ids").replaceAll(";", ","));
			}else {
				mallCategoryModule.setProductType2Ids("");
			}
			mallCategoryModule.setCategoryModuleName(request.getParameter("categoryModuleName"));
			mallCategoryModule.setModuleType(request.getParameter("moduleType"));
			if(!StringUtil.isEmpty(request.getParameter("id")) ) {
				mallCategoryModule.setId(Integer.parseInt(request.getParameter("id")));
				mallCategoryModule.setUpdateBy(Integer.parseInt(staffID));
				mallCategoryModule.setUpdateDate(date);
				mallCategoryModuleService.updateByPrimaryKeySelective(mallCategoryModule);
			}else {
				mallCategoryModule.setMallCategoryId(Integer.parseInt(request.getParameter("mallCategoryId")));
				mallCategoryModule.setStatus("0");
				mallCategoryModule.setCreateBy(Integer.parseInt(staffID));
				mallCategoryModule.setCreateDate(date);
				mallCategoryModule.setDelFlag("0");
				mallCategoryModuleService.insertSelective(mallCategoryModule);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	
	
}
