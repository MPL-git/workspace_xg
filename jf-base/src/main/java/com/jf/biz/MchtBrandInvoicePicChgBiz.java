package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandInvoicePicChgExtMapper;
import com.jf.dao.MchtBrandInvoicePicChgMapper;
import com.jf.entity.MchtBrandInvoicePicChg;
import com.jf.entity.MchtBrandInvoicePicChgExample;
import com.jf.entity.MchtBrandInvoicePicChgExt;
import com.jf.entity.MchtBrandInvoicePicChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandInvoicePicChgBiz extends BaseService<MchtBrandInvoicePicChg, MchtBrandInvoicePicChgExample> {

	@Autowired
	private MchtBrandInvoicePicChgMapper dao;
	@Autowired
	private MchtBrandInvoicePicChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandInvoicePicChgMapper(MchtBrandInvoicePicChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandInvoicePicChgExt save(MchtBrandInvoicePicChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandInvoicePicChg update(MchtBrandInvoicePicChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandInvoicePicChg model = new MchtBrandInvoicePicChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandInvoicePicChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandInvoicePicChgExt find(MchtBrandInvoicePicChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandInvoicePicChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandInvoicePicChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandInvoicePicChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandInvoicePicChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandInvoicePicChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandInvoicePicChgExt> list(MchtBrandInvoicePicChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandInvoicePicChgExt> paginate(MchtBrandInvoicePicChgExtExample example) {
		List<MchtBrandInvoicePicChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
