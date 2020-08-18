package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.common.utils.Week;
import com.jf.common.utils.WeekHelper;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController extends BaseController{
	
	@Resource
	private MemberGroupService memberGroupService;

	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private MemberAccountService memberAccountService;
	
	@Resource
	private MemberAddressService memberAddressService;

	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private MemberFootprintService memberFootprintService;
	
	@Resource
	private IntegralTypeService integralTypeService;
	
	@Resource
	private IntegralDtlService integralDtlService;

	@Resource
	private IntegralGiveService integralGiveService;
	
	@Resource
	private GrowthDtlService growthDtlService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private SysSmsTemplateService sysSmsTemplateService;
	
	@Resource
	private MemberShopFootprintService memberShopFootprintService;
	
	@Resource
	private MemberActivityFootprintService memberActivityFootprintService;
	
	@Resource
	private  MemberStatusLogService memberStatusLogService;
	
	@Resource
	private ImpeachMemberService impeachMemberService;

	@Resource
	private SysLoginLogService sysLoginLogService;
	
	@Resource
	private MemberCollegeStudentCertificationService memberCollegeStudentCertificationService;
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 //会员等级配置
	@RequestMapping(value = "/member/group/list.shtml")
	public ModelAndView memberGroupList(HttpServletRequest request) {	
		String rtPage = "/member/group/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		 
		return new ModelAndView(rtPage,resMap);
	}
	
	//会员等级清单
	@RequestMapping(value = "/member/group/dataList.shtml")
	@ResponseBody
	public Map<String, Object> memberGroupDataList(HttpServletRequest request,Page page) {	
		Map<String,Object> resMap = new HashMap<String,Object>(); 
		Integer totalCount =0;

		try {
			 
			MemberGroupExample memberGroupExample=new MemberGroupExample();
			memberGroupExample.createCriteria().andDelFlagEqualTo("0");
			totalCount = memberGroupService.countByExample(memberGroupExample);
			
			memberGroupExample.setLimitStart(page.getLimitStart());
			memberGroupExample.setLimitSize(page.getLimitSize());
			
			List<MemberGroup> memberGroups=memberGroupService.selectByExample(memberGroupExample);
			
			resMap.put("Rows", memberGroups);
			resMap.put("Total", totalCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//会员列表
	@RequestMapping(value ="/member/info/list.shtml")
	public ModelAndView memberInfoList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/member/info/list";// 首页
		Map<String,Object> resMap = new HashMap<String,Object>();
		MemberGroupExample memberGroupExample=new MemberGroupExample();
		memberGroupExample.createCriteria().andDelFlagEqualTo("0");
		
		List<MemberGroup> memberGroups=memberGroupService.selectByExample(memberGroupExample);
		resMap.put("memberGroups", memberGroups);
		resMap.put("sprChnls", DataDicUtil.getStatusList("BU_MEMBER_INFO", "SPR_CHNL"));
		resMap.put("type", request.getParameter("type"));
//		resMap.put("partStatus", request.getParameter("partStatus"));
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 导出选项列表
	 */
	@RequestMapping(value ="/member/info/partExportExcel.shtml")
	public ModelAndView partExportExcel(HttpServletRequest request) {
		String rtPage = "/member/info/partExportExcel";
		Map<String,Object> resMap = new HashMap<String,Object>();
		List countArrayList = new ArrayList();
		String totalCount = request.getParameter("Total");
		if(!totalCount.equals("")) {
			Integer count = Integer.parseInt((totalCount).trim());
			int num = 0;
			int middle = 20000;
			if (count % middle == 0) {

				num = (count / middle);
			} else {
				num = count / middle + 1;
			}
			int[] list = new int[num];
			int x = 0;
			int y = 0;
			for (int i = 0; i < num; i++) {
				list[i] = middle * i;
				x = list[i] + 1;
				if (list[i] + middle > count) {
					y = count;
				} else {
					y = list[i] + middle;
				}
				Map<String, Object> countList = new HashMap<String, Object>();
				countList.put(StringUtil.valueOf(list[i]), "第" + x + "~" + y + "条");
				countArrayList.add(countList);
			}
		}
		resMap.put("countArrayList",countArrayList);
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 会员列表(运营)-新
	 * @param request
	 * @param paramMap
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/member/info/partDataList.shtml")
	@ResponseBody
	public Map<String, Object> partDataList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap,Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		int sqlTemplate = 1;
		try {
            String partPayOrderAmountMin = (String)paramMap.get("partPayOrderAmountMin");
            String partPayOrderAmountMax = (String)paramMap.get("partPayOrderAmountMax");
            String partPayOrderCountMin = (String)paramMap.get("partPayOrderCountMin");
            String partPayOrderCountMax = (String)paramMap.get("partPayOrderCountMax");
            String comPayDateBegin = (String)paramMap.get("comPayDateBegin");
            String comPayDateEnd = (String)paramMap.get("comPayDateEnd");
            if(!StringUtils.isEmpty(partPayOrderAmountMin) || !StringUtils.isEmpty(partPayOrderAmountMax) || !StringUtils.isEmpty(partPayOrderCountMin) || !StringUtils.isEmpty(partPayOrderCountMax)){
                if(StringUtils.isEmpty(comPayDateBegin) || StringUtils.isEmpty(comPayDateEnd)){
                    resMap.put("Rows", new ArrayList<MemberInfoCustom>());
                    resMap.put("Total", totalCount);
                    return resMap;
                }
            }
            if(!StringUtils.isEmpty(comPayDateBegin) && !StringUtils.isEmpty(comPayDateEnd)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                long difference =  (sdf.parse(comPayDateEnd).getTime() - sdf.parse(comPayDateBegin).getTime())/86400000;
                if(Math.abs(difference)>100){
                    resMap.put("Rows", new ArrayList<MemberInfoCustom>());
                    resMap.put("Total", totalCount);
                    return resMap;
                }
            }
            if(!StringUtils.isEmpty(comPayDateBegin) && StringUtils.isEmpty(comPayDateEnd)){
                resMap.put("Rows", new ArrayList<MemberInfoCustom>());
                resMap.put("Total", totalCount);
                return resMap;
            }
            if(StringUtils.isEmpty(comPayDateBegin) && !StringUtils.isEmpty(comPayDateEnd)){
                resMap.put("Rows", new ArrayList<MemberInfoCustom>());
                resMap.put("Total", totalCount);
                return resMap;
            }
			if(!StringUtils.isEmpty(paramMap.get("createDateBegin"))){
				paramMap.put("createDateBegin",paramMap.get("createDateBegin")+" 00:00:00");
			}
			if(!StringUtils.isEmpty(paramMap.get("createDateEnd"))){
				paramMap.put("createDateEnd",paramMap.get("createDateEnd")+" 23:59:59");
			}
			if(!StringUtils.isEmpty(paramMap.get("loginDateBegin"))){
				paramMap.put("loginDateBegin",paramMap.get("loginDateBegin")+" 00:00:00");
			}
			if(!StringUtils.isEmpty(paramMap.get("loginDateEnd"))){
				paramMap.put("loginDateEnd",paramMap.get("loginDateEnd")+" 23:59:59");
			}
			if(!StringUtils.isEmpty(paramMap.get("firstPayDateBegin"))){
				paramMap.put("firstPayDateBegin",paramMap.get("firstPayDateBegin")+" 00:00:00");
			}
			if(!StringUtils.isEmpty(paramMap.get("firstPayDateEnd"))){
				paramMap.put("firstPayDateEnd",paramMap.get("firstPayDateEnd")+" 23:59:59");
			}
			if(!StringUtils.isEmpty(paramMap.get("lastPayDateBegin"))){
				paramMap.put("lastPayDateBegin",paramMap.get("lastPayDateBegin")+" 00:00:00");
			}
			if(!StringUtils.isEmpty(paramMap.get("lastPayDateEnd"))){
				paramMap.put("lastPayDateEnd",paramMap.get("lastPayDateEnd")+" 23:59:59");
			}
			if(!StringUtils.isEmpty(paramMap.get("comPayDateBegin")) && !StringUtils.isEmpty(paramMap.get("comPayDateEnd"))){
				paramMap.put("comPayDateBegin",paramMap.get("comPayDateBegin")+" 00:00:00");
				paramMap.put("comPayDateEnd",paramMap.get("comPayDateEnd")+" 23:59:59");
				sqlTemplate = 2;
			}
			paramMap.put("MIN_NUM",page.getLimitStart());
			paramMap.put("MAX_NUM",page.getLimitSize());
			if (sqlTemplate == 1) {
				totalCount = memberInfoService.countPartDataList(paramMap);
				List<MemberInfoCustom> memberInfoCustoms=memberInfoService.selectPartDataList(paramMap);
				resMap.put("Rows", memberInfoCustoms);
			}
			if (sqlTemplate == 2) {
				totalCount = memberInfoService.countPartDataFromOrderList(paramMap);
				List<MemberInfoCustom> memberInfoCustoms=memberInfoService.selectPartDataFromOrderList(paramMap);
				resMap.put("Rows", memberInfoCustoms);
			}
				resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 导出会员列表(运营)-新
//	 * @param request
//	 * @param paramMap
//	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/member/info/getPartExportExcel.shtml")
	@ResponseBody
	public void getPartExportExcel(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		try {
			int sqlTemplate = 1;
            String partPayOrderAmountMin = (String)paramMap.get("partPayOrderAmountMin");
            String partPayOrderAmountMax = (String)paramMap.get("partPayOrderAmountMax");
            String partPayOrderCountMin = (String)paramMap.get("partPayOrderCountMin");
            String partPayOrderCountMax = (String)paramMap.get("partPayOrderCountMax");
            String comPayDateBegin = (String)paramMap.get("comPayDateBegin");
            String comPayDateEnd = (String)paramMap.get("comPayDateEnd");
            if(!StringUtils.isEmpty(partPayOrderAmountMin) || !StringUtils.isEmpty(partPayOrderAmountMax) || !StringUtils.isEmpty(partPayOrderCountMin) || !StringUtils.isEmpty(partPayOrderCountMax)){
                if(StringUtils.isEmpty(comPayDateBegin) || StringUtils.isEmpty(comPayDateEnd)){
                    return ;
                }
            }
            if(!StringUtils.isEmpty(comPayDateBegin) && !StringUtils.isEmpty(comPayDateEnd)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                long difference =  (sdf.parse(comPayDateEnd).getTime() - sdf.parse(comPayDateBegin).getTime())/86400000;
                if(Math.abs(difference)>100){
                    return ;
                }
            }
            if(!StringUtils.isEmpty(comPayDateBegin) && StringUtils.isEmpty(comPayDateEnd)){
                return ;
            }
            if(StringUtils.isEmpty(comPayDateBegin) && !StringUtils.isEmpty(comPayDateEnd)){
                return ;
            }
			if(!StringUtils.isEmpty(paramMap.get("createDateBegin"))){
				paramMap.put("createDateBegin",paramMap.get("createDateBegin")+" 00:00:00");
			}
			if(!StringUtils.isEmpty(paramMap.get("createDateEnd"))){
				paramMap.put("createDateEnd",paramMap.get("createDateEnd")+" 23:59:59");
			}
			if(!StringUtils.isEmpty(paramMap.get("loginDateBegin"))){
				paramMap.put("loginDateBegin",paramMap.get("loginDateBegin")+" 00:00:00");
			}
			if(!StringUtils.isEmpty(paramMap.get("loginDateEnd"))){
				paramMap.put("loginDateEnd",paramMap.get("loginDateEnd")+" 23:59:59");
			}
			if(!StringUtils.isEmpty(paramMap.get("firstPayDateBegin"))){
				paramMap.put("firstPayDateBegin",paramMap.get("firstPayDateBegin")+" 00:00:00");
			}
			if(!StringUtils.isEmpty(paramMap.get("firstPayDateEnd"))){
				paramMap.put("firstPayDateEnd",paramMap.get("firstPayDateEnd")+" 23:59:59");
			}
			if(!StringUtils.isEmpty(paramMap.get("lastPayDateBegin"))){
				paramMap.put("lastPayDateBegin",paramMap.get("lastPayDateBegin")+" 00:00:00");
			}
			if(!StringUtils.isEmpty(paramMap.get("lastPayDateEnd"))){
				paramMap.put("lastPayDateEnd",paramMap.get("lastPayDateEnd")+" 23:59:59");
			}
			if(!StringUtils.isEmpty(paramMap.get("comPayDateBegin")) && !StringUtils.isEmpty(paramMap.get("comPayDateEnd"))){
				paramMap.put("comPayDateBegin",paramMap.get("comPayDateBegin")+" 00:00:00");
				paramMap.put("comPayDateEnd",paramMap.get("comPayDateEnd")+" 23:59:59");
				sqlTemplate = 2;
			}
			Integer MIN_NUM = Integer.parseInt((String)paramMap.get("section"));
			paramMap.put("MIN_NUM",MIN_NUM);
			paramMap.put("MAX_NUM",20000);
			List<MemberInfoCustom> memberInfoCustoms=new ArrayList<>();
			if (sqlTemplate == 1) {
				memberInfoCustoms=memberInfoService.selectPartDataList(paramMap);
			}
			if (sqlTemplate == 2) {
				memberInfoCustoms=memberInfoService.selectPartDataFromOrderList(paramMap);

			}
			String[] titles = {"会员ID", "注册时间", "最后登录时间", "首次消费时间", "最后消费时间", "历史消费订单数", "历史消费金额","本期消费订单数","本期消费金额", "登陆次数", "活跃天数"};
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			ExcelBean excelBean = new ExcelBean("会员列表（运营）导出"+df.format(new Date())+".xls",
					"导出会员列表（运营）", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MemberInfoCustom memberInfoCustom:memberInfoCustoms){
				String[] data = {
						memberInfoCustom.getId().toString(),
                        memberInfoCustom.getCreateDate()==null?"":DateUtil.formatDateByFormat(memberInfoCustom.getCreateDate(), "yyyy-MM-dd HH:mm:ss"),
                        memberInfoCustom.getMsLastLoginTime()==null?"":DateUtil.formatDateByFormat(memberInfoCustom.getMsLastLoginTime(), "yyyy-MM-dd HH:mm:ss"),
                        memberInfoCustom.getMsFirstBuyTime()==null?"":DateUtil.formatDateByFormat(memberInfoCustom.getMsFirstBuyTime(), "yyyy-MM-dd HH:mm:ss"),
                        memberInfoCustom.getMsLastBuyTime()==null?"":DateUtil.formatDateByFormat(memberInfoCustom.getMsLastBuyTime(), "yyyy-MM-dd HH:mm:ss"),
                        memberInfoCustom.getTotalBuyCount()==null?"":memberInfoCustom.getTotalBuyCount().toString(),
                        memberInfoCustom.getTotalBuyAmount()==null?"":memberInfoCustom.getTotalBuyAmount().toString(),
						memberInfoCustom.getPartBuyCount()==null?"":memberInfoCustom.getPartBuyCount().toString(),
						memberInfoCustom.getPartBuyAmount()==null?"":memberInfoCustom.getPartBuyAmount().toString(),
						memberInfoCustom.getLoginCount()==null?"":memberInfoCustom.getLoginCount().toString(),
                        memberInfoCustom.getLoginDays()==null?"":memberInfoCustom.getLoginDays().toString()
                };
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/member/info/dataList.shtml")
	@ResponseBody
	public Map<String, Object> memberInfoDataList(HttpServletRequest request,Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		try {

			MemberInfoCustomExample memberInfoCustomExample4count =  new MemberInfoCustomExample();
			MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria4count=memberInfoCustomExample4count.createCriteria();

			MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
			MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria=memberInfoCustomExample.createCriteria();


			memberInfoCustomCriteria.andDelFlagEqualTo("0");
			memberInfoCustomCriteria4count.andDelFlagEqualTo("0");


			memberInfoCustomExample.setOrderByClause(" a.id desc");
			if(!StringUtil.isEmpty(request.getParameter("id")) ){
				Integer id=Integer.valueOf(request.getParameter("id").trim());
				memberInfoCustomCriteria.andIdEqualTo(id);
				memberInfoCustomCriteria4count.andIdEqualTo(id);
			}
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				memberInfoCustomCriteria.andNickEqualTo(nick);
				memberInfoCustomCriteria4count.andNickEqualTo(nick);
			}
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile=request.getParameter("mobile");
				memberInfoCustomCriteria.andMobileEqualTo(mobile);
				memberInfoCustomCriteria4count.andMobileEqualTo(mobile);

			}
			if(!StringUtil.isEmpty(request.getParameter("groupId")) ){
				Integer groupId=Integer.valueOf(request.getParameter("groupId"));
				memberInfoCustomCriteria.andGroupIdEqualTo(groupId);
				memberInfoCustomCriteria4count.andGroupIdEqualTo(groupId);
			}
			if(!StringUtil.isEmpty(request.getParameter("province_val")) ){
				Integer province=Integer.valueOf(request.getParameter("province_val"));
				memberInfoCustomCriteria.andProvinceEqualTo(province);
				memberInfoCustomCriteria4count.andProvinceEqualTo(province);
			}
			if(!StringUtil.isEmpty(request.getParameter("city_val")) ){
				Integer city=Integer.valueOf(request.getParameter("city_val"));
				memberInfoCustomCriteria.andCityEqualTo(city);
				memberInfoCustomCriteria4count.andCityEqualTo(city);
			}
			if(!StringUtil.isEmpty(request.getParameter("county_val")) ){
				Integer county=Integer.valueOf(request.getParameter("county_val"));
				memberInfoCustomCriteria.andCountyEqualTo(county);
				memberInfoCustomCriteria4count.andCountyEqualTo(county);
			}
			String sprChnl = request.getParameter("sprChnl");
			if(!StringUtil.isEmpty(sprChnl) ){
				if("20000".equals(sprChnl)) {
					if(!StringUtil.isEmpty(request.getParameter("channel")) ) {
						memberInfoCustomCriteria.andWeixinChannelEqualTo(request.getParameter("channel"));
						memberInfoCustomCriteria4count.andWeixinChannelEqualTo(request.getParameter("channel"));
					}else {
						memberInfoCustomCriteria.andRegClientEqualTo("5"); //微信小程序
						memberInfoCustomCriteria4count.andRegClientEqualTo("5"); //微信小程序
					}
				}if("30000".equals(sprChnl)) { //抖音
					memberInfoCustomCriteria.andRegClientEqualTo("6");
					memberInfoCustomCriteria4count.andRegClientEqualTo("6");
					memberInfoCustomCriteria.andDouyinSprChnl(request.getParameter("spreadname"), request.getParameter("channel"));
				}else {
					memberInfoCustomCriteria.andSprChnlEqualTo(sprChnl);
					memberInfoCustomCriteria4count.andSprChnlEqualTo(sprChnl);
				}
			}
			String timeType = request.getParameter("timeType");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if("1".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) ) {
					memberInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("date_begin")+" 00:00:00"));
					memberInfoCustomCriteria4count.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("date_begin")+" 00:00:00"));
				}
				if(!StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("date_end")+" 23:59:59"));
					memberInfoCustomCriteria4count.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("date_end")+" 23:59:59"));
				}
			}else if("2".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andLastLoginTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andLastLoginTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}else if("3".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andFirstBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andFirstBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}else if("4".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andLastBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andLastBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ) {
				memberInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
				memberInfoCustomCriteria4count.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ) {
				memberInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
				memberInfoCustomCriteria4count.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("loginDateBegin")) || !StringUtil.isEmpty(request.getParameter("loginDateEnd")) ) {
				memberInfoCustomCriteria.andLastLoginTimeBetween(request.getParameter("loginDateBegin"), request.getParameter("loginDateEnd"));
				memberInfoCustomCriteria4count.andLastLoginTimeBetween(request.getParameter("loginDateBegin"), request.getParameter("loginDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("firstPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("firstPayDateEnd")) ) {
				memberInfoCustomCriteria.andFirstBuyTimeBetween(request.getParameter("firstPayDateBegin"), request.getParameter("firstPayDateEnd"));
				memberInfoCustomCriteria4count.andFirstBuyTimeBetween(request.getParameter("firstPayDateBegin"), request.getParameter("firstPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("lastPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("lastPayDateEnd")) ) {
				memberInfoCustomCriteria.andLastBuyTimeBetween(request.getParameter("lastPayDateBegin"), request.getParameter("lastPayDateEnd"));
				memberInfoCustomCriteria4count.andLastBuyTimeBetween(request.getParameter("lastPayDateBegin"), request.getParameter("lastPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("comPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("comPayDateEnd")) ) {
				memberInfoCustomCriteria.andComPayDateBetween(request.getParameter("comPayDateBegin"), request.getParameter("comPayDateEnd"),"1");
				memberInfoCustomCriteria4count.andComPayDateBetween(request.getParameter("comPayDateBegin"), request.getParameter("comPayDateEnd"),"2");
			}
			if(!StringUtil.isEmpty(request.getParameter("regClient")) && "1".equals(request.getParameter("regClient"))
					&& (StringUtil.isEmpty(sprChnl) || !sprChnl.equals("20000")) ) {
				if(!StringUtil.isEmpty(request.getParameter("channel")) || !StringUtil.isEmpty(request.getParameter("spreadname")) || !StringUtil.isEmpty(request.getParameter("activityname")) ) {
					memberInfoCustomCriteria.andTrackDataEqualTo(request.getParameter("channel"), request.getParameter("spreadname"), request.getParameter("activityname"));
					memberInfoCustomCriteria4count.andTrackDataEqualTo(request.getParameter("channel"), request.getParameter("spreadname"), request.getParameter("activityname"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
				if("P".equals(request.getParameter("memberStatus"))) {
					memberInfoCustomCriteria.andStatusEqualTo(request.getParameter("memberStatus"));
					memberInfoCustomCriteria4count.andStatusEqualTo(request.getParameter("memberStatus"));
				}else {
					memberInfoCustomCriteria.andStatusNotEqualTo(request.getParameter("memberStatus"));
					memberInfoCustomCriteria4count.andStatusNotEqualTo(request.getParameter("memberStatus"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				memberInfoCustomCriteria.andStatusEqualTo(request.getParameter("status"));
				memberInfoCustomCriteria4count.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMin"))) {
				memberInfoCustomCriteria.andtotalBuyCountGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMin")));
				memberInfoCustomCriteria4count.andtotalBuyCountGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMax"))) {
				memberInfoCustomCriteria.andtotalBuyCountLessThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMax")));
				memberInfoCustomCriteria4count.andtotalBuyCountLessThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMax")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMin"))) {
				memberInfoCustomCriteria.andtotalBuyAmountGreaterThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMin")));
				memberInfoCustomCriteria4count.andtotalBuyAmountGreaterThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMax"))) {
				memberInfoCustomCriteria.andtotalBuyAmountLessThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMax")));
				memberInfoCustomCriteria4count.andtotalBuyAmountLessThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMax")));
			}
			String type = request.getParameter("type");
			if(!StringUtil.isEmpty(type) && type.equals("3")) {
				memberInfoCustomCriteria.andStatusIn(Arrays.asList("A","P"));
				memberInfoCustomCriteria4count.andStatusIn(Arrays.asList("A","P"));
			}
			totalCount = memberInfoService.countMemberInfoCustomByExample(memberInfoCustomExample4count);
			memberInfoCustomExample.setLimitStart(page.getLimitStart());
			memberInfoCustomExample.setLimitSize(page.getLimitSize());
			List<MemberInfoCustom> memberInfoCustoms=memberInfoService.selectMemberInfoCustomByExample(memberInfoCustomExample);
			resMap.put("Rows", memberInfoCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 查询导出导出excel条数
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/member/info/getExportExcelTotal.shtml")
	@ResponseBody
	public Map<String, Object> getExportExcelTotal(HttpServletRequest request) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		try {
			
			MemberInfoCustomExample memberInfoCustomExample4count =  new MemberInfoCustomExample();
			MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria4count=memberInfoCustomExample4count.createCriteria();
			
			MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
			MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria=memberInfoCustomExample.createCriteria();
			
			
			memberInfoCustomCriteria.andDelFlagEqualTo("0");
			memberInfoCustomCriteria4count.andDelFlagEqualTo("0");
			
			
			memberInfoCustomExample.setOrderByClause(" a.id desc");
			if(!StringUtil.isEmpty(request.getParameter("id")) ){
				Integer id=Integer.valueOf(request.getParameter("id"));
				memberInfoCustomCriteria.andIdEqualTo(id);
				memberInfoCustomCriteria4count.andIdEqualTo(id);
			}
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				memberInfoCustomCriteria.andNickEqualTo(nick);
				memberInfoCustomCriteria4count.andNickEqualTo(nick);
			}
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile=request.getParameter("mobile");
				memberInfoCustomCriteria.andMobileEqualTo(mobile);
				memberInfoCustomCriteria4count.andMobileEqualTo(mobile);
				
			}
			if(!StringUtil.isEmpty(request.getParameter("groupId")) ){
				Integer groupId=Integer.valueOf(request.getParameter("groupId"));
				memberInfoCustomCriteria.andGroupIdEqualTo(groupId);
				memberInfoCustomCriteria4count.andGroupIdEqualTo(groupId);
			}
			if(!StringUtil.isEmpty(request.getParameter("province_val")) ){
				Integer province=Integer.valueOf(request.getParameter("province_val"));
				memberInfoCustomCriteria.andProvinceEqualTo(province);
				memberInfoCustomCriteria4count.andProvinceEqualTo(province);
			}
			if(!StringUtil.isEmpty(request.getParameter("city_val")) ){
				Integer city=Integer.valueOf(request.getParameter("city_val"));
				memberInfoCustomCriteria.andCityEqualTo(city);
				memberInfoCustomCriteria4count.andCityEqualTo(city);
			}
			if(!StringUtil.isEmpty(request.getParameter("county_val")) ){
				Integer county=Integer.valueOf(request.getParameter("county_val"));
				memberInfoCustomCriteria.andCountyEqualTo(county);
				memberInfoCustomCriteria4count.andCountyEqualTo(county);
			}
			String sprChnl = request.getParameter("sprChnl");
			if(!StringUtil.isEmpty(sprChnl) ){
				if("20000".equals(sprChnl)) {
					if(!StringUtil.isEmpty(request.getParameter("channel")) ) {
						memberInfoCustomCriteria.andWeixinChannelEqualTo(request.getParameter("channel"));
						memberInfoCustomCriteria4count.andWeixinChannelEqualTo(request.getParameter("channel"));
					}else {
						memberInfoCustomCriteria.andRegClientEqualTo("5"); //微信小程序
						memberInfoCustomCriteria4count.andRegClientEqualTo("5"); //微信小程序
					}
				}if("30000".equals(sprChnl)) { //抖音
					memberInfoCustomCriteria.andRegClientEqualTo("6");
					memberInfoCustomCriteria4count.andRegClientEqualTo("6");
					memberInfoCustomCriteria.andDouyinSprChnl(request.getParameter("spreadname"), request.getParameter("channel"));
				}else {
					memberInfoCustomCriteria.andSprChnlEqualTo(sprChnl);
					memberInfoCustomCriteria4count.andSprChnlEqualTo(sprChnl);
				}
			}
			String timeType = request.getParameter("timeType");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if("1".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) ) {
					memberInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("date_begin")+" 00:00:00"));
					memberInfoCustomCriteria4count.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("date_begin")+" 00:00:00"));
				}
				if(!StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("date_end")+" 23:59:59"));
					memberInfoCustomCriteria4count.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("date_end")+" 23:59:59"));
				}
			}else if("2".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andLastLoginTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andLastLoginTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}else if("3".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andFirstBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andFirstBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}else if("4".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andLastBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andLastBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ) {
				memberInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
				memberInfoCustomCriteria4count.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ) {
				memberInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
				memberInfoCustomCriteria4count.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("loginDateBegin")) || !StringUtil.isEmpty(request.getParameter("loginDateEnd")) ) {
				memberInfoCustomCriteria.andLastLoginTimeBetween(request.getParameter("loginDateBegin"), request.getParameter("loginDateEnd"));
				memberInfoCustomCriteria4count.andLastLoginTimeBetween(request.getParameter("loginDateBegin"), request.getParameter("loginDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("firstPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("firstPayDateEnd")) ) {
				memberInfoCustomCriteria.andFirstBuyTimeBetween(request.getParameter("firstPayDateBegin"), request.getParameter("firstPayDateEnd"));
				memberInfoCustomCriteria4count.andFirstBuyTimeBetween(request.getParameter("firstPayDateBegin"), request.getParameter("firstPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("lastPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("lastPayDateEnd")) ) {
				memberInfoCustomCriteria.andLastBuyTimeBetween(request.getParameter("lastPayDateBegin"), request.getParameter("lastPayDateEnd"));
				memberInfoCustomCriteria4count.andLastBuyTimeBetween(request.getParameter("lastPayDateBegin"), request.getParameter("lastPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("comPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("comPayDateEnd")) ) {
				memberInfoCustomCriteria.andComPayDateBetween(request.getParameter("comPayDateBegin"), request.getParameter("comPayDateEnd"),"1");
				memberInfoCustomCriteria4count.andComPayDateBetween(request.getParameter("comPayDateBegin"), request.getParameter("comPayDateEnd"),"2");
			}
			if(!StringUtil.isEmpty(request.getParameter("regClient")) && "1".equals(request.getParameter("regClient")) 
					&& (StringUtil.isEmpty(sprChnl) || !sprChnl.equals("20000")) ) {
				if(!StringUtil.isEmpty(request.getParameter("channel")) || !StringUtil.isEmpty(request.getParameter("spreadname")) || !StringUtil.isEmpty(request.getParameter("activityname")) ) {
					memberInfoCustomCriteria.andTrackDataEqualTo(request.getParameter("channel"), request.getParameter("spreadname"), request.getParameter("activityname"));
					memberInfoCustomCriteria4count.andTrackDataEqualTo(request.getParameter("channel"), request.getParameter("spreadname"), request.getParameter("activityname"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
				if("P".equals(request.getParameter("memberStatus"))) {
					memberInfoCustomCriteria.andStatusEqualTo(request.getParameter("memberStatus"));
					memberInfoCustomCriteria4count.andStatusEqualTo(request.getParameter("memberStatus"));
				}else {
					memberInfoCustomCriteria.andStatusNotEqualTo(request.getParameter("memberStatus"));
					memberInfoCustomCriteria4count.andStatusNotEqualTo(request.getParameter("memberStatus"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				memberInfoCustomCriteria.andStatusEqualTo(request.getParameter("status"));
				memberInfoCustomCriteria4count.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMin"))) {
				memberInfoCustomCriteria.andtotalBuyCountGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMin")));
				memberInfoCustomCriteria4count.andtotalBuyCountGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMax"))) {
				memberInfoCustomCriteria.andtotalBuyCountLessThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMax")));
				memberInfoCustomCriteria4count.andtotalBuyCountLessThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMax")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMin"))) {
				memberInfoCustomCriteria.andtotalBuyAmountGreaterThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMin")));
				memberInfoCustomCriteria4count.andtotalBuyAmountGreaterThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMax"))) {
				memberInfoCustomCriteria.andtotalBuyAmountLessThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMax")));
				memberInfoCustomCriteria4count.andtotalBuyAmountLessThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMax")));
			}
			String type = request.getParameter("type");
			if(!StringUtil.isEmpty(type) && type.equals("3")) {
				memberInfoCustomCriteria.andStatusIn(Arrays.asList("A","P"));
				memberInfoCustomCriteria4count.andStatusIn(Arrays.asList("A","P"));
			}
			totalCount = memberInfoService.countMemberInfoCustomByExample(memberInfoCustomExample4count);
			if(totalCount>10000){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "当前导出数据"+totalCount+"条,期望导出数据少于10000条,请修改查询条件");
				return resMap;
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/member/info/exportExcel.shtml")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			MemberInfoCustomExample memberInfoCustomExample4count =  new MemberInfoCustomExample();
			MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria4count=memberInfoCustomExample4count.createCriteria();
			
			MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
			MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria=memberInfoCustomExample.createCriteria();
			
			
			memberInfoCustomCriteria.andDelFlagEqualTo("0");
			memberInfoCustomCriteria4count.andDelFlagEqualTo("0");
			
			
			memberInfoCustomExample.setOrderByClause(" a.id desc");
			if(!StringUtil.isEmpty(request.getParameter("id")) ){
				Integer id=Integer.valueOf(request.getParameter("id"));
				memberInfoCustomCriteria.andIdEqualTo(id);
				memberInfoCustomCriteria4count.andIdEqualTo(id);
			}
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				memberInfoCustomCriteria.andNickEqualTo(nick);
				memberInfoCustomCriteria4count.andNickEqualTo(nick);
			}
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile=request.getParameter("mobile");
				memberInfoCustomCriteria.andMobileEqualTo(mobile);
				memberInfoCustomCriteria4count.andMobileEqualTo(mobile);
				
			}
			if(!StringUtil.isEmpty(request.getParameter("groupId")) ){
				Integer groupId=Integer.valueOf(request.getParameter("groupId"));
				memberInfoCustomCriteria.andGroupIdEqualTo(groupId);
				memberInfoCustomCriteria4count.andGroupIdEqualTo(groupId);
			}
			if(!StringUtil.isEmpty(request.getParameter("province_val")) ){
				Integer province=Integer.valueOf(request.getParameter("province_val"));
				memberInfoCustomCriteria.andProvinceEqualTo(province);
				memberInfoCustomCriteria4count.andProvinceEqualTo(province);
			}
			if(!StringUtil.isEmpty(request.getParameter("city_val")) ){
				Integer city=Integer.valueOf(request.getParameter("city_val"));
				memberInfoCustomCriteria.andCityEqualTo(city);
				memberInfoCustomCriteria4count.andCityEqualTo(city);
			}
			if(!StringUtil.isEmpty(request.getParameter("county_val")) ){
				Integer county=Integer.valueOf(request.getParameter("county_val"));
				memberInfoCustomCriteria.andCountyEqualTo(county);
				memberInfoCustomCriteria4count.andCountyEqualTo(county);
			}
			String sprChnl = request.getParameter("sprChnl");
			if(!StringUtil.isEmpty(sprChnl) ){
				if("20000".equals(sprChnl)) {
					if(!StringUtil.isEmpty(request.getParameter("channel")) ) {
						memberInfoCustomCriteria.andWeixinChannelEqualTo(request.getParameter("channel"));
						memberInfoCustomCriteria4count.andWeixinChannelEqualTo(request.getParameter("channel"));
					}else {
						memberInfoCustomCriteria.andRegClientEqualTo("5"); //微信小程序
						memberInfoCustomCriteria4count.andRegClientEqualTo("5"); //微信小程序
					}
				}if("30000".equals(sprChnl)) { //抖音
					memberInfoCustomCriteria.andRegClientEqualTo("6");
					memberInfoCustomCriteria4count.andRegClientEqualTo("6");
					memberInfoCustomCriteria.andDouyinSprChnl(request.getParameter("spreadname"), request.getParameter("channel"));
				}else {
					memberInfoCustomCriteria.andSprChnlEqualTo(sprChnl);
					memberInfoCustomCriteria4count.andSprChnlEqualTo(sprChnl);
				}
			}
			String timeType = request.getParameter("timeType");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if("1".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) ) {
					memberInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("date_begin")+" 00:00:00"));
					memberInfoCustomCriteria4count.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("date_begin")+" 00:00:00"));
				}
				if(!StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("date_end")+" 23:59:59"));
					memberInfoCustomCriteria4count.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("date_end")+" 23:59:59"));
				}
			}else if("2".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andLastLoginTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andLastLoginTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}else if("3".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andFirstBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andFirstBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}else if("4".equals(timeType) ) {
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) || !StringUtil.isEmpty(request.getParameter("date_end")) ) {
					memberInfoCustomCriteria.andLastBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
					memberInfoCustomCriteria4count.andLastBuyTimeBetween(request.getParameter("date_begin"), request.getParameter("date_end"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ) {
				memberInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
				memberInfoCustomCriteria4count.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ) {
				memberInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
				memberInfoCustomCriteria4count.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("loginDateBegin")) || !StringUtil.isEmpty(request.getParameter("loginDateEnd")) ) {
				memberInfoCustomCriteria.andLastLoginTimeBetween(request.getParameter("loginDateBegin"), request.getParameter("loginDateEnd"));
				memberInfoCustomCriteria4count.andLastLoginTimeBetween(request.getParameter("loginDateBegin"), request.getParameter("loginDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("firstPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("firstPayDateEnd")) ) {
				memberInfoCustomCriteria.andFirstBuyTimeBetween(request.getParameter("firstPayDateBegin"), request.getParameter("firstPayDateEnd"));
				memberInfoCustomCriteria4count.andFirstBuyTimeBetween(request.getParameter("firstPayDateBegin"), request.getParameter("firstPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("lastPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("lastPayDateEnd")) ) {
				memberInfoCustomCriteria.andLastBuyTimeBetween(request.getParameter("lastPayDateBegin"), request.getParameter("lastPayDateEnd"));
				memberInfoCustomCriteria4count.andLastBuyTimeBetween(request.getParameter("lastPayDateBegin"), request.getParameter("lastPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("comPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("comPayDateEnd")) ) {
				memberInfoCustomCriteria.andComPayDateBetween(request.getParameter("comPayDateBegin"), request.getParameter("comPayDateEnd"),"1");
				memberInfoCustomCriteria4count.andComPayDateBetween(request.getParameter("comPayDateBegin"), request.getParameter("comPayDateEnd"),"2");
			}
			if(!StringUtil.isEmpty(request.getParameter("regClient")) && "1".equals(request.getParameter("regClient")) 
					&& (StringUtil.isEmpty(sprChnl) || !sprChnl.equals("20000")) ) {
				if(!StringUtil.isEmpty(request.getParameter("channel")) || !StringUtil.isEmpty(request.getParameter("spreadname")) || !StringUtil.isEmpty(request.getParameter("activityname")) ) {
					memberInfoCustomCriteria.andTrackDataEqualTo(request.getParameter("channel"), request.getParameter("spreadname"), request.getParameter("activityname"));
					memberInfoCustomCriteria4count.andTrackDataEqualTo(request.getParameter("channel"), request.getParameter("spreadname"), request.getParameter("activityname"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
				if("P".equals(request.getParameter("memberStatus"))) {
					memberInfoCustomCriteria.andStatusEqualTo(request.getParameter("memberStatus"));
					memberInfoCustomCriteria4count.andStatusEqualTo(request.getParameter("memberStatus"));
				}else {
					memberInfoCustomCriteria.andStatusNotEqualTo(request.getParameter("memberStatus"));
					memberInfoCustomCriteria4count.andStatusNotEqualTo(request.getParameter("memberStatus"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				memberInfoCustomCriteria.andStatusEqualTo(request.getParameter("status"));
				memberInfoCustomCriteria4count.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMin"))) {
				memberInfoCustomCriteria.andtotalBuyCountGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMin")));
				memberInfoCustomCriteria4count.andtotalBuyCountGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMax"))) {
				memberInfoCustomCriteria.andtotalBuyCountLessThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMax")));
				memberInfoCustomCriteria4count.andtotalBuyCountLessThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMax")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMin"))) {
				memberInfoCustomCriteria.andtotalBuyAmountGreaterThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMin")));
				memberInfoCustomCriteria4count.andtotalBuyAmountGreaterThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMax"))) {
				memberInfoCustomCriteria.andtotalBuyAmountLessThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMax")));
				memberInfoCustomCriteria4count.andtotalBuyAmountLessThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMax")));
			}
			String type = request.getParameter("type");
			if(!StringUtil.isEmpty(type) && type.equals("3")) {
				memberInfoCustomCriteria.andStatusIn(Arrays.asList("A","P"));
				memberInfoCustomCriteria4count.andStatusIn(Arrays.asList("A","P"));
			}
			List<MemberInfoCustom> memberInfoCustoms=memberInfoService.selectMemberInfoCustomByExample(memberInfoCustomExample);
			String[] titles = {"会员ID", "注册时间", "最后登录时间", "首次消费时间", "最后消费时间", "消费订单数", "消费金额", "登陆次数", "活跃天数"};
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			ExcelBean excelBean = new ExcelBean("会员列表（运营）导出"+df.format(new Date())+".xls",
					"导出会员列表（运营）", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MemberInfoCustom memberInfoCustom:memberInfoCustoms){
				String[] data = {
					memberInfoCustom.getId().toString(),
					memberInfoCustom.getCreateDate()==null?"":DateUtil.formatDateByFormat(memberInfoCustom.getCreateDate(), "yyyy-MM-dd HH:mm:ss"),
					memberInfoCustom.getMsLastLoginTime()==null?"":DateUtil.formatDateByFormat(memberInfoCustom.getMsLastLoginTime(), "yyyy-MM-dd HH:mm:ss"),
					memberInfoCustom.getMsFirstBuyTime()==null?"":DateUtil.formatDateByFormat(memberInfoCustom.getMsFirstBuyTime(), "yyyy-MM-dd HH:mm:ss"),
					memberInfoCustom.getMsLastBuyTime()==null?"":DateUtil.formatDateByFormat(memberInfoCustom.getMsLastBuyTime(), "yyyy-MM-dd HH:mm:ss"),
					memberInfoCustom.getTotalBuyCount()==null?"":memberInfoCustom.getTotalBuyCount().toString(),
					memberInfoCustom.getTotalBuyAmount()==null?"":memberInfoCustom.getTotalBuyAmount().toString(),
					memberInfoCustom.getLoginCount()==null?"":memberInfoCustom.getLoginCount().toString(),			
					memberInfoCustom.getLoginDays()==null?"":memberInfoCustom.getLoginDays().toString()			
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 会员详情页
//	 * @param request
//	 * @param response
//	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/member/info/detail.shtml")
	public String memberInfoDetail(Model model,HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		MemberInfoCustom memberInfoCustom=memberInfoService.selectMemberInfoCustomByPrimaryKey(id);
		model.addAttribute("memberInfoCustom", memberInfoCustom);
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		model.addAttribute("canOperate", memberInfoService.getCanOperate(staffId));
		return "/member/info/detail";
	}
	
	
	//会员收货地址管理
	@RequestMapping(value ="/member/address/list.shtml")
	public ModelAndView memberAddressList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/member/address/list";// 首页
		return new ModelAndView(rtPage, null);
	}
	
	@RequestMapping(value = "/member/address/dataList.shtml")
	@ResponseBody
	public Map<String, Object> memberAddressDataList(HttpServletRequest request,Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;

		try {
			 
			MemberAddressCustomExample memberAddressCustomExample=new MemberAddressCustomExample();
			MemberAddressCustomExample.MemberAddressCustomCriteria memberAddressCustomCriteria=memberAddressCustomExample.createCriteria();
			memberAddressCustomCriteria.andDelFlagEqualTo("0");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ){
				int memberId=Integer.valueOf(request.getParameter("memberId"));
				memberAddressCustomCriteria.andMemberIdEqualTo(memberId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("recipient")) ){
				String recipient=request.getParameter("recipient");
				memberAddressCustomCriteria.andRecipientEqualTo(recipient);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("phone")) ){
				String phone=request.getParameter("phone");
				memberAddressCustomCriteria.andPhoneEqualTo(phone);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("province_val")) ){
				Integer province=Integer.valueOf(request.getParameter("province_val"));
				memberAddressCustomCriteria.andProvinceEqualTo(province);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("city_val")) ){
				Integer city=Integer.valueOf(request.getParameter("city_val"));
				memberAddressCustomCriteria.andCityEqualTo(city);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("county_val")) ){
				Integer county=Integer.valueOf(request.getParameter("county_val"));
				memberAddressCustomCriteria.andCountyEqualTo(county);
			}
			
			totalCount = memberAddressService.countMemberAddressCustomByExample(memberAddressCustomExample);
			
			memberAddressCustomExample.setLimitStart(page.getLimitStart());
			memberAddressCustomExample.setLimitSize(page.getLimitSize());
			List<MemberAddressCustom> memberAddressCustoms=memberAddressService.selectMemberAddressCustomByExample(memberAddressCustomExample);
			
			resMap.put("Rows", memberAddressCustoms);
			resMap.put("Total", totalCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//会员优惠劵
	@RequestMapping(value ="/member/coupon/list.shtml")
	public ModelAndView memberCouponList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/member/coupon/list";// 首页
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("memberCouponStatus", DataDicUtil.getStatusList("BU_MEMBER_COUPON", "STATUS"));
		return new ModelAndView(rtPage, resMap);
	}
	
	@RequestMapping(value = "/member/coupon/dataList.shtml")
	@ResponseBody
	public Map<String, Object> memberCouponDataList(HttpServletRequest request,Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;

		try {
			 
			MemberCouponCustomExample memberCouponCustomExample=new MemberCouponCustomExample();
			MemberCouponCustomExample.MemberCouponCustomCriteria memberCouponCustomCriteria=memberCouponCustomExample.createCriteria();
			memberCouponCustomCriteria.andDelFlagEqualTo("0");
			memberCouponCustomExample.setOrderByClause(" a.id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ){
				Integer memberId=Integer.valueOf(request.getParameter("memberId").trim());
				memberCouponCustomCriteria.andMemberIdEqualTo(memberId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				memberCouponCustomCriteria.andNickLike(nick);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				memberCouponCustomCriteria.andStatusEqualTo(status);
			}
			if (!StringUtil.isEmpty(request.getParameter("overdue"))) {
				    Date date1=new Date();
				if (request.getParameter("overdue").equals("1")) {
					memberCouponCustomCriteria.andSmallexpiryEndDateEqualTo(date1);
				}
				 if (request.getParameter("overdue").equals("2")) {
						memberCouponCustomCriteria.andlargeexpiryEndDateEqualTo(date1);
					}
			}
			if (!StringUtil.isEmpty(request.getParameter("activityareaId"))) {
				memberCouponCustomCriteria.andactivityareaIdEqualTo(Integer.valueOf(request.getParameter("activityareaId")));
			}
			totalCount = memberCouponService.countMemberCouponCustomByExample(memberCouponCustomExample);
			
			memberCouponCustomExample.setLimitStart(page.getLimitStart());
			memberCouponCustomExample.setLimitSize(page.getLimitSize());
			List<MemberCouponCustom> memberCouponCustoms=memberCouponService.selectMemberCouponCustomByExample(memberCouponCustomExample);
			
			resMap.put("Rows", memberCouponCustoms);
			resMap.put("Total", totalCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//会员足迹
	@RequestMapping(value = "/member/footprint/list.shtml")
	public ModelAndView footprintList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/member/footprint/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Calendar calendars = Calendar.getInstance();
		calendars.set(Calendar.HOUR_OF_DAY,23);
		calendars.set(Calendar.MINUTE, 59);
		calendars.set(Calendar.SECOND, 59);
		
		String dateBegin = df.format(calendar.getTime());
		String dateEnd = df.format(calendars.getTime());
		
		resMap.put("updateDate_end",   dateEnd);
		resMap.put("updateDate_begin", dateBegin);
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/member/footprint/datalist.shtml")
	@ResponseBody
	public Map<String, Object> footprintDataList(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {	
			MemberFootprintCustomExample memberFootprintCustomExample=new MemberFootprintCustomExample();
			MemberFootprintCustomExample.MemberFootprintCustomCriteria memberFootprintCustomCriteria=memberFootprintCustomExample.createCriteria();
			memberFootprintCustomExample.setOrderByClause("a.id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ){
				int memberId=Integer.valueOf(request.getParameter("memberId"));
				memberFootprintCustomCriteria.andMemberIdEqualTo(memberId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				memberFootprintCustomCriteria.andNickLike(nick);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile=request.getParameter("mobile");
				memberFootprintCustomCriteria.andMobileEqualTo(mobile);
			}

			if(!StringUtil.isEmpty(request.getParameter("productCode")) ){
				String productCode=request.getParameter("productCode");
				memberFootprintCustomCriteria.andProductCodeEqualTo(productCode);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("updateDate_begin")) ){
				memberFootprintCustomCriteria.andUpdateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("updateDate_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("updateDate_end")) ){
				memberFootprintCustomCriteria.andUpdateDateLessThanOrEqualTo(sdf.parse(request.getParameter("updateDate_end")+" 23:59:59"));
			}
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					memberFootprintCustomCriteria.andProductTypeEqualTo(isCwOrgProductTypeId);
				}
			}
			
			totalCount = memberFootprintService.countByExample(memberFootprintCustomExample);
			
			memberFootprintCustomExample.setLimitStart(page.getLimitStart());
			memberFootprintCustomExample.setLimitSize(page.getLimitSize());
			List<MemberFootprintCustom> memberFootprintCustoms=memberFootprintService.selectMemberFootprintCustomByExample(memberFootprintCustomExample);

			
			resMap.put("Rows", memberFootprintCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//积分项目配置
	@RequestMapping(value = "/member/integralType/list.shtml")
	public ModelAndView integralTypeList(HttpServletRequest request) {
		String rtPage = "/member/integralType/list";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		 
		return new ModelAndView(rtPage,resMap);
	} 
	//积分配置清单
	@RequestMapping(value = "/member/integralType/datalist.shtml")
	@ResponseBody
	public Map<String, Object> integralTypeDataList(HttpServletRequest request,Page page) {	
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
 
		try {
			IntegralTypeExample integralTypeExample=new IntegralTypeExample();
			integralTypeExample.createCriteria().andDelFlagEqualTo("0");
			totalCount = integralTypeService.countIntegralTypeCustomByExample(integralTypeExample);
			
			integralTypeExample.setLimitStart(page.getLimitStart());
			integralTypeExample.setLimitSize(page.getLimitSize());
			List<IntegralTypeCustom> integralTypes=integralTypeService.selectIntegralTypeCustomByExample(integralTypeExample);
        	
			resMap.put("Rows", integralTypes);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resMap; 
	} 
	 
	// 添加积分配置
	@RequestMapping(value = "/member/integralType/add.shtml")
	public ModelAndView integralTypeAdd(HttpServletRequest request) {
		String rtPage = "/member/integralType/edit";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();

		resMap.put("tallyModes", DataDicUtil.getStatusList("BU_INTEGRAL_TYPE", "TALLY_MODE"));
		resMap.put("status", DataDicUtil.getStatusList("BU_INTEGRAL_TYPE", "STATUS"));
		resMap.put("intTypes", DataDicUtil.getStatusList("BU_INTEGRAL_TYPE", "INT_TYPE"));
		resMap.put("growTypes", DataDicUtil.getStatusList("BU_INTEGRAL_TYPE", "GROW_TYPE"));

    	return new ModelAndView(rtPage,resMap);
	}
	
	// 修改积分配置
	@RequestMapping(value = "/member/integralType/edit.shtml")
	public ModelAndView integralTypeEdit(HttpServletRequest request) {
		String rtPage = "/member/integralType/edit";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		try {
			IntegralType integralType=integralTypeService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			resMap.put("integralType", integralType);
			resMap.put("tallyModes", DataDicUtil.getStatusList("BU_INTEGRAL_TYPE", "TALLY_MODE"));
			resMap.put("status", DataDicUtil.getStatusList("BU_INTEGRAL_TYPE", "STATUS"));
			resMap.put("intTypes", DataDicUtil.getStatusList("BU_INTEGRAL_TYPE", "INT_TYPE"));
			resMap.put("growTypes", DataDicUtil.getStatusList("BU_INTEGRAL_TYPE", "GROW_TYPE"));

		} catch (Exception e) {
			e.printStackTrace();
		}

    	return new ModelAndView(rtPage,resMap);
	}
	
	// 保存积分配置
	@RequestMapping(value = "/member/integralType/success.shtml") 
	public ModelAndView integralTypeEditSuccess(HttpServletRequest request,IntegralType integralType) {
		String rtPage = "/success/success";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
    	StaffBean staffBean = this.getSessionStaffBean(request);
		String code = null;
		String msg = "";
		 try{
			if ("0".equals(integralType.getIntType())){
				integralType.setIntegral(0);
				integralType.setiCharge(0);
			}else if ("1".equals(integralType.getIntType())){
				integralType.setiCharge(0);
			}else if ("2".equals(integralType.getIntType())){
				integralType.setIntegral(0);
			}
			if ("0".equals(integralType.getGrowType())){
				integralType.setgValue(0);
				integralType.setgCharge(0);
			}else if ("1".equals(integralType.getGrowType())){
				integralType.setiCharge(0);
			}else if ("2".equals(integralType.getGrowType())){
				integralType.setgValue(0);
			}
			if(integralType.getId()!=null){
				integralType.setUpdateBy(Integer.valueOf(staffBean.getStaffID()));
				integralType.setUpdateDate(new Date());
				integralTypeService.updateByPrimaryKeySelective(integralType);
			}else{
				integralType.setCreateBy(Integer.valueOf(staffBean.getStaffID()));
				integralType.setCreateDate(new Date());
				integralTypeService.insertSelective(integralType);
			}
				
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	

	//停用积分规则
	@RequestMapping(value = "/member/integralType/stop.shtml")
	@ResponseBody
	public Map<String, Object>  integralTypeStopSuccess(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap) {	
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
    	StaffBean staffBean = this.getSessionStaffBean(request);
		try{
			IntegralType integralType=new IntegralType();
			integralType.setId(Integer.valueOf(request.getParameter("id")));
			integralType.setStatus("2");
			integralType.setUpdateBy(Integer.valueOf(staffBean.getStaffID()));
			integralType.setUpdateDate(new Date());
			integralTypeService.updateByPrimaryKeySelective(integralType);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap; 
	}
	
	//启用积分规则
	@RequestMapping(value = "/member/integralType/start.shtml")
	@ResponseBody
	public Map<String, Object>  integralTypeStartSuccess(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap) {	
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
    	StaffBean staffBean = this.getSessionStaffBean(request);
		try{
			IntegralType integralType=new IntegralType();
			integralType.setId(Integer.valueOf(request.getParameter("id")));
			integralType.setStatus("1");
			integralType.setUpdateBy(Integer.valueOf(staffBean.getStaffID()));
			integralType.setUpdateDate(new Date());
			integralTypeService.updateByPrimaryKeySelective(integralType); 
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap; 
	}
	
	//会员金币日志
	@RequestMapping(value = "/member/integralDtl/list.shtml")
	public ModelAndView integralDtlList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/member/integralDtl/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();		 
		return new ModelAndView(rtPage,resMap);
	} 
	
	@RequestMapping(value = "/member/integralDtl/datalist.shtml")
	@ResponseBody
	public Map<String, Object> integralDtlDataList(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			IntegralDtlCustomExample integralDtlCustomExample=new IntegralDtlCustomExample();
			IntegralDtlCustomExample.IntegralDtlCustomCriteria integralDtlCustomCriteria=integralDtlCustomExample.createCriteria();
			integralDtlCustomCriteria.andDelFlagEqualTo("0");
			integralDtlCustomExample.setOrderByClause("a.id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ){
				int memberId=Integer.valueOf(request.getParameter("memberId"));
				integralDtlCustomCriteria.andMemberIdEqualTo(memberId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				integralDtlCustomCriteria.andNickLike(nick);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile=request.getParameter("mobile");
				integralDtlCustomCriteria.andMobileEqualTo(mobile);
			}

			if(!StringUtil.isEmpty(request.getParameter("tallyModeType")) ){
				String tallyModeType=request.getParameter("tallyModeType");
				if ("1".equals(tallyModeType) || "2".equals(tallyModeType)) {
					integralDtlCustomCriteria.andTallyModeEqualTo(tallyModeType);
				}else if("3".equals(tallyModeType)){
					integralDtlCustomCriteria.andTallyModeEqualTo("1");
					integralDtlCustomCriteria.andTypeEqualTo(23);
				}else if("4".equals(tallyModeType)){
					integralDtlCustomCriteria.andTallyModeEqualTo("2");
					integralDtlCustomCriteria.andTypeEqualTo(22);
				}
			}
			
			totalCount = integralDtlService.countIntegralDtlCustomByExample(integralDtlCustomExample);
			
			integralDtlCustomExample.setLimitStart(page.getLimitStart());
			integralDtlCustomExample.setLimitSize(page.getLimitSize());
			List<IntegralDtlCustom> integralDtlCustoms=integralDtlService.selectIntegralDtlCustomByExample(integralDtlCustomExample);
		
			resMap.put("Rows", integralDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//单个会员赠送积分
	@RequestMapping(value = "/member/integralGive/forMember.shtml")
	public ModelAndView integralGiveForMember(HttpServletRequest request) {
		String rtPage = "/member/integralGive/forMember";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer accId=Integer.valueOf(request.getParameter("accId"));
		Integer memberId=Integer.valueOf(request.getParameter("memberId"));
		resMap.put("accId", accId);
		resMap.put("memberId", memberId);

    	return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/member/integralGive/forMemberSuccess.shtml") 
	public ModelAndView integralGiveforMemberSuccess(HttpServletRequest request) {
		String rtPage = "/success/success";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
    	StaffBean staffBean = this.getSessionStaffBean(request);
		String code = null;
		String msg = null; 
		 try{
			
			Integer accId=Integer.valueOf(request.getParameter("accId"));
			Integer integral=Integer.valueOf(request.getParameter("integral"));
			MemberAccount memberAccount=memberAccountService.selectByPrimaryKey(accId);
			memberAccount.setIntegral(memberAccount.getIntegral()+integral);
			memberAccount.setUpdateBy(Integer.valueOf(staffBean.getStaffID()));
			memberAccount.setUpdateDate(new Date());
			
			if(!StringUtil.isEmpty(request.getParameter("remarks")) ){
				String remarks=request.getParameter("remarks");
				memberAccount.setRemarks(remarks);
			}

			memberAccountService.updateByPrimaryKeySelective(memberAccount);
			
			IntegralDtl integralDtl=new IntegralDtl();
			integralDtl.setAccId(memberAccount.getId());
			integralDtl.setTallyMode("1");
			integralDtl.setIntegral(integral);
			integralDtl.setBalance(memberAccount.getIntegral());
			integralDtl.setCreateBy(Integer.valueOf(staffBean.getStaffID()));
			
			integralDtlService.insertSelective(integralDtl);
			
				
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	//批量赠送会员积分
	@RequestMapping(value = "/member/integralGive/index.shtml")
	public ModelAndView integralGiveindex(HttpServletRequest request) {
		String rtPage = "/member/integralGive/index";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		MemberGroupExample memberGroupExample=new MemberGroupExample();
		memberGroupExample.createCriteria().andDelFlagEqualTo("0");
		
		List<MemberGroup> memberGroups=memberGroupService.selectByExample(memberGroupExample);
		resMap.put("memberGroups", memberGroups);

		resMap.put("types", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "TYPE"));

    	return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/member/integralGive/success.shtml") 
	public ModelAndView integralGiveSuccess(HttpServletRequest request) {
		String rtPage = "/success/success";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
    	StaffBean staffBean = this.getSessionStaffBean(request);
		String code = null;
		String msg = null; 
		 try{
			 
			IntegralGive integralGive=new IntegralGive();
			integralGive.setCreateBy(Integer.valueOf(staffBean.getStaffID()));
			integralGive.setCreateDate(new Date());
			 
			if(!StringUtil.isEmpty(request.getParameter("type")) ){
				Integer type=Integer.valueOf(request.getParameter("type"));
				integralGive.setType(type);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("groupId")) ){
				String groupId=request.getParameter("groupId");
				integralGive.setGroupId(groupId);
			}
				
			if(!StringUtil.isEmpty(request.getParameter("integral")) ){
				Integer integral=Integer.valueOf(request.getParameter("integral"));
				integralGive.setIntegral(integral);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("remarks")) ){
				String remarks=request.getParameter("remarks");
				integralGive.setRemarks(remarks);
			}

			integralGiveService.insertSelective(integralGive);
				
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	//赠送积分列表
	@RequestMapping(value = "/member/integralGive/list.shtml")
	public ModelAndView integralGiveList(HttpServletRequest request) {
		String rtPage = "/member/integralGive/list";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		MemberGroupExample memberGroupExample=new MemberGroupExample();
		memberGroupExample.createCriteria().andDelFlagEqualTo("0");
		
		List<MemberGroup> memberGroups=memberGroupService.selectByExample(memberGroupExample);
		resMap.put("memberGroups", memberGroups);

		resMap.put("types", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "TYPE"));
		resMap.put("auditStatus", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "AUDIT_STATUS"));

    	return new ModelAndView(rtPage,resMap);
	}
	
	//赠送积分数据
	@RequestMapping(value = "/member/integralGive/datalist.shtml")
	@ResponseBody
	public Map<String, Object> integralGiveDataList(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			IntegralGiveCustomExample integralGiveCustomExample=new IntegralGiveCustomExample();
			IntegralGiveCustomExample.IntegralGiveCustomCriteria integralGiveCustomCriteria=integralGiveCustomExample.createCriteria();
			integralGiveCustomCriteria.andDelFlagEqualTo("0");
			integralGiveCustomExample.setOrderByClause("a.id desc");
						
			if(!StringUtil.isEmpty(request.getParameter("staff"))){
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				integralGiveCustomCriteria.andCreateByEqualTo(staffId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("auditStatus")) ){
				String auditStatus=request.getParameter("auditStatus");
				integralGiveCustomCriteria.andAuditStatusEqualTo(auditStatus);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("staffName")) ){
				String staffName=request.getParameter("staffName");
				integralGiveCustomCriteria.andStaffNameEqualTo(staffName);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("remarks")) ){
				String remarks=request.getParameter("remarks");
				integralGiveCustomCriteria.andRemarksLike("%"+remarks+"%");
			}
			
			totalCount = integralGiveService.countIntegralGiveCustomByExample(integralGiveCustomExample);
			
			integralGiveCustomExample.setLimitStart(page.getLimitStart());
			integralGiveCustomExample.setLimitSize(page.getLimitSize());
			List<IntegralGiveCustom> integralGiveCustoms=integralGiveService.selectIntegralGiveCustomByExample(integralGiveCustomExample);
		
			resMap.put("Rows", integralGiveCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//赠送积分审核
	@RequestMapping(value = "/member/integralGive/audit.shtml")
	public ModelAndView integralGiveAudit(HttpServletRequest request) {
		String rtPage = "/member/integralGive/audit";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		Integer id= Integer.valueOf(request.getParameter("id"));
		
		IntegralGiveCustom integralGiveCustom=integralGiveService.selectIntegralGiveCustomByPrimaryKey(id);
		resMap.put("integralGiveCustom", integralGiveCustom);

    	return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/member/integralGive/auditSubmit.shtml")
	@ResponseBody
	public Map<String, Object> integralGiveAuditSubmit(Model model,HttpServletRequest request, MchtDepositDtl mchtDepositDtl) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		Integer id= Integer.valueOf(request.getParameter("id"));
		String auditStatus= request.getParameter("auditStatus");
		String auditRemarks= request.getParameter("auditRemarks");
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			IntegralGive integralGive=integralGiveService.selectByPrimaryKey(id);
			
			integralGive.setAuditStatus(auditStatus);
			integralGive.setAuditRemarks(auditRemarks);
			integralGive.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			integralGive.setUpdateDate(new Date());
			
			integralGiveService.updateByPrimaryKeySelective(integralGive);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title getIntegralGive   
	 * @Description TODO(查看赠送会员金币审核)   
	 * @author pengl
	 * @date 2017年9月22日 下午5:06:57
	 */
	@RequestMapping(value = "/member/integralGive/getIntegralGive.shtml")
	public ModelAndView getIntegralGive(HttpServletRequest request, Integer id) {
		ModelAndView m = new ModelAndView("/member/integralGive/getIntegralGive");
		IntegralGiveCustom integralGiveCustom = integralGiveService.selectIntegralGiveCustomByPrimaryKey(id);
		m.addObject("integralGiveCustom", integralGiveCustom);
		m.addObject("auditStatusList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "AUDIT_STATUS"));
		return m;
	}
	
	//会员成长值日记
	@RequestMapping(value = "/member/growthDtl/list.shtml")
	public ModelAndView growthDtlList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/member/growthDtl/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();		 
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/member/growthDtl/datalist.shtml")
	@ResponseBody
	public Map<String, Object> growthDtlDataList(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {	
			GrowthDtlCustomExample growthDtlCustomExample=new GrowthDtlCustomExample();
			GrowthDtlCustomExample.GrowthDtlCustomCriteria growthDtlCustomCriteria=growthDtlCustomExample.createCriteria();
			growthDtlCustomCriteria.andDelFlagEqualTo("0");
			growthDtlCustomExample.setOrderByClause("a.id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ){
				int memberId=Integer.valueOf(request.getParameter("memberId"));
				growthDtlCustomCriteria.andMemberIdEqualTo(memberId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				growthDtlCustomCriteria.andNickLike(nick);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile=request.getParameter("mobile");
				growthDtlCustomCriteria.andMobileEqualTo(mobile);
			}
			
			totalCount = growthDtlService.countByExample(growthDtlCustomExample);
			
			growthDtlCustomExample.setLimitStart(page.getLimitStart());
			growthDtlCustomExample.setLimitSize(page.getLimitSize());
			List<GrowthDtlCustom> growthDtlCustoms=growthDtlService.selectGrowthDtlCustomByExample(growthDtlCustomExample);

			
			resMap.put("Rows", growthDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title integralGiveAdd   
	 * @Description TODO(积分赠送添加)   
	 * @author pengl
	 * @date 2017年12月18日 下午2:55:26
	 */
	@RequestMapping(value = "/member/integralGive/integralGiveAddList.shtml")
	public ModelAndView integralGiveAddList(HttpServletRequest request ) {
		ModelAndView m = new ModelAndView("/member/integralGive/integralGiveAddList");
		m.addObject("isDepositDeductList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "IS_DEPOSIT_DEDUCT"));
		m.addObject("auditStatusList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "AUDIT_STATUS"));
    	return m;
	}
	
	/**
	 * 
	 * @Title integralGiveCheck   
	 * @Description TODO(积分赠送审核)   
	 * @author pengl
	 * @date 2017年12月18日 下午3:06:08
	 */
	@RequestMapping(value = "/member/integralGive/integralGiveCheckList.shtml")
	public ModelAndView integralGiveCheckList(HttpServletRequest request ) {
		ModelAndView m = new ModelAndView("/member/integralGive/integralGiveCheckList");
		m.addObject("isDepositDeductList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "IS_DEPOSIT_DEDUCT"));
		m.addObject("auditStatusList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "AUDIT_STATUS"));
    	return m;
	}
	
	/**
	 * 
	 * @Title integralGiveAddCostBear   
	 * @Description TODO(积分赠送商家承担)   
	 * @author pengl
	 * @date 2017年12月18日 下午3:02:19
	 */
	@RequestMapping(value = "/member/integralGive/integralGiveAddCostBearList.shtml")
	public ModelAndView integralGiveAddCostBearList(HttpServletRequest request ) {
		ModelAndView m = new ModelAndView("/member/integralGive/integralGiveAddCostBearList");
		m.addObject("isDepositDeductList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "IS_DEPOSIT_DEDUCT"));
    	return m;
	}
	
	/**
	 * 
	 * @Title integralGiveList   
	 * @Description TODO(积分赠送商列表)   
	 * @author pengl
	 * @date 2017年12月18日 下午3:39:42
	 */
	@ResponseBody
	@RequestMapping(value = "/member/integralGive/integralGiveList.shtml")
	public Map<String, Object> integralGiveList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<IntegralGiveCustom> integralGiveCustomList = new ArrayList<IntegralGiveCustom>();
		Integer totalCount = 0;
		try {
			IntegralGiveCustomExample integralGiveCustomExample = new IntegralGiveCustomExample();
			IntegralGiveCustomExample.IntegralGiveCustomCriteria integralGiveCustomCriteria = integralGiveCustomExample.createCriteria();
			integralGiveCustomCriteria.andDelFlagEqualTo("0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("startCreateDate"))) {
				String startCreateDate = request.getParameter("startCreateDate")+" 00:00:00";
				integralGiveCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(startCreateDate));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				String endCreateDate = request.getParameter("endCreateDate")+" 23:59:59";
				integralGiveCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(endCreateDate));
			}
			if(!StringUtil.isEmpty(request.getParameter("startUpdateDate"))) {
				String startUpdateDate = request.getParameter("startUpdateDate")+" 00:00:00";
				integralGiveCustomCriteria.andUpdateDateGreaterThanOrEqualTo(sdf.parse(startUpdateDate));
			}
			if(!StringUtil.isEmpty(request.getParameter("endUpdateDate"))) {
				String endUpdateDate = request.getParameter("endUpdateDate")+" 23:59:59";
				integralGiveCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(endUpdateDate));
			}
			if(!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
				String[] auditStatuss = request.getParameter("auditStatus").split(",");
				if(auditStatuss.length > 1) {
					List<String> auditStatusList = new ArrayList<String>();
					for(String auditS : auditStatuss) {
						auditStatusList.add(auditS);
					}
					integralGiveCustomCriteria.andAuditStatusIn(auditStatusList);
				}else {
					integralGiveCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("isDepositDeduct"))) {
				integralGiveCustomCriteria.andIsDepositDeductEqualTo(request.getParameter("isDepositDeduct"));
			}
			if(!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
				integralGiveCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				integralGiveCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			integralGiveCustomExample.setOrderByClause("a.id desc");
			integralGiveCustomExample.setLimitStart(page.getLimitStart());
			integralGiveCustomExample.setLimitSize(page.getLimitSize());
			totalCount = integralGiveService.countByExample(integralGiveCustomExample);
			integralGiveCustomList = integralGiveService.selectIntegralGiveCustomByExample(integralGiveCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", integralGiveCustomList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title integralGiveAdd   
	 * @Description TODO(积分赠送添加)   
	 * @author pengl
	 * @date 2017年12月18日 下午3:58:44
	 */
	@RequestMapping(value = "/member/integralGive/integralGiveAdd.shtml")
	public ModelAndView integralGiveAdd(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/member/integralGive/integralGiveAdd");
		MemberGroupExample memberGroupExample = new MemberGroupExample();
		memberGroupExample.createCriteria().andDelFlagEqualTo("0");
		List<MemberGroup> memberGroupList = memberGroupService.selectByExample(memberGroupExample);
		m.addObject("memberGroupList", memberGroupList);
		SysSmsTemplateExample sysSmsTemplateExample = new SysSmsTemplateExample();
		sysSmsTemplateExample.createCriteria().andDelFlagEqualTo("0").andTemplateTypeEqualTo(1);
		List<SysSmsTemplate> sysSmsTemplateList = sysSmsTemplateService.selectByExample(sysSmsTemplateExample);
		m.addObject("sysSmsTemplateList", sysSmsTemplateList);
		m.addObject("typeList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "TYPE"));
		m.addObject("costBearList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "COST_BEAR"));
		m.addObject("isDepositDeductList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "IS_DEPOSIT_DEDUCT"));
		return m;
	}
	
	/**
	 * 
	 * @Title integralGiveAudit   
	 * @Description TODO(积分赠送审核)   
	 * @author pengl
	 * @date 2017年12月18日 下午3:58:44
	 */
	@RequestMapping(value = "/member/integralGive/integralGiveCheck.shtml")
	public ModelAndView integralGiveCheck(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/member/integralGive/integralGiveCheck");
		if(!StringUtil.isEmpty(request.getParameter("id"))) {
			IntegralGiveCustom integralGiveCustom = integralGiveService.selectIntegralGiveCustomByPrimaryKey(Integer.parseInt(request.getParameter("id")));
			m.addObject("integralGiveCustom", integralGiveCustom);
			if(!StringUtil.isEmpty(integralGiveCustom.getTemplateContent())) {
				String templateContentStr = integralGiveCustom.getTemplateContent().replace("{1}", integralGiveCustom.getIntegral()+"").replace("{2}", Float.parseFloat(integralGiveCustom.getIntegral()+"")/100+"");
				m.addObject("templateContentStr", templateContentStr);
			}
		}
		MemberGroupExample memberGroupExample = new MemberGroupExample();
		memberGroupExample.createCriteria().andDelFlagEqualTo("0");
		List<MemberGroup> memberGroupList = memberGroupService.selectByExample(memberGroupExample);
		m.addObject("memberGroupList", memberGroupList);
		m.addObject("typeList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "TYPE"));
		m.addObject("costBearList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "COST_BEAR"));
		m.addObject("isDepositDeductList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "IS_DEPOSIT_DEDUCT"));
		return m;
	}
	/**
	 * 
	 * @Title integralGiveShow   
	 * @Description TODO(积分赠送查看)   
	 * @author pengl
	 * @date 2017年12月18日 下午3:58:44
	 */
	@RequestMapping(value = "/member/integralGive/integralGiveShow.shtml")
	public ModelAndView integralGiveShow(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/member/integralGive/integralGiveShow");
		if(!StringUtil.isEmpty(request.getParameter("id"))) {
			IntegralGiveCustom integralGiveCustom = integralGiveService.selectIntegralGiveCustomByPrimaryKey(Integer.parseInt(request.getParameter("id")));
			m.addObject("integralGiveCustom", integralGiveCustom);
			if(!StringUtil.isEmpty(integralGiveCustom.getTemplateContent())) {
				String templateContentStr = integralGiveCustom.getTemplateContent().replace("{1}", integralGiveCustom.getIntegral()+"").replace("{2}", Float.parseFloat(integralGiveCustom.getIntegral()+"")/100+"");
				m.addObject("templateContentStr", templateContentStr);
			}
		}
		MemberGroupExample memberGroupExample = new MemberGroupExample();
		memberGroupExample.createCriteria().andDelFlagEqualTo("0");
		List<MemberGroup> memberGroupList = memberGroupService.selectByExample(memberGroupExample);
		m.addObject("memberGroupList", memberGroupList);
		m.addObject("typeList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "TYPE"));
		m.addObject("costBearList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "COST_BEAR"));
		m.addObject("isDepositDeductList", DataDicUtil.getStatusList("BU_INTEGRAL_GIVE", "IS_DEPOSIT_DEDUCT"));
		return m;
	}
	
	/**
	 * 
	 * @Title saveOrUpdateIntegralGive   
	 * @Description TODO(积分赠送添加、审核)   
	 * @author pengl
	 * @date 2017年12月18日 下午4:16:52
	 */
	@RequestMapping(value = "/member/integralGive/saveOrUpdateIntegralGive.shtml")
	public ModelAndView saveOrUpdateIntegralGive(HttpServletRequest request, IntegralGive integralGive) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = null;
		try {
			integralGive.setUpdateDate(new Date());
			integralGive.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(integralGive.getId() != null) { //审核
				if(integralGive.getAuditStatus().equals("1")) {
					if(integralGive.getType() == 1) { //所有会员
						MemberInfoExample memberInfoExample = new MemberInfoExample();
						memberInfoExample.createCriteria().andDelFlagEqualTo("0");
						Integer memberInfoCount = memberInfoService.countByExample(memberInfoExample);
						integralGive.setMemberCount(memberInfoCount);
					}else if(integralGive.getType() == 2) { //指定会员组
						String groupId = integralGive.getGroupId();
						String[] groupIds = groupId.split(",");
						List<Integer> groupIdList = new ArrayList<Integer>();
						for (int i = 0; i < groupIds.length; i++) {
							if(!StringUtil.isEmpty(groupIds[i])) {
								groupIdList.add(Integer.parseInt(groupIds[i]));
							}
						}
						Integer memberInfoCount = 0;
						if(groupIdList.size() > 0) {
							MemberInfoExample memberInfoExample = new MemberInfoExample();
							memberInfoExample.createCriteria().andDelFlagEqualTo("0").andGroupIdIn(groupIdList);
							memberInfoCount = memberInfoService.countByExample(memberInfoExample);
						}
						integralGive.setMemberCount(memberInfoCount);
					}else if(integralGive.getType() == 3) { //指定会员
						String groupId = integralGive.getGroupId();
						String[] groupIds = groupId.split(",");
						int num = 0;
						for (int i = 0; i < groupIds.length; i++) {
							if(!StringUtil.isEmpty(groupIds[i])) {
								++num;
							}
						}
						integralGive.setMemberCount(num);
					}else if(integralGive.getType() == 4) { //指定订单号
						integralGive.setMemberCount(1);
					}
				}
				integralGiveService.updateByPrimaryKeySelective(integralGive);
			}else { //添加
				integralGive.setCreateDate(new Date());
				integralGive.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				integralGiveService.insertSelective(integralGive);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title getSubOrderCode   
	 * @Description TODO(验证子订单号)   
	 * @author pengl
	 * @date 2017年12月18日 下午6:29:25
	 */
	@ResponseBody
	@RequestMapping(value = "/member/integralGive/getSubOrderCode.shtml")
	public Map<String, Object> getSubOrderCode(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		if(!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
			SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
			subOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
			List<SubOrderCustom> subOrderCustomList = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
			if(subOrderCustomList != null && subOrderCustomList.size() > 0) {
				map.put("subOrderCustom", subOrderCustomList.get(0));
			}else {
				statusCode = "404";
			}
		}else {
			statusCode = "999";
		}
		map.put("statusCode", statusCode);
		return map;
	}
	
	/**
	 * 
	 * @Title deleteIntegralGive   
	 * @Description TODO(积分赠送删除)   
	 * @author pengl
	 * @date 2017年12月19日 下午1:44:29
	 */
	@ResponseBody
	@RequestMapping(value = "/member/integralGive/deleteIntegralGive.shtml")
	public Map<String, Object> deleteIntegralGive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			if(!StringUtil.isEmpty(request.getParameter("id"))) {
				IntegralGive integralGive = new IntegralGive();
				integralGive.setId(Integer.parseInt(request.getParameter("id")));
				integralGive.setDelFlag("1");
				integralGiveService.updateByPrimaryKeySelective(integralGive);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateIsDepositDeduct   
	 * @Description TODO(修改保证金扣款)   
	 * @author pengl
	 * @date 2017年12月20日 上午9:39:51
	 */
	@ResponseBody
	@RequestMapping(value = "/member/integralGive/updateIsDepositDeduct.shtml")
	public Map<String, Object> updateIsDepositDeduct(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			if(!StringUtil.isEmpty(request.getParameter("id"))) {
				IntegralGive integralGive = new IntegralGive();
				integralGive.setId(Integer.parseInt(request.getParameter("id")));
				integralGive.setIsDepositDeduct(request.getParameter("isDepositDeduct"));
				integralGive.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				integralGive.setUpdateDate(new Date());
				integralGiveService.updateByPrimaryKeySelective(integralGive);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateMemberStatus   
	 * @Description TODO(加入或取消黑名单)   
	 * @author pengl
	 * @date 2018年9月29日 下午2:27:49
	 */
	@ResponseBody
	@RequestMapping(value = "/member/integralGive/updateMemberStatus.shtml")
	public Map<String, Object> updateMemberStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			String memberId = request.getParameter("memberId");
			String memberStatus = request.getParameter("memberStatus");
			if(!StringUtil.isEmpty(memberId)) {
				Date date = new Date();
				String staffId = this.getSessionStaffBean(request).getStaffID();
				MemberInfo memberInfo = new MemberInfo();
				memberInfo.setId(Integer.parseInt(memberId));
				memberInfo.setStatus(memberStatus);
				memberInfo.setUpdateBy(Integer.parseInt(staffId));
				memberInfo.setUpdateDate(date);
				memberInfoService.updateByPrimaryKeySelective(memberInfo);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title subOrderList   
	 * @Description TODO(相关订单)   
	 * @author pengl
	 * @date 2018年9月29日 下午4:30:23
	 */
	@ResponseBody
	@RequestMapping(value = "/member/integralGive/subOrderList.shtml")
	public Map<String, Object> subOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SubOrderCustom> subOrderCustomList = new ArrayList<SubOrderCustom>();
		Integer totalCount = 0;
		try {
			String memberId = request.getParameter("memberId");
			if(!StringUtil.isEmpty(memberId)) {
				MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(Integer.parseInt(memberId));
				SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
				SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
				subOrderCustomCriteria.andDelFlagEqualTo("0");
				subOrderCustomCriteria.andMemberInfoOr(memberInfo);
				subOrderCustomExample.setLimitStart(page.getLimitStart());
				subOrderCustomExample.setLimitSize(page.getLimitSize());
				subOrderCustomList = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
				totalCount = subOrderService.countSubOrderCustomByExample(subOrderCustomExample);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", subOrderCustomList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	 //会员店铺足迹
	@RequestMapping(value = "/member/footprint/shoplist.shtml")
	public ModelAndView shoplist(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/member/footprint/shoplist";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Calendar calendars = Calendar.getInstance();
		calendars.set(Calendar.HOUR_OF_DAY,23);
		calendars.set(Calendar.MINUTE, 59);
		calendars.set(Calendar.SECOND, 59);
		
		String dateBegin = df.format(calendar.getTime());
		String dateEnd = df.format(calendars.getTime());
		
		resMap.put("updateDate_end",   dateEnd);
		resMap.put("updateDate_begin", dateBegin);
		return new ModelAndView(rtPage,resMap);
	}

	
	@RequestMapping(value = "/member/footprint/shopdatalist.shtml")
	@ResponseBody
	public Map<String, Object> shopdatalist(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {	
			MemberShopFootprintCustomExample membershopFootprintCustomExample=new MemberShopFootprintCustomExample();
			MemberShopFootprintCustomExample.MemberShopFootprintCustomCriteria membershopFootprintCustomCriteria=membershopFootprintCustomExample.createCriteria();
			membershopFootprintCustomExample.setOrderByClause("a.create_date desc");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ){
				int memberId=Integer.valueOf(request.getParameter("memberId"));
				membershopFootprintCustomCriteria.andMemberIdEqualTo(memberId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				membershopFootprintCustomCriteria.andNickLike(nick);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile=request.getParameter("mobile");
				membershopFootprintCustomCriteria.andMobileEqualTo(mobile);
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ){
				String mchtCode=request.getParameter("mchtCode");
				membershopFootprintCustomCriteria.andMchtCodeEqualTo(mchtCode);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("updateDate_begin")) ){
				membershopFootprintCustomCriteria.andUpdateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("updateDate_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("updateDate_end")) ){
				membershopFootprintCustomCriteria.andUpdateDateLessThanOrEqualTo(sdf.parse(request.getParameter("updateDate_end")+" 23:59:59"));
			}
			
			totalCount = memberShopFootprintService.countByExample(membershopFootprintCustomExample);
			
			membershopFootprintCustomExample.setLimitStart(page.getLimitStart());
			membershopFootprintCustomExample.setLimitSize(page.getLimitSize());
			List<MemberShopFootprintCustom> memberFootprintCustoms=memberShopFootprintService.selectMemberShopFootprintCustomByExample(membershopFootprintCustomExample);

			
			resMap.put("Rows", memberFootprintCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	        //会员特卖足迹
			@RequestMapping(value = "/member/footprint/specialSalelist.shtml")
			public ModelAndView specialSalelist(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
				String rtPage = "/member/footprint/specialSalelist";//重定向
				Map<String,Object> resMap = new HashMap<String,Object>();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.set(Calendar.HOUR_OF_DAY,0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				
				Calendar calendars = Calendar.getInstance();
				calendars.set(Calendar.HOUR_OF_DAY,23);
				calendars.set(Calendar.MINUTE, 59);
				calendars.set(Calendar.SECOND, 59);
				
				String dateBegin = df.format(calendar.getTime());
				String dateEnd = df.format(calendars.getTime());
				
				resMap.put("updateDate_end",   dateEnd);
				resMap.put("updateDate_begin", dateBegin);
				return new ModelAndView(rtPage,resMap);
			}
		
			
			@RequestMapping(value = "/member/footprint/specialSalelistdatalist.shtml")
			@ResponseBody
			public Map<String, Object> specialSalelistdatalist(HttpServletRequest request,Page page) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				Integer totalCount = 0;
				try {	
					MemberActivityFootprintCustomExample memberactivityFootprintCustomExample=new MemberActivityFootprintCustomExample();
					MemberActivityFootprintCustomExample.MemberActivityFootprintCustomCriteria memberActivityFootprintCustomCriteria=memberactivityFootprintCustomExample.createCriteria();
					memberactivityFootprintCustomExample.setOrderByClause("a.create_date desc");
					
					if(!StringUtil.isEmpty(request.getParameter("memberId")) ){
						int memberId=Integer.valueOf(request.getParameter("memberId"));
						memberActivityFootprintCustomCriteria.andMemberIdEqualTo(memberId);
					}
					
					if(!StringUtil.isEmpty(request.getParameter("nick")) ){
						String nick=request.getParameter("nick");
						memberActivityFootprintCustomCriteria.andNickLike(nick);
					}
					
					if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
						String mobile=request.getParameter("mobile");
						memberActivityFootprintCustomCriteria.andMobileEqualTo(mobile);
					}
					if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ){
						String mchtCode=request.getParameter("mchtCode");
						memberActivityFootprintCustomCriteria.andMchtCodeEqualTo(mchtCode);
					}
					
					if(!StringUtil.isEmpty(request.getParameter("activityId")) ){
						String activityId=request.getParameter("activityId");
						memberActivityFootprintCustomCriteria.andActivityIdEqualTo(Integer.valueOf(activityId));
					}
				
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(!StringUtil.isEmpty(request.getParameter("updateDate_begin")) ){
						memberActivityFootprintCustomCriteria.andUpdateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("updateDate_begin")+" 00:00:00"));
					}
					if(!StringUtil.isEmpty(request.getParameter("updateDate_end")) ){
						memberActivityFootprintCustomCriteria.andUpdateDateLessThanOrEqualTo(sdf.parse(request.getParameter("updateDate_end")+" 23:59:59"));
					}
					
					totalCount = memberActivityFootprintService.countByExample(memberactivityFootprintCustomExample);
					
					memberactivityFootprintCustomExample.setLimitStart(page.getLimitStart());
					memberactivityFootprintCustomExample.setLimitSize(page.getLimitSize());
					List<MemberActivityFootprintCustom> memberFootprintCustoms=memberActivityFootprintService.selectMemberActivityFootprintCustomByExample(memberactivityFootprintCustomExample);
					
					resMap.put("Rows", memberFootprintCustoms);
					resMap.put("Total", totalCount);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return resMap;
			}
			
	/**
	 * 加入黑名单页面(改造后)
	 * 
	 * @param request
	 * @param memberId
	 * @param operateType
	 * @return
	 */
	@RequestMapping(value = "/member/limitPermission.shtml")
	public ModelAndView limitPermission(HttpServletRequest request, Integer memberId, String operateType) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("memberId", memberId);
		resMap.put("operateType", operateType);
		// 如果操作类型为3则是移出黑名单
		String status = !StringUtil.isEmpty(operateType) && "3".equals(operateType) ? "A": "P";
		resMap.put("status", status);
		resMap.put("blackInfo", memberInfoService.getBlackInfo(memberId));
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		resMap.put("canOperate", memberInfoService.getCanOperate(staffId));
		String rtPage = "/member/info/blackDetail";
		return new ModelAndView(rtPage, resMap);
	}
	
	
	/**
	 * 提交黑名单信息(改造后)
	 * 
	 * @param request
	 * @param memberInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/member/saveLimitPermission.shtml")
	public Map<String, Object> saveLimitPermission(HttpServletRequest request, MemberInfoCustom memberInfo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_ERROR.getStateCode();
		String msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		try {
			if (memberInfo != null) {
				memberInfoService.saveLimitPermission(memberInfo, staffId);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}
		} catch (ArgException e) {
			msg = e.getMessage();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 黑名单操作日志列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/member/blackOperationList.shtml")
	public ModelAndView blackOperationList() {
		return new ModelAndView("/member/info/blackOperationLogList");
	}
	
	/**
	 * 获取黑名单操作日志列表数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/member/blackOperationData.shtml")
	@ResponseBody
	public Map<String, Object> blackOperationData(HttpServletRequest request, Page page) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			
			MemberBlackOperateLogExample memberBlackOperateLogExample = new MemberBlackOperateLogExample();
			MemberBlackOperateLogExample.Criteria logCriteria = memberBlackOperateLogExample.createCriteria();
			memberBlackOperateLogExample.setOrderByClause("id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ){
				int memberId = Integer.valueOf(request.getParameter("memberId"));
				logCriteria.andMemberIdEqualTo(memberId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("memberNick")) ){
				String memberNick = request.getParameter("memberNick");
				logCriteria.andMemberNickLike("%" + memberNick + "%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("limitType")) ){
				String limitTpe = request.getParameter("limitType");
				logCriteria.andLimitFunctionLike("%" + limitTpe + "%");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("createDate_begin")) ){
				logCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDate_begin") + " 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDate_end")) ){
				logCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDate_end") + " 23:59:59"));
			}
			
			totalCount = memberInfoService.countBlackOperateLogByExample(memberBlackOperateLogExample);
			
			memberBlackOperateLogExample.setLimitStart(page.getLimitStart());
			memberBlackOperateLogExample.setLimitSize(page.getLimitSize());
			List<MemberBlackOperateLogCustom> list = memberInfoService.selectBlackOperateLogByExample(memberBlackOperateLogExample);
			
			resultMap.put("Rows", list);
			resultMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	        //会员恢复状态
			@RequestMapping(value = "/member/memberRecovery.shtml")
			@ResponseBody
			public Map<String, Object> memberRecovery(HttpServletRequest request) {
				Map<String, Object> resMap = new HashMap<String, Object>(); 
				resMap.put("returnCode", "0000");
				resMap.put("returnMsg", "成功");
				try {
					Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
					String memberid=request.getParameter("id");
					String memberstatus=request.getParameter("status");
					MemberStatusLogExample memStatusLogExample=new MemberStatusLogExample();
					memStatusLogExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(Integer.valueOf(memberid)).andStatusNotEqualTo(memberstatus);
					memStatusLogExample.setOrderByClause("create_date desc");
					List<MemberStatusLog> memberStatusLog=memberStatusLogService.selectByExample(memStatusLogExample);
					if (memberStatusLog.size()>0) {
						MemberInfo memberInfo=memberInfoService.selectByPrimaryKey(Integer.valueOf(memberid));
						memberInfo.setStatus(memberStatusLog.get(0).getStatus());
						memberInfo.setUpdateBy(staffId);
						memberInfo.setUpdateDate(new Date());
						memberInfoService.updateByPrimaryKey(memberInfo);
					}else {
						MemberInfo memberInfo=memberInfoService.selectByPrimaryKey(Integer.valueOf(memberid));
						memberInfo.setStatus("A");
						memberInfo.setUpdateBy(staffId);
						memberInfo.setUpdateDate(new Date());
						memberInfoService.updateByPrimaryKey(memberInfo);
					}
					
				}catch (Exception e) {
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", e.getMessage());
					e.printStackTrace();
				}

				return resMap;
			}
			
			
			//会员相关举报单
			@ResponseBody
			@RequestMapping(value = "/member/impeachMemberList.shtml")
			public Map<String, Object> impeachMemberList(HttpServletRequest request, Page page) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				Integer totalCount = 0;
				try {
					String memberId = request.getParameter("memberId");

						ImpeachMemberCustomExample impeachMemberCustomExample=new ImpeachMemberCustomExample();
						ImpeachMemberCustomExample.ImpeachMemberCustomCriteria impeachMemberCustomeCriteria=impeachMemberCustomExample.createCriteria();
						impeachMemberCustomeCriteria.andDelFlagEqualTo("0").andMemberIdsEqualTo(memberId);
						
						totalCount = impeachMemberService.countByImpeachMemberCustomExample(impeachMemberCustomExample);
						impeachMemberCustomExample.setLimitStart(page.getLimitStart());
						impeachMemberCustomExample.setLimitSize(page.getLimitSize());
						List<ImpeachMemberCustom> impeachMemberCustoms = impeachMemberService.selectByImpeachMemberCustomExample(impeachMemberCustomExample);
						
						resMap.put("Rows", impeachMemberCustoms);
						resMap.put("Total", totalCount);
	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return resMap;
			}
			
			
			//会员数据(日报表)
			@RequestMapping(value = "/member/membership/memberDailyreports.shtml")
			public ModelAndView memberDailyreports(HttpServletRequest request) {
				String rtPage = "/member/membership/memberDailyreports";
				Map<String, Object> resMap = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date_end = df.format(new Date());
				String date_begin = date_end.substring(0,7)+"-01";
				resMap.put("date_end", date_end);
				resMap.put("date_begin", date_begin);
				return new ModelAndView(rtPage,resMap);
			}
			
			//会员数据(日报表)
			@RequestMapping(value = "/membership/memberDailyreportsdata.shtml")
			@ResponseBody
			public Map<String, Object> memberDailyreportsdata(HttpServletRequest request,Page page) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				try {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					String dateBegin = request.getParameter("date_begin");
					String dateEnd = request.getParameter("date_end");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					if(!StringUtil.isEmpty(dateBegin) ){
						paramMap.put("dateBegin", dateBegin);
					}else{
						paramMap.put("dateBegin", df.format(new Date()).substring(0,7)+"-01");
					}
					if(!StringUtil.isEmpty(dateEnd) ){
						paramMap.put("dateEnd", dateEnd);
					}else{
						paramMap.put("dateEnd", df.format(new Date()));
					}
					List<MemberInfoCustom> memberInfoCustoms=memberInfoService.memberEverydayCount(paramMap);
					resMap.put("Rows", memberInfoCustoms);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return resMap;
			}
	
			//会员数据(月报表)
			@RequestMapping(value = "/member/membership/memberMonthlyReport.shtml")
			public ModelAndView memberMonthlyReport(HttpServletRequest request) {
				String rtPage = "/member/membership/memberMonthlyReport";
				Map<String, Object> resMap = new HashMap<String, Object>();
				
				Date date = new Date();
				Calendar calendar = Calendar.getInstance(); 
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, -3);
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
				String date_begin = df.format(calendar.getTime());
				String date_end=df.format(date);
				resMap.put("date_begin", date_begin);
				resMap.put("date_end", date_end);
				
				return new ModelAndView(rtPage,resMap);
			}
			
			
			//会员数据(月报表)
			@RequestMapping(value = "/membership/memberMonthlyReportdata.shtml")
			@ResponseBody
			public Map<String, Object> memberMonthlyReportdata(HttpServletRequest request,Page page) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				try {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					String dateBegin = request.getParameter("date_begin");
					String dateEnd = request.getParameter("date_end");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
					if(!StringUtil.isEmpty(dateBegin) ){
						paramMap.put("dateBegin", dateBegin);
					}else{
						paramMap.put("dateBegin", df.format(new Date()).substring(0,5));
					}
					if(!StringUtil.isEmpty(dateEnd) ){
						paramMap.put("dateEnd", dateEnd);
					}else{
						paramMap.put("dateEnd", df.format(new Date()));
					}
					List<MemberInfoCustom> memberInfoCustoms=memberInfoService.memberMonthlydayCount(paramMap);
					resMap.put("Rows", memberInfoCustoms);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return resMap;
			}
			
	        //会员数据(周报表)
			@RequestMapping(value = "/member/membership/memberWeeklyReport.shtml")
			public ModelAndView weekReportList(HttpServletRequest request) {
				String rtPage = "/member/membership/memberWeeklyReport";
				Map<String, Object> resMap = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String dateEnd = df.format(new Date());
				resMap.put("date_end", dateEnd);
				resMap.put("week", "5");
				
				return new ModelAndView(rtPage,resMap);
			}
	        
			//会员数据(周报表)
			@RequestMapping(value = "/membership/memberWeeklyReportdata.shtml")
			@ResponseBody
			public Map<String, Object> memberWeeklyReportdata(HttpServletRequest request) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String dateEnd = request.getParameter("date_end");
					String week = request.getParameter("week");
					HashMap<String,Object> paramMap = new HashMap<String,Object>();
					if(!StringUtil.isEmpty(dateEnd)){
						paramMap.put("dateEnd",dateEnd+" 23:59:59");
					}else{
						dateEnd = df.format(new Date());
						paramMap.put("dateEnd",dateEnd+" 23:59:59");
					}
					Date dateBegin = DateUtil.getDateAfter(df.parse(dateEnd), -Integer.parseInt(week)*7+1);
					paramMap.put("dateBegin",df.format(dateBegin)+" 00:00:00");
					
					List<MemberInfoCustom> memberInfoCustoms = memberInfoService.mmemberWeeklyReportCount(paramMap);
					HashMap<String, MemberInfoCustom> map = new HashMap<String, MemberInfoCustom>();
					List<String> containDays = new ArrayList<String>();
					for(MemberInfoCustom memberInfoCustom:memberInfoCustoms){
						containDays.add(memberInfoCustom.getWeeklyDay());
						map.put(memberInfoCustom.getWeeklyDay(), memberInfoCustom);
					}
					List<String> betweenDays = this.getBetweenDays(df.format(dateBegin), dateEnd);
					for(int i= 0;i<betweenDays.size();i++){
						if(!containDays.contains(betweenDays.get(i))){
							MemberInfoCustom memberInfoCustom=new MemberInfoCustom();
							memberInfoCustom.setWeeklyDay(betweenDays.get(i));
							memberInfoCustom.setMemberWeeklyAdded(0);
							memberInfoCustom.setMemberWeeklyNewlyaddedConsumption(0);
							memberInfoCustom.setMemberWeeklyActive(0);
							memberInfoCustom.setMemberWeeklyConsumption(0);
							memberInfoCustom.setMemberWeeklySvipcount(0);
							memberInfoCustoms.add(memberInfoCustom);
							map.put(betweenDays.get(i), memberInfoCustom);
						}
					}
					Collections.sort(memberInfoCustoms, new Comparator<MemberInfoCustom>() {
			            @Override
			            public int compare(MemberInfoCustom c1, MemberInfoCustom c2) {
			                //升序
			                return c1.getWeeklyDay().compareTo(c2.getWeeklyDay());
			            }
			        });
					List<Week> weeks = WeekHelper.getWeekSplit(dateBegin, df.parse(dateEnd));
					List<MemberInfoCustom> memberInfoCustoms2 = new ArrayList<MemberInfoCustom>();
					for(int i=weeks.size()-1;i>=0;i--){
						MemberInfoCustom odc = new MemberInfoCustom();
						odc.setWeeklyDay(weeks.get(i).getWeekBegin()+"~"+weeks.get(i).getWeekEnd());
						int memberweeklyAddedCount = 0;
						int memberweeklyNewlyaddedConsumptionCount = 0;
						int memberweeklyActiveCount = 0;
						int memberweeklyConsumptionCount = 0;
						int memberweeklySvipcountCount = 0;
						
						for(MemberInfoCustom memberInfoCustom:memberInfoCustoms){
							if(df.parse(memberInfoCustom.getWeeklyDay()).getTime()>=df.parse(weeks.get(i).getWeekBegin()).getTime() && df.parse(memberInfoCustom.getWeeklyDay()).getTime()<=df.parse(weeks.get(i).getWeekEnd()).getTime()){
								memberweeklyAddedCount += memberInfoCustom.getMemberWeeklyAdded();
								memberweeklyNewlyaddedConsumptionCount += memberInfoCustom.getMemberWeeklyNewlyaddedConsumption();
								memberweeklyActiveCount += memberInfoCustom.getMemberWeeklyActive();
								memberweeklyConsumptionCount+=memberInfoCustom.getMemberWeeklyConsumption();
								memberweeklySvipcountCount +=memberInfoCustom.getMemberWeeklySvipcount();
							}
							
						}
						odc.setMemberWeeklyAdded(memberweeklyAddedCount);
						odc.setMemberWeeklyNewlyaddedConsumption(memberweeklyNewlyaddedConsumptionCount);
						odc.setMemberWeeklyActive(memberweeklyActiveCount);
						odc.setMemberWeeklyConsumption(memberweeklyConsumptionCount);
						odc.setMemberWeeklySvipcount(memberweeklySvipcountCount);
						
						memberInfoCustoms2.add(odc);
					}
					resMap.put("Rows", memberInfoCustoms2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return resMap;
			}
			
	
			//查看会员名词解释
			@RequestMapping(value = "/membership/memberExplain.shtml")
			public ModelAndView memberExplain(HttpServletRequest request) {
				String rtPage = "/member/membership/memberExplain";
				Map<String, Object> resMap = new HashMap<String, Object>();
				return new ModelAndView(rtPage,resMap);
			}

	//会员登录日志列表

	@RequestMapping(value = "/member/info/logList.shtml")
	public ModelAndView getLogList(HttpServletRequest request) {
		String rtPage = "/member/info/logList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(request.getParameter("memberId"))) {
			Integer memberId = Integer.valueOf(request.getParameter("memberId").trim());
			resMap.put("memberId",memberId);
		}

		return new ModelAndView(rtPage, resMap);
	}

	//会员登录日志详情

	@RequestMapping(value = "/member/loginLogInfo.shtml")
	@ResponseBody
	public Map<String, Object> loginLogInfo(HttpServletRequest request,Page page) {

		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		try {
			SysAppLoginLogExample sysAppLoginLogExample = new SysAppLoginLogExample();


			if (!StringUtil.isEmpty(request.getParameter("memberId"))) {
				Integer memberId = Integer.valueOf(request.getParameter("memberId").trim());
				sysAppLoginLogExample.createCriteria().andMemberIdEqualTo(memberId);
			}
			sysAppLoginLogExample.createCriteria().andDelFlagEqualTo("0");

			sysAppLoginLogExample.setLimitStart(page.getLimitStart());
			sysAppLoginLogExample.setLimitSize(page.getLimitSize());

			List<SysAppLoginLog> sysAppLoginLogs = sysLoginLogService.selectByExample(sysAppLoginLogExample);

			totalCount = sysLoginLogService.countByExample(sysAppLoginLogExample);

			resMap.put("Rows", sysAppLoginLogs);
			resMap.put("Total", totalCount);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resMap;
	}

	/**
	 *会员短信黑名单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/member/sms/smsBlackMobile.shtml")
	public ModelAndView smsBlackMobile(HttpServletRequest request) {
		String rtPage = "/member/sms/smsBlackMobile";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 *会员短信黑名单列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/member/sms/getSmsBlackMobile.shtml")
	@ResponseBody
	public Map<String, Object> getSmsBlackMobile(HttpServletRequest request,Page page) {

		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		try {
			HashMap<String, Object> paraMap = new HashMap<>();
			if(!StringUtils.isEmpty(request.getParameter("memberMobile"))){
				paraMap.put("mobile",request.getParameter("memberMobile"));
			}

			paraMap.put("limitStart",page.getLimitStart());
			paraMap.put("limitSize",page.getLimitSize());


			List<HashMap<String,Object>> smsMap = memberInfoService.selectSmsBlackMobile(paraMap);
			totalCount = memberInfoService.countSmsBlackMobile(paraMap);

			resMap.put("Rows", smsMap);
			resMap.put("Total", totalCount);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resMap;
	}

	/**
	 *删除会员短信黑名单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/member/delSmsBlackMobile.shtml")
	@ResponseBody
	public Map<String, Object> delSmsBlackMobile(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id) ) {
				Date date = new Date();
				String staffID = this.getSessionStaffBean(request).getStaffID();
				HashMap<String, Object> paraMap = new HashMap<>();
				paraMap.put("updateBy",staffID);
				paraMap.put("updateDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
				paraMap.put("id",id);
				memberInfoService.delSmsBlackMobil(paraMap);
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	
	     //在校大学生认证记录
		@RequestMapping(value ="/member/memberCollegeStudentCertification.shtml")
		public ModelAndView memberCollegeStudentCertification(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
			String rtPage = "/member/memberCollegeStudent/memberCollegeStudentCertificationList";
			Map<String,Object> resMap = new HashMap<String,Object>();
			return new ModelAndView(rtPage, resMap);
		}
		
		
		//在校大学生认证记录列表
		@RequestMapping(value = "/member/memberCollegeStudentCertificationdata.shtml")
		@ResponseBody
		public Map<String, Object> memberCollegeStudentCertificationdata(HttpServletRequest request,Page page) {	
			Map<String,Object> resMap = new HashMap<String,Object>(); 
			Integer totalCount =0;

			try {
				  MemberCollegeStudentCertificationCustomExample memberCollegeStudentCertificationCustomExample=new MemberCollegeStudentCertificationCustomExample();
				  MemberCollegeStudentCertificationCustomExample.MemberCollegeStudentCertificationCustomCriteria memberCollegeStudentCertificationCustomCriteria=memberCollegeStudentCertificationCustomExample.createCriteria();
				  memberCollegeStudentCertificationCustomCriteria.andDelFlagEqualTo("0");
				  memberCollegeStudentCertificationCustomExample.setOrderByClause("mcs.commit_date desc");
				  if (!StringUtil.isEmpty(request.getParameter("status"))) {
					  memberCollegeStudentCertificationCustomCriteria.andStatusEqualTo(request.getParameter("status"));
				  }
				  if (!StringUtil.isEmpty(request.getParameter("memberId"))) {
					  memberCollegeStudentCertificationCustomCriteria.andMemberIdEqualTo(Integer.valueOf(request.getParameter("memberId")));
				  }
				  if (!StringUtil.isEmpty(request.getParameter("memberNick"))) {
					  memberCollegeStudentCertificationCustomCriteria.andMemberNickLike(request.getParameter("memberNick"));
				  }
				  if (!StringUtil.isEmpty(request.getParameter("memberMobile"))) {
					  memberCollegeStudentCertificationCustomCriteria.andMemberMobileEqualTo(request.getParameter("memberMobile"));
				  }
				  totalCount = memberCollegeStudentCertificationService.memberCollegeStudentCertificationCustomCountByExample(memberCollegeStudentCertificationCustomExample);
				  memberCollegeStudentCertificationCustomExample.setLimitStart(page.getLimitStart());
				  memberCollegeStudentCertificationCustomExample.setLimitSize(page.getLimitSize());
				  List<MemberCollegeStudentCertificationCustom> memberCollegeStudentCertificationCustoms=memberCollegeStudentCertificationService.memberCollegeStudentCertificationCustomSelectByExample(memberCollegeStudentCertificationCustomExample);
				  
				resMap.put("Rows", memberCollegeStudentCertificationCustoms);
				resMap.put("Total", totalCount);		
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}	
}
