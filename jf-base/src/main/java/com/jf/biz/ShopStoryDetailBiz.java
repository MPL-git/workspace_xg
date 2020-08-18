package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopStoryDetailExtMapper;
import com.jf.dao.ShopStoryDetailMapper;
import com.jf.entity.ShopStoryDetail;
import com.jf.entity.ShopStoryDetailExample;
import com.jf.entity.ShopStoryDetailExt;
import com.jf.entity.ShopStoryDetailExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopStoryDetailBiz extends BaseService<ShopStoryDetail, ShopStoryDetailExample> {

	@Autowired
	private ShopStoryDetailMapper dao;
	@Autowired
	private ShopStoryDetailExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopStoryDetailMapper(ShopStoryDetailMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopStoryDetailExt save(ShopStoryDetailExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopStoryDetail update(ShopStoryDetail model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopStoryDetail model = new ShopStoryDetail();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopStoryDetailExt findById(int id){
		return extDao.findById(id);
	}

	public ShopStoryDetailExt find(ShopStoryDetailExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopStoryDetailExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopStoryDetailExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopStoryDetailExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopStoryDetailExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopStoryDetailExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopStoryDetailExt> list(ShopStoryDetailExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopStoryDetailExt> paginate(ShopStoryDetailExtExample example) {
		List<ShopStoryDetailExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
