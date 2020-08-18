package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ModuleNavigationExtMapper;
import com.jf.dao.ModuleNavigationMapper;
import com.jf.entity.ModuleNavigation;
import com.jf.entity.ModuleNavigationExample;
import com.jf.entity.ModuleNavigationExt;
import com.jf.entity.ModuleNavigationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ModuleNavigationBiz extends BaseService<ModuleNavigation, ModuleNavigationExample> {

	@Autowired
	private ModuleNavigationMapper dao;
	@Autowired
	private ModuleNavigationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setModuleNavigationMapper(ModuleNavigationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ModuleNavigationExt save(ModuleNavigationExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ModuleNavigation update(ModuleNavigation model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ModuleNavigation model = new ModuleNavigation();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ModuleNavigationExt findById(int id){
		return extDao.findById(id);
	}

	public ModuleNavigationExt find(ModuleNavigationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ModuleNavigationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ModuleNavigationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ModuleNavigationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ModuleNavigationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ModuleNavigationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ModuleNavigationExt> list(ModuleNavigationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ModuleNavigationExt> paginate(ModuleNavigationExtExample example) {
		List<ModuleNavigationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
