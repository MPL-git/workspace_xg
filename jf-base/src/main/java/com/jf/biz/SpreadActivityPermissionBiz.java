package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SpreadActivityPermissionExtMapper;
import com.jf.dao.SpreadActivityPermissionMapper;
import com.jf.entity.SpreadActivityPermission;
import com.jf.entity.SpreadActivityPermissionExample;
import com.jf.entity.SpreadActivityPermissionExt;
import com.jf.entity.SpreadActivityPermissionExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpreadActivityPermissionBiz extends BaseService<SpreadActivityPermission, SpreadActivityPermissionExample> {

	@Autowired
	private SpreadActivityPermissionMapper dao;
	@Autowired
	private SpreadActivityPermissionExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSpreadActivityPermissionMapper(SpreadActivityPermissionMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SpreadActivityPermissionExt save(SpreadActivityPermissionExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SpreadActivityPermission update(SpreadActivityPermission model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SpreadActivityPermission model = new SpreadActivityPermission();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SpreadActivityPermissionExt findById(int id){
		return extDao.findById(id);
	}

	public SpreadActivityPermissionExt find(SpreadActivityPermissionExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SpreadActivityPermissionExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SpreadActivityPermissionExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SpreadActivityPermissionExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SpreadActivityPermissionExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SpreadActivityPermissionExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SpreadActivityPermissionExt> list(SpreadActivityPermissionExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SpreadActivityPermissionExt> paginate(SpreadActivityPermissionExtExample example) {
		List<SpreadActivityPermissionExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
