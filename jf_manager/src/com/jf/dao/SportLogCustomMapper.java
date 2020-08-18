package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SportLog;
import com.jf.entity.SportLogCustom;
import com.jf.entity.SportLogCustomExample;

@Repository
public interface SportLogCustomMapper {
   
	public int countByCustomExample(SportLogCustomExample example);

	public List<SportLogCustom> selectByCustomExample(SportLogCustomExample example);

	public SportLogCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") SportLog record, @Param("example") SportLogCustomExample example);

	/**
	 * 
	 * @Title insertSportLogList   
	 * @Description TODO(批量插入)   
	 * @author pengl
	 * @date 2018年5月23日 下午6:37:34
	 */
	public void insertSportLogList(List<SportLog> sportLogList);
	
}