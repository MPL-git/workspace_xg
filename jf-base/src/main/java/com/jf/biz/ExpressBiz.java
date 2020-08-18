package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ExpressExtMapper;
import com.jf.dao.ExpressMapper;
import com.jf.entity.Express;
import com.jf.entity.ExpressExample;
import com.jf.entity.ExpressExt;
import com.jf.entity.ExpressExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpressBiz extends BaseService<Express, ExpressExample> {

	@Autowired
	private ExpressMapper dao;
	@Autowired
	private ExpressExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setExpressMapper(ExpressMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ExpressExt save(ExpressExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Express update(Express model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Express model = new Express();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ExpressExt findById(int id){
		return extDao.findById(id);
	}

	public ExpressExt find(ExpressExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ExpressExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ExpressExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ExpressExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ExpressExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ExpressExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ExpressExt> list(ExpressExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ExpressExt> paginate(ExpressExtExample example) {
		List<ExpressExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
