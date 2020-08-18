package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopModuleDraftExtMapper;
import com.jf.dao.ShopModuleDraftMapper;
import com.jf.entity.ShopModuleDraft;
import com.jf.entity.ShopModuleDraftExample;
import com.jf.entity.ShopModuleDraftExt;
import com.jf.entity.ShopModuleDraftExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopModuleDraftBiz extends BaseService<ShopModuleDraft, ShopModuleDraftExample> {

	@Autowired
	private ShopModuleDraftMapper dao;
	@Autowired
	private ShopModuleDraftExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopModuleDraftMapper(ShopModuleDraftMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopModuleDraftExt save(ShopModuleDraftExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopModuleDraft update(ShopModuleDraft model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopModuleDraft model = new ShopModuleDraft();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopModuleDraftExt findById(int id){
		return extDao.findById(id);
	}

	public ShopModuleDraftExt find(ShopModuleDraftExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopModuleDraftExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopModuleDraftExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopModuleDraftExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopModuleDraftExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopModuleDraftExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopModuleDraftExt> list(ShopModuleDraftExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopModuleDraftExt> paginate(ShopModuleDraftExtExample example) {
		List<ShopModuleDraftExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
