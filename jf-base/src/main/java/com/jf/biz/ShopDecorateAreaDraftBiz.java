package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopDecorateAreaDraftExtMapper;
import com.jf.dao.ShopDecorateAreaDraftMapper;
import com.jf.entity.ShopDecorateAreaDraft;
import com.jf.entity.ShopDecorateAreaDraftExample;
import com.jf.entity.ShopDecorateAreaDraftExt;
import com.jf.entity.ShopDecorateAreaDraftExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopDecorateAreaDraftBiz extends BaseService<ShopDecorateAreaDraft, ShopDecorateAreaDraftExample> {

	@Autowired
	private ShopDecorateAreaDraftMapper dao;
	@Autowired
	private ShopDecorateAreaDraftExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopDecorateAreaDraftMapper(ShopDecorateAreaDraftMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopDecorateAreaDraftExt save(ShopDecorateAreaDraftExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopDecorateAreaDraft update(ShopDecorateAreaDraft model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopDecorateAreaDraft model = new ShopDecorateAreaDraft();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopDecorateAreaDraftExt findById(int id){
		return extDao.findById(id);
	}

	public ShopDecorateAreaDraftExt find(ShopDecorateAreaDraftExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopDecorateAreaDraftExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopDecorateAreaDraftExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopDecorateAreaDraftExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopDecorateAreaDraftExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopDecorateAreaDraftExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopDecorateAreaDraftExt> list(ShopDecorateAreaDraftExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopDecorateAreaDraftExt> paginate(ShopDecorateAreaDraftExtExample example) {
		List<ShopDecorateAreaDraftExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
