package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DecorateAreaExtMapper;
import com.jf.dao.DecorateAreaMapper;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaExample;
import com.jf.entity.DecorateAreaExt;
import com.jf.entity.DecorateAreaExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DecorateAreaBiz extends BaseService<DecorateArea, DecorateAreaExample> {

	@Autowired
	private DecorateAreaMapper dao;
	@Autowired
	private DecorateAreaExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDecorateAreaMapper(DecorateAreaMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DecorateAreaExt save(DecorateAreaExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DecorateArea update(DecorateArea model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DecorateArea model = new DecorateArea();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DecorateAreaExt findById(int id){
		return extDao.findById(id);
	}

	public DecorateAreaExt find(DecorateAreaExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DecorateAreaExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DecorateAreaExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DecorateAreaExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DecorateAreaExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DecorateAreaExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DecorateAreaExt> list(DecorateAreaExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DecorateAreaExt> paginate(DecorateAreaExtExample example) {
		List<DecorateAreaExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
