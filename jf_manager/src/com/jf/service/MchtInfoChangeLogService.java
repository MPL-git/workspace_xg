package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtInfoChangeLogCustomMapper;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.entity.MchtInfoChangeLog;
import com.jf.entity.MchtInfoChangeLogCustom;
import com.jf.entity.MchtInfoChangeLogExample;

@Service
@Transactional
public class MchtInfoChangeLogService extends BaseService<MchtInfoChangeLog,MchtInfoChangeLogExample> {
	@Autowired
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Autowired
	private MchtInfoChangeLogCustomMapper mchtInfoChangeLogCustomMapper;
	
	@Autowired
	public void setMchtInfoChangeLogMapper(MchtInfoChangeLogMapper mchtInfoChangeLogMapper) {
		super.setDao(mchtInfoChangeLogMapper);
		this.mchtInfoChangeLogMapper = mchtInfoChangeLogMapper;
	}
	
	
   public List<MchtInfoChangeLogCustom> selectMchtInfoChangeLogCustomByExample(MchtInfoChangeLogExample example){
	   return mchtInfoChangeLogCustomMapper.selectByExample(example);
   }
	
   public List<Map<String, Object>> selectCreatorByListExample(MchtInfoChangeLogExample example) {
	   return mchtInfoChangeLogCustomMapper.selectCreatorByListExample(example);
   }
   
   public int countByExample(MchtInfoChangeLogExample example) {
	   return mchtInfoChangeLogCustomMapper.countByExample(example);
   }

public int rejectAuditCount(HashMap<String, Object> paramMap) {
	return mchtInfoChangeLogCustomMapper.rejectAuditCount(paramMap);
}
   
}
