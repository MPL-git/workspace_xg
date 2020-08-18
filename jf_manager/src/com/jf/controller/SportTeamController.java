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
import com.jf.entity.SportTeam;
import com.jf.entity.SportTeamCustom;
import com.jf.entity.SportTeamCustomExample;
import com.jf.entity.SportTeamLogCustom;
import com.jf.entity.SportTeamLogCustomExample;
import com.jf.entity.SportType;
import com.jf.entity.StateCode;
import com.jf.service.SportTeamLogService;
import com.jf.service.SportTeamService;
import com.jf.service.SportTypeService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SportTeamController extends BaseController {

	@Autowired
	private SportTeamService sportTeamService;
	
	@Autowired
	private SportTeamLogService sportTeamLogService;
	
	@Autowired
	private SportTypeService sportTypeService;
	
	/**
	 * 
	 * @Title sportTeamManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 上午10:26:45
	 */
	@RequestMapping("/sportTeam/sportTeamManager.shtml")
	public ModelAndView sportTeamManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sportTeam/getSportTeamList");
		m.addObject("resultList", DataDicUtil.getTableStatus("BU_SPORT_TEAM", "RESULT"));
		return m;
	}
	
	/**
	 * 
	 * @Title getSportTeamList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 上午10:42:03
	 */
	@ResponseBody
	@RequestMapping("/sportTeam/getSportTeamList.shtml")
	public Map<String, Object> getSportTeamList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SportTeamCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SportTeamCustomExample sportTeamCustomExample = new SportTeamCustomExample();
			SportTeamCustomExample.SportTeamCustomCriteria sportTeamCustomCriteria = sportTeamCustomExample.createCriteria();
			sportTeamCustomCriteria.andDelFlagEqualTo("0").andSportTypeEqualTo(1);
			if(!StringUtil.isEmpty(request.getParameter("sportTeamName"))) {
				sportTeamCustomCriteria.andNameLike("%"+request.getParameter("sportTeamName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("sportTeamResult"))) {
				sportTeamCustomCriteria.andResultEqualTo(request.getParameter("sportTeamResult"));
			}
			sportTeamCustomExample.setOrderByClause(" t.result asc, t.win_rate desc");
			sportTeamCustomExample.setLimitStart(page.getLimitStart());
			sportTeamCustomExample.setLimitSize(page.getLimitSize());
			totalCount = sportTeamService.countByCustomExample(sportTeamCustomExample);
			dataList = sportTeamService.selectByCustomExample(sportTeamCustomExample);
			
			//判断是否为最后一条参赛中（夺冠）
			SportTeamCustomExample sportTeamCustomE = new SportTeamCustomExample();
			SportTeamCustomExample.SportTeamCustomCriteria sportTeamCustomC = sportTeamCustomE.createCriteria();
			sportTeamCustomC.andDelFlagEqualTo("0").andResultEqualTo("0");
			Integer countSum = sportTeamService.countByCustomExample(sportTeamCustomE);
			for(SportTeamCustom sportTeamCustom : dataList) {
				sportTeamCustom.setSumCount(countSum);
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
	 * @Title updateAuditStatus   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 下午3:02:43
	 */
	@ResponseBody
	@RequestMapping("/sportTeam/updateAuditStatus.shtml")
	public Map<String, Object> updateAuditStatus(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String sportTeamId = request.getParameter("sportTeamId");
			String auditStatus = request.getParameter("auditStatus");
			String content = request.getParameter("content");
			if(!StringUtil.isEmpty(sportTeamId)) {
				Date date = new Date();
				String staffID = this.getSessionStaffBean(request).getStaffID();
				SportTeam sportTeam = new SportTeam();
				sportTeam.setId(Integer.parseInt(sportTeamId));
				sportTeam.setAuditStatus(auditStatus);
				if("2".equals(auditStatus)) {
					sportTeam.setResult("1"); //1 已淘汰
				}else if("4".equals(auditStatus)) {
					sportTeam.setResult("2"); //2 夺冠
				}
				sportTeam.setUpdateBy(Integer.parseInt(staffID));
				sportTeam.setUpdateDate(date);
				sportTeamService.updateAuditStatus(sportTeam, content);
			}else {
				code = "9999";
				msg = "参赛队伍ID为空！";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title addOrUpdateSportTeam   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 下午3:31:20
	 */
	@RequestMapping("/sportTeam/addOrUpdateSportTeamManager.shtml")
	public ModelAndView addOrUpdateSportTeamManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sportTeam/addOrUpdateSportTeam");
		String sportTeamId = request.getParameter("sportTeamId");
		if(!StringUtil.isEmpty(sportTeamId)) {
			SportTeam sportTeam = sportTeamService.selectByCustomPrimaryKey(Integer.parseInt(sportTeamId));
			m.addObject("sportTeam", sportTeam);
		}
		return m;
	} 
	
	/**
	 * 
	 * @Title addOrUpdateSportTeam   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 下午3:36:15
	 */
	@RequestMapping("/sportTeam/addOrUpdateSportTeam.shtml")
	public ModelAndView addOrUpdateSportTeam(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String sportTeamId = request.getParameter("sportTeamId");
			String sportName = request.getParameter("sportName");
			String sportPic = request.getParameter("sportPic");
			String sportWinRate = request.getParameter("sportWinRate");
			SportTeam sportTeam = new SportTeam();
			if(!StringUtil.isEmpty(sportTeamId)) {
				sportTeam.setId(Integer.parseInt(sportTeamId));
			}
			sportTeam.setName(sportName);
			sportTeam.setPic(sportPic);
			if(!StringUtil.isEmpty(sportWinRate)) {
				sportTeam.setWinRate(new BigDecimal(sportWinRate));
			}
			sportTeam.setCreateBy(Integer.parseInt(staffID));
			sportTeam.setCreateDate(date);
			sportTeam.setUpdateBy(Integer.parseInt(staffID));
			sportTeam.setUpdateDate(date);
			sportTeamService.addOrUpdateSportTeam(sportTeam);
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
	 * @Title sportTeamLogManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 下午5:57:47
	 */
	@RequestMapping("/sportTeam/sportTeamLogManager.shtml")
	public ModelAndView sportTeamLogManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sportTeam/getSportTeamLogList");
		String sportTeamId = request.getParameter("sportTeamId");
		m.addObject("sportTeamId", sportTeamId);
		return m;
	} 
	
	/**
	 * 
	 * @Title getsportTeamLogList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 下午6:01:38
	 */
	@ResponseBody
	@RequestMapping("/sportTeam/getsportTeamLogList.shtml")
	public Map<String, Object> getsportTeamLogList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SportTeamLogCustom> dataList = null;
		Integer totalCount = 0;
		try {
			String sportTeamId = request.getParameter("sportTeamId");
			if(!StringUtil.isEmpty(sportTeamId)) {
				SportTeamLogCustomExample sportTeamLogCustomExample = new SportTeamLogCustomExample();
				SportTeamLogCustomExample.SportTeamLogCustomCriteria sportTeamLogCustomCriteria = sportTeamLogCustomExample.createCriteria();
				sportTeamLogCustomCriteria.andDelFlagEqualTo("0").andSportTeamIdEqualTo(Integer.parseInt(sportTeamId));
				sportTeamLogCustomExample.setOrderByClause(" t.id desc");
				sportTeamLogCustomExample.setLimitStart(page.getLimitStart());
				sportTeamLogCustomExample.setLimitSize(page.getLimitSize());
				totalCount = sportTeamLogService.countByCustomExample(sportTeamLogCustomExample);
				dataList = sportTeamLogService.selectByCustomExample(sportTeamLogCustomExample);
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
	 * @Title sportTypeManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 下午6:41:49
	 */
	@RequestMapping("/sportTeam/sportTypeManager.shtml")
	public ModelAndView sportTypeManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sportTeam/addOrUpdateSportType");
		SportType sportType = sportTypeService.selectByPrimaryKey(1);
		m.addObject("sportType", sportType);
		return m;
	}  
	
	/**
	 * 
	 * @Title updateSportType   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月22日 下午6:44:40
	 */
	@RequestMapping("/signInCnf/addOrUpdateSportType.shtml")
	public ModelAndView addOrUpdateSportType(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String sportTypeId = request.getParameter("sportTypeId");
			String finalGuessTime = request.getParameter("finalGuessTime");
			if(!StringUtil.isEmpty(finalGuessTime)) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String staffID = this.getSessionStaffBean(request).getStaffID();
				if(!StringUtil.isEmpty(sportTypeId)) {
					SportType sportType = new SportType();
					sportType.setId(Integer.parseInt(sportTypeId));
					sportType.setFinalGuessTime(sdf.parse(finalGuessTime));
					sportType.setUpdateBy(Integer.parseInt(staffID));
					sportType.setUpdateDate(date);
					sportTypeService.updateByPrimaryKeySelective(sportType);
				}else {
					SportType sportType = new SportType();
					sportType.setName("世界杯");
					sportType.setFinalGuessTime(sdf.parse(finalGuessTime));
					sportType.setCreateBy(Integer.parseInt(staffID));
					sportType.setCreateDate(date);
					sportType.setDelFlag("0");
					sportTypeService.insertSelective(sportType);
				}
			}
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
	
	
	
}
