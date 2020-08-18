package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MallCategoryModuleExtMapper;
import com.jf.dao.MallCategoryModuleMapper;
import com.jf.entity.MallCategoryModule;
import com.jf.entity.MallCategoryModuleExample;
import com.jf.entity.MallCategoryModuleExt;
import com.jf.entity.MallCategoryModuleExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MallCategoryModuleBiz extends BaseService<MallCategoryModule, MallCategoryModuleExample> {

	@Autowired
	private MallCategoryModuleMapper dao;
	@Autowired
	private MallCategoryModuleExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMallCategoryModuleMapper(MallCategoryModuleMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MallCategoryModuleExt save(MallCategoryModuleExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MallCategoryModule update(MallCategoryModule model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MallCategoryModule model = new MallCategoryModule();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MallCategoryModuleExt findById(int id){
		return extDao.findById(id);
	}

	public MallCategoryModuleExt find(MallCategoryModuleExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MallCategoryModuleExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MallCategoryModuleExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MallCategoryModuleExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MallCategoryModuleExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MallCategoryModuleExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MallCategoryModuleExt> list(MallCategoryModuleExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MallCategoryModuleExt> paginate(MallCategoryModuleExtExample example) {
		List<MallCategoryModuleExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
