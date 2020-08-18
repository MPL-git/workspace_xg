package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysStaffInfoExtMapper;
import com.jf.dao.SysStaffInfoMapper;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;
import com.jf.entity.SysStaffInfoExt;
import com.jf.entity.SysStaffInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysStaffInfoBiz extends BaseService<SysStaffInfo, SysStaffInfoExample> {

	@Autowired
	private SysStaffInfoMapper dao;
	@Autowired
	private SysStaffInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysStaffInfoMapper(SysStaffInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysStaffInfoExt findById(int id){
		return extDao.findById(id);
	}

	public SysStaffInfoExt find(SysStaffInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysStaffInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysStaffInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysStaffInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysStaffInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysStaffInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysStaffInfoExt> list(SysStaffInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysStaffInfoExt> paginate(SysStaffInfoExtExample example) {
		List<SysStaffInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
