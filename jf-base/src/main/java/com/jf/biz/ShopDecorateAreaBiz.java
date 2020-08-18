package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopDecorateAreaExtMapper;
import com.jf.dao.ShopDecorateAreaMapper;
import com.jf.entity.ShopDecorateArea;
import com.jf.entity.ShopDecorateAreaExample;
import com.jf.entity.ShopDecorateAreaExt;
import com.jf.entity.ShopDecorateAreaExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopDecorateAreaBiz extends BaseService<ShopDecorateArea, ShopDecorateAreaExample> {

	@Autowired
	private ShopDecorateAreaMapper dao;
	@Autowired
	private ShopDecorateAreaExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopDecorateAreaMapper(ShopDecorateAreaMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopDecorateAreaExt save(ShopDecorateAreaExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopDecorateArea update(ShopDecorateArea model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopDecorateArea model = new ShopDecorateArea();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopDecorateAreaExt findById(int id){
		return extDao.findById(id);
	}

	public ShopDecorateAreaExt find(ShopDecorateAreaExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopDecorateAreaExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopDecorateAreaExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopDecorateAreaExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopDecorateAreaExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopDecorateAreaExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopDecorateAreaExt> list(ShopDecorateAreaExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopDecorateAreaExt> paginate(ShopDecorateAreaExtExample example) {
		List<ShopDecorateAreaExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
