package com.jf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SeckillBrandGroup;
import com.jf.entity.SeckillBrandGroupExample;
import com.jf.entity.SeckillBrandGroupProduct;
import com.jf.entity.SeckillBrandGroupProductCustom;
import com.jf.entity.SeckillBrandGroupProductCustomExample;
import com.jf.entity.SeckillBrandGroupProductExample;
import com.jf.entity.SeckillTime;
import com.jf.entity.SeckillTimeExample;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;
import com.jf.entity.StateCode;
import com.jf.service.ProductTypeService;
import com.jf.service.SeckillBrandGroupProductService;
import com.jf.service.SeckillBrandGroupService;
import com.jf.service.SeckillTimeService;
import com.jf.service.SingleProductActivityService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SeckillBrandGroupController extends BaseController {

	@Autowired
	private SeckillBrandGroupService seckillBrandGroupService;
	
	@Autowired
	private SeckillTimeService seckillTimeService;
	
	@Autowired
	private SeckillBrandGroupProductService seckillBrandGroupProductService;
	
	@Autowired
	private SingleProductActivityService singleProductActivityService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 
	 * @Title seckillBrandGroupManager   
	 * @Description TODO(限时抢购品牌团管理)   
	 * @author pengl
	 * @date 2018年4月18日 下午2:01:56
	 */
	@RequestMapping("/seckillBrandGroup/seckillBrandGroupManager.shtml")
	public ModelAndView seckillBrandGroupManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/seckillBrandGroup/seckillBrandGroupList");
		return m;
	}
	
	/**
	 * 
	 * @Title createSeckillBrandGroupManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月18日 下午7:14:01
	 */
	@RequestMapping("/seckillBrandGroup/saveSeckillBrandGroupManager.shtml")
	public ModelAndView saveSeckillBrandGroupManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/seckillBrandGroup/saveSeckillBrandGroup");
		if(!StringUtil.isEmpty(request.getParameter("seckillBrandGroupId"))) {
			SeckillBrandGroup seckillBrandGroup = seckillBrandGroupService.selectByPrimaryKey(Integer.parseInt(request.getParameter("seckillBrandGroupId")));
			m.addObject("seckillBrandGroup", seckillBrandGroup);
			if(seckillBrandGroup.getBeginTime() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String[] strs = sdf.format(seckillBrandGroup.getBeginTime()).split(" ");
				m.addObject("beginDate", strs[0]);
				String[] sts = strs[1].split(":");
				m.addObject("startHours", sts[0]);
				m.addObject("startMin", sts[1]);
			}
		}
		SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
		seckillTimeExample.createCriteria()
			.andDelFlagEqualTo("0")
			.andStatusEqualTo("1");
		seckillTimeExample.setOrderByClause(" id asc");
		List<SeckillTime> seckillTimeList = seckillTimeService.selectByExample(seckillTimeExample);
		m.addObject("seckillTimeList", seckillTimeList);
		try {
			InputStream stream = ActivityAreaNewController.class.getResourceAsStream("/base_config.properties");
			Properties properties = new Properties();
			properties.load(stream);
			String contextPathStr = properties.getProperty("CONTEXTPATH");
			stream.close();
			m.addObject("contextPathStr", contextPathStr); //查看图片路径
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * 
	 * @Title seckillBrandGroupList   
	 * @Description TODO(限时抢购品牌团管理)   
	 * @author pengl
	 * @date 2018年4月18日 下午2:01:59
	 */
	@ResponseBody
	@RequestMapping("/seckillBrandGroup/seckillBrandGroupList.shtml")
	public Map<String, Object> seckillBrandGroupList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<SeckillBrandGroup> dataList = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SeckillBrandGroupExample seckillBrandGroupExample = new SeckillBrandGroupExample();
			SeckillBrandGroupExample.Criteria seckillBrandGroupCriteria = seckillBrandGroupExample.createCriteria();
			seckillBrandGroupCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("seckillBrandGroupName"))) {
				seckillBrandGroupCriteria.andNameLike("%"+request.getParameter("seckillBrandGroupName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("seckillBrandGroupStatus"))) {
				seckillBrandGroupCriteria.andStatusEqualTo(request.getParameter("seckillBrandGroupStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("startBeginTime"))) {
				seckillBrandGroupCriteria.andBeginTimeGreaterThanOrEqualTo(sdf.parse(request.getParameter("startBeginTime")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endBeginTime"))) {
				seckillBrandGroupCriteria.andBeginTimeLessThanOrEqualTo(sdf.parse(request.getParameter("endBeginTime")+" 23:59:59"));
			}
			seckillBrandGroupExample.setLimitStart(page.getLimitStart());
			seckillBrandGroupExample.setLimitSize(page.getLimitSize());
			seckillBrandGroupExample.setOrderByClause(" id desc");
			totalCount = seckillBrandGroupService.countByExample(seckillBrandGroupExample);
			dataList = seckillBrandGroupService.selectByExample(seckillBrandGroupExample);
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
	 * @Title saveSeckillBrandGroup   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月18日 下午8:03:28
	 */
	@RequestMapping("/seckillBrandGroup/saveSeckillBrandGroup.shtml")
	public ModelAndView saveSeckillBrandGroup(HttpServletRequest request, SeckillBrandGroup seckillBrandGroup) {
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if(seckillBrandGroup.getId() == null) {
				String beginDate = request.getParameter("beginDate");
				String beginTm = request.getParameter("beginTm");
				seckillBrandGroup.setBeginTime(sdf.parse(beginDate+" "+beginTm));
				seckillBrandGroup.setStatus("0");
				seckillBrandGroup.setCreateDate(date);
				seckillBrandGroup.setCreateBy(staffID);
				seckillBrandGroup.setUpdateDate(date);
				seckillBrandGroup.setDelFlag("0");
				seckillBrandGroupService.insert(seckillBrandGroup);
			}else {
				seckillBrandGroup.setUpdateDate(date);
				seckillBrandGroup.setUpdateBy(staffID);
				seckillBrandGroupService.updateByPrimaryKeySelective(seckillBrandGroup);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	/**
	 * 
	 * @Title updateSeckillBrandGroup   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月18日 下午3:42:19
	 */
	@ResponseBody
	@RequestMapping("/seckillBrandGroup/updateSeckillBrandGroup.shtml")
	public Map<String, Object> updateSeckillBrandGroup(HttpServletRequest request, SeckillBrandGroup seckillBrandGroup) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			seckillBrandGroup.setUpdateDate(date);
			seckillBrandGroup.setUpdateBy(staffID);
			seckillBrandGroupService.updateByPrimaryKeySelective(seckillBrandGroup);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title delSeckillBrandGroup   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月18日 下午5:57:57
	 */
	@ResponseBody
	@RequestMapping("/seckillBrandGroup/delSeckillBrandGroup.shtml")
	public Map<String, Object> delSeckillBrandGroup(HttpServletRequest request, Integer seckillBrandGroupId) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			seckillBrandGroupService.delSeckillBrandGroup(seckillBrandGroupId, staffID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title seckillBrandGroupProductManager   
	 * @Description TODO(商品管理)   
	 * @author pengl
	 * @date 2018年4月19日 上午10:34:43
	 */
	@RequestMapping("/seckillBrandGroup/seckillBrandGroupProductManager.shtml")
	public ModelAndView seckillBrandGroupProductManager(HttpServletRequest request, Integer seckillBrandGroupId) {
		ModelAndView m = new ModelAndView("/seckillBrandGroup/seckillBrandGroupProductList");
		m.addObject("seckillBrandGroupId", seckillBrandGroupId);
		return m;
	}
	
	/**
	 * 
	 * @Title seckillBrandGroupProductList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月19日 上午10:40:25
	 */
	@ResponseBody
	@RequestMapping("/seckillBrandGroup/seckillBrandGroupProductList.shtml")
	public Map<String, Object> seckillBrandGroupProductList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<SeckillBrandGroupProductCustom> dataList = null;
		try {
			SeckillBrandGroupProductCustomExample seckillBrandGroupProductCustomExample = new SeckillBrandGroupProductCustomExample();
			SeckillBrandGroupProductCustomExample.SeckillBrandGroupProductCustomCriteria seckillBrandGroupProductCustomCriteria = seckillBrandGroupProductCustomExample.createCriteria();
			seckillBrandGroupProductCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("seckillBrandGroupId"))) {
				seckillBrandGroupProductCustomCriteria.andSeckillBrandGroupIdEqualTo(Integer.parseInt(request.getParameter("seckillBrandGroupId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("productCode"))) {
				seckillBrandGroupProductCustomCriteria.andProductCodeEqualTo(request.getParameter("productCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBandName"))) {
				seckillBrandGroupProductCustomCriteria.andProductBandNameLike("%"+request.getParameter("productBandName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productArtNo"))) {
				seckillBrandGroupProductCustomCriteria.andProductArtLike("%"+request.getParameter("productArtNo")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("shopName"))) {
				seckillBrandGroupProductCustomCriteria.andShopNameLike("%"+request.getParameter("shopName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("startProductActivityPrice"))) {
				seckillBrandGroupProductCustomCriteria.andProductActivityPriceGreaterThanOrEqualTo(request.getParameter("startProductActivityPrice"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endProductActivityPrice"))) {
				seckillBrandGroupProductCustomCriteria.andProductActivityPriceLessThanOrEqualTo(request.getParameter("endProductActivityPrice"));
			}
			seckillBrandGroupProductCustomExample.setLimitStart(page.getLimitStart());
			seckillBrandGroupProductCustomExample.setLimitSize(page.getLimitSize());
			seckillBrandGroupProductCustomExample.setOrderByClause(" id desc");
			
			totalCount = seckillBrandGroupProductService.countByCustomExample(seckillBrandGroupProductCustomExample);
			dataList = seckillBrandGroupProductService.selectByCustomExample(seckillBrandGroupProductCustomExample);
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
	 * @Title delSeckillBrandGroupProduct   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月19日 上午11:12:27
	 */
	@ResponseBody
	@RequestMapping("/seckillBrandGroup/delSeckillBrandGroupProduct.shtml")
	public Map<String, Object> delSeckillBrandGroupProduct(HttpServletRequest request, String seckillBrandGroupProductIds) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if(!StringUtil.isEmpty(seckillBrandGroupProductIds)) {
				String[] strs = seckillBrandGroupProductIds.split(",");
				List<Integer> seckillBrandGroupProductIdList = new ArrayList<Integer>();
				for(String seckillBrandGroupProductId : strs) {
					seckillBrandGroupProductIdList.add(Integer.parseInt(seckillBrandGroupProductId));
				}
				SeckillBrandGroupProductExample seckillBrandGroupProductExample = new SeckillBrandGroupProductExample();
				seckillBrandGroupProductExample.createCriteria().andIdIn(seckillBrandGroupProductIdList);
				SeckillBrandGroupProduct seckillBrandGroupProduct = new SeckillBrandGroupProduct();
				seckillBrandGroupProduct.setUpdateBy(staffID);
				seckillBrandGroupProduct.setUpdateDate(new Date());
				seckillBrandGroupProduct.setDelFlag("1");
				seckillBrandGroupProductService.updateByExampleSelective(seckillBrandGroupProduct, seckillBrandGroupProductExample);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title singleProductActivityManager   
	 * @Description TODO(单品活动)   
	 * @author pengl
	 * @date 2018年4月19日 上午11:52:35
	 */
	@RequestMapping("/seckillBrandGroup/singleProductActivityManager.shtml")
	public ModelAndView singleProductActivityManager(HttpServletRequest request, Integer seckillBrandGroupId) {
		ModelAndView m = new ModelAndView("/seckillBrandGroup/singleProductActivityList");
		SeckillBrandGroup seckillBrandGroup = seckillBrandGroupService.selectByPrimaryKey(seckillBrandGroupId);
		m.addObject("seckillBrandGroupId", seckillBrandGroupId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		m.addObject("beginTime", sdf.format(seckillBrandGroup.getBeginTime()));
		
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = example.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		m.addObject("isCwOrgStatus", isCwOrgStatus);
		List<ProductType> productTypeList = productTypeService.selectByExample(example);
		m.addObject("productTypeList", productTypeList);
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/seckillBrandGroup/singleProductActivityList.shtml")
	public Map<String, Object> singleProductActivityList(HttpServletRequest request, Page page, BigDecimal startActivityPrice, BigDecimal endActivityPrice) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<SingleProductActivityCustom> dataList = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			singleProductActivityCustomCriteria.andDelFlagEqualTo("0").andTypeEqualTo("3")
				.andAuditStatusEqualTo("3").andIsCloseEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("beginTime"))) {
				singleProductActivityCustomCriteria.andBeginTimeEqualTo(sdf.parse(request.getParameter("beginTime")));
			}
			if(!StringUtil.isEmpty(request.getParameter("code"))) {
				singleProductActivityCustomCriteria.andCodeByEqualTo(request.getParameter("code"));
			}
			if(!StringUtil.isEmpty(request.getParameter("brandName"))) {
				singleProductActivityCustomCriteria.andBrandNameLike(request.getParameter("brandName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("artNo"))) {
				singleProductActivityCustomCriteria.andArtNoLike(request.getParameter("artNo"));
			}
			if(!StringUtil.isEmpty(request.getParameter("shopName"))) {
				singleProductActivityCustomCriteria.andShopNameLike(request.getParameter("shopName"));
			}
			if(startActivityPrice != null) {
				singleProductActivityCustomCriteria.andActivityPriceGreaterThanOrEqualTo(startActivityPrice);
			}
			if(endActivityPrice != null) {
				singleProductActivityCustomCriteria.andActivityPriceLessThanOrEqualTo(endActivityPrice);
			}
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					singleProductActivityCustomCriteria.andProductTypeEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
					singleProductActivityCustomCriteria.andProductTypeEqualTo(Integer.parseInt(request.getParameter("productTypeId").trim()));
				}
			}
			
			singleProductActivityCustomCriteria.andNotExistsSeckillBrandGroupProduct();
			singleProductActivityCustomExample.setLimitSize(page.getLimitSize());
			singleProductActivityCustomExample.setLimitStart(page.getLimitStart());
			dataList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
			totalCount = singleProductActivityService.countByCustomExample(singleProductActivityCustomExample);
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
	 * @Title addSeckillBrandGroupProduct   
	 * @Description TODO(添加秒杀品牌团商品表)   
	 * @author pengl
	 * @date 2018年4月19日 下午1:12:10
	 */
	@ResponseBody
	@RequestMapping("/seckillBrandGroup/addSeckillBrandGroupProduct.shtml")
	public Map<String, Object> addSeckillBrandGroupProduct(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String singleProductActivityIds = request.getParameter("singleProductActivityIds");
			String seckillBrandGroupId = request.getParameter("seckillBrandGroupId");
			if(!StringUtil.isEmpty(singleProductActivityIds) && !StringUtil.isEmpty(seckillBrandGroupId)) {
				seckillBrandGroupService.addSeckillBrandGroupProduct(staffID, singleProductActivityIds, seckillBrandGroupId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	
}
