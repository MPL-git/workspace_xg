package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandInspectionPicMapper;
import com.jf.entity.MchtBrandInspectionPic;
import com.jf.entity.MchtBrandInspectionPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtBrandInspectionPicService extends BaseService<MchtBrandInspectionPic, MchtBrandInspectionPicExample> {

	@Autowired
	private MchtBrandInspectionPicMapper dao;

	@Autowired
	public void setMchtBrandInspectionPicMapper(MchtBrandInspectionPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}


	public List<MchtBrandInspectionPic> listByMchtProductBrandId(int mchtProductBrandId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtProductBrandId", mchtProductBrandId);
		return list(queryObject);
	}

	public MchtBrandInspectionPic findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtBrandInspectionPic save(MchtBrandInspectionPic model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtBrandInspectionPic update(MchtBrandInspectionPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtBrandInspectionPic model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtBrandInspectionPic> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtBrandInspectionPic> paginate(QueryObject queryObject) {
		MchtBrandInspectionPicExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtBrandInspectionPic> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtBrandInspectionPicExample builderQuery(QueryObject queryObject) {
		MchtBrandInspectionPicExample example = new MchtBrandInspectionPicExample();
		MchtBrandInspectionPicExample.Criteria criteria = example.createCriteria();
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
