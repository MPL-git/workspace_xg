package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.SportCustom;
@Repository
public interface SportCustomMapper{

	List<SportCustom> getSportWinGuessList(Map<String, Object> paramsMap);

	List<SportCustom> getSportRecordList(Map<String, Object> paramsMap);

	List<SportCustom> getSportGuessRecord(Map<String, Object> paramsMap);
	
}