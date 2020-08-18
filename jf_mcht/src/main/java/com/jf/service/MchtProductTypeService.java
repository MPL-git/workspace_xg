package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtProductTypeCustomMapper;
import com.jf.dao.MchtProductTypeMapper;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeCustom;
import com.jf.entity.MchtProductTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtProductTypeService extends BaseService<MchtProductType, MchtProductTypeExample> {

	@Autowired
	private MchtProductTypeMapper mchtProductTypeMapper;
	
	@Autowired
	private MchtProductTypeCustomMapper mchtProductTypeCustomMapper;
	
	
	@Autowired
	public void setMchtProductTypeMapper(MchtProductTypeMapper mchtProductTypeMapper) {
		super.setDao(mchtProductTypeMapper);
		this.mchtProductTypeMapper = mchtProductTypeMapper;
	}
	

	public MchtProductType findById(int id){
		return mchtProductTypeMapper.selectByPrimaryKey(id);
	}

	public List<MchtProductType> findList(QueryObject queryObject) {

		return mchtProductTypeMapper.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtProductType> paginate(QueryObject queryObject) {
		MchtProductTypeExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtProductType> list = mchtProductTypeMapper.selectByExample(example);
		int totalCount = mchtProductTypeMapper.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtProductTypeExample builderQuery(QueryObject queryObject) {
		MchtProductTypeExample example = new MchtProductTypeExample();
		MchtProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("statusIn") != null){
			List<String> statusIn = queryObject.getQuery("statusIn");
			criteria.andStatusIn(statusIn);
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
	
	
	public List<MchtProductTypeCustom> selectMchtProductTypeCustomByExample(MchtProductTypeExample example){
		return mchtProductTypeCustomMapper.selectByExample(example);
	}
	
}
