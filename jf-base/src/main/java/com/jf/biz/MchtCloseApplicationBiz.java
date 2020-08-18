package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtCloseApplicationExtMapper;
import com.jf.dao.MchtCloseApplicationMapper;
import com.jf.entity.MchtCloseApplication;
import com.jf.entity.MchtCloseApplicationExample;
import com.jf.entity.MchtCloseApplicationExt;
import com.jf.entity.MchtCloseApplicationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtCloseApplicationBiz extends BaseService<MchtCloseApplication, MchtCloseApplicationExample> {

	@Autowired
	private MchtCloseApplicationMapper dao;
	@Autowired
	private MchtCloseApplicationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtCloseApplicationMapper(MchtCloseApplicationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtCloseApplicationExt save(MchtCloseApplicationExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtCloseApplication update(MchtCloseApplication model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtCloseApplication model = new MchtCloseApplication();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtCloseApplicationExt findById(int id){
		return extDao.findById(id);
	}

	public MchtCloseApplicationExt find(MchtCloseApplicationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtCloseApplicationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtCloseApplicationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtCloseApplicationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtCloseApplicationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtCloseApplicationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtCloseApplicationExt> list(MchtCloseApplicationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtCloseApplicationExt> paginate(MchtCloseApplicationExtExample example) {
		List<MchtCloseApplicationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
