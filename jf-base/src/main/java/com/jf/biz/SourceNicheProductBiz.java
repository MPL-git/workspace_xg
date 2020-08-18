package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SourceNicheProductExtMapper;
import com.jf.dao.SourceNicheProductMapper;
import com.jf.entity.SourceNicheProduct;
import com.jf.entity.SourceNicheProductExample;
import com.jf.entity.SourceNicheProductExt;
import com.jf.entity.SourceNicheProductExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SourceNicheProductBiz extends BaseService<SourceNicheProduct, SourceNicheProductExample> {

	@Autowired
	private SourceNicheProductMapper dao;
	@Autowired
	private SourceNicheProductExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSourceNicheProductMapper(SourceNicheProductMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SourceNicheProductExt save(SourceNicheProductExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SourceNicheProduct update(SourceNicheProduct model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SourceNicheProduct model = new SourceNicheProduct();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SourceNicheProductExt findById(int id){
		return extDao.findById(id);
	}

	public SourceNicheProductExt find(SourceNicheProductExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SourceNicheProductExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SourceNicheProductExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SourceNicheProductExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SourceNicheProductExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SourceNicheProductExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SourceNicheProductExt> list(SourceNicheProductExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SourceNicheProductExt> paginate(SourceNicheProductExtExample example) {
		List<SourceNicheProductExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
