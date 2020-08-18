package com.jf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.*;
import com.jf.entity.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class MemberPvService extends BaseService<MemberPv, MemberPvExample> {

	private static Logger logger = LoggerFactory.getLogger(MemberPvService.class);

	@Autowired
	private MemberPvMapper memberPvMapper;

	@Autowired
	private MemberPvCustomMapper memberPvCustomMapper;

	@Autowired
	private MemberPvDtlMapper memberPvDtlMapper;

	@Autowired
	private MchtPvStatisticalMapper mchtPvStatisticalMapper;

	@Autowired
	private PlatformPvStatisticalMapper platformPvStatisticalMapper;

	@Autowired
	private ActivityPvStatisticalMapper activityPvStatisticalMapper;

	@Autowired
	private ActivityAreaPvStatisticalMapper activityAreaPvStatisticalMapper;

	@Autowired
	private AdPvStatisticalMapper adPvStatisticalMapper;

	@Autowired
	private MemberPvMiddleMapper memberPvMiddleMapper;

	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;

	@Autowired
	private MemberActionMapper memberActionMapper;

	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private AdInfoMapper adInfoMapper;

	@Autowired
	private ActivityCustomMapper activityCustomMapper;

	@Autowired
	private ColumnPvStatisticalMapper columnPvStatisticalMapper;

	@Autowired
	private SourceNicheCustomMapper sourceNicheCustomMapper;

	@Autowired
	private ColumnPvHourStatisticalMapper columnPvHourStatisticalMapper;
	@Autowired
	private TypeColumnPvStatisticalCustomMapper typeColumnPvStatisticalCustomMapper;

	@Autowired
	private TypeColumnPvStatisticalMapper typeColumnPvStatisticalMapper;

	@Autowired
	public void setMemberPvMapper(MemberPvMapper memberPvMapper) {
		this.setDao(memberPvMapper);
		this.memberPvMapper = memberPvMapper;
	}

	public static String pageClassify(String pageType) {
		String pageClassify = "";
		if(pageType.equals(Const.PAGE_TYPE1) || pageType.equals(Const.PAGE_TYPE2) || pageType.equals(Const.PAGE_TYPE3) ) {
			pageClassify = Const.PAGE_CLASSIFY1;
		} else if(pageType.equals(Const.PAGE_TYPE4) || pageType.equals(Const.PAGE_TYPE5) || pageType.equals(Const.PAGE_TYPE6)
				|| pageType.equals(Const.PAGE_TYPE7) || pageType.equals(Const.PAGE_TYPE8) || pageType.equals(Const.PAGE_TYPE9)
				|| pageType.equals(Const.PAGE_TYPE10) || pageType.equals(Const.PAGE_TYPE11) || pageType.equals(Const.PAGE_TYPE12)
				|| pageType.equals(Const.PAGE_TYPE13) ) {
			pageClassify = Const.PAGE_CLASSIFY2;
		} else if(pageType.equals(Const.PAGE_TYPE14) || pageType.equals(Const.PAGE_TYPE15) ) {
			pageClassify = Const.PAGE_CLASSIFY3;
		} else if(pageType.equals(Const.PAGE_TYPE16) ) {
			pageClassify = Const.PAGE_CLASSIFY4;
		} else if(pageType.equals(Const.PAGE_TYPE17) || pageType.equals(Const.PAGE_TYPE18) ) {
			pageClassify = Const.PAGE_CLASSIFY5;
		} else if(pageType.equals(Const.PAGE_TYPE19) || pageType.equals(Const.PAGE_TYPE20) || pageType.equals(Const.PAGE_TYPE21) ) {
			pageClassify = Const.PAGE_CLASSIFY6;
		} else if(pageType.equals(Const.PAGE_TYPE22) || pageType.equals(Const.PAGE_TYPE23) || pageType.equals(Const.PAGE_TYPE24) ) {
			pageClassify = Const.PAGE_CLASSIFY7;
		} else if(pageType.equals(Const.PAGE_TYPE25) || pageType.equals(Const.PAGE_TYPE26) ) {
			pageClassify = Const.PAGE_CLASSIFY8;
		} else if(pageType.equals(Const.PAGE_TYPE27) || pageType.equals(Const.PAGE_TYPE28) ) {
			pageClassify = Const.PAGE_CLASSIFY9;
		} else if(pageType.equals(Const.PAGE_TYPE29) ) {
			pageClassify = Const.PAGE_CLASSIFY10;
		} else if(pageType.equals(Const.PAGE_TYPE30) ) {
			pageClassify = Const.PAGE_CLASSIFY11;
		} else if(pageType.equals(Const.PAGE_TYPE31) || pageType.equals(Const.PAGE_TYPE32) || pageType.equals(Const.PAGE_TYPE33) ) {
			pageClassify = Const.PAGE_CLASSIFY12;
		} else if(pageType.equals(Const.PAGE_TYPE34) || pageType.equals(Const.PAGE_TYPE35) || pageType.equals(Const.PAGE_TYPE89) ) {
			pageClassify = Const.PAGE_CLASSIFY13;
		} else if(pageType.equals(Const.PAGE_TYPE36) || pageType.equals(Const.PAGE_TYPE37) || pageType.equals(Const.PAGE_TYPE38)
				|| pageType.equals(Const.PAGE_TYPE39) || pageType.equals(Const.PAGE_TYPE40) || pageType.equals(Const.PAGE_TYPE41)
				|| pageType.equals(Const.PAGE_TYPE42) || pageType.equals(Const.PAGE_TYPE43) || pageType.equals(Const.PAGE_TYPE44)
				|| pageType.equals(Const.PAGE_TYPE45) || pageType.equals(Const.PAGE_TYPE46) || pageType.equals(Const.PAGE_TYPE47)
				|| pageType.equals(Const.PAGE_TYPE48) || pageType.equals(Const.PAGE_TYPE49) || pageType.equals(Const.PAGE_TYPE50)
				|| pageType.equals(Const.PAGE_TYPE51) || pageType.equals(Const.PAGE_TYPE52) || pageType.equals(Const.PAGE_TYPE53)
				|| pageType.equals(Const.PAGE_TYPE54) || pageType.equals(Const.PAGE_TYPE92) || pageType.equals(Const.PAGE_TYPE93) ) {
			pageClassify = Const.PAGE_CLASSIFY14;
		} else if(pageType.equals(Const.PAGE_TYPE55) || pageType.equals(Const.PAGE_TYPE56) || pageType.equals(Const.PAGE_TYPE57)
				|| pageType.equals(Const.PAGE_TYPE58) ) {
			pageClassify = Const.PAGE_CLASSIFY15;
		} else if(pageType.equals(Const.PAGE_TYPE59) ) {
			pageClassify = Const.PAGE_CLASSIFY16;
		} else if(pageType.equals(Const.PAGE_TYPE60) || pageType.equals(Const.PAGE_TYPE61) || pageType.equals(Const.PAGE_TYPE62)
				|| pageType.equals(Const.PAGE_TYPE63) || pageType.equals(Const.PAGE_TYPE64) || pageType.equals(Const.PAGE_TYPE65)
				|| pageType.equals(Const.PAGE_TYPE66) || pageType.equals(Const.PAGE_TYPE67) || pageType.equals(Const.PAGE_TYPE68)
				|| pageType.equals(Const.PAGE_TYPE69) ) {
			pageClassify = Const.PAGE_CLASSIFY17;
		} else if(pageType.equals(Const.PAGE_TYPE70) || pageType.equals(Const.PAGE_TYPE71) ) {
			pageClassify = Const.PAGE_CLASSIFY18;
		} else if(pageType.equals(Const.PAGE_TYPE72) || pageType.equals(Const.PAGE_TYPE73) || pageType.equals(Const.PAGE_TYPE74) ) {
			pageClassify = Const.PAGE_CLASSIFY19;
		} else if(pageType.equals(Const.PAGE_TYPE75) ) {
			pageClassify = Const.PAGE_CLASSIFY20;
		} else if(pageType.equals(Const.PAGE_TYPE76) ) {
			pageClassify = Const.PAGE_CLASSIFY21;
		} else if(pageType.equals(Const.PAGE_TYPE77) ) {
			pageClassify = Const.PAGE_CLASSIFY22;
		} else if(pageType.equals(Const.PAGE_TYPE78) ) {
			pageClassify = Const.PAGE_CLASSIFY23;
		} else if(pageType.equals(Const.PAGE_TYPE79) || pageType.equals(Const.PAGE_TYPE91) ) {
			pageClassify = Const.PAGE_CLASSIFY24;
		} else if(pageType.equals(Const.PAGE_TYPE80) ) {
			pageClassify = Const.PAGE_CLASSIFY25;
		} else if(pageType.equals(Const.PAGE_TYPE81) || pageType.equals(Const.PAGE_TYPE82) ) {
			pageClassify = Const.PAGE_CLASSIFY26;
		} else if(pageType.equals(Const.PAGE_TYPE83) ) {
			pageClassify = Const.PAGE_CLASSIFY27;
		} else if(pageType.equals(Const.PAGE_TYPE84) ) {
			pageClassify = Const.PAGE_CLASSIFY28;
		} else if(pageType.equals(Const.PAGE_TYPE85) ) {
			pageClassify = Const.PAGE_CLASSIFY29;
		} else if(pageType.equals(Const.PAGE_TYPE86) ) {
			pageClassify = Const.PAGE_CLASSIFY30;
		} else if(pageType.equals(Const.PAGE_TYPE87) ) {
			pageClassify = Const.PAGE_CLASSIFY31;
		} else if(pageType.equals(Const.PAGE_TYPE88) ) {
			pageClassify = Const.PAGE_CLASSIFY32;
		} else if(pageType.equals(Const.PAGE_TYPE90) ) {
			pageClassify = Const.PAGE_CLASSIFY33;
		} else if(pageType.equals(Const.PAGE_TYPE94) ) {
			pageClassify = Const.PAGE_CLASSIFY34;
		} else if(pageType.equals(Const.PAGE_TYPE95) || pageType.equals(Const.PAGE_TYPE96) || pageType.equals(Const.PAGE_TYPE97) ) {
			pageClassify = Const.PAGE_CLASSIFY35;
		} else if(pageType.equals(Const.PAGE_TYPE98) ) {
			pageClassify = Const.PAGE_CLASSIFY36;
		} else if(pageType.equals(Const.PAGE_TYPE99) ) {
			pageClassify = Const.PAGE_CLASSIFY37;
		}
		return pageClassify;
	}

	public static String pageType(String pageType, String valueId) {
		if("5".equals(pageType) ) { //单品爆款分类
			if(Const.PRODUCT_TYPE2.equals(valueId) ) { //运动户外
				pageType = Const.PAGE_TYPE5;
			}else if(Const.PRODUCT_TYPE4.equals(valueId) ) { //服装配饰
				pageType = Const.PAGE_TYPE6;
			}else if(Const.PRODUCT_TYPE3.equals(valueId) ) { //鞋靴箱包
				pageType = Const.PAGE_TYPE7;
			}else if(Const.PRODUCT_TYPE430.equals(valueId) ) { //钟表珠宝
				pageType = Const.PAGE_TYPE8;
			}else if(Const.PRODUCT_TYPE5.equals(valueId) ) { //生活家居
				pageType = Const.PAGE_TYPE9;
			}else if(Const.PRODUCT_TYPE705.equals(valueId) ) { //数码家电
				pageType = Const.PAGE_TYPE10;
			}else if(Const.PRODUCT_TYPE858.equals(valueId) ) { //美妆个护
				pageType = Const.PAGE_TYPE12;
			}else if(Const.PRODUCT_TYPE954.equals(valueId) ) { //母婴童装
				pageType = Const.PAGE_TYPE13;
			}else if(Const.PRODUCT_TYPE1047.equals(valueId) ) { //食品超市
				pageType = Const.PAGE_TYPE11;
			}
		}else if("60".equals(pageType)) { //特卖分类
			if(Const.PRODUCT_TYPE2.equals(valueId) ) { //运动户外
				pageType = Const.PAGE_TYPE60;
			}else if(Const.PRODUCT_TYPE4.equals(valueId) ) { //服装配饰
				pageType = Const.PAGE_TYPE61;
			}else if(Const.PRODUCT_TYPE3.equals(valueId) ) { //鞋靴箱包
				pageType = Const.PAGE_TYPE62;
			}else if(Const.PRODUCT_TYPE430.equals(valueId) ) { //钟表珠宝
				pageType = Const.PAGE_TYPE63;
			}else if(Const.PRODUCT_TYPE5.equals(valueId) ) { //生活家居
				pageType = Const.PAGE_TYPE64;
			}else if(Const.PRODUCT_TYPE705.equals(valueId) ) { //数码家电
				pageType = Const.PAGE_TYPE65;
			}else if(Const.PRODUCT_TYPE858.equals(valueId) ) { //美妆个护
				pageType = Const.PAGE_TYPE67;
			}else if(Const.PRODUCT_TYPE954.equals(valueId) ) { //母婴童装
				pageType = Const.PAGE_TYPE68;
			}else if(Const.PRODUCT_TYPE1047.equals(valueId) ) { //食品超市
				pageType = Const.PAGE_TYPE66;
			}else if("0".equals(valueId)){ //预告
				pageType = Const.PAGE_TYPE69;
			}
		}
		return pageType;
	}
	
	/**
	 * 
	 * @MethodName: parseFlowData
	 * @Description: (数据解析)
	 * @author Pengl
	 * @date 2019年5月31日 下午3:15:01
	 */
	public void flowParseData(Date startTime, Date endTime) {
		int pvId = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//浏览记录
			logger.info(DateUtil.getStandardDateTime()+"浏览记录"+sdf.format(startTime)+":start");
			MemberPvMiddleExample memberPvMiddleExample = new MemberPvMiddleExample();
			memberPvMiddleExample.createCriteria().andDelFlagEqualTo("0").andSourceTypeEqualTo("1")
				.andCreateDateGreaterThanOrEqualTo(startTime).andCreateDateLessThan(endTime);
			List<MemberPvMiddle> memberPvMiddleList = memberPvMiddleMapper.selectByExampleWithBLOBs(memberPvMiddleExample);
			List<MemberPv> memberPvList = new ArrayList<MemberPv>();
			MemberPv memberPv = null;
			for(MemberPvMiddle memberPvMiddle : memberPvMiddleList) {
				logger.info("流量统计数据浏览记录解析"+pvId);
				try {
					if(!StringUtil.isEmpty(memberPvMiddle.getContent()) ) {
						pvId = memberPvMiddle.getId();
						JSONArray jsonArray = JSON.parseArray(memberPvMiddle.getContent());
						for(Object object : jsonArray) {
							JSONObject jo = JSONObject.fromObject(object);
							String pageClassify = pageClassify(jo.getString("pageType"));
							Date createDate = null;
							if(jo.containsKey("createDate") && !StringUtil.isEmpty(jo.getString("createDate")) ) {
								createDate = new Date(Long.parseLong(jo.getString("createDate")));
							}else {
								createDate = memberPvMiddle.getCreateDate();
							}
							/*if(createDate.compareTo(DateUtil.getDate(sdf.format(DateUtil.getDateAfter(DateUtil.getDate(), -1)), "yyyy-MM-dd")) == -1 ) {
								continue;
							}*/
							Integer mchtId = null;
							if(!StringUtil.isEmpty(jo.getString("mchtId"))) {
								mchtId = jo.getInt("mchtId");
							}
							if(pageClassify != null ) {
								if("9".equals(pageClassify) && !StringUtil.isEmpty(jo.getString("valueId")) ) { //9.特卖
									Activity activity = activityMapper.selectByPrimaryKey(jo.getInt("valueId"));
									if(activity != null ) {
										mchtId = activity.getMchtId();
									}
								}else if("10".equals(pageClassify) && !StringUtil.isEmpty(jo.getString("valueId")) ) { //10.商品
									Product product = productMapper.selectByPrimaryKey(jo.getInt("valueId"));
									if(product != null ) {
										mchtId = product.getMchtId();
									}
								}
							}
							String deviceNumber = jo.containsKey("deviceNumber")?jo.getString("deviceNumber"):"";
							String columnType = jo.containsKey("columnType")?jo.getString("columnType"):null;
							Integer columnId = null;
							if(("2".equals(columnType) || "3".equals(columnType)) && jo.containsKey("columnId") && !StringUtil.isEmpty(jo.getString("columnId")) ) {
								columnId = Integer.parseInt(jo.getString("columnId"));
							}
							Integer memberId = null;
							if(jo.containsKey("memberId") && !StringUtil.isEmpty(jo.getString("memberId")) ) {
								memberId = Integer.parseInt(jo.getString("memberId"));
							}
							memberPv = new MemberPv();
							memberPv.setPvId(jo.getString("pvId"));
							memberPv.setMemberId(memberId);
							memberPv.setDeviceNumber(deviceNumber);
							memberPv.setColumnType(columnType);
							memberPv.setColumnId(columnId);
							memberPv.setPageClassify(pageClassify);
							String pageType = jo.getString("pageType");
							pageType = pageType(pageType, jo.getString("valueId"));
							memberPv.setPageType(pageType);
							memberPv.setMchtId(mchtId);
							memberPv.setValueId(StringUtil.isEmpty(jo.getString("valueId"))?null:jo.getInt("valueId"));
							memberPv.setClientSource(memberPvMiddle.getClientSource());
							memberPv.setFromPageType(jo.containsKey("fromPageType")?jo.getString("fromPageType"):"");
							memberPv.setFromPvId(jo.containsKey("fromPvId")?jo.getString("fromPvId"):"");
							memberPv.setStayTime(jo.getInt("stayTime"));
							memberPv.setCreateDate(createDate);
							memberPv.setUpdateDate(DateUtil.getDate());
							if(memberPvList.size() > Const.pvListNum ) {
								memberPvCustomMapper.insertMemberPvList(memberPvList);
								memberPvList.clear();
							}
							memberPvList.add(memberPv);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.info("流量统计数据解析错误=====pvId==========>"+pvId);
					continue;
				}
			}
			if(memberPvList.size() > 0 ) {
				memberPvCustomMapper.insertMemberPvList(memberPvList);
                memberPvList.clear();
			}
			logger.info(DateUtil.getStandardDateTime()+"浏览记录"+sdf.format(startTime)+":end");
			
			//浏览明细
			logger.info(DateUtil.getStandardDateTime()+"浏览明细"+sdf.format(startTime)+":start");
			MemberPvMiddleExample memberPvMiddleDtlExample = new MemberPvMiddleExample();
			memberPvMiddleDtlExample.createCriteria().andDelFlagEqualTo("0").andSourceTypeEqualTo("2")
				.andCreateDateGreaterThanOrEqualTo(startTime).andCreateDateLessThan(endTime);
			List<MemberPvMiddle> memberPvMiddleDtlList = memberPvMiddleMapper.selectByExampleWithBLOBs(memberPvMiddleDtlExample);
			List<MemberPvDtl> memberPvDtlList = new ArrayList<MemberPvDtl>();
			MemberPvDtl memberPvDtl = null;
			for(MemberPvMiddle memberPvMiddleDtl : memberPvMiddleDtlList) {
				logger.info("流量统计数据明细解析"+pvId);
				try {
					if(!StringUtil.isEmpty(memberPvMiddleDtl.getContent()) ) {
						pvId = memberPvMiddleDtl.getId();
						JSONArray jsonArray = JSON.parseArray(memberPvMiddleDtl.getContent());
						for(Object object : jsonArray) {
							JSONObject jo = JSONObject.fromObject(object);
							Date createDate = null;
							if(jo.containsKey("createDate") && !StringUtil.isEmpty(jo.getString("createDate")) ) {
								createDate = new Date(Long.parseLong(jo.getString("createDate")));
							}else {
								createDate = memberPvMiddleDtl.getCreateDate();
							}
							/*if(createDate.compareTo(DateUtil.getDate(sdf.format(DateUtil.getDateAfter(DateUtil.getDate(), -1)), "yyyy-MM-dd")) == -1 ) {
								continue;
							}*/
							Integer memberId = null;
							if(jo.containsKey("memberId") && !StringUtil.isEmpty(jo.getString("memberId")) ) {
								memberId = Integer.parseInt(jo.getString("memberId"));
							}
							Integer dtlItemId = null;
							if(jo.containsKey("dtlItemId") && !StringUtil.isEmpty(jo.getString("dtlItemId")) ) {
								dtlItemId = Integer.parseInt(jo.getString("dtlItemId"));
							}
							Integer clickCount = null;
							if(jo.containsKey("clickCount") && !StringUtil.isEmpty(jo.getString("clickCount")) ) {
								clickCount = Integer.parseInt(jo.getString("clickCount"));
							}
							memberPvDtl = new MemberPvDtl();
							memberPvDtl.setClientSource(memberPvMiddleDtl.getClientSource());
							memberPvDtl.setMemberId(memberId);
							memberPvDtl.setDeviceNumber(jo.containsKey("deviceNumber")?jo.getString("deviceNumber"):"");
							memberPvDtl.setMemberPvId(jo.containsKey("memberPvId")?jo.getString("memberPvId"):"");
							memberPvDtl.setDtlItemType(jo.containsKey("dtlItemType")?jo.getString("dtlItemType"):"");
							memberPvDtl.setDtlItemId(dtlItemId);
							memberPvDtl.setPosition(jo.containsKey("position")?jo.getString("position"):"");
							memberPvDtl.setClickCount(clickCount);
							memberPvDtl.setCreateDate(createDate);
							memberPvDtl.setUpdateDate(DateUtil.getDate());
							if(memberPvDtlList.size() > Const.pvListNum ) {
								memberPvCustomMapper.insertMemberPvDtlList(memberPvDtlList);
								memberPvDtlList.clear();
							}
							memberPvDtlList.add(memberPvDtl);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.info("流量统计数据明细解析错误=====pvId==========>"+pvId);
					continue;
				}
			}
			if(memberPvDtlList.size() > 0 ) {
				memberPvCustomMapper.insertMemberPvDtlList(memberPvDtlList);
                memberPvDtlList.clear();
			}
			logger.info(DateUtil.getStandardDateTime()+"浏览明细"+sdf.format(startTime)+":end");
			
			//行为明细
			logger.info(DateUtil.getStandardDateTime()+"行为明细"+sdf.format(startTime)+":start");
			MemberPvMiddleExample memberPvMiddleActionExample = new MemberPvMiddleExample();
			memberPvMiddleActionExample.createCriteria().andDelFlagEqualTo("0").andSourceTypeEqualTo("3")
				.andCreateDateGreaterThanOrEqualTo(startTime).andCreateDateLessThan(endTime);
			List<MemberPvMiddle> memberPvMiddleActionList = memberPvMiddleMapper.selectByExampleWithBLOBs(memberPvMiddleActionExample);
			List<MemberAction> memberActions = new ArrayList<MemberAction>();
			MemberAction memberAction = null; 
			for(MemberPvMiddle memberPvMiddleAction : memberPvMiddleActionList) {
				try {
					if(!StringUtil.isEmpty(memberPvMiddleAction.getContent()) ) {
						pvId = memberPvMiddleAction.getId();
						JSONArray jsonArray = JSON.parseArray(memberPvMiddleAction.getContent());
						for(Object object : jsonArray) {
							JSONObject jo = JSONObject.fromObject(object);
							Date createDate = null;
							if(jo.containsKey("createDate") && !StringUtil.isEmpty(jo.getString("createDate")) ) {
								createDate = new Date(Long.parseLong(jo.getString("createDate")));
							}else {
								createDate = memberPvMiddleAction.getCreateDate();
							}
							/*if(createDate.compareTo(DateUtil.getDate(sdf.format(DateUtil.getDateAfter(DateUtil.getDate(), -1)), "yyyy-MM-dd")) == -1 ) {
								continue;
							}*/
							Integer memberId = null;
							if(jo.containsKey("memberId") && !StringUtil.isEmpty(jo.getString("memberId")) ) {
								memberId = Integer.parseInt(jo.getString("memberId"));
							}
							Integer actionItemId = null;
							if(jo.containsKey("actionItemId") && !StringUtil.isEmpty(jo.getString("actionItemId")) ) {
								actionItemId = Integer.parseInt(jo.getString("actionItemId"));
							}
							String actionType = jo.containsKey("actionType")?jo.getString("actionType"):"";
							memberAction = new MemberAction();
							memberAction.setClientSource(memberPvMiddleAction.getClientSource());
							memberAction.setMemberId(memberId);
							memberAction.setDeviceNumber(jo.containsKey("deviceNumber")?jo.getString("deviceNumber"):"");
                            String columnType = jo.containsKey("columnType")?jo.getString("columnType"):null;
							memberAction.setColumnType(columnType);
							Integer columnId = null;
							if(("2".equals(columnType) || "3".equals(columnType) || "30".equals(columnType)) && jo.containsKey("columnId") && !StringUtil.isEmpty(jo.getString("columnId")) ) {
								columnId = Integer.parseInt(jo.getString("columnId"));
							}
							memberAction.setColumnId(columnId);
							memberAction.setMemberPvId(jo.containsKey("memberPvId")?jo.getString("memberPvId"):"");
							memberAction.setActionType(actionType);
							memberAction.setActionItemId(actionItemId);
							memberAction.setCreateDate(createDate);
							memberAction.setUpdateDate(DateUtil.getDate());
							if(!StringUtil.isEmpty(actionType) && "14".equals(actionType) && actionItemId != null ) {
								MemberActionExample memberActionExample = new MemberActionExample();
								memberActionExample.createCriteria().andDelFlagEqualTo("0").andActionTypeEqualTo(actionType).andActionItemIdEqualTo(actionItemId);
								List<MemberAction> memberActionList = memberActionMapper.selectByExample(memberActionExample);
								if(memberActionList != null && memberActionList.size() > 0 ) {
									memberActionMapper.updateByExampleSelective(memberAction, memberActionExample);
									continue;
								}
								memberActionMapper.insertSelective(memberAction);
							}else {
								if(memberActions.size() > Const.pvListNum ) {
									memberPvCustomMapper.insertMemberActionList(memberActions);
									memberActions.clear();
								}
								memberActions.add(memberAction);
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.info("流量统计数据解析错误=====pvId==========>"+pvId);
					continue;
				}
			}
			if(memberActions.size() > 0 ) {
				memberPvCustomMapper.insertMemberActionList(memberActions);
				memberActions.clear();
			}
			logger.info(DateUtil.getStandardDateTime()+"行为明细"+sdf.format(startTime)+":end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(pvId+"");
		}
	}
	
	/**
	 * 
	 * @MethodName: flowStatisticsData
	 * @Description: (流量统计)
	 * @author Pengl
	 * @throws ParseException 
	 * @date 2019年5月31日 下午3:15:30
	 */
	public void flowStatisticsData(String batchDate,Boolean flag) throws ParseException {
		String beginDate = batchDate + " 00:00:00";
		String endDate = batchDate + " 23:59:59";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginDate", beginDate);
		paramMap.put("endDate", endDate);
		paramMap.put("flag", flag);

		//商家流量统计
		logger.info(DateUtil.getStandardDateTime()+"商家流量统计:start");
		List<Map<String, Object>> mchtPvStatisticalList = memberPvCustomMapper.getMchtPvStatisticalList(paramMap);
		for(Map<String, Object> m : mchtPvStatisticalList ) {
			MchtPvStatistical mchtPvStatistical = new MchtPvStatistical();
			String[] payAmountS = m.get("pay_amount").toString().split(",");
			BigDecimal payAmount = new BigDecimal(payAmountS[0]);
			Integer paySubOrderCount = Integer.parseInt(payAmountS[1]);
			Integer payMemberCount = Integer.parseInt(payAmountS[2]);
			Integer payCombineOrderCount = Integer.parseInt(payAmountS[3]);
			Integer payProductCount = Integer.parseInt(payAmountS[4]);
			mchtPvStatistical.setMchtId(Integer.parseInt(m.get("mcht_id").toString()));
			mchtPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
			mchtPvStatistical.setTotalVisitorCount(Integer.parseInt(m.get("total_visitor_count").toString()));
			mchtPvStatistical.setTotalVisitorCountTourist(Integer.parseInt(m.get("total_visitor_count_tourist").toString()));
			mchtPvStatistical.setTotalPvCount(Integer.parseInt(m.get("total_pv_count").toString()));
			mchtPvStatistical.setTotalPvCountTourist(Integer.parseInt(m.get("total_pv_count_tourist").toString()));
			mchtPvStatistical.setPayAmount(payAmount);
			mchtPvStatistical.setPayCombineOrderCount(payCombineOrderCount);
			mchtPvStatistical.setPaySubOrderCount(paySubOrderCount);
			mchtPvStatistical.setPayProductCount(payProductCount);
			mchtPvStatistical.setPayMemberCount(payMemberCount);
			mchtPvStatistical.setCreateDate(DateUtil.getDate());
			if(flag){
				MchtPvStatisticalExample mchtPvStatisticalExample = new MchtPvStatisticalExample();
				mchtPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
						.andMchtIdEqualTo(mchtPvStatistical.getMchtId())
						.andStatisticalDateEqualTo(mchtPvStatistical.getStatisticalDate());
				mchtPvStatistical.setCreateDate(null);
				mchtPvStatistical.setUpdateDate(DateUtil.getDate());
				if(mchtPvStatisticalMapper.updateByExampleSelective(mchtPvStatistical, mchtPvStatisticalExample) == 0) {
					mchtPvStatistical.setCreateDate(DateUtil.getDate());
					mchtPvStatistical.setUpdateDate(null);
					mchtPvStatisticalMapper.insertSelective(mchtPvStatistical);
				}
			}else {
				mchtPvStatisticalMapper.insertSelective(mchtPvStatistical);
			}
		}
		logger.info(DateUtil.getStandardDateTime()+"商家流量统计:end");
		
		//平台流量统计
		logger.info(DateUtil.getStandardDateTime()+"平台流量统计:start");
		List<Map<String, Object>> platformPvStatisticalList = memberPvCustomMapper.getPlatformPvStatisticalList(paramMap);
		for(Map<String, Object> m : platformPvStatisticalList ) {
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("payDate", m.get("statistical_date"));
			String sourceClient = m.get("client_source").toString();
			if("1".equals(sourceClient) ) {
				pMap.put("sourceClient", "1");
			}else if("2".equals(sourceClient) ) {
				pMap.put("sourceClient", "2");
			}else if("3".equals(sourceClient) ) {
				pMap.put("sourceClient", "4");
			}else if("4".equals(sourceClient) ) {
				pMap.put("sourceClient", "3");
			}else if("5".equals(sourceClient) ) {
				pMap.put("sourceClient", "5");
			}else if("6".equals(sourceClient) ) {
				pMap.put("sourceClient", "6");
			}
			if(pMap.containsKey("sourceClient") ) {
				Map<String, Object> platformPvStatisticalOrderMap = memberPvCustomMapper.getPlatformPvStatisticalOrder(pMap);
				PlatformPvStatistical platformPvStatistical = new PlatformPvStatistical();
				platformPvStatistical.setClientSource(m.get("client_source").toString());
				platformPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
				platformPvStatistical.setTotalVisitorCount(Integer.parseInt(m.get("total_visitor_count").toString()));
				platformPvStatistical.setTotalVisitorCountTourist(Integer.parseInt(m.get("total_visitor_count_tourist").toString()));
				platformPvStatistical.setTotalPvCount(Integer.parseInt(m.get("total_pv_count").toString()));
				platformPvStatistical.setTotalPvCountTourist(Integer.parseInt(m.get("total_pv_count_tourist").toString()));
				platformPvStatistical.setPayAmount(new BigDecimal(platformPvStatisticalOrderMap.get("pay_amount").toString()));
				platformPvStatistical.setPayCombineOrderCount(Integer.parseInt(platformPvStatisticalOrderMap.get("pay_combine_order_count").toString()));
				platformPvStatistical.setPaySubOrderCount(Integer.parseInt(platformPvStatisticalOrderMap.get("pay_sub_order_count").toString()));
				platformPvStatistical.setPayProductCount(Integer.parseInt(platformPvStatisticalOrderMap.get("pay_product_count").toString()));
				platformPvStatistical.setPayMemberCount(Integer.parseInt(platformPvStatisticalOrderMap.get("pay_member_count").toString()));
				platformPvStatistical.setCreateDate(DateUtil.getDate());
				if(flag) {
					PlatformPvStatisticalExample platformPvStatisticalExample = new PlatformPvStatisticalExample();
					platformPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
							.andClientSourceEqualTo(platformPvStatistical.getClientSource())
							.andStatisticalDateEqualTo(platformPvStatistical.getStatisticalDate());
					platformPvStatistical.setCreateDate(null);
					platformPvStatistical.setUpdateDate(DateUtil.getDate());
					if(platformPvStatisticalMapper.updateByExampleSelective(platformPvStatistical, platformPvStatisticalExample) == 0) {
						platformPvStatistical.setCreateDate(DateUtil.getDate());
						platformPvStatistical.setUpdateDate(null);
						platformPvStatisticalMapper.insertSelective(platformPvStatistical);
					}
				}else {
					platformPvStatisticalMapper.insertSelective(platformPvStatistical);
				}
			}
		}
		logger.info(DateUtil.getStandardDateTime()+"平台流量统计:end");
		
		//每日特卖统计
		logger.info(DateUtil.getStandardDateTime()+"每日特卖统计:start");
		List<Map<String, Object>> activityPvStatisticalList = memberPvCustomMapper.getActivityPvStatisticalList(paramMap);
		for(Map<String, Object> m : activityPvStatisticalList ) {
			ActivityPvStatistical activityPvStatistical = new ActivityPvStatistical();
			activityPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
			activityPvStatistical.setActivityId(Integer.parseInt(m.get("activity_id").toString()));

			Map<String, Object> pmMap = new HashMap<String, Object>();
			pmMap.put("statisticalDate", m.get("statistical_date").toString());
			pmMap.put("activityId", m.get("activity_id").toString());
			String homePagePosition = memberPvCustomMapper.getHomePagePosition(pmMap);
			String classifyPagePosition = memberPvCustomMapper.getClassifyPagePosition(pmMap);
			Integer homePageExposureCount = memberPvCustomMapper.getHomePageExposureCount(pmMap);
			Integer classifyPageExposureCount = memberPvCustomMapper.getClassifyPageExposureCount(pmMap);
			activityPvStatistical.setHomePagePosition(homePagePosition==null?"":homePagePosition);
			activityPvStatistical.setClassifyPagePosition(classifyPagePosition==null?"":classifyPagePosition);
			activityPvStatistical.setHomePageExposureCount(homePageExposureCount);
			activityPvStatistical.setClassifyPageExposureCount(classifyPageExposureCount);
			activityPvStatistical.setTotalVisitorCount(Integer.parseInt(m.get("total_visitor_count").toString()));
			activityPvStatistical.setTotalVisitorCountTourist(Integer.parseInt(m.get("total_visitor_count_tourist").toString()));
			activityPvStatistical.setTotalPvCount(Integer.parseInt(m.get("total_pv_count").toString()));
			activityPvStatistical.setTotalPvCountTourist(Integer.parseInt(m.get("total_pv_count_tourist").toString()));

			String[] payAmountS = m.get("pay_amount").toString().split(",");
			BigDecimal payAmount = new BigDecimal(payAmountS[0]);
			Integer paySubOrderCount = Integer.parseInt(payAmountS[1]);
			Integer payCombineOrderCount = Integer.parseInt(payAmountS[2]);
			activityPvStatistical.setPayAmount(payAmount);
			activityPvStatistical.setPaySubOrderCount(paySubOrderCount);
			activityPvStatistical.setPayCombineOrderCount(payCombineOrderCount);

			activityPvStatistical.setCreateDate(DateUtil.getDate());

			if(flag) {
				ActivityPvStatisticalExample activityPvStatisticalExample = new ActivityPvStatisticalExample();
				activityPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
						.andActivityIdEqualTo(activityPvStatistical.getActivityId())
						.andStatisticalDateEqualTo(activityPvStatistical.getStatisticalDate());
				activityPvStatistical.setCreateDate(null);
				activityPvStatistical.setUpdateDate(DateUtil.getDate());
				if(activityPvStatisticalMapper.updateByExampleSelective(activityPvStatistical, activityPvStatisticalExample) == 0) {
					activityPvStatistical.setCreateDate(DateUtil.getDate());
					activityPvStatistical.setUpdateDate(null);
					activityPvStatisticalMapper.insertSelective(activityPvStatistical);
				}
			}else {
				activityPvStatisticalMapper.insertSelective(activityPvStatistical);
			}
		}
		logger.info(DateUtil.getStandardDateTime()+"每日特卖根据栏目统计:start");
		getActivityPvStatisticalByColumn(paramMap);
		logger.info(DateUtil.getStandardDateTime()+"每日特卖统计:end");
		
		//每日会场统计
		logger.info(DateUtil.getStandardDateTime()+"每日会场统计:start");
		List<Map<String, Object>> activityAreaPvStatisticalList = memberPvCustomMapper.getActivityAreaPvStatisticalList(paramMap);
		for(Map<String, Object> m : activityAreaPvStatisticalList ) {
			ActivityAreaPvStatistical activityAreaPvStatistical = new ActivityAreaPvStatistical();
			activityAreaPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
			activityAreaPvStatistical.setActivityAreaId(Integer.parseInt(m.get("activity_area_id").toString()));
			activityAreaPvStatistical.setHomePagePosition(m.get("home_page_position")==null?"":m.get("home_page_position").toString());
			activityAreaPvStatistical.setClassifyPagePosition(m.get("classify_page_position")==null?"":m.get("classify_page_position").toString());
			activityAreaPvStatistical.setHomePageExposureCount(Integer.parseInt(m.get("home_page_exposure_count").toString()));
			activityAreaPvStatistical.setClassifyPageExposureCount(Integer.parseInt(m.get("classify_page_exposure_count").toString()));
			activityAreaPvStatistical.setTotalVisitorCount(Integer.parseInt(m.get("total_visitor_count").toString()));
			activityAreaPvStatistical.setTotalVisitorCountTourist(Integer.parseInt(m.get("total_visitor_count_tourist").toString()));
			activityAreaPvStatistical.setTotalPvCount(Integer.parseInt(m.get("total_pv_count").toString()));
			activityAreaPvStatistical.setTotalPvCountTourist(Integer.parseInt(m.get("total_pv_count_tourist").toString()));

			String[] payAmountS = m.get("pay_amount").toString().split(",");
			BigDecimal payAmount = new BigDecimal(payAmountS[0]);
			Integer paySubOrderCount = Integer.parseInt(payAmountS[1]);
			Integer payCombineOrderCount = Integer.parseInt(payAmountS[2]);
			activityAreaPvStatistical.setPayAmount(payAmount);
			activityAreaPvStatistical.setPaySubOrderCount(paySubOrderCount);
			activityAreaPvStatistical.setPayCombineOrderCount(payCombineOrderCount);

			activityAreaPvStatistical.setCreateDate(DateUtil.getDate());

			if(flag) {
				ActivityAreaPvStatisticalExample activityAreaPvStatisticalExample = new ActivityAreaPvStatisticalExample();
				activityAreaPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
						.andActivityAreaIdEqualTo(activityAreaPvStatistical.getActivityAreaId())
						.andStatisticalDateEqualTo(activityAreaPvStatistical.getStatisticalDate());
				activityAreaPvStatistical.setCreateDate(null);
				activityAreaPvStatistical.setUpdateDate(DateUtil.getDate());
				if(activityAreaPvStatisticalMapper.updateByExampleSelective(activityAreaPvStatistical, activityAreaPvStatisticalExample) == 0) {
					activityAreaPvStatistical.setCreateDate(DateUtil.getDate());
					activityAreaPvStatistical.setUpdateDate(null);
					activityAreaPvStatisticalMapper.insertSelective(activityAreaPvStatistical);
				}
			}else {
				activityAreaPvStatisticalMapper.insertSelective(activityAreaPvStatistical);
			}
		}
		logger.info(DateUtil.getStandardDateTime()+"每日会场统计:end");
		
		//每日广告位统计
		//普通广告
		logger.info(DateUtil.getStandardDateTime()+"普通广告:start");
		paramMap.put("dtlItemType", "4");
		List<Map<String, Object>> adPvStatisticalList = memberPvCustomMapper.getAdPvStatisticalList(paramMap);
		for(Map<String, Object> m : adPvStatisticalList ) {
			AdInfo adInfo = adInfoMapper.selectByPrimaryKey(Integer.parseInt(m.get("ad_id").toString()));
			if(adInfo != null ) {
				String adType = "";
				String adPic = "";
				if("1".equals(adInfo.getCatalog()) ) {
					adType = Const.AD_TYPE1; //首页
					adPic = adInfo.getPic();
				}else if("2".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE2; //运动户外
					adPic = adInfo.getPic();
				}else if("3".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE3; //服装配饰
					adPic = adInfo.getPic();
				}else if("4".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE4; //鞋靴箱包
					adPic = adInfo.getPic();
				}else if("5".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE6; //生活家居
					adPic = adInfo.getPic();
				}else if("11".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE5; //钟表珠宝
					adPic = adInfo.getPic();
				}else if("12".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE7; //数码家电
					adPic = adInfo.getPic();
				}else if("13".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE8; //美妆个护
					adPic = adInfo.getPic();
				}else if("14".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE9; //母婴童装
					adPic = adInfo.getPic();
				}else if("15".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE10; //食品超市
					adPic = adInfo.getPic();
				}else if("7".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE12; //限时秒杀
					adPic = adInfo.getPic();
				}else if("6".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE13; //新用户专享
					adPic = adInfo.getPic();
				}else if("9".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE14; //新人秒杀
					adPic = adInfo.getPic();
				}else if("10".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE15; //积分商城
					adPic = adInfo.getPic();
				}else if("16".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE16; //SVIP
					adPic = adInfo.getPic();
				}else if("17".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE17; //有好货
					adPic = adInfo.getPic();
				}else if("18".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE18; //每日好店
					adPic = adInfo.getPic();
				}else if("19".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE19; //启动广告
					adPic = adInfo.getAndroidPic();
				}else if("20".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE20; //拉新分润好货推荐
					adPic = adInfo.getPic();
				}else if("21".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE21; //潮鞋上新
					adPic = adInfo.getPic();
				}else if("22".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE22; //潮流男装
					adPic = adInfo.getPic();
				}else if("23".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE23; //断码特惠
					adPic = adInfo.getPic();
				}else if("24".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE24; //运动鞋服
					adPic = adInfo.getPic();
				}else if("25".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE25; //潮流美妆
					adPic = adInfo.getPic();
				}else if("26".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE26; //食品超市
					adPic = adInfo.getPic();
				}else if("27".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE27; //大学生创业
					adPic = adInfo.getPic();
				}else if("28".equals(adInfo.getCatalog())) {
					adType = Const.AD_TYPE29; //领劵中心
					adPic = adInfo.getPic();
				}
				AdPvStatistical adPvStatistical = new AdPvStatistical();
				adPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
				adPvStatistical.setAdSourceType("3");
				adPvStatistical.setAdType(adType);
				adPvStatistical.setAdPic(adPic);
				adPvStatistical.setAdInfoId(Integer.parseInt(m.get("ad_id").toString()));
				adPvStatistical.setExposureCount(Integer.parseInt(m.get("exposure_count").toString()));
				adPvStatistical.setClickMemberCount(Integer.parseInt(m.get("click_member_count").toString()));
				adPvStatistical.setClickCount(Integer.parseInt(m.get("click_count").toString()));
				adPvStatistical.setClickMemberCountTourist(Integer.parseInt(m.get("click_member_count_tourist").toString()));
				adPvStatistical.setClickCountTourist(Integer.parseInt(m.get("click_count_tourist").toString()));
				adPvStatistical.setCreateDate(DateUtil.getDate());

				if(flag) {
					AdPvStatisticalExample adPvStatisticalExample = new AdPvStatisticalExample();
					adPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
							.andAdInfoIdEqualTo(adPvStatistical.getAdInfoId())
							.andStatisticalDateEqualTo(adPvStatistical.getStatisticalDate())
							.andAdSourceTypeEqualTo(adPvStatistical.getAdSourceType());
					adPvStatistical.setCreateDate(null);
					adPvStatistical.setUpdateDate(DateUtil.getDate());
					if(adPvStatisticalMapper.updateByExampleSelective(adPvStatistical, adPvStatisticalExample) == 0) {
						adPvStatistical.setCreateDate(DateUtil.getDate());
						adPvStatistical.setUpdateDate(null);
						adPvStatisticalMapper.insertSelective(adPvStatistical);
					}
				}else {
					adPvStatisticalMapper.insertSelective(adPvStatistical);
				}

			}
		}
		logger.info(DateUtil.getStandardDateTime()+"普通广告:end");

		//品牌团广告
		logger.info(DateUtil.getStandardDateTime()+"品牌团广告:start");
		paramMap.put("dtlItemType", "5");
		List<Map<String, Object>> adBrandPvStatisticalList = memberPvCustomMapper.getAdPvStatisticalList(paramMap);
		for(Map<String, Object> m : adBrandPvStatisticalList ) {
			Map<String, Object> adBrandMap = memberPvCustomMapper.getBrandTeamTypeAd(Integer.parseInt(m.get("ad_id").toString()));
			if(adBrandMap != null ) {
				String adType = "";
				String adPic = "";
				if(Const.PRODUCT_TYPE2.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE2; //运动户外
				}else if(Const.PRODUCT_TYPE4.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE3; //服装配饰
				}else if(Const.PRODUCT_TYPE3.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE4; //鞋靴箱包
				}else if(Const.PRODUCT_TYPE430.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE5; //钟表珠宝
				}else if(Const.PRODUCT_TYPE5.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE6; //生活家居
				}else if(Const.PRODUCT_TYPE705.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE7; //数码家电
				}else if(Const.PRODUCT_TYPE858.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE8; //美妆个护
				}else if(Const.PRODUCT_TYPE954.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE9; //母婴童装
				}else if(Const.PRODUCT_TYPE1047.equals(adBrandMap.get("product_type_id").toString()) ) {
					adType = Const.AD_TYPE10; //食品超市
				}else {
					adType = Const.AD_TYPE11; //预告
				}
				adPic = adBrandMap.get("pic").toString();
				AdPvStatistical adPvStatistical = new AdPvStatistical();
				adPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
				adPvStatistical.setAdSourceType("1");
				adPvStatistical.setAdType(adType);
				adPvStatistical.setAdPic(adPic);
				adPvStatistical.setAdInfoId(Integer.parseInt(m.get("ad_id").toString()));
				adPvStatistical.setExposureCount(Integer.parseInt(m.get("exposure_count").toString()));
				adPvStatistical.setClickMemberCount(Integer.parseInt(m.get("click_member_count").toString()));
				adPvStatistical.setClickCount(Integer.parseInt(m.get("click_count").toString()));
				adPvStatistical.setClickMemberCountTourist(Integer.parseInt(m.get("click_member_count_tourist").toString()));
				adPvStatistical.setClickCountTourist(Integer.parseInt(m.get("click_count_tourist").toString()));
				adPvStatistical.setCreateDate(DateUtil.getDate());

				if(flag) {
					AdPvStatisticalExample adPvStatisticalExample = new AdPvStatisticalExample();
					adPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
							.andAdInfoIdEqualTo(adPvStatistical.getAdInfoId())
							.andStatisticalDateEqualTo(adPvStatistical.getStatisticalDate())
							.andAdSourceTypeEqualTo(adPvStatistical.getAdSourceType());
					adPvStatistical.setCreateDate(null);
					adPvStatistical.setUpdateDate(DateUtil.getDate());
					if(adPvStatisticalMapper.updateByExampleSelective(adPvStatistical, adPvStatisticalExample) == 0) {
						adPvStatistical.setCreateDate(DateUtil.getDate());
						adPvStatistical.setUpdateDate(null);
						adPvStatisticalMapper.insertSelective(adPvStatistical);
					}
				}else {
					adPvStatisticalMapper.insertSelective(adPvStatistical);
				}
			}
		}
		logger.info(DateUtil.getStandardDateTime()+"品牌团广告:end");

		//淘宝优选广告
		logger.info(DateUtil.getStandardDateTime()+"淘宝优选广告:start");
		paramMap.put("dtlItemType", "6");
		List<Map<String, Object>> adWetaoPvStatisticalList = memberPvCustomMapper.getAdPvStatisticalList(paramMap);
		for(Map<String, Object> m : adWetaoPvStatisticalList ) {
			Map<String, Object> adWetaoMap = memberPvCustomMapper.getWetaoTeamTypeAd(Integer.parseInt(m.get("ad_id").toString()));
			if(adWetaoMap != null ) {
				String adType = "";
				String adPic = "";
				if(adWetaoMap.get("product_type_id") == null ) {
					adType = Const.AD_TYPE28; //全品类（淘宝客）
				}else {
					if(Const.PRODUCT_TYPE2.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE2; //运动户外
					}else if(Const.PRODUCT_TYPE4.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE3; //服装配饰
					}else if(Const.PRODUCT_TYPE3.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE4; //鞋靴箱包
					}else if(Const.PRODUCT_TYPE430.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE5; //钟表珠宝
					}else if(Const.PRODUCT_TYPE5.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE6; //生活家居
					}else if(Const.PRODUCT_TYPE705.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE7; //数码家电
					}else if(Const.PRODUCT_TYPE858.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE8; //美妆个护
					}else if(Const.PRODUCT_TYPE954.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE9; //母婴童装
					}else if(Const.PRODUCT_TYPE1047.equals(adWetaoMap.get("product_type_id").toString()) ) {
						adType = Const.AD_TYPE10; //食品超市
					}
				}
				adPic = adWetaoMap.get("pic").toString();
				AdPvStatistical adPvStatistical = new AdPvStatistical();
				adPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
				adPvStatistical.setAdSourceType("2");
				adPvStatistical.setAdType(adType);
				adPvStatistical.setAdPic(adPic);
				adPvStatistical.setAdInfoId(Integer.parseInt(m.get("ad_id").toString()));
				adPvStatistical.setExposureCount(Integer.parseInt(m.get("exposure_count").toString()));
				adPvStatistical.setClickMemberCount(Integer.parseInt(m.get("click_member_count").toString()));
				adPvStatistical.setClickCount(Integer.parseInt(m.get("click_count").toString()));
				adPvStatistical.setClickMemberCountTourist(Integer.parseInt(m.get("click_member_count_tourist").toString()));
				adPvStatistical.setClickCountTourist(Integer.parseInt(m.get("click_count_tourist").toString()));
				adPvStatistical.setCreateDate(DateUtil.getDate());

				if(flag) {
					AdPvStatisticalExample adPvStatisticalExample = new AdPvStatisticalExample();
					adPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
							.andAdInfoIdEqualTo(adPvStatistical.getAdInfoId())
							.andStatisticalDateEqualTo(adPvStatistical.getStatisticalDate())
							.andAdSourceTypeEqualTo(adPvStatistical.getAdSourceType());
					adPvStatistical.setCreateDate(null);
					adPvStatistical.setUpdateDate(DateUtil.getDate());
					if(adPvStatisticalMapper.updateByExampleSelective(adPvStatistical, adPvStatisticalExample) == 0) {
						adPvStatistical.setCreateDate(DateUtil.getDate());
						adPvStatistical.setUpdateDate(null);
						adPvStatisticalMapper.insertSelective(adPvStatistical);
					}
				}else {
					adPvStatisticalMapper.insertSelective(adPvStatistical);
				}
			}
		}
		logger.info(DateUtil.getStandardDateTime()+"淘宝优选广告:end");

		//每日栏目统计
		logger.info(DateUtil.getStandardDateTime()+"每日栏目统计:start");
		//非自建页面 与 非会场
		List<Map<String, Object>> columnPvStatisticalList = memberPvCustomMapper.getColumnPvStatisticalList(paramMap);
		for(Map<String, Object> m : columnPvStatisticalList ) {
			ColumnPvStatistical columnPvStatistical = new ColumnPvStatistical();
			String[] payProductCount = m.get("pay_product_count").toString().split(",");
			columnPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
			columnPvStatistical.setColumnType(m.get("column_type").toString());
			columnPvStatistical.setColumnId(m.get("column_id")==null?null:Integer.parseInt(m.get("column_id").toString()));
			columnPvStatistical.setTotalVisitorCount(Integer.parseInt(m.get("total_visitor_count").toString()));
			columnPvStatistical.setTotalVisitorCountTourist(Integer.parseInt(m.get("total_visitor_count_tourist").toString()));
			columnPvStatistical.setTotalPvCount(Integer.parseInt(m.get("total_pv_count").toString()));
			columnPvStatistical.setTotalPvCountTourist(Integer.parseInt(m.get("total_pv_count_tourist").toString()));
			columnPvStatistical.setShoppingCartCount(Integer.parseInt(m.get("shopping_cart_count").toString()));
			columnPvStatistical.setSubProductCount(Integer.parseInt(m.get("sub_product_count").toString()));
			columnPvStatistical.setPayProductCount(Integer.parseInt(payProductCount[0]));
			columnPvStatistical.setPayAmountCount(new BigDecimal(payProductCount[1]));
			columnPvStatistical.setNewMemberCount(Integer.parseInt(m.get("new_member_count").toString()));
			columnPvStatistical.setCreateDate(DateUtil.getDate());

			if(flag) {
				ColumnPvStatisticalExample columnPvStatisticalExample = new ColumnPvStatisticalExample();
				columnPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
						.andColumnTypeEqualTo(columnPvStatistical.getColumnType())
						.andStatisticalDateEqualTo(columnPvStatistical.getStatisticalDate());
				columnPvStatistical.setCreateDate(null);
				columnPvStatistical.setUpdateDate(DateUtil.getDate());
				if(columnPvStatisticalMapper.updateByExampleSelective(columnPvStatistical, columnPvStatisticalExample) == 0) {
					columnPvStatistical.setCreateDate(DateUtil.getDate());
					columnPvStatistical.setUpdateDate(null);
					columnPvStatisticalMapper.insertSelective(columnPvStatistical);
				}
			}else {
				columnPvStatisticalMapper.insertSelective(columnPvStatistical);
			}

		}
		//自建页面 与 会场
		paramMap.put("columnId", "columnId");
		List<Map<String, Object>> columnPvStatisticals = memberPvCustomMapper.getColumnPvStatisticalList(paramMap);
		for(Map<String, Object> m : columnPvStatisticals ) {
			ColumnPvStatistical columnPvStatistical = new ColumnPvStatistical();
			String[] payProductCount = m.get("pay_product_count").toString().split(",");
			columnPvStatistical.setStatisticalDate(m.get("statistical_date").toString());
			columnPvStatistical.setColumnType(m.get("column_type").toString());
			columnPvStatistical.setColumnId(m.get("column_id")==null?null:Integer.parseInt(m.get("column_id").toString()));
			columnPvStatistical.setTotalVisitorCount(Integer.parseInt(m.get("total_visitor_count").toString()));
			columnPvStatistical.setTotalVisitorCountTourist(Integer.parseInt(m.get("total_visitor_count_tourist").toString()));
			columnPvStatistical.setTotalPvCount(Integer.parseInt(m.get("total_pv_count").toString()));
			columnPvStatistical.setTotalPvCountTourist(Integer.parseInt(m.get("total_pv_count_tourist").toString()));
			columnPvStatistical.setShoppingCartCount(Integer.parseInt(m.get("shopping_cart_count").toString()));
			columnPvStatistical.setSubProductCount(Integer.parseInt(m.get("sub_product_count").toString()));
			columnPvStatistical.setPayProductCount(Integer.parseInt(payProductCount[0]));
			columnPvStatistical.setPayAmountCount(new BigDecimal(payProductCount[1]));
			columnPvStatistical.setNewMemberCount(Integer.parseInt(m.get("new_member_count").toString()));
			columnPvStatistical.setCreateDate(DateUtil.getDate());

			if(flag) {
				ColumnPvStatisticalExample columnPvStatisticalExample = new ColumnPvStatisticalExample();
				columnPvStatisticalExample.createCriteria().andDelFlagEqualTo("0")
						.andColumnIdEqualTo(columnPvStatistical.getColumnId())
						.andColumnTypeEqualTo(columnPvStatistical.getColumnType())
						.andStatisticalDateEqualTo(columnPvStatistical.getStatisticalDate());
				columnPvStatistical.setCreateDate(null);
				columnPvStatistical.setUpdateDate(DateUtil.getDate());
				if(columnPvStatisticalMapper.updateByExampleSelective(columnPvStatistical, columnPvStatisticalExample) == 0) {
					columnPvStatistical.setCreateDate(DateUtil.getDate());
					columnPvStatistical.setUpdateDate(null);
					columnPvStatisticalMapper.insertSelective(columnPvStatistical);
				}
			}else {
				columnPvStatisticalMapper.insertSelective(columnPvStatistical);
			}
		}
		logger.info(DateUtil.getStandardDateTime()+"每日栏目统计:end");


		//统计商品，商家栏目流量数据
		saveColumnPvStatistical(paramMap);

        //资源位流量小时统计
        sourceNicheTrafficStatistics(paramMap);

	}

	


	/**
	 * 资源位流量统计,每天一次
	 */
	public void sourceNicheTrafficStatistics(Map<String,Object> paraMap) {
		Boolean flag = Boolean.valueOf(paraMap.get("flag").toString());
		String beginDate = paraMap.get("beginDate").toString();
		String endDate = paraMap.get("endDate").toString();
		Map<String,String> map = new HashMap<>();
		map.put("beginDate",beginDate);
		map.put("endDate",endDate);
		List<Map<String, Object>> columnPvHourStatisticalList = sourceNicheCustomMapper.getColumnPvHourStatistics(map);
		for(Map<String, Object> m : columnPvHourStatisticalList ) {
			if(StringUtils.isNotEmpty(m.get("column_type").toString())){
				ColumnPvHourStatistical columnPvHourStatistical = new ColumnPvHourStatistical();
				columnPvHourStatistical.setTimeHour(m.get("time_hour").toString());
				columnPvHourStatistical.setStatisticalDate(m.get("statistical_date").toString());
				columnPvHourStatistical.setColumnType(m.get("column_type").toString());
				columnPvHourStatistical.setColumnId(m.get("column_id")==null?null:Integer.parseInt(m.get("column_id").toString()));
				columnPvHourStatistical.setTotalVisitorCount(Integer.parseInt(m.get("total_visitor_count").toString()));
				columnPvHourStatistical.setTotalVisitorCountTourist(Integer.parseInt(m.get("total_visitor_count_tourist").toString()));
				columnPvHourStatistical.setTotalPvCount(Integer.parseInt(m.get("total_pv_count").toString()));
				columnPvHourStatistical.setTotalPvCountTourist(Integer.parseInt(m.get("total_pv_count_tourist").toString()));
				columnPvHourStatistical.setShoppingCartCount(Integer.parseInt(m.get("shopping_cart_count").toString()));
				columnPvHourStatistical.setSubProductCount(Integer.parseInt(m.get("sub_product_count").toString()));
				columnPvHourStatistical.setPayProductCount(Integer.parseInt(m.get("pay_product_count").toString()));
				columnPvHourStatistical.setPayAmountCount(new BigDecimal(m.get("pay_product_amount").toString()));
				columnPvHourStatistical.setCreateDate(DateUtil.getDate());

				if(flag) {
					ColumnPvHourStatisticalExample columnPvHourStatisticalExample = new ColumnPvHourStatisticalExample();
					columnPvHourStatisticalExample.createCriteria().andDelFlagEqualTo("0")
							.andTimeHourEqualTo(columnPvHourStatistical.getTimeHour())
							.andColumnTypeEqualTo(columnPvHourStatistical.getColumnType())
							.andStatisticalDateEqualTo(columnPvHourStatistical.getStatisticalDate());
					columnPvHourStatistical.setCreateDate(null);
					columnPvHourStatistical.setUpdateDate(DateUtil.getDate());
					if(columnPvHourStatisticalMapper.updateByExampleSelective(columnPvHourStatistical, columnPvHourStatisticalExample) == 0) {
						columnPvHourStatistical.setCreateDate(DateUtil.getDate());
						columnPvHourStatistical.setUpdateDate(null);
						columnPvHourStatisticalMapper.insertSelective(columnPvHourStatistical);
					}
				}else {
					columnPvHourStatisticalMapper.insertSelective(columnPvHourStatistical);
				}
			}
		}
	}

	public void deletePV() {
		memberPvCustomMapper.deleteActivityAreaPvStatistical();
		memberPvCustomMapper.deleteActivityPvStatistical();
		memberPvCustomMapper.deleteAdPvStatistical();
		memberPvCustomMapper.deleteMchtPvStatistical();
		memberPvCustomMapper.deleteMemberAction();
		memberPvCustomMapper.deleteMemberPv();
		memberPvCustomMapper.deleteMemberPvDtl();
		memberPvCustomMapper.deleteMemberPvMiddle();
		memberPvCustomMapper.deletePlatformPvStatistical();
	}

	private void getActivityPvStatisticalByColumn(Map<String, Object> paramMap){
		List<TypeColumnPvStatisticalCustom> data = memberPvCustomMapper.getActivityPvStatisticalByColumn(paramMap);
		if(data!=null && data.size()>0){
			typeColumnPvStatisticalCustomMapper.batchInsertTypeColumnPvStatistical(data,'3');
		}
	}

	/**
	 * 统计商品，商家栏目流量数据
	 * */
	public  void  saveColumnPvStatistical(Map<String,Object> paraMap){

		logger.info(DateUtil.getStandardDateTime()+"商品，商家，特卖栏目流量统计表插入商品:start");
		Boolean flag = Boolean.valueOf(paraMap.get("flag").toString());
		List<TypeColumnPvStatisticalCustom> productColumnPvStatisticalList = typeColumnPvStatisticalCustomMapper.getProductColumnPvStatistical(paraMap);
		if(productColumnPvStatisticalList!=null &&productColumnPvStatisticalList.size()>0){
			if(flag) {
				List<TypeColumnPvStatisticalCustom> typeColumnPvStatisticalCustoms = new ArrayList<>();
				for(TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom : productColumnPvStatisticalList) {
					TypeColumnPvStatisticalExample typeColumnPvStatisticalExample = new TypeColumnPvStatisticalExample();
					TypeColumnPvStatisticalExample.Criteria typeColumnPvStatisticalCriteria = typeColumnPvStatisticalExample.createCriteria();
					typeColumnPvStatisticalCriteria.andDelFlagEqualTo("0")
							.andTypeEqualTo("1")
							.andValueIdEqualTo(typeColumnPvStatisticalCustom.getValueId())
							.andStatisticalDateEqualTo(typeColumnPvStatisticalCustom.getStatisticalDate());
					if(!StringUtil.isEmpty(typeColumnPvStatisticalCustom.getColumnType())) {
						typeColumnPvStatisticalCriteria.andColumnTypeEqualTo(typeColumnPvStatisticalCustom.getColumnType());
					}
					typeColumnPvStatisticalCustom.setUpdateDate(DateUtil.getDate());
					if(typeColumnPvStatisticalMapper.updateByExampleSelective(typeColumnPvStatisticalCustom, typeColumnPvStatisticalExample) == 0) {
						typeColumnPvStatisticalCustoms.add(typeColumnPvStatisticalCustom);
					}
				}
				if(typeColumnPvStatisticalCustoms.size() > 0) {
					typeColumnPvStatisticalCustomMapper.batchInsertTypeColumnPvStatistical(typeColumnPvStatisticalCustoms, '1');
				}
			}else {
				int size = productColumnPvStatisticalList.size();
				int num = (size) % 10000 == 0 ? (size / 10000) : (size / 10000 + 1);// 按每10000条记录插入
				int start =0;
				int end  =0;
				List<TypeColumnPvStatisticalCustom> insertList = new ArrayList<TypeColumnPvStatisticalCustom>(productColumnPvStatisticalList);
				List<TypeColumnPvStatisticalCustom> itemsList = new ArrayList<TypeColumnPvStatisticalCustom>();
				for (int i = 1; i <= num; i++){
					end=(i*10000)> size ? size :(i*10000);
					start=(i-1)*10000;
					itemsList = insertList.subList(start,end);
					typeColumnPvStatisticalCustomMapper.batchInsertTypeColumnPvStatistical(itemsList, '1');
				}
			}
		}

		logger.info(DateUtil.getStandardDateTime()+"商品，商家，特卖栏目流量统计表插入商品:end");


		logger.info(DateUtil.getStandardDateTime()+"商品，商家，特卖栏目流量统计表插入商家:start");
		List<TypeColumnPvStatisticalCustom> mchtColumnPvStatisticalList = typeColumnPvStatisticalCustomMapper.getMchtColumnPvStatistical(paraMap);
		if(mchtColumnPvStatisticalList!=null && mchtColumnPvStatisticalList.size()>0){
			if(flag) {
				List<TypeColumnPvStatisticalCustom> mchtColumnPvStatisticals = new ArrayList<>();
				for(TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom : mchtColumnPvStatisticalList) {
					TypeColumnPvStatisticalExample typeColumnPvStatisticalExample = new TypeColumnPvStatisticalExample();
					TypeColumnPvStatisticalExample.Criteria typeColumnPvStatisticalCriteria = typeColumnPvStatisticalExample.createCriteria();
					typeColumnPvStatisticalCriteria.andDelFlagEqualTo("0")
							.andTypeEqualTo("2")
							.andValueIdEqualTo(typeColumnPvStatisticalCustom.getValueId())
							.andStatisticalDateEqualTo(typeColumnPvStatisticalCustom.getStatisticalDate());
					if(!StringUtil.isEmpty(typeColumnPvStatisticalCustom.getColumnType())) {
						typeColumnPvStatisticalCriteria.andColumnTypeEqualTo(typeColumnPvStatisticalCustom.getColumnType());
					}
					typeColumnPvStatisticalCustom.setUpdateDate(DateUtil.getDate());
					if(typeColumnPvStatisticalMapper.updateByExampleSelective(typeColumnPvStatisticalCustom, typeColumnPvStatisticalExample) == 0) {
						mchtColumnPvStatisticals.add(typeColumnPvStatisticalCustom);
					}
				}
				if(mchtColumnPvStatisticals.size() > 0) {
					typeColumnPvStatisticalCustomMapper.batchInsertTypeColumnPvStatistical(mchtColumnPvStatisticals, '2');
				}
			}else {
				typeColumnPvStatisticalCustomMapper.batchInsertTypeColumnPvStatistical(mchtColumnPvStatisticalList,'2');
			}
		}

		logger.info(DateUtil.getStandardDateTime()+"商品，商家，特卖栏目流量统计表插入商家:end");
	}


}
