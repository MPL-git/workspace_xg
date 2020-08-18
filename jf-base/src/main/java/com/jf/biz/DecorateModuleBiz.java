package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DecorateModuleExtMapper;
import com.jf.dao.DecorateModuleMapper;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.DecorateModuleExt;
import com.jf.entity.DecorateModuleExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DecorateModuleBiz extends BaseService<DecorateModule, DecorateModuleExample> {

	@Autowired
	private DecorateModuleMapper dao;
	@Autowired
	private DecorateModuleExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDecorateModuleMapper(DecorateModuleMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DecorateModuleExt save(DecorateModuleExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DecorateModule update(DecorateModule model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DecorateModule model = new DecorateModule();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DecorateModuleExt findById(int id){
		return extDao.findById(id);
	}

	public DecorateModuleExt find(DecorateModuleExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DecorateModuleExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DecorateModuleExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DecorateModuleExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DecorateModuleExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DecorateModuleExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DecorateModuleExt> list(DecorateModuleExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DecorateModuleExt> paginate(DecorateModuleExtExample example) {
		List<DecorateModuleExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
