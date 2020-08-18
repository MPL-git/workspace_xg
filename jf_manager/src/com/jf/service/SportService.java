package com.jf.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntegralDtlCustomMapper;
import com.jf.dao.MemberAccountCustomMapper;
import com.jf.dao.SportCustomMapper;
import com.jf.dao.SportGuessCustomMapper;
import com.jf.dao.SportGuessMapper;
import com.jf.dao.SportLogCustomMapper;
import com.jf.dao.SportLogMapper;
import com.jf.dao.SportMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.MemberAccount;
import com.jf.entity.Sport;
import com.jf.entity.SportCustom;
import com.jf.entity.SportCustomExample;
import com.jf.entity.SportExample;
import com.jf.entity.SportGuess;
import com.jf.entity.SportGuessCustom;
import com.jf.entity.SportGuessCustomExample;
import com.jf.entity.SportLog;

@Service
@Transactional
public class SportService extends BaseService<Sport, SportExample> {

	@Autowired
	private SportMapper sportMapper;
	
	@Autowired
	private SportCustomMapper sportCustomMapper;
	
	@Autowired
	private SportLogCustomMapper sportLogCustomMapper;
	
	@Autowired
	private SportLogMapper sportLogMapper;
	
	@Autowired
	private SportGuessMapper sportGuessMapper;
	
	@Autowired
	private SportGuessCustomMapper sportGuessCustomMapper;
	
	@Autowired
	private MemberAccountCustomMapper memberAccountCustomMapper;
	
	@Autowired
	private IntegralDtlCustomMapper integralDtlCustomMapper;
	
	@Autowired
	public void setSportMapper(SportMapper sportMapper) {
		super.setDao(sportMapper);
		this.sportMapper = sportMapper;
	}
	
	public int countByCustomExample(SportCustomExample example) {
		return sportCustomMapper.countByCustomExample(example);
	}

	public List<SportCustom> selectByCustomExample(SportCustomExample example) {
		return sportCustomMapper.selectByCustomExample(example);
	}

