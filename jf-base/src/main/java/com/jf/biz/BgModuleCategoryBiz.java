package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BgModuleCategoryExtMapper;
import com.jf.dao.BgModuleCategoryMapper;
import com.jf.entity.BgModuleCategory;
import com.jf.entity.BgModuleCategoryExample;
import com.jf.entity.BgModuleCategoryExt;
import com.jf.entity.BgModuleCategoryExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BgModuleCategoryBiz extends BaseService<BgModuleCategory, BgModuleCategoryExample> {

	@Autowired
	private BgModuleCategoryMapper dao;
	@Autowired
	private BgModuleCategoryExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBgModuleCategoryMapper(BgModuleCategoryMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BgModuleCategoryExt save(BgModuleCategoryExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BgModuleCategory update(BgModuleCategory model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BgModuleCategory model = new BgModuleCategory();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BgModuleCategoryExt findById(int id){
		return extDao.findById(id);
	}

	public BgModuleCategoryExt find(BgModuleCategoryExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BgModuleCategoryExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BgModuleCategoryExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BgModuleCategoryExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BgModuleCategoryExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BgModuleCategoryExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BgModuleCategoryExt> list(BgModuleCategoryExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BgModuleCategoryExt> paginate(BgModuleCategoryExtExample example) {
		List<BgModuleCategoryExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
