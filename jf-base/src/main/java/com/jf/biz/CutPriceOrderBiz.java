package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CutPriceOrderExtMapper;
import com.jf.dao.CutPriceOrderMapper;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderExample;
import com.jf.entity.CutPriceOrderExt;
import com.jf.entity.CutPriceOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CutPriceOrderBiz extends BaseService<CutPriceOrder, CutPriceOrderExample> {

	@Autowired
	private CutPriceOrderMapper dao;
	@Autowired
	private CutPriceOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCutPriceOrderMapper(CutPriceOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CutPriceOrderExt save(CutPriceOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CutPriceOrder update(CutPriceOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CutPriceOrder model = new CutPriceOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CutPriceOrderExt findById(int id){
		return extDao.findById(id);
	}

	public CutPriceOrderExt find(CutPriceOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CutPriceOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CutPriceOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CutPriceOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CutPriceOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CutPriceOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CutPriceOrderExt> list(CutPriceOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CutPriceOrderExt> paginate(CutPriceOrderExtExample example) {
		List<CutPriceOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
