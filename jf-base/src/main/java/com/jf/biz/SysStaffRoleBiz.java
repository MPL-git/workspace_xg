package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysStaffRoleExtMapper;
import com.jf.dao.SysStaffRoleMapper;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;
import com.jf.entity.SysStaffRoleExt;
import com.jf.entity.SysStaffRoleExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysStaffRoleBiz extends BaseService<SysStaffRole, SysStaffRoleExample> {

	@Autowired
	private SysStaffRoleMapper dao;
	@Autowired
	private SysStaffRoleExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysStaffRoleMapper(SysStaffRoleMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysStaffRoleExt findById(int id){
		return extDao.findById(id);
	}

	public SysStaffRoleExt find(SysStaffRoleExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysStaffRoleExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysStaffRoleExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysStaffRoleExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysStaffRoleExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysStaffRoleExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysStaffRoleExt> list(SysStaffRoleExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysStaffRoleExt> paginate(SysStaffRoleExtExample example) {
		List<SysStaffRoleExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
