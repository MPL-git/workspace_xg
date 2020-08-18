package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtPlatformAuthPicMapper;
import com.jf.entity.MchtPlatformAuthPic;
import com.jf.entity.MchtPlatformAuthPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtPlatformAuthPicService extends BaseService<MchtPlatformAuthPic, MchtPlatformAuthPicExample> {
	@Autowired
	private MchtPlatformAuthPicMapper mchtPlatformAuthPicMapper;
	
	@Autowired
	public void setMchtPlatformAuthPicMapper(MchtPlatformAuthPicMapper mchtPlatformAuthPicMapper) {
		super.setDao(mchtPlatformAuthPicMapper);
		this.mchtPlatformAuthPicMapper = mchtPlatformAuthPicMapper;
	}

	public List<MchtPlatformAuthPic> listByMchtProductBrandId(int mchtProductBrandId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtProductBrandId", mchtProductBrandId);
		return list(queryObject);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtPlatformAuthPic> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtPlatformAuthPic> paginate(QueryObject queryObject) {
		MchtPlatformAuthPicExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtPlatformAuthPic> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtPlatformAuthPicExample builderQuery(QueryObject queryObject) {
		MchtPlatformAuthPicExample example = new MchtPlatformAuthPicExample();
		MchtPlatformAuthPicExample.Criteria criteria = example.createCriteria();
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
