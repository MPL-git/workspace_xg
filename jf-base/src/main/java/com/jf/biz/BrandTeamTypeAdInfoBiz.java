package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BrandTeamTypeAdInfoExtMapper;
import com.jf.dao.BrandTeamTypeAdInfoMapper;
import com.jf.entity.BrandTeamTypeAdInfo;
import com.jf.entity.BrandTeamTypeAdInfoExample;
import com.jf.entity.BrandTeamTypeAdInfoExt;
import com.jf.entity.BrandTeamTypeAdInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrandTeamTypeAdInfoBiz extends BaseService<BrandTeamTypeAdInfo, BrandTeamTypeAdInfoExample> {

	@Autowired
	private BrandTeamTypeAdInfoMapper dao;
	@Autowired
	private BrandTeamTypeAdInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBrandTeamTypeAdInfoMapper(BrandTeamTypeAdInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BrandTeamTypeAdInfoExt save(BrandTeamTypeAdInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BrandTeamTypeAdInfo update(BrandTeamTypeAdInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BrandTeamTypeAdInfo model = new BrandTeamTypeAdInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BrandTeamTypeAdInfoExt findById(int id){
		return extDao.findById(id);
	}

	public BrandTeamTypeAdInfoExt find(BrandTeamTypeAdInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BrandTeamTypeAdInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BrandTeamTypeAdInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BrandTeamTypeAdInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BrandTeamTypeAdInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BrandTeamTypeAdInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BrandTeamTypeAdInfoExt> list(BrandTeamTypeAdInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BrandTeamTypeAdInfoExt> paginate(BrandTeamTypeAdInfoExtExample example) {
		List<BrandTeamTypeAdInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
