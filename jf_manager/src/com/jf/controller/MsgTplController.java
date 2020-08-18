package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MsgTplExample;
import com.jf.service.MsgTplService;
import com.jf.service.SysStatusService;
import com.jf.vo.Page;
/**
 * 消息模版
 * @author luoyl
 * 创建时间 2017-3-28 15:13
 */
@Controller
@RequestMapping("msgTpl")
public class MsgTplController extends BaseController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8432928674236368475L;
	@Autowired
	private MsgTplService msgTplService;
	
	@Autowired
	private SysStatusService sysStatusService;
	
	/**
	 * 消息模版列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("msgTpl-list.shtml")
	public ModelAndView getMsgTplList(Model model,HttpServletRequest request){
		String rtPage = "/msgTpl/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("statusList", DataDicUtil.getStatusList("BU_PLATFORM_MSG", "MSG_TYPE"));
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping("msgTpl-list-data.shtml")
	@ResponseBody
	public Map<String, Object> getMsgTplListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount =0;
		try {
			MsgTplExample msgTplExample=new MsgTplExample();
			MsgTplExample.Criteria criteria=msgTplExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			//模板类型
			if(!StringUtils.isEmpty(request.getParameter("tplType"))){
				String tplType=request.getParameter("tplType");
				criteria.andTplTypeEqualTo(tplType);
			}
			//消息类型
			if(!StringUtils.isEmpty(request.getParameter("msgType"))){
				String msgType=request.getParameter("msgType");
				criteria.andMsgTypeLike(msgType+"%");
			}
			msgTplExample.setLimitSize(page.getLimitSize());
			msgTplExample.setLimitStart(page.getLimitStart());
			totalCount=msgTplService.countMsgTplCustomByExample(msgTplExample);
			dataList=msgTplService.selectMsgTplCustomByExample(msgTplExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	

}
