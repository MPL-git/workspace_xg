package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSettleCompareExtMapper;
import com.jf.dao.MchtSettleCompareMapper;
import com.jf.entity.MchtSettleCompare;
import com.jf.entity.MchtSettleCompareExample;
import com.jf.entity.MchtSettleCompareExt;
import com.jf.entity.MchtSettleCompareExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtSettleCompareBiz extends BaseService<MchtSettleCompare, MchtSettleCompareExample> {

	@Autowired
	private MchtSettleCompareMapper dao;
	@Autowired
	private MchtSettleCompareExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtSettleCompareMapper(MchtSettleCompareMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtSettleCompareExt save(MchtSettleCompareExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtSettleCompare update(MchtSettleCompare model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtSettleCompare model = new MchtSettleCompare();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtSettleCompareExt findById(int id){
		return extDao.findById(id);
	}

	public MchtSettleCompareExt find(MchtSettleCompareExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtSettleCompareExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtSettleCompareExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtSettleCompareExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtSettleCompareExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtSettleCompareExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtSettleCompareExt> list(MchtSettleCompareExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtSettleCompareExt> paginate(MchtSettleCompareExtExample example) {
		List<MchtSettleCompareExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
