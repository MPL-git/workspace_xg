package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.IndexPopupAdCustomMapper;
import com.jf.dao.IndexPopupAdMapper;
import com.jf.entity.IndexPopupAd;
import com.jf.entity.IndexPopupAdCustom;
import com.jf.entity.IndexPopupAdCustomExample;
import com.jf.entity.IndexPopupAdExample;

@Service
public class IndexPopupAdService extends BaseService<IndexPopupAd, IndexPopupAdExample> {

	@Autowired
	private IndexPopupAdMapper indexPopupAdMapper;
	
	@Autowired
	private IndexPopupAdCustomMapper indexPopupAdCustomMapper;
	
	@Autowired
	public void setIndexPopupAdMapper(IndexPopupAdMapper indexPopupAdMapper) {
		super.setDao(indexPopupAdMapper);
		this.indexPopupAdMapper = indexPopupAdMapper;
	}
	
	public int indexPopupAdcountByExample(IndexPopupAdCustomExample example) {
		return indexPopupAdCustomMapper.indexPopupAdcountByExample(example);
	}

	public List<IndexPopupAdCustom> indexPopupAdselectByExample(IndexPopupAdCustomExample example) {
		return indexPopupAdCustomMapper.indexPopupAdselectByExample(example);
	}
	
	public IndexPopupAdCustom indexPopupAdselectByPrimaryKey(Integer id) {
		return indexPopupAdCustomMapper.indexPopupAdselectByPrimaryKey(id);
	}


}
