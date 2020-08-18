package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.TaobaoProductTypePageNoExtMapper;
import com.jf.dao.TaobaoProductTypePageNoMapper;
import com.jf.entity.TaobaoProductTypePageNo;
import com.jf.entity.TaobaoProductTypePageNoExample;
import com.jf.entity.TaobaoProductTypePageNoExt;
import com.jf.entity.TaobaoProductTypePageNoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaobaoProductTypePageNoBiz extends BaseService<TaobaoProductTypePageNo, TaobaoProductTypePageNoExample> {

	@Autowired
	private TaobaoProductTypePageNoMapper dao;
	@Autowired
	private TaobaoProductTypePageNoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTaobaoProductTypePageNoMapper(TaobaoProductTypePageNoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TaobaoProductTypePageNoExt save(TaobaoProductTypePageNoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public TaobaoProductTypePageNo update(TaobaoProductTypePageNo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		TaobaoProductTypePageNo model = new TaobaoProductTypePageNo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TaobaoProductTypePageNoExt findById(int id){
		return extDao.findById(id);
	}

	public TaobaoProductTypePageNoExt find(TaobaoProductTypePageNoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TaobaoProductTypePageNoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TaobaoProductTypePageNoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TaobaoProductTypePageNoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TaobaoProductTypePageNoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TaobaoProductTypePageNoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TaobaoProductTypePageNoExt> list(TaobaoProductTypePageNoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TaobaoProductTypePageNoExt> paginate(TaobaoProductTypePageNoExtExample example) {
		List<TaobaoProductTypePageNoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
