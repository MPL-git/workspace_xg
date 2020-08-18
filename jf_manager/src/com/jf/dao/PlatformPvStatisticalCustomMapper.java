package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.PlatformPvStatistical;
import com.jf.entity.PlatformPvStatisticalCustom;
import com.jf.entity.PlatformPvStatisticalCustomExample;

@Repository
public interface PlatformPvStatisticalCustomMapper {
   
	public int countByCustomExample(PlatformPvStatisticalCustomExample example);

	public List<PlatformPvStatisticalCustom> selectByCustomExample(PlatformPvStatisticalCustomExample example);

	public PlatformPvStatisticalCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") PlatformPvStatistical record, @Param("example") PlatformPvStatisticalCustomExample example);

    public Map<String, Object> getPlatformPvStatisticalSumUp(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> getPlatformPvStatisticalMSAList(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> getPlatformPvStatisticalUpstreamList(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> getPlatformPvStatisticalDownstreamList(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> getPlatformPvStatisticalDtlList(Map<String, Object> paramMap);

    public int getPlatformPvStatisticalDtlCount(Map<String, Object> paramMap);
    
    public Map<String, Object> getFlowProductPvMap(Map<String, Object> paramMap);
    
    public Map<String, Object> getProductPvMap(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> getProductPvList(Map<String, Object> paramMap);
    
    public Integer getProductPvCount(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> getPlatformPvMemberIdCountList(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> getPlatformPvPvIdCountList(Map<String, Object> paramMap);

    public Integer getShoppingCartId(Integer orderDtlId);

    public String getColumnType(Integer shoppingCartId);


    
}