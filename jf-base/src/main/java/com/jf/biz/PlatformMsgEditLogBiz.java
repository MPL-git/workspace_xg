package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformMsgEditLogExtMapper;
import com.jf.dao.PlatformMsgEditLogMapper;
import com.jf.entity.PlatformMsgEditLog;
import com.jf.entity.PlatformMsgEditLogExample;
import com.jf.entity.PlatformMsgEditLogExt;
import com.jf.entity.PlatformMsgEditLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlatformMsgEditLogBiz extends BaseService<PlatformMsgEditLog, PlatformMsgEditLogExample> {

	@Autowired
	private PlatformMsgEditLogMapper dao;
	@Autowired
	private PlatformMsgEditLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPlatformMsgEditLogMapper(PlatformMsgEditLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PlatformMsgEditLogExt save(PlatformMsgEditLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PlatformMsgEditLog update(PlatformMsgEditLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PlatformMsgEditLog model = new PlatformMsgEditLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PlatformMsgEditLogExt findById(int id){
		return extDao.findById(id);
	}

	public PlatformMsgEditLogExt find(PlatformMsgEditLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PlatformMsgEditLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PlatformMsgEditLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PlatformMsgEditLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PlatformMsgEditLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PlatformMsgEditLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PlatformMsgEditLogExt> list(PlatformMsgEditLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PlatformMsgEditLogExt> paginate(PlatformMsgEditLogExtExample example) {
		List<PlatformMsgEditLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
