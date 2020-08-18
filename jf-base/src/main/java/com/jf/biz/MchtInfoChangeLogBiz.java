package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtInfoChangeLogExtMapper;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.entity.MchtInfoChangeLog;
import com.jf.entity.MchtInfoChangeLogExample;
import com.jf.entity.MchtInfoChangeLogExt;
import com.jf.entity.MchtInfoChangeLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtInfoChangeLogBiz extends BaseService<MchtInfoChangeLog, MchtInfoChangeLogExample> {

	@Autowired
	private MchtInfoChangeLogMapper dao;
	@Autowired
	private MchtInfoChangeLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtInfoChangeLogMapper(MchtInfoChangeLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtInfoChangeLogExt save(MchtInfoChangeLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtInfoChangeLog update(MchtInfoChangeLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtInfoChangeLog model = new MchtInfoChangeLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtInfoChangeLogExt findById(int id){
		return extDao.findById(id);
	}

	public MchtInfoChangeLogExt find(MchtInfoChangeLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtInfoChangeLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtInfoChangeLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtInfoChangeLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtInfoChangeLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtInfoChangeLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtInfoChangeLogExt> list(MchtInfoChangeLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtInfoChangeLogExt> paginate(MchtInfoChangeLogExtExample example) {
		List<MchtInfoChangeLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
