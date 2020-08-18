package com.jf.controller;

import com.jf.entity.ConfigSpecialMcht;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SvipBindProduct;
import com.jf.entity.SvipBindProductCustom;
import com.jf.entity.SvipBindProductExample;
import com.jf.entity.SvipMarketingSetting;
import com.jf.entity.SvipMarketingSettingExample;
import com.jf.service.LandingPagePicService;
import com.jf.service.LandingPageService;
import com.jf.service.SpreadActivityGroupService;
import com.jf.service.SpreadNameService;
import com.jf.service.SvipBindProductService;
import com.jf.service.SvipMarketingSettingService;
import com.jf.service.SysParamCfgService;
import com.jf.vo.Page;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author chengh
 * @DATE 2020/6/16 15:28
 */
@Controller
public class SvipBindProductController extends BaseController{

	@Autowired
	private SvipBindProductService svipBindProductService;

	@Autowired
	private SvipMarketingSettingService svipMarketingSettingService;
	/**
	 * SVIP绑定营销页面初始化
	 * @return
	 */
	@RequestMapping(value="/svip/getSvipBindProductPageList.shtml")
	public ModelAndView getSvipBindProductPageList(HttpServletRequest request){
		return new ModelAndView("/member/svip/getSvipBindProductPageList");
	}

	/**
	 * @Description SVIP绑定营销列表
	 * @Author chengh
	 * @DATE 2020/6/18 14:20
	 */
	@RequestMapping(value="/svip/getSvipBindProductPageListData.shtml")
	@ResponseBody
	public Map<String, Object> getSvipBindProductPageListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<SvipBindProductCustom> dataList = null;
		Integer totalCount =0;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("mchtCode",request.getParameter("mchtCode"));
		paramMap.put("companyName",request.getParameter("companyName"));
		paramMap.put("searchFields",request.getParameter("searchFields"));
		int limitStart = (page.getPage()-1)*page.getLimitSize();
		int limitSize = page.getLimitSize();
		paramMap.put("limitStart", limitStart);
		paramMap.put("limitSize", limitSize);
		totalCount=svipBindProductService.countSvipBindProductCustomByExample(paramMap);
		dataList=svipBindProductService.selectSvipBindProductCustomByExample(paramMap);
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 批量删除商品
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/svip/batchDelProduct.shtml")
	@ResponseBody
	public Map<String, Object> batchDelProduct(HttpServletRequest request) {
		return svipBindProductService.batchDelProduct(request,Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
	}

	/**
	 * @Description 新增商品
	 * @Author chengh
	 * @DATE 2020/6/18 14:20
	 */
	@RequestMapping("/svipBindProduct/toAddProduct.shtml")
	public ModelAndView toAddProduct(HttpServletRequest request) {
		return new ModelAndView("/member/svip/toAddProductSubmit");
	}

	/**
	 * @Description 新增商品提交
	 * @Author chengh
	 * @DATE 2020/6/19 15:45
	 */
	@ResponseBody
	@RequestMapping("/svipBindProduct/toAddProductSubmit.shtml")
	public Map<String, Object> toAddProductSubmit(HttpServletRequest request) {
		return svipBindProductService.toAddProductSubmit(request,Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
	}

	/**
	 * @Description 基础设置
	 * @Author chengh
	 * @DATE 2020/6/18 14:20
	 */
	@RequestMapping("/svipBindProduct/svipMarketingSetUpSubmit.shtml")
	public ModelAndView svipMarketingSetUp(HttpServletRequest request) {
		SvipMarketingSettingExample svipMarketingSettingExample = new SvipMarketingSettingExample();
		List<SvipMarketingSetting> svipMarketingSettingList = svipMarketingSettingService.selectByExample(svipMarketingSettingExample);
		Map<String,Object> map = new HashMap<>();
		if(!svipMarketingSettingList.isEmpty()){
			SvipMarketingSetting svipMarketingSetting = svipMarketingSettingList.get(0);
			map.put("id",svipMarketingSetting.getId());
			map.put("year",svipMarketingSetting.getYear());
			map.put("price",svipMarketingSetting.getPrice().intValue());
			map.put("beginDate",svipMarketingSetting.getBeginDate());
			map.put("endDate",svipMarketingSetting.getEndDate());
		}
		return new ModelAndView("/member/svip/svipMarketingSetUpSubmit",map);
	}

	/**
	 * @Description 基础设置提交
	 * @Author chengh
	 * @DATE 2020/6/19 15:45
	 */
	@ResponseBody
	@RequestMapping("/svipBindProduct/svipMarketingSetUpSub.shtml")
	public ModelAndView svipMarketingSetUpSubmit(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = "设置成功！";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		String id = request.getParameter("id");
		try {
			SvipMarketingSetting svipMarketingSetting = new SvipMarketingSetting();
			svipMarketingSetting.setYear(Integer.parseInt(request.getParameter("year")));
			svipMarketingSetting.setPrice(new BigDecimal(request.getParameter("price")));
			svipMarketingSetting.setBeginDate(sdf.parse(request.getParameter("beginDate")));
			svipMarketingSetting.setEndDate(sdf.parse(request.getParameter("endDate")));
			if(StringUtils.isNotEmpty(id)){
				svipMarketingSetting.setId(Integer.parseInt(id));
				svipMarketingSetting.setUpdateBy(staffId);
				svipMarketingSetting.setUpdateDate(new Date());
				svipMarketingSettingService.updateByPrimaryKeySelective(svipMarketingSetting);
			}else{
				svipMarketingSetting.setCreateBy(staffId);
				svipMarketingSetting.setCreateDate(new Date());
				svipMarketingSettingService.insertSelective(svipMarketingSetting);
				List<SvipMarketingSetting> svipMarketingSettingList = svipMarketingSettingService.selectByExample(new SvipMarketingSettingExample());
				SvipBindProduct svipBindProduct = new SvipBindProduct();
				svipBindProduct.setSvipMarketingSettingId(svipMarketingSettingList.get(0).getId());
				svipBindProductService.updateByExampleSelective(svipBindProduct,new SvipBindProductExample());
			}
		}catch (Exception e){
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put("statusCode", code);
		map.put("message", msg);
		return new ModelAndView(rtPage,map);
	}
}
