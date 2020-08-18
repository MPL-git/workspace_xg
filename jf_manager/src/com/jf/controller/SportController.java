package com.jf.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.Sport;
import com.jf.entity.SportCustom;
import com.jf.entity.SportCustomExample;
import com.jf.entity.SportLog;
import com.jf.entity.SportLogCustom;
import com.jf.entity.SportLogCustomExample;
import com.jf.entity.SportTeam;
import com.jf.entity.SportTeamCustomExample;
import com.jf.entity.SportTeamExample;
import com.jf.entity.SportTeamLogCustom;
import com.jf.entity.SportTeamLogCustomExample;
import com.jf.entity.StateCode;
import com.jf.service.SportLogService;
import com.jf.service.SportService;
import com.jf.service.SportTeamService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SportController extends BaseController {

	@Autowired
	private SportService sportService;
	
	@Autowired
	private SportLogService sportLogService;
	
	@Autowired
	private SportTeamService sportTeamService;
	
	/**
	 * 
	 * @Title sportManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午2:51:29
	 */
	@RequestMapping("/sport/sportManager.shtml")
	public ModelAndView sportManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sport/getSportList");
		m.addObject("resultList", DataDicUtil.getTableStatus("BU_SPORT", "RESULT"));
		return m;
	}
	
	/**
	 * 
	 * @Title getSportList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午2:59:43
	 */
	@ResponseBody
	@RequestMapping("/sport/getSportList.shtml")
	public Map<String, Object> getSportList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SportCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SportCustomExample sportCustomExample = new SportCustomExample();
			SportCustomExample.SportCustomCriteria sportCustomCriteria = sportCustomExample.createCriteria();
			sportCustomCriteria.andDelFlagEqualTo("0").andSportTypeEqualTo(1);
			if(!StringUtil.isEmpty(request.getParameter("sportName"))) {
				sportCustomCriteria.andSportNameLike("%"+request.getParameter("sportName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("sportResult"))) {
				sportCustomCriteria.andResultEqualTo(request.getParameter("sportResult"));
			}
			if(!StringUtil.isEmpty(request.getParameter("startBeginTime"))) {
				sportCustomCriteria.andBeginTimeGreaterThanOrEqualTo(sdf.parse(request.getParameter("startBeginTime")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endBeginTime"))) {
				sportCustomCriteria.andBeginTimeLessThanOrEqualTo(sdf.parse(request.getParameter("endBeginTime")));
			}
			if(!StringUtil.isEmpty(request.getParameter("sportTeamName"))) {
				sportCustomCriteria.andSportTeamNameLike("%"+request.getParameter("sportTeamName")+"%");
			}
			sportCustomExample.setOrderByClause(" t.result asc ,t.begin_time asc");
			sportCustomExample.setLimitStart(page.getLimitStart());
			sportCustomExample.setLimitSize(page.getLimitSize());
			totalCount = sportService.countByCustomExample(sportCustomExample);
			dataList = sportService.selectByCustomExample(sportCustomExample);
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
	 * @Title sportLogManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午4:06:21
	 */
	@RequestMapping("/sport/sportLogManager.shtml")
	public ModelAndView sportLogManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sport/getSportLogList");
		String sportId = request.getParameter("sportId");
		m.addObject("sportId", sportId);
		return m;
	} 
	
	/**
	 * 
	 * @Title getsportLogList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午4:06:24
	 */
	@ResponseBody
	@RequestMapping("/sport/getsportLogList.shtml")
	public Map<String, Object> getsportLogList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SportLogCustom> dataList = null;
		Integer totalCount = 0;
		try {
			String sportId = request.getParameter("sportId");
			if(!StringUtil.isEmpty(sportId)) {
				SportLogCustomExample sportLogCustomExample = new SportLogCustomExample();
				SportLogCustomExample.SportLogCustomCriteria sportLogCustomCriteria = sportLogCustomExample.createCriteria();
				sportLogCustomCriteria.andDelFlagEqualTo("0").andSportIdEqualTo(Integer.parseInt(sportId));
				sportLogCustomExample.setOrderByClause(" t.id desc");
				sportLogCustomExample.setLimitStart(page.getLimitStart());
				sportLogCustomExample.setLimitSize(page.getLimitSize());
				totalCount = sportLogService.countByCustomExample(sportLogCustomExample);
				dataList = sportLogService.selectByCustomExample(sportLogCustomExample);
			}
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
	 * @Title addOrUpdateSportManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午4:26:17
	 */
	@RequestMapping("/sport/addOrUpdateSportManager.shtml")
	public ModelAndView addOrUpdateSportManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sport/addOrUpdateSport");
		String sportId = request.getParameter("sportId");
		if(!StringUtil.isEmpty(sportId)) {
			Sport sport = sportService.selectByCustomPrimaryKey(Integer.parseInt(sportId));
			m.addObject("sport", sport);
		}
		SportTeamExample sportTeamExample = new SportTeamCustomExample();
		SportTeamExample.Criteria sportTeamCriteria = sportTeamExample.createCriteria();
		sportTeamCriteria.andDelFlagEqualTo("0").andResultEqualTo("0");
		List<SportTeam> sportTeamList = sportTeamService.selectByExample(sportTeamExample);
		m.addObject("sportTeamList", sportTeamList);
		return m;
	} 
	
	/**
	 * 
	 * @Title addOrUpdateSport   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午4:26:23
	 */
	@RequestMapping("/sport/addOrUpdateSport.shtml")
	public ModelAndView addOrUpdateSport(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String sportId = request.getParameter("sportId");
			String sportName = request.getParameter("sportName");
			String homeTeam = request.getParameter("homeTeam");
			String awayTeam = request.getParameter("awayTeam");
			String homeRate = request.getParameter("homeRate");
			String awaysRate = request.getParameter("awaysRate");
			String drawRate = request.getParameter("drawRate");
			String beginTime = request.getParameter("beginTime");
			String homeTeamName = request.getParameter("homeTeamName");
			String awayTeamName = request.getParameter("awayTeamName");
			Sport sport = new Sport();
			if(!StringUtil.isEmpty(sportId)) {
				sport.setId(Integer.parseInt(sportId));
			}
			sport.setSportName(sportName);
			if(!StringUtil.isEmpty(homeTeam)) {
				sport.setHomeTeam(Integer.parseInt(homeTeam));
			}
			if(!StringUtil.isEmpty(awayTeam)) {
				sport.setAwayTeam(Integer.parseInt(awayTeam));
			}
			if(!StringUtil.isEmpty(homeRate)) {
				sport.setHomeRate(new BigDecimal(homeRate));
			}
			if(!StringUtil.isEmpty(awaysRate)) {
				sport.setAwaysRate(new BigDecimal(awaysRate));
			}
			if(!StringUtil.isEmpty(drawRate)) {
				sport.setDrawRate(new BigDecimal(drawRate));
			}
			if(!StringUtil.isEmpty(beginTime)) {
				sport.setBeginTime(sdf.parse(beginTime));
			}
			sport.setCreateBy(Integer.parseInt(staffID));
			sport.setCreateDate(date);
			sport.setUpdateBy(Integer.parseInt(staffID));
			sport.setUpdateDate(date);
			sportService.addOrUpdateSport(sport, homeTeamName, awayTeamName);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
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
	 * @Title openAuditStatus   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午7:16:31
	 */
	@RequestMapping("/sport/openAuditStatus.shtml")
	public ModelAndView openAuditStatus(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sport/openAuditStatus");
		String sportId = request.getParameter("sportId");
		if(!StringUtil.isEmpty(sportId)) {
			SportCustom sportCustom = sportService.selectByCustomPrimaryKey(Integer.parseInt(sportId));
			m.addObject("sportCustom", sportCustom);
		}
		return m;
	}  
	
	/**
	 * 
	 * @Title updateAuditStatus   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午9:00:41
	 */
	@RequestMapping("/sport/updateAuditStatus.shtml")
	public ModelAndView updateAuditStatus(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String sportId = request.getParameter("sportId");
			String homeScore = request.getParameter("homeScore");
			String awaysScore = request.getParameter("awaysScore");
			String result = request.getParameter("result");
			Sport sport = new Sport();
			if(!StringUtil.isEmpty(sportId)) {
				sport.setId(Integer.parseInt(sportId));
			}
			if(!StringUtil.isEmpty(homeScore)) {
				sport.setHomeScore(Integer.parseInt(homeScore));
			}
			if(!StringUtil.isEmpty(awaysScore)) {
				sport.setAwaysScore(Integer.parseInt(awaysScore));
			}
			sport.setResult(result);
			sport.setAuditStatus("1"); //1等待审核
			sport.setUpdateBy(Integer.parseInt(staffID));
			sport.setUpdateDate(date);
			sportService.updateAuditStatus(sport);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
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
	 * @Title editAuditStatus   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月23日 下午9:09:12
	 */
	@ResponseBody
	@RequestMapping("/sport/editAuditStatus.shtml")
	public Map<String, Object> editAuditStatus(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String sportId = request.getParameter("sportId");
			String auditStatus = request.getParameter("auditStatus");
			if(!StringUtil.isEmpty(sportId)) {
				Sport sport = new Sport();
				sport.setId(Integer.parseInt(sportId));
				sport.setAuditStatus(auditStatus);
				sport.setUpdateBy(Integer.parseInt(staffID));
				sport.setUpdateDate(date);
				sportService.editAuditStatus(sport);
			}else {
				code = "9999";
				msg = "比赛ID为空！";
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
