package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformContactExtMapper;
import com.jf.dao.PlatformContactMapper;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.PlatformContactExt;
import com.jf.entity.PlatformContactExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlatformContactBiz extends BaseService<PlatformContact, PlatformContactExample> {

	@Autowired
	private PlatformContactMapper dao;
	@Autowired
	private PlatformContactExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPlatformContactMapper(PlatformContactMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PlatformContactExt save(PlatformContactExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PlatformContact update(PlatformContact model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PlatformContact model = new PlatformContact();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PlatformContactExt findById(int id){
		return extDao.findById(id);
	}

	public PlatformContactExt find(PlatformContactExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PlatformContactExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PlatformContactExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PlatformContactExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PlatformContactExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PlatformContactExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PlatformContactExt> list(PlatformContactExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PlatformContactExt> paginate(PlatformContactExtExample example) {
		List<PlatformContactExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
