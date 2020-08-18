package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.XiaonengCustomerServiceExtMapper;
import com.jf.dao.XiaonengCustomerServiceMapper;
import com.jf.entity.XiaonengCustomerService;
import com.jf.entity.XiaonengCustomerServiceExample;
import com.jf.entity.XiaonengCustomerServiceExt;
import com.jf.entity.XiaonengCustomerServiceExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class XiaonengCustomerServiceBiz extends BaseService<XiaonengCustomerService, XiaonengCustomerServiceExample> {

	@Autowired
	private XiaonengCustomerServiceMapper dao;
	@Autowired
	private XiaonengCustomerServiceExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setXiaonengCustomerServiceMapper(XiaonengCustomerServiceMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public XiaonengCustomerServiceExt save(XiaonengCustomerServiceExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public XiaonengCustomerService update(XiaonengCustomerService model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		XiaonengCustomerService model = new XiaonengCustomerService();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public XiaonengCustomerServiceExt findById(int id){
		return extDao.findById(id);
	}

	public XiaonengCustomerServiceExt find(XiaonengCustomerServiceExtExample example){
		return extDao.find(example.fill());
	}

	public int count(XiaonengCustomerServiceExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, XiaonengCustomerServiceExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, XiaonengCustomerServiceExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, XiaonengCustomerServiceExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(XiaonengCustomerServiceExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<XiaonengCustomerServiceExt> list(XiaonengCustomerServiceExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<XiaonengCustomerServiceExt> paginate(XiaonengCustomerServiceExtExample example) {
		List<XiaonengCustomerServiceExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
