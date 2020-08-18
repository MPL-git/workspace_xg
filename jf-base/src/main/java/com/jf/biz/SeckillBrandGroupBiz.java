package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SeckillBrandGroupExtMapper;
import com.jf.dao.SeckillBrandGroupMapper;
import com.jf.entity.SeckillBrandGroup;
import com.jf.entity.SeckillBrandGroupExample;
import com.jf.entity.SeckillBrandGroupExt;
import com.jf.entity.SeckillBrandGroupExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeckillBrandGroupBiz extends BaseService<SeckillBrandGroup, SeckillBrandGroupExample> {

	@Autowired
	private SeckillBrandGroupMapper dao;
	@Autowired
	private SeckillBrandGroupExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSeckillBrandGroupMapper(SeckillBrandGroupMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SeckillBrandGroupExt save(SeckillBrandGroupExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SeckillBrandGroup update(SeckillBrandGroup model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SeckillBrandGroup model = new SeckillBrandGroup();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SeckillBrandGroupExt findById(int id){
		return extDao.findById(id);
	}

	public SeckillBrandGroupExt find(SeckillBrandGroupExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SeckillBrandGroupExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SeckillBrandGroupExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SeckillBrandGroupExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SeckillBrandGroupExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SeckillBrandGroupExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SeckillBrandGroupExt> list(SeckillBrandGroupExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SeckillBrandGroupExt> paginate(SeckillBrandGroupExtExample example) {
		List<SeckillBrandGroupExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
