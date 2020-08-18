package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinOauthRedirectUrlExtMapper;
import com.jf.dao.WeixinOauthRedirectUrlMapper;
import com.jf.entity.WeixinOauthRedirectUrl;
import com.jf.entity.WeixinOauthRedirectUrlExample;
import com.jf.entity.WeixinOauthRedirectUrlExt;
import com.jf.entity.WeixinOauthRedirectUrlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinOauthRedirectUrlBiz extends BaseService<WeixinOauthRedirectUrl, WeixinOauthRedirectUrlExample> {

	@Autowired
	private WeixinOauthRedirectUrlMapper dao;
	@Autowired
	private WeixinOauthRedirectUrlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinOauthRedirectUrlMapper(WeixinOauthRedirectUrlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinOauthRedirectUrlExt save(WeixinOauthRedirectUrlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinOauthRedirectUrl update(WeixinOauthRedirectUrl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinOauthRedirectUrl model = new WeixinOauthRedirectUrl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinOauthRedirectUrlExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinOauthRedirectUrlExt find(WeixinOauthRedirectUrlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinOauthRedirectUrlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinOauthRedirectUrlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinOauthRedirectUrlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinOauthRedirectUrlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinOauthRedirectUrlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinOauthRedirectUrlExt> list(WeixinOauthRedirectUrlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinOauthRedirectUrlExt> paginate(WeixinOauthRedirectUrlExtExample example) {
		List<WeixinOauthRedirectUrlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
