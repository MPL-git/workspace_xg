package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomAdPageExtMapper;
import com.jf.dao.CustomAdPageMapper;
import com.jf.entity.CustomAdPage;
import com.jf.entity.CustomAdPageExample;
import com.jf.entity.CustomAdPageExt;
import com.jf.entity.CustomAdPageExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomAdPageBiz extends BaseService<CustomAdPage, CustomAdPageExample> {

	@Autowired
	private CustomAdPageMapper dao;
	@Autowired
	private CustomAdPageExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------

	public CustomAdPageExt findByPageType(String pageType, Integer sourceProductTypeId){
		CustomAdPageExtExample example = new CustomAdPageExtExample();
		CustomAdPageExtExample.CustomAdPageExtCriteria criteria = example.createCriteria();
		criteria.andPageTypeEqualTo(pageType);
		criteria.andIsEffectEqualTo("1");
		criteria.andDelFlagEqualTo("0");
		Date now = new Date();
		criteria.andAutoUpDateLessThanOrEqualTo(now).andAutoDownDateGreaterThanOrEqualTo(now);
		if(sourceProductTypeId != null){
			criteria.andSourceProductTypeIdEqualTo(sourceProductTypeId);
		}
		return find(example);
	}



	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCustomAdPageMapper(CustomAdPageMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CustomAdPageExt save(CustomAdPageExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CustomAdPage update(CustomAdPage model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CustomAdPage model = new CustomAdPage();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CustomAdPageExt findById(int id){
		return extDao.findById(id);
	}

	public CustomAdPageExt find(CustomAdPageExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CustomAdPageExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CustomAdPageExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CustomAdPageExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CustomAdPageExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CustomAdPageExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CustomAdPageExt> list(CustomAdPageExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CustomAdPageExt> paginate(CustomAdPageExtExample example) {
		List<CustomAdPageExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
