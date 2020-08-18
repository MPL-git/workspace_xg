package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopStatusLogExtMapper;
import com.jf.dao.ShopStatusLogMapper;
import com.jf.entity.ShopStatusLog;
import com.jf.entity.ShopStatusLogExample;
import com.jf.entity.ShopStatusLogExt;
import com.jf.entity.ShopStatusLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopStatusLogBiz extends BaseService<ShopStatusLog, ShopStatusLogExample> {

	@Autowired
	private ShopStatusLogMapper dao;
	@Autowired
	private ShopStatusLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopStatusLogMapper(ShopStatusLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopStatusLogExt save(ShopStatusLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopStatusLog update(ShopStatusLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopStatusLog model = new ShopStatusLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopStatusLogExt findById(int id){
		return extDao.findById(id);
	}

	public ShopStatusLogExt find(ShopStatusLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopStatusLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopStatusLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopStatusLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopStatusLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopStatusLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopStatusLogExt> list(ShopStatusLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopStatusLogExt> paginate(ShopStatusLogExtExample example) {
		List<ShopStatusLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
