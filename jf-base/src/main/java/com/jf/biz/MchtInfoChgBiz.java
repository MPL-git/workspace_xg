package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtInfoChgExtMapper;
import com.jf.dao.MchtInfoChgMapper;
import com.jf.entity.MchtInfoChg;
import com.jf.entity.MchtInfoChgExample;
import com.jf.entity.MchtInfoChgExt;
import com.jf.entity.MchtInfoChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtInfoChgBiz extends BaseService<MchtInfoChg, MchtInfoChgExample> {

	@Autowired
	private MchtInfoChgMapper dao;
	@Autowired
	private MchtInfoChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtInfoChgMapper(MchtInfoChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtInfoChgExt save(MchtInfoChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtInfoChg update(MchtInfoChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtInfoChg model = new MchtInfoChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtInfoChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtInfoChgExt find(MchtInfoChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtInfoChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtInfoChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtInfoChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtInfoChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtInfoChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtInfoChgExt> list(MchtInfoChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtInfoChgExt> paginate(MchtInfoChgExtExample example) {
		List<MchtInfoChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
