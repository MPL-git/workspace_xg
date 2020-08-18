package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BusinessCirclesAppealOrderPicExtMapper;
import com.jf.dao.BusinessCirclesAppealOrderPicMapper;
import com.jf.entity.BusinessCirclesAppealOrderPic;
import com.jf.entity.BusinessCirclesAppealOrderPicExample;
import com.jf.entity.BusinessCirclesAppealOrderPicExt;
import com.jf.entity.BusinessCirclesAppealOrderPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BusinessCirclesAppealOrderPicBiz extends BaseService<BusinessCirclesAppealOrderPic, BusinessCirclesAppealOrderPicExample> {

	@Autowired
	private BusinessCirclesAppealOrderPicMapper dao;
	@Autowired
	private BusinessCirclesAppealOrderPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBusinessCirclesAppealOrderPicMapper(BusinessCirclesAppealOrderPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BusinessCirclesAppealOrderPicExt save(BusinessCirclesAppealOrderPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BusinessCirclesAppealOrderPic update(BusinessCirclesAppealOrderPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BusinessCirclesAppealOrderPic model = new BusinessCirclesAppealOrderPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BusinessCirclesAppealOrderPicExt findById(int id){
		return extDao.findById(id);
	}

	public BusinessCirclesAppealOrderPicExt find(BusinessCirclesAppealOrderPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BusinessCirclesAppealOrderPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BusinessCirclesAppealOrderPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BusinessCirclesAppealOrderPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BusinessCirclesAppealOrderPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BusinessCirclesAppealOrderPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BusinessCirclesAppealOrderPicExt> list(BusinessCirclesAppealOrderPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BusinessCirclesAppealOrderPicExt> paginate(BusinessCirclesAppealOrderPicExtExample example) {
		List<BusinessCirclesAppealOrderPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