	public SportCustom selectByCustomPrimaryKey(Integer id) {
		return sportCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(Sport record, SportCustomExample example) {
		return sportCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public void addOrUpdateSport(Sport sport, String homeTeamName, String awayTeamName) {
		if(sport.getId() == null) {
			sport.setSportType(1);
			sport.setResult("0");
			sport.setAuditStatus("0");
			sport.setUpdateBy(null);
			sport.setUpdateDate(null);
			sportMapper.insertSelective(sport);
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<SportLog> sportLogList = new ArrayList<SportLog>();
			SportCustom sportCustom = sportCustomMapper.selectByCustomPrimaryKey(sport.getId());
			sport.setCreateBy(null);
			sport.setCreateDate(null);
			sportMapper.updateByPrimaryKeySelective(sport);
			if(sportCustom.getSportName() != null && sport.getSportName() != null 
					&& !sportCustom.getSportName().equals(sport.getSportName())) {
				SportLog sportLogA = new SportLog();
				sportLogA.setSportId(sport.getId());
				sportLogA.setContent("编辑（比赛名称"+sportCustom.getSportName()+"改为"+sport.getSportName()+"）");
				sportLogA.setCreateBy(sport.getUpdateBy());
				sportLogA.setCreateDate(sport.getUpdateDate());
				sportLogList.add(sportLogA);
			}
			if(sportCustom.getHomeTeam() != null && sport.getHomeTeam() != null 
					&& !sportCustom.getHomeTeam().equals(sport.getHomeTeam())) {
				SportLog sportLogB = new SportLog();
				sportLogB.setSportId(sport.getId());
				sportLogB.setContent("编辑（主场队伍"+sportCustom.getHomeTeamName()+"改为"+homeTeamName+"）");
				sportLogB.setCreateBy(sport.getUpdateBy());
				sportLogB.setCreateDate(sport.getUpdateDate());
				sportLogList.add(sportLogB);
			}
			if(sportCustom.getAwayTeam() != null && sport.getAwayTeam() != null 
					&& !sportCustom.getAwayTeam().equals(sport.getAwayTeam())) {
				SportLog sportLogC = new SportLog();
				sportLogC.setSportId(sport.getId());
				sportLogC.setContent("编辑（客场队伍"+sportCustom.getAwayTeamName()+"改为"+awayTeamName+"）");
				sportLogC.setCreateBy(sport.getUpdateBy());
				sportLogC.setCreateDate(sport.getUpdateDate());
				sportLogList.add(sportLogC);
			}
			if(sportCustom.getHomeRate() != null && sport.getHomeRate() != null 
					&& !sportCustom.getHomeRate().setScale(2, BigDecimal.ROUND_HALF_UP).equals(sport.getHomeRate().setScale(2, BigDecimal.ROUND_HALF_UP))) {
				SportLog sportLogD = new SportLog();
				sportLogD.setSportId(sport.getId());
				sportLogD.setContent("编辑（主场比例"+sportCustom.getHomeRate().setScale(2, BigDecimal.ROUND_HALF_UP)+"改为"+sport.getHomeRate().setScale(2, BigDecimal.ROUND_HALF_UP)+"）");
				sportLogD.setCreateBy(sport.getUpdateBy());
				sportLogD.setCreateDate(sport.getUpdateDate());
				sportLogList.add(sportLogD);
			}
			if(sportCustom.getAwaysRate() != null && sport.getAwaysRate() != null 
					&& !sportCustom.getAwaysRate().setScale(2, BigDecimal.ROUND_HALF_UP).equals(sport.getAwaysRate().setScale(2, BigDecimal.ROUND_HALF_UP))) {
				SportLog sportLogE = new SportLog();
				sportLogE.setSportId(sport.getId());
				sportLogE.setContent("编辑（客场比例"+sportCustom.getAwaysRate().setScale(2, BigDecimal.ROUND_HALF_UP)+"改为"+sport.getAwaysRate().setScale(2, BigDecimal.ROUND_HALF_UP)+"）");
				sportLogE.setCreateBy(sport.getUpdateBy());
				sportLogE.setCreateDate(sport.getUpdateDate());
				sportLogList.add(sportLogE);
			}
			if(sportCustom.getDrawRate() != null && sport.getDrawRate() != null 
					&& !sportCustom.getDrawRate().setScale(2, BigDecimal.ROUND_HALF_UP).equals(sport.getDrawRate().setScale(2, BigDecimal.ROUND_HALF_UP))) {
				SportLog sportLogF = new SportLog();
				sportLogF.setSportId(sport.getId());
				sportLogF.setContent("编辑（平局比例"+sportCustom.getDrawRate().setScale(2, BigDecimal.ROUND_HALF_UP)+"改为"+sport.getDrawRate().setScale(2, BigDecimal.ROUND_HALF_UP)+"）");
				sportLogF.setCreateBy(sport.getUpdateBy());
				sportLogF.setCreateDate(sport.getUpdateDate());
				sportLogList.add(sportLogF);
			}
			if(sportCustom.getBeginTime() != null && sport.getBeginTime() != null 
					&& sportCustom.getBeginTime().getTime() != sport.getBeginTime().getTime()) {
				SportLog sportLogG = new SportLog();
				sportLogG.setSportId(sport.getId());
				sportLogG.setContent("编辑（比赛开始时间"+sdf.format(sportCustom.getBeginTime())+"改为"+sdf.format(sport.getBeginTime())+"）");
				sportLogG.setCreateBy(sport.getUpdateBy());
				sportLogG.setCreateDate(sport.getUpdateDate());
				sportLogList.add(sportLogG);
			}
			if(sportLogList.size() > 0) {
				sportLogCustomMapper.insertSportLogList(sportLogList);
			}
		}
	}
	
	public void updateAuditStatus(Sport sport) {
		sportMapper.updateByPrimaryKeySelective(sport);
		SportLog sportLog = new SportLog();
		sportLog.setSportId(sport.getId());
		sportLog.setContent("等待审核");
		sportLog.setCreateBy(sport.getUpdateBy());
		sportLog.setCreateDate(sport.getUpdateDate());
		sportLogMapper.insertSelective(sportLog);
	}
	
	public void editAuditStatus(Sport sport) {
		Sport sportNew = sportMapper.selectByPrimaryKey(sport.getId());
		sportNew.setAuditStatus(sport.getAuditStatus());
		sportNew.setUpdateBy(sport.getUpdateBy());
		sportNew.setUpdateDate(sport.getUpdateDate());
		if("3".equals(sport.getAuditStatus())) {
			sportNew.setHomeScore(null);
			sportNew.setAwaysScore(null);
			sportNew.setResult("0");
		}
		sportMapper.updateByPrimaryKey(sportNew);
		sportMapper.updateByPrimaryKeySelective(sport);
		SportLog sportLog = new SportLog();
		sportLog.setSportId(sport.getId());
		sportLog.setCreateBy(sport.getUpdateBy());
		sportLog.setCreateDate(sport.getUpdateDate());
		if("3".equals(sport.getAuditStatus())) {
			sportLog.setContent("审核失败");
		}else if("2".equals(sport.getAuditStatus())) {
			sportLog.setContent("审核通过");
			resultIntegral(sport);
		}
		sportLogMapper.insertSelective(sportLog);
	}
	public void resultIntegral(Sport sport) {
		SportCustom sportCustom = sportCustomMapper.selectByCustomPrimaryKey(sport.getId());
		/**
		 * 修改赛事竞猜结果
		 */
		//1 主场胜
		SportGuessCustomExample sportGuessHomeCustomExample = new SportGuessCustomExample();
		SportGuessCustomExample.SportGuessCustomCriteria sportGuessHomeCustomCriteria = sportGuessHomeCustomExample.createCriteria();
		sportGuessHomeCustomCriteria.andDelFlagEqualTo("0").andGuessTypeEqualTo("1") //1赛事竞猜
		.andGuessWinTeamEqualTo(sportCustom.getHomeTeam()).andSportIdEqualTo(sport.getId());
		SportGuess sportGuessHome = new SportGuess();
		if("1".equals(sportCustom.getResult())) {
			sportGuessHome.setResult("2"); //2竞猜成功
		}else {
			sportGuessHome.setResult("3"); //3竞猜失败
		}
		sportGuessHome.setUpdateBy(sport.getUpdateBy());
		sportGuessHome.setUpdateDate(sport.getUpdateDate());
		sportGuessCustomMapper.updateByCustomExampleSelective(sportGuessHome, sportGuessHomeCustomExample);
		//2客场胜
		SportGuessCustomExample sportGuessAwayCustomExample = new SportGuessCustomExample();
		SportGuessCustomExample.SportGuessCustomCriteria sportGuessAwayCustomCriteria = sportGuessAwayCustomExample.createCriteria();
		sportGuessAwayCustomCriteria.andDelFlagEqualTo("0").andGuessTypeEqualTo("1") //1赛事竞猜
			.andGuessWinTeamEqualTo(sportCustom.getAwayTeam()).andSportIdEqualTo(sport.getId());
		SportGuess sportGuessAway = new SportGuess();
		if("2".equals(sportCustom.getResult())) {
			sportGuessAway.setResult("2"); //2竞猜成功
		}else {
			sportGuessAway.setResult("3"); //3竞猜失败
		}
		sportGuessAway.setUpdateBy(sport.getUpdateBy());
		sportGuessAway.setUpdateDate(sport.getUpdateDate());
		sportGuessCustomMapper.updateByCustomExampleSelective(sportGuessAway, sportGuessAwayCustomExample);
		//3平局
		SportGuessCustomExample sportGuessDrawCustomExample = new SportGuessCustomExample();
		SportGuessCustomExample.SportGuessCustomCriteria sportGuessDrawCustomCriteria = sportGuessDrawCustomExample.createCriteria();
		sportGuessDrawCustomCriteria.andDelFlagEqualTo("0").andGuessTypeEqualTo("1") //1赛事竞猜
			.andGuessWinTeamIsNull().andSportIdEqualTo(sport.getId());
		SportGuess sportGuessDraw = new SportGuess();
		if("3".equals(sportCustom.getResult())) {
			sportGuessDraw.setResult("2"); //2竞猜成功
		}else {
			sportGuessDraw.setResult("3"); //3竞猜失败
		}
		sportGuessDraw.setUpdateBy(sport.getUpdateBy());
		sportGuessDraw.setUpdateDate(sport.getUpdateDate());
		sportGuessCustomMapper.updateByCustomExampleSelective(sportGuessDraw, sportGuessDrawCustomExample);
		
		/**
		 * 结算积分
		 */
		List<SportGuessCustom> sportGuessCustomList = new ArrayList<SportGuessCustom>();
		/*BigDecimal sportRate = new BigDecimal(0);*/
		//1 主场胜
		if("1".equals(sportCustom.getResult())) {
			sportGuessCustomList = sportGuessCustomMapper.selectByCustomExample(sportGuessHomeCustomExample);
			/*sportRate = sportCustom.getHomeRate();*/
		}
		//2客场胜
		if("2".equals(sportCustom.getResult())) {
			sportGuessCustomList = sportGuessCustomMapper.selectByCustomExample(sportGuessAwayCustomExample);
			/*sportRate = sportCustom.getAwaysRate();*/
		}
		//3平局
		if("3".equals(sportCustom.getResult())) {
			sportGuessCustomList = sportGuessCustomMapper.selectByCustomExample(sportGuessDrawCustomExample);
			/*sportRate = sportCustom.getDrawRate();*/
		}
		List<MemberAccount> memberAccountList = new ArrayList<MemberAccount>();
    	List<IntegralDtl> integralDtlList = new ArrayList<IntegralDtl>();
    	Map<String, Integer> map = new HashMap<String, Integer>();
		for(SportGuessCustom sportGuessCustom : sportGuessCustomList) {
			MemberAccount memberAccount = new MemberAccount();
    		IntegralDtl integralDtl = new IntegralDtl();
    		
    		Integer memberIntegral = map.get(sportGuessCustom.getMemberId().toString());
    		if(memberIntegral == null) {
    			memberIntegral = sportGuessCustom.getMemberIntegral(); //积分
    		}
    		int integralNum = sportGuessCustom.getRate().multiply(new BigDecimal(sportGuessCustom.getIntegral())).intValue();
    		memberIntegral = memberIntegral + integralNum;
    		map.put(sportGuessCustom.getMemberId().toString(), memberIntegral);
    		memberAccount.setId(sportGuessCustom.getMemberAccountId());
    		memberAccount.setIntegral(memberIntegral);
    		memberAccount.setMemberId(sportGuessCustom.getMemberId());
    		memberAccount.setUpdateBy(sport.getUpdateBy());
    		memberAccount.setUpdateDate(sport.getUpdateDate());
    		memberAccountList.add(memberAccount); //会员账户
    		integralDtl.setAccId(sportGuessCustom.getMemberAccountId());
    		integralDtl.setTallyMode("1"); //收入
    		integralDtl.setType(10); //竞猜奖励
    		integralDtl.setBizType("5"); //5竞猜
    		integralDtl.setOrderId(sportGuessCustom.getId());
    		integralDtl.setIntegral(integralNum);
    		integralDtl.setBalance(memberIntegral);
    		integralDtl.setCreateBy(sport.getUpdateBy());
    		integralDtl.setCreateDate(sport.getUpdateDate());
    		integralDtl.setRemarks("世界杯赛事竞猜中奖");
    		integralDtlList.add(integralDtl); //积分明细
		}
		if(memberAccountList.size() > 0) {
			memberAccountCustomMapper.updateMemberAccountIdListSelective(memberAccountList);
		}
		if(integralDtlList.size() > 0) {
			integralDtlCustomMapper.insertIntegralDtlList(integralDtlList);
		}
	}
	
}
