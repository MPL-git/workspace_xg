package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopStoryExtMapper;
import com.jf.dao.ShopStoryMapper;
import com.jf.entity.ShopStory;
import com.jf.entity.ShopStoryExample;
import com.jf.entity.ShopStoryExt;
import com.jf.entity.ShopStoryExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopStoryBiz extends BaseService<ShopStory, ShopStoryExample> {

	@Autowired
	private ShopStoryMapper dao;
	@Autowired
	private ShopStoryExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopStoryMapper(ShopStoryMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopStoryExt save(ShopStoryExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopStory update(ShopStory model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopStory model = new ShopStory();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopStoryExt findById(int id){
		return extDao.findById(id);
	}

	public ShopStoryExt find(ShopStoryExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopStoryExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopStoryExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopStoryExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopStoryExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopStoryExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopStoryExt> list(ShopStoryExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopStoryExt> paginate(ShopStoryExtExample example) {
		List<ShopStoryExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
