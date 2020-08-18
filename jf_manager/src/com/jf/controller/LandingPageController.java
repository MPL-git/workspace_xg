package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAuditLog;
import com.jf.entity.ActivityAuditLogCustom;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityCustomExample;
import com.jf.entity.ActivityExample;
import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductExample;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;
import com.jf.entity.FullCutCustom;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullGive;
import com.jf.entity.LandingPage;
import com.jf.entity.LandingPageCustom;
import com.jf.entity.LandingPageExample;
import com.jf.entity.LandingPagePic;
import com.jf.entity.LandingPagePicExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SpreadActivityGroup;
import com.jf.entity.SpreadActivityGroupExample;
import com.jf.entity.SpreadName;
import com.jf.entity.SpreadNameExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysStatus;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderPic;
import com.jf.entity.WithdrawOrderPicExample;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityAuditLogService;
import com.jf.service.ActivityProductService;
import com.jf.service.ActivityService;
import com.jf.service.CouponService;
import com.jf.service.FullCutService;
import com.jf.service.FullDiscountService;
import com.jf.service.FullGiveService;
import com.jf.service.LandingPagePicService;
import com.jf.service.LandingPageService;
import com.jf.service.MchtInfoService;
import com.jf.service.MsgTplService;
import com.jf.service.PlatformContactService;
import com.jf.service.PlatformMsgService;
import com.jf.service.ProductTypeService;
import com.jf.service.SpreadActivityGroupService;
import com.jf.service.SpreadNameService;
import com.jf.service.SysParamCfgService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Description
 * @Author chengh
 * @DATE 2020/6/16 15:28
 */
@Controller
public class LandingPageController extends BaseController{

	@Autowired
	private LandingPageService landingPageService;
	@Autowired
	private SpreadActivityGroupService spreadActivityGroupService;
	@Autowired
	private LandingPagePicService landingPagePicService;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	@Autowired
	private SpreadNameService spreadNameService;
	/**
	 * 落地页初始页面数据
	 * @return
	 */
	@RequestMapping(value="/landingPage/getLandingPageList.shtml")
	public ModelAndView getLandingPageList(HttpServletRequest request, String pageType){
		ModelAndView m = new ModelAndView();
		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		m.addObject("mUrl",resource.getString("mUrl"));
		m.setViewName("/landingPage/getLandingPageList");
		return m;
	}

