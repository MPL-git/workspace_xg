package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntegralDtlCustomMapper;
import com.jf.dao.MemberAccountCustomMapper;
import com.jf.dao.SportGuessCustomMapper;
import com.jf.dao.SportGuessMapper;
import com.jf.dao.SportMapper;
import com.jf.dao.SportTeamCustomMapper;
import com.jf.dao.SportTeamLogMapper;
import com.jf.dao.SportTeamMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.MemberAccount;
import com.jf.entity.SportGuess;
import com.jf.entity.SportGuessCustom;
import com.jf.entity.SportGuessCustomExample;
import com.jf.entity.SportGuessExample;
import com.jf.entity.SportTeam;
import com.jf.entity.SportTeamCustom;
import com.jf.entity.SportTeamCustomExample;
import com.jf.entity.SportTeamExample;
import com.jf.entity.SportTeamLog;

@Service
@Transactional
public class SportTeamService extends BaseService<SportTeam, SportTeamExample> {

	@Autowired
	private SportTeamMapper sportTeamMapper;
	
	@Autowired
	private SportTeamCustomMapper sportTeamCustomMapper;
	
	@Autowired
	private SportTeamLogMapper sportTeamLogMapper;
	
	@Autowired
	private SportGuessMapper sportGuessMapper;
	
	@Autowired
	private SportGuessCustomMapper sportGuessCustomMapper;
	
	@Autowired
	private SportMapper sportMapper;
	
	@Autowired
	private MemberAccountCustomMapper memberAccountCustomMapper;
	
	@Autowired
	private IntegralDtlCustomMapper integralDtlCustomMapper;
	
	@Autowired
	public void setSportTeamMapper(SportTeamMapper sportTeamMapper) {
		super.setDao(sportTeamMapper);
		this.sportTeamMapper = sportTeamMapper;
	}
	
	public int countByCustomExample(SportTeamCustomExample example) {
		return sportTeamCustomMapper.countByCustomExample(example);
	}

    public List<SportTeamCustom> selectByCustomExample(SportTeamCustomExample example) {
    	return sportTeamCustomMapper.selectByCustomExample(example);
    }

