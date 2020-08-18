package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DouyinSprChnlCustomMapper;
import com.jf.dao.DouyinSprChnlMapper;
import com.jf.entity.DouyinSprChnl;
import com.jf.entity.DouyinSprChnlCustom;
import com.jf.entity.DouyinSprChnlCustomExample;
import com.jf.entity.DouyinSprChnlExample;

@Service
@Transactional
public class DouyinSprChnlService extends BaseService<DouyinSprChnl, DouyinSprChnlExample> {

	@Autowired
	private DouyinSprChnlMapper douyinSprChnlMapper;
	
	@Autowired
	private DouyinSprChnlCustomMapper douyinSprChnlCustomMapper;
	
	@Autowired
	public void setDouyinSprChnlMapper(DouyinSprChnlMapper douyinSprChnlMapper) {
		super.setDao(douyinSprChnlMapper);
		this.douyinSprChnlMapper = douyinSprChnlMapper;
	}
	
	public int countByCustomExample(DouyinSprChnlCustomExample example) {
		return douyinSprChnlCustomMapper.countByCustomExample(example);
	}

	public List<DouyinSprChnlCustom> selectByCustomExample(DouyinSprChnlCustomExample example) {
		return douyinSprChnlCustomMapper.selectByCustomExample(example);
	}

	public DouyinSprChnlCustom selectByCustomPrimaryKey(Integer id) {
		return douyinSprChnlCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") DouyinSprChnl record, @Param("example") DouyinSprChnlCustomExample example) {
		return douyinSprChnlCustomMapper.updateByCustomExampleSelective(record, example);
	}

	
}
