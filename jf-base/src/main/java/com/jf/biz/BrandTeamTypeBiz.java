package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BrandTeamTypeExtMapper;
import com.jf.dao.BrandTeamTypeMapper;
import com.jf.entity.BrandTeamType;
import com.jf.entity.BrandTeamTypeExample;
import com.jf.entity.BrandTeamTypeExt;
import com.jf.entity.BrandTeamTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrandTeamTypeBiz extends BaseService<BrandTeamType, BrandTeamTypeExample> {

	@Autowired
	private BrandTeamTypeMapper dao;
	@Autowired
	private BrandTeamTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBrandTeamTypeMapper(BrandTeamTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BrandTeamTypeExt save(BrandTeamTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BrandTeamType update(BrandTeamType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BrandTeamType model = new BrandTeamType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BrandTeamTypeExt findById(int id){
		return extDao.findById(id);
	}

	public BrandTeamTypeExt find(BrandTeamTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BrandTeamTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BrandTeamTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BrandTeamTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BrandTeamTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BrandTeamTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BrandTeamTypeExt> list(BrandTeamTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BrandTeamTypeExt> paginate(BrandTeamTypeExtExample example) {
		List<BrandTeamTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
