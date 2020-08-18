package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.ActivityArea;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.dto.AdDTO;
import com.jf.service.ActivityAreaService;
import com.jf.service.AdInfoService;
import com.jf.service.CommonService;
import com.jf.service.IndexPopupAdService;
import com.jf.service.SingleProductActivityService;
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
  * @date 创建时间：2017年4月25日 上午9:09:17 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class AdInfoController extends BaseController{
	
	@Resource
	private AdInfoService adInfoService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private IndexPopupAdService indexPopupAdService;
	@Resource
	private CommonService commonService;
	
	
	/**
	 * 
	 * 方法描述 ：获取首页弹窗广告（要是有弹窗红包的，不在调该接口）
	 * @author  chenwf: 
	 * @date 创建时间：2017年8月23日 下午2:19:49 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getIndexPopupAds")
	@ResponseBody
	public ResponseMsg getIndexPopupAds(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Map<String, Object> map = indexPopupAdService.getIndexPopupAds(reqPRM,reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取广告列表
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月16日 上午11:38:05 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getAdInfoList")
	@ResponseBody
	public ResponseMsg getAdInfoList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"type","catalog","position"};
			this.requiredStr(obj,request);
			String system = reqPRM.getString("system");
			Integer version = reqPRM.getInt("version");
			//广告类型(1 广告图 2 推荐活动)
			String type = reqDataJson.getString("type");
			//广告类目(1 首页 2 运动 3 鞋包 4 服饰 5 生活)
			String catalog = reqDataJson.getString("catalog");
			//广告位置(1 头部海报 2 广告位1 3 广告位2 9 推荐区域)
			String position = reqDataJson.getString("position");
			Date date = new Date();
			AdInfoExample adInfoExample = new AdInfoExample();
			//statu = 1 表示 上架
			adInfoExample.createCriteria().andStatusEqualTo("1").andTypeEqualTo(type)
										  .andCatalogEqualTo(catalog).andPositionEqualTo(position)
										  .andAutoUpDateLessThanOrEqualTo(date)
										  .andAutoDownDateGreaterThanOrEqualTo(date)
										  .andDelFlagEqualTo("0");
			if (Const.AD_CATALOG_START_AD.equals(catalog)) {
				adInfoExample.setOrderByClause("RAND()");
			}else{
				adInfoExample.setOrderByClause("ifnull(seq_no,99999)");
			}
			
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
			if(adInfoList != null && adInfoList.size() > 0){
				for (AdInfo adInfo : adInfoList) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					Integer id = adInfo.getId();
					String pic = adInfo.getPic();
					String linkType = adInfo.getLinkType();
					Integer linkId = adInfo.getLinkId();
					String linkUrl = adInfo.getLinkUrl();
					String androidPic = adInfo.getAndroidPic();
					String iosPic = adInfo.getIosPic();
					if(catalog.equals(Const.AD_CATALOG_START_AD)){
						if(Const.ANDROID.equals(system)){
							pic = androidPic;
						}else if(Const.IOS.equals(system)){
							pic = iosPic;
						}
					}
					String areaUrl = "";
					String activityAreaName = "";
					String activityAreaType = "";
					if(linkType.equals(Const.AD_ACTIVITY_AREA)){
						ActivityArea activityArea = activityAreaService.selectByPrimaryKey(linkId);
						if(!StringUtil.isBlank(activityArea.getTempletSuffix())){
							areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&memberId=";
						}
						if(!StringUtil.isBlank(activityArea.getName())){
							activityAreaName = activityArea.getName();
						}
						if(!StringUtil.isBlank(activityArea.getType())){
							activityAreaType = activityArea.getType();
						}
					}
					AdDTO adDTO = new AdDTO();
					adDTO.setAdId(id+"");
					adDTO.setLinkType(linkType);
					adDTO.setLinkId(linkId);
					adDTO.setLinkUrl(linkUrl);
					adDTO.setPic(pic);
					adDTO.setType("2");
					dataMap = commonService.buildAdMap(adDTO);
					
					if((Const.ANDROID.equals(system) && version <= 57) || (Const.IOS.equals(system) && version <= 58)){
						//android<=57,ios<=58(废弃)
						dataMap.put("id", id);
						dataMap.put("pic", StringUtil.getPic(pic, ""));
						dataMap.put("linkType", this.setStrByNull(linkType));
						dataMap.put("linkId", this.setStrByNull(linkId));
						dataMap.put("linkUrl", this.setStrByNull(linkUrl));
						dataMap.put("areaUrl", areaUrl);
						dataMap.put("activityAreaName", activityAreaName);
						dataMap.put("activityAreaType", activityAreaType);
					}
					
					
					returnData.add(dataMap);
				}
			}
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：根据商品类目获取广告图
	 * @author  chenwf: 
	 * @date 创建时间：2017年8月23日 下午2:19:49 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getAdByproductTypeId")
	@ResponseBody
	public ResponseMsg getAdByproductTypeId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			//广告类型(1 广告图 2 推荐活动)
			String type = reqDataJson.getString("type");
			//广告类目(1 首页 2 运动 3 鞋包 4 服饰 5 生活)
			String catalog = reqDataJson.getString("catalog");
			String system = reqPRM.getString("system");
			Integer version = reqPRM.getInt("version");
			if(!JsonUtils.isEmpty(reqDataJson, "catalog")){
				catalog = reqDataJson.getString("catalog");
			}else{
				//根据品类id查找对应的广告类目id
				if(!JsonUtils.isEmpty(reqDataJson, "productTypeId")){
					SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", reqDataJson.getString("productTypeId"));
					if(sysParamCfg != null){
						catalog = sysParamCfg.getParamOrder();
					}
				}else{
					catalog = "1";//首页
				}
			}
			Date date = new Date();
			AdInfoExample adInfoExample = new AdInfoExample();
			//statu = 1 表示 上架
			adInfoExample.createCriteria().andStatusEqualTo("1").andTypeEqualTo("1")
										  .andCatalogEqualTo(catalog)
										  .andAutoUpDateLessThanOrEqualTo(date)
										  .andAutoDownDateGreaterThanOrEqualTo(date)
										  .andDelFlagEqualTo("0");
			adInfoExample.setOrderByClause("seq_no");
			
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map<String,Object>> topPic = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> topPic1 = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> topPic2 = new ArrayList<Map<String,Object>>();
			List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
			if(CollectionUtils.isNotEmpty(adInfoList)){
				for (AdInfo adInfo : adInfoList) {
					//广告位置(1 头部海报 2 广告位1 3 广告位2 9 推荐区域)
					String position = adInfo.getPosition();
					Integer id = adInfo.getId();
					String pic = adInfo.getPic();
					String linkType = adInfo.getLinkType();
					Integer linkId = adInfo.getLinkId();
					String linkUrl = adInfo.getLinkUrl();
					String areaUrl = "";
					String activityAreaName = "";
					String activityAreaType = "";
					if(linkType.equals(Const.AD_ACTIVITY_AREA)){
						ActivityArea activityArea = activityAreaService.selectByPrimaryKey(linkId);
						if(!StringUtil.isBlank(activityArea.getTempletSuffix())){
							areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&memberId=";
						}
						if(!StringUtil.isBlank(activityArea.getName())){
							activityAreaName = activityArea.getName();
						}
						if(!StringUtil.isBlank(activityArea.getType())){
							activityAreaType = activityArea.getType();
						}
					}
					Map<String,Object> dataMap = new HashMap<String,Object>();
					AdDTO adDTO = new AdDTO();
					adDTO.setAdId(id+"");
					adDTO.setLinkType(linkType);
					adDTO.setLinkId(linkId);
					adDTO.setLinkUrl(linkUrl);
					adDTO.setPic(pic);
					adDTO.setType("2");
					dataMap = commonService.buildAdMap(adDTO);
					
					
					
					if((Const.ANDROID.equals(system) && version <= 57) || (Const.IOS.equals(system) && version <= 58)){
						//android<=57,ios<=58(废弃)
						dataMap.put("id", id);
						dataMap.put("pic", StringUtil.getPic(pic, ""));
						dataMap.put("linkType", this.setStrByNull(linkType));
						dataMap.put("linkId", this.setStrByNull(linkId));
						dataMap.put("linkUrl", this.setStrByNull(linkUrl));
						dataMap.put("areaUrl", areaUrl);
						dataMap.put("activityAreaName", activityAreaName);
						dataMap.put("activityAreaType", activityAreaType);
					}
					
					
					
					if(position.equals("1")){
						topPic.add(dataMap);
					}else if(position.equals("2")){
						topPic1.add(dataMap);
					}else if(position.equals("3")){
						topPic2.add(dataMap);
					}
				}
			}
			if(CollectionUtils.isEmpty(topPic1)){
				topPic2.clear();
			}
			if(CollectionUtils.isEmpty(topPic2)){
				topPic1.clear();
			}
			map.put("topPic", topPic);
			map.put("topPic1", topPic1);
			map.put("topPic2", topPic2);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
