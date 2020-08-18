package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WithdrawOrderPicExtMapper;
import com.jf.dao.WithdrawOrderPicMapper;
import com.jf.entity.WithdrawOrderPic;
import com.jf.entity.WithdrawOrderPicExample;
import com.jf.entity.WithdrawOrderPicExt;
import com.jf.entity.WithdrawOrderPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WithdrawOrderPicBiz extends BaseService<WithdrawOrderPic, WithdrawOrderPicExample> {

	@Autowired
	private WithdrawOrderPicMapper dao;
	@Autowired
	private WithdrawOrderPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWithdrawOrderPicMapper(WithdrawOrderPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WithdrawOrderPicExt save(WithdrawOrderPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WithdrawOrderPic update(WithdrawOrderPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WithdrawOrderPic model = new WithdrawOrderPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WithdrawOrderPicExt findById(int id){
		return extDao.findById(id);
	}

	public WithdrawOrderPicExt find(WithdrawOrderPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WithdrawOrderPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WithdrawOrderPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WithdrawOrderPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WithdrawOrderPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WithdrawOrderPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WithdrawOrderPicExt> list(WithdrawOrderPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WithdrawOrderPicExt> paginate(WithdrawOrderPicExtExample example) {
		List<WithdrawOrderPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
