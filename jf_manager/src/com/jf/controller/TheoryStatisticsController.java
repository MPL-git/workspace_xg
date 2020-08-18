package com.jf.controller;

import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.*;
import com.jf.entity.MemberAccountDtlCustomExample.MemberAccountDtlCustomCriteria;
import com.jf.entity.WithdrawOrderCustomExample.WithdrawOrderCustomCriteria;
import com.jf.service.MemberAccountDtlService;
import com.jf.service.MemberInfoService;
import com.jf.service.WithdrawOrderPicService;
import com.jf.service.WithdrawOrderService;
import com.jf.vo.Page;

import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Controller
public class TheoryStatisticsController extends BaseController{
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private MemberAccountDtlService memberAccountDtlService;
	
	@Autowired
	private WithdrawOrderService withdrawOrderService;
	
	@Autowired
	private WithdrawOrderPicService withdrawOrderPicService;
	@RequestMapping(value="/theoryStatistics/index.shtml")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/novaStrategy"
				+ "/theoryStatisticsList");
		return m;
	}
	
	//获取会员分润统计列表
	@ResponseBody
	@RequestMapping(value="/theoryStatistics/getTheoryStatisticsList.shtml")
	public Map<String, Object> getNovaStrategyList(HttpServletRequest request,Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//会员ID
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			paramsMap.put("id", request.getParameter("id"));
		}
		//会员昵称
		if(StringUtils.isNotBlank(request.getParameter("nick"))){
			paramsMap.put("nick", "%"+request.getParameter("nick")+"%");
		}
		//当前新星计划状态
		if(StringUtils.isNotBlank(request.getParameter("currentStatus"))){
			String status = request.getParameter("currentStatus");
			if(StringUtils.equals(status, "1")){
				//开通中
				paramsMap.put("status",1);
			}else if(StringUtils.equals(status, "2")){
				//已过期
				paramsMap.put("status",2);
			}
		}
		//首次加入新星计划日期
		if(StringUtils.isNotBlank(request.getParameter("firstJoinDateBegin"))){
			paramsMap.put("firstJoinDateBegin", request.getParameter("firstJoinDateBegin")+" 00:00:00");
		}
		if(StringUtils.isNotBlank(request.getParameter("firstJoinDateEnd"))){
			paramsMap.put("firstJoinDateEnd", request.getParameter("firstJoinDateEnd")+" 23:59:59");
		}
		//最后拉新日期
		if(StringUtils.isNotBlank(request.getParameter("laNewDateBegin"))){
			paramsMap.put("laNewDateBegin", request.getParameter("laNewDateBegin")+" 00:00:00");
		}
		if(StringUtils.isNotBlank(request.getParameter("laNewDateEnd"))){
			paramsMap.put("laNewDateEnd", request.getParameter("laNewDateEnd")+" 23:59:59");
		}
		int limitStart = (page.getPage()-1)*page.getLimitSize();
		int limitSize = page.getLimitSize();
		paramsMap.put("limitStart", limitStart);
		paramsMap.put("limitSize", limitSize);
		int totalCount = memberInfoService.countByExampleNova(paramsMap);
		List<MemberInfoCustom> list = memberInfoService.selectMemberInfoCustomByExampleNova(paramsMap);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	//拉新记录页面
	@RequestMapping("/theoryStatistics/laNewRecord.shtml")
	public ModelAndView laNewRecord(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String rtPage = "/novaStrategy/laNewRecord";
		String memberInfoId=request.getParameter("Id");
		map.put("id",memberInfoId);
		return new ModelAndView(rtPage,map);
	}
	
	//拉新记录列表
	@ResponseBody
	@RequestMapping("/theoryStatistics/laNewRecordList.shtml")
	public Map<String, Object> laNewRecordList(HttpServletRequest request,Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		MemberInfoExample memberInfoExample = new MemberInfoExample();
		MemberInfoExample.Criteria criteria = memberInfoExample.createCriteria();
		memberInfoExample.setLimitSize(page.getLimitSize());
		memberInfoExample.setLimitStart(page.getLimitStart());
		criteria.andDelFlagEqualTo("0").andInvitationMemberIdEqualTo(Integer.valueOf(request.getParameter("id")));
		memberInfoExample.setOrderByClause("create_date desc");
		//注册日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(StringUtils.isNotBlank(request.getParameter("createDateBegin"))){
				criteria.andCreateDateGreaterThanOrEqualTo(df.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			if(StringUtils.isNotBlank(request.getParameter("createDateEnd"))){
				criteria.andCreateDateLessThanOrEqualTo(df.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Integer totalCount = memberInfoService.countByExample(memberInfoExample);
		List<MemberInfo> list = memberInfoService.selectByExample(memberInfoExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	//分润记录页面
	@RequestMapping("/theoryStatistics/fenRunRecord.shtml")
	public ModelAndView fenRunrecord(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String rtPage = "/novaStrategy/fenRunRecord";
		String memberInfoId=request.getParameter("Id");
		map.put("id",memberInfoId);
		return new ModelAndView(rtPage,map);
	}
	
	//分润记录列表
	@ResponseBody
	@RequestMapping("/theoryStatistics/fenRunRecordList.shtml")
	public Map<String, Object> fenRunRecordList(HttpServletRequest request,Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		MemberAccountDtlCustomExample memberAccountDtlCustomExample = new MemberAccountDtlCustomExample();
		MemberAccountDtlCustomCriteria criteria = memberAccountDtlCustomExample.createCriteria();
		memberAccountDtlCustomExample.setLimitSize(page.getLimitSize());
		memberAccountDtlCustomExample.setLimitStart(page.getLimitStart());
		memberAccountDtlCustomExample.setOrderByClause("a.create_date desc,a.balance desc");
		//会员ID
		criteria.andProductIdEqualTo(Integer.valueOf(request.getParameter("id")));
		//收支类型
		if(StringUtils.isNotBlank(request.getParameter("tallyModes"))){
			criteria.andTallyModeEqualTo(request.getParameter("tallyModes"));
		}	
		//来源
		if(StringUtils.isNotBlank(request.getParameter("bizTypes"))){
			criteria.andBizTypeEqualTo(request.getParameter("bizTypes"));
		}
		//会员ID
		if(StringUtils.isNotBlank(request.getParameter("memberId"))){
			criteria.andMemberIdEqualTo(request.getParameter("memberId"));
		}
		//会员昵称
		if(StringUtils.isNotBlank(request.getParameter("nick"))){
			criteria.andNickEqualTo(request.getParameter("nick"));
		}
		try {
			//变动日期(开始)
			if(StringUtils.isNotBlank(request.getParameter("createDateOrderBegin"))){
				criteria.andCreateDateGreaterThanOrEqualToFenRun(request.getParameter("createDateOrderBegin")+" 00:00:00");
			}
			//变动日期(结束)
			if(StringUtils.isNotBlank(request.getParameter("createDateOrderEnd"))){
				criteria.andCreateDateLessThanOrEqualToFenRun(request.getParameter("createDateOrderEnd")+" 23:59:59");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Integer totalCount = memberAccountDtlService.countByCustomExampleFenRun(memberAccountDtlCustomExample);
		List<MemberAccountDtlCustom> list = memberAccountDtlService.selectByCustomExampleFenRun(memberAccountDtlCustomExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
		// 查看详情
		@RequestMapping("/theoryStatistics/detail.shtml")
		public ModelAndView detail(HttpServletRequest request) {
			Map<String, Object> map = new HashMap<String, Object>();
			String pageType = request.getParameter("pageType");
			String rtPage = "/novaStrategy/detail";
			String withdrawOrders = request.getParameter("Id");
			//获取会员账户提现表数据
			WithdrawOrderCustomExample withdrawOrderCustomExample = new WithdrawOrderCustomExample();
			WithdrawOrderCustomCriteria criteria = withdrawOrderCustomExample
					.createCriteria();
			criteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(withdrawOrders));
			List<WithdrawOrderCustom> list = withdrawOrderService
					.selectByCustomExampleNewStart(withdrawOrderCustomExample);
			String createDate = null;			
			String yyAuditDate = null;
			String cwAuditDate = null;
			if(!list.isEmpty()){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				createDate = df.format(list.get(0).getCreateDate());
				if(list.get(0).getYyAuditDate()!=null){
					yyAuditDate = df.format(list.get(0).getYyAuditDate());
				}
				if(list.get(0).getCwAuditDate()!=null){
					cwAuditDate = df.format(list.get(0).getCwAuditDate());
				}					
			}
			//获取图片
			WithdrawOrderPicExample withdrawOrderPicExample = new WithdrawOrderPicExample();
			withdrawOrderPicExample.createCriteria().andDelFlagEqualTo("0").andWithdrawOrderIdEqualTo(Integer.valueOf(withdrawOrders));
			List<WithdrawOrderPic> withdrawOrderPics = withdrawOrderPicService.selectByExample(withdrawOrderPicExample);
			map.put("wthdrawOrder", list.get(0));
			map.put("withdrawOrderPics", withdrawOrderPics);
			map.put("createDate", createDate);
			map.put("yyAuditDate", yyAuditDate);
			map.put("cwAuditDate", cwAuditDate);
			map.put("pageType", pageType);
			return new ModelAndView(rtPage, map);
		}
		
		
		//分润记录列表导出
		@RequestMapping("/theoryStatistics/export.shtml")
		public void exportTheoryStatistics(HttpServletRequest request,HttpServletResponse response) throws Exception {
			try {
				MemberAccountDtlCustomExample memberAccountDtlCustomExample = new MemberAccountDtlCustomExample();
				MemberAccountDtlCustomCriteria criteria = memberAccountDtlCustomExample.createCriteria();
				memberAccountDtlCustomExample.setOrderByClause("a.create_date desc,a.balance desc");	
				//商品ID
				criteria.andProductIdEqualTo(Integer.valueOf(request.getParameter("id")));
				//收支类型
				if(StringUtils.isNotBlank(request.getParameter("tallyModes"))){
					criteria.andTallyModeEqualTo(request.getParameter("tallyModes"));
				}	
				//来源
				if(StringUtils.isNotBlank(request.getParameter("bizTypes"))){
					criteria.andBizTypeEqualTo(request.getParameter("bizTypes"));
				}
				//会员ID
				if(StringUtils.isNotBlank(request.getParameter("memberId"))){
					criteria.andMemberIdEqualTo(request.getParameter("memberId"));
				}
				//会员昵称
				if(StringUtils.isNotBlank(request.getParameter("nick"))){
					criteria.andNickEqualTo(request.getParameter("nick"));
				}
				try {
					//变动日期(开始)
					if(StringUtils.isNotBlank(request.getParameter("createDateOrderBegin"))){
						criteria.andCreateDateGreaterThanOrEqualToFenRun(request.getParameter("createDateOrderBegin")+" 00:00:00");
					}
					//变动日期(结束)
					if(StringUtils.isNotBlank(request.getParameter("createDateOrderEnd"))){
						criteria.andCreateDateLessThanOrEqualToFenRun(request.getParameter("createDateOrderEnd")+" 23:59:59");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				List<MemberAccountDtlCustom> list = memberAccountDtlService.selectByCustomExampleFenRun(memberAccountDtlCustomExample);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String[] titles = { "收支类型", "来源/去向", "会员ID","会员昵称","商品ID","子订单号","实付金额","变化金额","当前现金余额(含提现中金额)","变动时间"};
				ExcelBean excelBean = new ExcelBean("分润记录信息汇总列表.xls",
						"分润记录信息总列表", titles);
				List<String[]> datas = new ArrayList<String[]>();
				String getAmount = null;
				for(MemberAccountDtlCustom memberAccountDtlCustom:list){
				     String tallyModes=memberAccountDtlCustom.getTallyModes();
					if (tallyModes.equals("收入")) {
						getAmount="+"+memberAccountDtlCustom.getAmount().toString();
					 }else if (tallyModes.equals("支出")) {
						 getAmount="-"+memberAccountDtlCustom.getAmount().toString();
					}
					String[] data = {
							memberAccountDtlCustom.getTallyModes()==null?"":memberAccountDtlCustom.getTallyModes(),
							memberAccountDtlCustom.getBizTypes()==null?"":memberAccountDtlCustom.getBizTypes(),
							memberAccountDtlCustom.getMemberId()==null?"":String.valueOf(memberAccountDtlCustom.getMemberId()),
							memberAccountDtlCustom.getNick()==null?"":memberAccountDtlCustom.getNick(),
						    memberAccountDtlCustom.getProductId()==null?"":String.valueOf(memberAccountDtlCustom.getProductId()),
							memberAccountDtlCustom.getSubOrderCode()==null?"":memberAccountDtlCustom.getSubOrderCode(),
							memberAccountDtlCustom.getPayAmount()==null?"":String.valueOf(memberAccountDtlCustom.getPayAmount()),
							getAmount==null?"":getAmount,
							memberAccountDtlCustom.getBalance()==null?"":String.valueOf(memberAccountDtlCustom.getBalance()),
						    memberAccountDtlCustom.getCreateDate()==null?"":df.format(memberAccountDtlCustom.getCreateDate()),
				};
					datas.add(data);
				}
				excelBean.setDataList(datas);
				ExcelUtils.export(excelBean,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
