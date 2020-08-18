package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBlPicCustomMapper;
import com.jf.dao.MchtBlPicMapper;
import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;

@Service
@Transactional
public class MchtBlPicService extends BaseService<MchtBlPic,MchtBlPicExample> {
	@Autowired
	private MchtBlPicMapper mchtBlPicMapper;
	
	@Autowired
	private MchtBlPicCustomMapper mchtBlPicCustomMapper;
	
	@Autowired
	public void setMchtBlPicMapper(MchtBlPicMapper mchtBlPicMapper) {
		super.setDao(mchtBlPicMapper);
		this.mchtBlPicMapper = mchtBlPicMapper;
	}
	
	
	public List<MchtBlPic> selectNoWatermarkBlPic(){
		return mchtBlPicCustomMapper.selectNoWatermarkBlPic();
	}
}
