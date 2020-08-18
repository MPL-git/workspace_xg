package com.jf.controller;

import com.jf.common.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Controller
public class LinkTypeController extends BaseController {
	
	
	/**
	 * 
	 * @Title 获取图片热区的连接类型
	 * @Description TODO   
	 * @author yjc
	 * @date 2019年6月26日17:49:39
	 */
	@ResponseBody
	@RequestMapping("/linkType/moduleMap/getLinkTypeList.shtml")
	public Map<String, Object> getLinkTypeList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		List<String> linkTypeNames = new ArrayList<String>();
		linkTypeNames.add("会场ID");//1
		linkTypeNames.add("特卖ID");//2
		linkTypeNames.add("商品ID");//3
		linkTypeNames.add("自建页面ID");//4
		linkTypeNames.add("栏目");//5
		linkTypeNames.add("旧品牌特卖一级类目");//6
		linkTypeNames.add("商城一级页面");//7
		linkTypeNames.add("商城二级页面");//8
		linkTypeNames.add("url链接");//9
		linkTypeNames.add("分区锚点");//10
		linkTypeNames.add("优惠券ID");//11
		linkTypeNames.add("商城一级类目");//12
		linkTypeNames.add("商家序号");//13
		linkTypeNames.add("淘宝优选关键字");//14
		linkTypeNames.add("新品牌团");//15
		linkTypeNames.add("新品牌团二级分类");//16
		linkTypeNames.add("淘宝优选频道");//17
		linkTypeNames.add("有好货二级分类");//18
		linkTypeNames.add("有好货品牌ID");//19
		linkTypeNames.add("潮鞋上新二级分类");//20
		linkTypeNames.add("潮鞋上新品牌ID");//21
		linkTypeNames.add("潮流男装二级分类");//22
		linkTypeNames.add("潮流男装品牌ID");//23
		linkTypeNames.add("断码特惠二级分类");//24
		linkTypeNames.add("断码特惠品牌ID");//25
		linkTypeNames.add("运动鞋服二级分类");//26
		linkTypeNames.add("运动鞋服品牌ID");//27
		linkTypeNames.add("潮流美妆二级分类");//28
		linkTypeNames.add("潮流美妆品牌ID");//29
		linkTypeNames.add("食品超市二级分类");//30
		linkTypeNames.add("食品超市品牌ID");//31
		linkTypeNames.add("小视频");//32
        linkTypeNames.add("倒计时");//33
        linkTypeNames.add("功能链接");//34
		String showType = request.getParameter("showType");
		JSONArray linkTypeJa = new JSONArray();
		for(int i=1; i<=linkTypeNames.size();i++){
			if(i==7 || i==8 || i==14){
				continue;
			}
			if(i<=15 || i == 33){
				if(i==10){
					if(!StringUtils.isEmpty(showType) && showType.equals("10")){
						JSONObject jo = new JSONObject();
						jo.put("linkType", i);
						jo.put("linkTypeName", linkTypeNames.get(i-1));
						linkTypeJa.add(jo);
					}
				}else{
					JSONObject jo = new JSONObject();
					jo.put("linkType", i);
					jo.put("linkTypeName", linkTypeNames.get(i-1));
					linkTypeJa.add(jo);
				}
			}
		}
		if(!StringUtils.isEmpty(showType)){
			//16.品牌团装修 17.淘宝客装修(14.淘宝优选关键字) 18.有好货装修 20.潮鞋上新装修 22.潮流男装装修 24.断码特惠装修 26.运动鞋服装修 28.潮流美妆装修 30.食品超市装修
			String[] showTypeArray = showType.split(",");
			for(String item:showTypeArray){
				int index = Integer.parseInt(item);
				JSONObject jo = new JSONObject();
				if(index<=17){
					jo.put("linkType", index);
					jo.put("linkTypeName", linkTypeNames.get(index-1));
					if(index==17){
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("linkType", 14);
						jsonObject.put("linkTypeName", "淘宝优选关键字");
						linkTypeJa.add(jsonObject);
					}
				}else if(index==34){ //功能链接
					jo.put("linkType", index);
					jo.put("linkTypeName", linkTypeNames.get(index-1));
				}else{
					jo.put("linkType", index);
					jo.put("linkTypeName", linkTypeNames.get(index-1));
					
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("linkType", index+1);
					jsonObject.put("linkTypeName", linkTypeNames.get(index));
					linkTypeJa.add(jsonObject);
				}
				linkTypeJa.add(jo);
			}
		}
		map.put("linkTypeList", linkTypeJa);
		List<String> linkValueNames = new ArrayList<String>();
		linkValueNames.add("新用户专享");//1
		linkValueNames.add("单品爆款");//2
		linkValueNames.add("限时抢购");//3
		linkValueNames.add("新用户秒杀");//4
		linkValueNames.add("积分商城");//5
		linkValueNames.add("断码清仓");//6
		linkValueNames.add("签到");//7
		linkValueNames.add("砍价");//8
		linkValueNames.add("邀请免单");//9
		linkValueNames.add("有好货");//10
		linkValueNames.add("每日好店");//11
		linkValueNames.add("新品牌团");//12
		linkValueNames.add("新星计划");//13
		linkValueNames.add("淘宝优选");//14
		linkValueNames.add("潮鞋上新");//15
		linkValueNames.add("潮流男装");//16
		linkValueNames.add("断码特惠");//17
		linkValueNames.add("运动鞋服");//18
		linkValueNames.add("潮流美妆");//19
		linkValueNames.add("食品超市");//20
		linkValueNames.add("大学生创业");//21
		linkValueNames.add("SVIP");//22
		linkValueNames.add("领劵中心");//23
		linkValueNames.add("小视频");//24
		linkValueNames.add("主会场");//25
		linkValueNames.add("我的优惠券");//26
		linkValueNames.add("热卖榜单");//27
		linkValueNames.add("积分抽奖");//28
		JSONArray linkValueJa = new JSONArray();
		for(int j=1; j<=linkValueNames.size();j++){
			JSONObject jo = new JSONObject();
			jo.put("linkValue", j);
			jo.put("linkValueName", linkValueNames.get(j-1));
			linkValueJa.add(jo);
		}
		map.put("linkValueList", linkValueJa);
		//功能链接
		if(!StringUtils.isEmpty(showType) && "34".equals(showType) ) {
			List<String> linkValueNameURLs = new ArrayList<String>();
			linkValueNameURLs.add("领取Svip体验卡");
			JSONArray linkValueURLJa = new JSONArray();
			for(int j=1; j<=linkValueNameURLs.size();j++){
				JSONObject jo = new JSONObject();
				jo.put("linkValue", j);
				jo.put("linkValueName", linkValueNameURLs.get(j-1));
				linkValueURLJa.add(jo);
			}
			map.put("linkValueURLList", linkValueURLJa);
		}
		return map;
	}
	
	/**
	 * 
	 * @Title 获取广告的连接类型
	 * @Description TODO   
	 * @author yjc
	 * @date 2019年6月26日17:49:39
	 */
	@ResponseBody
	@RequestMapping("/linkType/adInfo/getLinkTypeList.shtml")
	public Map<String, Object> getLinkTypes(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		List<String> linkTypeNames = new ArrayList<String>();
		linkTypeNames.add("会场");
		linkTypeNames.add("特卖");
		linkTypeNames.add("商品");
		linkTypeNames.add("外部链接");
		linkTypeNames.add("无链接");
		linkTypeNames.add("频道");
		linkTypeNames.add("自建页面");
		linkTypeNames.add("淘宝优选关键字");
		linkTypeNames.add("新品牌团");
		linkTypeNames.add("商家序号");
		linkTypeNames.add("一级分类");
		linkTypeNames.add("淘宝优选频道");
		linkTypeNames.add("新品牌团二级分类");
		linkTypeNames.add("有好货二级分类");
		linkTypeNames.add("有好货品牌ID");
		linkTypeNames.add("潮鞋上新二级分类");
		linkTypeNames.add("潮鞋上新品牌ID");
		linkTypeNames.add("潮流男装二级分类");
		linkTypeNames.add("潮流男装品牌ID");
		linkTypeNames.add("断码特惠二级分类");
		linkTypeNames.add("断码特惠品牌ID");
		linkTypeNames.add("运动鞋服二级分类");
		linkTypeNames.add("运动鞋服品牌ID");
		linkTypeNames.add("潮流美妆二级分类");
		linkTypeNames.add("潮流美妆品牌ID");
		linkTypeNames.add("食品超市二级分类");
		linkTypeNames.add("食品超市品牌ID");
		linkTypeNames.add("商城一级分类");
		linkTypeNames.add("优惠券ID");
//		linkTypeNames.add("每日好店二级分类");
//		linkTypeNames.add("每日好店品牌ID");
//		linkTypeNames.add("大学生创业二级分类");
//		linkTypeNames.add("大学生创业品牌ID");
		JSONArray linkTypeJa = new JSONArray();
		for(int i=1; i<=linkTypeNames.size();i++){
			if((i<=11 && i!=8) || i == 28 || i == 29){
				JSONObject jo = new JSONObject();
				jo.put("linkType", i);
				jo.put("linkTypeName", linkTypeNames.get(i-1));
				linkTypeJa.add(jo);
			}
		}
		String showType = request.getParameter("showType");
		if(!StringUtils.isEmpty(showType)){
			//12.淘宝优选频道,13.品牌团装修,14.有好货装修,16.潮鞋上新装修,18.潮流男装装修,20.断码特惠装修,22.运动鞋服装修,24.潮流美妆装修,26.食品超市装修
			int index = Integer.parseInt(showType);
			JSONObject jo = new JSONObject();
			if(index<=13){
				jo.put("linkType", index);
				jo.put("linkTypeName", linkTypeNames.get(index-1));
				if(index == 12){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("linkType", 8);
					jsonObject.put("linkTypeName", "淘宝优选关键字");
					linkTypeJa.add(jsonObject);
				}
			}else{
				jo.put("linkType", index);
				jo.put("linkTypeName", linkTypeNames.get(index-1));
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("linkType", index+1);
				jsonObject.put("linkTypeName", linkTypeNames.get(index));
				linkTypeJa.add(jsonObject);
			}
			linkTypeJa.add(jo);
		}
		
		map.put("linkTypeList", linkTypeJa);
		List<String> linkValueNames = new ArrayList<String>();
		linkValueNames.add("新用户专区");
		linkValueNames.add("爆款专区");
		linkValueNames.add("限时抢购");
		linkValueNames.add("新人秒杀");
		linkValueNames.add("积分商城");
		linkValueNames.add("断码清仓");
		linkValueNames.add("签到");
		linkValueNames.add("砍价");
		linkValueNames.add("邀请免单");
		linkValueNames.add("有好货");
		linkValueNames.add("每日好店");
		linkValueNames.add("新品牌团");
		linkValueNames.add("拉新分润好货推荐");
		linkValueNames.add("新星计划");
		linkValueNames.add("淘宝优选");
		linkValueNames.add("潮鞋上新");
		linkValueNames.add("潮流男装");
		linkValueNames.add("断码特惠");
		linkValueNames.add("运动鞋服");
		linkValueNames.add("潮流美妆");
		linkValueNames.add("食品超市");
		linkValueNames.add("大学生创业");
		linkValueNames.add("SVIP");
		linkValueNames.add("主会场");
		JSONArray linkValueJa = new JSONArray();
		for(int j=1; j<=linkValueNames.size();j++){
			JSONObject jo = new JSONObject();
			jo.put("linkValue", j);
			jo.put("linkValueName", linkValueNames.get(j-1));
			linkValueJa.add(jo);
		}
		map.put("linkValueList", linkValueJa);
		return map;
	}
}
