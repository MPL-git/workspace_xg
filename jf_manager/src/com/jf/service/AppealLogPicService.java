package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AppealLogPicCustomMapper;
import com.jf.dao.AppealLogPicMapper;
import com.jf.entity.AppealLogPic;
import com.jf.entity.AppealLogPicCustom;
import com.jf.entity.AppealLogPicCustomExample;
import com.jf.entity.AppealLogPicExample;

@Service
@Transactional
public class AppealLogPicService extends BaseService<AppealLogPic,AppealLogPicExample> {
	@Autowired
	private AppealLogPicMapper dao;
	
	@Autowired
	private AppealLogPicCustomMapper appealLogPicCustomMapper;
	
	@Autowired
	public void setAppealLogPicMapper(AppealLogPicMapper appealLogPicMapper) {
		super.setDao(appealLogPicMapper);
		this.dao = appealLogPicMapper;
	}

	public List<AppealLogPicCustom> selectByAppealLogPicCustomExample(AppealLogPicCustomExample example) {
		return appealLogPicCustomMapper.selectByExample(example);
	}
	
	public List<String> getPicsByAppealLogId(Integer appealLogId) {
		return appealLogPicCustomMapper.getPicsByAppealLogId(appealLogId);
	}
}
