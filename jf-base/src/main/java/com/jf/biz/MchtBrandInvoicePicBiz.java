package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandInvoicePicExtMapper;
import com.jf.dao.MchtBrandInvoicePicMapper;
import com.jf.entity.MchtBrandInvoicePic;
import com.jf.entity.MchtBrandInvoicePicExample;
import com.jf.entity.MchtBrandInvoicePicExt;
import com.jf.entity.MchtBrandInvoicePicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandInvoicePicBiz extends BaseService<MchtBrandInvoicePic, MchtBrandInvoicePicExample> {

	@Autowired
	private MchtBrandInvoicePicMapper dao;
	@Autowired
	private MchtBrandInvoicePicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandInvoicePicMapper(MchtBrandInvoicePicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandInvoicePicExt save(MchtBrandInvoicePicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandInvoicePic update(MchtBrandInvoicePic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandInvoicePic model = new MchtBrandInvoicePic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandInvoicePicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandInvoicePicExt find(MchtBrandInvoicePicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandInvoicePicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandInvoicePicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandInvoicePicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandInvoicePicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandInvoicePicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandInvoicePicExt> list(MchtBrandInvoicePicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandInvoicePicExt> paginate(MchtBrandInvoicePicExtExample example) {
		List<MchtBrandInvoicePicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
