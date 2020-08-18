package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.RoleMenuMapper;
import com.jf.dao.RoleMenuExtMapper;
import com.jf.entity.RoleMenu;
import com.jf.entity.RoleMenuExample;
import com.jf.entity.RoleMenuExt;
import com.jf.entity.RoleMenuExtExample;
import com.jf.common.base.BaseService;

@Service
public class RoleMenuBiz extends BaseService<RoleMenu, RoleMenuExample> {

	@Autowired
	private RoleMenuMapper dao;
	@Autowired
	private RoleMenuExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setRoleMenuMapper(RoleMenuMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public RoleMenuExt save(RoleMenuExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public RoleMenu update(RoleMenu model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		RoleMenu model = new RoleMenu();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public RoleMenuExt findById(int id){
		return extDao.findById(id);
	}

	public RoleMenuExt find(RoleMenuExtExample example){
		return extDao.find(example.fill());
	}

	public int count(RoleMenuExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, RoleMenuExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, RoleMenuExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, RoleMenuExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(RoleMenuExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<RoleMenuExt> list(RoleMenuExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<RoleMenuExt> paginate(RoleMenuExtExample example) {
		List<RoleMenuExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
