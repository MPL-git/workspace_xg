package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopDecoratePageExtMapper;
import com.jf.dao.ShopDecoratePageMapper;
import com.jf.entity.ShopDecoratePage;
import com.jf.entity.ShopDecoratePageExample;
import com.jf.entity.ShopDecoratePageExt;
import com.jf.entity.ShopDecoratePageExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopDecoratePageBiz extends BaseService<ShopDecoratePage, ShopDecoratePageExample> {

	@Autowired
	private ShopDecoratePageMapper dao;
	@Autowired
	private ShopDecoratePageExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopDecoratePageMapper(ShopDecoratePageMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopDecoratePageExt save(ShopDecoratePageExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopDecoratePage update(ShopDecoratePage model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopDecoratePage model = new ShopDecoratePage();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopDecoratePageExt findById(int id){
		return extDao.findById(id);
	}

	public ShopDecoratePageExt find(ShopDecoratePageExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopDecoratePageExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopDecoratePageExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopDecoratePageExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopDecoratePageExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopDecoratePageExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopDecoratePageExt> list(ShopDecoratePageExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopDecoratePageExt> paginate(ShopDecoratePageExtExample example) {
		List<ShopDecoratePageExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
