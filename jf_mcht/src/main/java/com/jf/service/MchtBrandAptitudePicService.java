package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandAptitudePicMapper;
import com.jf.entity.MchtBrandAptitudePic;
import com.jf.entity.MchtBrandAptitudePicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtBrandAptitudePicService extends BaseService<MchtBrandAptitudePic, MchtBrandAptitudePicExample> {
	@Autowired
	private MchtBrandAptitudePicMapper mchtBrandAptitudePicMapper;
	
	@Autowired
	public void setMchtBrandAptitudePicMapper(MchtBrandAptitudePicMapper mchtBrandAptitudePicMapper) {
		super.setDao(mchtBrandAptitudePicMapper);
		this.mchtBrandAptitudePicMapper = mchtBrandAptitudePicMapper;
	}


	public List<MchtBrandAptitudePic> listByMchtProductBrandId(int mchtProductBrandId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtProductBrandId", mchtProductBrandId);
		return list(queryObject);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtBrandAptitudePic> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtBrandAptitudePic> paginate(QueryObject queryObject) {
		MchtBrandAptitudePicExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtBrandAptitudePic> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtBrandAptitudePicExample builderQuery(QueryObject queryObject) {
		MchtBrandAptitudePicExample example = new MchtBrandAptitudePicExample();
		MchtBrandAptitudePicExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("mchtProductBrandId") != null){
			criteria.andMchtProductBrandIdEqualTo(queryObject.getQueryToInt("mchtProductBrandId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
