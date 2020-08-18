package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MallCategoryItemExtMapper;
import com.jf.dao.MallCategoryItemMapper;
import com.jf.entity.MallCategoryItem;
import com.jf.entity.MallCategoryItemExample;
import com.jf.entity.MallCategoryItemExt;
import com.jf.entity.MallCategoryItemExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MallCategoryItemBiz extends BaseService<MallCategoryItem, MallCategoryItemExample> {

	@Autowired
	private MallCategoryItemMapper dao;
	@Autowired
	private MallCategoryItemExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMallCategoryItemMapper(MallCategoryItemMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MallCategoryItemExt save(MallCategoryItemExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MallCategoryItem update(MallCategoryItem model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MallCategoryItem model = new MallCategoryItem();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MallCategoryItemExt findById(int id){
		return extDao.findById(id);
	}

	public MallCategoryItemExt find(MallCategoryItemExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MallCategoryItemExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MallCategoryItemExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MallCategoryItemExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MallCategoryItemExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MallCategoryItemExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MallCategoryItemExt> list(MallCategoryItemExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MallCategoryItemExt> paginate(MallCategoryItemExtExample example) {
		List<MallCategoryItemExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
