package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinXcxSprChnlCustomMapper;
import com.jf.dao.WeixinXcxSprChnlMapper;
import com.jf.entity.WeixinXcxSprChnl;
import com.jf.entity.WeixinXcxSprChnlCustom;
import com.jf.entity.WeixinXcxSprChnlCustomExample;
import com.jf.entity.WeixinXcxSprChnlExample;

@Service
@Transactional
public class WeixinXcxSprChnlService extends BaseService<WeixinXcxSprChnl, WeixinXcxSprChnlExample> {

	@Autowired
	private WeixinXcxSprChnlMapper weixinXcxSprChnlMapper;
	
	@Autowired
	private WeixinXcxSprChnlCustomMapper weixinXcxSprChnlCustomMapper;

	@Autowired
	public void setWeixinXcxSprChnlMapper(WeixinXcxSprChnlMapper weixinXcxSprChnlMapper) {
		super.setDao(weixinXcxSprChnlMapper);
		this.weixinXcxSprChnlMapper = weixinXcxSprChnlMapper;
	}
	
	public int countByCustomExample(WeixinXcxSprChnlCustomExample example) {
		return weixinXcxSprChnlCustomMapper.countByCustomExample(example);
	}

	public List<WeixinXcxSprChnlCustom> selectByCustomExample(WeixinXcxSprChnlCustomExample example) {
		return weixinXcxSprChnlCustomMapper.selectByCustomExample(example);
	}

	public WeixinXcxSprChnlCustom selectByCustomPrimaryKey(Integer id) {
		return weixinXcxSprChnlCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") WeixinXcxSprChnl record, @Param("example") WeixinXcxSprChnlCustomExample example) {
		return weixinXcxSprChnlCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
}
