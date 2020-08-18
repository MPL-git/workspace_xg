package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CutPriceCnfDtlExtMapper;
import com.jf.dao.CutPriceCnfDtlMapper;
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceCnfDtlExample;
import com.jf.entity.CutPriceCnfDtlExt;
import com.jf.entity.CutPriceCnfDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CutPriceCnfDtlBiz extends BaseService<CutPriceCnfDtl, CutPriceCnfDtlExample> {

	@Autowired
	private CutPriceCnfDtlMapper dao;
	@Autowired
	private CutPriceCnfDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCutPriceCnfDtlMapper(CutPriceCnfDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CutPriceCnfDtlExt save(CutPriceCnfDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CutPriceCnfDtl update(CutPriceCnfDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CutPriceCnfDtl model = new CutPriceCnfDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CutPriceCnfDtlExt findById(int id){
		return extDao.findById(id);
	}

	public CutPriceCnfDtlExt find(CutPriceCnfDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CutPriceCnfDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CutPriceCnfDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CutPriceCnfDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CutPriceCnfDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CutPriceCnfDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CutPriceCnfDtlExt> list(CutPriceCnfDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CutPriceCnfDtlExt> paginate(CutPriceCnfDtlExtExample example) {
		List<CutPriceCnfDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
