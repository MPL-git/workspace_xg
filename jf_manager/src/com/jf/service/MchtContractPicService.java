package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtContractPicMapper;
import com.jf.entity.MchtContractPic;
import com.jf.entity.MchtContractPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtContractPicService extends BaseService<MchtContractPic, MchtContractPicExample> {

	@Autowired
	private MchtContractPicMapper dao;

	@Autowired
	public void setMchtContractPicMapper(MchtContractPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}


	public List<MchtContractPic> listByContractId(int contractId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("contractId", contractId);
		return list(queryObject);
	}

	public MchtContractPic findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtContractPic save(MchtContractPic model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtContractPic update(MchtContractPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtContractPic model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtContractPic> list(QueryObject queryObject) {
		MchtContractPicExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<MchtContractPic> paginate(QueryObject queryObject) {
		MchtContractPicExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtContractPic> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtContractPic>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtContractPicExample builderQuery(QueryObject queryObject) {
		MchtContractPicExample example = new MchtContractPicExample();
		MchtContractPicExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("contractId") != null){
			criteria.andContractIdEqualTo(queryObject.getQueryToInt("contractId"));
		}
		if(queryObject.getQuery("pic") != null){
			criteria.andPicEqualTo(queryObject.getQueryToStr("pic"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}


	public void saveMchtContractPicList(List<MchtContractPic> mchtContractPicList) {
		for(MchtContractPic mchtContractPic:mchtContractPicList){
			this.insertSelective(mchtContractPic);
		}
	}
}
