package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopDecorateInfoExtMapper;
import com.jf.dao.ShopDecorateInfoMapper;
import com.jf.entity.ShopDecorateInfo;
import com.jf.entity.ShopDecorateInfoExample;
import com.jf.entity.ShopDecorateInfoExt;
import com.jf.entity.ShopDecorateInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopDecorateInfoBiz extends BaseService<ShopDecorateInfo, ShopDecorateInfoExample> {

	@Autowired
	private ShopDecorateInfoMapper dao;
	@Autowired
	private ShopDecorateInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopDecorateInfoMapper(ShopDecorateInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopDecorateInfoExt save(ShopDecorateInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopDecorateInfo update(ShopDecorateInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopDecorateInfo model = new ShopDecorateInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopDecorateInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ShopDecorateInfoExt find(ShopDecorateInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopDecorateInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopDecorateInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopDecorateInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopDecorateInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopDecorateInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopDecorateInfoExt> list(ShopDecorateInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopDecorateInfoExt> paginate(ShopDecorateInfoExtExample example) {
		List<ShopDecorateInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
