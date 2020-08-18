package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinXcxMemberBindExtMapper;
import com.jf.dao.WeixinXcxMemberBindMapper;
import com.jf.entity.WeixinXcxMemberBind;
import com.jf.entity.WeixinXcxMemberBindExample;
import com.jf.entity.WeixinXcxMemberBindExt;
import com.jf.entity.WeixinXcxMemberBindExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinXcxMemberBindBiz extends BaseService<WeixinXcxMemberBind, WeixinXcxMemberBindExample> {

	@Autowired
	private WeixinXcxMemberBindMapper dao;
	@Autowired
	private WeixinXcxMemberBindExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinXcxMemberBindMapper(WeixinXcxMemberBindMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinXcxMemberBindExt save(WeixinXcxMemberBindExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinXcxMemberBind update(WeixinXcxMemberBind model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinXcxMemberBind model = new WeixinXcxMemberBind();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinXcxMemberBindExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinXcxMemberBindExt find(WeixinXcxMemberBindExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinXcxMemberBindExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinXcxMemberBindExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinXcxMemberBindExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinXcxMemberBindExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinXcxMemberBindExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinXcxMemberBindExt> list(WeixinXcxMemberBindExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinXcxMemberBindExt> paginate(WeixinXcxMemberBindExtExample example) {
		List<WeixinXcxMemberBindExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
