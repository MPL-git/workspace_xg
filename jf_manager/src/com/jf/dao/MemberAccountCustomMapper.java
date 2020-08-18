package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountCustom;
import com.jf.entity.MemberAccountCustomExample;
@Repository
public interface MemberAccountCustomMapper {
    
	public int countByCustomExample(MemberAccountCustomExample example);

	public List<MemberAccountCustom> selectByCustomExample(MemberAccountCustomExample example);

	public MemberAccountCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") MemberAccount record, @Param("example") MemberAccountCustomExample example);

	/**
	 * 
	 * @Title updateMemberAccountIdListSelective   
	 * @Description TODO(批量修改会员账户)   
	 * @author pengl
	 * @date 2018年5月23日 下午1:03:13
	 */
	public void updateMemberAccountIdListSelective(List<MemberAccount> memberAccountList);

	public int updateMemberAccountBalanceAndFreeze(MemberAccount newAccount);
	
}