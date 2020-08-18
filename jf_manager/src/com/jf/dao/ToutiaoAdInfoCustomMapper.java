package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ToutiaoAdInfo;
import com.jf.entity.ToutiaoAdInfoCustom;
import com.jf.entity.ToutiaoAdInfoCustomExample;

@Repository
public interface ToutiaoAdInfoCustomMapper {
    
	public int countByCustomExample(ToutiaoAdInfoCustomExample example);

	public List<ToutiaoAdInfoCustom> selectByCustomExampleWithBLOBs(ToutiaoAdInfoCustomExample example);
	
	public List<ToutiaoAdInfoCustom> selectByCustomExample(ToutiaoAdInfoCustomExample example);

	public ToutiaoAdInfoCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") ToutiaoAdInfo record, @Param("example") ToutiaoAdInfoCustomExample example);

}