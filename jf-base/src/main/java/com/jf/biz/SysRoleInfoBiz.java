package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysRoleInfoExtMapper;
import com.jf.dao.SysRoleInfoMapper;
import com.jf.entity.SysRoleInfo;
import com.jf.entity.SysRoleInfoExample;
import com.jf.entity.SysRoleInfoExt;
import com.jf.entity.SysRoleInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleInfoBiz extends BaseService<SysRoleInfo, SysRoleInfoExample> {

	@Autowired
	private SysRoleInfoMapper dao;
	@Autowired
	private SysRoleInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysRoleInfoMapper(SysRoleInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysRoleInfoExt findById(int id){
		return extDao.findById(id);
	}

	public SysRoleInfoExt find(SysRoleInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysRoleInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysRoleInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysRoleInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysRoleInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysRoleInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysRoleInfoExt> list(SysRoleInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysRoleInfoExt> paginate(SysRoleInfoExtExample example) {
		List<SysRoleInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
