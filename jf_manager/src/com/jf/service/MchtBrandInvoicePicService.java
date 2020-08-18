package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandInvoicePicMapper;
import com.jf.entity.MchtBrandInvoicePic;
import com.jf.entity.MchtBrandInvoicePicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtBrandInvoicePicService extends BaseService<MchtBrandInvoicePic, MchtBrandInvoicePicExample> {

	@Autowired
	private MchtBrandInvoicePicMapper dao;

	@Autowired
	public void setMchtBrandInvoicePicMapper(MchtBrandInvoicePicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}




	public MchtBrandInvoicePic findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtBrandInvoicePic save(MchtBrandInvoicePic model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtBrandInvoicePic update(MchtBrandInvoicePic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtBrandInvoicePic model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtBrandInvoicePic> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtBrandInvoicePic> paginate(QueryObject queryObject) {
		MchtBrandInvoicePicExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtBrandInvoicePic> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtBrandInvoicePic>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtBrandInvoicePicExample builderQuery(QueryObject queryObject) {
		MchtBrandInvoicePicExample example = new MchtBrandInvoicePicExample();
		MchtBrandInvoicePicExample.Criteria criteria = example.createCriteria();
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
