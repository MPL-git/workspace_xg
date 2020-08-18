package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandOtherPicMapper;
import com.jf.entity.MchtBrandOtherPic;
import com.jf.entity.MchtBrandOtherPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtBrandOtherPicService extends BaseService<MchtBrandOtherPic, MchtBrandOtherPicExample> {

	@Autowired
	private MchtBrandOtherPicMapper dao;

	@Autowired
	public void setMchtBrandOtherPicMapper(MchtBrandOtherPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}




	public MchtBrandOtherPic findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtBrandOtherPic save(MchtBrandOtherPic model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtBrandOtherPic update(MchtBrandOtherPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtBrandOtherPic model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtBrandOtherPic> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtBrandOtherPic> paginate(QueryObject queryObject) {
		MchtBrandOtherPicExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtBrandOtherPic> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtBrandOtherPic>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtBrandOtherPicExample builderQuery(QueryObject queryObject) {
		MchtBrandOtherPicExample example = new MchtBrandOtherPicExample();
		MchtBrandOtherPicExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
