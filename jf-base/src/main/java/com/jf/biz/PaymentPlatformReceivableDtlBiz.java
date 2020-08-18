package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PaymentPlatformReceivableDtlExtMapper;
import com.jf.dao.PaymentPlatformReceivableDtlMapper;
import com.jf.entity.PaymentPlatformReceivableDtl;
import com.jf.entity.PaymentPlatformReceivableDtlExample;
import com.jf.entity.PaymentPlatformReceivableDtlExt;
import com.jf.entity.PaymentPlatformReceivableDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentPlatformReceivableDtlBiz extends BaseService<PaymentPlatformReceivableDtl, PaymentPlatformReceivableDtlExample> {

	@Autowired
	private PaymentPlatformReceivableDtlMapper dao;
	@Autowired
	private PaymentPlatformReceivableDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPaymentPlatformReceivableDtlMapper(PaymentPlatformReceivableDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PaymentPlatformReceivableDtlExt save(PaymentPlatformReceivableDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PaymentPlatformReceivableDtl update(PaymentPlatformReceivableDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PaymentPlatformReceivableDtl model = new PaymentPlatformReceivableDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PaymentPlatformReceivableDtlExt findById(int id){
		return extDao.findById(id);
	}

	public PaymentPlatformReceivableDtlExt find(PaymentPlatformReceivableDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PaymentPlatformReceivableDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PaymentPlatformReceivableDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PaymentPlatformReceivableDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PaymentPlatformReceivableDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PaymentPlatformReceivableDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PaymentPlatformReceivableDtlExt> list(PaymentPlatformReceivableDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PaymentPlatformReceivableDtlExt> paginate(PaymentPlatformReceivableDtlExtExample example) {
		List<PaymentPlatformReceivableDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
