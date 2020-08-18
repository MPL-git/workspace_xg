package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SourceProductTypeExtMapper;
import com.jf.dao.SourceProductTypeMapper;
import com.jf.entity.SourceProductType;
import com.jf.entity.SourceProductTypeExample;
import com.jf.entity.SourceProductTypeExt;
import com.jf.entity.SourceProductTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SourceProductTypeBiz extends BaseService<SourceProductType, SourceProductTypeExample> {

	@Autowired
	private SourceProductTypeMapper dao;
	@Autowired
	private SourceProductTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSourceProductTypeMapper(SourceProductTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SourceProductTypeExt save(SourceProductTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SourceProductType update(SourceProductType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SourceProductType model = new SourceProductType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SourceProductTypeExt findById(int id){
		return extDao.findById(id);
	}

	public SourceProductTypeExt find(SourceProductTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SourceProductTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SourceProductTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SourceProductTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SourceProductTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SourceProductTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SourceProductTypeExt> list(SourceProductTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SourceProductTypeExt> paginate(SourceProductTypeExtExample example) {
		List<SourceProductTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
