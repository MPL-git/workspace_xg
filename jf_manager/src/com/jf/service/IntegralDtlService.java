package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntegralDtlCustomMapper;
import com.jf.dao.IntegralDtlMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlCustom;
import com.jf.entity.IntegralDtlExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IntegralDtlService extends BaseService<IntegralDtl,IntegralDtlExample> {
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	@Autowired
	private IntegralDtlCustomMapper integralDtlCustomMapper;
	@Autowired
	public void setIntegralDtlMapper(IntegralDtlMapper integralDtlMapper) {
		super.setDao(integralDtlMapper);
		this.integralDtlMapper = integralDtlMapper;
	}
	
	public int countIntegralDtlCustomByExample(IntegralDtlExample example){
		return integralDtlCustomMapper.countByExample(example);
	}
    public List<IntegralDtlCustom> selectIntegralDtlCustomByExample(IntegralDtlExample example){
    	return integralDtlCustomMapper.selectByExample(example);
    }
    public IntegralDtlCustom selectIntegralDtlCustomByPrimaryKey(Integer id){
    	return integralDtlCustomMapper.selectByPrimaryKey(id);
    }

	
	public int sumIntegral(QueryObject queryObject) {
		return integralDtlCustomMapper.sumIntegralByExample(builderQuery(queryObject));
	}
	public int selectTotalIntegral() {
		return integralDtlCustomMapper.selectTotalIntegral();
	}

	public List<IntegralDtl> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private IntegralDtlExample builderQuery(QueryObject queryObject) {
		IntegralDtlExample example = new IntegralDtlExample();
		IntegralDtlExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("tallyMode") != null){
			criteria.andTallyModeEqualTo(queryObject.getQueryToStr("tallyMode"));
		}
		if(queryObject.getQuery("createDateAfter") != null){
			criteria.andCreateDateGreaterThan(queryObject.getQueryToDate("createDateAfter"));
		}
		if(queryObject.getQuery("createDateBefore") != null){
			criteria.andCreateDateLessThan(queryObject.getQueryToDate("createDateBefore"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
