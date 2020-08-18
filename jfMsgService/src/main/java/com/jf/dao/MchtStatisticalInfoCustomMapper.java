package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtStatisticalInfo;
@Repository
public interface MchtStatisticalInfoCustomMapper{
	 MchtStatisticalInfo selectMchtIdKey(@Param("mchtId") Integer mchtId);
	
	 List<MchtStatisticalInfo> selectMchtIdByExample(@Param("mchtId") Integer mchtId);
	
}