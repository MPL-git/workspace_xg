package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BankExtMapper;
import com.jf.dao.BankMapper;
import com.jf.entity.Bank;
import com.jf.entity.BankExample;
import com.jf.entity.BankExt;
import com.jf.entity.BankExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankBiz extends BaseService<Bank, BankExample> {

	@Autowired
	private BankMapper dao;
	@Autowired
	private BankExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBankMapper(BankMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BankExt findById(int id){
		return extDao.findById(id);
	}

	public BankExt find(BankExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BankExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BankExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BankExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BankExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BankExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BankExt> list(BankExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BankExt> paginate(BankExtExample example) {
		List<BankExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
