package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ModuleMapExtMapper;
import com.jf.dao.ModuleMapMapper;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;
import com.jf.entity.ModuleMapExt;
import com.jf.entity.ModuleMapExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ModuleMapBiz extends BaseService<ModuleMap, ModuleMapExample> {

	@Autowired
	private ModuleMapMapper dao;
	@Autowired
	private ModuleMapExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setModuleMapMapper(ModuleMapMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ModuleMapExt save(ModuleMapExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ModuleMap update(ModuleMap model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ModuleMap model = new ModuleMap();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ModuleMapExt findById(int id){
		return extDao.findById(id);
	}

	public ModuleMapExt find(ModuleMapExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ModuleMapExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ModuleMapExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ModuleMapExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ModuleMapExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ModuleMapExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ModuleMapExt> list(ModuleMapExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ModuleMapExt> paginate(ModuleMapExtExample example) {
		List<ModuleMapExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
