package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopDecoratePageDraftExtMapper;
import com.jf.dao.ShopDecoratePageDraftMapper;
import com.jf.entity.ShopDecoratePageDraft;
import com.jf.entity.ShopDecoratePageDraftExample;
import com.jf.entity.ShopDecoratePageDraftExt;
import com.jf.entity.ShopDecoratePageDraftExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopDecoratePageDraftBiz extends BaseService<ShopDecoratePageDraft, ShopDecoratePageDraftExample> {

	@Autowired
	private ShopDecoratePageDraftMapper dao;
	@Autowired
	private ShopDecoratePageDraftExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopDecoratePageDraftMapper(ShopDecoratePageDraftMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopDecoratePageDraftExt save(ShopDecoratePageDraftExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopDecoratePageDraft update(ShopDecoratePageDraft model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopDecoratePageDraft model = new ShopDecoratePageDraft();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopDecoratePageDraftExt findById(int id){
		return extDao.findById(id);
	}

	public ShopDecoratePageDraftExt find(ShopDecoratePageDraftExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopDecoratePageDraftExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopDecoratePageDraftExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopDecoratePageDraftExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopDecoratePageDraftExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopDecoratePageDraftExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopDecoratePageDraftExt> list(ShopDecoratePageDraftExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopDecoratePageDraftExt> paginate(ShopDecoratePageDraftExtExample example) {
		List<ShopDecoratePageDraftExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
