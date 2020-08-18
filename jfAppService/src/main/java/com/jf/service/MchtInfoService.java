package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtInfoCustomMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.entity.Coupon;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoExample;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月15日 上午11:53:14 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MchtInfoService extends BaseService<MchtInfo, MchtInfoExample> {
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	@Autowired
	private MchtInfoCustomMapper mchtInfoCustomMapper;
	
	@Autowired
	public void setMchtInfoMapper(MchtInfoMapper mchtInfoMapper) {
		this.setDao(mchtInfoMapper);
		this.mchtInfoMapper = mchtInfoMapper;
	}

	public MchtInfo findModelByCode(String linkValue) {
		MchtInfo m = null;
		MchtInfoExample mchtInfoExample = new MchtInfoExample();
		mchtInfoExample.createCriteria().andMchtCodeEqualTo(linkValue);
		List<MchtInfo> mchtInfos = selectByExample(mchtInfoExample);
		if(CollectionUtils.isNotEmpty(mchtInfos)){
			m = mchtInfos.get(0);
		}
		return m;
	}

	public List<MchtInfoCustom> getEveryDayShopList(Map<String, Object> paramsMap) {
		
		return mchtInfoCustomMapper.getEveryDayShopList(paramsMap);
	}

	public List<Coupon> getCouponListByIds(Map<String, Object> paramsMap) {
		return mchtInfoCustomMapper.getCouponListByIds(paramsMap);
	}

}
