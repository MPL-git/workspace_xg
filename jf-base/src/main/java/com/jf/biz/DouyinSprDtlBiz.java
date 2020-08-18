package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DouyinSprDtlExtMapper;
import com.jf.dao.DouyinSprDtlMapper;
import com.jf.entity.DouyinSprDtl;
import com.jf.entity.DouyinSprDtlExample;
import com.jf.entity.DouyinSprDtlExt;
import com.jf.entity.DouyinSprDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DouyinSprDtlBiz extends BaseService<DouyinSprDtl, DouyinSprDtlExample> {

	@Autowired
	private DouyinSprDtlMapper dao;
	@Autowired
	private DouyinSprDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDouyinSprDtlMapper(DouyinSprDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DouyinSprDtlExt save(DouyinSprDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DouyinSprDtl update(DouyinSprDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DouyinSprDtl model = new DouyinSprDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DouyinSprDtlExt findById(int id){
		return extDao.findById(id);
	}

	public DouyinSprDtlExt find(DouyinSprDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DouyinSprDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DouyinSprDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DouyinSprDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DouyinSprDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DouyinSprDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DouyinSprDtlExt> list(DouyinSprDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DouyinSprDtlExt> paginate(DouyinSprDtlExtExample example) {
		List<DouyinSprDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
