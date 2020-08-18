package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FullDiscountExtMapper;
import com.jf.dao.FullDiscountMapper;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;
import com.jf.entity.FullDiscountExt;
import com.jf.entity.FullDiscountExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FullDiscountBiz extends BaseService<FullDiscount, FullDiscountExample> {

	@Autowired
	private FullDiscountMapper dao;
	@Autowired
	private FullDiscountExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setFullDiscountMapper(FullDiscountMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public FullDiscountExt save(FullDiscountExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public FullDiscount update(FullDiscount model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		FullDiscount model = new FullDiscount();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public FullDiscountExt findById(int id){
		return extDao.findById(id);
	}

	public FullDiscountExt find(FullDiscountExtExample example){
		return extDao.find(example.fill());
	}

	public int count(FullDiscountExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, FullDiscountExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, FullDiscountExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, FullDiscountExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(FullDiscountExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<FullDiscountExt> list(FullDiscountExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<FullDiscountExt> paginate(FullDiscountExtExample example) {
		List<FullDiscountExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
