package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformMsgExtMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.entity.PlatformMsg;
import com.jf.entity.PlatformMsgExample;
import com.jf.entity.PlatformMsgExt;
import com.jf.entity.PlatformMsgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlatformMsgBiz extends BaseService<PlatformMsg, PlatformMsgExample> {

	@Autowired
	private PlatformMsgMapper dao;
	@Autowired
	private PlatformMsgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPlatformMsgMapper(PlatformMsgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PlatformMsgExt save(PlatformMsgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PlatformMsg update(PlatformMsg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PlatformMsg model = new PlatformMsg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PlatformMsgExt findById(int id){
		return extDao.findById(id);
	}

	public PlatformMsgExt find(PlatformMsgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PlatformMsgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PlatformMsgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PlatformMsgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PlatformMsgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PlatformMsgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PlatformMsgExt> list(PlatformMsgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PlatformMsgExt> paginate(PlatformMsgExtExample example) {
		List<PlatformMsgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
