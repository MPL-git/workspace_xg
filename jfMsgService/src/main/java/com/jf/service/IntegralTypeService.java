package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntegralTypeMapper;
import com.jf.entity.IntegralType;
import com.jf.entity.IntegralTypeExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月18日 下午2:31:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class IntegralTypeService extends BaseService<IntegralType, IntegralTypeExample> {
	@Autowired
	private IntegralTypeMapper integralTypeMapper;
	
	@Autowired
	public void setIntegralTypeMapper(IntegralTypeMapper integralTypeMapper) {
		this.setDao(integralTypeMapper);
		this.integralTypeMapper = integralTypeMapper;
	}

	public IntegralType findModel(Integer id) {
		IntegralTypeExample integralTypeExample = new IntegralTypeExample();
		integralTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andIdEqualTo(id);
		List<IntegralType> integralTypes = integralTypeMapper.selectByExample(integralTypeExample);
		if(CollectionUtils.isNotEmpty(integralTypes)){
			return integralTypes.get(0);
		}
		return null;
	}
	
	
}
