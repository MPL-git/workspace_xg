package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtTaxInvoiceInfoExtMapper;
import com.jf.dao.MchtTaxInvoiceInfoMapper;
import com.jf.entity.MchtTaxInvoiceInfo;
import com.jf.entity.MchtTaxInvoiceInfoExample;
import com.jf.entity.MchtTaxInvoiceInfoExt;
import com.jf.entity.MchtTaxInvoiceInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtTaxInvoiceInfoBiz extends BaseService<MchtTaxInvoiceInfo, MchtTaxInvoiceInfoExample> {

	@Autowired
	private MchtTaxInvoiceInfoMapper dao;
	@Autowired
	private MchtTaxInvoiceInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtTaxInvoiceInfoMapper(MchtTaxInvoiceInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtTaxInvoiceInfoExt save(MchtTaxInvoiceInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtTaxInvoiceInfo update(MchtTaxInvoiceInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtTaxInvoiceInfo model = new MchtTaxInvoiceInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtTaxInvoiceInfoExt findById(int id){
		return extDao.findById(id);
	}

	public MchtTaxInvoiceInfoExt find(MchtTaxInvoiceInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtTaxInvoiceInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtTaxInvoiceInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtTaxInvoiceInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtTaxInvoiceInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtTaxInvoiceInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtTaxInvoiceInfoExt> list(MchtTaxInvoiceInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtTaxInvoiceInfoExt> paginate(MchtTaxInvoiceInfoExtExample example) {
		List<MchtTaxInvoiceInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
