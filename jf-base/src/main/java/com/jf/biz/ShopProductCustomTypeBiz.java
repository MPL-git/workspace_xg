package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopProductCustomTypeExtMapper;
import com.jf.dao.ShopProductCustomTypeMapper;
import com.jf.entity.ShopProductCustomType;
import com.jf.entity.ShopProductCustomTypeExample;
import com.jf.entity.ShopProductCustomTypeExt;
import com.jf.entity.ShopProductCustomTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopProductCustomTypeBiz extends BaseService<ShopProductCustomType, ShopProductCustomTypeExample> {

	@Autowired
	private ShopProductCustomTypeMapper dao;
	@Autowired
	private ShopProductCustomTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopProductCustomTypeMapper(ShopProductCustomTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopProductCustomTypeExt save(ShopProductCustomTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopProductCustomType update(ShopProductCustomType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopProductCustomType model = new ShopProductCustomType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopProductCustomTypeExt findById(int id){
		return extDao.findById(id);
	}

	public ShopProductCustomTypeExt find(ShopProductCustomTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopProductCustomTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopProductCustomTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopProductCustomTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopProductCustomTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopProductCustomTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopProductCustomTypeExt> list(ShopProductCustomTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopProductCustomTypeExt> paginate(ShopProductCustomTypeExtExample example) {
		List<ShopProductCustomTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
