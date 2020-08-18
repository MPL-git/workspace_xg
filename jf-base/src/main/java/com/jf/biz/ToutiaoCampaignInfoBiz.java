package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ToutiaoCampaignInfoExtMapper;
import com.jf.dao.ToutiaoCampaignInfoMapper;
import com.jf.entity.ToutiaoCampaignInfo;
import com.jf.entity.ToutiaoCampaignInfoExample;
import com.jf.entity.ToutiaoCampaignInfoExt;
import com.jf.entity.ToutiaoCampaignInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ToutiaoCampaignInfoBiz extends BaseService<ToutiaoCampaignInfo, ToutiaoCampaignInfoExample> {

	@Autowired
	private ToutiaoCampaignInfoMapper dao;
	@Autowired
	private ToutiaoCampaignInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setToutiaoCampaignInfoMapper(ToutiaoCampaignInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ToutiaoCampaignInfoExt save(ToutiaoCampaignInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ToutiaoCampaignInfo update(ToutiaoCampaignInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ToutiaoCampaignInfo model = new ToutiaoCampaignInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ToutiaoCampaignInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ToutiaoCampaignInfoExt find(ToutiaoCampaignInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ToutiaoCampaignInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ToutiaoCampaignInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ToutiaoCampaignInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ToutiaoCampaignInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ToutiaoCampaignInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ToutiaoCampaignInfoExt> list(ToutiaoCampaignInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ToutiaoCampaignInfoExt> paginate(ToutiaoCampaignInfoExtExample example) {
		List<ToutiaoCampaignInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
