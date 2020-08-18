package com.jf.controller;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SpOfficeMapper;
import com.jf.dao.SpUserMapper;
import com.jf.entity.*;
import com.jf.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

@Controller
public class CloudProductController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(CloudProductController.class);

	@Resource
	private CloudProductService cloudProductService;
	
	@Resource
	private MchtSupplierUserService mchtSupplierUserService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private SpOfficeMapper spOfficeMapper;
	
	@Resource
	private SpUserMapper spUserMapper;
	
	@Resource
	private CloudProductAfterTempletService cloudProductAfterTempletService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private CloudProductItemService cloudProductItemService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private SingleProductActivityService singleProductActivityService;
	
	/**
	 * 供应商SKU池列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cloudProduct/cloudProductIndex")
	public String cooperationChangeApplyIndex(Model model, HttpServletRequest request) {
		MchtSupplierUserCustomExample e = new MchtSupplierUserCustomExample();
		MchtSupplierUserCustomExample.MchtSupplierUserCustomCriteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andStatusEqualTo("1");
		c.andSupplierStatusEqualTo("1");
		List<MchtSupplierUserCustom> mchtSupplierUserCustoms = mchtSupplierUserService.selectMchtSupplierUserCustomByExample(e);
		model.addAttribute("mchtSupplierUserCustoms", mchtSupplierUserCustoms);
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		return "cloudProduct/cloudProductIndex";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/cloudProduct/getCloudProductList")
	@ResponseBody
	public ResponseMsg getCloudProductList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String productBrandId = request.getParameter("productBrandId");
		String artNo = request.getParameter("artNo");
		String status = request.getParameter("status");
		String supplierUserId = request.getParameter("supplierUserId");
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("mchtId", this.getSessionMchtInfo(request).getId());
		if(!StringUtil.isEmpty(productBrandId)){
			map.put("productBrandId", Integer.parseInt(productBrandId));
		}
		if(!StringUtil.isEmpty(artNo)){
			map.put("artNo", "%"+artNo.trim()+"%");
		}
		if(!StringUtil.isEmpty(status)){
			map.put("status", status);
		}
		if(!StringUtil.isEmpty(supplierUserId)){
			map.put("supplierUserId", Integer.parseInt(supplierUserId));
		}
		int totalCount = cloudProductService.countByCustomExample(map);
		map.put("limitStart", page.getLimitStart());
		map.put("limitSize", page.getLimitSize());
		List<CloudProductCustom> cloudProductCustoms = cloudProductService.selectByCustomExample(map);
		returnData.put("Rows", cloudProductCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}
	
	/**
	 * 绑定供应商页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cloudProduct/toBind")
	public String toBind(Model model, HttpServletRequest request) {
		return "cloudProduct/toBind";
	}
	
	/**
	 * 绑定供应商
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cloudProduct/bind")
	@ResponseBody
	public ResponseMsg bind(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		SpUserExample sue = new SpUserExample();
		sue.createCriteria().andDelFlagEqualTo("0").andLoginNameEqualTo(userName);
		List<SpUser> spUsers = spUserMapper.selectByExample(sue);
		if(spUsers!=null && spUsers.size()>0){
			SpUser spUser = spUsers.get(0);
			MchtSupplierUserExample e = new MchtSupplierUserExample();
			e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andSpOfficeIdEqualTo(spUser.getCompanyId());
			List<MchtSupplierUser> mchtSupplierUsers = mchtSupplierUserService.selectByExample(e);
			if(mchtSupplierUsers!=null && mchtSupplierUsers.size()>0){
				return new ResponseMsg(ResponseMsg.ERROR, "对不起，该供应商之前绑定过，不能重复绑定，如需调整请联系技术管理员");
			}else{
				SpOffice spOffice = spOfficeMapper.selectByPrimaryKey(spUser.getCompanyId());
				if(spOffice.getStatus().equals("1")){
					MchtSupplierUser mchtSupplierUser = new MchtSupplierUser();
					mchtSupplierUser.setDelFlag("0");
					mchtSupplierUser.setCreateBy(this.getSessionUserInfo(request).getId());
					mchtSupplierUser.setCreateDate(new Date());
					mchtSupplierUser.setMchtId(this.getSessionMchtInfo(request).getId());
					mchtSupplierUser.setSpOfficeId(spUser.getCompanyId());
					mchtSupplierUser.setStatus("1");
					mchtSupplierUserService.insertSelective(mchtSupplierUser);
				}else{
					return new ResponseMsg(ResponseMsg.ERROR, "对不起，该供应商不能绑定");
				}
			}
		}else{
			return new ResponseMsg(ResponseMsg.ERROR, "供应商不存在，无法绑定");
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 查看商品页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cloudProduct/toViewCloudProduct")
	public String toViewCloudProduct(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		CloudProductCustom cloudProductCustom = cloudProductService.selectCustomById(Integer.parseInt(id));
		model.addAttribute("cloudProductCustom", cloudProductCustom);
		CloudProductAfterTempletCustom cloudProductAfterTempletCustom = cloudProductAfterTempletService.selectCustomByPrimaryKey(cloudProductCustom.getCloudProductAfterTempletId());
		model.addAttribute("cloudProductAfterTempletCustom", cloudProductAfterTempletCustom);
		CloudProductItemCustomExample e = new CloudProductItemCustomExample();
		e.createCriteria().andDelFlagEqualTo("0").andCloudProductIdEqualTo(Integer.parseInt(id));
		List<CloudProductItemCustom> cloudProductItemCustoms = cloudProductItemService.selectByCustomExample(e);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cloudProductId", Integer.parseInt(id));
		map.put("mchtId", this.getSessionMchtInfo(request).getId());
		List<HashMap<String,Object>> relatedProductList = cloudProductService.getRelatedProduct(map);
		model.addAttribute("cloudProductItemCustoms", cloudProductItemCustoms);
		model.addAttribute("relatedProductList", relatedProductList);
		InputStream stream = CloudProductController.class.getResourceAsStream("/config.properties");
		String cloudProductSkuPicBaseUrl="";
		try {
			Properties properties = new Properties();
			properties.load(stream);
			cloudProductSkuPicBaseUrl = properties.getProperty("cloudProductSkuPicBaseUrl");
			stream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		model.addAttribute("cloudProductSkuPicBaseUrl", cloudProductSkuPicBaseUrl);
		return "cloudProduct/toViewCloudProduct";
	}
	
	/**
	 * 商品列表,驳回
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cloudProduct/toProductList")
	public String toProductList(Model model, HttpServletRequest request) {
		model.addAttribute("productIds", request.getParameter("productIds"));
		model.addAttribute("shopStatus", this.getSessionMchtInfo(request).getShopStatus());
		return "cloudProduct/toProductList";
	}

	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/cloudProduct/getProductList")
	@ResponseBody
	public ResponseMsg getProductList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductCustomExample productCustomExample = new ProductCustomExample();
		ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
		productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		productCustomExample.setOrderByClause("id desc");
		productCustomCriteria.andDelFlagEqualTo("0");
		String productIds = request.getParameter("productIds");
		if(!StringUtil.isEmpty(productIds)){
			List<Integer> productIdList = new ArrayList<Integer>();
			String[] productIdArray = productIds.split(",");
			for(String productId:productIdArray){
				productIdList.add(Integer.parseInt(productId));
			}
			if(productIdList!=null && productIdList.size()>0){
				productCustomCriteria.andIdIn(productIdList);
			}
		}
		int totalCount = productService.countProductCustomByExample(productCustomExample);
		productCustomExample.setLimitStart(page.getLimitStart());
		productCustomExample.setLimitSize(page.getLimitSize());
		List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
		for (ProductCustom productCustom:productCustoms) {
			if(!StringUtil.isEmpty(productCustom.getPic()) && !productCustom.getPic().contains("http")){
				productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
			}
			
			//判断商品是否可删除
			if("1".equals(productCustom.getStatus())){//上架状态的都不可删除
				productCustom.setCanDelete(false);
			}else{
				if("1".equals(productCustom.getSaleType())){//品牌款，可报名就可删除
					if("0".equals(productCustom.getActivityStatusDesc())){
						productCustom.setCanDelete(true);
					}else{
						productCustom.setCanDelete(false);
					}
				}else{//单品款  从未报名（该商品不存在[未关闭][未删除]的报名）就可删除
					SingleProductActivityExample singleProductActivityExample=new SingleProductActivityExample();
					singleProductActivityExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productCustom.getId()).andIsCloseEqualTo("0");
					if(singleProductActivityService.countByExample(singleProductActivityExample)>0)	{
						productCustom.setCanDelete(false);
					}else{
						productCustom.setCanDelete(true);
					}
				}
			}
			
		}
		returnData.put("Rows", productCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
}
