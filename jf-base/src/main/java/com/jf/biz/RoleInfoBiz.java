package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.RoleInfoMapper;
import com.jf.dao.RoleInfoExtMapper;
import com.jf.entity.RoleInfo;
import com.jf.entity.RoleInfoExample;
import com.jf.entity.RoleInfoExt;
import com.jf.entity.RoleInfoExtExample;
import com.jf.common.base.BaseService;

@Service
public class RoleInfoBiz extends BaseService<RoleInfo, RoleInfoExample> {

	@Autowired
	private RoleInfoMapper dao;
	@Autowired
	private RoleInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setRoleInfoMapper(RoleInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public RoleInfoExt save(RoleInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public RoleInfo update(RoleInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		RoleInfo model = new RoleInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public RoleInfoExt findById(int id){
		return extDao.findById(id);
	}

	public RoleInfoExt find(RoleInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(RoleInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, RoleInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, RoleInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, RoleInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(RoleInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<RoleInfoExt> list(RoleInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<RoleInfoExt> paginate(RoleInfoExtExample example) {
		List<RoleInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
