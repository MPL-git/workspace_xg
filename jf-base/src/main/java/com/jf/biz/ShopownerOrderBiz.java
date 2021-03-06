package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopownerOrderExtMapper;
import com.jf.dao.ShopownerOrderMapper;
import com.jf.entity.ShopownerOrder;
import com.jf.entity.ShopownerOrderExample;
import com.jf.entity.ShopownerOrderExt;
import com.jf.entity.ShopownerOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopownerOrderBiz extends BaseService<ShopownerOrder, ShopownerOrderExample> {

	@Autowired
	private ShopownerOrderMapper dao;
	@Autowired
	private ShopownerOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopownerOrderMapper(ShopownerOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopownerOrderExt save(ShopownerOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopownerOrder update(ShopownerOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopownerOrder model = new ShopownerOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopownerOrderExt findById(int id){
		return extDao.findById(id);
	}

	public ShopownerOrderExt find(ShopownerOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopownerOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopownerOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopownerOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopownerOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopownerOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopownerOrderExt> list(ShopownerOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopownerOrderExt> paginate(ShopownerOrderExtExample example) {
		List<ShopownerOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
