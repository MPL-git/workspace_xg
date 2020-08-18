package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CutPriceCnfExtMapper;
import com.jf.dao.CutPriceCnfMapper;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfExample;
import com.jf.entity.CutPriceCnfExt;
import com.jf.entity.CutPriceCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CutPriceCnfBiz extends BaseService<CutPriceCnf, CutPriceCnfExample> {

	@Autowired
	private CutPriceCnfMapper dao;
	@Autowired
	private CutPriceCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCutPriceCnfMapper(CutPriceCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CutPriceCnfExt save(CutPriceCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CutPriceCnf update(CutPriceCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CutPriceCnf model = new CutPriceCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CutPriceCnfExt findById(int id){
		return extDao.findById(id);
	}

	public CutPriceCnfExt find(CutPriceCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CutPriceCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CutPriceCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CutPriceCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CutPriceCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CutPriceCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CutPriceCnfExt> list(CutPriceCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CutPriceCnfExt> paginate(CutPriceCnfExtExample example) {
		List<CutPriceCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
