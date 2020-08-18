package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopModuleExtMapper;
import com.jf.dao.ShopModuleMapper;
import com.jf.entity.ShopModule;
import com.jf.entity.ShopModuleExample;
import com.jf.entity.ShopModuleExt;
import com.jf.entity.ShopModuleExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopModuleBiz extends BaseService<ShopModule, ShopModuleExample> {

	@Autowired
	private ShopModuleMapper dao;
	@Autowired
	private ShopModuleExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopModuleMapper(ShopModuleMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopModuleExt save(ShopModuleExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopModule update(ShopModule model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopModule model = new ShopModule();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopModuleExt findById(int id){
		return extDao.findById(id);
	}

	public ShopModuleExt find(ShopModuleExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopModuleExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopModuleExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopModuleExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopModuleExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopModuleExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopModuleExt> list(ShopModuleExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopModuleExt> paginate(ShopModuleExtExample example) {
		List<ShopModuleExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
