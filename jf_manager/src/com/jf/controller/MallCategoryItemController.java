package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.MallCategoryItem;
import com.jf.entity.MallCategoryItemCustom;
import com.jf.entity.MallCategoryItemCustomExample;
import com.jf.entity.MallCategoryItemExample;
import com.jf.entity.MallCategoryModule;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandCustom;
import com.jf.entity.ProductBrandCustomExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StateCode;
import com.jf.service.MallCategoryItemService;
import com.jf.service.MallCategoryModuleService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class MallCategoryItemController extends BaseController {

	@Autowired
	private MallCategoryItemService mallCategoryItemService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private MallCategoryModuleService mallCategoryModuleService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 
	 * @Title mallCategoryItemManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月20日 下午6:01:07
	 */
	@RequestMapping("/mallCategoryItem/mallCategoryItemManager.shtml")
	public ModelAndView mallCategoryItemManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_MALL_CATEGORY_ITEM", "STATUS")); //状态
		m.addObject("mallCategoryModuleId", request.getParameter("mallCategoryModuleId")); //商城类目模块ID
		if(!StringUtil.isEmpty(request.getParameter("moduleType")) ) {
			if("1".equals(request.getParameter("moduleType")) ) { //1 品牌推荐模块
				m.setViewName("/mallCategoryItem/getMallCategoryItemBrandList");
			}else if("2".equals(request.getParameter("moduleType")) ) { //2 类目模块
				m.setViewName("/mallCategoryItem/getMallCategoryItemTypeList");
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title getMallCategoryItemList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月20日 下午6:11:22
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryItem/getMallCategoryItemList.shtml")
	public Map<String, Object> getMallCategoryItemList(HttpServletRequest request, Page page, Integer mallCategoryModuleId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MallCategoryItemCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MallCategoryItemCustomExample mallCategoryItemCustomExample = new MallCategoryItemCustomExample();
			MallCategoryItemCustomExample.MallCategoryItemCustomCriteria mallCategoryItemCustomCriteria = mallCategoryItemCustomExample.createCriteria();
			mallCategoryItemCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("mallCategoryModuleId")) ) {
				mallCategoryItemCustomCriteria.andMallCategoryModuleIdEqualTo(Integer.parseInt(request.getParameter("mallCategoryModuleId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("itemLinkValue")) ) {
				mallCategoryItemCustomCriteria.andItemLinkValueEqualTo(request.getParameter("itemLinkValue"));
			}
			if(!StringUtil.isEmpty(request.getParameter("itemName")) ) {
				mallCategoryItemCustomCriteria.andItemNameLike("%"+request.getParameter("itemName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ) {
				mallCategoryItemCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate")) ) {
				mallCategoryItemCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate")) ) {
				mallCategoryItemCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			mallCategoryItemCustomExample.setOrderByClause(" t.seq_no desc, t.create_date desc");
			mallCategoryItemCustomExample.setLimitStart(page.getLimitStart());
			mallCategoryItemCustomExample.setLimitSize(page.getLimitSize());
			totalCount = mallCategoryItemService.countByCustomExample(mallCategoryItemCustomExample);
			dataList = mallCategoryItemService.selectByCustomExample(mallCategoryItemCustomExample);
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
	 * @date 2018年7月20日 下午6:14:01
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryItem/updateStatus.shtml")
	public Map<String, Object> updateStatus(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryItemId = request.getParameter("mallCategoryItemId");
			if(!StringUtil.isEmpty(mallCategoryItemId)) {
				MallCategoryItemExample mallCategoryItemExample = new MallCategoryItemExample();
				mallCategoryItemExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryItemId));
				MallCategoryItem mallCategoryItem = new MallCategoryItem();
				mallCategoryItem.setStatus(request.getParameter("status"));
				mallCategoryItem.setUpdateBy(Integer.parseInt(staffID));
				mallCategoryItem.setUpdateDate(date);
				mallCategoryItemService.updateByExampleSelective(mallCategoryItem, mallCategoryItemExample);
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
	 * @date 2018年7月20日 下午6:15:33
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryItem/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryItemId = request.getParameter("mallCategoryItemId");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(mallCategoryItemId) && !StringUtil.isEmpty(seqNo) ) {
				MallCategoryItemExample mallCategoryItemExample = new MallCategoryItemExample();
				mallCategoryItemExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryItemId));
				MallCategoryItem mallCategoryItem = new MallCategoryItem();
				mallCategoryItem.setSeqNo(Integer.parseInt(seqNo));
				mallCategoryItem.setUpdateBy(Integer.parseInt(staffID));
				mallCategoryItem.setUpdateDate(date);
				mallCategoryItemService.updateByExampleSelective(mallCategoryItem, mallCategoryItemExample);
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
	 * @Title delMallCategoryItem   
	 * @Description TODO(删除)   
	 * @author pengl
	 * @date 2018年7月20日 下午6:17:05
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryItem/delMallCategoryItem.shtml")
	public Map<String, Object> delMallCategoryItem(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryItemId = request.getParameter("mallCategoryItemId");
			if(!StringUtil.isEmpty(mallCategoryItemId) ) {
				MallCategoryItemExample mallCategoryItemExample = new MallCategoryItemExample();
				mallCategoryItemExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryItemId));
				MallCategoryItem mallCategoryItem = new MallCategoryItem();
				mallCategoryItem.setDelFlag("1");
				mallCategoryItem.setUpdateBy(Integer.parseInt(staffID));
				mallCategoryItem.setUpdateDate(date);
				mallCategoryItemService.updateByExampleSelective(mallCategoryItem, mallCategoryItemExample);
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
	 * @Title productBrandManager   
	 * @Description TODO(添加品牌)   
	 * @author pengl
	 * @date 2018年7月23日 上午10:38:01
	 */
	@RequestMapping("/mallCategoryItem/productBrandManager.shtml")
	public ModelAndView productBrandManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.addObject("mallCategoryModuleId", request.getParameter("mallCategoryModuleId")); //商城类目模块
		m.setViewName("/mallCategoryItem/getProductBrandList");
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/mallCategoryItem/getProductBrandList.shtml")
	public Map<String, Object> getProductBrandList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ProductBrandCustom> dataList = null;
		Integer totalCount = 0;
		try {
			List<Integer> inList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(request.getParameter("mallCategoryModuleId")) ) {
				MallCategoryItemExample mallCategoryItemExample = new MallCategoryItemExample();
				MallCategoryItemExample.Criteria mallCategoryItemCriteria = mallCategoryItemExample.createCriteria();
				mallCategoryItemCriteria.andDelFlagEqualTo("0")
					.andItemLinkTypeEqualTo("1")
					.andMallCategoryModuleIdEqualTo(Integer.parseInt(request.getParameter("mallCategoryModuleId")));
				List<MallCategoryItem> mallCategoryItemList = mallCategoryItemService.selectByExample(mallCategoryItemExample);
				for(MallCategoryItem mallCategoryItem : mallCategoryItemList) {
					inList.add(Integer.parseInt(mallCategoryItem.getItemLinkValue()));
				}
			}
			ProductBrandCustomExample productBrandCustomExample = new ProductBrandCustomExample();
			ProductBrandCustomExample.ProductBrandExampleCriteria productBrandCustomCriteria = productBrandCustomExample.createCriteria();
			productBrandCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			if(inList.size() > 0) {
				productBrandCustomCriteria.andIdNotIn(inList);
			}
			productBrandCustomCriteria.andProductIdEqualTo();
			if(!StringUtil.isEmpty(request.getParameter("productId")) ) {
				productBrandCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("productId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("productName")) ) {
				productBrandCustomCriteria.andNameLike("%"+request.getParameter("productName")+"%");
			}
			productBrandCustomExample.setLimitStart(page.getLimitStart());
			productBrandCustomExample.setLimitSize(page.getLimitSize());
			totalCount = productBrandService.countByCustomExample(productBrandCustomExample);
			dataList = productBrandService.selectByCustomExample(productBrandCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title addProductBrand   
	 * @Description TODO(添加)   
	 * @author pengl
	 * @date 2018年7月23日 上午11:55:16
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryItem/addProductBrand.shtml")
	public Map<String, Object> addProductBrand(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String productBrandId = request.getParameter("productBrandId");
			String mallCategoryModuleId = request.getParameter("mallCategoryModuleId");
			if(!StringUtil.isEmpty(productBrandId) && !StringUtil.isEmpty(mallCategoryModuleId) ) {
				ProductBrand productBrand = productBrandService.selectByPrimaryKey(Integer.parseInt(productBrandId));
				MallCategoryItem mallCategoryItem = new MallCategoryItem();
				mallCategoryItem.setMallCategoryModuleId(Integer.parseInt(mallCategoryModuleId));
				mallCategoryItem.setStatus("0");
				mallCategoryItem.setItemName(productBrand.getName());
				mallCategoryItem.setItemLinkType("1");
				mallCategoryItem.setItemLinkValue(productBrandId);
				mallCategoryItem.setCreateBy(Integer.parseInt(staffID));
				mallCategoryItem.setCreateDate(date);
				mallCategoryItem.setDelFlag("0");
				mallCategoryItemService.insertSelective(mallCategoryItem);
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
	 * @Title updateMallCategoryItemBrandManager   
	 * @Description TODO(品牌修改)   
	 * @author pengl
	 * @date 2018年7月23日 下午3:16:15
	 */
	@RequestMapping("/mallCategoryItem/updateMallCategoryItemBrandManager.shtml")
	public ModelAndView updateMallCategoryItemBrandManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		if(!StringUtil.isEmpty(request.getParameter("mallCategoryItemId"))) {
			MallCategoryItem mallCategoryItem = mallCategoryItemService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mallCategoryItemId")));
			m.addObject("mallCategoryItem", mallCategoryItem);
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andDelFlagEqualTo("0");
			List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList);
		}
		m.setViewName("/mallCategoryItem/updateMallCategoryItem");
		return m;
	}
	
	/**
	 * 
	 * @Title updateMallCategoryItemBrand   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月23日 下午3:16:05
	 */
	@RequestMapping("/mallCategoryItem/updateMallCategoryItemBrand.shtml")
	public ModelAndView updateMallCategoryItemBrand(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg = null;
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			MallCategoryItem mallCategoryItem = new MallCategoryItem();
			if(!StringUtil.isEmpty(request.getParameter("mallCategoryItemId"))) {
				mallCategoryItem.setId(Integer.parseInt(request.getParameter("mallCategoryItemId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("itemName"))) {
				mallCategoryItem.setItemName(request.getParameter("itemName"));
			}
			mallCategoryItem.setItemPic(request.getParameter("itemPic"));
			mallCategoryItem.setUpdateBy(Integer.parseInt(staffID));
			mallCategoryItem.setUpdateDate(date);
			mallCategoryItemService.updateByPrimaryKeySelective(mallCategoryItem);
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
	
	/**
	 * 
	 * @Title productTypeManager   
	 * @Description TODO(添加类目)   
	 * @author pengl
	 * @date 2018年7月23日 下午4:01:30
	 */
	@RequestMapping("/mallCategoryItem/productTypeManager.shtml")
	public ModelAndView productTypeManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.addObject("mallCategoryModuleId", request.getParameter("mallCategoryModuleId")); //商城类目模块
		m.setViewName("/mallCategoryItem/getProductTypeList");
		return m;
	}
	
	/**
	 * 
	 * @Title getProductTypeList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月23日 下午6:08:38
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryItem/getProductTypeList.shtml")
	public Map<String, Object> getProductTypeList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			StringBuffer notIds = new StringBuffer();
			MallCategoryModule mallCategoryModule = null;
			if(!StringUtil.isEmpty(request.getParameter("mallCategoryModuleId")) ) {
				mallCategoryModule = mallCategoryModuleService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mallCategoryModuleId")));
				MallCategoryItemExample mallCategoryItemExample = new MallCategoryItemExample();
				MallCategoryItemExample.Criteria mallCategoryItemCriteria = mallCategoryItemExample.createCriteria();
				mallCategoryItemCriteria.andDelFlagEqualTo("0")
					.andItemLinkTypeEqualTo("2")
					.andMallCategoryModuleIdEqualTo(Integer.parseInt(request.getParameter("mallCategoryModuleId")));
				List<MallCategoryItem> mallCategoryItemList = mallCategoryItemService.selectByExample(mallCategoryItemExample);
				for(MallCategoryItem mallCategoryItem : mallCategoryItemList) {
					if(notIds.length() > 0) {
						notIds.append(",");
					}
					notIds.append(Integer.parseInt(mallCategoryItem.getItemLinkValue()));
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				map.put("productTypeId", request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeName"))) {
				map.put("productTypeName", "%"+request.getParameter("productTypeName")+"%");
			}
			if(mallCategoryModule != null && !StringUtil.isEmpty(mallCategoryModule.getProductType2Ids())) {
				map.put("productType2Ids", mallCategoryModule.getProductType2Ids());
			}
			if(notIds.length() > 0) {
				map.put("notIds", notIds.toString());
			}
			map.put("status", "1");
			/*map.put("product_count", "1");*/ //只显示有商品的品类
			map.put("limitStart", page.getLimitStart());
			map.put("limitSize", page.getLimitSize());
			totalCount = productTypeService.getProductTypeCount(map);
			dataList = productTypeService.getProductTypeList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title addProductType   
	 * @Description TODO(添加类目)   
	 * @author pengl
	 * @date 2018年7月24日 上午11:37:23
	 */
	@ResponseBody
	@RequestMapping("/mallCategoryItem/addProductType.shtml")
	public Map<String, Object> addProductType(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String productTypeId = request.getParameter("productTypeId");
			String mallCategoryModuleId = request.getParameter("mallCategoryModuleId");
			if(!StringUtil.isEmpty(productTypeId) && !StringUtil.isEmpty(mallCategoryModuleId) ) {
				ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(productTypeId));
				MallCategoryItem mallCategoryItem = new MallCategoryItem();
				mallCategoryItem.setMallCategoryModuleId(Integer.parseInt(mallCategoryModuleId));
				mallCategoryItem.setStatus("0");
				mallCategoryItem.setItemName(productType.getName());
				mallCategoryItem.setItemLinkType("2");
				mallCategoryItem.setItemLinkValue(productTypeId);
				mallCategoryItem.setCreateBy(Integer.parseInt(staffID));
				mallCategoryItem.setCreateDate(date);
				mallCategoryItem.setDelFlag("0");
				mallCategoryItemService.insertSelective(mallCategoryItem);
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
	
	
	
}
