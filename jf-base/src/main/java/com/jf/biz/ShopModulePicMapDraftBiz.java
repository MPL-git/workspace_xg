package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopModulePicMapDraftExtMapper;
import com.jf.dao.ShopModulePicMapDraftMapper;
import com.jf.entity.ShopModulePicMapDraft;
import com.jf.entity.ShopModulePicMapDraftExample;
import com.jf.entity.ShopModulePicMapDraftExt;
import com.jf.entity.ShopModulePicMapDraftExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopModulePicMapDraftBiz extends BaseService<ShopModulePicMapDraft, ShopModulePicMapDraftExample> {

	@Autowired
	private ShopModulePicMapDraftMapper dao;
	@Autowired
	private ShopModulePicMapDraftExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopModulePicMapDraftMapper(ShopModulePicMapDraftMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopModulePicMapDraftExt save(ShopModulePicMapDraftExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopModulePicMapDraft update(ShopModulePicMapDraft model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopModulePicMapDraft model = new ShopModulePicMapDraft();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopModulePicMapDraftExt findById(int id){
		return extDao.findById(id);
	}

	public ShopModulePicMapDraftExt find(ShopModulePicMapDraftExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopModulePicMapDraftExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopModulePicMapDraftExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopModulePicMapDraftExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopModulePicMapDraftExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopModulePicMapDraftExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopModulePicMapDraftExt> list(ShopModulePicMapDraftExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopModulePicMapDraftExt> paginate(ShopModulePicMapDraftExtExample example) {
		List<ShopModulePicMapDraftExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
