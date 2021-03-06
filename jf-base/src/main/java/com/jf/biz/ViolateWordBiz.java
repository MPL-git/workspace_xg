package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.ViolateWordMapper;
import com.jf.dao.ViolateWordExtMapper;
import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;
import com.jf.entity.ViolateWordExt;
import com.jf.entity.ViolateWordExtExample;
import com.jf.common.base.BaseService;

@Service
public class ViolateWordBiz extends BaseService<ViolateWord, ViolateWordExample> {

	@Autowired
	private ViolateWordMapper dao;
	@Autowired
	private ViolateWordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setViolateWordMapper(ViolateWordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ViolateWordExt save(ViolateWordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ViolateWord update(ViolateWord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ViolateWord model = new ViolateWord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ViolateWordExt findById(int id){
		return extDao.findById(id);
	}

	public ViolateWordExt find(ViolateWordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ViolateWordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ViolateWordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ViolateWordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ViolateWordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ViolateWordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ViolateWordExt> list(ViolateWordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ViolateWordExt> paginate(ViolateWordExtExample example) {
		List<ViolateWordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
