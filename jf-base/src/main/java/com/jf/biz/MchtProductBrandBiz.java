package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtProductBrandExtMapper;
import com.jf.dao.MchtProductBrandMapper;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.MchtProductBrandExt;
import com.jf.entity.MchtProductBrandExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtProductBrandBiz extends BaseService<MchtProductBrand, MchtProductBrandExample> {

	@Autowired
	private MchtProductBrandMapper dao;
	@Autowired
	private MchtProductBrandExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtProductBrandMapper(MchtProductBrandMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtProductBrandExt save(MchtProductBrandExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtProductBrand update(MchtProductBrand model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtProductBrand model = new MchtProductBrand();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtProductBrandExt findById(int id){
		return extDao.findById(id);
	}

	public MchtProductBrandExt find(MchtProductBrandExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtProductBrandExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtProductBrandExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtProductBrandExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtProductBrandExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtProductBrandExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtProductBrandExt> list(MchtProductBrandExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtProductBrandExt> paginate(MchtProductBrandExtExample example) {
		List<MchtProductBrandExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
