package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.TaskMarketing;
import com.jf.entity.TaskMarketingCustom;
import com.jf.entity.TaskMarketingCustomExample;

@Repository
public interface TaskMarketingCustomMapper {
    
	public int countByCustomExample(TaskMarketingCustomExample example);

	public List<TaskMarketingCustom> selectByCustomExample(TaskMarketingCustomExample example);

	public TaskMarketingCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") TaskMarketing record, @Param("example") TaskMarketingCustomExample example);

}