package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DeliveryOvertimeCnfExtMapper;
import com.jf.dao.DeliveryOvertimeCnfMapper;
import com.jf.entity.DeliveryOvertimeCnf;
import com.jf.entity.DeliveryOvertimeCnfExample;
import com.jf.entity.DeliveryOvertimeCnfExt;
import com.jf.entity.DeliveryOvertimeCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeliveryOvertimeCnfBiz extends BaseService<DeliveryOvertimeCnf, DeliveryOvertimeCnfExample> {

	@Autowired
	private DeliveryOvertimeCnfMapper dao;
	@Autowired
	private DeliveryOvertimeCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDeliveryOvertimeCnfMapper(DeliveryOvertimeCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DeliveryOvertimeCnfExt save(DeliveryOvertimeCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DeliveryOvertimeCnf update(DeliveryOvertimeCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DeliveryOvertimeCnf model = new DeliveryOvertimeCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DeliveryOvertimeCnfExt findById(int id){
		return extDao.findById(id);
	}

	public DeliveryOvertimeCnfExt find(DeliveryOvertimeCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DeliveryOvertimeCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DeliveryOvertimeCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DeliveryOvertimeCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DeliveryOvertimeCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DeliveryOvertimeCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DeliveryOvertimeCnfExt> list(DeliveryOvertimeCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DeliveryOvertimeCnfExt> paginate(DeliveryOvertimeCnfExtExample example) {
		List<DeliveryOvertimeCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
