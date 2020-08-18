package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountDtlCustom;
import com.jf.entity.MemberAccountDtlCustomExample;

@Repository
public interface MemberAccountDtlCustomMapper {
    
	public int countByCustomExample(MemberAccountDtlCustomExample example);

	public List<MemberAccountDtlCustom> selectByCustomExample(MemberAccountDtlCustomExample example);

	public MemberAccountDtlCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") MemberAccountDtl record, @Param("example") MemberAccountDtlCustomExample example);

	//分润记录总数
	public Integer countByCustomExampleFenRun(
			MemberAccountDtlCustomExample memberAccountDtlCustomExample);
	
	//分润记录列表
	public List<MemberAccountDtlCustom> selectByCustomExampleFenRun(
			MemberAccountDtlCustomExample memberAccountDtlCustomExample);
	
	//新星余额每日汇总
	public List<MemberAccountDtlCustom> selectMemberAccountDtlCountEachDayList(
			HashMap<String, Object> paramMap);
}