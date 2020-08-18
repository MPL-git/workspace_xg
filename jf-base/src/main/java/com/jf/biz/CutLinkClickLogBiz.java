package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CutLinkClickLogExtMapper;
import com.jf.dao.CutLinkClickLogMapper;
import com.jf.entity.CutLinkClickLog;
import com.jf.entity.CutLinkClickLogExample;
import com.jf.entity.CutLinkClickLogExt;
import com.jf.entity.CutLinkClickLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CutLinkClickLogBiz extends BaseService<CutLinkClickLog, CutLinkClickLogExample> {

	@Autowired
	private CutLinkClickLogMapper dao;
	@Autowired
	private CutLinkClickLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCutLinkClickLogMapper(CutLinkClickLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CutLinkClickLogExt save(CutLinkClickLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CutLinkClickLog update(CutLinkClickLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CutLinkClickLog model = new CutLinkClickLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CutLinkClickLogExt findById(int id){
		return extDao.findById(id);
	}

	public CutLinkClickLogExt find(CutLinkClickLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CutLinkClickLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CutLinkClickLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CutLinkClickLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CutLinkClickLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CutLinkClickLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CutLinkClickLogExt> list(CutLinkClickLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CutLinkClickLogExt> paginate(CutLinkClickLogExtExample example) {
		List<CutLinkClickLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
