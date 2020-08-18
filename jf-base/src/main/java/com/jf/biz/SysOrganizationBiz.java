package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysOrganizationExtMapper;
import com.jf.dao.SysOrganizationMapper;
import com.jf.entity.SysOrganization;
import com.jf.entity.SysOrganizationExample;
import com.jf.entity.SysOrganizationExt;
import com.jf.entity.SysOrganizationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOrganizationBiz extends BaseService<SysOrganization, SysOrganizationExample> {

	@Autowired
	private SysOrganizationMapper dao;
	@Autowired
	private SysOrganizationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysOrganizationMapper(SysOrganizationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysOrganizationExt findById(int id){
		return extDao.findById(id);
	}

	public SysOrganizationExt find(SysOrganizationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysOrganizationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysOrganizationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysOrganizationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysOrganizationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysOrganizationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysOrganizationExt> list(SysOrganizationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysOrganizationExt> paginate(SysOrganizationExtExample example) {
		List<SysOrganizationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
