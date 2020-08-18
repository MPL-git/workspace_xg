package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinXcxSprLogExtMapper;
import com.jf.dao.WeixinXcxSprLogMapper;
import com.jf.entity.WeixinXcxSprLog;
import com.jf.entity.WeixinXcxSprLogExample;
import com.jf.entity.WeixinXcxSprLogExt;
import com.jf.entity.WeixinXcxSprLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinXcxSprLogBiz extends BaseService<WeixinXcxSprLog, WeixinXcxSprLogExample> {

	@Autowired
	private WeixinXcxSprLogMapper dao;
	@Autowired
	private WeixinXcxSprLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinXcxSprLogMapper(WeixinXcxSprLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinXcxSprLogExt save(WeixinXcxSprLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinXcxSprLog update(WeixinXcxSprLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinXcxSprLog model = new WeixinXcxSprLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinXcxSprLogExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinXcxSprLogExt find(WeixinXcxSprLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinXcxSprLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinXcxSprLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinXcxSprLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinXcxSprLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinXcxSprLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinXcxSprLogExt> list(WeixinXcxSprLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinXcxSprLogExt> paginate(WeixinXcxSprLogExtExample example) {
		List<WeixinXcxSprLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
