package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ZsProductTypeExtMapper;
import com.jf.dao.ZsProductTypeMapper;
import com.jf.entity.ZsProductType;
import com.jf.entity.ZsProductTypeExample;
import com.jf.entity.ZsProductTypeExt;
import com.jf.entity.ZsProductTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZsProductTypeBiz extends BaseService<ZsProductType, ZsProductTypeExample> {

	@Autowired
	private ZsProductTypeMapper dao;
	@Autowired
	private ZsProductTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setZsProductTypeMapper(ZsProductTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ZsProductTypeExt save(ZsProductTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ZsProductType update(ZsProductType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ZsProductType model = new ZsProductType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ZsProductTypeExt findById(int id){
		return extDao.findById(id);
	}

	public ZsProductTypeExt find(ZsProductTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ZsProductTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ZsProductTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ZsProductTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ZsProductTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ZsProductTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ZsProductTypeExt> list(ZsProductTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ZsProductTypeExt> paginate(ZsProductTypeExtExample example) {
		List<ZsProductTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
