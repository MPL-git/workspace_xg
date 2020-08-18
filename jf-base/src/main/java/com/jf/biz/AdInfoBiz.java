package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AdInfoExtMapper;
import com.jf.dao.AdInfoMapper;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoExample;
import com.jf.entity.AdInfoExt;
import com.jf.entity.AdInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdInfoBiz extends BaseService<AdInfo, AdInfoExample> {

	@Autowired
	private AdInfoMapper dao;
	@Autowired
	private AdInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAdInfoMapper(AdInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AdInfoExt save(AdInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AdInfo update(AdInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AdInfo model = new AdInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AdInfoExt findById(int id){
		return extDao.findById(id);
	}

	public AdInfoExt find(AdInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AdInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AdInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AdInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AdInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AdInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AdInfoExt> list(AdInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AdInfoExt> paginate(AdInfoExtExample example) {
		List<AdInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
