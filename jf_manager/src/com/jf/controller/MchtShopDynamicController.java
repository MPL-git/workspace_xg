package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicCustom;
import com.jf.entity.MchtShopDynamicCustomExample;
import com.jf.entity.MchtShopDynamicExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.SysStaffInfo;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtShopDynamicService;
import com.jf.service.ProductService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.service.ViolateOrderService;
import com.jf.vo.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
public class MchtShopDynamicController extends BaseController {
	
	private static final Log logger = LogFactory.getLog(MchtInfoService.class);
	
	@Resource
	private ViolateOrderService violateOrderService;
	
	@Resource
	private MchtShopDynamicService mchtShopDynamicService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 店铺动态列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws AlipayApiException 
	 */
	@RequestMapping(value = "/mchtShopDynamic/list.shtml")
	public ModelAndView manuallyViolateOrderList(HttpServletRequest request){
		String rtPage = "/mchtShopDynamic/mchtShopDynamic_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("defaultAuditStatus", "0");
		
		// 当前角色负责的主营类目 add by huangdl 2019-07-16
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		resMap.put("productTypes", sysStaffProductTypeService.selectByStaffId(staffId));
		
		InputStream stream = MchtShopDynamicController.class.getResourceAsStream("/base_config.properties");
		String mUrl="";
		try {
			Properties properties = new Properties();
			properties.load(stream);
			mUrl = properties.getProperty("mUrl");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		resMap.put("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=activity/novaplan/pages/newsfeed/index.html?id=");
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 店铺动态列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopDynamic/mchtShopDynamicData.shtml")
	@ResponseBody
	public Map<String, Object> mchtShopDynamicData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtShopDynamicCustomExample e = new MchtShopDynamicCustomExample();
			e.setOrderByClause("IFNULL(t.weight,99999) desc,t.id desc");
			MchtShopDynamicCustomExample.MchtShopDynamicCustomCriteria c = e.createCriteria();
			String mchtCode = request.getParameter("mchtCode");
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			String auditStatus = request.getParameter("auditStatus");
			String createDateBegin = request.getParameter("createDateBegin");
			String createDateEnd = request.getParameter("createDateEnd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String productTypeId = request.getParameter("productTypeId");
			
			if(!StringUtils.isEmpty(mchtCode)){
				c.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(name)){
				c.andNameLikeTo("%"+name+"%");
			}
			if(!StringUtils.isEmpty(content)){
				c.andContentLike("%"+content+"%");
			}
			if(!StringUtils.isEmpty(auditStatus)){
				if(!auditStatus.equals("3")){
					c.andAuditStatusEqualTo(auditStatus);
					c.andDelFlagEqualTo("0");
				}else{
					c.andDelFlagEqualTo("1");
				}
			}else{
				c.andDelFlagEqualTo("0");
			}
			if(!StringUtils.isEmpty(createDateBegin) && !StringUtils.isEmpty(createDateEnd)){
				c.andCreateDateBetween(sdf.parse(createDateBegin+" 00:00:00"), sdf.parse(createDateEnd+" 23:59:59"));
			}else{
				if(!StringUtils.isEmpty(createDateBegin)){
					c.andCreateDateGreaterThanOrEqualTo(sdf.parse(createDateBegin+" 00:00:00"));
				}
				if(!StringUtils.isEmpty(createDateEnd)){
					c.andCreateDateLessThanOrEqualTo(sdf.parse(createDateEnd+" 23:59:59"));
				}
			}
			if(!StringUtil.isEmpty(productTypeId)){
				c.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			
			totalCount = mchtShopDynamicService.countMchtShopDynamicCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<MchtShopDynamicCustom> mchtShopDynamicCustoms = mchtShopDynamicService.selectMchtShopDynamicCustomByExample(e);
			resMap.put("Rows", mchtShopDynamicCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 置底
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mchtShopDynamic/updateWeight.shtml")
	@ResponseBody
	public Map<String, Object> updateWeight(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			MchtShopDynamic mchtShopDynamic = new MchtShopDynamic();
			mchtShopDynamic.setWeight(1);
			mchtShopDynamic.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtShopDynamic.setUpdateDate(new Date());
			MchtShopDynamicExample e = new MchtShopDynamicExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			mchtShopDynamicService.updateByExampleSelective(mchtShopDynamic, e);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 审核页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopDynamic/toAudit.shtml")
	public ModelAndView toAudit(HttpServletRequest request) {
		String rtPage = "mchtShopDynamic/toAudit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			MchtShopDynamic mchtShopDynamic = mchtShopDynamicService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("mchtShopDynamic", mchtShopDynamic);
			if(!StringUtils.isEmpty(mchtShopDynamic.getProductIds())){
				String[] productIdsArray = mchtShopDynamic.getProductIds().split(",");
				List<Integer> productIdList = new ArrayList<Integer>();
				for(String productIdStr:productIdsArray){
					productIdList.add(Integer.parseInt(productIdStr));
				}
				if(productIdList.size()>0){
					ProductCustomExample e = new ProductCustomExample();
					e.createCriteria().andDelFlagEqualTo("0").andIdIn(productIdList);
					List<ProductCustom> productCustomList = productService.selectProductCustomByExample(e);
					resMap.put("productCustomList", productCustomList);
				}
				try {
					InputStream stream = MchtShopDynamicController.class.getResourceAsStream("/base_config.properties");
					Properties properties = new Properties();
					properties.load(stream);
					String mUrl = properties.getProperty("mUrl");
					stream.close();
					resMap.put("mUrl", mUrl + "/share.html?id="); // 手机预览路径
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/mchtShopDynamic/audit.shtml")
	@ResponseBody
	public Map<String, Object> audit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String auditStatus = request.getParameter("auditStatus");
			String rejectionReason = request.getParameter("rejectionReason");
			MchtShopDynamic mchtShopDynamic = new MchtShopDynamic();
			mchtShopDynamic.setAuditStatus(auditStatus);
			mchtShopDynamic.setAuditDate(new Date());
			mchtShopDynamic.setAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtShopDynamic.setRejectionReason(rejectionReason);
			mchtShopDynamic.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtShopDynamic.setUpdateDate(new Date());
			MchtShopDynamicExample e = new MchtShopDynamicExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			mchtShopDynamicService.updateByExampleSelective(mchtShopDynamic, e);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 详情页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopDynamic/toView.shtml")
	public ModelAndView toView(HttpServletRequest request) {
		String rtPage = "mchtShopDynamic/toView";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			MchtShopDynamic mchtShopDynamic = mchtShopDynamicService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("mchtShopDynamic", mchtShopDynamic);
			if(!StringUtils.isEmpty(mchtShopDynamic.getProductIds())){
				String[] productIdsArray = mchtShopDynamic.getProductIds().split(",");
				List<Integer> productIdList = new ArrayList<Integer>();
				for(String productIdStr:productIdsArray){
					productIdList.add(Integer.parseInt(productIdStr));
				}
				if(productIdList.size()>0){
					ProductCustomExample e = new ProductCustomExample();
					e.createCriteria().andDelFlagEqualTo("0").andIdIn(productIdList);
					List<ProductCustom> productCustomList = productService.selectProductCustomByExample(e);
					resMap.put("productCustomList", productCustomList);
				}
				try {
					InputStream stream = MchtShopDynamicController.class.getResourceAsStream("/base_config.properties");
					Properties properties = new Properties();
					properties.load(stream);
					String mUrl = properties.getProperty("mUrl");
					stream.close();
					resMap.put("mUrl", mUrl + "/share.html?id="); // 手机预览路径
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(mchtShopDynamic.getAuditBy()!=null){
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(mchtShopDynamic.getAuditBy());
				resMap.put("staffName", sysStaffInfo.getStaffName());
			}
		}
		return new ModelAndView(rtPage,resMap);
	}

	/**
	 *
	 * @Title saveWeight
	 * @Description 保存动态权重
	 * @author XDD
	 * @date 2020年3月16日 下午14:35:11
	 */
	@ResponseBody
	@RequestMapping("/mchtShopDynamic/saveWeight.shtml")
	public Map<String, Object> saveWeight(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(request.getParameter("id"))) {
				Date date = new Date();
				String staffId = this.getSessionStaffBean(request).getStaffID();
				MchtShopDynamicExample mchtShopDynamicExample = new MchtShopDynamicExample();
				mchtShopDynamicExample.createCriteria().andIdEqualTo(Integer.parseInt(request.getParameter("id")));
				MchtShopDynamic mchtShopDynamic = new MchtShopDynamic();
				mchtShopDynamic.setWeight(Integer.parseInt(request.getParameter("weight")));
				mchtShopDynamic.setUpdateDate(date);
				mchtShopDynamic.setUpdateBy(Integer.parseInt(staffId));
				mchtShopDynamicService.updateByExampleSelective(mchtShopDynamic, mchtShopDynamicExample);
			}else {
				code = "9999";
				msg = "动态ID为空！";
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
