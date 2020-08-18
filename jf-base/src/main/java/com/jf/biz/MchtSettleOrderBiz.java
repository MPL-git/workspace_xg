package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSettleOrderExtMapper;
import com.jf.dao.MchtSettleOrderMapper;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtSettleOrderExample;
import com.jf.entity.MchtSettleOrderExt;
import com.jf.entity.MchtSettleOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtSettleOrderBiz extends BaseService<MchtSettleOrder, MchtSettleOrderExample> {

	@Autowired
	private MchtSettleOrderMapper dao;
	@Autowired
	private MchtSettleOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtSettleOrderMapper(MchtSettleOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtSettleOrderExt save(MchtSettleOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtSettleOrder update(MchtSettleOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtSettleOrder model = new MchtSettleOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtSettleOrderExt findById(int id){
		return extDao.findById(id);
	}

	public MchtSettleOrderExt find(MchtSettleOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtSettleOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtSettleOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtSettleOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtSettleOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtSettleOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtSettleOrderExt> list(MchtSettleOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtSettleOrderExt> paginate(MchtSettleOrderExtExample example) {
		List<MchtSettleOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
