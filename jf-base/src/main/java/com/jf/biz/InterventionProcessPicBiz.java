package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.InterventionProcessPicExtMapper;
import com.jf.dao.InterventionProcessPicMapper;
import com.jf.entity.InterventionProcessPic;
import com.jf.entity.InterventionProcessPicExample;
import com.jf.entity.InterventionProcessPicExt;
import com.jf.entity.InterventionProcessPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InterventionProcessPicBiz extends BaseService<InterventionProcessPic, InterventionProcessPicExample> {

	@Autowired
	private InterventionProcessPicMapper dao;
	@Autowired
	private InterventionProcessPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setInterventionProcessPicMapper(InterventionProcessPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public InterventionProcessPicExt save(InterventionProcessPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public InterventionProcessPic update(InterventionProcessPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		InterventionProcessPic model = new InterventionProcessPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public InterventionProcessPicExt findById(int id){
		return extDao.findById(id);
	}

	public InterventionProcessPicExt find(InterventionProcessPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(InterventionProcessPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, InterventionProcessPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, InterventionProcessPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, InterventionProcessPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(InterventionProcessPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<InterventionProcessPicExt> list(InterventionProcessPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<InterventionProcessPicExt> paginate(InterventionProcessPicExtExample example) {
		List<InterventionProcessPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
