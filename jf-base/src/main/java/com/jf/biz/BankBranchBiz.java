package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BankBranchExtMapper;
import com.jf.dao.BankBranchMapper;
import com.jf.entity.BankBranch;
import com.jf.entity.BankBranchExample;
import com.jf.entity.BankBranchExt;
import com.jf.entity.BankBranchExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankBranchBiz extends BaseService<BankBranch, BankBranchExample> {

	@Autowired
	private BankBranchMapper dao;
	@Autowired
	private BankBranchExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBankBranchMapper(BankBranchMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BankBranchExt save(BankBranchExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BankBranch update(BankBranch model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BankBranch model = new BankBranch();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BankBranchExt findById(int id){
		return extDao.findById(id);
	}

	public BankBranchExt find(BankBranchExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BankBranchExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BankBranchExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BankBranchExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BankBranchExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BankBranchExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BankBranchExt> list(BankBranchExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BankBranchExt> paginate(BankBranchExtExample example) {
		List<BankBranchExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
