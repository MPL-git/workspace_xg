package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtViewLogExtMapper;
import com.jf.dao.MchtViewLogMapper;
import com.jf.entity.MchtViewLog;
import com.jf.entity.MchtViewLogExample;
import com.jf.entity.MchtViewLogExt;
import com.jf.entity.MchtViewLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtViewLogBiz extends BaseService<MchtViewLog, MchtViewLogExample> {

	@Autowired
	private MchtViewLogMapper dao;
	@Autowired
	private MchtViewLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtViewLogMapper(MchtViewLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtViewLogExt save(MchtViewLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtViewLog update(MchtViewLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtViewLog model = new MchtViewLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtViewLogExt findById(int id){
		return extDao.findById(id);
	}

	public MchtViewLogExt find(MchtViewLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtViewLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtViewLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtViewLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtViewLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtViewLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtViewLogExt> list(MchtViewLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtViewLogExt> paginate(MchtViewLogExtExample example) {
		List<MchtViewLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
