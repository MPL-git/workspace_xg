package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicCustom;
import com.jf.entity.MchtShopDynamicCustomExample;
import com.jf.entity.MchtShopDynamicExample.Criteria;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.service.MemberDynamicService;
import com.jf.service.ProductService;
import com.jf.service.ShopDynamicService;
import com.jf.service.SingleProductActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/shopDynamic")
public class shopDynamicController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(shopDynamicController.class);
	
	@Autowired
	@Qualifier("shopDynamicService")
	private ShopDynamicService shopDynamicService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private MemberDynamicService memberDynamicService;
	
	@Resource
	private SingleProductActivityService singleProductActivityService;

	
	/**
	 * 动态管理列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopDynamicInfoIndex")
	public String shopDynamicInfoIndex(Model model, HttpServletRequest request) {
		InputStream stream = shopDynamicController.class.getResourceAsStream("/config.properties");
		String defaultPath=null;
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("previewServerUrl");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(defaultPath==null){
			return "/shopDynamic/shopDynamicInfoIndex";
		}
		String url = defaultPath+"/webApp/xgbuy/views/index.html?redirect_url=activity/novaplan/pages/newsfeed/index.html?id=";
		model.addAttribute("url",url);
		return "/shopDynamic/shopDynamicInfoIndex";
	}
	
	/**
	 * 动态管理列表数据
	 *
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getShopDynamicInfoIndexList")
	@ResponseBody
	public ResponseMsg getShopDynamicInfoIndexList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Integer id = this.getMchtInfo().getId();
		
		MchtShopDynamicCustomExample e = new MchtShopDynamicCustomExample();
		Criteria c = e.createCriteria();
		
		c.andMchtIdEqualTo(id);
		e.setLimitStart(page.getLimitStart());
		e.setLimitSize(page.getLimitSize());
		e.setOrderByClause("t.create_date DESC");
		
		String searchContent = request.getParameter("search_content");
		if(!StringUtil.isEmpty(searchContent)){
			c.andContentLike("%"+searchContent.trim()+"%");
		}
		
		String searchStatus = request.getParameter("search_status");
		if(!StringUtil.isEmpty(searchStatus)){
			if(searchStatus.equals("3")){
				c.andDelFlagEqualTo("1");
			}else{
				c.andAuditStatusEqualTo(searchStatus);
				c.andDelFlagEqualTo("0");
			}
			
		}else{
			c.andDelFlagEqualTo("0");
		}
		String dynamicDateBegin = request.getParameter("dynamicDateBegin");
		String dynamicDateEnd = request.getParameter("dynamicDateEnd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtil.isEmpty(dynamicDateBegin)){
			c.andCreateDateGreaterThanOrEqualTo(sdf.parse((dynamicDateBegin+" 00:00:00")));
		}
		if(!StringUtil.isEmpty(dynamicDateEnd)){
			c.andCreateDateLessThanOrEqualTo(sdf.parse((dynamicDateEnd+" 23:59:59")));
		}
		
		int totalCount = shopDynamicService.countShopDynamicCustomByExample(e);
		List<MchtShopDynamicCustom> list = shopDynamicService.selectShopDynamicCustomByExample(e);
		returnData.put("Rows", list);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		
	}
	
	
	/**
	 *  删除动态
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteDynamic")
	@ResponseBody
	public ResponseMsg delete(HttpServletRequest request) {
		try {
			Integer updateBy=this.getSessionMchtInfo(request).getId();
			shopDynamicService.deleteDynamicById(request,updateBy);		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	
	/**
	 * 添加动态列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopDynamicAdd")
	public String shopDynamicAdd(HttpServletRequest request) {
		String img = request.getParameter("img");
		if (!StringUtil.isEmpty(img)) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("img", img);
			modelAndView.setViewName("/shopDynamic/ShopDynamicChoose");
		}
		return "/shopDynamic/shopDynamicEdit";
	}
	
	/**
	 * 转到商品选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopDynamicChoose")
	public ModelAndView shopDynamicChoose(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String ids = request.getParameter("ids");
		if(!StringUtil.isEmpty(ids)){
		modelAndView.addObject("ids", ids);
		}
		modelAndView.setViewName("/shopDynamic/shopDynamicChoose");
		return modelAndView;
	}

	/**
	 * 修改商品
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopDynamicEdit")
	public String shopDynamicEdit(Model model, HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		if (id != null ) {
			
			MchtShopDynamic shopDynamic = shopDynamicService.selectByPrimaryKey(id);
			model.addAttribute("shopDynamic",shopDynamic);
			ArrayList<Integer> list = new ArrayList<>();
			String productIds = shopDynamic.getProductIds();

			if(!StringUtil.isEmpty(productIds)){
			String[] spilt = productIds.split(",");
			for (int i = 0; i < spilt.length; i++) {
				list.add(Integer.parseInt(spilt[i]));
			}
			ProductCustomExample productCustomExample = new ProductCustomExample();
			ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
			productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			productCustomExample.setOrderByClause("id desc");
			productCustomCriteria.andIdIn(list);
			List<ProductCustom> products = productService.selectProductCustomByExample(productCustomExample);
			model.addAttribute("productList",products);
			model.addAttribute("productIds",productIds);
			}
		}
			
		return "/shopDynamic/shopDynamicEdit";
	}
	
	
	/**
	 * 动态详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopDynamicDetail")
	public String shopDynamicDetail(Model model, HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		if (id != null) {
			MchtShopDynamicCustomExample e = new MchtShopDynamicCustomExample();
			e.createCriteria().andIdEqualTo(id);
			e.setLimitSize(1);
			e.setLimitStart(0);
			List<MchtShopDynamicCustom> ShopDynamicCustoms = shopDynamicService.selectShopDynamicCustomByExample(e);
			MchtShopDynamicCustom ShopDynamicCustom = ShopDynamicCustoms.get(0);

			model.addAttribute("ShopDynamicCustom",ShopDynamicCustom);
			ArrayList<Integer> list = new ArrayList<>();
			String productIds = ShopDynamicCustom.getProductIds();
			if(!StringUtil.isEmpty(productIds)){
				String[] spilt = productIds.split(",");
				for (int i = 0; i < spilt.length; i++) {
					list.add(Integer.parseInt(spilt[i]));
				}
				ProductCustomExample productCustomExample = new ProductCustomExample();
				ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();

				productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				productCustomExample.setOrderByClause("id desc");
				productCustomCriteria.andIdIn(list);
				List<ProductCustom> products = productService.selectProductCustomByExample(productCustomExample);
				model.addAttribute("productList",products);
				model.addAttribute("productIds",productIds);
			}
			if (ShopDynamicCustom.getAuditDate()!=null) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
				String format = simpleDateFormat.format(ShopDynamicCustom.getAuditDate());
				model.addAttribute("auditDate",format);
			}

			if (StrKit.notBlank(ShopDynamicCustom.getRejectionReason())){
				model.addAttribute("rejectionReason",ShopDynamicCustom.getRejectionReason());
			}
		}
			
		return "/shopDynamic/shopDynamicDetail";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getProductList")
	@ResponseBody
	public ResponseMsg getProductList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductCustomExample productCustomExample = new ProductCustomExample();
		ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();

		productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		productCustomExample.setOrderByClause("t.create_date DESC");
		productCustomCriteria.andDelFlagEqualTo("0");
		productCustomCriteria.andSaleTypeEqualTo("1");
		productCustomCriteria.andStatusEqualTo("1");
//      动态商品查询
		String singleName = "";
		String singleArtNo = "";
		String singleCode = "";		
		if (!StringUtil.isEmpty(request.getParameter("search_name"))) {
			productCustomCriteria.andNameLike("%" + request.getParameter("search_name").trim() + "%");
			singleName = "%" + request.getParameter("search_name").trim() + "%";
		}
		if (!StringUtil.isEmpty(request.getParameter("search_number"))) {
			productCustomCriteria.andArtNoEqualTo(request.getParameter("search_number").trim());
			singleArtNo = request.getParameter("search_number").trim();
		}
		if (!StringUtil.isEmpty(request.getParameter("search_id"))) {
			productCustomCriteria.andCodeEqualTo(request.getParameter("search_id").trim());
			singleCode = request.getParameter("search_id").trim();
		}
		
		productCustomCriteria.andSingleProductActivityOnLine(this.getSessionMchtInfo(request).getId(),singleName,singleArtNo,singleCode);
		int totalCount = productService.countProductCustomByExample(productCustomExample);
		
		productCustomExample.setLimitStart(page.getLimitStart());
		productCustomExample.setLimitSize(page.getLimitSize());
		List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);

		
		for (ProductCustom productCustom:productCustoms) {
			if(!StringUtil.isEmpty(productCustom.getPic()) && !productCustom.getPic().contains("http")){
				productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
			}
		}
		returnData.put("Rows", productCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}
	
	/**
	 * 提交动态列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopDynamicCommit")
	@ResponseBody
	public ResponseMsg shopDynamicCommit(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)) {
			 MchtShopDynamic shopDynamic = new MchtShopDynamic();
			 shopDynamic.setId(Integer.parseInt(id));
			if(!StringUtil.isEmpty(request.getParameter("pic"))){
				 shopDynamic.setTopCover(request.getParameter("pic"));
			}
			if(!StringUtil.isEmpty(request.getParameter("dynamicContent"))){
				shopDynamic.setContent(request.getParameter("dynamicContent"));
			}
			if(!StringUtil.isEmpty(request.getParameter("ids"))){
				shopDynamic.setProductIds(request.getParameter("ids"));
			}
			if(StringUtil.isEmpty(request.getParameter("ids"))){
				System.out.println(Integer.parseInt(id));
				shopDynamicService.setProductIdsNull(Integer.parseInt(id));
			}
			shopDynamic.setUpdateDate(new Date());
			shopDynamic.setUpdateBy(this.getSessionMchtInfo(request).getId());
			shopDynamic.setAuditStatus("0");
			shopDynamicService.updateByPrimaryKeySelective(shopDynamic);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		}else {
			try {
			MchtShopDynamic mchtShopDynamic = new MchtShopDynamic();
			mchtShopDynamic.setCreateDate(new Date());
			mchtShopDynamic.setCreateBy(this.getSessionUserInfo(request).getId());
			mchtShopDynamic.setAuditStatus("0");
			mchtShopDynamic.setMchtId(this.getMchtInfo().getId());
			String topCover = request.getParameter("pic");
			mchtShopDynamic.setTopCover(topCover);
			String content = request.getParameter("dynamicContent");
			mchtShopDynamic.setContent(content);
			String ids = request.getParameter("ids");
			mchtShopDynamic.setProductIds(ids);
			mchtShopDynamic.setWeight(99999);
			mchtShopDynamic.setDelFlag("0");
			shopDynamicService.insertSelective(mchtShopDynamic);
			} catch (ArgException arge) {
				return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
			}

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		}
		}
		
	
	}

