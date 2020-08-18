package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.*;
import com.jf.entity.WithdrawOrderCustomExample.WithdrawOrderCustomCriteria;
import com.jf.service.MemberAccountDtlService;
import com.jf.service.SysStaffRoleService;
import com.jf.service.WithdrawOrderPicService;
import com.jf.service.WithdrawOrderService;
import com.jf.vo.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
@Controller
public class NewStartController extends BaseController {

	@Autowired
	private WithdrawOrderService withdrawOrderService;

	@Autowired
	private MemberAccountDtlService memberAccountDtlService;
	@Autowired
	private WithdrawOrderPicService withdrawOrderPicService;
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	@RequestMapping(value = "/newStart/index.shtml")
	public ModelAndView index(HttpServletRequest request,Model model) {
		ModelAndView m = new ModelAndView();
		String pageType = request.getParameter("page");
		//获取当前角色
		String staffId  = this.getSessionStaffBean(request).getStaffID();
		SysStaffRoleExample staffRoleExample = new SysStaffRoleExample();
		com.jf.entity.SysStaffRoleExample.Criteria criteria = staffRoleExample.createCriteria();
		criteria.andStaffIdEqualTo(Integer.valueOf(staffId)).andRoleIdEqualTo(117).andStatusEqualTo("A");
		List<SysStaffRole> yyList = sysStaffRoleService.selectByExample(staffRoleExample);
		SysStaffRoleExample staffRoleExample1 = new SysStaffRoleExample();
		com.jf.entity.SysStaffRoleExample.Criteria criteria1 = staffRoleExample1.createCriteria();
		criteria1.andStaffIdEqualTo(Integer.valueOf(staffId)).andRoleIdEqualTo(118).andStatusEqualTo("A");
		List<SysStaffRole> cwList = sysStaffRoleService.selectByExample(staffRoleExample1);
		String yyStatus = null;
		String cwStatus = null;
		if(yyList.isEmpty()){
			yyStatus = "1";
		}else{
			yyStatus = "0";
		}
		if(cwList.isEmpty()){
			cwStatus = "1";
		}else{
			cwStatus = "0";
		}
			model.addAttribute("yyStatus", yyStatus);
			model.addAttribute("cwStatus", cwStatus);
			if(StringUtils.equals(pageType, "0")){
				m.setViewName("/novaStrategy/newStartList");
			}else if(StringUtils.equals(pageType, "1")){
				m.setViewName("/novaStrategy/cashWithdrawalList");
		}	
		return m;
	}
	
