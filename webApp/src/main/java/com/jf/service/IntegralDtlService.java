package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.IntegralDtlCustomMapper;
import com.jf.dao.IntegralDtlMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlCustom;
import com.jf.entity.IntegralDtlExample;
import com.jf.entity.MemberAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午6:32:37 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class IntegralDtlService extends BaseService<IntegralDtl, IntegralDtlExample> {
	
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	
	@Autowired
	private IntegralDtlCustomMapper integralDtlCustomMapper;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	
	public void setIntegralDtlMapper(IntegralDtlMapper integralDtlMapper) {
		this.setDao(integralDtlMapper);
		this.integralDtlMapper = integralDtlMapper;
	}
	

	public List<IntegralDtlCustom> getIntegralDtlList(Map<String, Object> params) {

		return integralDtlCustomMapper.getIntegralDtlList(params);
	}

	public Integer getIntegralDtlCount(Map<String, Object> params) {
		
		return integralDtlCustomMapper.getIntegralDtlCount(params);
	}


	public IntegralDtl addIntegralDtl(Integer accId, String tallyMode, String type, Integer integral, Integer balance,Integer orderId , Integer memberId, String bizType) {
		IntegralDtl integralDtl = new IntegralDtl();
		integralDtl.setAccId(accId);
		integralDtl.setIntegral(integral);
		integralDtl.setBalance(balance);
		integralDtl.setTallyMode(tallyMode);
		integralDtl.setType(Integer.valueOf(type));
		integralDtl.setBizType(bizType);
		integralDtl.setOrderId(orderId);
		integralDtl.setCreateBy(memberId);
		integralDtl.setDelFlag("0");
		saveModel(integralDtl);
		return integralDtl;
	}


	public void saveModel(IntegralDtl integralDtl) {
		integralDtl.setCreateDate(new Date());
		integralDtlMapper.insertSelective(integralDtl);
	}


	/**
	 * 
	 * 方法描述 ：获取进出账积分
	 * @author  chenwf: 
	 * @date 创建时间：2017年12月18日 下午5:07:19 
	 * @version
	 * @param params
	 * @return
	 */
	public List<IntegralDtl> getImportAccountIntegral(Map<String, Object> params) {
		
		return integralDtlCustomMapper.getImportAccountIntegral(params);
	}


	/**
	 * 是否领取新星攻略积分
	 * @param accId
	 * @return
	 */
	public boolean getIsNoviceIntegral(Integer accId, Integer integralType) {
		IntegralDtlExample integralDtlExample = new IntegralDtlExample();
		integralDtlExample.createCriteria().andAccIdEqualTo(accId).andTypeEqualTo(integralType).andDelFlagEqualTo("0");
		return countByExample(integralDtlExample) > 0 ? true : false;
	}


	public void save(IntegralDtl integralDtl, MemberAccount memberAccount) {
		this.insertSelective(integralDtl);
		memberAccountService.updateByPrimaryKeySelective(memberAccount);
	}
		
}
