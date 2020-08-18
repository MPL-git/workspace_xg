package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FavoritesExtMapper;
import com.jf.dao.FavoritesMapper;
import com.jf.entity.Favorites;
import com.jf.entity.FavoritesExample;
import com.jf.entity.FavoritesExt;
import com.jf.entity.FavoritesExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FavoritesBiz extends BaseService<Favorites, FavoritesExample> {

	@Autowired
	private FavoritesMapper dao;
	@Autowired
	private FavoritesExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setFavoritesMapper(FavoritesMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public FavoritesExt save(FavoritesExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Favorites update(Favorites model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Favorites model = new Favorites();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public FavoritesExt findById(int id){
		return extDao.findById(id);
	}

	public FavoritesExt find(FavoritesExtExample example){
		return extDao.find(example.fill());
	}

	public int count(FavoritesExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, FavoritesExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, FavoritesExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, FavoritesExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(FavoritesExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<FavoritesExt> list(FavoritesExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<FavoritesExt> paginate(FavoritesExtExample example) {
		List<FavoritesExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
