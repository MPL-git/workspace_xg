package com.jf.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberAccountDtl;
@Repository
public interface MemberAccountDtlCustomMapper{
	MemberAccountDtl selectAccIdKey(@Param("accId") Integer accId);

	int getIsReceiveSubFristReward(Map<String, Object> paramsMap);
}