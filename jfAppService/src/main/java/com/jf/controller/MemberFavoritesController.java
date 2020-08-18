package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.Favorites;
import com.jf.entity.FavoritesExample;
import com.jf.entity.MemberFavorites;
import com.jf.entity.MemberFavoritesExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.Style;
import com.jf.entity.StyleExample;
import com.jf.service.FavoritesService;
import com.jf.service.MemberFavoritesService;
import com.jf.service.MemberInfoService;
import com.jf.service.StyleService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月12日 下午3:07:26 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class MemberFavoritesController extends BaseController{
	
	@Resource
	private MemberFavoritesService memberFavoritesService;
	
	@Resource
	private FavoritesService favoritesService;
	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private StyleService styleService;
	
	/**
	 * 
	 * 方法描述 ：添加会员个人喜好
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月12日 下午3:27:31 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberFavorites")
	@ResponseBody
	public ResponseMsg addMemberFavorites(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			String memberId = reqDataJson.getString("memberId");
			String favoritesId = reqDataJson.getString("favoritesId");
			String imei = "";
			if(reqDataJson.containsKey("imei")){
				imei = reqDataJson.getString("imei");
			}
			if(!StringUtil.isBlank(memberId)){
				MemberFavoritesExample memberFavoritesExample = new MemberFavoritesExample();
				memberFavoritesExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId));
				memberFavoritesService.deleteByExample(memberFavoritesExample);
				if(!StringUtil.isBlank(favoritesId)){
					String[] favoritesIds = favoritesId.split(",");
					for (String str : favoritesIds) {
						MemberFavorites memberFavorites = new MemberFavorites();
						memberFavorites.setMemberId(Integer.valueOf(memberId));
						memberFavorites.setFavoritesId(Integer.valueOf(str));
						memberFavorites.setCreateBy(memberId);
						memberFavorites.setCreateDate(new Date());
						memberFavorites.setDelFlag("0");
						memberFavoritesService.insertSelective(memberFavorites);
					}
				}
			}else if(!StringUtil.isBlank(imei)){
				//中类型为4的用户信息
				MemberInfo memberInfo = memberInfoService.findModelByImei(imei,Const.MEMBER_INFO_TYPE_NEW_TOURIST);
				//找到插入
				if(memberInfo != null){
					MemberFavoritesExample memberFavoritesExample = new MemberFavoritesExample();
					memberFavoritesExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId));
					memberFavoritesService.deleteByExample(memberFavoritesExample);
					if(!StringUtil.isBlank(favoritesId)){
						String[] favoritesIds = favoritesId.split(",");
						for (String str : favoritesIds) {
							MemberFavorites memberFavorites = new MemberFavorites();
							memberFavorites.setMemberId(memberInfo.getId());
							memberFavorites.setFavoritesId(Integer.valueOf(str));
							memberFavorites.setCreateBy(memberId);
							memberFavorites.setCreateDate(new Date());
							memberFavorites.setDelFlag("0");
							memberFavoritesService.insertSelective(memberFavorites);
						}
					}
				}
			}

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	@RequestMapping(value = "/api/n/addMemberFavorites20170818")
	@ResponseBody
	public ResponseMsg addMemberFavorites20170818(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			String favoritesId = reqDataJson.getString("favoritesId");
			if(!StringUtil.isBlank(memberId)){
				MemberFavoritesExample memberFavoritesExample = new MemberFavoritesExample();
				memberFavoritesExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId));
				memberFavoritesService.deleteByExample(memberFavoritesExample);
				if(!StringUtil.isBlank(favoritesId)){
					String[] favoritesIds = favoritesId.split(",");
					for (String str : favoritesIds) {
						MemberFavorites memberFavorites = new MemberFavorites();
						memberFavorites.setMemberId(Integer.valueOf(memberId));
						memberFavorites.setFavoritesId(Integer.valueOf(str));
						memberFavorites.setCreateBy(memberId);
						memberFavorites.setCreateDate(new Date());
						memberFavorites.setDelFlag("0");
						memberFavoritesService.insertSelective(memberFavorites);
					}
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取个人喜好列表
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月12日 下午3:25:57 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMemberFavoritesList")
	@ResponseBody
	public ResponseMsg getMemberFavoritesList(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			
			FavoritesExample favoritesExample = new FavoritesExample();
			favoritesExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
			favoritesExample.setOrderByClause("seq_no");
			List<Favorites> favoritesList = favoritesService.selectByExample(favoritesExample);
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataMap = new HashMap<String,Object>();
			if(favoritesList != null && favoritesList.size() > 0){
				for (Favorites favorites : favoritesList) {
					dataMap = new HashMap<String,Object>();
					dataMap.put("id", favorites.getId());
					dataMap.put("name", favorites.getName());
					dataMap.put("pic", FileUtil.getImageServiceUrl()+favorites.getPic());
					if(!StringUtil.isBlank(memberId)){
						MemberFavoritesExample memberFavoritesExample = new MemberFavoritesExample();
						memberFavoritesExample.createCriteria().andFavoritesIdEqualTo(favorites.getId())
						.andDelFlagEqualTo("0").andMemberIdEqualTo(Integer.valueOf(memberId));
						List<MemberFavorites> memberFavoritesList = memberFavoritesService.selectByExample(memberFavoritesExample);
						if(memberFavoritesList != null && memberFavoritesList.size() > 0){
							dataMap.put("isExist", true);
						}else{
							dataMap.put("isExist", false);
						}
					}else{
						dataMap.put("isExist", false);
					}
					returnData.add(dataMap);
				}
			}

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * 方法描述 ：是否采集用户信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年3月9日 下午4:07:41 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getIsSaveMemberFavorites")
	@ResponseBody
	public ResponseMsg getIsSaveMemberFavorites(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			boolean isHasFavorite = false;
			if(!StringUtil.isBlank(memberId)){
				MemberFavoritesExample memberFavoritesExample = new MemberFavoritesExample();
				memberFavoritesExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
				.andDelFlagEqualTo("0").andSexIsNotNull();
				List<MemberFavorites> memberFavoritesList = memberFavoritesService.selectByExample(memberFavoritesExample);
				if(CollectionUtils.isEmpty(memberFavoritesList)){
					isHasFavorite = true;
				}
			} 
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,isHasFavorite);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * 方法描述 ：采集用户信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年3月8日 下午5:26:44 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/saveMemberFavorites")
	@ResponseBody
	public ResponseMsg saveMemberFavorites(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			if(!StringUtil.isBlank(memberId)){
				MemberFavorites memberFavorites = new MemberFavorites();
				String sex = null;
				String age = null;
				String styleIdGroup = null;
				String occupation = null;
				Date date = new Date();
				if(reqDataJson.containsKey("sex")){
					sex = reqDataJson.getString("sex");
				}
				if(reqDataJson.containsKey("age")){
					age = reqDataJson.getString("age");
				}
				if(reqDataJson.containsKey("styleIdGroup")){
					styleIdGroup = reqDataJson.getString("styleIdGroup");
				}
				if(reqDataJson.containsKey("occupation")){
					occupation = reqDataJson.getString("occupation");
				}
				MemberFavoritesExample memberFavoritesExample = new MemberFavoritesExample();
				memberFavoritesExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
				.andDelFlagEqualTo("0");
				memberFavoritesExample.setOrderByClause("id desc");
				List<MemberFavorites> memberFavoritesList = memberFavoritesService.selectByExample(memberFavoritesExample);
				if(CollectionUtils.isNotEmpty(memberFavoritesList)){
					memberFavorites = memberFavoritesList.get(0);
					memberFavorites.setSex(sex);
					memberFavorites.setAge(age);
					memberFavorites.setStyleIdGroup(styleIdGroup);
					memberFavorites.setOccupation(occupation);
					memberFavorites.setUpdateBy(Integer.valueOf(memberId));
					memberFavorites.setUpdateDate(date);
					memberFavoritesService.updateByPrimaryKeySelective(memberFavorites);
				}else{
					memberFavorites.setMemberId(Integer.valueOf(memberId));
					memberFavorites.setSex(sex);
					memberFavorites.setAge(age);
					memberFavorites.setStyleIdGroup(styleIdGroup);
					memberFavorites.setOccupation(occupation);
					memberFavorites.setCreateBy(memberId);
					memberFavorites.setCreateDate(date);
					memberFavorites.setDelFlag("0");
					memberFavoritesService.insertSelective(memberFavorites);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取男或女风格 集合
	 * @author  chenwf: 
	 * @date 创建时间：2018年3月9日 下午4:07:14 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getStyleList")
	@ResponseBody
	public ResponseMsg getStyleList(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"sex"};
			this.requiredStr(obj,request);
			String sex = reqDataJson.getString("sex");
			List<Map<String,Object>> styleList = new ArrayList<Map<String,Object>>();
			StyleExample styleExample = new StyleExample();
			styleExample.createCriteria().andStatusEqualTo("1").andSexEqualTo(sex).andDelFlagEqualTo("0");
			styleExample.setOrderByClause("ifnull(seq_no,99999) ASC,id DESC");
			List<Style> styles = styleService.selectByExample(styleExample);
			if(CollectionUtils.isNotEmpty(styles)){
				for (Style style : styles) {
					Map<String,Object> styleMap = new HashMap<String,Object>();
					styleMap.put("styleId", style.getId());
					styleMap.put("stylePic", StringUtil.getPic(style.getPic(), ""));
					styleMap.put("styleName", style.getName());
					styleList.add(styleMap);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,styleList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
}
