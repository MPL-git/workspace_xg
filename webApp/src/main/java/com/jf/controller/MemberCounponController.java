package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberCouponCustomMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.CouponExchangeCode;
import com.jf.entity.CouponExchangeCodeExample;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.SysParamCfg;
import com.jf.entity.dto.MemberCouponCountDTO;
import com.jf.entity.dto.MemberCouponDTO;
import com.jf.service.ActivityAreaService;
import com.jf.service.CouponExchangeCodeService;
import com.jf.service.CouponService;
import com.jf.service.MemberCouponConverter;
import com.jf.service.MemberCouponService;
import com.jf.service.MemberInfoService;
import com.jf.vo.request.FindMemberCouponRequest;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月12日 下午2:39:16 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class MemberCounponController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(MemberCounponController.class);
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private ActivityAreaService activityAreaService;
	
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
	 * 创建时间：2019年07月09日 上午10:16:16
	 * 更新时间：2019-12-07
	 */
	@RequestMapping(value = "/api/y/getMemberCouponList")
	@ResponseBody
	public ResponseMsg getMemberCouponList(HttpServletRequest request){
        JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
        if (Const.WX_XCX.equalsIgnoreCase(reqPRM.optString("system"))) {
            FindMemberCouponRequest findRequest = appContext.getRequestAndValidate(FindMemberCouponRequest.class);
            FindMemberCouponResponse findResponse = memberCouponService.findMemberCoupon(findRequest, getMemberId(request));
            return ResponseMsg.success(findResponse);
        }

        JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
        Object[] obj = {"currentPage"};
        this.requiredStr(obj, request);
        Integer pageSize = Const.RETURN_SIZE_10;//页数
        reqDataJson.put("pageSize", pageSize);
        reqDataJson.put("memberId", getMemberId(request));
        Map<String, Object> map = memberCouponService.getMemberCouponList(reqPRM, reqDataJson);
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, pageSize, map);
	}
	
	/**
	 * 获取优惠券数量
	 */
	@RequestMapping(value = "/api/y/getMemberCouponCounts")
	@ResponseBody
	public ResponseMsg getMemberCouponCounts(HttpServletRequest request){
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		if (Const.WX_XCX.equalsIgnoreCase(reqPRM.optString("system"))) {
			MemberCouponCountDTO memberCouponCountDTO = memberCouponService.countMemberNotUsedCoupon(getMemberId(request));
			CountMemberCouponResponse response = memberCouponConverter.buildCountMemberCouponResponse(memberCouponCountDTO);
			return ResponseMsg.success(response);
		}

		Integer memberId = getMemberId(request);
		MemberCouponDTO memberCouponDto = memberCouponService.getMemberCouponCount(memberId);
		int unUsedCouponCount = memberCouponDto.getUnUsedCouponCount();
		int usedCouponCount = memberCouponDto.getUsedCouponCount();
		int expiredCouponCount = memberCouponDto.getExpiredCouponCount();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		for (int i = 1; i < 4; i++) {
			Map<String,Object> dataMap = new HashMap<>();
			int couponCount = 0;
			String name = "";
			if(i == 1){
				couponCount = unUsedCouponCount;
				name = "未使用";
			}else if(i == 2){
				couponCount = usedCouponCount;
				name = "已使用";
			}else if(i == 3){
				couponCount = expiredCouponCount;
				name = "已过期";
			}
			dataMap.put("type", i);
			dataMap.put("couponCount", couponCount);
			dataMap.put("name", name);
			dataList.add(dataMap);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataList", dataList);
		return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
	}
	
	/**
	 * 
	 * 方法描述 ：获取优惠券By活动会场id
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月28日 下午6:13:32 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getCouponByActivityAreaId")
	@ResponseBody
	public ResponseMsg getCouponByActivityAreaId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"activityAreaId"};
			this.requiredStr(obj,request);
			
			String memberId = getMemberIdStr(request);
			String activityAreaId = reqDataJson.getString("activityAreaId");
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			//依次表示会场id,删除标记,启用状态,优惠券类型,领取方式
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.valueOf(activityAreaId));
			List<Integer> couponIds = new ArrayList<Integer>();
			String preferentialType = activityArea.getPreferentialType();
			if(!StringUtil.isBlank(preferentialType) && preferentialType.equals("1")){
				String preferentialIdGroup = activityArea.getPreferentialIdGroup();
				if(!StringUtil.isBlank(preferentialIdGroup)){
					String[] preferentialIdGroups = preferentialIdGroup.split(",");
					for (String str : preferentialIdGroups) {
						couponIds.add(Integer.valueOf(str));
					}
				}
			}
			if(CollectionUtils.isNotEmpty(couponIds)){
				CouponExample couponExample = new CouponExample();
				couponExample.createCriteria().andActivityAreaIdEqualTo(Integer.valueOf(activityAreaId))
				.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIds).andRecTypeNotEqualTo("3");
				couponExample.setOrderByClause("money desc");
				couponExample.setLimitStart(0);
				couponExample.setLimitSize(3);
				List<Coupon> couponList = couponService.selectByExample(couponExample);
				if(couponList != null && couponList.size() >0){
					for (Coupon coupon : couponList) {
						//优惠券发行数量
						Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();
						//优惠券已领数量
						Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
						//优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
						String recLimitType = coupon.getRecLimitType() == null ? "" : coupon.getRecLimitType();
						//优惠券 限领数量
						Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
						if(grantQuantity > 0 && grantQuantity > recQuantity){
							Map<String,Object> couponMap = new HashMap<String,Object>();
							couponMap.put("couponId", coupon.getId());
							couponMap.put("couponName", coupon.getName());
							couponMap.put("money", coupon.getMoney());
							couponMap.put("minimum", coupon.getMinimum());
							couponMap.put("expiryBeginDate", coupon.getExpiryBeginDate());
							couponMap.put("expiryEndDate", coupon.getExpiryEndDate());
							couponMap.put("recType", coupon.getRecType());
							couponMap.put("needIntegral", coupon.getNeedIntegral() == null ? 0 : coupon.getNeedIntegral());
							couponMap.put("recLimitType", coupon.getRecLimitType());
							couponMap.put("recEach", coupon.getRecEach());
							
							//查询该优惠券是否背会员领取
							MemberCouponExample memberCouponExample = new MemberCouponExample();
							memberCouponExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
							.andCouponIdEqualTo(coupon.getId()).andDelFlagEqualTo("0");
							memberCouponExample.setOrderByClause("rec_date desc");
							List<MemberCoupon> memberCoupons = memberCouponService.selectByExample(memberCouponExample);
							Integer memberCouponCount = 0;
							boolean isReceive = true;
							if(CollectionUtils.isNotEmpty(memberCoupons)){
								memberCouponCount = memberCoupons.size();
								String recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyyMMdd");
								String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
								if(recLimitType.equals("1")){
									if(recDate.equals(currentDate)){
										isReceive = false;
									}else{
										isReceive = true;
									}
								}else if(recLimitType.equals("2")){
									if(memberCoupons.size() >= recEach){
										isReceive = false;
									}
								}else{
									isReceive = true;
								}
							}
							couponMap.put("memberCouponCount", memberCouponCount);
							couponMap.put("isReceive", isReceive);
							returnData.add(couponMap);
						}
					}
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
	 * 方法描述 ：领取优惠券(微商城使用)
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

            String memberId = getMemberIdStr(request);
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
                    Map<String, Object> coupon = memberCouponService.addReceiveCoupon(memberId, type, couponIdList.get(0), null, receiveType);
                    couponList.add(coupon);
                }else{
                    boolean isReceived = false;
                    for(Integer couponId : couponIdList){
                        try {
                            Map<String, Object> coupon = memberCouponService.addReceiveCoupon(memberId, type, couponId, null, receiveType);
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

                Map<String,Object> coupon = memberCouponService.addReceiveCoupon(memberId, type, couponExchangeCode.getCouponId(), couponExchangeCode, receiveType);
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
			
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			
			List<Map<String,Object>> couponDataList = new ArrayList<Map<String,Object>>();
			Integer memberId = getMemberId(request);//会员标识id
			MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
			String str = "";
			List<String> strList = new ArrayList<String>();
			if(memberInfo != null && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
				
				if(StringUtil.isEmpty(memberInfo.getMobile())||"0".equals(memberInfo.getmVerfiyFlag())){
					return new ResponseMsg("1003","未绑定手机号");
				}
				
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
			String memberId = getMemberIdStr(request);//会员标识id
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
			Integer memberId = getMemberId(request);
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
