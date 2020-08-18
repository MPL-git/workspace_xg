package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BannerRecommendProductExtMapper;
import com.jf.dao.BannerRecommendProductMapper;
import com.jf.entity.BannerRecommendProduct;
import com.jf.entity.BannerRecommendProductExample;
import com.jf.entity.BannerRecommendProductExt;
import com.jf.entity.BannerRecommendProductExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BannerRecommendProductBiz extends BaseService<BannerRecommendProduct, BannerRecommendProductExample> {

	@Autowired
	private BannerRecommendProductMapper dao;
	@Autowired
	private BannerRecommendProductExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBannerRecommendProductMapper(BannerRecommendProductMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BannerRecommendProductExt save(BannerRecommendProductExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BannerRecommendProduct update(BannerRecommendProduct model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BannerRecommendProduct model = new BannerRecommendProduct();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BannerRecommendProductExt findById(int id){
		return extDao.findById(id);
	}

	public BannerRecommendProductExt find(BannerRecommendProductExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BannerRecommendProductExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BannerRecommendProductExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BannerRecommendProductExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BannerRecommendProductExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BannerRecommendProductExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BannerRecommendProductExt> list(BannerRecommendProductExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BannerRecommendProductExt> paginate(BannerRecommendProductExtExample example) {
		List<BannerRecommendProductExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
