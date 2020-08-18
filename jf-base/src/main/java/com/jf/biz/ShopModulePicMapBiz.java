package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopModulePicMapExtMapper;
import com.jf.dao.ShopModulePicMapMapper;
import com.jf.entity.ShopModulePicMap;
import com.jf.entity.ShopModulePicMapExample;
import com.jf.entity.ShopModulePicMapExt;
import com.jf.entity.ShopModulePicMapExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopModulePicMapBiz extends BaseService<ShopModulePicMap, ShopModulePicMapExample> {

	@Autowired
	private ShopModulePicMapMapper dao;
	@Autowired
	private ShopModulePicMapExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopModulePicMapMapper(ShopModulePicMapMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopModulePicMapExt save(ShopModulePicMapExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopModulePicMap update(ShopModulePicMap model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopModulePicMap model = new ShopModulePicMap();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopModulePicMapExt findById(int id){
		return extDao.findById(id);
	}

	public ShopModulePicMapExt find(ShopModulePicMapExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopModulePicMapExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopModulePicMapExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopModulePicMapExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopModulePicMapExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopModulePicMapExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopModulePicMapExt> list(ShopModulePicMapExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopModulePicMapExt> paginate(ShopModulePicMapExtExample example) {
		List<ShopModulePicMapExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
