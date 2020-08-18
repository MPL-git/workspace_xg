package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.BottomMapper;

@Service
public class BottomService {
	@Autowired BottomMapper bottomMapper;
	public List<Map<String,Object>> selectBottom(Map<String,Object> paramMap){
		return bottomMapper.selectBottom(paramMap);
	}
	public void updateBottom (Map<String,Object> paramMap){
		bottomMapper.updateBottom(paramMap);
	}
}
