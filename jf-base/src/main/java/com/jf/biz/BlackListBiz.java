package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BlackListExtMapper;
import com.jf.dao.BlackListMapper;
import com.jf.entity.BlackList;
import com.jf.entity.BlackListExample;
import com.jf.entity.BlackListExt;
import com.jf.entity.BlackListExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlackListBiz extends BaseService<BlackList, BlackListExample> {

	@Autowired
	private BlackListMapper dao;
	@Autowired
	private BlackListExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBlackListMapper(BlackListMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BlackListExt save(BlackListExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BlackList update(BlackList model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BlackList model = new BlackList();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BlackListExt findById(int id){
		return extDao.findById(id);
	}

	public BlackListExt find(BlackListExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BlackListExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BlackListExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BlackListExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BlackListExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BlackListExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BlackListExt> list(BlackListExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BlackListExt> paginate(BlackListExtExample example) {
		List<BlackListExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
