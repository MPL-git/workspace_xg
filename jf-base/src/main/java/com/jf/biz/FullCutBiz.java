package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FullCutExtMapper;
import com.jf.dao.FullCutMapper;
import com.jf.entity.FullCut;
import com.jf.entity.FullCutExample;
import com.jf.entity.FullCutExt;
import com.jf.entity.FullCutExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FullCutBiz extends BaseService<FullCut, FullCutExample> {

	@Autowired
	private FullCutMapper dao;
	@Autowired
	private FullCutExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setFullCutMapper(FullCutMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public FullCutExt save(FullCutExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public FullCut update(FullCut model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		FullCut model = new FullCut();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public FullCutExt findById(int id){
		return extDao.findById(id);
	}

	public FullCutExt find(FullCutExtExample example){
		return extDao.find(example.fill());
	}

	public int count(FullCutExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, FullCutExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, FullCutExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, FullCutExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(FullCutExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<FullCutExt> list(FullCutExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<FullCutExt> paginate(FullCutExtExample example) {
		List<FullCutExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
