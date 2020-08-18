package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformMsgEditExtMapper;
import com.jf.dao.PlatformMsgEditMapper;
import com.jf.entity.PlatformMsgEdit;
import com.jf.entity.PlatformMsgEditExample;
import com.jf.entity.PlatformMsgEditExt;
import com.jf.entity.PlatformMsgEditExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlatformMsgEditBiz extends BaseService<PlatformMsgEdit, PlatformMsgEditExample> {

	@Autowired
	private PlatformMsgEditMapper dao;
	@Autowired
	private PlatformMsgEditExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPlatformMsgEditMapper(PlatformMsgEditMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PlatformMsgEditExt save(PlatformMsgEditExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PlatformMsgEdit update(PlatformMsgEdit model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PlatformMsgEdit model = new PlatformMsgEdit();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PlatformMsgEditExt findById(int id){
		return extDao.findById(id);
	}

	public PlatformMsgEditExt find(PlatformMsgEditExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PlatformMsgEditExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PlatformMsgEditExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PlatformMsgEditExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PlatformMsgEditExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PlatformMsgEditExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PlatformMsgEditExt> list(PlatformMsgEditExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PlatformMsgEditExt> paginate(PlatformMsgEditExtExample example) {
		List<PlatformMsgEditExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
