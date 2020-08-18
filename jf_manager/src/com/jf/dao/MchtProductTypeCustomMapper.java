package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtProductTypeCustom;
import com.jf.entity.MchtProductTypeCustomExample;
import com.jf.vo.Page;
@Repository
public interface MchtProductTypeCustomMapper{
	List<MchtProductTypeCustom> getMchtProductTypeCustomsByMchtId(@Param("mchtId")Integer mchtId,@Param("page")Page page);
	
	  	int countByExample(MchtProductTypeCustomExample example);

	    List<MchtProductTypeCustom> selectByExample(MchtProductTypeCustomExample example);

	    MchtProductTypeCustom selectByPrimaryKey(Integer id);

		List<HashMap<String, Object>> countByProductType(HashMap<String, Object> paramMap);
}