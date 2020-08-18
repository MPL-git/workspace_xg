package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ToutiaoTokenAdvertiserInfoExtMapper;
import com.jf.dao.ToutiaoTokenAdvertiserInfoMapper;
import com.jf.entity.ToutiaoTokenAdvertiserInfo;
import com.jf.entity.ToutiaoTokenAdvertiserInfoExample;
import com.jf.entity.ToutiaoTokenAdvertiserInfoExt;
import com.jf.entity.ToutiaoTokenAdvertiserInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ToutiaoTokenAdvertiserInfoBiz extends BaseService<ToutiaoTokenAdvertiserInfo, ToutiaoTokenAdvertiserInfoExample> {

	@Autowired
	private ToutiaoTokenAdvertiserInfoMapper dao;
	@Autowired
	private ToutiaoTokenAdvertiserInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setToutiaoTokenAdvertiserInfoMapper(ToutiaoTokenAdvertiserInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ToutiaoTokenAdvertiserInfoExt save(ToutiaoTokenAdvertiserInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ToutiaoTokenAdvertiserInfo update(ToutiaoTokenAdvertiserInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ToutiaoTokenAdvertiserInfo model = new ToutiaoTokenAdvertiserInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ToutiaoTokenAdvertiserInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ToutiaoTokenAdvertiserInfoExt find(ToutiaoTokenAdvertiserInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ToutiaoTokenAdvertiserInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ToutiaoTokenAdvertiserInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ToutiaoTokenAdvertiserInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ToutiaoTokenAdvertiserInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ToutiaoTokenAdvertiserInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ToutiaoTokenAdvertiserInfoExt> list(ToutiaoTokenAdvertiserInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ToutiaoTokenAdvertiserInfoExt> paginate(ToutiaoTokenAdvertiserInfoExtExample example) {
		List<ToutiaoTokenAdvertiserInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
