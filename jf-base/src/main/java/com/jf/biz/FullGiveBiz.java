package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FullGiveExtMapper;
import com.jf.dao.FullGiveMapper;
import com.jf.entity.FullGive;
import com.jf.entity.FullGiveExample;
import com.jf.entity.FullGiveExt;
import com.jf.entity.FullGiveExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FullGiveBiz extends BaseService<FullGive, FullGiveExample> {

	@Autowired
	private FullGiveMapper dao;
	@Autowired
	private FullGiveExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setFullGiveMapper(FullGiveMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public FullGiveExt save(FullGiveExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public FullGive update(FullGive model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		FullGive model = new FullGive();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public FullGiveExt findById(int id){
		return extDao.findById(id);
	}

	public FullGiveExt find(FullGiveExtExample example){
		return extDao.find(example.fill());
	}

	public int count(FullGiveExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, FullGiveExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, FullGiveExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, FullGiveExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(FullGiveExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<FullGiveExt> list(FullGiveExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<FullGiveExt> paginate(FullGiveExtExample example) {
		List<FullGiveExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
