package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.common.constant.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.SpreadActivityGroupCustom;
import com.jf.entity.SpreadActivityGroupExample;
import com.jf.entity.SpreadActivityGroupSet;
import com.jf.entity.SpreadActivityGroupSetCustom;
import com.jf.entity.SpreadActivityGroupSetCustomExample;
import com.jf.entity.SpreadActivityGroupSetDtlCustom;
import com.jf.entity.SpreadActivityGroupSetDtlCustomExample;
import com.jf.entity.StateCode;
import com.jf.entity.SysStatus;
import com.jf.service.SpreadActivityGroupService;
import com.jf.service.SpreadActivityGroupSetDtlService;
import com.jf.service.SpreadActivityGroupSetService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SpreadActivityGroupSetController extends BaseController {

	@Autowired
	private SpreadActivityGroupSetService spreadActivityGroupSetService;
	
	@Autowired
	private SpreadActivityGroupSetDtlService spreadActivityGroupSetDtlService;
	
	@Autowired
	private SpreadActivityGroupService spreadActivityGroupService;

	/**
	 * 
	 * @MethodName: spreadActivityGroupSetManager
	 * @Description: (IOS活动组集合)
	 * @author Pengl
	 * @date 2019年4月19日 下午2:39:55
	 */
	@RequestMapping("/spreadActivityGroupSet/spreadActivityGroupSetManager.shtml")
	public ModelAndView spreadActivityGroupSetManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/spreadActivityGroupSet/spreadActivityGroupSetList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: spreadActivityGroupSetList
	 * @Description: (IOS活动组集合)
	 * @author Pengl
	 * @date 2019年4月19日 下午2:40:37
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityGroupSet/spreadActivityGroupSetList.shtml")
	public Map<String, Object> spreadActivityGroupSetList(HttpServletRequest request, HttpServletResponse response, Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<SpreadActivityGroupSetCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.SpreadActivityGroupSetCustomCriteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			if(!StringUtil.isEmpty(request.getParameter("channel")) ) {
				spreadActivityGroupSetCustomCriteria.andChannelEqualTo(request.getParameter("channel"));
			}
			if(!StringUtil.isEmpty(request.getParameter("name")) ) {
				spreadActivityGroupSetCustomCriteria.andNameLike("%" + request.getParameter("name") + "%");
			} 
			if(!StringUtil.isEmpty(request.getParameter("status")) ) {
				spreadActivityGroupSetCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(seq_no, 999999999), id desc");
			spreadActivityGroupSetCustomExample.setLimitSize(page.getLimitSize());
			spreadActivityGroupSetCustomExample.setLimitStart(page.getLimitStart());
			dataList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
			totalCount = spreadActivityGroupSetService.countByCustomExample(spreadActivityGroupSetCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: updateSeqNo
	 * @Description: (排序)
	 * @author Pengl
	 * @date 2019年4月19日 下午2:40:48
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityGroupSet/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(id)) {
				SpreadActivityGroupSet spreadActivityGroupSet = new SpreadActivityGroupSet();
				spreadActivityGroupSet.setId(Integer.parseInt(id));
				spreadActivityGroupSet.setSeqNo(Integer.parseInt(seqNo));
				spreadActivityGroupSetService.updateByPrimaryKeySelective(spreadActivityGroupSet);
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
	 * @MethodName: editSpreadActivityGroupSetManager
	 * @Description: (编辑)
	 * @author Pengl
	 * @date 2019年4月19日 下午2:40:58
	 */
	@RequestMapping("/spreadActivityGroupSet/editSpreadActivityGroupSetManager.shtml")
	public ModelAndView editSpreadActivityGroupSetManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/spreadActivityGroupSet/editSpreadActivityGroupSet");
		if(!StringUtil.isEmpty(request.getParameter("id")) ) {
			SpreadActivityGroupSetCustom spreadActivityGroupSetCustom = spreadActivityGroupSetService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("id")));
			m.addObject("spreadActivityGroupSetCustom", spreadActivityGroupSetCustom);
			SpreadActivityGroupSetDtlCustomExample spreadActivityGroupSetDtlCustomExample = new SpreadActivityGroupSetDtlCustomExample();
			SpreadActivityGroupSetDtlCustomExample.SpreadActivityGroupSetDtlCustomCriteria spreadActivityGroupSetDtlCustomCriteria = spreadActivityGroupSetDtlCustomExample.createCriteria();
			spreadActivityGroupSetDtlCustomCriteria.andDelFlagEqualTo("0").andSpreadActivityGroupSetIdEqualTo(spreadActivityGroupSetCustom.getId());
			List<SpreadActivityGroupSetDtlCustom> spreadActivityGroupSetDtlCustomList = spreadActivityGroupSetDtlService.selectByCustomExample(spreadActivityGroupSetDtlCustomExample);
			m.addObject("spreadActivityGroupSetDtlCustomList", spreadActivityGroupSetDtlCustomList);
		}
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);
		m.addObject("deviceType", request.getParameter("deviceType"));
		return m;
	}
	
	/**
	 * 
	 * @MethodName: spreadActivityGroupManager
	 * @Description: (添加广告组)
	 * @author Pengl
	 * @date 2019年4月19日 下午2:41:31
	 */
	@RequestMapping("/spreadActivityGroupSet/spreadActivityGroupManager.shtml")
	public ModelAndView spreadActivityGroupManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/spreadActivityGroupSet/spreadActivityGroupList");
		m.addObject("channel", request.getParameter("channel"));
		m.addObject("activityGroupIds", request.getParameter("activityGroupIds"));
		m.addObject("deviceType", request.getParameter("deviceType"));
		return m;
	}
	
	/**
	 * 
	 * @MethodName: spreadActivityGroupList
	 * @Description: (添加广告组)
	 * @author Pengl
	 * @date 2019年4月19日 下午2:41:54
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityGroupSet/spreadActivityGroupList.shtml")
	public Map<String, Object> spreadActivityGroupList(HttpServletRequest request, HttpServletResponse response, Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<SpreadActivityGroupCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
			SpreadActivityGroupExample.Criteria spreadActivityGroupCriteria = spreadActivityGroupExample.createCriteria();
			spreadActivityGroupCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("channel")) ) {
				spreadActivityGroupCriteria.andChannelEqualTo(request.getParameter("channel"));
			}
			if(!StringUtil.isEmpty(request.getParameter("deviceType"))) {
				spreadActivityGroupCriteria.andDeviceTypeEqualTo(request.getParameter("deviceType"));
			}
			String activityGroupIds = request.getParameter("activityGroupIds");
			if(!StringUtil.isEmpty(activityGroupIds) ) {
				List<Integer> idList = new ArrayList<Integer>();
				String[] groupIds = activityGroupIds.split(",");
				for(String groupId : groupIds ) {
					idList.add(Integer.parseInt(groupId));
				}
				spreadActivityGroupCriteria.andIdNotIn(idList);
			}
			if(!StringUtil.isEmpty(request.getParameter("activityGroup")) ) {
				spreadActivityGroupCriteria.andActivityGroupLike("%"+request.getParameter("activityGroup")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("isEffect")) ) {
				spreadActivityGroupCriteria.andIsEffectEqualTo(request.getParameter("isEffect"));
			}
			spreadActivityGroupExample.setOrderByClause(" id desc");
			spreadActivityGroupExample.setLimitSize(page.getLimitSize());
			spreadActivityGroupExample.setLimitStart(page.getLimitStart());
			dataList = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
			totalCount = spreadActivityGroupService.countByExample(spreadActivityGroupExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: editSpreadActivityGroupSet
	 * @Description: (保存编辑)
	 * @author Pengl
	 * @date 2019年4月19日 下午2:42:03
	 */
	@ResponseBody
	@RequestMapping("/spreadActivityGroupSet/editSpreadActivityGroupSet.shtml")
	public ModelAndView editSpreadActivityGroupSet(HttpServletRequest request){
		ModelAndView m = new ModelAndView("/success/success");
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			String id = request.getParameter("id");
			String channelOld = request.getParameter("channelOld");
			String activityGroupIds = request.getParameter("activityGroupIds");
			String channel = request.getParameter("channel");
			String name = request.getParameter("name");
			String status = request.getParameter("status");
			String remarks = request.getParameter("remarks");
			String deviceType = request.getParameter("deviceType");
			SpreadActivityGroupSet spreadActivityGroupSet = new SpreadActivityGroupSet();
			if(!StringUtil.isEmpty(id) ) {
				spreadActivityGroupSet.setId(Integer.parseInt(id));
				spreadActivityGroupSet.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				spreadActivityGroupSet.setUpdateDate(date);
			}else {
				spreadActivityGroupSet.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				spreadActivityGroupSet.setCreateDate(date);
			}
			if(!StringUtil.isEmpty(channel) ) {
				spreadActivityGroupSet.setChannel(channel);
			}
			if(!StringUtil.isEmpty(status) ) {
				spreadActivityGroupSet.setStatus(status);;
			}
			if(!StringUtil.isEmpty(name) ) {
				spreadActivityGroupSet.setName(name);
			}
			if(!StringUtil.isEmpty(remarks) ) {
				spreadActivityGroupSet.setRemarks(remarks);
			}
			if (!StringUtil.isEmpty(deviceType)) {
				spreadActivityGroupSet.setDeviceType(deviceType);
			}
			spreadActivityGroupSetService.editSpreadActivityGroupSet(spreadActivityGroupSet, channelOld, activityGroupIds);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		}catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		m.addObject(this.JSON_RESULT_CODE, code);
		m.addObject(this.JSON_RESULT_MESSAGE, msg);
		return m;
	}

	/**
	 * 安卓活动组集合列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/androidActivityGroupSet/list.shtml")
	public ModelAndView androidActivityGroupSetList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/spreadActivityGroupSet/androidActivityGroupSetList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);
		return m;
	}

	/**
	 * 安卓活动组集合列表数据
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/androidActivityGroupSet/data.shtml")
	public Map<String, Object> androidActivityGroupSetData(HttpServletRequest request, Page page){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<SpreadActivityGroupSetCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.SpreadActivityGroupSetCustomCriteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			if(!StringUtil.isEmpty(request.getParameter("channel")) ) {
				spreadActivityGroupSetCustomCriteria.andChannelEqualTo(request.getParameter("channel"));
			}
			if(!StringUtil.isEmpty(request.getParameter("name")) ) {
				spreadActivityGroupSetCustomCriteria.andNameLike("%" + request.getParameter("name") + "%");
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ) {
				spreadActivityGroupSetCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(seq_no, 999999999), id desc");
			spreadActivityGroupSetCustomExample.setLimitSize(page.getLimitSize());
			spreadActivityGroupSetCustomExample.setLimitStart(page.getLimitStart());
			dataList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
			totalCount = spreadActivityGroupSetService.countByCustomExample(spreadActivityGroupSetCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}



	
}
