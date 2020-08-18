package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ToutiaoAdvertiserInfo;
import com.jf.entity.ToutiaoAdvertiserInfoCustom;
import com.jf.entity.ToutiaoAdvertiserInfoCustomExample;
import com.jf.entity.ToutiaoAdvertiserInfoExample;

@Repository
public interface ToutiaoAdvertiserInfoCustomMapper extends BaseDao<ToutiaoAdvertiserInfo, ToutiaoAdvertiserInfoExample> {
    
	public int countByCustomExample(ToutiaoAdvertiserInfoCustomExample example);

	public List<ToutiaoAdvertiserInfoCustom> selectByCustomExample(ToutiaoAdvertiserInfoCustomExample example);

	public ToutiaoAdvertiserInfoCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") ToutiaoAdvertiserInfo record, @Param("example") ToutiaoAdvertiserInfoCustomExample example);

}