package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopDecorateInfoDraftExtMapper;
import com.jf.dao.ShopDecorateInfoDraftMapper;
import com.jf.entity.ShopDecorateInfoDraft;
import com.jf.entity.ShopDecorateInfoDraftExample;
import com.jf.entity.ShopDecorateInfoDraftExt;
import com.jf.entity.ShopDecorateInfoDraftExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopDecorateInfoDraftBiz extends BaseService<ShopDecorateInfoDraft, ShopDecorateInfoDraftExample> {

	@Autowired
	private ShopDecorateInfoDraftMapper dao;
	@Autowired
	private ShopDecorateInfoDraftExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopDecorateInfoDraftMapper(ShopDecorateInfoDraftMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopDecorateInfoDraftExt save(ShopDecorateInfoDraftExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopDecorateInfoDraft update(ShopDecorateInfoDraft model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopDecorateInfoDraft model = new ShopDecorateInfoDraft();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopDecorateInfoDraftExt findById(int id){
		return extDao.findById(id);
	}

	public ShopDecorateInfoDraftExt find(ShopDecorateInfoDraftExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopDecorateInfoDraftExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopDecorateInfoDraftExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopDecorateInfoDraftExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopDecorateInfoDraftExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopDecorateInfoDraftExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopDecorateInfoDraftExt> list(ShopDecorateInfoDraftExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopDecorateInfoDraftExt> paginate(ShopDecorateInfoDraftExtExample example) {
		List<ShopDecorateInfoDraftExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
