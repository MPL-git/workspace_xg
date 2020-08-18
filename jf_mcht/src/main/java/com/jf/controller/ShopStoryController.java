package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.ShopStory;
import com.jf.entity.ShopStoryDetail;
import com.jf.entity.ShopStoryDetailExample;
import com.jf.entity.ShopStoryExample;
import com.jf.service.ShopStoryDetailService;
import com.jf.service.ShopStoryService;

@Controller
public class ShopStoryController  extends BaseController {
	
	@Autowired
	private ShopStoryService shopStoryService;
	
	@Autowired
	private ShopStoryDetailService shopStoryDetailService;
	

	@RequestMapping("/shopStory/shopStoryIndex")
	public String shopStoryIndex(HttpServletRequest request,Model model) {
		Integer mchtId = this.getSessionMchtInfo(request).getId();
		ShopStoryExample shopStoryExample = new ShopStoryExample();
		shopStoryExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<ShopStory> shopStoryList = shopStoryService.selectByExample(shopStoryExample);

		
		if(shopStoryList!=null&&shopStoryList.size()>0){
			model.addAttribute("shopStoryList", shopStoryList);//故事
			ShopStory shopStory = shopStoryList.get(0);
			model.addAttribute("shopStoryId", shopStory.getId());//故事
			/*String replace = shopStory.getStoryIntroduction().replace("\r\n", "<br>").replace("\n", "<br>");
			shopStory.setStoryIntroduction(replace);
			
			model.addAttribute("shopStory", shopStory);//故事
*/			ShopStoryDetailExample shopStoryDetailExample = new ShopStoryDetailExample();
			shopStoryDetailExample.createCriteria().andDelFlagEqualTo("0").andShopStoryIdEqualTo(shopStory.getId()).andMchtIdEqualTo(mchtId);
			List<ShopStoryDetail> shopStoryDetailList = shopStoryDetailService.selectByExample(shopStoryDetailExample);
			if(shopStoryDetailList!=null&&shopStoryDetailList.size()>0){
				model.addAttribute("shopStoryDetailList", shopStoryDetailList);
			}
		}
		return "shopStory/shopStoryIndex";
	}
	
	
	@RequestMapping("/shopStory/addPics")
	public String addPics(HttpServletRequest request,Model model) {
		String picId = request.getParameter("picId");
		if(!StringUtil.isEmpty(picId)){
			model.addAttribute("picId", picId);
		}
		String picPath = request.getParameter("picPath");
		String picUrl = request.getParameter("picUrl");	
		model.addAttribute("picPath", picPath);
		model.addAttribute("picUrl", picUrl);
		return "shopStory/addPics";
	}
	
	@ResponseBody
	@RequestMapping("/shopStory/shopStoryDetailSave")
	public ResponseMsg shopStoryDetailSave(HttpServletRequest request){
		
		try {
			Integer mchtId = this.getSessionMchtInfo(request).getId();
			String shopStoryId = request.getParameter("shopStoryId");
			String storyBrief = request.getParameter("storyBrief");//故事简介
			String picArrayJsonStr = request.getParameter("picArrayJsonStr");//故事详情
			if(!StringUtil.isEmpty(shopStoryId)){
				shopStoryService.updateWordAndPic(mchtId,shopStoryId, storyBrief, picArrayJsonStr);
			}else{
				shopStoryService.insertWordAndPic(mchtId,storyBrief, picArrayJsonStr);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	

}
