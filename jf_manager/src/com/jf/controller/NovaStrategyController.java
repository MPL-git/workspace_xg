package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;












import com.gzs.common.utils.StringUtil;
import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordExample;
import com.jf.entity.KeywordHomoionym;
import com.jf.entity.NovaStrategy;
import com.jf.entity.NovaStrategyCustom;
import com.jf.entity.NovaStrategyExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.NovaStrategyService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class NovaStrategyController extends BaseController{
	
	@Autowired
	private NovaStrategyService novaStrategyService;
	
	//新星攻略、店长攻略页面
	@RequestMapping(value="/novaStrategy/index.shtml")
	public ModelAndView index(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		//新星攻略0 店长攻略1
		String type = request.getParameter("type");
		map.put("type", type);
		return new ModelAndView("/novaStrategy/novaStrategyList",map);
	}
	
	//获取新星攻略数据、店长攻略数据
	@ResponseBody
	@RequestMapping(value="/novaStrategy/getNovaStrategyList.shtml")
	public Map<String, Object> getNovaStrategyList(HttpServletRequest request,Page page){
		Map<String,Object> map = new HashMap<String, Object>();
		//查询新星攻略数据
		NovaStrategyExample novaStrategyExample = new NovaStrategyExample();
		novaStrategyExample.setOrderByClause("ifnull(a.seq_no,99999) asc,a.id desc");
		NovaStrategyExample.Criteria criteria = novaStrategyExample.createCriteria();
		novaStrategyExample.setLimitSize(page.getLimitSize());
		novaStrategyExample.setLimitStart(page.getLimitStart());
		criteria.andDelFlagEqualTo("0").andTypeEqualTo(request.getParameter("type"));
		if(!StringUtils.isBlank(request.getParameter("title"))){
			criteria.andTitleLike("%"+request.getParameter("title")+"%");
		}
		int totalCount = novaStrategyService.countByExample(novaStrategyExample);
		List<NovaStrategyCustom> list = novaStrategyService.selectByCustomExample(novaStrategyExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	//更改排序
	@ResponseBody
	@RequestMapping("/novaStartegy/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String novaStrategyId = request.getParameter("novaStrategyId");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(novaStrategyId) && !StringUtil.isEmpty(seqNo) ) {
				NovaStrategyExample novaStrategyExample = new NovaStrategyExample();
				novaStrategyExample.createCriteria().andIdEqualTo(Integer.parseInt(novaStrategyId));
				NovaStrategy novaStrategy = new NovaStrategy();
				novaStrategy.setSeqNo(Integer.parseInt(seqNo));
				novaStrategy.setUpdateBy(Integer.parseInt(staffID));
				novaStrategy.setUpdateDate(date);
				novaStrategyService.updateByExampleSelective(novaStrategy, novaStrategyExample);
			}else {
				code = "9999";
				msg = "ID为空！";
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
	
    //编辑或添加的新星攻略信息初始页面
	@RequestMapping(value = "/novaStrategy/edit.shtml")
	public ModelAndView edit(HttpServletRequest request) {
		String rtPage = "/novaStrategy/editNovaStrategy";
		String  editNovaStrategyId=request.getParameter("Id");		
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (editNovaStrategyId!=null && editNovaStrategyId!="") {
			NovaStrategy novaStrategy = novaStrategyService.selectByPrimaryKey(Integer.valueOf(editNovaStrategyId));
			resMap.put("novaStrategy", novaStrategy);
		}
		resMap.put("type", request.getParameter("type"));
		return new ModelAndView(rtPage, resMap);
	}
	
	//提交新星攻略信息
	@RequestMapping(value = "/novaStartegy/updateOrAddNovaStrategy.shtml")
	public ModelAndView updateNovaStrategy(HttpServletRequest request){
		String rtPage = "/success/success";
		Map<String, Object> map = new HashMap<String, Object>();
		StaffBean staffBean = this.getSessionStaffBean(request);
		int staffId=Integer.valueOf(staffBean.getStaffID());
		request.setAttribute("staffId", staffId);
		map = novaStrategyService.editOrAdd(request);
		return new ModelAndView(rtPage,map);
	}
	
	//删除新星攻略信息
	@ResponseBody
	@RequestMapping(value = "/novaStrategy/delnovaStrategy.shtml")
	public Map<String, Object> delnovaStrategy(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		Integer novaStrategyId = Integer.parseInt(request.getParameter("novaStrategyId"));
		try {
			novaStrategyService.deleteByPrimaryKey(novaStrategyId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
			
		}
		map.put(this.JSON_RESULT_CODE,code);
		map.put(this.JSON_RESULT_MESSAGE, msg);
		return map;
	}
}
