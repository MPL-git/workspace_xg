package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShopScoreExtMapper;
import com.jf.dao.ShopScoreMapper;
import com.jf.entity.ShopScore;
import com.jf.entity.ShopScoreExample;
import com.jf.entity.ShopScoreExt;
import com.jf.entity.ShopScoreExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopScoreBiz extends BaseService<ShopScore, ShopScoreExample> {

	@Autowired
	private ShopScoreMapper dao;
	@Autowired
	private ShopScoreExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShopScoreMapper(ShopScoreMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShopScoreExt save(ShopScoreExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShopScore update(ShopScore model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShopScore model = new ShopScore();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShopScoreExt findById(int id){
		return extDao.findById(id);
	}

	public ShopScoreExt find(ShopScoreExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShopScoreExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShopScoreExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShopScoreExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShopScoreExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShopScoreExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShopScoreExt> list(ShopScoreExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShopScoreExt> paginate(ShopScoreExtExample example) {
		List<ShopScoreExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
