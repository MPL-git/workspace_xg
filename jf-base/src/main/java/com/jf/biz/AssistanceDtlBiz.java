package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AssistanceDtlExtMapper;
import com.jf.dao.AssistanceDtlMapper;
import com.jf.entity.AssistanceDtl;
import com.jf.entity.AssistanceDtlExample;
import com.jf.entity.AssistanceDtlExt;
import com.jf.entity.AssistanceDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AssistanceDtlBiz extends BaseService<AssistanceDtl, AssistanceDtlExample> {

	@Autowired
	private AssistanceDtlMapper dao;
	@Autowired
	private AssistanceDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAssistanceDtlMapper(AssistanceDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AssistanceDtlExt save(AssistanceDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AssistanceDtl update(AssistanceDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AssistanceDtl model = new AssistanceDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AssistanceDtlExt findById(int id){
		return extDao.findById(id);
	}

	public AssistanceDtlExt find(AssistanceDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AssistanceDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AssistanceDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AssistanceDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AssistanceDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AssistanceDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AssistanceDtlExt> list(AssistanceDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AssistanceDtlExt> paginate(AssistanceDtlExtExample example) {
		List<AssistanceDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
