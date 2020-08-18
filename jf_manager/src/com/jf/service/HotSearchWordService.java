package com.jf.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.HotSearchWordCustomMapper;
import com.jf.dao.HotSearchWordMapper;
import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordCustom;
import com.jf.entity.HotSearchWordCustomExample;
import com.jf.entity.HotSearchWordExample;

@Service
@Transactional
public class HotSearchWordService extends BaseService<HotSearchWord, HotSearchWordExample> {

	@Autowired
	private HotSearchWordMapper hotSearchWordMapper;
	
	@Autowired
	private HotSearchWordCustomMapper hotSearchWordCustomMapper;
	
	@Autowired
	public void setHotSearchWordMapper(HotSearchWordMapper hotSearchWordMapper) {
		super.setDao(hotSearchWordMapper);
		this.hotSearchWordMapper = hotSearchWordMapper;
	}
	
	public int countByCustomExample(HotSearchWordCustomExample example) {
		return hotSearchWordCustomMapper.countByCustomExample(example);
	}

	public List<HotSearchWordCustom> selectByCustomExample(HotSearchWordCustomExample example) {
		return hotSearchWordCustomMapper.selectByCustomExample(example);
	}

	public HotSearchWordCustom selectByCustomPrimaryKey(Integer id) {
		return hotSearchWordCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") HotSearchWord record, @Param("example") HotSearchWordCustomExample example) {
		return hotSearchWordCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public List<HotSearchWordCustom> selectByCustomExampleNew(Map<String, Object> map) {
		return hotSearchWordCustomMapper.selectByCustomExampleNew(map);
	}
}
