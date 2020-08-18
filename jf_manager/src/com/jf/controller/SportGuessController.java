package com.jf.controller;

import java.text.SimpleDateFormat;
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
import com.jf.dao.SportGuessService;
import com.jf.entity.SportGuessCustom;
import com.jf.entity.SportGuessCustomExample;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SportGuessController extends BaseController {

	@Autowired
	private SportGuessService sportGuessService;
	
	/**
	 * 
	 * @Title sportGuessTypeOneManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月24日 上午10:58:24
	 */
	@RequestMapping("/sportGuess/sportGuessTypeGameManager.shtml")
	public ModelAndView sportGuessTypeGameManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sportGuess/getGuessTypeGameList");
		m.addObject("resultList", DataDicUtil.getTableStatus("BU_SPORT_GUESS", "RESULT"));
		return m;
	}
	
	/**
	 * 
	 * @Title sportGuessTypeWinManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月24日 上午11:01:10
	 */
	@RequestMapping("/sportGuess/sportGuessTypeWinManager.shtml")
	public ModelAndView sportGuessTypeWinManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/sport/sportGuess/getGuessTypeWinList");
		m.addObject("resultList", DataDicUtil.getTableStatus("BU_SPORT_GUESS", "RESULT"));
		return m;
	}
	
	/**
	 * 
	 * @Title getSportGuessList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月24日 上午11:03:01
	 */
	@ResponseBody
	@RequestMapping("/sportGuess/getSportGuessList.shtml")
	public Map<String, Object> getSportGuessList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SportGuessCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SportGuessCustomExample sportGuessCustomExample = new SportGuessCustomExample();
			SportGuessCustomExample.SportGuessCustomCriteria sportGuessCustomCriteria = sportGuessCustomExample.createCriteria();
			sportGuessCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("guessType"))) {
				sportGuessCustomCriteria.andGuessTypeEqualTo(request.getParameter("guessType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("result"))) {
				sportGuessCustomCriteria.andResultEqualTo(request.getParameter("result"));
			}
			if(!StringUtil.isEmpty(request.getParameter("guessCode"))) {
				sportGuessCustomCriteria.andGuessCodeEqualTo(request.getParameter("guessCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("startBeginTime"))) {
				sportGuessCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("startBeginTime")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endBeginTime"))) {
				sportGuessCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endBeginTime")));
			}
			if(!StringUtil.isEmpty(request.getParameter("sportTeamName"))) {
				sportGuessCustomCriteria.andSportTeamNameLike("%"+request.getParameter("sportTeamName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("memberName"))) {
				sportGuessCustomCriteria.andMemberNameLike("%"+request.getParameter("memberName")+"%");
			}
			sportGuessCustomExample.setOrderByClause(" t.id desc");
			sportGuessCustomExample.setLimitStart(page.getLimitStart());
			sportGuessCustomExample.setLimitSize(page.getLimitSize());
			totalCount = sportGuessService.countByCustomExample(sportGuessCustomExample);
			dataList = sportGuessService.selectByCustomExample(sportGuessCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}  
	
}
