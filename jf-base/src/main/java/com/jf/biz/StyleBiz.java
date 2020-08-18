package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.StyleExtMapper;
import com.jf.dao.StyleMapper;
import com.jf.entity.Style;
import com.jf.entity.StyleExample;
import com.jf.entity.StyleExt;
import com.jf.entity.StyleExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StyleBiz extends BaseService<Style, StyleExample> {

	@Autowired
	private StyleMapper dao;
	@Autowired
	private StyleExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setStyleMapper(StyleMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public StyleExt save(StyleExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Style update(Style model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Style model = new Style();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public StyleExt findById(int id){
		return extDao.findById(id);
	}

	public StyleExt find(StyleExtExample example){
		return extDao.find(example.fill());
	}

	public int count(StyleExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, StyleExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, StyleExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, StyleExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(StyleExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<StyleExt> list(StyleExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<StyleExt> paginate(StyleExtExample example) {
		List<StyleExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
