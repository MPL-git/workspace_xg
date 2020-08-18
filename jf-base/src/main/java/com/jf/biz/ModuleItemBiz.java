package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ModuleItemExtMapper;
import com.jf.dao.ModuleItemMapper;
import com.jf.entity.ModuleItem;
import com.jf.entity.ModuleItemExample;
import com.jf.entity.ModuleItemExt;
import com.jf.entity.ModuleItemExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ModuleItemBiz extends BaseService<ModuleItem, ModuleItemExample> {

	@Autowired
	private ModuleItemMapper dao;
	@Autowired
	private ModuleItemExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setModuleItemMapper(ModuleItemMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ModuleItemExt save(ModuleItemExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ModuleItem update(ModuleItem model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ModuleItem model = new ModuleItem();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ModuleItemExt findById(int id){
		return extDao.findById(id);
	}

	public ModuleItemExt find(ModuleItemExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ModuleItemExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ModuleItemExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ModuleItemExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ModuleItemExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ModuleItemExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ModuleItemExt> list(ModuleItemExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ModuleItemExt> paginate(ModuleItemExtExample example) {
		List<ModuleItemExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
