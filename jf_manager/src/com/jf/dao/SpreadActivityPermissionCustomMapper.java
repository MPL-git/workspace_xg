package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SpreadActivityPermission;
import com.jf.entity.SpreadActivityPermissionCustom;
import com.jf.entity.SpreadActivityPermissionCustomExample;
import com.jf.entity.SpreadActivityPermissionExample;

@Repository
public interface SpreadActivityPermissionCustomMapper {
    
	public int countByCustomExample(SpreadActivityPermissionExample example);

	public List<SpreadActivityPermissionCustom> selectByCustomExample(SpreadActivityPermissionCustomExample example);

	public SpreadActivityPermissionCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") SpreadActivityPermission record, @Param("example") SpreadActivityPermissionCustomExample example);

	public Integer getSpreadActivityGroupCount(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> getSpreadActivityGroupList(Map<String, Object> paramMap);
	
	public Integer getAndroidChannelGroupCount(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> getAndroidChannelGroupList(Map<String, Object> paramMap);
	
}