package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



















import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



















import com.jf.common.utils.StringUtils;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.WetaoChannel;
import com.jf.entity.WetaoChannelCustom;
import com.jf.entity.WetaoChannelCustomExample;
import com.jf.entity.WetaoChannelExample;
import com.jf.entity.WetaoChannelProduct;
import com.jf.entity.WetaoPage;
import com.jf.service.WeitaoChannelProductService;
import com.jf.service.WeitaoChannelService;
import com.jf.vo.Page;

@Controller
public class WeitaoChannelController extends BaseController{
	
	@Resource
	private WeitaoChannelService weitaoChannelService;
	
	@Resource
	private WeitaoChannelProductService weitaoChannelProductService;
	
	/**
	 * 微淘频道管理
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoChannel/list.shtml")
	public ModelAndView wetaoPageList(HttpServletRequest request) {
		return new ModelAndView("wetaoChannel/wetaoChannel_list");
	}
	
	/**
	 * 微淘频道管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoChannel/data.shtml")
	@ResponseBody
	public Map<String, Object> wetaoPageData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		/*Integer totalCount = 0;*/
		List<WetaoChannelCustom> WetaoChannelCustoms=null;
		try {
			WetaoChannelExample wetaoChannelExample = new WetaoChannelExample() ;
			wetaoChannelExample.createCriteria().andDelFlagEqualTo("0");
		    WetaoChannelCustoms = weitaoChannelService.selectWetaoChannel(wetaoChannelExample);
			resMap.put("Rows", WetaoChannelCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 新增和修改频道页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoChannel/toEdit.shtml")
	public ModelAndView toEdit(HttpServletRequest request) {
		String rtPage = "wetaoChannel/toEdit";
		String id = request.getParameter("id");
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(id)){
			WetaoChannel wetaoChannel = weitaoChannelService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("wetaoChannel", wetaoChannel);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoChannel/save.shtml")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
		String id = request.getParameter("id");
		String channelName = request.getParameter("channelName");
		String remarks = request.getParameter("remarks");
		WetaoChannel wetaoChannel=null;
		if(!StringUtils.isEmpty(id)){
			WetaoChannelExample example = new WetaoChannelExample();
			example.createCriteria().andIdEqualTo(Integer.parseInt(id)).andDelFlagEqualTo("0");
			List<WetaoChannel> WetaoChannels = weitaoChannelService.selectByExample(example );
			wetaoChannel=WetaoChannels.get(0);
			if(!StringUtils.isEmpty(channelName)){
				wetaoChannel.setChannelName(channelName);
			}
			if(!StringUtils.isEmpty(remarks)){
				wetaoChannel.setRemarks(remarks);
			}
			wetaoChannel.setUpdateDate(new Date());
			
			 String staffID = this.getSessionStaffBean(request).getStaffID();
			 wetaoChannel.setUpdateBy(Integer.parseInt(staffID));
			 
			weitaoChannelService.updateByPrimaryKeySelective(wetaoChannel);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
			
		}else{
			wetaoChannel=new WetaoChannel();
			if(!StringUtils.isEmpty(channelName)){
				wetaoChannel.setChannelName(channelName);
			}
			if(!StringUtils.isEmpty(remarks)){
				wetaoChannel.setRemarks(remarks);
			}
			wetaoChannel.setDelFlag("0");
			wetaoChannel.setCreateDate(new Date());
			 String staffID = this.getSessionStaffBean(request).getStaffID();
			 wetaoChannel.setCreateBy(Integer.parseInt(staffID));
			weitaoChannelService.insertSelective(wetaoChannel);
	/*		
			WetaoChannelProduct wetaoChannelProduct = new WetaoChannelProduct();
			wetaoChannelProduct.setChannelId(wetaoChannel.getId());
			wetaoChannelProduct.setDelFlag("0");
			wetaoChannelProduct.setCreateDate(new Date());
			
		
			 
			weitaoChannelProductService.insertSelective(wetaoChannelProduct);*/
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
			
		}
		}catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "确认失败");
		}
		return resMap;
	}

}
