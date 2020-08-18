package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFavorItesCustom;
import com.jf.entity.MemberFavorItesCustomExample;
@Repository
public interface MemberFavorItesCustomMapper{
	public List<MemberFavorItesCustom> selectByExample (MemberFavorItesCustomExample memberfavoritesCustomExample);
	int countByCustomExample(MemberFavorItesCustomExample example);
	
}