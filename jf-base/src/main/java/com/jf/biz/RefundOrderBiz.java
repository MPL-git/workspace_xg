package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.RefundOrderExtMapper;
import com.jf.dao.RefundOrderMapper;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;
import com.jf.entity.RefundOrderExt;
import com.jf.entity.RefundOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RefundOrderBiz extends BaseService<RefundOrder, RefundOrderExample> {

	@Autowired
	private RefundOrderMapper dao;
	@Autowired
	private RefundOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setRefundOrderMapper(RefundOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public RefundOrderExt save(RefundOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public RefundOrder update(RefundOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		RefundOrder model = new RefundOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public RefundOrderExt findById(int id){
		return extDao.findById(id);
	}

	public RefundOrderExt find(RefundOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(RefundOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, RefundOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, RefundOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, RefundOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(RefundOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<RefundOrderExt> list(RefundOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<RefundOrderExt> paginate(RefundOrderExtExample example) {
		List<RefundOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
