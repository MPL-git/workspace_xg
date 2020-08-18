package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CutPriceCnfTplExtMapper;
import com.jf.dao.CutPriceCnfTplMapper;
import com.jf.entity.CutPriceCnfTpl;
import com.jf.entity.CutPriceCnfTplExample;
import com.jf.entity.CutPriceCnfTplExt;
import com.jf.entity.CutPriceCnfTplExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CutPriceCnfTplBiz extends BaseService<CutPriceCnfTpl, CutPriceCnfTplExample> {

	@Autowired
	private CutPriceCnfTplMapper dao;
	@Autowired
	private CutPriceCnfTplExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCutPriceCnfTplMapper(CutPriceCnfTplMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CutPriceCnfTplExt save(CutPriceCnfTplExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CutPriceCnfTpl update(CutPriceCnfTpl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CutPriceCnfTpl model = new CutPriceCnfTpl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CutPriceCnfTplExt findById(int id){
		return extDao.findById(id);
	}

	public CutPriceCnfTplExt find(CutPriceCnfTplExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CutPriceCnfTplExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CutPriceCnfTplExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CutPriceCnfTplExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CutPriceCnfTplExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CutPriceCnfTplExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CutPriceCnfTplExt> list(CutPriceCnfTplExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CutPriceCnfTplExt> paginate(CutPriceCnfTplExtExample example) {
		List<CutPriceCnfTplExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
