package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtTaxInvoiceInfoChgExtMapper;
import com.jf.dao.MchtTaxInvoiceInfoChgMapper;
import com.jf.entity.MchtTaxInvoiceInfoChg;
import com.jf.entity.MchtTaxInvoiceInfoChgExample;
import com.jf.entity.MchtTaxInvoiceInfoChgExt;
import com.jf.entity.MchtTaxInvoiceInfoChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtTaxInvoiceInfoChgBiz extends BaseService<MchtTaxInvoiceInfoChg, MchtTaxInvoiceInfoChgExample> {

	@Autowired
	private MchtTaxInvoiceInfoChgMapper dao;
	@Autowired
	private MchtTaxInvoiceInfoChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtTaxInvoiceInfoChgMapper(MchtTaxInvoiceInfoChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtTaxInvoiceInfoChgExt save(MchtTaxInvoiceInfoChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtTaxInvoiceInfoChg update(MchtTaxInvoiceInfoChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtTaxInvoiceInfoChg model = new MchtTaxInvoiceInfoChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtTaxInvoiceInfoChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtTaxInvoiceInfoChgExt find(MchtTaxInvoiceInfoChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtTaxInvoiceInfoChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtTaxInvoiceInfoChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtTaxInvoiceInfoChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtTaxInvoiceInfoChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtTaxInvoiceInfoChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtTaxInvoiceInfoChgExt> list(MchtTaxInvoiceInfoChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtTaxInvoiceInfoChgExt> paginate(MchtTaxInvoiceInfoChgExtExample example) {
		List<MchtTaxInvoiceInfoChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