    public SportTeamCustom selectByCustomPrimaryKey(Integer id) {
    	return sportTeamCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(SportTeam record, SportTeamCustomExample example) {
    	return sportTeamCustomMapper.updateByCustomExampleSelective(record, example);
    }

    /**
     * 
     * @Title updateAuditStatus   
     * @Description TODO(这里用一句话描述这个方法的作用)   
     * @author pengl
     * @date 2018年5月22日 下午5:02:32
     */
    public void updateAuditStatus(SportTeam sportTeam, String content) {
    	sportTeamMapper.updateByPrimaryKeySelective(sportTeam);
    	SportTeamLog sportTeamLog = new SportTeamLog();
    	sportTeamLog.setSportTeamId(sportTeam.getId());
    	sportTeamLog.setCreateBy(sportTeam.getUpdateBy());
    	sportTeamLog.setCreateDate(sportTeam.getUpdateDate());
    	sportTeamLog.setContent(content);
		sportTeamLogMapper.insertSelective(sportTeamLog);
		
		if("2".equals(sportTeam.getResult())) { //2 夺冠
    		//夺冠竞猜成功
			SportGuessCustomExample sportGuessCustomExample = new SportGuessCustomExample();
			SportGuessCustomExample.SportGuessCustomCriteria sportGuessCustomCriteria = sportGuessCustomExample.createCriteria();
			sportGuessCustomCriteria.andDelFlagEqualTo("0").andGuessWinTeamEqualTo(sportTeam.getId())
    			.andGuessTypeEqualTo("2"); //2夺冠竞猜
        	SportGuess sportGuessOne = new SportGuess();
        	sportGuessOne.setResult("2"); //竞猜成功
        	sportGuessOne.setUpdateBy(sportTeam.getUpdateBy());
        	sportGuessOne.setUpdateDate(sportTeam.getUpdateDate());
        	sportGuessCustomMapper.updateByCustomExampleSelective(sportGuessOne, sportGuessCustomExample);
        	
    		//夺冠竞猜失败
    		SportGuessExample sportGuessExample = new SportGuessExample();
        	SportGuessExample.Criteria sportGuessCriteria = sportGuessExample.createCriteria();
        	sportGuessCriteria.andDelFlagEqualTo("0").andGuessWinTeamNotEqualTo(sportTeam.getId())
        		.andGuessTypeEqualTo("2"); //2夺冠竞猜
        	SportGuess sportGuessTwo = new SportGuess();
        	sportGuessTwo.setResult("3"); //竞猜失败
        	sportGuessTwo.setUpdateBy(sportTeam.getUpdateBy());
        	sportGuessTwo.setUpdateDate(sportTeam.getUpdateDate());
        	sportGuessMapper.updateByExampleSelective(sportGuessTwo, sportGuessExample);
        	
        	//夺冠竞猜成功===>结算积分
        	List<SportGuessCustom> sportGuessCustomList = sportGuessCustomMapper.selectByCustomExample(sportGuessCustomExample);
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
        		int integralNum = 0;
        		
        		/*if(sportGuessCustom.getSportHomeTeam() != null && sportGuessCustom.getSportAwayTeam() != null) {
        			BigDecimal sportRate = new BigDecimal(0);
        			if(sportGuessCustom.getGuessWinTeam().intValue() == sportGuessCustom.getSportHomeTeam().intValue()) { //主场队伍
        				sportRate = sportGuessCustom.getSportHomeRate(); //主场比例
        			}else if(sportGuessCustom.getGuessWinTeam().intValue() == sportGuessCustom.getSportAwayTeam().intValue()) { //客场队伍
        				sportRate = sportGuessCustom.getSportAwaysRate(); //客场比例
        			}
        			integralNum = sportRate.multiply(new BigDecimal(sportGuessCustom.getIntegral())).intValue();
        		}*/
        		
        		integralNum = sportGuessCustom.getRate().multiply(new BigDecimal(sportGuessCustom.getIntegral())).intValue();
        		memberIntegral = memberIntegral + integralNum;
        		map.put(sportGuessCustom.getMemberId().toString(), memberIntegral);
        		memberAccount.setId(sportGuessCustom.getMemberAccountId());
        		memberAccount.setIntegral(memberIntegral);
        		memberAccount.setMemberId(sportGuessCustom.getMemberId());
        		memberAccount.setUpdateBy(sportTeam.getUpdateBy());
        		memberAccount.setUpdateDate(sportTeam.getUpdateDate());
        		memberAccountList.add(memberAccount); //会员账户
        		integralDtl.setAccId(sportGuessCustom.getMemberAccountId());
        		integralDtl.setTallyMode("1"); //收入
        		integralDtl.setType(10); //竞猜奖励
        		integralDtl.setBizType("5"); //5竞猜
        		integralDtl.setOrderId(sportGuessCustom.getId());
        		integralDtl.setIntegral(integralNum);
        		integralDtl.setBalance(memberIntegral);
        		integralDtl.setCreateBy(sportTeam.getUpdateBy());
        		integralDtl.setCreateDate(sportTeam.getUpdateDate());
        		integralDtl.setRemarks("世界杯夺冠竞猜中奖");
        		integralDtlList.add(integralDtl); //积分明细
        	}
        	if(memberAccountList.size() > 0) {
        		memberAccountCustomMapper.updateMemberAccountIdListSelective(memberAccountList);
        	}
        	if(integralDtlList.size() > 0) {
        		integralDtlCustomMapper.insertIntegralDtlList(integralDtlList);
        	}
		}else if("1".equals(sportTeam.getResult())) { //1 已淘汰
			//夺冠竞猜失败
			SportGuessExample sportGuessExample = new SportGuessExample();
			SportGuessExample.Criteria sportGuessCriteria = sportGuessExample.createCriteria();
			sportGuessCriteria.andDelFlagEqualTo("0").andGuessWinTeamEqualTo(sportTeam.getId())
    			.andGuessTypeEqualTo("2"); //2夺冠竞猜
        	SportGuess sportGuess = new SportGuess();
        	sportGuess.setResult("3"); //竞猜失败
        	sportGuess.setUpdateBy(sportTeam.getUpdateBy());
        	sportGuess.setUpdateDate(sportTeam.getUpdateDate());
        	sportGuessMapper.updateByExampleSelective(sportGuess, sportGuessExample);
		}
    }
    
    
    /**
     * 
     * @Title addOrUpdateSportTeam   
     * @Description TODO(这里用一句话描述这个方法的作用)   
     * @author pengl
     * @date 2018年5月22日 下午4:59:15
     */
    public void addOrUpdateSportTeam(SportTeam sportTeam) {
    	if(sportTeam.getId() != null) {
    		Integer sportTeamId = sportTeam.getId();
    		SportTeam sportT = sportTeamMapper.selectByPrimaryKey(sportTeamId);
    		sportTeam.setCreateBy(null);
    		sportTeam.setCreateDate(null);
    		sportTeamMapper.updateByPrimaryKeySelective(sportTeam); //修改
    		if(!sportT.getName().equals(sportTeam.getName())) {
    			SportTeamLog sportTeamLogA = new SportTeamLog();
    			sportTeamLogA.setSportTeamId(sportTeamId);
    			sportTeamLogA.setCreateBy(sportTeam.getUpdateBy());
    			sportTeamLogA.setCreateDate(sportTeam.getUpdateDate());
    			sportTeamLogA.setContent("编辑（队伍名称"+sportT.getName()+"改为"+sportTeam.getName()+"）");
    			sportTeamLogMapper.insertSelective(sportTeamLogA);
    		}
    		if(!sportT.getWinRate().setScale(2, BigDecimal.ROUND_HALF_UP).equals(sportTeam.getWinRate().setScale(2, BigDecimal.ROUND_HALF_UP))) {
    			SportTeamLog sportTeamLogB = new SportTeamLog();
    			sportTeamLogB.setSportTeamId(sportTeamId);
    			sportTeamLogB.setCreateBy(sportTeam.getUpdateBy());
    			sportTeamLogB.setCreateDate(sportTeam.getUpdateDate());
    			sportTeamLogB.setContent("编辑（夺冠比例"+sportT.getWinRate().setScale(2, BigDecimal.ROUND_HALF_UP)+"改为"+sportTeam.getWinRate().setScale(2, BigDecimal.ROUND_HALF_UP)+"）");
    			sportTeamLogMapper.insertSelective(sportTeamLogB);
    		}
    		if(!sportT.getPic().equals(sportTeam.getPic())) {
    			SportTeamLog sportTeamLogC = new SportTeamLog();
    			sportTeamLogC.setSportTeamId(sportTeamId);
    			sportTeamLogC.setCreateBy(sportTeam.getUpdateBy());
    			sportTeamLogC.setCreateDate(sportTeam.getUpdateDate());
    			sportTeamLogC.setContent("编辑（修改图标）");
    			sportTeamLogMapper.insertSelective(sportTeamLogC);
    		}
		}else {
			sportTeam.setSportType(1);
			sportTeam.setResult("0");
			sportTeam.setAuditStatus("0");
			sportTeam.setUpdateBy(null);
			sportTeam.setUpdateDate(null);
			sportTeamMapper.insertSelective(sportTeam); //新增
		}
		
		
    }
    
	
}
