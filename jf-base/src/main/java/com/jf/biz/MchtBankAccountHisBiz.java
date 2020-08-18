package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBankAccountHisExtMapper;
import com.jf.dao.MchtBankAccountHisMapper;
import com.jf.entity.MchtBankAccountHis;
import com.jf.entity.MchtBankAccountHisExample;
import com.jf.entity.MchtBankAccountHisExt;
import com.jf.entity.MchtBankAccountHisExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBankAccountHisBiz extends BaseService<MchtBankAccountHis, MchtBankAccountHisExample> {

	@Autowired
	private MchtBankAccountHisMapper dao;
	@Autowired
	private MchtBankAccountHisExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBankAccountHisMapper(MchtBankAccountHisMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBankAccountHisExt save(MchtBankAccountHisExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBankAccountHis update(MchtBankAccountHis model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBankAccountHis model = new MchtBankAccountHis();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBankAccountHisExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBankAccountHisExt find(MchtBankAccountHisExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBankAccountHisExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBankAccountHisExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBankAccountHisExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBankAccountHisExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBankAccountHisExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBankAccountHisExt> list(MchtBankAccountHisExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBankAccountHisExt> paginate(MchtBankAccountHisExtExample example) {
		List<MchtBankAccountHisExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
