package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.*;
import com.jf.controller.base.BaseController;
import com.jf.dao.MemberCouponCustomMapper;
import com.jf.entity.*;
import com.jf.entity.dto.MemberCouponCountDTO;
import com.jf.entity.dto.MemberCouponCountV2DTO;
import com.jf.entity.dto.MemberCouponDTO;
import com.jf.service.*;
import com.jf.vo.request.FindMemberCouponRequest;
import com.jf.vo.request.FindMemberCouponRequestV2;
import com.jf.vo.request.MemberIdRequest;
import com.jf.vo.response.CountMemberCouponResponse;
import com.jf.vo.response.FindMemberCouponResponse;
import com.jf.vo.response.ReceivedRedInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
  * 优惠券
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午4:08:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class MemberCouponController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(MemberCouponController.class);
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private CouponService couponService;
	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private ProductTypeService productTypeService;
    @Resource
    private CouponExchangeCodeService couponExchangeCodeService;
    @Autowired
    private MemberCouponCustomMapper memberCouponCustomMapper;
	@Autowired
	private AppContext appContext;
	@Autowired
	private MemberCouponConverter memberCouponConverter;
	
	/**
	 * 
	 * 方法描述 ：获取我的优惠券列表
	 * @author  chenwf: 
	 * @date 创建时间：2019年07月09日 上午10:16:16 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberCouponList")
	@ResponseBody
	public ResponseMsg getMemberCouponList(HttpServletRequest request){
		if (appContext.olderThan(65,66)) { //历史版本
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId", "currentPage"};
			this.requiredStr(obj, request);
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			reqDataJson.put("pageSize", pageSize);
			Map<String, Object> map = memberCouponService.getMemberCouponLists(reqPRM, reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, pageSize, map);
		}

		if (appContext.olderThan(66, 67)) { //改自需求号：1809 （2019-12-07）
			FindMemberCouponRequest findRequest = appContext.getRequestAndValidate(FindMemberCouponRequest.class);
			FindMemberCouponResponse findResponse = memberCouponService.findMemberCoupon(findRequest);
			return ResponseMsg.success(findResponse);
		}
		//新版：2020-03-04 ，需求号：1876
		FindMemberCouponRequestV2 findRequest = appContext.getRequestAndValidate(FindMemberCouponRequestV2.class);
		FindMemberCouponResponse findResponse = memberCouponService.findMemberCouponV2(findRequest);
		return ResponseMsg.success(findResponse);
	}
	
	
	/**
	 * 
	 * 方法描述 ：获取我的优惠券列表(20190708废弃)
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:16:16 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberCouponList20180809")
	@ResponseBody
	public ResponseMsg getMemberCouponList20180809(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","currentPage"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");//会员标识id
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = reqDataJson.getInt("currentPage");//页码
			String type = "1";//1未过期 2已使用 3已过期
			if(reqDataJson.containsKey("type") && !StringUtil.isBlank(reqDataJson.getString("type"))){
				type = reqDataJson.getString("type");
			}
			boolean isSvip = memberInfoService.isRealSVip(null, memberId);
			String source = "";
			Date date = new Date();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("memberId", memberId);
			params.put("type", type);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			List<MemberCouponDTO> MemberCouponDTOList= memberCouponService.getMemberCouponList(params);
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			if(MemberCouponDTOList != null &&MemberCouponDTOList.size() > 0){
				for (MemberCouponDTO memberCouponDTO : MemberCouponDTOList) {
					Map<String,Object> map = new HashMap<String,Object>();
					String rang = memberCouponDTO.getRang();
					String describeContent = "";
					String name = "";
					String minimum = memberCouponDTO.getMinimum();
					String money = memberCouponDTO.getMoney();
					String id = memberCouponDTO.getId();
					Date expiryBeginDate = memberCouponDTO.getExpiryBeginDate();
					Date expiryEndDate = memberCouponDTO.getExpiryEndDate();
					Integer activityAreaId = memberCouponDTO.getActivityAreaId();
					String templetSuffix = memberCouponDTO.getTempletSuffix();
					Integer activityId = memberCouponDTO.getActivityId();
					source =  this.setStrByNull(memberCouponDTO.getSource()).toString();
					String couponType = memberCouponDTO.getCouponType();
					String canSuperpose = memberCouponDTO.getCanSuperpose();
					String typeIds = memberCouponDTO.getTypeIds();
					String receiveType = memberCouponDTO.getReceiveType() == null ? "" : memberCouponDTO.getReceiveType();
					String areaUrl = ""; 
					String activityAreaName = "";
					boolean isCanGive = false;
					if(source.equals(Const.AD_ACTIVITY_AREA)){
						if(!StringUtil.isBlank(templetSuffix)){
							areaUrl = Config.getProperty("mUrl")+templetSuffix+"?activityAreaId="+memberCouponDTO.getActivityAreaId()+"&currentPage=0&pageSize=10&memberId=";
						}
						if(!StringUtil.isBlank(memberCouponDTO.getActivityAreaName())){
							activityAreaName = memberCouponDTO.getActivityAreaName();
						}
					}
					if(rang.equals("1")){//全平台
						if("1".equals(couponType)){
							if("1".equals(canSuperpose)){
								name = "可与品类券叠加";
							}else{
								name = "不可与品类券叠加";
							}
						}else if("2".equals(couponType)){
							if(!StringUtil.isBlank(typeIds)){
								ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(typeIds));
								name = "仅"+productType.getName()+"类目可以使用";
							}
						}
						describeContent = "限时抢购、新人专享、积分抵扣等不可用";
					}else if(rang.equals("4")){
						name = "商家券";
						describeContent = "(仅店铺可用)";
					}else{
						name = "专场券 "+memberCouponDTO.getActivityAreaName();
						describeContent = "(仅本场可用)";
					}
					if("1".equals(type) && isSvip){
						isCanGive = true;
					}
					map.put("id", id);
					map.put("expiryBeginDate", expiryBeginDate);
					map.put("expiryEndDate", expiryEndDate);
					map.put("currentDate", date.getTime());
					map.put("rang", rang);
					map.put("money", money);
					map.put("minimum", minimum);
					map.put("activityAreaId", activityAreaId);
					map.put("couponInfo", "满"+minimum+"减"+money);
					map.put("describeContent", describeContent);
					map.put("name", name);
					map.put("status", 1);
					map.put("activityId", activityId);
					map.put("mchtId", memberCouponDTO.getMchtId());
					map.put("source", source);
					map.put("areaUrl", areaUrl);
					map.put("activityAreaName", activityAreaName);
					map.put("couponType", couponType);
					map.put("receiveType", receiveType);
					map.put("isCanGive", isCanGive);
					returnData.add(map);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 获取优惠券数量
	 */
	@RequestMapping(value = "/api/y/getMemberCouponCounts")
	@ResponseBody
	public ResponseMsg getMemberCouponCounts(){
		MemberIdRequest request = appContext.getRequestAndValidate(MemberIdRequest.class);
		if (appContext.olderThan(65,66)) { //历史版本
			MemberCouponDTO memberCouponDto = memberCouponService.getMemberCouponCount(request.getMemberId());
			int unUsedCouponCount = memberCouponDto.getUnUsedCouponCount();
			int usedCouponCount = memberCouponDto.getUsedCouponCount();
			int expiredCouponCount = memberCouponDto.getExpiredCouponCount();
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for (int i = 1; i < 4; i++) {
				Map<String, Object> dataMap = new HashMap<>();
				int couponCount = 0;
				String name = "";
				if (i == 1) {
					couponCount = unUsedCouponCount;
					name = "未使用";
				} else if (i == 2) {
					couponCount = usedCouponCount;
					name = "已使用";
				} else if (i == 3) {
					couponCount = expiredCouponCount;
					name = "已过期";
				}
				dataMap.put("type", i);
				dataMap.put("couponCount", couponCount);
				dataMap.put("name", name);
				dataList.add(dataMap);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dataList", dataList);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
		}else if (appContext.olderThan(66,67)) { //改自需求号：1809 （2019-12-07）
			MemberCouponCountDTO memberCouponCountDTO = memberCouponService.countMemberNotUsedCoupon(request.getMemberId());
			CountMemberCouponResponse response = memberCouponConverter.buildCountMemberCouponResponse(memberCouponCountDTO);
			return ResponseMsg.success(response);
		}
		//新版：2020-03-04 ，需求号：1876
		MemberCouponCountV2DTO memberCouponCountV2DTO = memberCouponService.countMemberNotUsedCouponV2(request.getMemberId());
		CountMemberCouponResponse response = memberCouponConverter.buildCountMemberCouponResponseV2(memberCouponCountV2DTO);
		return ResponseMsg.success(response);
	}

	/**
	 * 
	 * 方法描述 ：新用户专享领取优惠券且展示
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月7日 上午11:28:13 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addCouponAndView")
	@ResponseBody
	public ResponseMsg addCouponAndView(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);
			
			List<Map<String,Object>> couponDataList = new ArrayList<Map<String,Object>>();
			Integer memberId = reqDataJson.getInt("memberId");//会员标识id
			List<Integer> couponIdList = new ArrayList<Integer>();
			List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg("APP_COUPON_ID");
			if(CollectionUtils.isNotEmpty(sysParamCfgs)){
				for (SysParamCfg sysParamCfg : sysParamCfgs) {
					if(!StringUtil.isBlank(sysParamCfg.getParamValue())){
						if(!StringUtil.isBlank(sysParamCfg.getParamValue())){
							couponIdList.add(Integer.valueOf(sysParamCfg.getParamValue()));
						}
					}
				}
			}
			
			if(CollectionUtils.isNotEmpty(couponIdList)){
				MemberCouponExample memberCouponExample = new MemberCouponExample();
				memberCouponExample.createCriteria().andCouponIdIn(couponIdList).andMemberIdEqualTo(memberId);
				int count = memberCouponService.countByExample(memberCouponExample);
				//已经领取过了不再让领了
				Date date = new Date();
				if(count <= 0){
					CouponExample couponExample = new CouponExample();
					couponExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIdList)
					.andRecBeginDateLessThanOrEqualTo(date).andRecEndDateGreaterThanOrEqualTo(date);
					couponExample.setOrderByClause("money desc");
					
					List<Coupon> couponList = couponService.selectByExample(couponExample);
					if(CollectionUtils.isNotEmpty(couponList)){
						for (Coupon coupon : couponList) {
							Map<String,Object> map = new HashMap<String,Object>();
							Integer couponId = coupon.getId();
							String expiryType = coupon.getExpiryType();
							Integer expiryDates = coupon.getExpiryDays();
							Date expiryBeginDate = coupon.getExpiryBeginDate();
							Date expiryEndDate = coupon.getExpiryEndDate();
							BigDecimal money = coupon.getMoney();
							BigDecimal minimum = coupon.getMinimum();
							String name = coupon.getName();
							if(expiryType.equals("2")){
								expiryBeginDate = date;
								expiryEndDate = DateUtil.addDay(date, expiryDates);
							}
							map.put("expiryBeginDate", DateUtil.getFormatDate(expiryBeginDate, "yyyy.MM.dd HH:mm"));
							map.put("expiryEndDate", DateUtil.getFormatDate(expiryEndDate, "yyyy.MM.dd HH:mm"));
							map.put("money", money);
							map.put("minimum", minimum);
							map.put("name", name);
							couponDataList.add(map);
							
							MemberCoupon memberCoupon = new MemberCoupon();
							memberCoupon.setCouponId(couponId);
							memberCoupon.setMemberId(memberId);
							memberCoupon.setRecDate(date);
							memberCoupon.setExpiryBeginDate(expiryBeginDate);
							memberCoupon.setExpiryEndDate(expiryEndDate);
							memberCoupon.setStatus("0");
							memberCoupon.setCreateBy(memberId);
							memberCoupon.setCreateDate(date);
							memberCoupon.setDelFlag("0");
							memberCoupon.setRemarks("新用户专享赠送的优惠券");
							memberCouponService.addMemberCoupon(memberCoupon);
						}
					}
				}
			}
			
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,couponDataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：新用户专享领取优惠券且展示
	 * @author  chenwf: (new)
	 * @date 创建时间：2017年7月7日 上午11:28:13 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addCouponAndView20170731")
	@ResponseBody
	public ResponseMsg addCouponAndView20170731(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","type"};
			this.requiredStr(obj,request);
			
			List<Map<String,Object>> couponDataList = new ArrayList<Map<String,Object>>();
			Integer memberId = reqDataJson.getInt("memberId");//会员标识id
			MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
			String str = "";
			List<String> strList = new ArrayList<String>();
			if(memberInfo != null && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
				//1:新用户专享领取红包入口
				//2:红包领取优惠券入口
				String type = reqDataJson.getString("type");
				List<Integer> couponIdList = new ArrayList<Integer>();
				List<Integer> oldCouponIdList = new ArrayList<Integer>();
				List<SysParamCfg> sysParamCfgs = new ArrayList<SysParamCfg>();
				String code = "APP_EXPIRED_COUPON_IDS";
				String couponIdsStr = "";
				sysParamCfgs = DataDicUtil.getSysParamCfg(code);
				if(CollectionUtils.isNotEmpty(sysParamCfgs)){//提取过期的优惠券，之前已领取过旧500大红包优惠券，新的也不能在领取了
					couponIdsStr = sysParamCfgs.get(0).getParamValue();
					if(!StringUtil.isBlank(couponIdsStr)){
						for (String couponIdStr : couponIdsStr.split(",")) {
							couponIdList.add(Integer.parseInt(couponIdStr));
							oldCouponIdList.add(Integer.parseInt(couponIdStr));
						}
					}
				}
				code = "APP_COUPON_ID_BY_RED";
				String remarks = "红包赠送优惠券";
				sysParamCfgs = DataDicUtil.getSysParamCfg(code);
				if(CollectionUtils.isNotEmpty(sysParamCfgs)){
					for (SysParamCfg sysParamCfg : sysParamCfgs) {
						if(!StringUtil.isBlank(sysParamCfg.getParamValue())){
							couponIdList.add(Integer.valueOf(sysParamCfg.getParamValue()));
						}
					}
				}
				
				if(CollectionUtils.isNotEmpty(couponIdList)){
					MemberCouponExample memberCouponExample = new MemberCouponExample();
					memberCouponExample.createCriteria().andCouponIdIn(couponIdList).andMemberIdEqualTo(memberId);
					int count = memberCouponService.countByExample(memberCouponExample);
					//没有领取过 才可以领取
					if(count <= 0){
						Date date = new Date();
						CouponExample couponExample = new CouponExample();
						couponExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIdList)
						.andRecBeginDateLessThanOrEqualTo(date).andRecEndDateGreaterThanOrEqualTo(date);
						couponExample.setOrderByClause("money desc");
						
						List<Coupon> couponList = couponService.selectByExample(couponExample);
						if(CollectionUtils.isNotEmpty(couponList)){
							for (Coupon coupon : couponList) {
								Map<String,Object> map = new HashMap<String,Object>();
								Integer couponId = coupon.getId();
								Integer recQuantity = coupon.getRecQuantity();
								String expiryType = coupon.getExpiryType();
								Integer expiryDates = coupon.getExpiryDays();
								Date expiryBeginDate = coupon.getExpiryBeginDate();
								Date expiryEndDate = coupon.getExpiryEndDate();
								BigDecimal money = coupon.getMoney();
								BigDecimal minimum = coupon.getMinimum();
								Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
								String name = coupon.getName();
								if(expiryType.equals("2")){
									expiryBeginDate = date;
									expiryEndDate = DateUtil.addDay(date, expiryDates);
								}
								if(oldCouponIdList.contains(couponId)){
									continue;
								}
								if(type.equals("1")){
									map.put("expiryBeginDate", DateUtil.getFormatDate(expiryBeginDate, "yyyy.MM.dd HH:mm"));
									map.put("expiryEndDate", DateUtil.getFormatDate(expiryEndDate, "yyyy.MM.dd HH:mm"));
									map.put("money", money);
									map.put("minimum", minimum);
									map.put("name", name);
									map.put("recEach", recEach);
									couponDataList.add(map);
									MemberCoupon memberCoupon = new MemberCoupon();
									memberCoupon.setCouponId(couponId);
									memberCoupon.setMemberId(memberId);
									memberCoupon.setRecDate(date);
									memberCoupon.setExpiryBeginDate(expiryBeginDate);
									memberCoupon.setExpiryEndDate(expiryEndDate);
									memberCoupon.setStatus("0");
									memberCoupon.setCreateBy(memberId);
									memberCoupon.setCreateDate(date);
									memberCoupon.setDelFlag("0");
									memberCoupon.setRemarks(remarks);
									memberCouponService.addMemberCoupon(memberCoupon);
								}else if(type.equals("2")){
									if(recEach != 0){
										map.put("expiryBeginDate", DateUtil.getFormatDate(expiryBeginDate, "yyyy.MM.dd HH:mm"));
										map.put("expiryEndDate", DateUtil.getFormatDate(expiryEndDate, "yyyy.MM.dd HH:mm"));
										map.put("money", money);
										map.put("minimum", minimum);
										map.put("name", name);
										map.put("recEach", recEach);
										couponDataList.add(map);
										for (int i = 0; i < recEach; i++) {
											str = memberId.toString()
													+","+ couponId.toString()
													+","+ DateUtil.getFormatDate(expiryBeginDate, "yyyy-MM-dd HH:mm:ss")
													+","+ DateUtil.getFormatDate(expiryEndDate, "yyyy-MM-dd HH:mm:ss")
													+","+ memberId.toString();
											strList.add(str);		
											/*Map<String,Object> dataMap = new HashMap<String,Object>();
											dataMap.put("couponId", couponId);
											dataMap.put("memberId", memberId);
											dataMap.put("recDate", date);
											dataMap.put("expiryBeginDate", expiryBeginDate);
											dataMap.put("expiryEndDate", expiryEndDate);
											dataMap.put("status", "0");
											dataMap.put("createBy", memberId);
											dataMap.put("createDate", date);
											dataMap.put("delFlag", "0");
											dataMap.put("remarks", remarks);*/
											/*MemberCoupon memberCoupon = new MemberCoupon();
											memberCoupon.setCouponId(couponId);
											memberCoupon.setMemberId(memberId);
											memberCoupon.setRecDate(date);
											memberCoupon.setExpiryBeginDate(expiryBeginDate);
											memberCoupon.setExpiryEndDate(expiryEndDate);
											memberCoupon.setStatus("0");
											memberCoupon.setCreateBy(memberId);
											memberCoupon.setCreateDate(date);
											memberCoupon.setDelFlag("0");
											memberCoupon.setRemarks(remarks);
											memberCouponService.addMemberCoupon(memberCoupon);
											list.add(memberCoupon);*/
										}
									}
								}
								coupon.setRecQuantity(recQuantity+recEach);
								couponService.updateByModel(coupon);
							}
						}
						if(CollectionUtils.isNotEmpty(strList)){
							String insertData = StringUtils.join(strList, "|");
							memberCouponService.addMemberCouponBatch(insertData);
						}
					}
				}
			}
			
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,couponDataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：新用户专享领取优惠券且展示
	 * @author  chenwf: (new)
	 * @date 创建时间：2017年7月7日 上午11:28:13 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getIsReceivedRed")
	@ResponseBody
	public ResponseMsg getIsReceivedRed(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			//1:新用户专享领取红包入口
			//2:红包领取优惠券入口
			String type = reqDataJson.getString("type");
			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			//领取 true 红包图片不展示
			//是否展示红包 true 不展示 false 展示
			boolean isReceivedRed = true;
			if(type.equals("2")){
				ReceivedRedInfo receivedRedInfo = memberCouponService.isReceivedRed(StringUtil.isBlank(memberId) ? null : Integer.valueOf(memberId));
				isReceivedRed = receivedRedInfo.isReceived();
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,isReceivedRed);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：商品活动会场领取优惠券
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月28日 下午6:24:50 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addReceiveCoupon")
	@ResponseBody
	public ResponseMsg addReceiveCoupon(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"memberId"};
			this.requiredStr(obj, request);

            String system = reqPRM.getString("system");
            Integer version = reqPRM.getInt("version");
            String memberId = reqDataJson.getString("memberId");
            String type = reqDataJson.optString("type", "0");
            if(!type.equals("0") && !type.equals("1")){
                throw new BizException("不支持的type");
            }
            String receiveType = reqDataJson.optString("receiveType");
            if(StrKit.notBlank(receiveType) && !Const.valitateCouponReceiveType(receiveType)){
                throw new BizException("不支持的receiveType");
            }

            List<Map<String, Object>> couponList = new ArrayList<>();
            if(type.equals("0")){
                //领取优惠券
                String couponIds = reqDataJson.getString("couponId");
                if(StringUtil.isBlank(couponIds)){
                    throw new ArgException("couponId不能为空");
                }

                List<Integer> couponIdList = StrKit.strToList(couponIds, Integer.class);
                if(couponIdList.size() == 1){
                    Map<String, Object> coupon = memberCouponService.addReceiveCoupon(memberId, type, couponIdList.get(0), null, receiveType, system, version);
                    couponList.add(coupon);
                }else{
                    boolean isReceived = false;
                    for(Integer couponId : couponIdList){
                        try {
                            Map<String, Object> coupon = memberCouponService.addReceiveCoupon(memberId, type, couponId, null, receiveType, system, version);
                            couponList.add(coupon);
                            isReceived = true;
                        }catch(Exception e) {
                            logger.warn("{}领取优惠券{}失败",  memberId, couponId);
                        }
                    }

                    if(!isReceived){
                        throw new ArgException("领取优惠券失败");
                    }
                }



            }else if(type.equals("1")){
                //游离码兑换
                String code = reqDataJson.getString("code");
                if(StringUtil.isBlank(code)){
                    throw new ArgException("请输入兑换码");
                }

                //游离码兑换
                CouponExchangeCode couponExchangeCode = null;
                CouponExchangeCodeExample couponExchangeCodeExample = new CouponExchangeCodeExample();
                couponExchangeCodeExample.createCriteria().andCodeEqualTo(code);
                List<CouponExchangeCode> couponExchangeCodes = couponExchangeCodeService.selectByExample(couponExchangeCodeExample);
                if(CollectionUtil.isNotEmpty(couponExchangeCodes)){
                    couponExchangeCode = couponExchangeCodes.get(0);
                    String isExchange = couponExchangeCode.getIsExchange();
                    String exchangeLimit = couponExchangeCode.getExchangeLimit() == null ? "" : couponExchangeCode.getExchangeLimit();
                    if(isExchange.equals("1")){
                        throw new ArgException("兑换码已被使用");
                    }
                    if(exchangeLimit.equals("1")){
                        //首次兑换
                        int exchangeCouponCount = memberCouponCustomMapper.getExchangeCouponCount(Integer.valueOf(memberId));
                        if(exchangeCouponCount > 0){
                            throw new ArgException("该优惠券只适用于新用户");
                        }
                    }
                }else{
                    throw new ArgException("请输入正确的兑换码");
                }

                Map<String,Object> coupon = memberCouponService.addReceiveCoupon(memberId, type, couponExchangeCode.getCouponId(), couponExchangeCode, receiveType, system, version);
                couponList.add(coupon);
            }

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("isCanReceive", true);
            map.put("mark", true);
            map.put("integral", "");
            map.put("content", "领取成功");
            map.put("couponList", couponList);
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
	 * 方法描述 ：SVIP(赠送好友优惠券)
	 * @author  chenwf: 
	 * @date 创建时间：2019年4月20日 上午10:16:16 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/giveMemberCouponBySvip")
	@ResponseBody
	public ResponseMsg giveMemberCouponBySvip(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"mobile","memberCouponId","memberId"};
			this.requiredStr(obj,request);
			String mobile = reqDataJson.getString("mobile");
			Integer memberCouponId = reqDataJson.getInt("memberCouponId");
			Integer memberId = reqDataJson.getInt("memberId");
			memberCouponService.giveMemberCouponBySvip(mobile,memberCouponId,memberId,request);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
