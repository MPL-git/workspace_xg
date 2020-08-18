package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MallCategoryTopExtMapper;
import com.jf.dao.MallCategoryTopMapper;
import com.jf.entity.MallCategoryTop;
import com.jf.entity.MallCategoryTopExample;
import com.jf.entity.MallCategoryTopExt;
import com.jf.entity.MallCategoryTopExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MallCategoryTopBiz extends BaseService<MallCategoryTop, MallCategoryTopExample> {

	@Autowired
	private MallCategoryTopMapper dao;
	@Autowired
	private MallCategoryTopExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMallCategoryTopMapper(MallCategoryTopMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MallCategoryTopExt save(MallCategoryTopExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MallCategoryTop update(MallCategoryTop model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MallCategoryTop model = new MallCategoryTop();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MallCategoryTopExt findById(int id){
		return extDao.findById(id);
	}

	public MallCategoryTopExt find(MallCategoryTopExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MallCategoryTopExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MallCategoryTopExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MallCategoryTopExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MallCategoryTopExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MallCategoryTopExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MallCategoryTopExt> list(MallCategoryTopExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MallCategoryTopExt> paginate(MallCategoryTopExtExample example) {
		List<MallCategoryTopExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
