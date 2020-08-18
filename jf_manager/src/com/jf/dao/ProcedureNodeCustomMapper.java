package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProcedureNodeCustom;
import com.jf.entity.ProcedureNodeExample;

@Repository
public interface ProcedureNodeCustomMapper {
	

	ProcedureNodeCustom selectCustomByPrimaryKey(Integer id);
    int countCustomByExample(ProcedureNodeExample example);
    List<ProcedureNodeCustom> selectCustomsByExample(ProcedureNodeExample example);

}
