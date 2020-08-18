package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ServiceLogPicExtMapper;
import com.jf.dao.ServiceLogPicMapper;
import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicExample;
import com.jf.entity.ServiceLogPicExt;
import com.jf.entity.ServiceLogPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceLogPicBiz extends BaseService<ServiceLogPic, ServiceLogPicExample> {

	@Autowired
	private ServiceLogPicMapper dao;
	@Autowired
	private ServiceLogPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setServiceLogPicMapper(ServiceLogPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ServiceLogPicExt save(ServiceLogPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ServiceLogPic update(ServiceLogPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ServiceLogPic model = new ServiceLogPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ServiceLogPicExt findById(int id){
		return extDao.findById(id);
	}

	public ServiceLogPicExt find(ServiceLogPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ServiceLogPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ServiceLogPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ServiceLogPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ServiceLogPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ServiceLogPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ServiceLogPicExt> list(ServiceLogPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ServiceLogPicExt> paginate(ServiceLogPicExtExample example) {
		List<ServiceLogPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
