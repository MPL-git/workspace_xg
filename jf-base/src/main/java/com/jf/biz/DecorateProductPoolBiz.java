package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DecorateProductPoolExtMapper;
import com.jf.dao.DecorateProductPoolMapper;
import com.jf.entity.DecorateProductPool;
import com.jf.entity.DecorateProductPoolExample;
import com.jf.entity.DecorateProductPoolExt;
import com.jf.entity.DecorateProductPoolExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DecorateProductPoolBiz extends BaseService<DecorateProductPool, DecorateProductPoolExample> {

	@Autowired
	private DecorateProductPoolMapper dao;
	@Autowired
	private DecorateProductPoolExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDecorateProductPoolMapper(DecorateProductPoolMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DecorateProductPoolExt save(DecorateProductPoolExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DecorateProductPool update(DecorateProductPool model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DecorateProductPool model = new DecorateProductPool();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DecorateProductPoolExt findById(int id){
		return extDao.findById(id);
	}

	public DecorateProductPoolExt find(DecorateProductPoolExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DecorateProductPoolExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DecorateProductPoolExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DecorateProductPoolExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DecorateProductPoolExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DecorateProductPoolExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DecorateProductPoolExt> list(DecorateProductPoolExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DecorateProductPoolExt> paginate(DecorateProductPoolExtExample example) {
		List<DecorateProductPoolExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
