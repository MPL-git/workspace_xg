package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopownerExtMapper;
import com.jf.dao.ShopownerMapper;
import com.jf.entity.Shopowner;
import com.jf.entity.ShopownerExample;
import com.jf.entity.ShopownerExt;
import com.jf.entity.ShopownerExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopownerBiz extends BaseService<Shopowner, ShopownerExample> {

	@Autowired
	private ShopownerMapper dao;
	@Autowired
	private ShopownerExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopownerMapper(ShopownerMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopownerExt save(ShopownerExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Shopowner update(Shopowner model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Shopowner model = new Shopowner();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopownerExt findById(int id){
		return extDao.findById(id);
	}

	public ShopownerExt find(ShopownerExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopownerExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopownerExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopownerExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopownerExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopownerExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopownerExt> list(ShopownerExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopownerExt> paginate(ShopownerExtExample example) {
		List<ShopownerExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
