package com.jf.service;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.MapHelper;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberCouponCustomMapper;
import com.jf.dao.MemberCouponMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.CouponExchangeCode;
import com.jf.entity.CouponExchangeCodeExample;
import com.jf.entity.FullCut;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullGive;
import com.jf.entity.MchtInfo;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponCustom;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.MemberCouponLog;
import com.jf.entity.MemberCouponLogExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.Product;
import com.jf.entity.ProductType;
import com.jf.entity.SysParamCfg;
import com.jf.entity.dto.AdDTO;
import com.jf.entity.dto.MemberCouponCountDTO;
import com.jf.entity.dto.MemberCouponDTO;
import com.jf.vo.request.FindMemberCouponRequest;
import com.jf.vo.response.FindMemberCouponResponse;
import com.jf.vo.response.ReceivedRedInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author chenwf:
 * @date 创建时间：2017年9月5日 下午2:53:13
 * @version 1.0
 * @parameter
 * @return
 */
@Service
@Transactional
public class MemberCouponService extends BaseService<MemberCoupon, MemberCouponExample> {

	private static Logger logger = LoggerFactory.getLogger(MemberCouponService.class);

	@Autowired
	private MemberCouponMapper memberCouponMapper;
	@Autowired
	private MemberCouponCustomMapper memberCouponCustomMapper;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private CouponService couponService;
	@Resource
	private CouponExchangeCodeService couponExchangeCodeService;
	@Resource
	private FullCutService fullCutService;
	@Resource
	private FullGiveService fullGiveService;
	@Resource
	private FullDiscountService fullDiscountService;
	@Resource
	private MemberCouponLogService memberCouponLogService;
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private CommonService commonService;
	@Resource
	private MemberRemindService memberRemindService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ActivityService activityService;
	@Resource
	private CouponCombineRecLimitDtlService couponCombineRecLimitDtlService;
	@Autowired
	private ConfigSpecialMchtService configSpecialMchtService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	public void setMemberCouponMapper(MemberCouponMapper memberCouponMapper) {
		this.setDao(memberCouponMapper);
		this.memberCouponMapper = memberCouponMapper;
	}

	public List<MemberCoupon> getMmberCouponList(Integer memberId, Integer couponId) {
		MemberCouponExample memberCouponExample = new MemberCouponExample();
		memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andCouponIdEqualTo(couponId);
		memberCouponExample.setOrderByClause("rec_date desc");
		return memberCouponMapper.selectByExample(memberCouponExample);
	}
	
	public int findMemberCouponNumByCouponId(Integer memberId,List<Integer> couponIdList, String recLimitType) {
		MemberCouponExample memberCouponExample = new MemberCouponExample();
		MemberCouponExample.Criteria criteria = memberCouponExample.createCriteria();
		criteria.andMemberIdEqualTo(memberId).andCouponIdIn(couponIdList).andDelFlagEqualTo("0");
		Date currentDate = new Date();
		if("1".equals(recLimitType)){
			Date beginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
			Date endDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
			criteria.andRecDateGreaterThanOrEqualTo(beginDate).andRecDateLessThanOrEqualTo(endDate);
		}else if("4".equals(recLimitType)){
			Date beginDate = DateUtil.getMonthBeginData(currentDate);
			Date endDate = DateUtil.getMonthEndData(currentDate);
			criteria.andRecDateGreaterThanOrEqualTo(beginDate).andRecDateLessThanOrEqualTo(endDate);
		}
		return countByExample(memberCouponExample);
	}

	public List<MemberCoupon> findMemberCouponByCouponId(Integer memberId, Integer couponId) {
		MemberCouponExample memberCouponExample = new MemberCouponExample();
		memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andCouponIdEqualTo(couponId).andDelFlagEqualTo("0");
		memberCouponExample.setOrderByClause("rec_date desc");
		List<MemberCoupon> memberCoupons = memberCouponMapper.selectByExample(memberCouponExample);
		return memberCoupons;
	}

	public void addMemberCoupon(MemberCoupon memberCoupon) {

		memberCouponMapper.insertSelective(memberCoupon);
	}

	public void addMemberCouponBatch(String in_dataStr) {

		memberCouponCustomMapper.addMemberCouponBatch(in_dataStr);
	}

	public Map<String, Object> isReceiveCoupon(Integer memberId, Coupon coupon) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		String msgType = "0";//1已领取
		// 优惠券发行数量
		Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();
		// 优惠券已领数量
		Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
		// 优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限4.每人每月限领1张)
		String recLimitType = coupon.getRecLimitType() == null ? "" : coupon.getRecLimitType();
		// 优惠券 限领数量
		Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
		String status = coupon.getStatus();
		Date begin = coupon.getRecBeginDate();
		Date end = coupon.getRecEndDate();
		String rang = coupon.getRang();
		String careShopCanRec = coupon.getCareShopCanRec();
		Integer mchtId = coupon.getMchtId();
		// 领取类型
		String recType = coupon.getRecType();
		boolean isCanReceive = false;
		String recDate = "";
		String msg = "";
		Integer memberCouponSize = 0;
		
		if(StringUtil.isBlank(msg)){
			if("4".equals(recType)){
				MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
				Date now = new Date();
				if(!"1".equals(memberInfo.getIsSvip())||memberInfo.getSvipExpireDate()==null||now.getTime()>memberInfo.getSvipExpireDate().getTime()){
					isCanReceive = false;
					msg = "对不起，此优惠券只有svip会员才能领取";
					map.put("isSvip", "0");
				}else{
					map.put("isSvip", "1");
				}
			}
				
		}
		
		
		if(StringUtil.isBlank(msg)){
			if (date.compareTo(begin) < 0) {
				msg = "对不起，当前优惠领取时间未开始";
			} else if (date.compareTo(end) > 0) {
				msg = "对不起，当前优惠领取时间已结束";
			} else if (!"1".equals(status)) {
				// 启用状态(0未启用 1启用 2停用)
				msg = "优惠券未启用";
			}
		}
		if(rang.equals("4")){
			if("1".equals(careShopCanRec)){
				Integer count = memberRemindService.findCountByRemindType(mchtId,memberId,"3");
				if(count <= 0){
					isCanReceive = false;
					msg = "该券需要收藏店铺才能领取";
					map.put("isCanReceive", isCanReceive);
					map.put("msg", msg);
					return map;
				}
			}
		}
		
