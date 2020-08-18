package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jf.common.constant.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityExample;
import com.jf.entity.SpreadActivityGroup;
import com.jf.entity.SpreadActivityGroupExample;
import com.jf.entity.SpreadName;
import com.jf.entity.SpreadNameCustom;
import com.jf.entity.SpreadNameCustomExample;
import com.jf.entity.SpreadNameExample;
import com.jf.entity.StateCode;
import com.jf.service.SpreadActivityGroupService;
import com.jf.service.SpreadNameService;
import com.jf.vo.Page;

@Controller
public class SpreadNameController extends BaseController {
	
	@Resource
	private SpreadActivityGroupService spreadActivityGroupService;
	
	@Resource
	private SpreadNameService spreadNameService;
	
	/**
	 * 推广活动组列表页
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "/spread/spreadNameIndex.shtml")
	public ModelAndView mchtProductTypeCheckIndex(HttpServletRequest request) {
		String rePage = "/spread/spreadNameIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();

		SpreadActivityGroupExample spreadActivityGroupExample=new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0").andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
		List<SpreadActivityGroup> spreadActivityGroupList=spreadActivityGroupService.selectByExample(spreadActivityGroupExample);
		
		resMap.put("channelList", DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL"));
		resMap.put("spreadActivityGroupList", spreadActivityGroupList);
		return new ModelAndView(rePage, resMap);
	}

	@RequestMapping(value = "/spread/spreadNameDataList.shtml")
	@ResponseBody
	public Map<String, Object> spreadNameDataList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		
		SpreadNameCustomExample spreadNameCustomExample=new SpreadNameCustomExample();
		SpreadNameCustomExample.SpreadNameCustomCriteria criteria=spreadNameCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
		
		if(!StringUtil.isEmpty(request.getParameter("channel"))){
			criteria.andChannelIdEqualTo(request.getParameter("channel"));
		}
		if(!StringUtil.isEmpty(request.getParameter("spreadName"))){
			criteria.andSpreadNameLike("%"+request.getParameter("spreadName")+"%");
		}
		if(!StringUtil.isEmpty(request.getParameter("activityGroupId"))){
			criteria.andActivityGroupIdEqualTo(Integer.valueOf(request.getParameter("activityGroupId")));
		}
		if(!StringUtil.isEmpty(request.getParameter("isEffect"))){
			criteria.andIsEffectEqualTo(request.getParameter("isEffect"));
		}
		
		
		totalCount=spreadNameService.countSpreadNameCustomByExample(spreadNameCustomExample);
		
		spreadNameCustomExample.setLimitSize(page.getLimitSize());
		spreadNameCustomExample.setLimitStart(page.getLimitStart());
		
		List<SpreadNameCustom> spreadNames=spreadNameService.selectSpreadNameCustomByExample(spreadNameCustomExample);

		resMap.put("Rows", spreadNames);
		resMap.put("Total", totalCount);

		return resMap;
	}
	
	
	//批量修改推广活动组
	@RequestMapping(value = "/spread/batchEditSpreadName.shtml")
	public ModelAndView batchEditSpreadName(HttpServletRequest request) {	
		String rtPage = "/spread/batchEditSpreadName";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("spreadNameIds", request.getParameter("spreadNameIds"));
		SpreadActivityGroupExample spreadActivityGroupExample=new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0").andIsEffectEqualTo("1");
		resMap.put("spreadActivityGroups", spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample));
		return new ModelAndView(rtPage, resMap);
	}
	
	
	
	
	
	//保存修改或添加
	@ResponseBody
	@RequestMapping(value = "/spread/batchEditSpreadNameSave.shtml")
	public ModelAndView batchEditSpreadNameSave(HttpServletRequest request,SpreadName spreadName){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			
			
			if(StringUtil.isEmpty(request.getParameter("spreadNameIds"))){
				throw new Exception("Id不能为空。");
			}
			
			if(spreadName.getActivityGroupId()==null||spreadName.getActivityGroupId()==0){
				spreadName.setActivityGroupId(null);
			}
			if(StringUtil.isEmpty(spreadName.getIsEffect())){
				spreadName.setIsEffect(null);
			}
			
			String[] spreadNameIds=request.getParameter("spreadNameIds").split(",");
			spreadName.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			spreadName.setUpdateDate(new Date());
			spreadNameService.batchEditSpreadName(spreadNameIds, spreadName);
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
	
	@ResponseBody
	@RequestMapping(value = "/spread/updateSpreadNameSeqNo.shtml")
	public Map<String, Object> updateSingleProductActivity(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new Exception("id不能为空");
			}
			if(StringUtil.isEmpty(request.getParameter("seqNo"))){
				throw new Exception("排序值不能为空");
			}
			
			SpreadName spreadName4Update=new SpreadName();
			spreadName4Update.setSeqNo(Integer.valueOf(request.getParameter("seqNo")));
			spreadName4Update.setId(Integer.valueOf(request.getParameter("id")));
			spreadName4Update.setUpdateDate(new Date());
			spreadName4Update.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			spreadNameService.updateByPrimaryKeySelective(spreadName4Update);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}

	/**
	 * 安卓活动名称管理列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/androidSpread/list.shtml")
	public ModelAndView androidSpreadList(HttpServletRequest request) {
		String rePage = "/spread/androidSpreadList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SpreadActivityGroupExample spreadActivityGroupExample=new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0").andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
		List<SpreadActivityGroup> spreadActivityGroupList=spreadActivityGroupService.selectByExample(spreadActivityGroupExample);
		resMap.put("channelList", DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL"));
		resMap.put("spreadActivityGroupList", spreadActivityGroupList);
		return new ModelAndView(rePage, resMap);
	}

	/**
	 * 安卓活动名称管理列表数据
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/androidSpread/data.shtml")
	public Map<String, Object> androidSpreadData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		SpreadNameCustomExample spreadNameCustomExample=new SpreadNameCustomExample();
		SpreadNameCustomExample.SpreadNameCustomCriteria criteria=spreadNameCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
		if(!StringUtil.isEmpty(request.getParameter("channel"))){
			criteria.andChannelIdEqualTo(request.getParameter("channel"));
		}
		if(!StringUtil.isEmpty(request.getParameter("spreadName"))){
			criteria.andSpreadNameLike("%"+request.getParameter("spreadName")+"%");
		}
		if(!StringUtil.isEmpty(request.getParameter("activityGroupId"))){
			criteria.andActivityGroupIdEqualTo(Integer.valueOf(request.getParameter("activityGroupId")));
		}
		if(!StringUtil.isEmpty(request.getParameter("isEffect"))){
			criteria.andIsEffectEqualTo(request.getParameter("isEffect"));
		}
		spreadNameCustomExample.setLimitSize(page.getLimitSize());
		spreadNameCustomExample.setLimitStart(page.getLimitStart());
		totalCount = spreadNameService.countSpreadNameCustomByExample(spreadNameCustomExample);
		List<SpreadNameCustom> spreadNames = spreadNameService.selectSpreadNameCustomByExample(spreadNameCustomExample);
		resMap.put("Rows", spreadNames);
		resMap.put("Total", totalCount);
		return resMap;
	}

	
	
}
