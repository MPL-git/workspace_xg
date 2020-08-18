package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SysStaffProductTypeMapper;
import com.jf.dao.SysStaffProductTypeExtMapper;
import com.jf.entity.SysStaffProductType;
import com.jf.entity.SysStaffProductTypeExample;
import com.jf.entity.SysStaffProductTypeExt;
import com.jf.entity.SysStaffProductTypeExtExample;
import com.jf.common.base.BaseService;

@Service
public class SysStaffProductTypeBiz extends BaseService<SysStaffProductType, SysStaffProductTypeExample> {

	@Autowired
	private SysStaffProductTypeMapper dao;
	@Autowired
	private SysStaffProductTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysStaffProductTypeMapper(SysStaffProductTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysStaffProductTypeExt save(SysStaffProductTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SysStaffProductType update(SysStaffProductType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SysStaffProductType model = new SysStaffProductType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SysStaffProductTypeExt findById(int id){
		return extDao.findById(id);
	}

	public SysStaffProductTypeExt find(SysStaffProductTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysStaffProductTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysStaffProductTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysStaffProductTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysStaffProductTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysStaffProductTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysStaffProductTypeExt> list(SysStaffProductTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysStaffProductTypeExt> paginate(SysStaffProductTypeExtExample example) {
		List<SysStaffProductTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
