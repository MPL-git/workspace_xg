package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SubDepositOrderExtMapper;
import com.jf.dao.SubDepositOrderMapper;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;
import com.jf.entity.SubDepositOrderExt;
import com.jf.entity.SubDepositOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubDepositOrderBiz extends BaseService<SubDepositOrder, SubDepositOrderExample> {

	@Autowired
	private SubDepositOrderMapper dao;
	@Autowired
	private SubDepositOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSubDepositOrderMapper(SubDepositOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SubDepositOrderExt save(SubDepositOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SubDepositOrder update(SubDepositOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SubDepositOrder model = new SubDepositOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SubDepositOrderExt findById(int id){
		return extDao.findById(id);
	}

	public SubDepositOrderExt find(SubDepositOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SubDepositOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SubDepositOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SubDepositOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SubDepositOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SubDepositOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SubDepositOrderExt> list(SubDepositOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SubDepositOrderExt> paginate(SubDepositOrderExtExample example) {
		List<SubDepositOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
