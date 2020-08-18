package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtContractPicMapper;
import com.jf.entity.MchtContract;
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
	private MchtContractService mchtContractService;

	@Autowired
	public void setMchtContractPicMapper(MchtContractPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}


	public MchtContractPic save(int mchtId, int contractId, String pic) {
		MchtContractPic model = findByKey(mchtId, contractId, pic);
		boolean isSave = model == null;
		if(isSave){
			model = new MchtContractPic();
			model.setMchtId(mchtId);
			model.setContractId(contractId);
			model.setPic(pic);
		}

		model.setDelFlag(Const.FLAG_FALSE);

		if(isSave){
			save(model);
		}else{
			update(model);
		}
		return model;
	}

	public List<MchtContractPic> listByContractId(int contractId) {
		MchtContract mchtContract = mchtContractService.findById(contractId);
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtContract.getMchtId());
		queryObject.addQuery("contractId", contractId);
		queryObject.addQuery("operatorType", "1");
		return list(queryObject);
	}

	public void deleteByContractId(int contractId) {
		List<MchtContractPic> list = listByContractId(contractId);
		for(MchtContractPic model : list){
			model.setDelFlag(Const.FLAG_TRUE);
			update(model);
		}
	}

	/**
	 * 获取商家合同
	 */
	public MchtContractPic findByKey(int mchtId, int contractId, String pic) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("contractId", contractId);
		queryObject.addQuery("operatorType", "1");
		queryObject.addQuery("pic", pic);
		queryObject.addQuery(QueryObject.INCLUDE_DELETE, true);
		queryObject.setLimitSize(1);
		List<MchtContractPic> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
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
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtContractPic> paginate(QueryObject queryObject) {
		MchtContractPicExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtContractPic> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
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
		if(queryObject.getQuery("operatorType") != null){
			criteria.andOperatorTypeEqualTo(queryObject.getQueryToStr("operatorType"));
		}
		if(queryObject.getQuery("pic") != null){
			criteria.andPicEqualTo(queryObject.getQueryToStr("pic"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
