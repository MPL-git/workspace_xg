package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ToutiaoCampaignInfo;
import com.jf.entity.ToutiaoCampaignInfoCustom;
import com.jf.entity.ToutiaoCampaignInfoCustomExample;

@Repository
public interface ToutiaoCampaignInfoCustomMapper {
    
	public int countByCustomExample(ToutiaoCampaignInfoCustomExample example);

	public List<ToutiaoCampaignInfoCustom> selectByCustomExample(ToutiaoCampaignInfoCustomExample example);

	public ToutiaoCampaignInfoCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") ToutiaoCampaignInfo record, @Param("example") ToutiaoCampaignInfoCustomExample example);

}