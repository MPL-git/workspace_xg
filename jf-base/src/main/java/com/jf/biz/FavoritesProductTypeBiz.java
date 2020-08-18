package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FavoritesProductTypeExtMapper;
import com.jf.dao.FavoritesProductTypeMapper;
import com.jf.entity.FavoritesProductType;
import com.jf.entity.FavoritesProductTypeExample;
import com.jf.entity.FavoritesProductTypeExt;
import com.jf.entity.FavoritesProductTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FavoritesProductTypeBiz extends BaseService<FavoritesProductType, FavoritesProductTypeExample> {

	@Autowired
	private FavoritesProductTypeMapper dao;
	@Autowired
	private FavoritesProductTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setFavoritesProductTypeMapper(FavoritesProductTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public FavoritesProductTypeExt save(FavoritesProductTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public FavoritesProductType update(FavoritesProductType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		FavoritesProductType model = new FavoritesProductType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public FavoritesProductTypeExt findById(int id){
		return extDao.findById(id);
	}

	public FavoritesProductTypeExt find(FavoritesProductTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(FavoritesProductTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, FavoritesProductTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, FavoritesProductTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, FavoritesProductTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(FavoritesProductTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<FavoritesProductTypeExt> list(FavoritesProductTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<FavoritesProductTypeExt> paginate(FavoritesProductTypeExtExample example) {
		List<FavoritesProductTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
