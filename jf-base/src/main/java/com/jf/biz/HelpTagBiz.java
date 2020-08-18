package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.HelpTagExtMapper;
import com.jf.dao.HelpTagMapper;
import com.jf.entity.HelpTag;
import com.jf.entity.HelpTagExample;
import com.jf.entity.HelpTagExt;
import com.jf.entity.HelpTagExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HelpTagBiz extends BaseService<HelpTag, HelpTagExample> {

	@Autowired
	private HelpTagMapper dao;
	@Autowired
	private HelpTagExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setHelpTagMapper(HelpTagMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public HelpTagExt save(HelpTagExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public HelpTag update(HelpTag model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		HelpTag model = new HelpTag();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public HelpTagExt findById(int id){
		return extDao.findById(id);
	}

	public HelpTagExt find(HelpTagExtExample example){
		return extDao.find(example.fill());
	}

	public int count(HelpTagExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, HelpTagExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, HelpTagExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, HelpTagExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(HelpTagExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<HelpTagExt> list(HelpTagExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<HelpTagExt> paginate(HelpTagExtExample example) {
		List<HelpTagExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
