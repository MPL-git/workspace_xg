package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopPreferentialInfoExtMapper;
import com.jf.dao.ShopPreferentialInfoMapper;
import com.jf.entity.ShopPreferentialInfo;
import com.jf.entity.ShopPreferentialInfoExample;
import com.jf.entity.ShopPreferentialInfoExt;
import com.jf.entity.ShopPreferentialInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopPreferentialInfoBiz extends BaseService<ShopPreferentialInfo, ShopPreferentialInfoExample> {

	@Autowired
	private ShopPreferentialInfoMapper dao;
	@Autowired
	private ShopPreferentialInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopPreferentialInfoMapper(ShopPreferentialInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopPreferentialInfoExt save(ShopPreferentialInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopPreferentialInfo update(ShopPreferentialInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopPreferentialInfo model = new ShopPreferentialInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopPreferentialInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ShopPreferentialInfoExt find(ShopPreferentialInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopPreferentialInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopPreferentialInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopPreferentialInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopPreferentialInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopPreferentialInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopPreferentialInfoExt> list(ShopPreferentialInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopPreferentialInfoExt> paginate(ShopPreferentialInfoExtExample example) {
		List<ShopPreferentialInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
