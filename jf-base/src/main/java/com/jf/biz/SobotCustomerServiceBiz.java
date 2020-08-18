package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SobotCustomerServiceExtMapper;
import com.jf.dao.SobotCustomerServiceMapper;
import com.jf.entity.SobotCustomerService;
import com.jf.entity.SobotCustomerServiceExample;
import com.jf.entity.SobotCustomerServiceExt;
import com.jf.entity.SobotCustomerServiceExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SobotCustomerServiceBiz extends BaseService<SobotCustomerService, SobotCustomerServiceExample> {

	@Autowired
	private SobotCustomerServiceMapper dao;
	@Autowired
	private SobotCustomerServiceExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSobotCustomerServiceMapper(SobotCustomerServiceMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SobotCustomerServiceExt save(SobotCustomerServiceExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SobotCustomerService update(SobotCustomerService model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SobotCustomerService model = new SobotCustomerService();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SobotCustomerServiceExt findById(int id){
		return extDao.findById(id);
	}

	public SobotCustomerServiceExt find(SobotCustomerServiceExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SobotCustomerServiceExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SobotCustomerServiceExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SobotCustomerServiceExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SobotCustomerServiceExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SobotCustomerServiceExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SobotCustomerServiceExt> list(SobotCustomerServiceExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SobotCustomerServiceExt> paginate(SobotCustomerServiceExtExample example) {
		List<SobotCustomerServiceExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
