package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BusinessCirclesAppealOrderExtMapper;
import com.jf.dao.BusinessCirclesAppealOrderMapper;
import com.jf.entity.BusinessCirclesAppealOrder;
import com.jf.entity.BusinessCirclesAppealOrderExample;
import com.jf.entity.BusinessCirclesAppealOrderExt;
import com.jf.entity.BusinessCirclesAppealOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BusinessCirclesAppealOrderBiz extends BaseService<BusinessCirclesAppealOrder, BusinessCirclesAppealOrderExample> {

	@Autowired
	private BusinessCirclesAppealOrderMapper dao;
	@Autowired
	private BusinessCirclesAppealOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBusinessCirclesAppealOrderMapper(BusinessCirclesAppealOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BusinessCirclesAppealOrderExt save(BusinessCirclesAppealOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BusinessCirclesAppealOrder update(BusinessCirclesAppealOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BusinessCirclesAppealOrder model = new BusinessCirclesAppealOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BusinessCirclesAppealOrderExt findById(int id){
		return extDao.findById(id);
	}

	public BusinessCirclesAppealOrderExt find(BusinessCirclesAppealOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BusinessCirclesAppealOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BusinessCirclesAppealOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BusinessCirclesAppealOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BusinessCirclesAppealOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BusinessCirclesAppealOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BusinessCirclesAppealOrderExt> list(BusinessCirclesAppealOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BusinessCirclesAppealOrderExt> paginate(BusinessCirclesAppealOrderExtExample example) {
		List<BusinessCirclesAppealOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
