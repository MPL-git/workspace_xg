package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomPageExtMapper;
import com.jf.dao.CustomPageMapper;
import com.jf.entity.CustomPage;
import com.jf.entity.CustomPageExample;
import com.jf.entity.CustomPageExt;
import com.jf.entity.CustomPageExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomPageBiz extends BaseService<CustomPage, CustomPageExample> {

	@Autowired
	private CustomPageMapper dao;
	@Autowired
	private CustomPageExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCustomPageMapper(CustomPageMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CustomPageExt save(CustomPageExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CustomPage update(CustomPage model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CustomPage model = new CustomPage();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CustomPageExt findById(int id){
		return extDao.findById(id);
	}

	public CustomPageExt find(CustomPageExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CustomPageExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CustomPageExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CustomPageExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CustomPageExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CustomPageExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CustomPageExt> list(CustomPageExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CustomPageExt> paginate(CustomPageExtExample example) {
		List<CustomPageExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
