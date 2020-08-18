package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysStatusExtMapper;
import com.jf.dao.SysStatusMapper;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;
import com.jf.entity.SysStatusExt;
import com.jf.entity.SysStatusExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysStatusBiz extends BaseService<SysStatus, SysStatusExample> {

	@Autowired
	private SysStatusMapper dao;
	@Autowired
	private SysStatusExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysStatusMapper(SysStatusMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysStatusExt findById(int id){
		return extDao.findById(id);
	}

	public SysStatusExt find(SysStatusExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysStatusExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysStatusExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysStatusExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysStatusExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysStatusExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysStatusExt> list(SysStatusExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysStatusExt> paginate(SysStatusExtExample example) {
		List<SysStatusExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
