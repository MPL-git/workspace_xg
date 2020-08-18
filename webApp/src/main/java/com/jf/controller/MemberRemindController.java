package com.jf.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jf.entity.VideoCustom;
import com.jf.vo.request.PageRequest;
import com.jf.vo.response.VideoCollectView;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindCustom;
import com.jf.entity.MemberRemindExample;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.SysParamCfg;
import com.jf.service.MemberRemindService;
import com.jf.service.ProductItemService;

import net.sf.json.JSONObject;

/**
  * 开售提醒
  * @author  chenwf: 
  * @date 创建时间：2017年5月8日 下午7:49:15 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class MemberRemindController extends BaseController{
	
	@Resource
	private MemberRemindService memberRemindService;
	
	@Resource
	private ProductItemService productItemService;
	
	/**
	 * 
	 * 方法描述 ：添加开售提醒
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月8日 下午7:40:43 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addRemindSale")
	@ResponseBody
	public ResponseMsg addRemindSale(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"remindType","remindId"};
			this.requiredStr(obj,request);
			
			Integer memberId = getMemberId(request);//会员id
			//1 活动开售 2 商品开售
			Integer remindId = reqDataJson.getInt("remindId");
			String remindType = reqDataJson.getString("remindType");
			
			MemberRemindExample remindExample = new MemberRemindExample();
			remindExample.createCriteria().andMemberIdEqualTo(memberId).andRemindTypeEqualTo(remindType).andRemindIdEqualTo(remindId).andDelFlagEqualTo("0");
			List<MemberRemind> reminds = memberRemindService.selectByExample(remindExample);
			if(CollectionUtils.isEmpty(reminds)){
				MemberRemind memberRemind = new MemberRemind();
				memberRemind.setMemberId(memberId);
				memberRemind.setCreateBy(memberId);
				memberRemind.setCreateDate(new Date());
				memberRemind.setDelFlag("0");
				memberRemind.setRemindId(remindId);
				memberRemind.setRemindType(remindType);
				memberRemindService.insertSelective(memberRemind);
			}else{
				throw new ArgException("已收藏");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/y/deleteRemindSale")
	@ResponseBody
	public ResponseMsg deleteRemindSale(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"remindId","type"};
			this.requiredStr(obj,request);
			
			Integer memberId = getMemberId(request);
			Integer remindId = reqDataJson.getInt("remindId");
			String type = "";
			if(reqDataJson.containsKey("type")){
				type = reqDataJson.getString("type");
			}
			MemberRemindExample memberRemindExample = new MemberRemindExample();
			MemberRemindExample.Criteria criteria = memberRemindExample.createCriteria();
			criteria.andMemberIdEqualTo(memberId).andRemindIdEqualTo(remindId);
			if(!StringUtil.isBlank(type)){
				criteria.andRemindTypeEqualTo(type);
			}
			memberRemindService.deleteByExample(memberRemindExample);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
//	/**
//	 * 
//	 * 方法描述 ：获取会员开售提醒列表(废弃)
//	 * @author  chenwf: 
//	 * @date 创建时间：2017年5月9日 下午2:47:50 
//	 * @version
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/api/y/getRemindSaleList")
//	@ResponseBody
//	public ResponseMsg getRemindSaleList(HttpServletRequest request){
//		try {
//			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
//			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
//			
//			Object[] obj = {"remindType","pageSize","currentPage"};
//			this.requiredStr(obj,request);
//			
//			Integer memberId = getMemberId(request);//会员id
//			//1 活动开售 2 商品开售
//			//Integer remindId = reqDataJson.getInt("remindId");
//			String remindType = reqDataJson.getString("remindType");
//			Integer pageSize = Integer.valueOf(reqDataJson.getString("pageSize"));//页数
//			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
//			
//			if(currentPage == null){
//				currentPage = 0;
//			}
//			if(pageSize == null){
//				pageSize = 5;
//			}
//			Map<String,Object> params = new HashMap<String,Object>();
//			params.put("memberId", memberId);
//			params.put("remindType", remindType);
//			params.put("pageSize", pageSize);
//			params.put("currentPage", currentPage*pageSize);
//			MemberRemindExample memberRemindExample = new MemberRemindExample();
//			Map<String,Object> dataMap = new HashMap<String,Object>();
//			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
//			Date date = new Date();
//			//当天
//			String currentDay = DateUtil.getFormatDate(date, "yyyy-MM-dd");
//			Map<String,Object> map = null;
//			if(remindType.equals("2")){
//				memberRemindExample.createCriteria().andRemindTypeEqualTo(remindType).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
//				/*Integer count = memberRemindService.countByExample(memberRemindExample);
//				Page page = new Page(currentPage, pageSize, count);*/
//				List<MemberRemindCustom> memberRemindList = memberRemindService.getProductRemindList(params);
//				if(memberRemindList != null && memberRemindList.size() > 0){
//					for (MemberRemindCustom memberRemindCustom : memberRemindList) {
//						map = new HashMap<String,Object>();
//						map.put("id", memberRemindCustom.getId());
//						map.put("productId", memberRemindCustom.getRemindId());
//						map.put("productName", memberRemindCustom.getProductName());
//						map.put("activityBeginTime", memberRemindCustom.getActivityBeginTime());
//						map.put("activityEndTime", memberRemindCustom.getActivityEndTime());
//						ProductItemExample productItemExample = new ProductItemExample();
//						productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(memberRemindCustom.getRemindId());
//						productItemExample.setOrderByClause("sale_price desc");
//						List<ProductItem> productItemList = productItemService.selectByExample(productItemExample);
//						if(productItemList != null && productItemList.size() > 0){
//							ProductItem productItem = productItemList.get(0);
//							double tagPrice = Double.valueOf(productItem.getTagPrice().toString());
//							double salePrice = Double.valueOf(productItem.getSalePrice().toString());
//							double discount = new BigDecimal((float)salePrice/tagPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//							map.put("pic", StringUtil.getPic(productItem.getPic(), "S"));
//							map.put("salePrice", salePrice);
//							map.put("tagPrice", tagPrice);
//							map.put("discount", discount);
//						}
//						returnData.add(map);
//					}
//				}else{
//					dataMap.put("arrayData", returnData);
//				}
//				dataMap.put("totalPage", "");
//			}else if(remindType.equals("1")){
//				/*memberRemindExample.createCriteria().andRemindTypeEqualTo(remindType).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
//				Integer count = memberRemindService.countByExample(memberRemindExample);
//				Page page = new Page(currentPage, pageSize, count);*/
//				
//				List<MemberRemindCustom> memberRemindList = memberRemindService.getActivityRemindList(params);
//				if(memberRemindList != null && memberRemindList.size() > 0){
//					for (MemberRemindCustom memberRemindCustom : memberRemindList) {
//						map = new HashMap<String, Object>();
//						String source = memberRemindCustom.getSource();
//						String logo = "";
//						String entryPic = "";
//						String areaUrl = "";
//						String name = "";
//						String code = "";
//						String activityAreaType = "";
//						if(source.equals("1")){
//							//会场
//							if(memberRemindCustom.getActivityAreaType().equals("1")){
//								code = "APP_ACTIVITY_BRAND";
//							}else{
//								code = "APP_ACTIVITY_SINGLE_PRODUCT";
//							}
//							List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg(code);
//							if(CollectionUtils.isNotEmpty(sysParamCfgs)){
//								logo = sysParamCfgs.get(0).getParamValue();
//								if(!StringUtil.isBlank(logo)){
//									logo = FileUtil.getImageServiceUrl()+logo;
//								}
//							}
//							if(!StringUtil.isBlank(memberRemindCustom.getTempletSuffix())){
//								areaUrl = Config.getProperty("mUrl")+memberRemindCustom.getTempletSuffix()+"?activityAreaId="+memberRemindCustom.getActivityAreaId()+"&currentPage=0&pageSize=10&memberId=";
//							}
//							if(!StringUtil.isBlank(memberRemindCustom.getActivityAreaName())){
//								name = memberRemindCustom.getActivityAreaName();
//							}
//							if(!StringUtil.isBlank(memberRemindCustom.getActivityAreaType())){
//								activityAreaType = memberRemindCustom.getActivityAreaType();
//							}
//							if(!StringUtil.isBlank(memberRemindCustom.getEntryPic())){
//								entryPic = FileUtil.getImageServiceUrl()+memberRemindCustom.getActivityAreaEntryPic();
//							}
//						}else{
//							//活动
//							if(!StringUtil.isBlank(memberRemindCustom.getLogo())){
//								logo = FileUtil.getImageServiceUrl()+memberRemindCustom.getLogo();
//							}
//							if(!StringUtil.isBlank(memberRemindCustom.getEntryPic())){
//								entryPic = FileUtil.getImageServiceUrl()+memberRemindCustom.getEntryPic();
//							}
//							name = memberRemindCustom.getActivityName();
//						}
//						
//						String activityBeginTime = "";
//						String currentBeginTime = DateUtil.getFormatDate(memberRemindCustom.getActivityBeginTime(), "yyyy-MM-dd");
//						if(currentBeginTime.equals(currentDay)){
//							activityBeginTime = DateUtil.getFormatDate(memberRemindCustom.getActivityBeginTime(), "HH:mm");
//						}else{
//							activityBeginTime = DateUtil.getFormatDate(memberRemindCustom.getActivityBeginTime(), "MM-dd");
//						}
//						
//						map.put("id", memberRemindCustom.getId());
//						map.put("activityId", memberRemindCustom.getActivityId());
//						map.put("logo", logo);
//						map.put("entryPic", entryPic);
//						map.put("activityAreaId", memberRemindCustom.getActivityAreaId());
//						map.put("activityName", name);
//						map.put("activityBeginTime", activityBeginTime);
//						map.put("source", source);
//						map.put("areaUrl", areaUrl);
//						map.put("activityAreaType", activityAreaType);
//						map.put("benefitPoint", memberRemindCustom.getBenefitPoint() == null ? "" : memberRemindCustom.getBenefitPoint());
//						returnData.add(map);
//					}
//					dataMap.put("totalPage", "");
//				}
//			}else if(remindType.equals("3")){	
//				params.put("pageSize", pageSize);
//				params.put("currentPage", currentPage*pageSize);
//				List<MemberRemindCustom> reminds = memberRemindService.getMchtShopList(params);
//				if(CollectionUtils.isNotEmpty(reminds)){
//					for (MemberRemindCustom memberRemind : reminds) {
//						map = new HashMap<String,Object>();
//						map.put("mchtId", memberRemind.getRemindId());
//						map.put("shopName", memberRemind.getShopName());
//						map.put("shopLogo", StringUtil.getPic(memberRemind.getShopLogo(), ""));
//						returnData.add(map);
//					}
//				}
//			}else{
//				throw new ArgException("开售提醒类型"+remindType+"不存在");
//			}
//			dataMap.put("arrayData", returnData);
//			dataMap.put("currentTime", date);
//			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataMap);
//		} catch (ArgException args){
//			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
//		}
//	}
	
	/**
	 * 
	 * 方法描述 ：开售提现列表
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月28日 下午2:08:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getRemindSaleRecordList")
	@ResponseBody
	public ResponseMsg getRemindSaleRecordList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"remindType","currentPage"};
			this.requiredStr(obj,request);
			
			Integer memberId = getMemberId(request);//会员id
			//1 活动开售 2 商品开售 3 店铺 4 视频
			String remindType = reqDataJson.getString("remindType");
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("memberId", memberId);
			params.put("remindType", remindType);
			
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			Date date = new Date();
			//当天
			String currentDay = DateUtil.getFormatDate(date, "yyyy-MM-dd");
			Map<String,Object> map = null;
			if(remindType.equals("2")){
				params.put("pageSize", pageSize);
				params.put("currentPage", currentPage*pageSize);
				List<MemberRemindCustom> memberRemindList = memberRemindService.getProductCollectionList(params);
				if(memberRemindList != null && memberRemindList.size() > 0){
					for (MemberRemindCustom memberRemindCustom : memberRemindList) {
						map = new HashMap<String,Object>();
						map.put("id", memberRemindCustom.getId());
						map.put("productId", memberRemindCustom.getRemindId());
						map.put("productName", memberRemindCustom.getProductName());
						ProductItemExample productItemExample = new ProductItemExample();
						productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(memberRemindCustom.getRemindId());
						productItemExample.setOrderByClause("sale_price desc");
						List<ProductItem> productItemList = productItemService.selectByExample(productItemExample);
						if(productItemList != null && productItemList.size() > 0){
							ProductItem productItem = productItemList.get(0);
							double tagPrice = Double.valueOf(productItem.getTagPrice().toString());
							double salePrice = Double.valueOf(productItem.getSalePrice().toString());
							double discount = new BigDecimal((float)salePrice/tagPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							map.put("pic", StringUtil.getPic(productItem.getPic(), "S"));
							map.put("salePrice", salePrice);
							map.put("tagPrice", tagPrice);
							map.put("discount", discount);
						}
						returnData.add(map);
					}
				}
			}else if(remindType.equals("1")){
				params.put("pageSize", pageSize);
				params.put("currentPage", currentPage*pageSize);
				List<MemberRemindCustom> memberRemindList = memberRemindService.getActivityRemindList(params);
				if(memberRemindList != null && memberRemindList.size() > 0){
					for (MemberRemindCustom memberRemindCustom : memberRemindList) {
						map = new HashMap<String, Object>();
						String source = memberRemindCustom.getSource();
						String logo = "";
						String entryPic = "";
						String areaUrl = "";
						String name = "";
						String code = "";
						String activityAreaType = "";
						if(source.equals("1")){
							//会场
							if(memberRemindCustom.getActivityAreaType().equals("1")){
								code = "APP_ACTIVITY_BRAND";
							}else{
								code = "APP_ACTIVITY_SINGLE_PRODUCT";
							}
							List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg(code);
							if(CollectionUtils.isNotEmpty(sysParamCfgs)){
								logo = sysParamCfgs.get(0).getParamValue();
								if(!StringUtil.isBlank(logo)){
									logo = FileUtil.getImageServiceUrl()+logo;
								}
							}
							if(!StringUtil.isBlank(memberRemindCustom.getTempletSuffix())){
								areaUrl = Config.getProperty("mUrl")+memberRemindCustom.getTempletSuffix()+"?activityAreaId="+memberRemindCustom.getActivityAreaId()+"&currentPage=0&pageSize=10&memberId=";
							}
							if(!StringUtil.isBlank(memberRemindCustom.getActivityAreaName())){
								name = memberRemindCustom.getActivityAreaName();
							}
							if(!StringUtil.isBlank(memberRemindCustom.getActivityAreaType())){
								activityAreaType = memberRemindCustom.getActivityAreaType();
							}
							if(!StringUtil.isBlank(memberRemindCustom.getEntryPic())){
								entryPic = FileUtil.getImageServiceUrl()+memberRemindCustom.getActivityAreaEntryPic();
							}
						}else{
							//活动
							if(!StringUtil.isBlank(memberRemindCustom.getLogo())){
								logo = FileUtil.getImageServiceUrl()+memberRemindCustom.getLogo();
							}
							if(!StringUtil.isBlank(memberRemindCustom.getEntryPic())){
								entryPic = FileUtil.getImageServiceUrl()+memberRemindCustom.getEntryPic();
							}
							name = memberRemindCustom.getActivityName();
						}
						
						String activityBeginTime = "";
						String currentBeginTime = DateUtil.getFormatDate(memberRemindCustom.getActivityBeginTime(), "yyyy-MM-dd");
						if(currentBeginTime.equals(currentDay)){
							activityBeginTime = DateUtil.getFormatDate(memberRemindCustom.getActivityBeginTime(), "HH:mm");
						}else{
							activityBeginTime = DateUtil.getFormatDate(memberRemindCustom.getActivityBeginTime(), "MM-dd");
						}
						
						map.put("id", memberRemindCustom.getId());
						map.put("activityId", memberRemindCustom.getActivityId());
						map.put("logo", logo);
						map.put("entryPic", entryPic);
						map.put("activityAreaId", memberRemindCustom.getActivityAreaId());
						map.put("activityName", name);
						map.put("activityBeginTime", activityBeginTime);
						map.put("source", source);
						map.put("areaUrl", areaUrl);
						map.put("activityAreaType", activityAreaType);
						map.put("benefitPoint", memberRemindCustom.getBenefitPoint() == null ? "" : memberRemindCustom.getBenefitPoint());
						returnData.add(map);
					}
				}
			}else if(remindType.equals("3")){	
				params.put("pageSize", pageSize);
				params.put("currentPage", currentPage*pageSize);
				List<MemberRemindCustom> reminds = memberRemindService.getMchtShopList(params);
				if(CollectionUtils.isNotEmpty(reminds)){
					for (MemberRemindCustom memberRemind : reminds) {
						map = new HashMap<String,Object>();
						map.put("mchtId", memberRemind.getRemindId());
						map.put("shopName", memberRemind.getShopName());
						map.put("shopLogo", StringUtil.getPic(memberRemind.getShopLogo(), ""));
						returnData.add(map);
					}
				}
			}else if("4".equals(remindType)){ //视频收藏列表查询
				List<VideoCustom> videoCustoms = memberRemindService.findVideoCollect(memberId, new PageRequest(currentPage, pageSize));
				List<VideoCollectView> viewList = memberRemindService.toVideoCollectViewList(videoCustoms);
				dataMap.put("arrayData", viewList);
				dataMap.put("currentTime", date);
				return ResponseMsg.success(dataMap);
			}else{
				throw new ArgException("开售提醒类型"+remindType+"不存在");
			}
			dataMap.put("arrayData", returnData);
			dataMap.put("currentTime", date);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 我的收藏--店铺--屏蔽/接收动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateMchtShieldingDynamics")
	@ResponseBody
	public ResponseMsg updateShieldingDynamics(HttpServletRequest request){
		try {
			Map<String,String> map = new HashMap<String,String>();
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","remindId"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");
			Integer remindId = reqDataJson.getInt("remindId");
			String type = "";
			if(reqDataJson.containsKey("type")){
				type = reqDataJson.getString("type");
			}
			MemberRemindExample memberRemindExample = new MemberRemindExample();
			MemberRemindExample.Criteria criteria = memberRemindExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andRemindIdEqualTo(remindId);
			if(!StringUtil.isBlank(type)){
				criteria.andRemindTypeEqualTo(type);
			}
			List<MemberRemind> memberRemindList = memberRemindService.selectByExample(memberRemindExample);
			if(memberRemindList!=null && memberRemindList.size()>0){
				MemberRemind memberRemind = memberRemindList.get(0);
				if(memberRemind.getShieldingDynamics().equals("0")){
					memberRemind.setShieldingDynamics("1");
				}else{
					memberRemind.setShieldingDynamics("0");
				}
				memberRemind.setUpdateDate(new Date());
				memberRemind.setUpdateBy(memberId);
				memberRemindService.updateByPrimaryKeySelective(memberRemind);
				map.put("shieldingDynamics", memberRemind.getShieldingDynamics());
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 会员是否收藏了该店铺及屏蔽动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/hasCollectionMcht")
	@ResponseBody
	public ResponseMsg hasCollectionMcht(HttpServletRequest request){
		try {
			Map<String,String> map = new HashMap<String,String>();
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","remindId"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");
			Integer remindId = reqDataJson.getInt("remindId");
			String type = "";
			if(reqDataJson.containsKey("type")){
				type = reqDataJson.getString("type");
			}
			MemberRemindExample memberRemindExample = new MemberRemindExample();
			MemberRemindExample.Criteria criteria = memberRemindExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andRemindIdEqualTo(remindId);
			if(!StringUtil.isBlank(type)){
				criteria.andRemindTypeEqualTo(type);
			}
			List<MemberRemind> memberRemindList = memberRemindService.selectByExample(memberRemindExample);
			if(memberRemindList!=null && memberRemindList.size()>0){
				MemberRemind memberRemind = memberRemindList.get(0);
				map.put("hasCollectionMcht", "1");
				map.put("shieldingDynamics", memberRemind.getShieldingDynamics());
			}else{
				map.put("hasCollectionMcht", "0");
				map.put("shieldingDynamics", "0");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
