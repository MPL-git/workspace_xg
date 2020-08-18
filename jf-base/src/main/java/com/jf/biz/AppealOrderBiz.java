package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AppealOrderExtMapper;
import com.jf.dao.AppealOrderMapper;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderExample;
import com.jf.entity.AppealOrderExt;
import com.jf.entity.AppealOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppealOrderBiz extends BaseService<AppealOrder, AppealOrderExample> {

	@Autowired
	private AppealOrderMapper dao;
	@Autowired
	private AppealOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAppealOrderMapper(AppealOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AppealOrderExt save(AppealOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AppealOrder update(AppealOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AppealOrder model = new AppealOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AppealOrderExt findById(int id){
		return extDao.findById(id);
	}

	public AppealOrderExt find(AppealOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AppealOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AppealOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AppealOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AppealOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AppealOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AppealOrderExt> list(AppealOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AppealOrderExt> paginate(AppealOrderExtExample example) {
		List<AppealOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