	//提现财务列表导出
	@RequestMapping("/newStart/export.shtml")
	public void exportCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			WithdrawOrderCustomExample withdrawOrderCustomExample = new WithdrawOrderCustomExample();
			WithdrawOrderCustomCriteria criteria = withdrawOrderCustomExample
					.createCriteria();
			criteria.andDelFlagEqualTo("0").andWithdrawTypeEqualTo("3");
			withdrawOrderCustomExample.setOrderByClause("create_date asc");
			// 会员ID
			if (StringUtils.isNotBlank(request.getParameter("memberId"))) {
				criteria.andMemberIdEqualTo(Integer.valueOf(request
						.getParameter("memberId")));
			}
			// 会员昵称
			if (StringUtils.isNotBlank(request.getParameter("nick"))) {
				criteria.andNickLikeEqualToNewStart("%"
						+ request.getParameter("nick") + "%");
			}
			// 审核状态	
			if(StringUtils.isNotBlank(request.getParameter("status"))){
				criteria.andStatusEqualToNewStart(request.getParameter("status"));
			}
			// 申请日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {			
				if (StringUtils.isNotBlank(request.getParameter("createDateBegin"))) {
					criteria.andCreateDateGreaterThanOrEqualTo(df.parse(request
							.getParameter("createDateBegin") + " 00:00:00"));
				}
				if (StringUtils.isNotBlank(request.getParameter("createDateEnd"))) {
					criteria.andCreateDateLessThanOrEqualTo(df.parse(request
							.getParameter("createDateEnd") + " 23:59:59"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			List<WithdrawOrderCustom> list = withdrawOrderService
					.selectByCustomExampleNewStart(withdrawOrderCustomExample);
			
			String[] titles = { "会员ID", "会员昵称", "提现前余额","提现金额","账户名","银行卡号","开户行","省份/城市","申请时间","审核状态"};
			ExcelBean excelBean = new ExcelBean("提现财务信息汇总列表.xls",
					"现财务信息总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(WithdrawOrderCustom withdrawOrderCustom:list){
				String[] data = {
						String.valueOf(withdrawOrderCustom.getMemberId()),
						withdrawOrderCustom.getNick(),
						String.valueOf(withdrawOrderCustom.getNovaBalance()),
						String.valueOf(withdrawOrderCustom.getAmount()),
						withdrawOrderCustom.getRealName(),
						withdrawOrderCustom.getAlipayAccount()==null?"":"`"+withdrawOrderCustom.getAlipayAccount(),
						withdrawOrderCustom.getBranchName(),
						(withdrawOrderCustom.getProvinceAreaName()==null?"":withdrawOrderCustom.getProvinceAreaName())+(withdrawOrderCustom.getCityAreaName()==null?"":withdrawOrderCustom.getCityAreaName()),
						df.format(withdrawOrderCustom.getCreateDate()),
						withdrawOrderCustom.getStatus()
			};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取新星余额提现审核列表
	@ResponseBody
	@RequestMapping(value = "/newStart/getNewStartList.shtml")
	public Map<String, Object> getNewStartList(HttpServletRequest request,
			Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		WithdrawOrderCustomExample withdrawOrderCustomExample = new WithdrawOrderCustomExample();
		WithdrawOrderCustomCriteria criteria = withdrawOrderCustomExample
				.createCriteria();
		withdrawOrderCustomExample.setLimitSize(page.getLimitSize());
		withdrawOrderCustomExample.setLimitStart(page.getLimitStart());
		criteria.andDelFlagEqualTo("0").andWithdrawTypeEqualTo("3");
		withdrawOrderCustomExample.setOrderByClause("create_date asc");
		// 会员ID
		if (StringUtils.isNotBlank(request.getParameter("memberId"))) {
			criteria.andMemberIdEqualTo(Integer.valueOf(request
					.getParameter("memberId")));
		}
		// 会员昵称
		if (StringUtils.isNotBlank(request.getParameter("nick"))) {
			criteria.andNickLikeEqualToNewStart("%"
					+ request.getParameter("nick") + "%");
		}
		// 审核状态	
		if(StringUtils.isNotBlank(request.getParameter("status"))){
			criteria.andStatusEqualToNewStart(request.getParameter("status"));
		}
		// 申请日期
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (StringUtils.isNotBlank(request.getParameter("createDateBegin"))) {
				criteria.andCreateDateGreaterThanOrEqualTo(df.parse(request
						.getParameter("createDateBegin") + " 00:00:00"));
			}
			if (StringUtils.isNotBlank(request.getParameter("createDateEnd"))) {
				criteria.andCreateDateLessThanOrEqualTo(df.parse(request
						.getParameter("createDateEnd") + " 23:59:59"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Integer totalCount = withdrawOrderService
				.countByExample(withdrawOrderCustomExample);
		List<WithdrawOrderCustom> list = withdrawOrderService
				.selectByCustomExampleNewStart(withdrawOrderCustomExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}

	// 审核页面
	@RequestMapping("/newStart/audit.shtml")
	public ModelAndView audit(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String rtPage = "/novaStrategy/audit";
		String withdrawOrders = request.getParameter("ids");
		List<String> total = Arrays.asList(withdrawOrders.split(","));
		// 1审核 2重审
		String type = request.getParameter("type");
		// 1营运 2财务
		String yy = request.getParameter("yy");
		//当是重审的时候，获取初始化数据
		if(StringUtils.equals(type, "2")){
			WithdrawOrder withdrawOrder = withdrawOrderService.selectByPrimaryKey(Integer.valueOf(withdrawOrders));
			WithdrawOrderPicExample withdrawOrderPicExample = new WithdrawOrderPicExample();
			WithdrawOrderPicExample.Criteria criteria = withdrawOrderPicExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andWithdrawOrderIdEqualTo(Integer.valueOf(withdrawOrders));
			List<WithdrawOrderPic> list = withdrawOrderPicService.selectByExample(withdrawOrderPicExample);
			List<Map<String, Object>> withdrawOrderPicList=new ArrayList<Map<String, Object>>();
			for(WithdrawOrderPic withdrawOrderPic:list){
				Map<String, Object> pic=new HashMap<String, Object>();
				pic.put("PICTURE_PATH", withdrawOrderPic.getPic());
				withdrawOrderPicList.add(pic);
			}
			map.put("withdrawOrderPicList", withdrawOrderPicList);
			map.put("picLength", withdrawOrderPicList.size());
			map.put("withdrawOrder", withdrawOrder);
			map.put("list", list);
		}
		if(!map.containsKey("picLength")){
			map.put("picLength", 0);
		}		
		map.put("ids", withdrawOrders);
		map.put("yy", yy);
		map.put("total", total.size());
		map.put("type", type);
		return new ModelAndView(rtPage, map);
	}
	
	// 审核提交
	@ResponseBody
	@RequestMapping("/newStart/auditSubmit.shtml")
	public Map<String, Object> auditSubmit(HttpServletRequest request) {
		String staffId = this.getSessionStaffBean(request).getStaffID();
		// 审核、重审操作
		Map<String, Object> map = withdrawOrderService.operate(request,staffId);
		return map;
	}
	
	//新星余额每日汇总
	@RequestMapping(value = "/newStart/dailySummary.shtml")
	public ModelAndView dailySummary(HttpServletRequest request) {
		String rtPage = "/novaStrategy/dailySummary";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createDateBegin = "";
		String createDateEnd = "";
		Date now = new Date();
		if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
			createDateBegin = request.getParameter("create_date_begin");
		}else{
			createDateBegin = dateFormat.format(now).substring(0,7)+"-01";
		}
		if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
			createDateEnd = request.getParameter("create_date_end");
		}else{
			createDateEnd = dateFormat.format(now);
		}
		resMap.put("createDateBegin", createDateBegin);
		resMap.put("createDateEnd", createDateEnd);
		return new ModelAndView(rtPage,resMap);
	}
	
	//新星余额每日汇总列表
	@ResponseBody
	@RequestMapping(value="/novaStrategy/dailySummaryList.shtml")
	public Map<String,Object> dailySummaryList(HttpServletRequest request,Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createDateBegin = "";
			String createDateEnd = "";
			Date now = new Date();
			if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ){
				createDateBegin = request.getParameter("createDateBegin")+" 00:00:00";
			}else{
				createDateBegin = dateFormat.format(now).substring(0,7)+"-01 00:00:00";
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ){
				createDateEnd = request.getParameter("createDateEnd")+" 23:59:59";
			}else{
				createDateEnd = dateFormat.format(now);
			}
			paramMap.put("createDateBegin", createDateBegin);
			paramMap.put("createDateEnd", createDateEnd);
			List<MemberAccountDtlCustom> memberAccountDtlCustoms = memberAccountDtlService.selectMemberAccountDtlCountEachDayList(paramMap);
			HashMap<String, MemberAccountDtlCustom> map = new HashMap<String, MemberAccountDtlCustom>();
			List<String> containDays = new ArrayList<String>();
			for(MemberAccountDtlCustom mAccountDtlCustom:memberAccountDtlCustoms){
				containDays.add(mAccountDtlCustom.getEachDay());
				map.put(mAccountDtlCustom.getEachDay(), mAccountDtlCustom);
			}
			createDateBegin = createDateBegin.substring(0, 10);
			createDateEnd = createDateEnd.substring(0, 10);
			List<String> betweenDays = this.getBetweenDays(createDateBegin, createDateEnd);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					MemberAccountDtlCustom memberAccountDtlCustom = new MemberAccountDtlCustom();
					memberAccountDtlCustom.setEachDay(betweenDays.get(i));
					memberAccountDtlCustom.setOrderDistribution(new BigDecimal(0));
					memberAccountDtlCustom.setInviteNewAwards(new BigDecimal(0));
					memberAccountDtlCustom.setCashWithdrawals(new BigDecimal(0));
					if(i == 0){
						memberAccountDtlCustom.setFirst1(new BigDecimal(0));
						memberAccountDtlCustom.setEnd1(new BigDecimal(0));
					}else{
						MemberAccountDtlCustom prevMchtDepositDtlCustom = map.get(betweenDays.get(i-1));
						memberAccountDtlCustom.setFirst1(prevMchtDepositDtlCustom.getEnd1());
						memberAccountDtlCustom.setEnd1(prevMchtDepositDtlCustom.getEnd1());
					}
					memberAccountDtlCustoms.add(memberAccountDtlCustom);
					map.put(betweenDays.get(i), memberAccountDtlCustom);
				}
			}
			Collections.sort(memberAccountDtlCustoms, new Comparator<MemberAccountDtlCustom>() {
	            @Override
	            public int compare(MemberAccountDtlCustom m1, MemberAccountDtlCustom m2) {
	                //升序
	                return m1.getEachDay().compareTo(m2.getEachDay());
	            }
	        });
			for (int i = 0;i<memberAccountDtlCustoms.size();i++) {
				if(i+1<memberAccountDtlCustoms.size()){
					memberAccountDtlCustoms.get(i+1).setFirst1(memberAccountDtlCustoms.get(i).getEnd1());;
				}			
			}
			resMap.put("Rows", memberAccountDtlCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	protected List<String> getBetweenDays(String stime,String etime){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=null;
        Date eDate=null;
        try {
             sdate=df.parse(stime);
             eDate=df.parse(etime);
        } catch (Exception e) {
              e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        List<String> list=new ArrayList<String>();
        while (sdate.getTime()<=eDate.getTime()) {
              list.add(df.format(sdate));
              c.setTime(sdate);
              c.add(Calendar.DATE, 1); // 日期加1天
              sdate = c.getTime();
              }
        return list;
  }
}
