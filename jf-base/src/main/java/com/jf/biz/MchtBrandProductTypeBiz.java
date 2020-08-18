package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandProductTypeExtMapper;
import com.jf.dao.MchtBrandProductTypeMapper;
import com.jf.entity.MchtBrandProductType;
import com.jf.entity.MchtBrandProductTypeExample;
import com.jf.entity.MchtBrandProductTypeExt;
import com.jf.entity.MchtBrandProductTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandProductTypeBiz extends BaseService<MchtBrandProductType, MchtBrandProductTypeExample> {

	@Autowired
	private MchtBrandProductTypeMapper dao;
	@Autowired
	private MchtBrandProductTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandProductTypeMapper(MchtBrandProductTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandProductTypeExt save(MchtBrandProductTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandProductType update(MchtBrandProductType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandProductType model = new MchtBrandProductType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandProductTypeExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandProductTypeExt find(MchtBrandProductTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandProductTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandProductTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandProductTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandProductTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandProductTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandProductTypeExt> list(MchtBrandProductTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandProductTypeExt> paginate(MchtBrandProductTypeExtExample example) {
		List<MchtBrandProductTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