		if (StringUtil.isBlank(msg)) {
			if (grantQuantity > 0 && grantQuantity > recQuantity) {
				//if (!recType.equals("3")) {
					List<MemberCoupon> memberCoupons = findMemberCouponByCouponId(memberId, coupon.getId());
					if (CollectionUtils.isNotEmpty(memberCoupons)) {
						msgType = "1";
						memberCouponSize = memberCoupons.size();
						recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyy,MM,dd");
						String currentDate = DateUtil.getFormatDate(new Date(), "yyyy,MM,dd");
						String memberRecType = memberCoupons.get(0).getReceiveType();
						if (recLimitType.equals("1")) {
							if (!recDate.equals(currentDate)) {
								Map<String, Object> recLimitMap = couponCombineRecLimitDtlService.isCombinationLimit(coupon.getId(),1,memberId,recLimitType);
								isCanReceive = (boolean) recLimitMap.get("success");
								if(!isCanReceive){
									msg = recLimitMap.get("msg")+"";
								}else{
									msgType = "0";
								}
							} else {
								msg = "每人每天限领1张";
							}
						} else if (recLimitType.equals("2")) {
							if (memberCouponSize < recEach) {
								Map<String, Object> recLimitMap = couponCombineRecLimitDtlService.isCombinationLimit(coupon.getId(),1,memberId,recLimitType);
								isCanReceive = (boolean) recLimitMap.get("success");
								if(!isCanReceive){
									msg = recLimitMap.get("msg")+"";
								}else{
									msgType = "0";
								}
							} else {
								msg = "每人限领" + recEach + "张";
							}
						} else if (recLimitType.equals("3")) {
							isCanReceive = true;
							msgType = "0";
						}else if(recLimitType.equals("4")){
							recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyyMM");
							currentDate = DateUtil.getFormatDate(new Date(), "yyyyMM");
							String isSvip = memberInfoService.getIsSvip(null, memberId);
							if("6".equals(memberRecType)){
								isCanReceive = true;
								msgType = "0";
							}else{
								if(!"1".equals(isSvip)){
									msg = "您还不是SVIP会员，不能领取!";
									msgType = "3";
								}else{
									if(!recDate.equals(currentDate)){
										isCanReceive = true;
										msgType = "0";
									}else{
										msg = "每人每月限领1张";
									}
								}
							}
						}

					} else {
						if(recLimitType.equals("1") || recLimitType.equals("2") || recLimitType.equals("4")){
							if(recLimitType.equals("1") || recLimitType.equals("4")){
								recEach = 1;
							}
							Map<String, Object> recLimitMap = couponCombineRecLimitDtlService.isCombinationLimit(coupon.getId(),recEach,memberId,recLimitType);
							isCanReceive = (boolean) recLimitMap.get("success");
							if(!isCanReceive){
								msgType = "1";
								msg = recLimitMap.get("msg")+"";
							}
						}else{
							isCanReceive = true;
						}
					}
					
					
					//大额优惠券限领
					List<Integer> cjCouponIds = new ArrayList<Integer>();

//					33086、33089、33088 
					cjCouponIds.add(33086);
					cjCouponIds.add(33089);
					cjCouponIds.add(33088);
					if (cjCouponIds.contains(coupon.getId())) {
						MemberCouponExample memberCouponExample = new MemberCouponExample();
						memberCouponExample.createCriteria().andDelFlagEqualTo("0").andCouponIdIn(cjCouponIds).andMemberIdEqualTo(memberId);
						if (this.countByExample(memberCouponExample) > 0) {
							isCanReceive = false;
							msg = "对不起，您已领取过50元面额的优惠券";
							msgType = "1";
						}
					}

//					33087、33091、33090
					cjCouponIds.clear();
					cjCouponIds.add(33087);
					cjCouponIds.add(33091);
					cjCouponIds.add(33090);
					if (cjCouponIds.contains(coupon.getId())) {
						MemberCouponExample memberCouponExample = new MemberCouponExample();
						memberCouponExample.createCriteria().andDelFlagEqualTo("0").andCouponIdIn(cjCouponIds).andMemberIdEqualTo(memberId);
						if (this.countByExample(memberCouponExample) > 0) {
							isCanReceive = false;
							msg = "对不起，您已领取过10元面额的优惠券";
							msgType = "1";
						}
					}
					
					

					

//				} else {
//					msg = "对不起，优惠券已被抢光了，欢迎下次再来试试手气。";
//					msgType = "2";
//				}
			} else {
				isCanReceive = false;
				msg = "对不起，优惠券已被抢光了，欢迎下次再来试试手气。";
				msgType = "2";
			}
		}
		map.put("isCanReceive", isCanReceive);
		map.put("msg", msg);
		map.put("memberCouponSize", memberCouponSize);
		map.put("recType", coupon.getRecType());
		map.put("needIntegral", coupon.getNeedIntegral() == null ? 0 : coupon.getNeedIntegral());
		map.put("recLimitType", coupon.getRecLimitType());
		map.put("recEach", coupon.getRecEach());
		map.put("recDate", recDate);
		map.put("msgType", msgType);
		return map;
	}

	public List<Map<String, Object>> getMemberUsebleActivityAreaCoupons(Map<String, Object> params) {
		return memberCouponCustomMapper.getMemberUsebleActivityAreaCoupons(params);
	}

	public List<Map<String, Object>> getMemberUseblePlatformCoupons(Map<String, Object> platParamsMap) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<MemberCouponCustom> memberPlatformCoupons = memberCouponCustomMapper.getMemberUseblePlatformCoupons(platParamsMap);
		if(CollectionUtils.isNotEmpty(memberPlatformCoupons)){
			for (MemberCouponCustom model : memberPlatformCoupons) {
				Map<String, Object> dataMap = buildMemberCouponInfo(model);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	public Map<String, Object> getMemberCouponList(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");//会员标识id
		Integer currentPage = reqDataJson.getInt("currentPage");//页码
		Integer pageSize = reqDataJson.getInt("pageSize");
		String type = "1";//1未过期 2已使用 3已过期
		if(reqDataJson.containsKey("type") && !StringUtil.isBlank(reqDataJson.getString("type"))){
			type = reqDataJson.getString("type");
		}
		String isSvip = memberInfoService.getIsSvip(null, memberId);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
		params.put("type", type);
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<MemberCouponCustom> memberCouponCustoms = memberCouponCustomMapper.getMemberCouponList(params);
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(memberCouponCustoms)){
			for (MemberCouponCustom model : memberCouponCustoms) {
				boolean isCanGive = false;
				Map<String,Object> dataMap = buildMemberCouponInfo(model);
				if("1".equals(type) && isSvip.equals("1")){
					isCanGive = true;
				}
				dataMap.put("isCanGive", isCanGive);
				dataList.add(dataMap);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		return map;
	}

	public FindMemberCouponResponse findMemberCoupon(FindMemberCouponRequest request, Integer memberId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("memberId", memberId);
		params.put("type", request.getType());
		params.put("status", request.getStatus());
		params.put("offset", request.getOffset());
		params.put("fetchSize", request.getPageSize());

		FindMemberCouponResponse response = new FindMemberCouponResponse();
		int totalCount = memberCouponCustomMapper.countMemberCoupon(params);
		if (totalCount <= 0) {
			return response;
		}
		response.setTotalCount(totalCount);

		List<MemberCouponCustom> memberCouponCustomList = memberCouponCustomMapper.findMemberCoupon(params);
		Map<Integer, Date> couponGiveDateMap = buildCouponGiveDateMap(memberCouponCustomList);
		boolean isSvip = memberInfoService.isRealSVip(null, memberId);
		if (CollectionUtils.isNotEmpty(memberCouponCustomList)) {
			for (MemberCouponCustom model : memberCouponCustomList) {
				Map<String, Object> memberCouponView = buildMemberCouponInfoV2(model, couponGiveDateMap);
				boolean isCanGive = false;
				if (request.getStatus() == 1 && isSvip) {
					isCanGive = true;
				}
				if (Objects.equals(model.getRecType(), "4")) { //svip领取
					memberCouponView.put("couponTypeName", "SVIP专享");
				}
				memberCouponView.put("isCanGive", isCanGive);
				response.getDataList().add(memberCouponView);
			}
		}
		return response;
	}

	private Map<Integer, Date> buildCouponGiveDateMap(List<MemberCouponCustom> memberCouponCustomList) {
		List<Integer> giveMemberCouponIds = Lists.newArrayList();
		for (MemberCouponCustom memberCoupon : memberCouponCustomList) {
			if (Objects.equals(memberCoupon.getIsGive(), StateConst.TRUE)) {
				giveMemberCouponIds.add(memberCoupon.getId());
			}
		}
		if (CollectionUtils.isEmpty(giveMemberCouponIds)) return Collections.emptyMap();

		MemberCouponLogExample example = new MemberCouponLogExample();
		example.createCriteria()
				.andLogTypeEqualTo("3") //赠送
				.andMemberCouponIdIn(giveMemberCouponIds)
				.andDelFlagEqualTo(StateConst.FALSE);
		List<MemberCouponLog> logs = memberCouponLogService.selectByExample(example);
		return MapHelper.toMap(logs, new Function<MemberCouponLog, Integer>() {
			@Override
			public Integer apply(MemberCouponLog input) {
				return input.getMemberCouponId();
			}
		}, new Function<MemberCouponLog, Date>() {
			@Override
			public Date apply(MemberCouponLog input) {
				return input.getCreateDate();
			}
		});
	}

	public Map<String, Object> buildMemberCouponInfoV2(MemberCouponCustom model, Map<Integer, Date> couponGiveDateMap) {
		Date currentDate = new Date();
		Map<String, Object> dataMap = new HashMap<>();
		String rang = model.getRang();
		BigDecimal minimum = model.getMinimum();
		BigDecimal money = model.getMoney();
		Integer id = model.getId();
		Date expiryBeginDate = model.getExpiryBeginDate();
		Date expiryEndDate = model.getExpiryEndDate();
		Integer activityAreaId = model.getActivityAreaId();
		String couponType = StringUtil.isBlank(model.getCouponType()) ? "" : model.getCouponType();
		String typeIds = model.getTypeIds();
		Integer mchtId = model.getMchtId();
		String receiveType = model.getReceiveType() == null ? "" : model.getReceiveType();
		String couponPreferentialType = model.getPreferentialType();//1优惠券满减 2优惠券折扣
		String conditionType = model.getConditionType();//1无条件 2满多少元可用 3满多少件可用
		BigDecimal maxDiscountMoney = model.getMaxDiscountMoney();
		BigDecimal discount = model.getDiscount();//折扣
		Integer minicount = model.getMinicount();//最低几件
		String careShopCanRec = StringUtil.isBlank(model.getCareShopCanRec()) ? "0" : model.getCareShopCanRec();
		String linkType = StringUtil.isBlank(model.getLinkType()) ? "" : model.getLinkType();
		String linkValue = model.getLinkValue();
		String recType = model.getRecType();
		String couponTypeName = "";
		String preferentialInfo = "";
		String useDescription = "";
		if (rang.equals("1")) { //全平台
			if ("1".equals(couponType)) {
				couponTypeName = "全品类";
				useDescription = "全品类可用，";
			} else if ("2".equals(couponType)) {
				couponTypeName = "品类券";
				if (!StringUtil.isBlank(typeIds)) {
					List<Integer> productTypeIds = new ArrayList<>();
					String productTypeName = "";
					for (String str : typeIds.split(",")) {
						if (!StringUtil.isBlank(str)) {
							productTypeIds.add(Integer.parseInt(str));
						}
					}
					List<ProductType> productTypes = productTypeService.findModelsByIds(productTypeIds);
					if (CollectionUtils.isNotEmpty(productTypes)) {
						for (ProductType pt : productTypes) {
							productTypeName = productTypeName + pt.getName() + "，";
						}
					}
					if (StrKit.notBlank(productTypeName)) {
						useDescription = StringUtil.buildMsg("限{}品类可用，", productTypeName.substring(0, productTypeName.length() - 1));
					}
				}
			} else if ("3".equals(couponType)) {
				couponTypeName = "品牌券";
			} else if ("4".equals(couponType)) {
				couponTypeName = "商品券";
			}
			useDescription += "新人专享、积分抵扣等特惠商品不可用。不可与其他平台券叠加。";
		} else if (rang.equals("2")) {
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
			linkType = "1";
			linkValue = activityAreaId + "";
			couponTypeName = "专场券";
			useDescription = StringUtil.buildMsg("指定专场：{}可用", activityArea.getName());
		} else if (rang.equals("3")) {
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
			List<Activity> activities = activityService.findModelByAreaId(activityAreaId);
			linkType = "2";
			linkValue = activities.get(0).getId() + "";
			couponTypeName = "专场券";
			useDescription = StringUtil.buildMsg("指定专场：{}可用", activityArea.getName());
		} else if (rang.equals("4")) {
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if ("4".equals(couponType)) {
				couponTypeName = "商品券";
				if (StringUtils.hasText(typeIds)) { //商品券 typeIds 仅存一个商品ID
					Product product = productService.selectByPrimaryKey(Integer.valueOf(typeIds));
					useDescription = StringUtil.buildMsg("指定商品：{}可用", product.getName());
				}
				linkType = "3";
				linkValue = typeIds;
			} else {
				linkType = "10";
				linkValue = mchtId + "";
				couponTypeName = "店铺券";
				useDescription = StringUtil.buildMsg("指定店铺：{}可用，特惠商品除外", mchtInfo.getShopName());
			}
		}
		if ("4".equals(couponType)) {
			preferentialInfo = "无门槛优惠券";
		} else {
			if ("1".equals(couponPreferentialType)) {
				if (minimum.subtract(money).compareTo(new BigDecimal("0.01")) == 0) {
					preferentialInfo = "无门槛优惠券";
				} else {
					preferentialInfo = "满" + minimum.stripTrailingZeros().toPlainString() + "元可用";
				}

			} else if ("2".equals(couponPreferentialType)) {
				if ("1".equals(conditionType)) {
					preferentialInfo = "无门槛优惠券";
				} else if ("2".equals(conditionType)) {
					preferentialInfo = "满" + minimum.stripTrailingZeros().toPlainString() + "元可用";
				} else if ("3".equals(conditionType)) {
					preferentialInfo = "满" + minicount + "件可用";
				}
				if (maxDiscountMoney != null) {
					preferentialInfo += "最高抵扣" + maxDiscountMoney.stripTrailingZeros().toPlainString() + "元";
				}
			}
		}

		AdDTO adDTO = new AdDTO();
		adDTO.setAdId("0");
		adDTO.setLinkType(linkType);
		adDTO.setLinkValue(linkValue);
		dataMap = commonService.buildAdMap(adDTO);
		dataMap.put("id", id);
		dataMap.put("expiryBeginDate", expiryBeginDate);
		dataMap.put("expiryEndDate", expiryEndDate);
		dataMap.put("currentDate", currentDate);
		// STORY #1632
		if (StringUtil.isEmpty(model.getDefinitionPrefix())) {
			dataMap.put("couponTypeName", couponTypeName);
		} else {
			dataMap.put("couponTypeName", model.getDefinitionPrefix());
		}
		dataMap.put("couponPreferentialType", couponPreferentialType);
		dataMap.put("money", money == null ? 0 : money.stripTrailingZeros().toPlainString());
		dataMap.put("discount", discount != null ? discount.multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString() : discount);
		dataMap.put("minimum", minimum == null ? 0 : minimum.stripTrailingZeros().toPlainString());
		dataMap.put("status", 1);
		dataMap.put("preferentialInfo", preferentialInfo);
		dataMap.put("conditionType", conditionType);
		dataMap.put("maxDiscountMoney", maxDiscountMoney);
		dataMap.put("minicount", minicount);
		dataMap.put("useDescription", useDescription);
		dataMap.put("couponType", couponType);
		dataMap.put("receiveType", receiveType);
		dataMap.put("recType", recType);
		dataMap.put("typeIds", typeIds);
		dataMap.put("canSuperpose", 0);
		dataMap.put("careShopCanRec", careShopCanRec);
		dataMap.put("useDate", model.getUseDate());
		if (Objects.equals(model.getIsGive(), StateConst.TRUE)) {
			dataMap.put("give", StateConst.TRUE);
			dataMap.put("giveDate", couponGiveDateMap.get(model.getId()));
		}else{
			dataMap.put("give", StateConst.FALSE);
		}
		return dataMap;
	}

	public Map<String, Object> buildMemberCouponInfo(MemberCouponCustom model) {
		Date currentDate = new Date();
		Map<String, Object> dataMap = new HashMap<>();
		String rang = model.getRang();
		BigDecimal minimum = model.getMinimum();
		BigDecimal money = model.getMoney();
		Integer id = model.getId();
		Date expiryBeginDate = model.getExpiryBeginDate();
		Date expiryEndDate = model.getExpiryEndDate();
		Integer activityAreaId = model.getActivityAreaId();
		String couponType = StringUtil.isBlank(model.getCouponType()) ? "" : model.getCouponType();
		String typeIds = model.getTypeIds();
		Integer mchtId = model.getMchtId();
		String receiveType = model.getReceiveType() == null ? "" : model.getReceiveType();
		String couponPreferentialType = model.getPreferentialType();//1优惠券满减 2优惠券折扣
		String conditionType = model.getConditionType();//1无条件 2满多少元可用 3满多少件可用
		BigDecimal maxDiscountMoney = model.getMaxDiscountMoney();
		BigDecimal discount = model.getDiscount();//折扣
		Integer minicount = model.getMinicount();//最低几件
		String careShopCanRec = StringUtil.isBlank(model.getCareShopCanRec()) ? "0" : model.getCareShopCanRec();
		String linkType = StringUtil.isBlank(model.getLinkType()) ? "" : model.getLinkType();//5无连接
		String linkValue = model.getLinkValue();
		String recType = model.getRecType();
		String couponTypeName = "";
		String describeContent = "";
		String preferentialInfo = "";
		String useDescription = "";
		if(rang.equals("1")){//全平台
			if("1".equals(couponType)){
				couponTypeName = "全品类";
			}else if("2".equals(couponType)){
				couponTypeName = "品类券";
				useDescription = "";
				if(!StringUtil.isBlank(typeIds)){
					List<Integer> productTypeIds = new ArrayList<>();
					String productTypeName = "";
					for (String str : typeIds.split(",")) {
						if(!StringUtil.isBlank(str)){
							productTypeIds.add(Integer.parseInt(str));
						}
					}
					List<ProductType> productTypes = productTypeService.findModelsByIds(productTypeIds);
					if(CollectionUtils.isNotEmpty(productTypes)){
						for (ProductType pt : productTypes) {
							productTypeName =  productTypeName + pt.getName()+"，";
						}
					}
					useDescription = "限"+productTypeName.substring(0,productTypeName.length()-1)+"品类可用";
				}
			}else if("3".equals(couponType)){
				couponTypeName = "品牌券";
			}else if("4".equals(couponType)){
				couponTypeName = "商品券";
				useDescription = "指定商品可用";
			}
			describeContent = "限时抢购、新人专享、积分抵扣、特殊商品等不可用";
		}else if(rang.equals("2")){
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
			linkType = "1";
			linkValue = activityAreaId+"";
			couponTypeName = "专场券";
			useDescription = activityArea.getName();
			describeContent = "仅本场可用";
		}else if(rang.equals("3")){
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
			List<Activity> activities = activityService.findModelByAreaId(activityAreaId);
			linkType = "2";
			linkValue = activities.get(0).getId()+"";
			couponTypeName = "专场券";
			useDescription = activityArea.getName();
			describeContent = "仅本场可用";
		}else if(rang.equals("4")){
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if("4".equals(couponType)){
				couponTypeName = "商品券";
				useDescription = "指定商品可用";
				linkType = "3";
				linkValue = typeIds;
			}else{
				linkType = "10";
				linkValue = mchtId + "";
				couponTypeName = "店铺券";
				describeContent = "活动商品不可用";
				useDescription = mchtInfo.getShopName();
			}
		}
		
		if(couponType.equals("4")){
			preferentialInfo = "无门槛优惠券";
		}else{
			if("1".equals(couponPreferentialType)){
				if(minimum.subtract(money).compareTo(new BigDecimal("0.01")) == 0){
					preferentialInfo = "无门槛优惠券";
				}else{
					preferentialInfo = "满"+minimum.stripTrailingZeros().toPlainString()+"元可用";
				}
				
			}else if("2".equals(couponPreferentialType)){
				if("1".equals(conditionType)){
					preferentialInfo = "无门槛优惠券";
				}else if("2".equals(conditionType)){
					preferentialInfo = "满"+minimum.stripTrailingZeros().toPlainString()+"元可用";
				}else if("3".equals(conditionType)){
					preferentialInfo = "满"+minicount+"件可用";
				}
				if(maxDiscountMoney != null){
					preferentialInfo += "最高抵扣"+maxDiscountMoney.stripTrailingZeros().toPlainString()+"元";
				}
			}
		}
		
		AdDTO adDTO = new AdDTO();
		adDTO.setAdId("0");
		adDTO.setLinkType(linkType);
		adDTO.setLinkValue(linkValue);
		dataMap = commonService.buildAdMap(adDTO);
		dataMap.put("id", id);
		dataMap.put("expiryBeginDate", expiryBeginDate);
		dataMap.put("expiryEndDate", expiryEndDate);
		dataMap.put("currentDate", currentDate);
		// STORY #1632
		if(StringUtil.isEmpty(model.getDefinitionPrefix())){
			dataMap.put("couponTypeName", couponTypeName);
		}else{
			dataMap.put("couponTypeName", model.getDefinitionPrefix());
		}
		dataMap.put("couponPreferentialType", couponPreferentialType);
		dataMap.put("money", money == null ? 0 : money.stripTrailingZeros().toPlainString());
		dataMap.put("discount", discount != null ? discount.multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString() : discount);
		dataMap.put("minimum", minimum == null ? 0 : minimum.stripTrailingZeros().toPlainString());
		dataMap.put("status", 1);
		dataMap.put("describeContent", describeContent);
		dataMap.put("preferentialInfo", preferentialInfo);
		dataMap.put("useDescription", useDescription);
		dataMap.put("couponType", couponType);
		dataMap.put("receiveType", receiveType);
		dataMap.put("recType", recType);
		dataMap.put("typeIds", typeIds);
		dataMap.put("canSuperpose", 0);
		dataMap.put("conditionType", conditionType);
		dataMap.put("maxDiscountMoney", maxDiscountMoney);
		dataMap.put("minicount", minicount);
		dataMap.put("careShopCanRec", careShopCanRec);
		return dataMap;
	}

	public MemberCoupon findMemberCouponById(Integer memberId, Integer id, Date date) {

		MemberCouponExample memberCouponExample = new MemberCouponExample();
		memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andIdEqualTo(id).andDelFlagEqualTo("0").andStatusEqualTo("0").andExpiryBeginDateLessThanOrEqualTo(date).andExpiryEndDateGreaterThanOrEqualTo(date);
		List<MemberCoupon> memberCoupons = memberCouponMapper.selectByExample(memberCouponExample);
		MemberCoupon memberCoupon = new MemberCoupon();
		if (CollectionUtils.isNotEmpty(memberCoupons)) {
			memberCoupon = memberCoupons.get(0);
			return memberCoupon;
		}
		return null;
	}

	public Map<String, Object> addReceiveCoupon(JSONObject reqPRM,JSONObject reqDataJson) {
		String system = reqPRM.getString("system");
		Integer version = reqPRM.getInt("version");
		String memberId = reqDataJson.getString("memberId");
		String type = "0";
		String couponId = "";
		String code = "";
		if(reqDataJson.containsKey("type")){
			type = reqDataJson.getString("type");
			if(StringUtil.isBlank(type)){
				throw new ArgException("type不能为空");
			}
		}
		if(type.equals("0")){
			//领取优惠券
			couponId = reqDataJson.getString("couponId");
			if(StringUtil.isBlank(couponId)){
				throw new ArgException("couponId不能为空");
			}
		}else if(type.equals("1")){
			//游离码兑换
			code = reqDataJson.getString("code");
			if(StringUtil.isBlank(code)){
				throw new ArgException("请输入兑换码");
			}
		}else{
			throw new ArgException("领取类型错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Date expiryBeginDate = null;
		Date expiryEndDate = null;
		Date date = new Date();
		CouponExchangeCode couponExchangeCode = null;
		if (type.equals("1")) {
			// 游离码兑换
			CouponExchangeCodeExample couponExchangeCodeExample = new CouponExchangeCodeExample();
			couponExchangeCodeExample.createCriteria().andCodeEqualTo(code);
			List<CouponExchangeCode> couponExchangeCodes = couponExchangeCodeService.selectByExample(couponExchangeCodeExample);
			if (CollectionUtil.isNotEmpty(couponExchangeCodes)) {
				couponExchangeCode = couponExchangeCodes.get(0);
				String isExchange = couponExchangeCode.getIsExchange();
				String exchangeLimit = couponExchangeCode.getExchangeLimit() == null ? "" : couponExchangeCode.getExchangeLimit();
				couponId = couponExchangeCode.getCouponId().toString();
				if (isExchange.equals("1")) {
					throw new ArgException("兑换码已被使用");
				}
				if (exchangeLimit.equals("1")) {
					// 首次兑换
					int exchangeCouponCount = memberCouponCustomMapper.getExchangeCouponCount(Integer.valueOf(memberId));
					if (exchangeCouponCount > 0) {
						throw new ArgException("该优惠券只适用于新用户");
					}
				}
			} else {
				throw new ArgException("请输入正确的兑换码");
			}
		}
		Coupon coupon = couponService.selectByPrimaryKey(Integer.valueOf(couponId));
		String typeIds = coupon.getTypeIds();
		String rang = coupon.getRang();
		String couponType = coupon.getCouponType();
		String preferentialType = coupon.getPreferentialType();
		if((system.equals(Const.ANDROID) && version <= 57) || (system.equals(Const.IOS) && version <= 58)){
			if("2".equals(preferentialType) || "4".equals(couponType) || (rang.equals("1") && "2".equals(couponType) && typeIds.contains(","))){
				throw new ArgException("版本过低无法领取请更新最新版本！");
			}
		}
		if (coupon == null || coupon.getId() == null || Const.FLAG_TRUE.equals(coupon.getDelFlag())) {
			throw new ArgException("优惠券已失效");
		} else {
			if (coupon.getExpiryType().equals("2")) {
				expiryBeginDate = date;
				expiryEndDate = DateUtil.addDay(date, coupon.getExpiryDays());
			} else {
				expiryBeginDate = coupon.getExpiryBeginDate();
				expiryEndDate = coupon.getExpiryEndDate();
			}
			if (type.equals("1")) {
				if (!coupon.getRecType().equals("3")) {
					throw new ArgException("优惠券兑换类型错误");
				}
			} else {
				if (coupon.getRecType().equals("3")) {
					throw new ArgException("优惠券领取类型错误");
				}
			}
		}
		// 判断优惠券是否可以领取
		// isCanReceive = true 可以领取
		// isCanReceive = false 不可以领取
		Map<String, Object> couponMap = isReceiveCoupon(Integer.valueOf(memberId), coupon);
		boolean isCanReceive = (boolean) couponMap.get("isCanReceive");
		String msg = (String) couponMap.get("msg");
		if (!isCanReceive) {
			map.put("isCanReceive", isCanReceive);
			map.put("mark", true);
			map.put("integral", "");
			map.put("content", msg);
			return map;
		}
		if (coupon.getRecType().equals("2")) {
			MemberAccountExample memberAccountExample = new MemberAccountExample();
			memberAccountExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId)).andDelFlagEqualTo("0");
			List<MemberAccount> memberAccounts = memberAccountService.selectByExample(memberAccountExample);
			if (CollectionUtils.isNotEmpty(memberAccounts)) {
				MemberAccount memberAccount = memberAccounts.get(0);
				Integer integral = memberAccount.getIntegral();
				if (coupon.getNeedIntegral() <= integral) {
					memberAccount.setIntegral(integral - coupon.getNeedIntegral());
					memberAccount.setUpdateBy(Integer.valueOf(memberId));
					memberAccount.setUpdateDate(new Date());
					memberAccountService.updateByPrimaryKeySelective(memberAccount);

					integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_ACCOUNT, Const.INTEGRAL_TYPE_EXCHANGE, coupon.getNeedIntegral(), memberAccount.getIntegral(), null, Integer.valueOf(memberId), "1");
				} else {
					map.put("isCanReceive", isCanReceive);
					map.put("mark", false);
					map.put("integral", integral);
					map.put("content", "积分不足");
					return map;
				}
			}
		} else if (coupon.getRecType().equals("3")) {
			if (StringUtil.isBlank(code)) {
				throw new ArgException("请用游离码兑换");
			}
		}
		
		// 更改优惠券的领取数量
		int count = memberCouponService.updateRecCouponInfo(coupon.getId());
		if(count <= 0){
			throw new ArgException("对不起，优惠券已被抢光了，欢迎下次再来试试手气。");
		}
		
		
		MemberCoupon memberCoupon = new MemberCoupon();
		memberCoupon.setMemberId(Integer.valueOf(memberId));
		memberCoupon.setCouponId(Integer.valueOf(couponId));
		memberCoupon.setRecDate(new Date());
		memberCoupon.setExpiryBeginDate(expiryBeginDate);
		memberCoupon.setExpiryEndDate(expiryEndDate);
		memberCoupon.setDelFlag("0");
		memberCoupon.setCreateBy(Integer.valueOf(memberId));
		memberCoupon.setCreateDate(new Date());
		memberCouponService.insertSelective(memberCoupon);

		// 修改游离码打上已使用标志
		if (couponExchangeCode != null) {
			couponExchangeCode.setIsExchange("1");
			couponExchangeCode.setCouponId(Integer.valueOf(couponId));
			couponExchangeCode.setMemberCouponId(memberCoupon.getId());
			couponExchangeCode.setExchangeDate(date);
			couponExchangeCode.setUpdateBy(Integer.valueOf(memberId));
			couponExchangeCode.setUpdateDate(date);
			couponExchangeCodeService.updateByPrimaryKeySelective(couponExchangeCode);
		}
		map.put("isCanReceive", isCanReceive);
		map.put("mark", true);
		map.put("integral", "");
		map.put("content", "领取成功");
		return map;
	}

	public Map<String, String> getPreferentialInfo(String preferentialType, String preferentialIdGroup, Date date) {
		Map<String, String> map = new HashMap<String, String>();
		String preferentialInfo = "";
		List<Integer> preferentialCouponIds = Lists.newArrayList();
		// 特惠类型(0无 1优惠劵 2满减 3满赠 4多买优惠)
		if (StringUtil.isBlank(preferentialIdGroup)) {
			preferentialIdGroup = "";
		}
		if (StringUtil.isBlank(preferentialType)) {
			preferentialType = "";
		}
		List<Integer> couponIds = new ArrayList<Integer>();
		if (preferentialType.equals("1")) {
			// 优惠券
			if (!StringUtil.isBlank(preferentialIdGroup)) {
				String[] preferentialIdGroups = preferentialIdGroup.split(",");
				for (String str : preferentialIdGroups) {
					couponIds.add(Integer.valueOf(str));
				}
			}
			if (CollectionUtils.isNotEmpty(couponIds)) {
				CouponExample couponExample = new CouponExample();
				couponExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIds).andRecBeginDateLessThanOrEqualTo(date).andRecEndDateGreaterThanOrEqualTo(date).andRecTypeNotEqualTo("3");
				couponExample.setOrderByClause("money desc");
				List<Coupon> couponList = couponService.selectByExample(couponExample);
				if (couponList != null && couponList.size() > 0) {
					boolean b = false;// 判断是否是无门槛优惠券
					for (Coupon coupon : couponList) {
						BigDecimal minimum = coupon.getMinimum().stripTrailingZeros();
						BigDecimal money = coupon.getMoney().stripTrailingZeros();
						if (couponList.size() == 1 && minimum.compareTo(money) == 0.01) {
							b = true;
							preferentialInfo = money.toPlainString() + "元无门槛优惠券";
							break;
						}
						preferentialInfo += "满" + minimum.toPlainString() + "减" + money.toPlainString() + ",";
						preferentialCouponIds.add(coupon.getId());
					}
					if (!b) {
						preferentialInfo = preferentialInfo.substring(0, preferentialInfo.length() - 1);
					}
				}
			}
		} else if (preferentialType.equals("2")) {
			// 满减
			FullCut fullCut = fullCutService.selectByPrimaryKey(Integer.valueOf(preferentialIdGroup));
			if (fullCut != null) {
				// 是否阶梯(0否 1是)
				String ladderFlag = fullCut.getLadderFlag();
				// 是否累加(0否 1是)
				String sumFlag = fullCut.getSumFlag();
				// 优惠规则
				String rule = fullCut.getRule();
				if (ladderFlag.equals("0")) {// 非阶梯
					String[] rules = rule.split(",");
					if (sumFlag.equals("0")) {// 非累计
						preferentialInfo = "满" + rules[0] + "减" + rules[1] + "";
					} else if (sumFlag.equals("1")) {// 累计
						preferentialInfo = "满" + rules[0] + "减" + rules[1] + "【上不封顶】";
					}
				} else if (ladderFlag.equals("1")) {// 阶梯(没有累加不累加的)
					// 获取上一次循环的值
					String[] rules = rule.split("\\|");
					for (String ladderRules : rules) {
						String[] ladderRule = ladderRules.split(",");
						preferentialInfo += "满" + ladderRule[0] + "减" + ladderRule[1] + ",";
					}
					preferentialInfo = preferentialInfo.substring(0, preferentialInfo.length() - 1);
				}
			}
		} else if (preferentialType.equals("3")) {
			// 满赠优惠
			FullGive fullGive = fullGiveService.selectByPrimaryKey(Integer.valueOf(preferentialIdGroup));
			String type = fullGive.getType();// 满赠类型(1满额赠 2买即赠)
			String sumFlag = fullGive.getSumFlag();// 是否累加 0否 1是
			if (type.equals("1")) {
				BigDecimal minimum = fullGive.getMinimum().stripTrailingZeros();
				if (sumFlag.equals("0")) {
					preferentialInfo = "满" + minimum.toPlainString() + "元即赠送礼品，赠完即止";
				} else {
					preferentialInfo = "满" + minimum.toPlainString() + "元即赠送礼品，赠完即止【满额累加赠送】";
				}
			} else {
				preferentialInfo = "买即赠礼品，赠完即止";
			}
		} else if (preferentialType.equals("4")) {
			// 多买优惠
			FullDiscount fullDiscount = fullDiscountService.selectByPrimaryKey(Integer.valueOf(preferentialIdGroup));
			if (fullDiscount != null) {
				// 多买类型(1:满M件减N件 2:M元任选N件 3:M件N折 4:第M件N折)
				String type = fullDiscount.getType();
				// 规则串
				String rule = fullDiscount.getRule();
				if (type.equals("1")) {
					// 满M件减N件
					String[] rules = rule.split(",");
					int max = Integer.valueOf(rules[0]);// 满M件
					int min = Integer.valueOf(rules[1]);// 减N件
					preferentialInfo = "满" + max + "件减" + min + "件";
				} else if (type.equals("2")) {
					// 满M件减N元
					String[] rules = rule.split(",");
					int max = Integer.valueOf(rules[0]);// 满M件
					BigDecimal min = new BigDecimal(rules[1]).stripTrailingZeros();// 减N元
					preferentialInfo = "满" + max + "件" + min.toPlainString() + "元";
				} else if (type.equals("3")) {
					// 满M件打N折
					String[] rules = rule.split("\\|");
					// 获取上一次循环的值
					for (String ladderRules : rules) {
						String[] ladderRule = ladderRules.split(",");
						preferentialInfo += "满" + ladderRule[0] + "件" + ladderRule[1] + "折,";
					}
					preferentialInfo = preferentialInfo.substring(0, preferentialInfo.length() - 1);
				} else if (type.equals("4")) {
					// 4:第M件N折
					String[] rules = rule.split(",");
					preferentialInfo = "第" + rules[0] + "件" + rules[1] + "折";
				}
			}
		}
		map.put("preferentialInfo", preferentialInfo);
		map.put("preferentialType", preferentialType);
		map.put("preferentialCouponIds", Joiner.on(",").join(preferentialCouponIds));
		return map;
	}

	public void addReceiveWithdrawCoupon(Integer memberId, Integer couponId) {
		Coupon coupon = couponService.selectByPrimaryKey(couponId);
		if (!coupon.getStatus().equals("1")) {
			// 启用状态(0未启用 1启用 2停用)
			throw new ArgException("优惠券未启用");
		}
		Map<String, Object> map = getMemberIsReceiveCoupon(coupon, memberId);
		if ((boolean) map.get("isHasCoupon")) {
			// 优惠券要等平台审核通过了，才能给用户。放在定时任务中进行处理
			// Date expiryBeginDate = null;
			// Date expiryEndDate = null;
			// Date date = new Date();
			// if(coupon.getExpiryType().equals("2")){
			// expiryBeginDate = date;
			// expiryEndDate = DateUtil.addDay(date, coupon.getExpiryDays());
			// }else{
			// expiryBeginDate = coupon.getExpiryBeginDate();
			// expiryEndDate = coupon.getExpiryEndDate();
			// }
			// MemberCoupon memberCoupon = new MemberCoupon();
			// memberCoupon.setMemberId(Integer.valueOf(memberId));
			// memberCoupon.setCouponId(Integer.valueOf(couponId));
			// memberCoupon.setRecDate(new Date());
			// memberCoupon.setExpiryBeginDate(expiryBeginDate);
			// memberCoupon.setExpiryEndDate(expiryEndDate);
			// memberCoupon.setDelFlag("0");
			// memberCoupon.setCreateBy(Integer.valueOf(memberId));
			// memberCoupon.setCreateDate(new Date());
			// memberCouponService.insertSelective(memberCoupon);
			// //更改优惠券的领取数量
			// Integer recQuantity = coupon.getRecQuantity() == null ? 0 :
			// coupon.getRecQuantity();
			// coupon.setRecQuantity(recQuantity+1);
			// couponService.updateByModel(coupon);
		} else {
			throw new ArgException(map.get("couponReceiveMsg").toString());
		}

	}

	/**
	 * 
	 * 方法描述 ：判断用户是否领取过优惠券
	 * 
	 * @author chenwf:
	 * @date 创建时间：2018年5月31日 下午4:00:04
	 * @version
	 * @param coupon
	 * @param memberId
	 * @return
	 */
	public Map<String, Object> getMemberIsReceiveCoupon(Coupon coupon, Integer memberId) {
		// 优惠券发行数量
		Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();
		// 优惠券已领数量
		Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
		// 优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
		String recLimitType = coupon.getRecLimitType() == null ? "" : coupon.getRecLimitType();
		// 优惠券 限领数量
		Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
		String msgType = "";//1已领取
		String msg = "";
		boolean isHasCoupon = true;
		if (grantQuantity > 0 && grantQuantity > recQuantity) {
			if (memberId != null) {
				MemberCouponExample memberCouponExample = new MemberCouponExample();
				memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andCouponIdEqualTo(coupon.getId()).andDelFlagEqualTo("0");
				memberCouponExample.setOrderByClause("rec_date desc");
				List<MemberCoupon> memberCoupons = memberCouponService.selectByExample(memberCouponExample);
				if (CollectionUtils.isNotEmpty(memberCoupons)) {
					String recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyyMMdd");
					String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
					// 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
					if (recLimitType.equals("1")) {
						if (recDate.equals(currentDate)) {
							isHasCoupon = false;
							msgType = "1";
							msg = "每人每天限领1张 ";
						}
					} else if (recLimitType.equals("2")) {
						if (memberCoupons.size() >= recEach) {
							isHasCoupon = false;
							msgType = "1";
							msg = "每人限领 " + recEach + "张";
						}
					} else if (recLimitType.equals("3")) {
					} else {
						isHasCoupon = false;
						msgType = "2";
						msg = "优惠券已抢光";
					}
				}
			}
		} else {
			msgType = "2";
			isHasCoupon = false;
			msg = "优惠券已抢光";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isHasCoupon", isHasCoupon);
		map.put("couponReceiveMsg", msg);
		map.put("msgType", msgType);
		return map;
	}

	public List<Map<String, Object>> getMemberUsebleMchtShopCoupons(Map<String, Object> params) {

		return memberCouponCustomMapper.getMemberUsebleMchtShopCoupons(params);
	}

	public List<Map<String, Object>> getActivityMemberCouponInfo618() {
		Date date = new Date();
		List<Integer> couponIds = new ArrayList<Integer>();
		couponIds.add(4288);
		Date begin = DateUtil.getDateAfterAndBeginTime(date, 0);
		Date end = DateUtil.getDateAfterAndEndTime(date, 0);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("couponIds", couponIds);
		paramsMap.put("begin", begin);
		paramsMap.put("end", end);
		List<MemberCouponDTO> couponDTOs = memberCouponCustomMapper.getActivityMemberCouponInfo618(paramsMap);
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (MemberCouponDTO memberCouponDTO : couponDTOs) {
			Map<String, Object> map = new HashMap<String, Object>();
			Integer tataolNum = 0;
			if (memberCouponDTO.getCouponId() == 4288) {
				tataolNum = 10000;
			}
			map.put("tataolNum", tataolNum);
			map.put("memberCouponNum", memberCouponDTO.getMemberCouponNum());
			map.put("couponId", memberCouponDTO.getCouponId());
			dataList.add(map);
		}

		return dataList;
	}

	public void updateMemberCouponUseInfo(Integer memberId, Integer combineOrderId, String logType) {
		Date date = new Date();
		MemberCouponExample couponExample = new MemberCouponExample();
		couponExample.createCriteria().andOrderIdEqualTo(combineOrderId).andMemberIdEqualTo(memberId);
		List<MemberCoupon> coupons = memberCouponService.selectByExample(couponExample);
		for (MemberCoupon memberCoupon : coupons) {
			MemberCouponLog log = new MemberCouponLog();
			log.setMemberCouponId(memberCoupon.getId());
			log.setOrderId(combineOrderId);
			log.setLogType(logType);
			log.setCreateBy(memberId);
			log.setCreateDate(date);
			memberCouponLogService.insertSelective(log);
		}
		if (logType.equals("2")) {
			// 还还的时候才进行更新，使用只要添加日志
			MemberCoupon memberCoupon = new MemberCoupon();
			memberCoupon.setStatus("0");
			memberCoupon.setUpdateBy(memberId);
			memberCoupon.setUpdateDate(date);
			updateByExampleSelective(memberCoupon, couponExample);
		}
	}

	public int updateRecCouponInfo(int couponId) {
		return memberCouponCustomMapper.updateRecCouponInfo(couponId);
	}

	public MemberCouponDTO getMemberCouponCount(Integer memberId) {

		return memberCouponCustomMapper.getMemberCouponCount(memberId);
	}

	public MemberCouponCountDTO countMemberNotUsedCoupon(Integer memberId) {
		return memberCouponCustomMapper.countMemberNotUsedCoupon(memberId);
	}

	public void insertBatchMemberCoupon(List<MemberCoupon> memberCouponList) {
		
		memberCouponCustomMapper.insertBatchMemberCoupon(memberCouponList);
	}

	/**
	 * @param tType 1 表示使用，2 表示查询
	 */
	public Map<String, Object> computingPlatPreferentialInfo(boolean isContainsSpecMcht, List<Map<String, Object>> salePriceMapList, BigDecimal totalShoppingAmount, String mermberPlatformCouponId
			, Integer memberId, Integer combineOrderId, Map<String, BigDecimal> activityTotalAmountMap, String tType, Set<Integer> mchtIdSet) {
		Date currentDate = new Date();
		BigDecimal zero = new BigDecimal("0");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(!StringUtil.isBlank(mermberPlatformCouponId)){
			List<Integer> memberCouponIdList = new ArrayList<Integer>();
			for (String memberCouponId : mermberPlatformCouponId.split(",")) {
				memberCouponIdList.add(Integer.valueOf(memberCouponId));
			}
			if (!StringUtil.isBlank(mermberPlatformCouponId)) {
				//过滤条件  开始<=now<=结束,未使用，memberId,会员优惠券id
				MemberCouponExample memberCouponExample = new MemberCouponExample();
				memberCouponExample.createCriteria().andMemberIdEqualTo(memberId)
				.andIdIn(memberCouponIdList).andDelFlagEqualTo("0").andStatusEqualTo("0")
				.andExpiryBeginDateLessThanOrEqualTo(currentDate).andExpiryEndDateGreaterThanOrEqualTo(currentDate);
				List<MemberCoupon> memberCoupons = selectByExample(memberCouponExample);
				MemberCoupon memberCoupon = memberCoupons.get(0);
				// 全平台优惠券已经使用过，标记为1
				if(CollectionUtils.isNotEmpty(memberCoupons) && "1".equals(tType)){
					MemberCoupon mermberPlatformCouponUpdate = new MemberCoupon();
					mermberPlatformCouponUpdate.setStatus("1");
					mermberPlatformCouponUpdate.setUseDate(currentDate);
					mermberPlatformCouponUpdate.setOrderId(combineOrderId);
					memberCouponMapper.updateByExampleSelective(mermberPlatformCouponUpdate, memberCouponExample);
				}
				Coupon coupon = couponService.selectByPrimaryKey(memberCoupon.getCouponId());
				String couponType = coupon.getCouponType();
				String typeIds = coupon.getTypeIds();
				BigDecimal mininum = coupon.getMinimum();
				BigDecimal money = coupon.getMoney();
				String couponPreferentialType = coupon.getPreferentialType();//1优惠券满减 2优惠券折扣
				String conditionType = coupon.getConditionType();//1无条件 2满多少元可用 3满多少件可用
				BigDecimal maxDiscountMoney = coupon.getMaxDiscountMoney();
				BigDecimal discount = coupon.getDiscount();//折扣
				Integer minicount = coupon.getMinicount();//最低几件
				BigDecimal categoryProductPreTotalAmountDtl = zero;//品类券优惠总金额
				List<Map<String, Object>> categoryPreList = new ArrayList<>();//有享受品类优惠的集合
				Integer categoryPreTotalQuantity = 0;
				BigDecimal categoryPreTotalAmount = zero;

				// 1696需求， 特殊商家不允许使用平台优惠券
				if(isContainsSpecMcht && !couponType.equals(Const.COUPON_CATEGORY_COUPON) && coupon.getBelong().equals("1")){
					logger.warn("特殊商家不允许使用平台优惠券");
					throw new ArgException("系统开了下小差，请重新提交订单");
				}
				// 1813需求，特殊商家商品不参与平台券优惠
				Set<Integer> specialMchtIdSet = configSpecialMchtService.findSpecialMchtIds(Lists.newArrayList(mchtIdSet));

				if(couponType.equals(Const.COUPON_CATEGORY_COUPON)){
					for (String ptId : typeIds.split(",")) {
						List<String> specMchtCodeList = commonService.listSpecMchtCode();
						if(CollectionUtils.isNotEmpty(salePriceMapList)){
							for (Map<String, Object> salePriceMap : salePriceMapList) {
								String productType1Id = salePriceMap.get("productType1Id").toString();
								if(!productType1Id.equals(ptId)){
									continue;
								}

								Integer mchtId = (Integer)salePriceMap.get("mchtId");
								MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
								if(isContainsSpecMcht && specMchtCodeList.contains(mchtInfo.getMchtCode())){
									logger.warn(StringUtil.buildMsg("特殊商家不允许使用平台优惠券【商家ID:{}】", mchtId));
									throw new ArgException("系统开了下小差，请重新提交订单");
								}
								if (specialMchtIdSet.contains(mchtId)) {
									continue; //特殊商家商品不参与平台券优惠
								}

								BigDecimal productPreAmountDtl = new BigDecimal(salePriceMap.get("productPreAmountDtl").toString()).add(new BigDecimal(salePriceMap.get("platformAmount").toString()));
								BigDecimal salePrice = new BigDecimal(salePriceMap.get("salePrice").toString());
								Integer quantity = Integer.parseInt(salePriceMap.get("quantity").toString());
								categoryPreTotalQuantity = categoryPreTotalQuantity + quantity;
								categoryPreTotalAmount = salePrice.multiply(new BigDecimal(quantity+"")).add(categoryPreTotalAmount).subtract(productPreAmountDtl);
								categoryPreList.add(salePriceMap);
							}
						}
					}
				}else if(couponType.equals(Const.COUPON_PLAT_COUPON)){
					if(CollectionUtils.isNotEmpty(salePriceMapList)){
						for (Map<String, Object> map : salePriceMapList) {
							Integer mchtId = (Integer) map.get("mchtId");
							if (specialMchtIdSet.contains(mchtId)) {
								continue; //特殊商家商品不参与平台券优惠
							}
							BigDecimal productPreAmountDtl = new BigDecimal(map.get("productPreAmountDtl").toString()).add(new BigDecimal(map.get("platformAmount").toString()));
							BigDecimal salePrice = new BigDecimal(map.get("salePrice").toString());
							Integer quantity = Integer.parseInt(map.get("quantity").toString());
							categoryPreTotalQuantity = categoryPreTotalQuantity + quantity;
							categoryPreTotalAmount = salePrice.multiply(new BigDecimal(quantity+"")).add(categoryPreTotalAmount).subtract(productPreAmountDtl);
							categoryPreList.add(map);
						}
					}
				}
				if(couponPreferentialType.equals("2")){//优惠券折扣
					money = categoryPreTotalAmount.multiply(new BigDecimal("1").subtract(discount)).setScale(2, BigDecimal.ROUND_DOWN);
					if(maxDiscountMoney != null && money.compareTo(maxDiscountMoney) > 0){
						money = maxDiscountMoney;
					}
				}
				if((couponPreferentialType.equals("1") && categoryPreTotalAmount.compareTo(mininum) >= 0) || 
						(couponPreferentialType.equals("2") && (conditionType.equals("1") || (conditionType.equals("2") && categoryPreTotalAmount.compareTo(mininum) >= 0) || (conditionType.equals("3") && categoryPreTotalQuantity >= minicount)))){
					int j = 0;
					for (Map<String, Object> map : categoryPreList) {
						BigDecimal productPreAmountDtl = new BigDecimal(map.get("productPreAmountDtl").toString());
						BigDecimal salePrice = new BigDecimal(map.get("salePrice").toString());
						BigDecimal platformAmount = new BigDecimal(map.get("platformAmount").toString());
						BigDecimal quantity = new BigDecimal(map.get("quantity").toString());
						String activityType = map.get("activityType").toString();
						productPreAmountDtl = productPreAmountDtl.add(platformAmount);
						j++;
						//品类总金额满足品类券最低金额
						BigDecimal categoryProductPreAmountDtl = zero;//品类商品优惠金额
						if(categoryPreList.size() == j){
							categoryProductPreAmountDtl = money.subtract(categoryProductPreTotalAmountDtl);
						}else{
							categoryProductPreAmountDtl = (salePrice.multiply(quantity).subtract(productPreAmountDtl)).multiply(money);
							categoryProductPreAmountDtl = categoryProductPreAmountDtl.divide(categoryPreTotalAmount, 2, BigDecimal.ROUND_DOWN);
						}
						categoryProductPreTotalAmountDtl = categoryProductPreTotalAmountDtl.add(categoryProductPreAmountDtl);
						map.put("platformAmount", categoryProductPreAmountDtl.add(platformAmount));
						activityTotalAmountMap.put(activityType, activityTotalAmountMap.get(activityType).subtract(categoryProductPreAmountDtl));
						if("1".equals(tType)){
							orderPreferentialInfoService.saveOrderPreferentialInfo("1", coupon.getId(), Integer.valueOf(map.get("orderDtlId").toString()),
									coupon.getRang(), coupon.getName(), categoryProductPreAmountDtl,
									coupon.getBelong(), memberId);
						}
					}
					totalShoppingAmount = totalShoppingAmount.subtract(categoryProductPreTotalAmountDtl);//所有优惠的总金额
				}
			}
		}
		returnMap.put("salePriceMapList", salePriceMapList);
		returnMap.put("totalShoppingAmount", totalShoppingAmount);
		returnMap.put("activityTotalAmountMap", activityTotalAmountMap);
		return returnMap;
	}

	public Map<String, Object> getProductCouponList(Integer memberId, Integer productId, List<Map<String, Object>> productCouponList, Map<Integer, Object> productIdMap) {
		List<Map<String, Object>> productCoupons = new ArrayList<>();
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("memberId", memberId);
		paramsMap.put("productId", productId);
		List<MemberCouponDTO> memberCoupons = findProductCouponList(paramsMap);
		if(CollectionUtils.isNotEmpty(memberCoupons)){
			for (MemberCouponDTO memberCoupon : memberCoupons) {
				Map<String, Object> dataMap = new HashMap<>();
				String id = memberCoupon.getId();
				String money = memberCoupon.getMoney();
				String minimum = StringUtil.isBlank(memberCoupon.getMinimum()) ? money : memberCoupon.getMinimum();
				dataMap.put("id", id);
				dataMap.put("money", money);
				dataMap.put("minimum", minimum);
				productCoupons.add(dataMap);
				if(!productIdMap.containsKey(Integer.parseInt(id))){
					productIdMap.put(Integer.parseInt(id), productId);
					productCouponList.add(dataMap);
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("productCouponList", productCouponList);
		map.put("productCoupons", productCoupons);
		map.put("productIdMap", productIdMap);
		return map;
	}

	public List<MemberCouponDTO> findProductCouponList(Map<String, Object> paramsMap) {
		
		return memberCouponCustomMapper.findProductCouponList(paramsMap);
	}

	public Map<String, Object> getProductCouponAmount(Integer productId, Integer productItemId, JSONObject reqDataJson,BigDecimal productAmount, Integer orderDtlId, String tType, Integer combineOrderId,boolean isSelftSpopMcht) {
		BigDecimal productCouponAmount = new BigDecimal("0");
		String belong = "";
		if(!JsonUtils.isEmpty(reqDataJson, "productCouponJson")){
			String productCouponJson = "";
			try {
				productCouponJson = URLDecoder.decode(reqDataJson.getString("productCouponJson"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new ArgException("格式异常");
			}
			JSONObject pcJson = JSONObject.fromObject(productCouponJson);
			if(pcJson.containsKey(productItemId+"")){
				Integer memberId = reqDataJson.getInt("memberId");
				Integer memberCouponId = pcJson.getInt(productItemId+"");
				MemberCoupon memberCoupon = findMemberCouponById(memberId, memberCouponId, new Date());
				if(memberCoupon == null){
					throw new ArgException("该商品券已被使用或已过期");
				}
				Coupon coupon = couponService.selectByPrimaryKey(memberCoupon.getCouponId());
				String typeIds = coupon.getTypeIds();
				belong = isSelftSpopMcht ? "1" :coupon.getBelong();
				String preferentialType = coupon.getPreferentialType() == null ? "" : coupon.getPreferentialType();
				if(!Const.COUPON_BRAND_PRODUCT_COUPON.equals(coupon.getCouponType())){
					throw new ArgException("数据异常，请联系客服处理.");
				}
				if(StringUtil.isBlank(coupon.getTypeIds())){
					throw new ArgException("数据异常，请联系客服处理..");
				}
				if(!typeIds.equals(productId+"")){
					throw new ArgException("数据异常，请联系客服处理...");
				}
				if(preferentialType.equals("1")){
					productCouponAmount = coupon.getMoney();
					if(productAmount.compareTo(productCouponAmount) <= 0){
						throw new ArgException("商品金额小于商品券的面额");
					}
					if("1".equals(tType)){
						memberCoupon.setStatus("1");
						memberCoupon.setOrderId(combineOrderId);
						memberCoupon.setUpdateDate(new Date());
						updateByPrimaryKeySelective(memberCoupon);
						orderPreferentialInfoService.saveOrderPreferentialInfo("1", memberCouponId,orderDtlId, coupon.getRang(), coupon.getName(), productCouponAmount, belong, memberId);
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("productCouponAmount", productCouponAmount);
		map.put("belong", belong);
		return map;
	}

	public void giveMemberCouponBySvip(String mobile,Integer memberCouponId, Integer memberId, HttpServletRequest request) {
		Date currentDate = new Date();
		final MemberInfo giveUser = memberInfoService.findModelByMobile(mobile);
		if(giveUser == null){
			throw new ArgException("该手机账号还未成为醒购会员！");
		}
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		String isGive = memberInfoService.getIsSvip(memberInfo, null);
		if(!"1".equals(isGive)){
			throw new ArgException("您不是SVIP专属会员，不能赠送会员优惠券！");
		}
		isGive = memberInfoService.getIsSvip(giveUser, null);
		if("1".equals(isGive)){
			throw new ArgException("对方也是SVIP哦！");
		}
		MemberCouponExample memberCouponExample = new MemberCouponExample();
		memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andIdEqualTo(memberCouponId).andDelFlagEqualTo("0")
		.andStatusEqualTo("0").andExpiryEndDateGreaterThanOrEqualTo(currentDate);
		List<MemberCoupon> memberCoupons = memberCouponMapper.selectByExample(memberCouponExample);
		if(CollectionUtils.isEmpty(memberCoupons)){
			throw new ArgException("该优惠券已过期或已被使用哦！");
		}
		MemberCoupon memberCoupon = memberCoupons.get(0);
		memberCoupon.setStatus("1");
		memberCoupon.setIsGive("1");
		memberCoupon.setRemarks("已赠送");
		int count = updateByExampleSelective(memberCoupon, memberCouponExample);
		if(count <= 0){
			throw new ArgException("该优惠券已过期或已被使用哦！");
		}
		MemberCoupon mc = new MemberCoupon();
		mc.setMemberId(giveUser.getId());
		mc.setCouponId(memberCoupon.getCouponId());
		mc.setRecDate(memberCoupon.getRecDate());
		mc.setExpiryBeginDate(memberCoupon.getExpiryBeginDate());
		mc.setExpiryEndDate(memberCoupon.getExpiryEndDate());
		mc.setStatus("0");
		mc.setReceiveType("6");
		mc.setCreateBy(giveUser.getId());
		mc.setCreateDate(currentDate);
		mc.setDelFlag("0");
		insertSelective(mc);
		String remarks = "SVIP会员{"+memberId+"}赠送优惠券{"+memberCoupon.getCouponId()+"}--->给会员{"+giveUser.getId()+"}";
		memberCouponLogService.addLog(memberCouponId, "3", null, memberId, remarks);
		String content = "您的好友"+memberInfo.getNick()+"给您赠送了一张醒购SVIP专属优惠券，快去查看吧→APP下载地址http://suo.im/52HfjJ";
		SendSmsThread sms = new SendSmsThread("", StringUtil.getIpAddr(request), mobile, "1", content);
		Thread thread = new Thread(sms);
		thread.start();
	}

	public List<MemberCoupon> findMemberCouponByCouponIds(Integer memberId, List<Integer> cIds) {
		Date currentDate = new Date();
		MemberCouponExample memberCouponExample = new MemberCouponExample();
		memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andCouponIdIn(cIds).andStatusEqualTo("0")
		.andExpiryBeginDateLessThanOrEqualTo(currentDate).andExpiryEndDateGreaterThanOrEqualTo(currentDate).andDelFlagEqualTo("0");
		return selectByExample(memberCouponExample);
	}

	public List<MemberCoupon> getMemberMchtCouponByMchtIdOrMemberId(Integer memberId, Integer mchtId, List<Integer> cIds) {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("memberId", memberId);
		paramsMap.put("mchtId", mchtId);
		paramsMap.put("couponIdList", cIds);
		return memberCouponCustomMapper.getMemberMchtCouponByMchtIdOrMemberId(paramsMap);
	}
	
	public Map<String, Object> addReceiveCoupon(String memberId, String type, Integer couponId, CouponExchangeCode couponExchangeCode,String receiveType) {
			Date expiryBeginDate = null;
			Date expiryEndDate = null;
			Date date = new Date();
			
			Coupon coupon = couponService.selectByPrimaryKey(couponId);
			if(coupon == null || coupon.getId() == null || Const.FLAG_TRUE.equals(coupon.getDelFlag())){
				throw new ArgException("优惠券已失效");
			}
			if(coupon.getExpiryType().equals("2")){
				expiryBeginDate = date;
				expiryEndDate = DateUtil.addDay(date, coupon.getExpiryDays());
			}else{
				expiryBeginDate = coupon.getExpiryBeginDate();
				expiryEndDate = coupon.getExpiryEndDate();
			}
			if(type.equals("1")){
				if(!coupon.getRecType().equals("3")){
					throw new ArgException("优惠券兑换类型错误");
				}
			}else{
				if(coupon.getRecType().equals("3")){
					throw new ArgException("优惠券领取类型错误");
				}
			}
			if(!coupon.getStatus().equals("1")){
				//启用状态(0未启用 1启用 2停用)
				throw new ArgException("优惠券未启用");
			}
			if(date.after(expiryEndDate)){
				if(type.equals("1")){
					throw new ArgException("该兑换码已过期");
				}else{
					throw new ArgException("领取优惠券过期");
				}
			}
			//判断优惠券是否可以领取
			//isCanReceive = true 可以领取
			//isCanReceive = false 不可以领取
			Map<String,Object> couponMap = isReceiveCoupon(Integer.valueOf(memberId),coupon);
			if(!(boolean) couponMap.get("isCanReceive")){
				throw new ArgException((String) couponMap.get("msg"));
			}
			if(coupon.getRecType().equals("2")){
				MemberAccountExample memberAccountExample = new MemberAccountExample();
				memberAccountExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId)).andDelFlagEqualTo("0");
				List<MemberAccount> memberAccounts = memberAccountService.selectByExample(memberAccountExample);
				if(CollectionUtils.isNotEmpty(memberAccounts)){
					MemberAccount memberAccount = memberAccounts.get(0);
					Integer integral = memberAccount.getIntegral();
					if(coupon.getNeedIntegral() <= integral){
						memberAccount.setIntegral(integral-coupon.getNeedIntegral());
						memberAccount.setUpdateBy(Integer.valueOf(memberId));
						memberAccount.setUpdateDate(new Date());
						memberAccountService.updateByPrimaryKeySelective(memberAccount);
					
						integralDtlService.addIntegralDtl(memberAccount.getId(),Const.INTEGRAL_TALLY_MODE_ACCOUNT,
						Const.INTEGRAL_TYPE_EXCHANGE,coupon.getNeedIntegral(),memberAccount.getIntegral(),null,Integer.valueOf(memberId),"1");
					}else{
						throw new ArgException("积分不足");
						//Map<String,Object> map = new HashMap<>();
						//map.put("isCanReceive", true);
						//map.put("mark", false);
						//map.put("integral", integral);
						//map.put("content", "积分不足");
						//return map;
					}
				}
			}else if(coupon.getRecType().equals("3")){
				if(couponExchangeCode == null){
					throw new ArgException("请用游离码兑换");
				}
			}
			String recType = "1";
			if("4".equals(coupon.getRecType())){
			recType = "5";
			}
			MemberCoupon memberCoupon = new MemberCoupon();
			memberCoupon.setMemberId(Integer.valueOf(memberId));
			memberCoupon.setCouponId(couponId);
			memberCoupon.setRecDate(new Date());
			memberCoupon.setExpiryBeginDate(expiryBeginDate);
			memberCoupon.setExpiryEndDate(expiryEndDate);
			memberCoupon.setDelFlag("0");
			memberCoupon.setReceiveType(StrKit.notBlank(receiveType) ? receiveType : recType);	// 前端有传领取方式优先设置
			memberCoupon.setCreateBy(Integer.valueOf(memberId));
			memberCoupon.setCreateDate(new Date());
			insertSelective(memberCoupon);
			//更改优惠券的领取数量
			Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
			coupon.setRecQuantity(recQuantity+1);
			couponService.updateByModel(coupon);
			//修改游离码打上已使用标志
			if(couponExchangeCode != null){
			couponExchangeCode.setIsExchange("1");
			couponExchangeCode.setCouponId(couponId);
			couponExchangeCode.setMemberCouponId(memberCoupon.getId());
			couponExchangeCode.setExchangeDate(date);
			couponExchangeCode.setUpdateBy(Integer.valueOf(memberId));
			couponExchangeCode.setUpdateDate(date);
			couponExchangeCodeService.updateByPrimaryKeySelective(couponExchangeCode);
			}
		
			return couponToMap(coupon);
	}
	
	private Map<String,Object> couponToMap(Coupon coupon){
		Date expiryBeginDate = coupon.getExpiryBeginDate();
		Date expiryEndDate = coupon.getExpiryEndDate();
		if(coupon.getExpiryType().equals("2")){
			Date date = new Date();
			expiryBeginDate = date;
			expiryEndDate = DateUtil.addDay(date, coupon.getExpiryDays());
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("expiryBeginDate", DateUtil.getFormatDate(expiryBeginDate, "yyyy.MM.dd HH:mm"));
		map.put("expiryEndDate", DateUtil.getFormatDate(expiryEndDate, "yyyy.MM.dd HH:mm"));
		map.put("preferentialType", coupon.getPreferentialType());
		map.put("money", coupon.getMoney());
		map.put("discount", coupon.getDiscount() != null ? coupon.getDiscount().multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString() : null);
		map.put("maxDiscountMoney", coupon.getMaxDiscountMoney());
		map.put("minimum", coupon.getMinimum());
		map.put("name", coupon.getName());
		map.put("recEach", coupon.getRecEach() == null ? 0 : coupon.getRecEach());
		return map;
	}


	/**
	 * 新人专享判断：
	 * 是否领取过新人专享红包
	 */
	public ReceivedRedInfo isReceivedRed(Integer memberId) {
		List<SysParamCfg> sysParamCfgs1 = DataDicUtil.getSysParamCfg("APP_RED_COUPON");
		//红包优惠券下架返回true 0 上架 1下架
		if (CollectionUtils.isEmpty(sysParamCfgs1)
				|| sysParamCfgs1.get(0).getParamValue() == null
				|| "1".equals(sysParamCfgs1.get(0).getParamValue())) {
			return ReceivedRedInfo.yes();
		}
		List<Integer> couponIdList = new ArrayList<Integer>();
		List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg("APP_EXPIRED_COUPON_IDS");
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){ //提取过期的优惠券，之前已领取过旧500大红包优惠券，新的也不能在领取了
			String couponIdsStr = sysParamCfgs.get(0).getParamValue();
			if(!StringUtil.isBlank(couponIdsStr)){
				for (String str : couponIdsStr.split(",")) {
					couponIdList.add(Integer.parseInt(str));
				}
			}
		}
		sysParamCfgs = DataDicUtil.getSysParamCfg("APP_COUPON_ID_BY_RED");
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			for (SysParamCfg sysParamCfg : sysParamCfgs) {
				if(!StringUtil.isBlank(sysParamCfg.getParamValue())){
					couponIdList.add(Integer.valueOf(sysParamCfg.getParamValue()));
				}
			}
		}
		Date date = new Date();
		if(CollectionUtils.isNotEmpty(couponIdList)){
			CouponExample couponExample = new CouponExample();
			couponExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIdList)
					.andRecBeginDateLessThanOrEqualTo(date).andRecEndDateGreaterThanOrEqualTo(date);
			List<Coupon> coupons = couponService.selectByExample(couponExample);
			if(CollectionUtils.isEmpty(coupons)){
				return ReceivedRedInfo.yes();
			}
		}
		if (memberId == null) {
			//如果活动还没下架，且用户id不存在
			//红包展示出来，前端引导用户去注册
			return ReceivedRedInfo.no();
		}
		MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
		if(memberInfo == null || memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){ //是游客
			return ReceivedRedInfo.no();
		}
		if (CollectionUtils.isEmpty(couponIdList)) {
			return ReceivedRedInfo.yes();
		}
		MemberCouponExample memberCouponExample = new MemberCouponExample();
		memberCouponExample.createCriteria().andCouponIdIn(couponIdList).andMemberIdEqualTo(memberId);
		List<MemberCoupon> memberCouponList = this.selectByExample(memberCouponExample);
		if (CollectionUtil.isEmpty(memberCouponList)) {
			return ReceivedRedInfo.no();
		}
		List<Integer> receivedCouponIds = Lists.newArrayListWithCapacity(memberCouponList.size());
		for (MemberCoupon memberCoupon : memberCouponList) {
			receivedCouponIds.add(memberCoupon.getCouponId());
		}
		return ReceivedRedInfo.yes().putAllReceived(receivedCouponIds);
	}

	public Map<Integer, Integer> countMemberRecQuantity(Integer memberId, List<Integer> couponIds) {
		if (CollectionUtils.isEmpty(couponIds)) return Collections.emptyMap();

		Map<String, Object> params = Maps.newHashMap();
		params.put("memberId", memberId);
		params.put("couponIds", couponIds);
		params.put("startOfCurrentDay", DateUtil.getStandardDate(new Date()));
		params.put("startOfCurrentMonth", DateUtil.getMonthBeginData(new Date()));
		List<Map<String, Object>> list = memberCouponCustomMapper.countMemberRecQuantity(params);
		if (CollectionUtils.isEmpty(list)) return Collections.emptyMap();

		Map<Integer, Integer> couponRecCountMap = Maps.newHashMap(); //key:couponId value:recQty
		for (Map<String, Object> couponCount : list) {
			couponRecCountMap.put(Integer.valueOf(couponCount.get("id").toString()), Integer.valueOf(couponCount.get("qty").toString()));
		}
		return couponRecCountMap;
	}

	public List<MemberCoupon> findMemberActiveCoupon(Integer memberId, List<Integer> couponIdList, Integer fetchSize) {
		if (CollectionUtils.isEmpty(couponIdList)) return Collections.emptyList();

		Map<String, Object> params = Maps.newHashMap();
		params.put("memberId", memberId);
		params.put("couponIdList", couponIdList);
		params.put("fetchSize", fetchSize);
		return memberCouponCustomMapper.findMemberActiveCoupon(params);
	}

}
