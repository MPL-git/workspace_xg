package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinGzhMemberBindExtMapper;
import com.jf.dao.WeixinGzhMemberBindMapper;
import com.jf.entity.WeixinGzhMemberBind;
import com.jf.entity.WeixinGzhMemberBindExample;
import com.jf.entity.WeixinGzhMemberBindExt;
import com.jf.entity.WeixinGzhMemberBindExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinGzhMemberBindBiz extends BaseService<WeixinGzhMemberBind, WeixinGzhMemberBindExample> {

	@Autowired
	private WeixinGzhMemberBindMapper dao;
	@Autowired
	private WeixinGzhMemberBindExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinGzhMemberBindMapper(WeixinGzhMemberBindMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinGzhMemberBindExt save(WeixinGzhMemberBindExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinGzhMemberBind update(WeixinGzhMemberBind model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinGzhMemberBind model = new WeixinGzhMemberBind();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinGzhMemberBindExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinGzhMemberBindExt find(WeixinGzhMemberBindExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinGzhMemberBindExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinGzhMemberBindExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinGzhMemberBindExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinGzhMemberBindExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinGzhMemberBindExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinGzhMemberBindExt> list(WeixinGzhMemberBindExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinGzhMemberBindExt> paginate(WeixinGzhMemberBindExtExample example) {
		List<WeixinGzhMemberBindExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
