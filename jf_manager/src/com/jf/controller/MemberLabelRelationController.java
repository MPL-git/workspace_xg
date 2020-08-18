package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jf.common.utils.StringUtils;
import com.jf.entity.MemberLabelRelationCustom;
import com.jf.entity.MemberLabelRelationCustomExample;
import com.jf.service.MemberLabelRelationService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class MemberLabelRelationController extends BaseController {

	@Autowired
	private MemberLabelRelationService memberLabelRelationService;
	
	/**
	 * 
	 * @MethodName: memberLabelRelationListManager
	 * @Description: (查看会员)
	 * @author Pengl
	 * @date 2019年8月8日 上午10:04:42
	 */
	@RequestMapping("/memberLabelRelation/memberLabelRelationListManager.shtml")
	public ModelAndView memberLabelRelationListManager(HttpServletRequest request, HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/memberLabel/memberLabelRelation/getMemberLabelRelationList");
		m.addObject("labelGroupId", request.getParameter("labelGroupId"));
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/memberLabelRelation/getMemberLabelRelationList.shtml")
	public Map<String, Object> getMemberLabelRelationList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MemberLabelRelationCustom> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(request.getParameter("labelGroupId")) ) {
				paramMap.put("labelGroupId", request.getParameter("labelGroupId"));
			}
			if(!StringUtils.isEmpty(request.getParameter("memeberId")) ) {
				paramMap.put("memeberId", request.getParameter("memeberId"));
			}
			if(!StringUtils.isEmpty(request.getParameter("nick")) ) {
				paramMap.put("nick", request.getParameter("nick"));
			}
			if(!StringUtils.isEmpty(request.getParameter("startCreateDate")) ) {
				paramMap.put("startCreateDate", request.getParameter("startCreateDate")+" 00:00:00" );
			}
			if(!StringUtils.isEmpty(request.getParameter("endCreateDate")) ) {
				paramMap.put("endCreateDate", request.getParameter("endCreateDate")+" 23:59:59");
			}
			paramMap.put("orderByClause", " a.id desc");
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			dataList = memberLabelRelationService.getMemberLabelRelationList(paramMap);
			totalCount = memberLabelRelationService.getMemberLabelRelationCount(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	@ResponseBody
	@RequestMapping("/member/MemberLabelRelationList.shtml")
	public Map<String, Object> MemberLabelRelationList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MemberLabelRelationCustom> dataList = null;
		Integer totalCount = 0;
		try {
			String memberId=request.getParameter("memberId");
			MemberLabelRelationCustomExample memberLabelRelationCustomExample=new MemberLabelRelationCustomExample();
			memberLabelRelationCustomExample.createCriteria().andDelFlagEqualTo("0").andMemeberIdEqualTo(Integer.valueOf(memberId));
			
			dataList=memberLabelRelationService.selectMemberLabelRelationCustomExample(memberLabelRelationCustomExample);
			totalCount=memberLabelRelationService.countMemberLabelRelationCustomExample(memberLabelRelationCustomExample);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	
}
