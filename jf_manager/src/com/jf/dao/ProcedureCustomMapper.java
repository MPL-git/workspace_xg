package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProcedureCustom;
import com.jf.entity.ProcedureExample;

@Repository
public interface ProcedureCustomMapper {
	
	ProcedureCustom selectCustomByPrimaryKey(Integer id);
    int countCustomByExample(ProcedureExample example);
    List<ProcedureCustom> selectCustomsByExample(ProcedureExample example);

}
