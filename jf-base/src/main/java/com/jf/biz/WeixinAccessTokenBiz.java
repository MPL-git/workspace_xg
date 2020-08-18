package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinAccessTokenExtMapper;
import com.jf.dao.WeixinAccessTokenMapper;
import com.jf.entity.WeixinAccessToken;
import com.jf.entity.WeixinAccessTokenExample;
import com.jf.entity.WeixinAccessTokenExt;
import com.jf.entity.WeixinAccessTokenExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinAccessTokenBiz extends BaseService<WeixinAccessToken, WeixinAccessTokenExample> {

	@Autowired
	private WeixinAccessTokenMapper dao;
	@Autowired
	private WeixinAccessTokenExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinAccessTokenMapper(WeixinAccessTokenMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinAccessTokenExt save(WeixinAccessTokenExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinAccessToken update(WeixinAccessToken model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinAccessToken model = new WeixinAccessToken();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinAccessTokenExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinAccessTokenExt find(WeixinAccessTokenExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinAccessTokenExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinAccessTokenExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinAccessTokenExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinAccessTokenExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinAccessTokenExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinAccessTokenExt> list(WeixinAccessTokenExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinAccessTokenExt> paginate(WeixinAccessTokenExtExample example) {
		List<WeixinAccessTokenExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
