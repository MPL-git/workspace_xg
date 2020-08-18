package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SpUserCustom;
import com.jf.entity.SpUserCustomExample;

@Repository
public interface SpUserCustomMapper {
   
	public int countByExample(SpUserCustomExample example);

	public List<SpUserCustom> selectByExample(SpUserCustomExample example);

}