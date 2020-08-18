package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.WeixinXcxSprDtl;
import com.jf.entity.WeixinXcxSprDtlCustom;
import com.jf.entity.WeixinXcxSprDtlCustomExample;

@Repository
public interface WeixinXcxSprDtlCustomMapper {
    int countByCustomExample(WeixinXcxSprDtlCustomExample example);

    List<WeixinXcxSprDtlCustom> selectByCustomExample(WeixinXcxSprDtlCustomExample example);

    WeixinXcxSprDtlCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") WeixinXcxSprDtl record, @Param("example") WeixinXcxSprDtlCustomExample example);

    List<Map<String, Object>> getWeixinXcxSprDtlList(Map<String, Object> paramMap);
	
	int countWeixinXcxSprDtlList(Map<String, Object> paramMap);
	
	List<Map<String, Object>> getCountSubOrderList(Map<String, Object> paramMap);
    
}