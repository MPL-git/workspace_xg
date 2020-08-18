package com.jf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.service.AreaService;

@Controller
public class AreaController extends BaseController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private AreaService areaService;
	
	@RequestMapping(value="/area/getAreaList.shtml")
	@ResponseBody
	public List<Area> getAreaList(HttpServletRequest request){
		String parentAreaId=request.getParameter("parentAreaId");
		
		if(StringUtil.isEmpty(parentAreaId)){
			return new ArrayList<Area>();
		}
		
		AreaExample areaExample=new AreaExample();
		AreaExample.Criteria areaCriteria=areaExample.createCriteria();
		if(!StringUtil.isEmpty(parentAreaId)){
			areaCriteria.andParentIdEqualTo(Integer.valueOf(parentAreaId));
		}
		List<Area> areas=areaService.selectByExample(areaExample);
		return areas;
	}
}
