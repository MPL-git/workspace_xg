package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.jf.entity.BlackList;
import com.jf.entity.BlackListCustom;
import com.jf.entity.BlackListCustomExample;
import com.jf.service.BlackListService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class BlackListController extends BaseController {

	@Autowired
	private BlackListService blackListService;
	
	/**
	 * 
	 * @Title blackListManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月11日 下午4:52:40
	 */
	@RequestMapping("/blackList/blackListManager.shtml")
	public ModelAndView blackListManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/blackList/getBlackListList");
		m.addObject("blackTypeList", DataDicUtil.getTableStatus("BU_BLACK_LIST", "BLACK_TYPE"));
		return m;
	}
	
	/**
	 * 
	 * @Title getBlackListList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月11日 下午5:09:58
	 */
	@ResponseBody
	@RequestMapping("/blackList/getBlackListList.shtml")
	public Map<String, Object> getBlackListList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<BlackListCustom> dataList = null;
		Integer totalCount = 0;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			BlackListCustomExample blackListCustomExample = new BlackListCustomExample();
			BlackListCustomExample.BlackListCustomCriteria blackListCustomCriteria = blackListCustomExample.createCriteria();
			blackListCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("memberNick"))) {
				blackListCustomCriteria.andMemberNickLike("%"+request.getParameter("memberNick")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("memberMobile"))) {
				blackListCustomCriteria.andMemberMobileLike("%"+request.getParameter("memberMobile")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("beginBlackToDate"))) {
				blackListCustomCriteria.andBlackToDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginBlackToDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endBlackToDate"))) {
				blackListCustomCriteria.andBlackToDateLessThanOrEqualTo(sdf.parse(request.getParameter("endBlackToDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("blackType"))) {
				blackListCustomCriteria.andBlackTypeEqualTo(request.getParameter("blackType"));
			}
			blackListCustomCriteria.andBlackToDateGreaterThanOrEqualTo(sdf.parse(sdf.format(date)));
			blackListCustomExample.setOrderByClause(" t.id desc");
			blackListCustomExample.setLimitStart(page.getLimitStart());
			blackListCustomExample.setLimitSize(page.getLimitSize());
			totalCount = blackListService.countByCustomExample(blackListCustomExample);
			dataList = blackListService.selectByCustomExample(blackListCustomExample);
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
	 * @Title updateBlackList   
	 * @Description TODO(解冻)   
	 * @author pengl
	 * @date 2018年6月11日 下午5:36:54
	 */
	@ResponseBody
	@RequestMapping("/blackList/updateBlackList.shtml")
	public Map<String, Object> updateBlackList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String blackListId = request.getParameter("blackListId");
			if(!StringUtil.isEmpty(blackListId)) {
				Date date = new Date();
				String staffID = this.getSessionStaffBean(request).getStaffID();
				String[] blackListIds = blackListId.split(",");
				List<BlackList> blackListList = new ArrayList<BlackList>();
				for(int i=0;i<blackListIds.length;i++) {
					BlackList blackList = new BlackList();
					blackList.setId(Integer.parseInt(blackListIds[i]));
					blackList.setDelFlag("1");
					blackList.setUpdateBy(Integer.parseInt(staffID));
					blackList.setUpdateDate(date);
					blackListList.add(blackList);
				}
				blackListService.updateBlackListIdListSelective(blackListList);
			}else {
				code = "9999";
				msg = "ID为空！";
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
	
	
	
}
