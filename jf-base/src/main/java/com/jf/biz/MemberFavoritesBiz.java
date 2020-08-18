package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberFavoritesExtMapper;
import com.jf.dao.MemberFavoritesMapper;
import com.jf.entity.MemberFavorites;
import com.jf.entity.MemberFavoritesExample;
import com.jf.entity.MemberFavoritesExt;
import com.jf.entity.MemberFavoritesExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberFavoritesBiz extends BaseService<MemberFavorites, MemberFavoritesExample> {

	@Autowired
	private MemberFavoritesMapper dao;
	@Autowired
	private MemberFavoritesExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberFavoritesMapper(MemberFavoritesMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberFavoritesExt save(MemberFavoritesExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberFavorites update(MemberFavorites model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberFavorites model = new MemberFavorites();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberFavoritesExt findById(int id){
		return extDao.findById(id);
	}

	public MemberFavoritesExt find(MemberFavoritesExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberFavoritesExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberFavoritesExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberFavoritesExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberFavoritesExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberFavoritesExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberFavoritesExt> list(MemberFavoritesExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberFavoritesExt> paginate(MemberFavoritesExtExample example) {
		List<MemberFavoritesExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
