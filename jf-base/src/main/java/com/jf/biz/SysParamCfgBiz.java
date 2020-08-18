package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysParamCfgExtMapper;
import com.jf.dao.SysParamCfgMapper;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysParamCfgExt;
import com.jf.entity.SysParamCfgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysParamCfgBiz extends BaseService<SysParamCfg, SysParamCfgExample> {

	@Autowired
	private SysParamCfgMapper dao;
	@Autowired
	private SysParamCfgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 *
	 */
	public SysParamCfg findByCode(String code){
		SysParamCfgExtExample example = new SysParamCfgExtExample();
		SysParamCfgExtExample.SysParamCfgExtCriteria criteria = example.createCriteria();
		criteria.andParamCodeEqualTo(code);
		return find(example);
	}



	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysParamCfgMapper(SysParamCfgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysParamCfgExt findById(int id){
		return extDao.findById(id);
	}

	public SysParamCfgExt find(SysParamCfgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysParamCfgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysParamCfgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysParamCfgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysParamCfgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysParamCfgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysParamCfgExt> list(SysParamCfgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysParamCfgExt> paginate(SysParamCfgExtExample example) {
		List<SysParamCfgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
