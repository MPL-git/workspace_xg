package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MallCategoryExtMapper;
import com.jf.dao.MallCategoryMapper;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryExample;
import com.jf.entity.MallCategoryExt;
import com.jf.entity.MallCategoryExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MallCategoryBiz extends BaseService<MallCategory, MallCategoryExample> {

	@Autowired
	private MallCategoryMapper dao;
	@Autowired
	private MallCategoryExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMallCategoryMapper(MallCategoryMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MallCategoryExt save(MallCategoryExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MallCategory update(MallCategory model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MallCategory model = new MallCategory();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MallCategoryExt findById(int id){
		return extDao.findById(id);
	}

	public MallCategoryExt find(MallCategoryExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MallCategoryExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MallCategoryExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MallCategoryExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MallCategoryExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MallCategoryExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MallCategoryExt> list(MallCategoryExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MallCategoryExt> paginate(MallCategoryExtExample example) {
		List<MallCategoryExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
