package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SourceNicheExtMapper;
import com.jf.dao.SourceNicheMapper;
import com.jf.entity.SourceNiche;
import com.jf.entity.SourceNicheExample;
import com.jf.entity.SourceNicheExt;
import com.jf.entity.SourceNicheExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SourceNicheBiz extends BaseService<SourceNiche, SourceNicheExample> {

	@Autowired
	private SourceNicheMapper dao;
	@Autowired
	private SourceNicheExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSourceNicheMapper(SourceNicheMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SourceNicheExt save(SourceNicheExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SourceNiche update(SourceNiche model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SourceNiche model = new SourceNiche();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SourceNicheExt findById(int id){
		return extDao.findById(id);
	}

	public SourceNicheExt find(SourceNicheExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SourceNicheExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SourceNicheExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SourceNicheExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SourceNicheExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SourceNicheExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SourceNicheExt> list(SourceNicheExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SourceNicheExt> paginate(SourceNicheExtExample example) {
		List<SourceNicheExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
