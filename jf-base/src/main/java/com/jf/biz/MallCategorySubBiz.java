package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MallCategorySubExtMapper;
import com.jf.dao.MallCategorySubMapper;
import com.jf.entity.MallCategorySub;
import com.jf.entity.MallCategorySubExample;
import com.jf.entity.MallCategorySubExt;
import com.jf.entity.MallCategorySubExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MallCategorySubBiz extends BaseService<MallCategorySub, MallCategorySubExample> {

	@Autowired
	private MallCategorySubMapper dao;
	@Autowired
	private MallCategorySubExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMallCategorySubMapper(MallCategorySubMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MallCategorySubExt save(MallCategorySubExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MallCategorySub update(MallCategorySub model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MallCategorySub model = new MallCategorySub();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MallCategorySubExt findById(int id){
		return extDao.findById(id);
	}

	public MallCategorySubExt find(MallCategorySubExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MallCategorySubExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MallCategorySubExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MallCategorySubExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MallCategorySubExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MallCategorySubExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MallCategorySubExt> list(MallCategorySubExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MallCategorySubExt> paginate(MallCategorySubExtExample example) {
		List<MallCategorySubExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
