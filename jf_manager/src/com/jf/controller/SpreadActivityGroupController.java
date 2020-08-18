package com.jf.controller;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jf.common.constant.Const;
import com.jf.entity.*;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.service.SpreadActivityGroupService;
import com.jf.vo.Page;

@Controller
public class SpreadActivityGroupController extends BaseController {
	
	@Resource
	private SpreadActivityGroupService spreadActivityGroupService;
	
	/**
	 * 推广活动组列表页
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "/spread/spreadActivityGroupIndex.shtml")
	public ModelAndView mchtProductTypeCheckIndex(HttpServletRequest request) {
		String rePage = "/spread/spreadActivityGroupIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("channelList", DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL"));
		return new ModelAndView(rePage, resMap);
	}

	@RequestMapping(value = "/spread/spreadActivityGroupDataList.shtml")
	@ResponseBody
	public Map<String, Object> spreadActivityGroupDataList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		SpreadActivityGroupExample.Criteria criteria = spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0");
		if(!StringUtil.isEmpty(request.getParameter("channel"))){
			criteria.andChannelEqualTo(request.getParameter("channel"));
		}
		if(!StringUtil.isEmpty(request.getParameter("groupId"))){
			criteria.andGroupIdEqualTo(request.getParameter("groupId"));
		}
		if(!StringUtil.isEmpty(request.getParameter("activityGroup"))){
			criteria.andActivityGroupLike("%"+request.getParameter("activityGroup")+"%");
		}
		if(!StringUtil.isEmpty(request.getParameter("isEffect"))){
			criteria.andIsEffectEqualTo(request.getParameter("isEffect"));
		}
		criteria.andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
		totalCount = spreadActivityGroupService.countByExample(spreadActivityGroupExample);
		spreadActivityGroupExample.setLimitSize(page.getLimitSize());
		spreadActivityGroupExample.setLimitStart(page.getLimitStart());
		spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
		List<SpreadActivityGroupCustom> spreadActivityGroupCustoms = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		resMap.put("Rows", spreadActivityGroupCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	//添加，修改推广活动组
	@RequestMapping(value = "/spread/editSpreadActivityGroup.shtml")
	public ModelAndView editSpreadActivityGroup(HttpServletRequest request) {	
		String rtPage = "/spread/editSpreadActivityGroup";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if (!StringUtil.isEmpty(request.getParameter("id"))) {
			int id=Integer.valueOf(request.getParameter("id"));
			SpreadActivityGroup spreadActivityGroup=spreadActivityGroupService.selectByPrimaryKey(id);
			resMap.put("spreadActivityGroup", spreadActivityGroup);
		}else{
			resMap.put("spreadActivityGroup", new SpreadActivityGroup());
		}
		
		resMap.put("deviceType", request.getParameter("deviceType"));
		resMap.put("channelList", DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL"));
		return new ModelAndView(rtPage, resMap);
	}
	//批量修改推广活动组
	@RequestMapping(value = "/spread/batchEditSpreadActivityGroup.shtml")
	public ModelAndView batchEditSpreadActivityGroup(HttpServletRequest request) {	
		String rtPage = "/spread/batchEditSpreadActivityGroup";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("deviceType", request.getParameter("deviceType"));
		resMap.put("channelList", DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL"));
		resMap.put("spreadActivityGroupIds", request.getParameter("spreadActivityGroupIds"));
		return new ModelAndView(rtPage, resMap);
	}
	
	
	
	//保存修改或添加
	@ResponseBody
	@RequestMapping(value = "/spread/saveSpreadActivityGroup.shtml")
	public ModelAndView saveSpreadActivityGroup(HttpServletRequest request,SpreadActivityGroup spreadActivityGroup){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			if(spreadActivityGroup.getId() == null){
				spreadActivityGroup.setCreateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				spreadActivityGroup.setCreateDate(new Date());
			}else{
				spreadActivityGroup.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				spreadActivityGroup.setUpdateDate(new Date());
			}
			
			spreadActivityGroupService.saveSpreadActivityGroup(spreadActivityGroup);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = e.getMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	
	//保存修改或添加
	@ResponseBody
	@RequestMapping(value = "/spread/batchEditSpreadActivityGroupSave.shtml")
	public ModelAndView batchEditSpreadActivityGroup(HttpServletRequest request,SpreadActivityGroup spreadActivityGroup){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			if(StringUtil.isEmpty(request.getParameter("spreadActivityGroupIds"))){
				throw new Exception("Id不能为空。");
			}
			if(StringUtil.isEmpty(spreadActivityGroup.getChannel())){
				spreadActivityGroup.setChannel(null);
			}
			if(StringUtil.isEmpty(spreadActivityGroup.getIsEffect())){
				spreadActivityGroup.setIsEffect(null);
			}
			String[] spreadActivityGroupIds=request.getParameter("spreadActivityGroupIds").split(",");
			spreadActivityGroup.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			spreadActivityGroup.setUpdateDate(new Date());
			spreadActivityGroupService.batchEditSpreadActivityGroup(spreadActivityGroupIds, spreadActivityGroup);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = e.getMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title updateSeqNo   
	 * @Description TODO(排序)   
	 * @author pengl
	 * @date 2018年9月12日 下午1:57:30
	 */
	@ResponseBody
	@RequestMapping("/spread/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(id)) {
				SpreadActivityGroup spreadActivityGroup = new SpreadActivityGroup();
				spreadActivityGroup.setId(Integer.parseInt(id));
				spreadActivityGroup.setSeqNo(Integer.parseInt(seqNo));
				spreadActivityGroupService.updateByPrimaryKeySelective(spreadActivityGroup);
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
	
	/**
	 * 
	 * @Title delSpreadActivityGroup   
	 * @Description TODO(删除)   
	 * @author pengl
	 * @date 2018年9月12日 下午2:03:41
	 */
	@ResponseBody
	@RequestMapping("/spread/delSpreadActivityGroup.shtml")
	public Map<String, Object> delSpreadActivityGroup(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String id = request.getParameter("id");
			if(!StringUtil.isEmpty(id)) {
				SpreadActivityGroup spreadActivityGroup = new SpreadActivityGroup();
				spreadActivityGroup.setId(Integer.parseInt(id));
				spreadActivityGroup.setUpdateBy(Integer.parseInt(staffID));
				spreadActivityGroup.setUpdateDate(date);
				spreadActivityGroup.setDelFlag("1");
				spreadActivityGroupService.updateByPrimaryKeySelective(spreadActivityGroup);
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


	@RequestMapping("/spread/updateDiscountRateBatchManager.shtml")
	public ModelAndView updateDiscountRateBatchManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/spread/updateDiscountRateBatch");
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		SpreadActivityGroupExample.Criteria criteria = spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0")
				.andDeviceTypeEqualTo(request.getParameter("deviceType"));
		spreadActivityGroupExample.setOrderByClause(" IFNULL(seq_no, 99999999999) asc, create_date asc");
		List<SpreadActivityGroup> spreadActivityGroupList = spreadActivityGroupService.selectByExample(spreadActivityGroupExample);

		List<JSONObject> spreadActivityGroupJson = new ArrayList();
		JSONObject listBoxJson;
		for(SpreadActivityGroup spreadActivityGroup : spreadActivityGroupList ) {
			listBoxJson = new JSONObject();
			listBoxJson.put("id", spreadActivityGroup.getId());
			listBoxJson.put("text", spreadActivityGroup.getActivityGroup());
			spreadActivityGroupJson.add(listBoxJson);
		}
		m.addObject("spreadActivityGroupJson", spreadActivityGroupJson);
		return m;
	}

	@RequestMapping("/spread/updateDiscountRateBatch.shtml")
	public ModelAndView updateDiscountRateBatch(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("discountRate")) && !StringUtil.isEmpty(request.getParameter("ids")) ) {
				String staffId = this.getSessionStaffBean(request).getStaffID();
				SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
				SpreadActivityGroupExample.Criteria criteria = spreadActivityGroupExample.createCriteria();
				if(!StringUtil.isEmpty(request.getParameter("ids")) ) {
					String[] ids = request.getParameter("ids").split(",");
					List<Integer> idList = new ArrayList<>();
					for(String id : ids ) {
						idList.add(Integer.parseInt(id));
					}
					criteria.andIdIn(idList);
				}
				SpreadActivityGroup spreadActivityGroup = new SpreadActivityGroup();
				spreadActivityGroup.setDiscountRate(new BigDecimal(request.getParameter("discountRate")));
				spreadActivityGroup.setUpdateBy(Integer.parseInt(staffId));
				spreadActivityGroup.setUpdateDate(new Date());
				spreadActivityGroupService.updateByExampleSelective(spreadActivityGroup, spreadActivityGroupExample);
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

	/**
	 * 安卓活动组管理列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/androidSpreadActivityGroup/list.shtml")
	public ModelAndView androidSpreadActivityGroupList(HttpServletRequest request) {
		String rePage = "/spread/androidSpreadActivityGroupList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("channelList", DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL"));
		return new ModelAndView(rePage, resMap);
	}

	/**
	 * 安卓活动组管理列表数据
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/androidSpreadActivityGroup/data.shtml")
	public Map<String, Object> androidSpreadActivityGroupData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		SpreadActivityGroupExample.Criteria criteria = spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0");
		if(!StringUtil.isEmpty(request.getParameter("channel"))){
			criteria.andChannelEqualTo(request.getParameter("channel"));
		}
		if(!StringUtil.isEmpty(request.getParameter("groupId"))){
			criteria.andGroupIdEqualTo(request.getParameter("groupId"));
		}
		if(!StringUtil.isEmpty(request.getParameter("activityGroup"))){
			criteria.andActivityGroupLike("%"+request.getParameter("activityGroup")+"%");
		}
		if(!StringUtil.isEmpty(request.getParameter("isEffect"))){
			criteria.andIsEffectEqualTo(request.getParameter("isEffect"));
		}
		criteria.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
		totalCount = spreadActivityGroupService.countByExample(spreadActivityGroupExample);
		spreadActivityGroupExample.setLimitSize(page.getLimitSize());
		spreadActivityGroupExample.setLimitStart(page.getLimitStart());
		spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
		List<SpreadActivityGroupCustom> spreadActivityGroupCustoms = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		resMap.put("Rows", spreadActivityGroupCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}



}
