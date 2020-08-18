package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SaleWeightCnfExtMapper;
import com.jf.dao.SaleWeightCnfMapper;
import com.jf.entity.SaleWeightCnf;
import com.jf.entity.SaleWeightCnfExample;
import com.jf.entity.SaleWeightCnfExt;
import com.jf.entity.SaleWeightCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleWeightCnfBiz extends BaseService<SaleWeightCnf, SaleWeightCnfExample> {

	@Autowired
	private SaleWeightCnfMapper dao;
	@Autowired
	private SaleWeightCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSaleWeightCnfMapper(SaleWeightCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SaleWeightCnfExt save(SaleWeightCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SaleWeightCnf update(SaleWeightCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SaleWeightCnf model = new SaleWeightCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SaleWeightCnfExt findById(int id){
		return extDao.findById(id);
	}

	public SaleWeightCnfExt find(SaleWeightCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SaleWeightCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SaleWeightCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SaleWeightCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SaleWeightCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SaleWeightCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SaleWeightCnfExt> list(SaleWeightCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SaleWeightCnfExt> paginate(SaleWeightCnfExtExample example) {
		List<SaleWeightCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
