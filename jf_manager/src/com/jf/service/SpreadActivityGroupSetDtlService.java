package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SpreadActivityGroupSetDtlCustomMapper;
import com.jf.dao.SpreadActivityGroupSetDtlMapper;
import com.jf.entity.SpreadActivityGroupSetDtl;
import com.jf.entity.SpreadActivityGroupSetDtlCustom;
import com.jf.entity.SpreadActivityGroupSetDtlCustomExample;
import com.jf.entity.SpreadActivityGroupSetDtlExample;

@Service
@Transactional
public class SpreadActivityGroupSetDtlService extends BaseService<SpreadActivityGroupSetDtl, SpreadActivityGroupSetDtlExample> {

	@Autowired
	private SpreadActivityGroupSetDtlMapper spreadActivityGroupSetDtlMapper;
	
	@Autowired
	private SpreadActivityGroupSetDtlCustomMapper spreadActivityGroupSetDtlCustomMapper;
	
	@Autowired
	public void setSpreadActivityGroupSetDtlMapper(SpreadActivityGroupSetDtlMapper spreadActivityGroupSetDtlMapper) {
		super.setDao(spreadActivityGroupSetDtlMapper);
		this.dao = spreadActivityGroupSetDtlMapper;
	}
	
	public int countByCustomExample(SpreadActivityGroupSetDtlCustomExample example) {
		return spreadActivityGroupSetDtlCustomMapper.countByCustomExample(example);
	}

    public List<SpreadActivityGroupSetDtlCustom> selectByCustomExample(SpreadActivityGroupSetDtlCustomExample example) {
    	return spreadActivityGroupSetDtlCustomMapper.selectByCustomExample(example);
    }

    public SpreadActivityGroupSetDtlCustom selectByCustomPrimaryKey(Integer id) {
    	return spreadActivityGroupSetDtlCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(@Param("record") SpreadActivityGroupSetDtl record, @Param("example") SpreadActivityGroupSetDtlCustomExample example) {
    	return spreadActivityGroupSetDtlCustomMapper.updateByCustomExampleSelective(record, example);
    }

	
}
