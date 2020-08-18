package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoExample;
import com.jf.entity.SysParamCfg;
import com.jf.service.ActivityAreaService;
import com.jf.service.AdInfoService;
import com.jf.service.SingleProductActivityService;

import net.sf.json.JSONObject;

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
			adInfoExample.setOrderByClause("seq_no");
			
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
					dataMap.put("id", id);
					dataMap.put("pic", StringUtil.getPic(pic, ""));
					dataMap.put("linkType", linkType);
					dataMap.put("linkValue", linkId == null || linkId == 0 ? linkUrl : linkId.toString());
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
			
			//广告类目(1 首页 2 运动 3 鞋包 4 服饰 5 生活)
			String catalog = "";
			String productTypeId = "";
			if(reqDataJson.containsKey("catalog") && !StringUtil.isBlank(reqDataJson.getString("catalog"))){
				catalog = reqDataJson.getString("catalog");
			}else if(reqDataJson.containsKey("productTypeId") && !StringUtil.isBlank(reqDataJson.getString("productTypeId"))){
				productTypeId = reqDataJson.getString("productTypeId");
			}else{
				throw new ArgException("异常错误数据");
			}
			if(StringUtil.isBlank(catalog)){
				if(StringUtil.isBlank(productTypeId)){
					catalog = "1";//首页
				}else{
					SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", productTypeId);
					if(sysParamCfg != null){
						catalog = sysParamCfg.getParamOrder();
					}
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
					Map<String,Object> dataMap = new HashMap<String,Object>();
					dataMap.put("id", id);
					dataMap.put("pic", StringUtil.getPic(pic, ""));
					dataMap.put("linkType", linkType);
					dataMap.put("linkValue", linkId == null || linkId == 0 ? linkUrl : linkId.toString());
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
