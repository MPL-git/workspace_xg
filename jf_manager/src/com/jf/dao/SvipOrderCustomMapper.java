package com.jf.dao;

import com.jf.entity.SvipOrderCustom;
import com.jf.entity.SvipOrderCustomExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SvipOrderCustomMapper {
    
	 int countByCustomExample(SvipOrderCustomExample example);

	 List<SvipOrderCustom> selectByCustomExample(SvipOrderCustomExample example);

	 SvipOrderCustom selectByCustomPrimaryKey(Integer id);

	 int updateByCustomExampleSelective(@Param("record") SvipOrderCustom record, @Param("example") SvipOrderCustomExample example);

	 List<Map<String, Object>> countSvipOrderList(Map<String, Object> paramMap);

	 List<Map<String, Object>> refCountSvipOrderList(Map<String, Object> paramMap);
	
}