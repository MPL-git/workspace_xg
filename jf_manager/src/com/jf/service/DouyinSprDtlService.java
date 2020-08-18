package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DouyinSprDtlCustomMapper;
import com.jf.dao.DouyinSprDtlMapper;
import com.jf.entity.DouyinSprDtl;
import com.jf.entity.DouyinSprDtlCustom;
import com.jf.entity.DouyinSprDtlCustomExample;
import com.jf.entity.DouyinSprDtlExample;

@Service
@Transactional
public class DouyinSprDtlService extends BaseService<DouyinSprDtl, DouyinSprDtlExample> {

	@Autowired
	private DouyinSprDtlMapper douyinSprDtlMapper;
	
	@Autowired
	private DouyinSprDtlCustomMapper douyinSprDtlCustomMapper;
	
	@Autowired
	public void setDouyinSprDtlMapper(DouyinSprDtlMapper douyinSprDtlMapper) {
		super.setDao(douyinSprDtlMapper);
		this.douyinSprDtlMapper = douyinSprDtlMapper;
	}
	
	public int countByCustomExample(DouyinSprDtlCustomExample example) {
		return douyinSprDtlCustomMapper.countByCustomExample(example);
	}

	public List<DouyinSprDtlCustom> selectByCustomExample(DouyinSprDtlCustomExample example) {
		return douyinSprDtlCustomMapper.selectByCustomExample(example);
	}

	public DouyinSprDtlCustom selectByCustomPrimaryKey(Integer id) {
		return douyinSprDtlCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") DouyinSprDtl record, @Param("example") DouyinSprDtlCustomExample example) {
		return douyinSprDtlCustomMapper.updateByCustomExampleSelective(record, example);
	}

	
}
