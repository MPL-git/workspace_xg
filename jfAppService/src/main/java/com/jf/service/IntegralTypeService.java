package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntegralTypeMapper;
import com.jf.entity.IntegralType;
import com.jf.entity.IntegralTypeExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	public List<IntegralType> findListQuery(QueryObject queryObject) {
		
		return integralTypeMapper.selectByExample(builderQuery(queryObject));
	}

	private IntegralTypeExample builderQuery(QueryObject queryObject) {
		IntegralTypeExample example = new IntegralTypeExample();
		IntegralTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}

}
