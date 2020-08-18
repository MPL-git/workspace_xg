package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtProductTypeExtMapper;
import com.jf.dao.MchtProductTypeMapper;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeExample;
import com.jf.entity.MchtProductTypeExt;
import com.jf.entity.MchtProductTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtProductTypeBiz extends BaseService<MchtProductType, MchtProductTypeExample> {

	@Autowired
	private MchtProductTypeMapper dao;
	@Autowired
	private MchtProductTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtProductTypeMapper(MchtProductTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtProductTypeExt save(MchtProductTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtProductType update(MchtProductType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtProductType model = new MchtProductType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtProductTypeExt findById(int id){
		return extDao.findById(id);
	}

	public MchtProductTypeExt find(MchtProductTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtProductTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtProductTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtProductTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtProductTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtProductTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtProductTypeExt> list(MchtProductTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtProductTypeExt> paginate(MchtProductTypeExtExample example) {
		List<MchtProductTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
