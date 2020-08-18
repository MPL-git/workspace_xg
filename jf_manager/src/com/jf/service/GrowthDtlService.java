package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.GrowthDtlCustomMapper;
import com.jf.dao.GrowthDtlMapper;
import com.jf.entity.GrowthDtl;
import com.jf.entity.GrowthDtlCustom;
import com.jf.entity.GrowthDtlExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GrowthDtlService extends BaseService<GrowthDtl,GrowthDtlExample> {
	@Autowired
	private GrowthDtlMapper growthDtlMapper;
	@Autowired
	private GrowthDtlCustomMapper growthDtlCustomMapper;
	@Autowired
	public void setGrowthDtlMapper(GrowthDtlMapper growthDtlMapper) {
		super.setDao(growthDtlMapper);
		this.growthDtlMapper = growthDtlMapper;
	}
	
	public int countGrowthDtlCustomByExample(GrowthDtlExample example){
		return growthDtlCustomMapper.countByExample(example);
	}
    public List<GrowthDtlCustom> selectGrowthDtlCustomByExample(GrowthDtlExample example){
    	return growthDtlCustomMapper.selectByExample(example);
    }
    public GrowthDtlCustom selectGrowthDtlCustomByPrimaryKey(Integer id){
    	return growthDtlCustomMapper.selectByPrimaryKey(id);
    }

	
	
	
	public int sumGValue(QueryObject queryObject) {
		return growthDtlCustomMapper.sumValueByExample(builderQuery(queryObject));
	}
	public int selectTotalGValue() {
		return growthDtlCustomMapper.selectTotalGValue();
	}

	public List<GrowthDtl> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private GrowthDtlExample builderQuery(QueryObject queryObject) {
		GrowthDtlExample example = new GrowthDtlExample();
		GrowthDtlExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
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
