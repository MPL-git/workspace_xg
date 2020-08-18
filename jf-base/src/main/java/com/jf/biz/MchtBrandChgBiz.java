package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandChgExtMapper;
import com.jf.dao.MchtBrandChgMapper;
import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtBrandChgExample;
import com.jf.entity.MchtBrandChgExt;
import com.jf.entity.MchtBrandChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandChgBiz extends BaseService<MchtBrandChg, MchtBrandChgExample> {

	@Autowired
	private MchtBrandChgMapper dao;
	@Autowired
	private MchtBrandChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandChgMapper(MchtBrandChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandChgExt save(MchtBrandChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandChg update(MchtBrandChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandChg model = new MchtBrandChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandChgExt find(MchtBrandChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandChgExt> list(MchtBrandChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandChgExt> paginate(MchtBrandChgExtExample example) {
		List<MchtBrandChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
