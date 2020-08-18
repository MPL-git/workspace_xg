package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DouyinSprLogExtMapper;
import com.jf.dao.DouyinSprLogMapper;
import com.jf.entity.DouyinSprLog;
import com.jf.entity.DouyinSprLogExample;
import com.jf.entity.DouyinSprLogExt;
import com.jf.entity.DouyinSprLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DouyinSprLogBiz extends BaseService<DouyinSprLog, DouyinSprLogExample> {

	@Autowired
	private DouyinSprLogMapper dao;
	@Autowired
	private DouyinSprLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDouyinSprLogMapper(DouyinSprLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DouyinSprLogExt save(DouyinSprLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DouyinSprLog update(DouyinSprLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DouyinSprLog model = new DouyinSprLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DouyinSprLogExt findById(int id){
		return extDao.findById(id);
	}

	public DouyinSprLogExt find(DouyinSprLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DouyinSprLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DouyinSprLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DouyinSprLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DouyinSprLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DouyinSprLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DouyinSprLogExt> list(DouyinSprLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DouyinSprLogExt> paginate(DouyinSprLogExtExample example) {
		List<DouyinSprLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
