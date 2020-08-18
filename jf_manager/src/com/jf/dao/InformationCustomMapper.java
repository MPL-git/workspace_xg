package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.Information;
import com.jf.entity.InformationCustom;
import com.jf.entity.InformationExample;

@Repository
public interface InformationCustomMapper {
	public List<InformationCustom> selectInformationByExample(InformationExample example);
	public Integer countByExample(InformationExample example);
    int updateReleaseTimeByPrimaryKey(Information record);
}
