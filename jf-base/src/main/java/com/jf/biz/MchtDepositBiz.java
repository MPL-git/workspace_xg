package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtDepositExtMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositExample;
import com.jf.entity.MchtDepositExt;
import com.jf.entity.MchtDepositExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtDepositBiz extends BaseService<MchtDeposit, MchtDepositExample> {

	@Autowired
	private MchtDepositMapper dao;
	@Autowired
	private MchtDepositExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtDepositMapper(MchtDepositMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtDepositExt save(MchtDepositExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtDeposit update(MchtDeposit model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtDeposit model = new MchtDeposit();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtDepositExt findById(int id){
		return extDao.findById(id);
	}

	public MchtDepositExt find(MchtDepositExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtDepositExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtDepositExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtDepositExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtDepositExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtDepositExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtDepositExt> list(MchtDepositExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtDepositExt> paginate(MchtDepositExtExample example) {
		List<MchtDepositExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
