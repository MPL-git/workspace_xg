package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PayToMchtLogExtMapper;
import com.jf.dao.PayToMchtLogMapper;
import com.jf.entity.PayToMchtLog;
import com.jf.entity.PayToMchtLogExample;
import com.jf.entity.PayToMchtLogExt;
import com.jf.entity.PayToMchtLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PayToMchtLogBiz extends BaseService<PayToMchtLog, PayToMchtLogExample> {

	@Autowired
	private PayToMchtLogMapper dao;
	@Autowired
	private PayToMchtLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPayToMchtLogMapper(PayToMchtLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PayToMchtLogExt save(PayToMchtLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PayToMchtLog update(PayToMchtLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PayToMchtLog model = new PayToMchtLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PayToMchtLogExt findById(int id){
		return extDao.findById(id);
	}

	public PayToMchtLogExt find(PayToMchtLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PayToMchtLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PayToMchtLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PayToMchtLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PayToMchtLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PayToMchtLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PayToMchtLogExt> list(PayToMchtLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PayToMchtLogExt> paginate(PayToMchtLogExtExample example) {
		List<PayToMchtLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
