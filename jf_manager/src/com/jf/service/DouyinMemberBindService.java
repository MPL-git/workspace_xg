package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DouyinMemberBindCustomMapper;
import com.jf.dao.DouyinMemberBindMapper;
import com.jf.entity.DouyinMemberBind;
import com.jf.entity.DouyinMemberBindCustom;
import com.jf.entity.DouyinMemberBindCustomExample;
import com.jf.entity.DouyinMemberBindExample;

@Service
@Transactional
public class DouyinMemberBindService extends BaseService<DouyinMemberBind, DouyinMemberBindExample> {

	@Autowired
	private DouyinMemberBindMapper douyinMemberBindMapper;
	
	@Autowired
	private DouyinMemberBindCustomMapper douyinMemberBindCustomMapper;
	
	@Autowired
	public void setDouyinMemberBindMapper(DouyinMemberBindMapper douyinMemberBindMapper) {
		super.setDao(douyinMemberBindMapper);
		this.douyinMemberBindMapper = douyinMemberBindMapper;
	}
	
	public int countByCustomExample(DouyinMemberBindCustomExample example) {
		return douyinMemberBindCustomMapper.countByCustomExample(example);
	}

	public List<DouyinMemberBindCustom> selectByCustomExample(DouyinMemberBindCustomExample example) {
		return douyinMemberBindCustomMapper.selectByCustomExample(example);
	}

	public DouyinMemberBindCustom selectByCustomPrimaryKey(Integer id) {
		return douyinMemberBindCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") DouyinMemberBind record, @Param("example") DouyinMemberBindCustomExample example) {
		return douyinMemberBindCustomMapper.updateByCustomExampleSelective(record, example);
	}

	
}
