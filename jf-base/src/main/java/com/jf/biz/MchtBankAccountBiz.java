package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBankAccountExtMapper;
import com.jf.dao.MchtBankAccountMapper;
import com.jf.entity.MchtBankAccount;
import com.jf.entity.MchtBankAccountExample;
import com.jf.entity.MchtBankAccountExt;
import com.jf.entity.MchtBankAccountExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBankAccountBiz extends BaseService<MchtBankAccount, MchtBankAccountExample> {

	@Autowired
	private MchtBankAccountMapper dao;
	@Autowired
	private MchtBankAccountExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBankAccountMapper(MchtBankAccountMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBankAccountExt save(MchtBankAccountExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBankAccount update(MchtBankAccount model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBankAccount model = new MchtBankAccount();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBankAccountExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBankAccountExt find(MchtBankAccountExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBankAccountExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBankAccountExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBankAccountExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBankAccountExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBankAccountExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBankAccountExt> list(MchtBankAccountExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBankAccountExt> paginate(MchtBankAccountExtExample example) {
		List<MchtBankAccountExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
