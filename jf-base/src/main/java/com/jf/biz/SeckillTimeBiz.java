package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SeckillTimeExtMapper;
import com.jf.dao.SeckillTimeMapper;
import com.jf.entity.SeckillTime;
import com.jf.entity.SeckillTimeExample;
import com.jf.entity.SeckillTimeExt;
import com.jf.entity.SeckillTimeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeckillTimeBiz extends BaseService<SeckillTime, SeckillTimeExample> {

	@Autowired
	private SeckillTimeMapper dao;
	@Autowired
	private SeckillTimeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSeckillTimeMapper(SeckillTimeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SeckillTimeExt save(SeckillTimeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SeckillTime update(SeckillTime model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SeckillTime model = new SeckillTime();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SeckillTimeExt findById(int id){
		return extDao.findById(id);
	}

	public SeckillTimeExt find(SeckillTimeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SeckillTimeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SeckillTimeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SeckillTimeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SeckillTimeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SeckillTimeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SeckillTimeExt> list(SeckillTimeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SeckillTimeExt> paginate(SeckillTimeExtExample example) {
		List<SeckillTimeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
