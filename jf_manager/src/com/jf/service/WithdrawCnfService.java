package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WithdrawCnfCustomMapper;
import com.jf.dao.WithdrawCnfMapper;
import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfCustom;
import com.jf.entity.WithdrawCnfCustomExample;
import com.jf.entity.WithdrawCnfExample;

@Service
@Transactional
public class WithdrawCnfService extends BaseService<WithdrawCnf, WithdrawCnfExample> {

	@Autowired
	private WithdrawCnfMapper withdrawCnfMapper;
	
	@Autowired
	private WithdrawCnfCustomMapper withdrawCnfCustomMapper;
	
	@Autowired
	public void setWithdrawCnfMapper(WithdrawCnfMapper withdrawCnfMapper) {
		super.setDao(withdrawCnfMapper);
		this.withdrawCnfMapper = withdrawCnfMapper;
	}
	
	public int countByCustomExample(WithdrawCnfCustomExample example) {
		return withdrawCnfCustomMapper.countByCustomExample(example);
	}

    public List<WithdrawCnfCustom> selectByCustomExample(WithdrawCnfCustomExample example) {
    	return withdrawCnfCustomMapper.selectByCustomExample(example);
    }

    public WithdrawCnfCustom selectByCustomPrimaryKey(Integer id) {
    	return withdrawCnfCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(WithdrawCnf record, WithdrawCnfCustomExample example) {
    	return withdrawCnfCustomMapper.updateByCustomExampleSelective(record, example);
    }
	
	
}
