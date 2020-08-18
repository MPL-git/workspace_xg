package com.jf.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.entity.FreightTemplate;
import com.jf.entity.FreightTemplateCustom;
import com.jf.entity.FreightTemplateExample;
import com.jf.entity.Product;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.ProductExample;
import com.jf.entity.ProvinceFreight;
import com.jf.entity.ProvinceFreightCustom;
import com.jf.entity.ProvinceFreightExample;
import com.jf.service.AreaService;
import com.jf.service.FreightTemplateService;
import com.jf.service.ProductService;
import com.jf.service.ProvinceFreightService;

@Controller
@RequestMapping("/freightTemplate")
public class FreightTemplateController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(FreightTemplateController.class);

	@Resource
	private FreightTemplateService freightTemplateService;
	
	@Resource
	private ProvinceFreightService provinceFreightService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private ProductService productService;
	
	/**
	 * 运费模板管理
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/freightTemplateIndex")
	public String freightTemplateIndex(Model model, HttpServletRequest request) {
		FreightTemplateExample e = new FreightTemplateExample();
		FreightTemplateExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<FreightTemplateCustom> freightTemplateCustoms = freightTemplateService.selectCustomByExample(e);
		for(FreightTemplateCustom freightTemplateCustom:freightTemplateCustoms){
			ProvinceFreightExample pfe = new ProvinceFreightExample();
			ProvinceFreightExample.Criteria pfec = pfe.createCriteria();
			pfec.andDelFlagEqualTo("0");
			pfec.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			pfec.andFreightTemplateIdEqualTo(freightTemplateCustom.getId());
			List<ProvinceFreightCustom> provinceFreightCustoms = provinceFreightService.selectByCustomExample(pfe);
			freightTemplateCustom.setProvinceFreightCustoms(provinceFreightCustoms);
		}
		model.addAttribute("freightTemplateCustoms", freightTemplateCustoms);
		return "freightTemplate/freightTemplateIndex";
	}
	
	/**
	 * 运费模板管理数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getFreightTemplateList")
	@ResponseBody
	public ResponseMsg getFreightTemplateList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		FreightTemplateExample e = new FreightTemplateExample();
		FreightTemplateExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		e.setLimitStart(page.getLimitStart());
		e.setLimitSize(page.getLimitSize());
		int totalCount = freightTemplateService.countByExample(e);
		List<FreightTemplateCustom> freightTemplateCustoms = freightTemplateService.selectCustomByExample(e);
		for(FreightTemplateCustom freightTemplateCustom:freightTemplateCustoms){
			ProvinceFreightExample pfe = new ProvinceFreightExample();
			ProvinceFreightExample.Criteria pfec = pfe.createCriteria();
			pfec.andDelFlagEqualTo("0");
			pfec.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			pfec.andFreightTemplateIdEqualTo(freightTemplateCustom.getId());
			List<ProvinceFreightCustom> provinceFreightCustoms = provinceFreightService.selectByCustomExample(pfe);
			freightTemplateCustom.setProvinceFreightCustoms(provinceFreightCustoms);
		}
		returnData.put("Rows", freightTemplateCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 *添加/编辑运费模板页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/editFreightTemplate")
	public String editFreightTemplate(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			FreightTemplate freightTemplate = freightTemplateService.selectByPrimaryKey(Integer.parseInt(id));
			model.addAttribute("freightTemplate", freightTemplate);
			ProvinceFreightExample e = new ProvinceFreightExample();
			ProvinceFreightExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			c.andFreightTemplateIdEqualTo(freightTemplate.getId());
			List<ProvinceFreightCustom> provinceFreightCustoms = provinceFreightService.selectByCustomExample(e);
			model.addAttribute("provinceFreightCustoms", provinceFreightCustoms);
		}else{
			FreightTemplate ft = new FreightTemplate();
			ft.setFirstFreight(new BigDecimal(0));
			ft.setAdditionalFreight(new BigDecimal(0));
			model.addAttribute("freightTemplate", ft);
		}
		return "freightTemplate/editFreightTemplate";
	}
	
	/**
	 * 获取区域
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getAreas")
	@ResponseBody
	public ResponseMsg getAreas(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String freightTemplateId = request.getParameter("freightTemplateId");
		String provinceFreightId = request.getParameter("provinceFreightId");
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("mchtId", this.getSessionMchtInfo(request).getId());
		if(StringUtil.isEmpty(freightTemplateId)){
			AreaExample example = new AreaExample();
			AreaExample.Criteria c = example.createCriteria();
			c.andStatusEqualTo("A");
			c.andAreaTypeEqualTo("1");
			List<Area> areas = areaService.selectByExample(example);
			returnData.put("areas",areas);
		}else{
			map.put("freightTemplateId", freightTemplateId);
			if(!StringUtil.isEmpty(provinceFreightId)){
				map.put("provinceFreightId", provinceFreightId);
			}
			String areaIds = provinceFreightService.getAreaIds(map);
			List<Integer> areaIdList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(areaIds)){
				String[] areaIdArray = areaIds.split(",");
				for(int i=0;i<areaIdArray.length;i++){
					areaIdList.add(Integer.parseInt(areaIdArray[i]));
				}
			}
			AreaExample e = new AreaExample();
			AreaExample.Criteria c = e.createCriteria();
			c.andStatusEqualTo("A");
			c.andAreaTypeEqualTo("1");
			if(areaIdList!=null && areaIdList.size()>0){
				c.andAreaIdNotIn(areaIdList);
			}
			List<Area> areas = areaService.selectByExample(e);
			returnData.put("areas",areas);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 *添加/编辑指定省份运费页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/editProvinceFreight")
	public String editProvinceFreight(Model model, HttpServletRequest request) {
		String provinceFreightId = request.getParameter("provinceFreightId");
		if(!StringUtil.isEmpty(provinceFreightId)){
			ProvinceFreight provinceFreight = provinceFreightService.selectByPrimaryKey(Integer.parseInt(provinceFreightId));
			model.addAttribute("provinceFreight", provinceFreight);
		}
		AreaExample example = new AreaExample();
		AreaExample.Criteria c = example.createCriteria();
		c.andStatusEqualTo("A");
		c.andAreaTypeEqualTo("1");
		List<Area> areas = areaService.selectByExample(example);
		model.addAttribute("areas", areas);
		return "freightTemplate/editProvinceFreight";
	}

	/**
	 *获取省份运费表信息
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/getProvinceFreightInfo")
	public ResponseMsg getProvinceFreightInfo(HttpServletRequest request) {
		String getProvinceFreightInfo = request.getParameter("getProvinceFreightInfo");
		ProvinceFreight provinceFreight = provinceFreightService.selectByPrimaryKey(Integer.parseInt(getProvinceFreightInfo));
		provinceFreightService.save(provinceFreight);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("provinceFreight", provinceFreight);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}

	/**
	 * 删除指定省份运费
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/deleteProvinceFreight")
	@ResponseBody
	public ResponseMsg deleteProvinceFreight(HttpServletRequest request) {
		try {
			String provinceFreightId = request.getParameter("provinceFreightId");
			ProvinceFreight provinceFreight = provinceFreightService.selectByPrimaryKey(Integer.parseInt(provinceFreightId));
			provinceFreight.setUpdateDate(new Date());
			provinceFreight.setUpdateBy(this.getSessionMchtInfo(request).getId());
			provinceFreight.setDelFlag("1");//删除
			provinceFreightService.updateByPrimaryKeySelective(provinceFreight);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 保存指定省份运费
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/saveProvinceFreight")
	@ResponseBody
	public ResponseMsg saveProvinceFreight(HttpServletRequest request) throws ParseException {
		String freightTemplateId = request.getParameter("freightTemplateId");
		String id = request.getParameter("id");
		String areaIds = request.getParameter("areaIds");
		String firstFreight = request.getParameter("firstFreight");
		String additionalFreight = request.getParameter("additionalFreight");
		ProvinceFreight provinceFreight = new ProvinceFreight();
		if(!StringUtil.isEmpty(id)){
			provinceFreight = provinceFreightService.selectByPrimaryKey(Integer.parseInt(id));
		}else{
			provinceFreight.setDelFlag("0");
			provinceFreight.setCreateBy(this.getSessionMchtInfo(request).getId());
			provinceFreight.setCreateDate(new Date());
			provinceFreight.setMchtId(this.getSessionMchtInfo(request).getId());
			provinceFreight.setFreightTemplateId(Integer.parseInt(freightTemplateId));
		}
		provinceFreight.setUpdateBy(this.getSessionMchtInfo(request).getId());
		provinceFreight.setUpdateDate(new Date());
		provinceFreight.setAreaIds(areaIds);
		provinceFreight.setFirstFreight(new BigDecimal(firstFreight));
		provinceFreight.setAdditionalFreight(new BigDecimal(additionalFreight));
		provinceFreightService.save(provinceFreight);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("provinceFreightId", provinceFreight.getId());
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
	/**
	 * 保存运费模板
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/saveFreightTemplate")
	@ResponseBody
	public ResponseMsg saveFreightTemplate(HttpServletRequest request) throws ParseException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String firstFreight = request.getParameter("firstFreight");
		String additionalFreight = request.getParameter("additionalFreight");
		String provinceFreightJsonStr = request.getParameter("provinceFreightJsonStr");
		String fullNumber = request.getParameter("fullNumber");
		String fullReductionFreight = request.getParameter("fullReductionFreight");
		String isFullReduction = request.getParameter("isFullReduction");
		FreightTemplate freightTemplate = new FreightTemplate();
		List<ProvinceFreight> provinceFreights = new ArrayList<ProvinceFreight>();
		if(!StringUtil.isEmpty(id)){
			freightTemplate = freightTemplateService.selectByPrimaryKey(Integer.parseInt(id));
		}else{
			freightTemplate.setMchtId(this.getSessionMchtInfo(request).getId());
			freightTemplate.setDelFlag("0");
			freightTemplate.setCreateDate(new Date());
			freightTemplate.setCreateBy(this.getSessionMchtInfo(request).getId());
		}
		freightTemplate.setName(name);
		freightTemplate.setFirstFreight(new BigDecimal(firstFreight));
		freightTemplate.setAdditionalFreight(new BigDecimal(additionalFreight));
		freightTemplate.setFullNumber(StringUtils.isEmpty(fullNumber)?null:Integer.parseInt(fullNumber));
		freightTemplate.setFullReductionFreight(StringUtils.isEmpty(fullReductionFreight)?null:new BigDecimal(fullReductionFreight));
		freightTemplate.setIsFullReductionFreight(isFullReduction);
		freightTemplate.setUpdateBy(this.getSessionMchtInfo(request).getId());
		freightTemplate.setUpdateDate(new Date());
		if(!StringUtil.isEmpty(provinceFreightJsonStr)){
			JSONArray ja = JSONArray.fromObject(provinceFreightJsonStr);
			for(int i=0;i<ja.size();i++){
				JSONObject jo = (JSONObject)ja.get(i);
				String provinceFreightId = jo.getString("provinceFreightId");
				if(StringUtil.isEmpty(provinceFreightId) || provinceFreightId.equals("0")){
					String areaIds = jo.getString("areaIds");
					String ff = jo.getString("firstFreight");
					String af = jo.getString("additionalFreight");
					ProvinceFreight provinceFreight = new ProvinceFreight();
					provinceFreight.setMchtId(this.getSessionMchtInfo(request).getId());
					provinceFreight.setAreaIds(areaIds);
					provinceFreight.setFirstFreight(new BigDecimal(ff));
					provinceFreight.setAdditionalFreight(new BigDecimal(af));
					provinceFreight.setCreateBy(this.getSessionMchtInfo(request).getId());
					provinceFreight.setCreateDate(new Date());
					provinceFreight.setDelFlag("0");
					provinceFreights.add(provinceFreight);
				}
			}
		}
		freightTemplateService.save(freightTemplate,provinceFreights);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,freightTemplate);
	}
	
	/**
	 * 获取指定省份运费
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getProvinceFreightCustoms")
	@ResponseBody
	public ResponseMsg getProvinceFreightCustoms(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String freightTemplateId = request.getParameter("freightTemplateId");
		ProvinceFreightExample e = new ProvinceFreightExample();
		ProvinceFreightExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andFreightTemplateIdEqualTo(Integer.parseInt(freightTemplateId));
		List<ProvinceFreightCustom> provinceFreightCustoms = provinceFreightService.selectByCustomExample(e);
		returnData.put("provinceFreightCustoms",provinceFreightCustoms);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 删除运费模板
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/deleteFreightTemplate")
	@ResponseBody
	public ResponseMsg deleteFreightTemplate(HttpServletRequest request) {
		try {
			String freightTemplateId = request.getParameter("freightTemplateId");
			ProductExample pe = new ProductExample();
			pe.createCriteria().andDelFlagEqualTo("0").andFreightTemplateIdEqualTo(Integer.parseInt(freightTemplateId));
			List<Product> products = productService.selectByExample(pe);
			if(products!=null && products.size()>0){
				return new ResponseMsg(ResponseMsg.ERROR, "当前模板下有关联商品，请将商品退出模板");
			}else{
				FreightTemplateExample e = new FreightTemplateExample();
				e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(freightTemplateId));
				FreightTemplate ft = new FreightTemplate();
				ft.setDelFlag("1");
				ft.setUpdateDate(new Date());
				ft.setUpdateBy(this.getSessionUserInfo(request).getId());
				freightTemplateService.updateByExampleSelective(ft, e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, "删除成功");
	}
	
	/**
	 * 运费模板--商品管理列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/freightTemplateProductIndex")
	public String freightTemplateProductIndex(Model model, HttpServletRequest request) {
		String freightTemplateId = request.getParameter("freightTemplateId");
		if(!StringUtil.isEmpty(freightTemplateId)){
			FreightTemplate freightTemplate = freightTemplateService.selectByPrimaryKey(Integer.parseInt(freightTemplateId));
			model.addAttribute("freightTemplateName", freightTemplate.getName());
		}
		model.addAttribute("freightTemplateId", freightTemplateId);
		model.addAttribute("searchTemplateType", request.getParameter("searchTemplateType"));
		return "freightTemplate/freightTemplateProductIndex";
	}
	
	/**
	 * 运费模板--商品管理列表数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getFreightTemplateProductList")
	@ResponseBody
	public ResponseMsg getFreightTemplateProductList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductCustomExample productCustomExample = new ProductCustomExample();
		productCustomExample.setOrderByClause("freight_template_id desc,id desc");
		ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
		productCustomCriteria.andDelFlagEqualTo("0");
		productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		Integer freightTemplateId = Integer.parseInt(request.getParameter("freightTemplateId"));
		String searchTemplateType = request.getParameter("searchTemplateType");
		if(!StringUtil.isEmpty(searchTemplateType)){
			if(searchTemplateType.equals("0")){
				productCustomCriteria.andFreightTemplateIdEqualTo(freightTemplateId);
			}else if(searchTemplateType.equals("1")){
				productCustomCriteria.andFreightTemplateIdIsNull();
			}else if(searchTemplateType.equals("2")){
				productCustomCriteria.andFreightTemplateIdIsNotNull().andFreightTemplateIdNotEqualTo(freightTemplateId);
			}
		}
		if (!StringUtil.isEmpty(request.getParameter("searchKeywrod"))) {
			if ("1".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andNameLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			if ("2".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andArtNoLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			if ("3".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andCodeEqualTo(request.getParameter("searchKeywrod"));
			}
			if ("4".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andRemarksLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			
		}
		String artNos = request.getParameter("artNos");
		if(!StringUtil.isEmpty(artNos)){
			List<String> artNoList = Arrays.asList(getPara("artNos").split("\r\n"));
			productCustomCriteria.andArtNoIn(artNoList);
		}
		int totalCount = productService.countProductCustomByExample(productCustomExample);
		productCustomExample.setLimitStart(page.getLimitStart());
		productCustomExample.setLimitSize(page.getLimitSize());
		List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
		returnData.put("Rows", productCustoms);
		returnData.put("Total", totalCount);
		
		int currentTemplateCount = productService.countCurrentTemplate(freightTemplateId);
		int noTemplateCount = productService.countNoTemplate(this.getSessionMchtInfo(request).getId());
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("mchtId", this.getSessionMchtInfo(request).getId());
		map.put("freightTemplateId", freightTemplateId);
		int otherTemplateCount = productService.countOtherTemplateCount(map);
		returnData.put("currentTemplateCount", currentTemplateCount);
		returnData.put("noTemplateCount", noTemplateCount);
		returnData.put("otherTemplateCount", otherTemplateCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 退出模板
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/outFreightTemplate")
	@ResponseBody
	public ResponseMsg outFreightTemplate(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			Integer freightTemplateId = Integer.parseInt(request.getParameter("freightTemplateId"));
			String productIds = request.getParameter("productIds");
			List<Integer> productIdList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(productIds)){
				String[] productIdsArray = productIds.split(",");
				for(String productId:productIdsArray){
					productIdList.add(Integer.parseInt(productId));
				}
			}
			if(productIdList.size() == 0){
				return new ResponseMsg(ResponseMsg.ERROR, "请先选中要退出模板的商品");
			}else{
				productService.outFreightTemplate(productIdList);
				int currentTemplateCount = productService.countCurrentTemplate(freightTemplateId);
				returnData.put("currentTemplateCount", currentTemplateCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
	/**
	 * 选择模板
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectFreightTemplate")
	@ResponseBody
	public ResponseMsg selectFreightTemplate(HttpServletRequest request) {
		try {
			String productIds = request.getParameter("productIds");
			String freightTemplateId = request.getParameter("freightTemplateId");
			List<Integer> productIdList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(productIds)){
				String[] productIdsArray = productIds.split(",");
				for(String productId:productIdsArray){
					productIdList.add(Integer.parseInt(productId));
				}
			}
			if(productIdList.size() == 0){
				return new ResponseMsg(ResponseMsg.ERROR, "请先选中要选择模板的商品");
			}else{
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("freightTemplateId", freightTemplateId);
				map.put("list", productIdList);
				productService.selectFreightTemplate(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 保存商品公告
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/saveProductNoticePic")
	@ResponseBody
	public ResponseMsg saveProductNoticePic(HttpServletRequest request) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String productNoticePic = request.getParameter("productNoticePic");
		String fullNumber = request.getParameter("fullNumber");
		String fullReductionFreight = request.getParameter("fullReductionFreight");
		String isFullReduction = request.getParameter("isFullReduction");
		if(!StringUtil.isEmpty(id)){
			FreightTemplate freightTemplate = freightTemplateService.selectByPrimaryKey(Integer.parseInt(id));
			freightTemplate.setProductNoticePic(productNoticePic);
			freightTemplate.setFullNumber(StringUtils.isEmpty(fullNumber)?null:Integer.parseInt(fullNumber));
			freightTemplate.setFullReductionFreight(StringUtils.isEmpty(fullReductionFreight)?null:new BigDecimal(fullReductionFreight));
			freightTemplate.setIsFullReductionFreight(isFullReduction);
			freightTemplate.setUpdateDate(new Date());
			freightTemplate.setUpdateBy(this.getSessionUserInfo(request).getId());
			freightTemplateService.updateByPrimaryKeySelective(freightTemplate);
		}else{
			String name = request.getParameter("name");
			String firstFreight = request.getParameter("firstFreight");
			String additionalFreight = request.getParameter("additionalFreight");
			String provinceFreightJsonStr = request.getParameter("provinceFreightJsonStr");
			FreightTemplate freightTemplate = new FreightTemplate();
			List<ProvinceFreight> provinceFreights = new ArrayList<ProvinceFreight>();
			freightTemplate.setMchtId(this.getSessionMchtInfo(request).getId());
			freightTemplate.setDelFlag("0");
			freightTemplate.setCreateDate(new Date());
			freightTemplate.setCreateBy(this.getSessionMchtInfo(request).getId());
			freightTemplate.setName(name);
			freightTemplate.setFirstFreight(new BigDecimal(firstFreight));
			freightTemplate.setAdditionalFreight(new BigDecimal(additionalFreight));
			freightTemplate.setFullNumber(StringUtils.isEmpty(fullNumber)?null:Integer.parseInt(fullNumber));
			freightTemplate.setFullReductionFreight(StringUtils.isEmpty(fullReductionFreight)?null:new BigDecimal(fullReductionFreight));
			freightTemplate.setIsFullReductionFreight(isFullReduction);
			freightTemplate.setUpdateBy(this.getSessionMchtInfo(request).getId());
			freightTemplate.setUpdateDate(new Date());
			if(!StringUtil.isEmpty(provinceFreightJsonStr)){
				JSONArray ja = JSONArray.fromObject(provinceFreightJsonStr);
				for(int i=0;i<ja.size();i++){
					JSONObject jo = (JSONObject)ja.get(i);
					String provinceFreightId = jo.getString("provinceFreightId");
					if(StringUtil.isEmpty(provinceFreightId) || provinceFreightId.equals("0")){
						String areaIds = jo.getString("areaIds");
						String ff = jo.getString("firstFreight");
						String af = jo.getString("additionalFreight");
						ProvinceFreight provinceFreight = new ProvinceFreight();
						provinceFreight.setMchtId(this.getSessionMchtInfo(request).getId());
						provinceFreight.setAreaIds(areaIds);
						provinceFreight.setFirstFreight(new BigDecimal(ff));
						provinceFreight.setAdditionalFreight(new BigDecimal(af));
						provinceFreight.setCreateBy(this.getSessionMchtInfo(request).getId());
						provinceFreight.setCreateDate(new Date());
						provinceFreight.setDelFlag("0");
						provinceFreights.add(provinceFreight);
					}
				}
			}
			freightTemplate.setProductNoticePic(productNoticePic);
			freightTemplateService.save(freightTemplate,provinceFreights);
			returnData.put("freightTemplateId", freightTemplate.getId());
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
	/**
	 * 删除商品公告
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/deleteProductNoticePic")
	@ResponseBody
	public ResponseMsg deleteProductNoticePic(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			FreightTemplate freightTemplate = freightTemplateService.selectByPrimaryKey(Integer.parseInt(id));
			freightTemplate.setUpdateDate(new Date());
			freightTemplate.setUpdateBy(this.getSessionMchtInfo(request).getId());
			freightTemplate.setProductNoticePic("");
			freightTemplateService.updateByPrimaryKeySelective(freightTemplate);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
}
