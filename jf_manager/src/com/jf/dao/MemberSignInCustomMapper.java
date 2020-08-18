package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSignIn;
import com.jf.entity.MemberSignInCustom;
import com.jf.entity.MemberSignInCustomExample;

@Repository
public interface MemberSignInCustomMapper {
    
	public int countByCustomExample(MemberSignInCustomExample example);

	public List<MemberSignInCustom> selectByCustomExample(MemberSignInCustomExample example);

	public MemberSignInCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") MemberSignIn record, @Param("example") MemberSignInCustomExample example);

}