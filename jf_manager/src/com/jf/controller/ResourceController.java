package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.ext.core.ABaseController;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoExample;
import com.jf.entity.SubOrder;
import com.jf.service.ActivityAuditLogService;
import com.jf.service.KdnWuliuInfoService;
/**
 * 资源管理
 * 
 * @author huangdl
 */
@Controller
public class ResourceController extends ABaseController {

	@Autowired
	private KdnWuliuInfoService kdnWuliuInfoService;
	
	
	/**
	 * 查看物流信息
	 */
	@RequestMapping(value="/resource/viewWuliu.shtml")
	public String viewWuliu(Integer expressId, String expressNo){
		String page = "/resource/viewWuliu";
		Map<String, Object> data = new HashMap<String, Object>();
		
		String trackInfo = kdnWuliuInfoService.findTractInfo(expressId, expressNo);
		if(StrKit.notBlank(trackInfo)){
			data.put("trackInfo", JSONArray.fromObject(trackInfo));
		}
		
		return page(data, page);
	}
	

}
