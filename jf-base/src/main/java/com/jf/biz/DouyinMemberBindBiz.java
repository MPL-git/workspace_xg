package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DouyinMemberBindExtMapper;
import com.jf.dao.DouyinMemberBindMapper;
import com.jf.entity.DouyinMemberBind;
import com.jf.entity.DouyinMemberBindExample;
import com.jf.entity.DouyinMemberBindExt;
import com.jf.entity.DouyinMemberBindExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DouyinMemberBindBiz extends BaseService<DouyinMemberBind, DouyinMemberBindExample> {

	@Autowired
	private DouyinMemberBindMapper dao;
	@Autowired
	private DouyinMemberBindExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDouyinMemberBindMapper(DouyinMemberBindMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DouyinMemberBindExt save(DouyinMemberBindExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DouyinMemberBind update(DouyinMemberBind model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DouyinMemberBind model = new DouyinMemberBind();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DouyinMemberBindExt findById(int id){
		return extDao.findById(id);
	}

	public DouyinMemberBindExt find(DouyinMemberBindExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DouyinMemberBindExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DouyinMemberBindExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DouyinMemberBindExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DouyinMemberBindExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DouyinMemberBindExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DouyinMemberBindExt> list(DouyinMemberBindExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DouyinMemberBindExt> paginate(DouyinMemberBindExtExample example) {
		List<DouyinMemberBindExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
