package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.StaffMchtContactPermissionExtMapper;
import com.jf.dao.StaffMchtContactPermissionMapper;
import com.jf.entity.StaffMchtContactPermission;
import com.jf.entity.StaffMchtContactPermissionExample;
import com.jf.entity.StaffMchtContactPermissionExt;
import com.jf.entity.StaffMchtContactPermissionExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaffMchtContactPermissionBiz extends BaseService<StaffMchtContactPermission, StaffMchtContactPermissionExample> {

	@Autowired
	private StaffMchtContactPermissionMapper dao;
	@Autowired
	private StaffMchtContactPermissionExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setStaffMchtContactPermissionMapper(StaffMchtContactPermissionMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public StaffMchtContactPermissionExt save(StaffMchtContactPermissionExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public StaffMchtContactPermission update(StaffMchtContactPermission model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		StaffMchtContactPermission model = new StaffMchtContactPermission();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public StaffMchtContactPermissionExt findById(int id){
		return extDao.findById(id);
	}

	public StaffMchtContactPermissionExt find(StaffMchtContactPermissionExtExample example){
		return extDao.find(example.fill());
	}

	public int count(StaffMchtContactPermissionExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, StaffMchtContactPermissionExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, StaffMchtContactPermissionExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, StaffMchtContactPermissionExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(StaffMchtContactPermissionExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<StaffMchtContactPermissionExt> list(StaffMchtContactPermissionExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<StaffMchtContactPermissionExt> paginate(StaffMchtContactPermissionExtExample example) {
		List<StaffMchtContactPermissionExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
