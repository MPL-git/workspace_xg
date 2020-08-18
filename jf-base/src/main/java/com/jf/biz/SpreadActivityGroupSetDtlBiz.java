package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SpreadActivityGroupSetDtlExtMapper;
import com.jf.dao.SpreadActivityGroupSetDtlMapper;
import com.jf.entity.SpreadActivityGroupSetDtl;
import com.jf.entity.SpreadActivityGroupSetDtlExample;
import com.jf.entity.SpreadActivityGroupSetDtlExt;
import com.jf.entity.SpreadActivityGroupSetDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpreadActivityGroupSetDtlBiz extends BaseService<SpreadActivityGroupSetDtl, SpreadActivityGroupSetDtlExample> {

	@Autowired
	private SpreadActivityGroupSetDtlMapper dao;
	@Autowired
	private SpreadActivityGroupSetDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSpreadActivityGroupSetDtlMapper(SpreadActivityGroupSetDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SpreadActivityGroupSetDtlExt save(SpreadActivityGroupSetDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SpreadActivityGroupSetDtl update(SpreadActivityGroupSetDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SpreadActivityGroupSetDtl model = new SpreadActivityGroupSetDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SpreadActivityGroupSetDtlExt findById(int id){
		return extDao.findById(id);
	}

	public SpreadActivityGroupSetDtlExt find(SpreadActivityGroupSetDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SpreadActivityGroupSetDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SpreadActivityGroupSetDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SpreadActivityGroupSetDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SpreadActivityGroupSetDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SpreadActivityGroupSetDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SpreadActivityGroupSetDtlExt> list(SpreadActivityGroupSetDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SpreadActivityGroupSetDtlExt> paginate(SpreadActivityGroupSetDtlExtExample example) {
		List<SpreadActivityGroupSetDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
