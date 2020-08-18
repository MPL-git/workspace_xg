package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SportCustomMapper;
import com.jf.dao.SportMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlExample;
import com.jf.entity.IntegralType;
import com.jf.entity.IntegralTypeExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberInfo;
import com.jf.entity.Sport;
import com.jf.entity.SportCustom;
import com.jf.entity.SportExample;
import com.jf.entity.SportGuess;
import com.jf.entity.SportTeam;
import com.jf.entity.SportTeamExample;
import com.jf.entity.SportType;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月21日 下午3:16:27 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SportService extends BaseService<Sport, SportExample> {
	@Autowired
	private SportMapper sportMapper;
	@Autowired
	private SportCustomMapper sportCustomMapper;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private SportTeamService sportTeamService;
	@Resource
	private SportGuessService sportGuessService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private SportTypeService sportTypeService;
	@Resource
	private IntegralTypeService integralTypeService;
	@Resource
	private MemberInfoService memberInfoService;

	@Autowired
	public void setSportMapper(SportMapper sportMapper) {
		this.setDao(sportMapper);
		this.sportMapper = sportMapper;
	}

	public Map<String, Object> getSportList(Integer memberId, JSONObject reqDataJson, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		String type = reqDataJson.getString("type");
		String integral = "";
		String nick = "";
		String pic = "";
		MemberAccount memberAccount = null;
		MemberInfo memberInfo = null;
		if(memberId != null){
			memberAccount = memberAccountService.findAccountByMemberId(memberId);
			memberInfo =memberInfoService.findMemberById(memberId);
			if(memberAccount != null && memberAccount.getIntegral() != null){
				integral = memberAccount.getIntegral().toString();
			}
			if(memberInfo != null){
				nick = memberInfo.getNick();
				pic = StringUtil.getPic(memberInfo.getPic(), "");
			}
		}
		if(type.equals("1")){
			Integer currentPage = reqDataJson.getInt("currentPage");
			paramsMap.put("currentPage", currentPage*pageSize);
			paramsMap.put("pageSize", pageSize);
			List<SportCustom> sportCustomes = sportCustomMapper.getSportWinGuessList(paramsMap);
			if(CollectionUtils.isNotEmpty(sportCustomes)){
				for (SportCustom sportCustom : sportCustomes) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					Date beginTime = sportCustom.getBeginTime();
					dataMap.put("beginTime", beginTime);
					dataMap.put("sportName", sportCustom.getSportName());
					dataMap.put("homeTeam", sportCustom.getHomeTeam());
					dataMap.put("homeTeamName", sportCustom.getHomeTeamName());
					dataMap.put("homeTeamPic", StringUtil.getPic(sportCustom.getHomeTeamPic(), ""));
					dataMap.put("homeTeamRate", sportCustom.getHomeRate());
					dataMap.put("awayTeam", sportCustom.getAwayTeam());
					dataMap.put("awayTeamName", sportCustom.getAwayTeamName());
					dataMap.put("awayTeamPic", StringUtil.getPic(sportCustom.getAwayTeamPic(), ""));
					dataMap.put("awayTeamRate", sportCustom.getAwaysRate());
					dataMap.put("drawRate", sportCustom.getDrawRate());
					dataMap.put("sportId", sportCustom.getId());
					dataList.add(dataMap);
				}
			}
		}else if(type.equals("2")){
			SportTeamExample sportTeamExample = new SportTeamExample();
			sportTeamExample.createCriteria().andDelFlagEqualTo("0");
			sportTeamExample.setOrderByClause("find_in_set(result,'2,0,1'),win_rate desc");
			List<SportTeam> sportTeams = sportTeamService.selectByExample(sportTeamExample);
			if(CollectionUtils.isNotEmpty(sportTeams)){
				SportType sportType = sportTypeService.selectByPrimaryKey(sportTeams.get(0).getSportType());
				map.put("sportGuessEndTime", sportType.getFinalGuessTime());
				for (SportTeam sportTeam : sportTeams) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					dataMap.put("sportTeamId", sportTeam.getId());
					dataMap.put("sportTeamName", sportTeam.getName());
					dataMap.put("winRate", sportTeam.getWinRate());
					dataMap.put("resutlt", sportTeam.getResult());
					dataList.add(dataMap);
				}
			}
		}else if(type.equals("3")){
			Integer currentPage = reqDataJson.getInt("currentPage");
			paramsMap.put("currentPage", currentPage*pageSize);
			paramsMap.put("pageSize", pageSize);
			List<SportCustom> sportCustomes = sportCustomMapper.getSportRecordList(paramsMap);
			if(CollectionUtils.isNotEmpty(sportCustomes)){
				for (SportCustom sportCustom : sportCustomes) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					Date beginTime = sportCustom.getBeginTime();
					dataMap.put("sportName", sportCustom.getSportName());
					dataMap.put("beginTime", beginTime);
					dataMap.put("homeTeam", sportCustom.getHomeTeam());
					dataMap.put("homeTeamName", sportCustom.getHomeTeamName());
					dataMap.put("homeTeamPic", StringUtil.getPic(sportCustom.getHomeTeamPic(), ""));
					dataMap.put("homeTeamRate", sportCustom.getHomeRate());
					dataMap.put("homeScore", sportCustom.getHomeScore());
					dataMap.put("awayTeam", sportCustom.getAwayTeam());
					dataMap.put("awayTeamName", sportCustom.getAwayTeamName());
					dataMap.put("awayTeamPic", StringUtil.getPic(sportCustom.getAwayTeamPic(), ""));
					dataMap.put("awayTeamRate", sportCustom.getAwaysRate());
					dataMap.put("awayScore", sportCustom.getAwaysScore());
					dataMap.put("drawRate", sportCustom.getDrawRate());
					dataMap.put("sportId", sportCustom.getId());
					dataList.add(dataMap);
				}
			}
		}
		map.put("dataList", dataList);
		map.put("currentDate", new Date());
		map.put("integral", integral);
		map.put("nick", nick);
		map.put("pic", pic);
		map.put("type", type);
		return map;
	}

	public void addSportGuess(Integer memberId, JSONObject reqDataJson) {
		Date currentDate = new Date();
		String guessIntegral = reqDataJson.getString("integral");
		String sportItemId = "";
		String sportId = "";
		if(reqDataJson.containsKey("sportId")){
			sportId = reqDataJson.getString("sportId");
		}
		if(reqDataJson.containsKey("sportTeamId")){
			sportItemId = reqDataJson.getString("sportTeamId");
		}
		String type = reqDataJson.getString("type");
		Integer memberIntegral = 0;
		MemberAccount memberAccount = null;
		if(StringUtil.isBlank(guessIntegral)){
			throw new ArgException("竞猜积分不能为空");
		}else{
			if(Integer.valueOf(guessIntegral) < 20){
				throw new ArgException("竞猜积分必须大于20");
			}
		}
		if(memberId != null){
			memberAccount = memberAccountService.findAccountByMemberId(memberId);
			if(memberAccount != null && memberAccount.getIntegral() != null){
				memberIntegral = memberAccount.getIntegral();
				if(Integer.valueOf(guessIntegral) > memberIntegral){
					throw new ArgException("积分不足");
				}
			}else{
				throw new ArgException("用户不存在");
			}
		}else{
			throw new ArgException("用户不存在");
		}
		SportGuess sg = null;
		String guessCode = "JC"+CommonUtil.getOrderCode();
		if(type.equals("1")){
			if(!StringUtil.isBlank(sportItemId)){
				SportTeamExample sportTeamExample = new SportTeamExample();
				sportTeamExample.createCriteria().andIdEqualTo(Integer.valueOf(sportItemId)).andDelFlagEqualTo("0")
				.andResultEqualTo("0").andAuditStatusEqualTo("0");
				List<SportTeam> sportTeams = sportTeamService.selectByExample(sportTeamExample);
				if(CollectionUtils.isNotEmpty(sportTeams)){
					SportExample sportExample = new SportExample();
					sportExample.createCriteria().andIdEqualTo(Integer.valueOf(sportId)).andDelFlagEqualTo("0")
					.andResultEqualTo("0").andAuditStatusEqualTo("0").andBeginTimeGreaterThan(currentDate);
					List<Sport> sports = selectByExample(sportExample);
					if(CollectionUtils.isNotEmpty(sports)){
						Sport sport = sports.get(0);
						BigDecimal rate = new BigDecimal("0");
						if(sportItemId.equals(sport.getHomeTeam().toString())){
							rate = sport.getHomeRate();
						}else{
							rate = sport.getAwaysRate();
						}
						sg = new SportGuess();
						sg.setGuessCode(guessCode);
						sg.setMemberId(memberId);
						sg.setGuessType("1");
						sg.setSportId(Integer.valueOf(sportId));
						sg.setGuessWinTeam(Integer.valueOf(sportItemId));
						sg.setIntegral(Integer.valueOf(guessIntegral));
						sg.setRate(rate);
						sg.setResult("1");
						sg.setCreateBy(memberId);
						sg.setCreateDate(currentDate);
						sg.setDelFlag("0");
						sportGuessService.insertSelective(sg);
					}else{
						throw new ArgException("投注已截止");
					}
				}else{
					throw new ArgException("投注已截止");
				}
			}else{
				//平局
				SportExample sportExample = new SportExample();
				sportExample.createCriteria().andIdEqualTo(Integer.valueOf(sportId)).andDelFlagEqualTo("0")
				.andResultEqualTo("0").andAuditStatusEqualTo("0").andBeginTimeGreaterThan(currentDate);
				List<Sport> sports = selectByExample(sportExample);
				if(CollectionUtils.isNotEmpty(sports)){
					sg = new SportGuess();
					sg.setGuessCode(guessCode);
					sg.setMemberId(memberId);
					sg.setGuessType("1");
					sg.setSportId(Integer.valueOf(sportId));
					sg.setIntegral(Integer.valueOf(guessIntegral));
					sg.setRate(sports.get(0).getDrawRate());
					sg.setResult("1");
					sg.setCreateBy(memberId);
					sg.setCreateDate(currentDate);
					sg.setDelFlag("0");
					sportGuessService.insertSelective(sg);
				}else{
					throw new ArgException("投注已截止");
				}
			}
			
		}else if(type.equals("2")){
			if(StringUtil.isBlank(sportItemId)){
				throw new ArgException("请选择竞猜夺冠队伍");
			}
			SportTeamExample sportTeamExample = new SportTeamExample();
			sportTeamExample.createCriteria().andIdEqualTo(Integer.valueOf(sportItemId)).andDelFlagEqualTo("0")
			.andResultEqualTo("0").andAuditStatusEqualTo("0");
			List<SportTeam> sportTeams = sportTeamService.selectByExample(sportTeamExample);
			if(CollectionUtils.isNotEmpty(sportTeams)){
				SportType sportType = sportTypeService.selectByPrimaryKey(sportTeams.get(0).getSportType());
				if(sportType != null && sportType.getFinalGuessTime().compareTo(currentDate) < 0){
					throw new ArgException("竞猜投注已截止");
				}
				sg = new SportGuess();
				sg.setMemberId(memberId);
				sg.setGuessCode(guessCode);
				sg.setGuessType("2");
				sg.setGuessWinTeam(Integer.valueOf(sportItemId));
				sg.setIntegral(Integer.valueOf(guessIntegral));
				sg.setRate(sportTeams.get(0).getWinRate());
				sg.setResult("1");
				sg.setCreateBy(memberId);
				sg.setCreateDate(currentDate);
				sg.setDelFlag("0");
				sportGuessService.insertSelective(sg);
			}else{
				throw new ArgException("比赛已结束");
			}
		}
		if(sg != null){
			memberAccount.setIntegral(memberIntegral-Integer.valueOf(guessIntegral));
			memberAccount.setUpdateBy(memberId);
			memberAccount.setUpdateDate(currentDate);
			memberAccountService.updateByPrimaryKey(memberAccount);
			
			IntegralDtl dtl = new IntegralDtl();
			dtl.setAccId(memberAccount.getId());
			dtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_ACCOUNT);
			dtl.setType(Integer.valueOf(Const.INTEGRAL_TYPE_SYSTEM_SPORT_DE));
			dtl.setIntegral(Integer.valueOf(guessIntegral));
			dtl.setBalance(memberAccount.getIntegral());
			dtl.setBizType("5");
			dtl.setOrderId(sg.getId());
			dtl.setCreateBy(memberId);
			dtl.setCreateDate(currentDate);
			dtl.setDelFlag("0");
			integralDtlService.insertSelective(dtl);
		}else{
			throw new ArgException("竞猜失败");
		}
	}

	public List<Map<String, Object>> getSportGuessRecord(Integer memberId, JSONObject reqDataJson, Integer pageSize) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		String result = reqDataJson.getString("result");
		Integer currentPage = reqDataJson.getInt("currentPage");
		if(result.equals("1")){
			paramsMap.put("result", "1");
		}else if(result.equals("2")){
			paramsMap.put("result", "2");
		}
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("memberId", memberId);
		List<SportCustom> sportCustoms = sportCustomMapper.getSportGuessRecord(paramsMap);
		if(CollectionUtils.isNotEmpty(sportCustoms)){
			for (SportCustom sportCustom : sportCustoms) {
				Map<String, Object> map = new HashMap<String, Object>();
				String sprotResult = sportCustom.getResult();
				String guessType = sportCustom.getGuessType();
				Integer homeTeamId = sportCustom.getHomeTeamId();
				String homeTeamName = sportCustom.getHomeTeamName();
				Integer awayTeamId = sportCustom.getAwayTeamId();
				String awayTeamName = sportCustom.getAwayTeamName();
				Integer guessWinTeam = sportCustom.getGuessWinTeam();
				Integer Integral = sportCustom.getIntegral();
				String guessResult = sportCustom.getGuessResult();
				BigDecimal homeRate = sportCustom.getHomeRate();
				String guessCode = sportCustom.getGuessCode();
				String teamResult = sportCustom.getTeamResult();
				BigDecimal rate = sportCustom.getRate();
				String createDate = DateUtil.getFormatDate(sportCustom.getCreateDate(), "MM-dd HH:mm");
				String vs = "";
				String victory = "";
				String guessContent = "";
				String guessAmount = "";
				String openResult = "";
				String prizeResult = "";
				Integer getIntegral = 0;
				if(guessType.equals("1")){
					vs = homeTeamName+"VS"+awayTeamName;
					if(guessResult.equals("1")){
						//等待开奖
						prizeResult = "等待开奖";
						if(homeTeamId != null && guessWinTeam != null && guessWinTeam.equals(homeTeamId)){
							guessContent = homeTeamName +"胜";
							guessAmount = Integral+"积分*"+sportCustom.getRate();
							openResult = "等待开奖";
						}else if(awayTeamId != null && guessWinTeam != null && guessWinTeam.equals(awayTeamId)){
							guessContent = awayTeamName +"胜";
							guessAmount = Integral+"积分*"+rate;
							openResult = "等待开奖";
						}else{
							guessContent = "平局";
							guessAmount = Integral+"积分*"+rate;
							openResult = "等待开奖";
						}
					}else if(guessResult.equals("2")){
						//竞猜成功
						if(sprotResult.equals("1")){
							guessContent = homeTeamName +"胜";
							guessAmount = Integral+"积分*"+rate;
							openResult = homeTeamName+"胜";
							getIntegral = new BigDecimal(Integral).multiply(rate).intValue();
						}else if(sprotResult.equals("2")){
							guessContent = awayTeamName +"胜";
							guessAmount = Integral+"积分*"+rate;
							openResult = awayTeamName+"胜";
							getIntegral = new BigDecimal(Integral).multiply(rate).intValue();
						}else if(sprotResult.equals("3")){
							guessContent = "平局";
							guessAmount = Integral+"积分*"+rate;
							openResult = "平局";
							getIntegral = new BigDecimal(Integral).multiply(rate).intValue();
						}
						prizeResult = "中奖获得"+getIntegral+"积分";
					}else if(guessResult.equals("3")){
						//竞猜失败
						if(homeTeamId != null && guessWinTeam != null && guessWinTeam.equals(homeTeamId)){
							guessContent = homeTeamName +"胜";
							guessAmount = Integral+"积分*"+rate;
							getIntegral = new BigDecimal(Integral).multiply(rate).intValue();
						}else if(awayTeamId != null && guessWinTeam != null && guessWinTeam.equals(awayTeamId)){
							guessContent = awayTeamName +"胜";
							guessAmount = Integral+"积分*"+rate;
							getIntegral = new BigDecimal(Integral).multiply(rate).intValue();
						}else{
							guessContent = "平局胜";
							guessAmount = Integral+"积分*"+rate;
							getIntegral = new BigDecimal(Integral).multiply(rate).intValue();
						}
						if(sprotResult.equals("1")){
							//如果主场胜利
							openResult = homeTeamName+"胜";
						}else if(sprotResult.equals("2")){
							//客场胜利
							openResult = awayTeamName+"胜";
						}else if(sprotResult.equals("3")){
							//平局
							openResult = "平局";
						}
						prizeResult = "未中奖";
					}
					
					
				}else{
					vs = homeTeamName+"夺冠";
					guessContent = homeTeamName+"夺冠";
					guessAmount = Integral+"积分*"+rate;
					getIntegral = new BigDecimal(Integral).multiply(rate).intValue();
					if(guessResult.equals("1")){
						prizeResult = "等待开奖";
					}else if(guessResult.equals("2")){
						prizeResult = "中奖获得"+getIntegral+"积分";
					}else if(guessResult.equals("3")){
						prizeResult = "未中奖";
					}
					if(teamResult.equals("0")){
						openResult = "参赛中";
					}else if(teamResult.equals("1")){
						openResult = "已淘汰";
					}else if(teamResult.equals("2")){
						openResult = "夺冠";
					}
				}
				victory = createDate+" "+guessContent+" 投注"+Integral+"积分";
				map.put("title", vs);
				map.put("victory", victory);
				map.put("guessCode", "竞猜编号："+guessCode);
				map.put("guessContent", "竞猜内容："+guessContent);
				map.put("guessAmount", "竞猜金额："+guessAmount);
				map.put("openResult", "开奖结果："+openResult);
				map.put("prizeResult", prizeResult);
				dataList.add(map);
			}
		}
		return dataList;
	}

	public List<Map<String, Object>> getIntegralTask(Integer memberId) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(2);
		idList.add(3);
		IntegralTypeExample integralTypeExample = new IntegralTypeExample();
		integralTypeExample.createCriteria().andIdIn(idList).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<IntegralType> list = integralTypeService.selectByExample(integralTypeExample);
		if(CollectionUtils.isNotEmpty(list)){
			for (IntegralType integralType : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				boolean isFinishTask = false;
				boolean isMobile = false;
				String name = integralType.getName();
				Integer id = integralType.getId();
				String integral = integralType.getIntegral().toString();
				if(memberId != null){
					MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
					if(id == 2){
						//判断是否领取过500积分
						MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
						if(memberAccount != null){
							if(memberInfo != null && !StringUtil.isBlank(memberInfo.getMobile())){
								//已经绑定手机
								isMobile = true;
								IntegralDtlExample integralDtlExample = new IntegralDtlExample();
								integralDtlExample.createCriteria().andAccIdEqualTo(memberAccount.getId()).andDelFlagEqualTo("0")
								.andTypeEqualTo(Integer.valueOf(Const.INTEGRAL_TYPE_SYSTEM_GIFT)).andTallyModeEqualTo(Const.INTEGRAL_TALLY_MODE_INCOME)
								.andRemarksEqualTo("活动期间赠送500积分");
								int count = integralDtlService.countByExample(integralDtlExample);
								if(count > 0){
									isFinishTask = true;
								}
							}
						}
						if(!isFinishTask){
							name = "活动期间赠送500积分";
							integral = "领取";
						}else{
							name = "活动期间赠送500积分";
							integral = "已领取";
						}
					}else{
						//完善资料
						if(memberInfo != null && memberInfo.getIsInfPerfect().equals("1")){
							isFinishTask = true;
						}
					}
				}else{
					if(id == 2){
						name = "活动期间赠送500积分";
						integral = "领取";
					}
				}
				map.put("name", name);
				map.put("id", id);
				map.put("integral", integral);
				map.put("isFinishTask", isFinishTask);
				map.put("isMobile", isMobile);
				dataList.add(map);
			}
		}
		return dataList;
	}

	public void addMemberIntegralTask(Integer memberId) {
		//判断是否领取过500积分
		MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
		MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
		Integer integralGift = 500;
		if(memberAccount != null){
			if(memberInfo != null && !StringUtil.isBlank(memberInfo.getMobile())){
				//已经绑定手机
				Date date = new Date();
				IntegralDtlExample integralDtlExample = new IntegralDtlExample();
				integralDtlExample.createCriteria().andAccIdEqualTo(memberAccount.getId()).andDelFlagEqualTo("0")
				.andTypeEqualTo(Integer.valueOf(Const.INTEGRAL_TYPE_SYSTEM_GIFT)).andTallyModeEqualTo(Const.INTEGRAL_TALLY_MODE_INCOME)
				.andRemarksEqualTo("活动期间赠送500积分");
				int count = integralDtlService.countByExample(integralDtlExample);
				if(count <= 0){
					IntegralDtl dtl = new IntegralDtl();
					dtl.setAccId(memberAccount.getId());
					dtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
					dtl.setType(Integer.valueOf(Const.INTEGRAL_TYPE_SYSTEM_GIFT));
					dtl.setIntegral(integralGift);
					dtl.setBalance(memberAccount.getIntegral()+integralGift);
					dtl.setRemarks("活动期间赠送500积分");
					dtl.setCreateBy(memberId);
					dtl.setCreateDate(date);
					dtl.setDelFlag("0");
					integralDtlService.insertSelective(dtl);
					memberAccount.setIntegral(memberAccount.getIntegral()+integralGift);
					memberAccount.setUpdateBy(memberId);
					memberAccount.setUpdateDate(date);
					memberAccountService.updateByPrimaryKeySelective(memberAccount);
				}else{
					throw new ArgException("已领取");
				}
			}else{
				throw new ArgException("清先绑定手机");
			}
		}else{
			throw new ArgException("领取失败");
		}
		
	}
	
	
}
