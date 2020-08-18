package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SportGuess;
import com.jf.entity.SportGuessCustom;
import com.jf.entity.SportGuessCustomExample;

@Repository
public interface SportGuessCustomMapper {
    
	public int countByCustomExample(SportGuessCustomExample example);

	public List<SportGuessCustom> selectByCustomExample(SportGuessCustomExample example);

	public SportGuessCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") SportGuess record, @Param("example") SportGuessCustomExample example);

}