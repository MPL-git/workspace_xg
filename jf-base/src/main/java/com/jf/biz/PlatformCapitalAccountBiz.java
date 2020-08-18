package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformCapitalAccountExtMapper;
import com.jf.dao.PlatformCapitalAccountMapper;
import com.jf.entity.PlatformCapitalAccount;
import com.jf.entity.PlatformCapitalAccountExample;
import com.jf.entity.PlatformCapitalAccountExt;
import com.jf.entity.PlatformCapitalAccountExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlatformCapitalAccountBiz extends BaseService<PlatformCapitalAccount, PlatformCapitalAccountExample> {

	@Autowired
	private PlatformCapitalAccountMapper dao;
	@Autowired
	private PlatformCapitalAccountExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPlatformCapitalAccountMapper(PlatformCapitalAccountMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PlatformCapitalAccountExt save(PlatformCapitalAccountExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PlatformCapitalAccount update(PlatformCapitalAccount model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PlatformCapitalAccount model = new PlatformCapitalAccount();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PlatformCapitalAccountExt findById(int id){
		return extDao.findById(id);
	}

	public PlatformCapitalAccountExt find(PlatformCapitalAccountExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PlatformCapitalAccountExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PlatformCapitalAccountExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PlatformCapitalAccountExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PlatformCapitalAccountExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PlatformCapitalAccountExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PlatformCapitalAccountExt> list(PlatformCapitalAccountExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PlatformCapitalAccountExt> paginate(PlatformCapitalAccountExtExample example) {
		List<PlatformCapitalAccountExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
