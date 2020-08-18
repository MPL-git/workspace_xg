package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtShopDynamicCustomMapper;
import com.jf.dao.MchtShopDynamicMapper;
import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicCustom;
import com.jf.entity.MchtShopDynamicExample;

@Service
@Transactional
public class MchtShopDynamicService extends BaseService<MchtShopDynamic,MchtShopDynamicExample> {
	@Autowired
	private MchtShopDynamicMapper mchtShopDynamicMapper;
	@Autowired
	private MchtShopDynamicCustomMapper mchtShopDynamicCustomMapper;
	@Autowired
	public void setMchtShopDynamicMapper(MchtShopDynamicMapper mchtShopDynamicMapper) {
		super.setDao(mchtShopDynamicMapper);
		this.mchtShopDynamicMapper = mchtShopDynamicMapper;
	}
	
	public int countMchtShopDynamicCustomByExample(MchtShopDynamicExample example){
		return mchtShopDynamicCustomMapper.countByExample(example);
	}
    public List<MchtShopDynamicCustom> selectMchtShopDynamicCustomByExample(MchtShopDynamicExample example){
    	return mchtShopDynamicCustomMapper.selectByExample(example);
    }
}