	/**
	 * @Description 落地页列表数据
	 * @Author chengh
	 * @DATE 2020/6/18 14:20
	 */
	@RequestMapping(value="/landingPage/getLandingPageListData.shtml")
	@ResponseBody
	public Map<String, Object> getLandingPageListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<LandingPageCustom> dataList = null;
		Integer totalCount =0;
		try {
			LandingPageExample landingPageExample = new LandingPageExample();
			LandingPageExample.Criteria criteria = landingPageExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(request.getParameter("id"))){
				criteria.andIdEqualTo(Integer.parseInt(request.getParameter("id")));
			}
			if(!StringUtils.isEmpty(request.getParameter("name"))){
				criteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (org.apache.commons.lang.StringUtils.isNotBlank(request.getParameter("createDateBegin"))) {
					criteria.andCreateDateGreaterThanOrEqualTo(df.parse(request
							.getParameter("createDateBegin") + " 00:00:00"));
				}
				if (org.apache.commons.lang.StringUtils.isNotBlank(request.getParameter("createDateEnd"))) {
					criteria.andCreateDateLessThanOrEqualTo(df.parse(request
							.getParameter("createDateEnd") + " 23:59:59"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			landingPageExample.setLimitSize(page.getLimitSize());
			landingPageExample.setLimitStart(page.getLimitStart());
			totalCount=landingPageService.countLandingPagesCustomByExample(landingPageExample);
			dataList=landingPageService.selectLandingPagesCustomByExample(landingPageExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount-1);
		return resMap;
	}

	/**
	 * @Description 添加、修改落地页
	 * @Author chengh
	 * @DATE 2020/6/18 14:20
	 */
	@RequestMapping("/landingPage/addOrModify.shtml")
	public ModelAndView addOrModify(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String rtPage = "/landingPage/addOrModify";
		LandingPage landingPage = new LandingPage();
		List<LandingPagePic> landingPagePics = new ArrayList<>();
		List<Map<String, Object>> landingPagePicsList=new ArrayList<Map<String, Object>>();
		if(!StringUtils.isEmpty(request.getParameter("id"))){
			landingPage = landingPageService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
			LandingPagePicExample landingPagePicExample = new LandingPagePicExample();
			landingPagePicExample.createCriteria().andLandingPageIdEqualTo(landingPage.getId()).andDelFlagEqualTo("0");
			landingPagePics = landingPagePicService.selectByExample(landingPagePicExample);
			for(LandingPagePic landingPagePic:landingPagePics){
				Map<String, Object> pic=new HashMap<String, Object>();
				pic.put("PICTURE_PATH", landingPagePic.getPic());
				landingPagePicsList.add(pic);
			}
		}else {
			LandingPageExample landingPageExample = new LandingPageExample();
			landingPageExample.createCriteria().andDelFlagEqualTo("1");
			landingPageExample.setOrderByClause(" id asc");
			List<LandingPage> landingPages = landingPageService.selectByExample(landingPageExample);
			landingPage = !landingPages.isEmpty()?landingPages.get(0):null;
			//当新增时，获取配置默认图片
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			sysParamCfgExample.createCriteria().andParamCodeEqualTo("LANDING_PAGE_PIC");
			List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
			for(SysParamCfg sysParamCfg:sysParamCfgs){
				Map<String, Object> pic=new HashMap<String, Object>();
				pic.put("PICTURE_PATH", sysParamCfg.getParamValue());
				landingPagePicsList.add(pic);
			}
		}
		if(StringUtils.isEmpty(request.getParameter("id"))){
			landingPage.setId(null);
		}
		map.put("picLength",landingPagePics.size());
		map.put("landingPage",landingPage);
		map.put("landingPagePicsList",landingPagePicsList);
		return new ModelAndView(rtPage, map);
	}

	/**
	 * @Description 落地页信息提交
	 * @Author chengh
	 * @DATE 2020/6/19 15:45
	 */
	@ResponseBody
	@RequestMapping("/landingPage/submit.shtml")
	public Map<String, Object> submit(HttpServletRequest request) {
		String staffId = this.getSessionStaffBean(request).getStaffID();
		Map<String,Object> map = landingPageService.submit(request,staffId);
		return map;
	}


	/**
	 * @Description 查看地址
	 * @Author chengh
	 * @DATE 2020/6/18 14:20
	 */
	@RequestMapping("/landingPage/viewAddress.shtml")
	public ModelAndView viewAddress(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String rtPage = "/landingPage/viewAddress";
		LandingPage landingPage = landingPageService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		map.put("landingPage",landingPage);
		//渠道为落地页活动组
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andChannelEqualTo("6").andDelFlagEqualTo("0");
		List<SpreadActivityGroup> spreadActivityGroups = spreadActivityGroupService.selectByExample(spreadActivityGroupExample);
		map.put("spreadActivityGroups",spreadActivityGroups);
		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		map.put("mUrl",resource.getString("mUrl"));
		return new ModelAndView(rtPage, map);
	}

	/**
	 * @Description 提交地址参数
	 * @Author chengh
	 * @DATE 2020/6/18 14:45
	 */
	@ResponseBody
	@RequestMapping(value = "/landingPage/viewAddressSubmit.shtml")
	public String viewAddressSubmit(HttpServletRequest request){
		String id = request.getParameter("id");
		String iosActivityName = request.getParameter("iosActivityName");
		Integer iosActivityGroupId = StringUtils.isEmpty(request.getParameter("iosActivityGroupId"))?null:Integer.parseInt(request.getParameter("iosActivityGroupId"));
		LandingPage landingPage = landingPageService.selectByPrimaryKey(Integer.parseInt(id));
		landingPage.setAndroidChannel(request.getParameter("androidChannel"));
		landingPage.setIosActivityGroupId(iosActivityGroupId);
		landingPage.setIosActivityName(iosActivityName);
		landingPageService.updateByPrimaryKey(landingPage);

		//保存IOS活动组及活动名称
		if(iosActivityGroupId!=null){
			SpreadNameExample spreadNameExample = new SpreadNameExample();
			spreadNameExample.createCriteria().andActivityGroupIdEqualTo(iosActivityGroupId).andSpreadNameEqualTo(iosActivityName).andIsEffectEqualTo("1").andDelFlagEqualTo("0");
			List<SpreadName> spreadNames = spreadNameService.selectByExample(spreadNameExample);
			if(spreadNames.isEmpty()){
				SpreadName spreadName = new SpreadName();
				spreadName.setSpreadName(iosActivityName);
				spreadName.setDeviceType("1");
				spreadName.setActivityGroupId(iosActivityGroupId);
				spreadName.setIsEffect("1");
				spreadName.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				spreadName.setCreateDate(new Date());
				spreadNameService.insertSelective(spreadName);
			}
		}
		return "";
	}
}
