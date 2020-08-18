package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MallCategoryMapExtMapper;
import com.jf.dao.MallCategoryMapMapper;
import com.jf.entity.MallCategoryMap;
import com.jf.entity.MallCategoryMapExample;
import com.jf.entity.MallCategoryMapExt;
import com.jf.entity.MallCategoryMapExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MallCategoryMapBiz extends BaseService<MallCategoryMap, MallCategoryMapExample> {

	@Autowired
	private MallCategoryMapMapper dao;
	@Autowired
	private MallCategoryMapExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMallCategoryMapMapper(MallCategoryMapMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MallCategoryMapExt save(MallCategoryMapExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MallCategoryMap update(MallCategoryMap model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MallCategoryMap model = new MallCategoryMap();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MallCategoryMapExt findById(int id){
		return extDao.findById(id);
	}

	public MallCategoryMapExt find(MallCategoryMapExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MallCategoryMapExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MallCategoryMapExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MallCategoryMapExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MallCategoryMapExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MallCategoryMapExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MallCategoryMapExt> list(MallCategoryMapExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MallCategoryMapExt> paginate(MallCategoryMapExtExample example) {
		List<MallCategoryMapExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
