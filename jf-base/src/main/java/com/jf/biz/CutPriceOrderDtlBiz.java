package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CutPriceOrderDtlExtMapper;
import com.jf.dao.CutPriceOrderDtlMapper;
import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlExample;
import com.jf.entity.CutPriceOrderDtlExt;
import com.jf.entity.CutPriceOrderDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CutPriceOrderDtlBiz extends BaseService<CutPriceOrderDtl, CutPriceOrderDtlExample> {

	@Autowired
	private CutPriceOrderDtlMapper dao;
	@Autowired
	private CutPriceOrderDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCutPriceOrderDtlMapper(CutPriceOrderDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CutPriceOrderDtlExt save(CutPriceOrderDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CutPriceOrderDtl update(CutPriceOrderDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CutPriceOrderDtl model = new CutPriceOrderDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CutPriceOrderDtlExt findById(int id){
		return extDao.findById(id);
	}

	public CutPriceOrderDtlExt find(CutPriceOrderDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CutPriceOrderDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CutPriceOrderDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CutPriceOrderDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CutPriceOrderDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CutPriceOrderDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CutPriceOrderDtlExt> list(CutPriceOrderDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CutPriceOrderDtlExt> paginate(CutPriceOrderDtlExtExample example) {
		List<CutPriceOrderDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
