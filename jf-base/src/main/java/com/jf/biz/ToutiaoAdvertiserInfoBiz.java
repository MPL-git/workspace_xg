package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ToutiaoAdvertiserInfoExtMapper;
import com.jf.dao.ToutiaoAdvertiserInfoMapper;
import com.jf.entity.ToutiaoAdvertiserInfo;
import com.jf.entity.ToutiaoAdvertiserInfoExample;
import com.jf.entity.ToutiaoAdvertiserInfoExt;
import com.jf.entity.ToutiaoAdvertiserInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ToutiaoAdvertiserInfoBiz extends BaseService<ToutiaoAdvertiserInfo, ToutiaoAdvertiserInfoExample> {

	@Autowired
	private ToutiaoAdvertiserInfoMapper dao;
	@Autowired
	private ToutiaoAdvertiserInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setToutiaoAdvertiserInfoMapper(ToutiaoAdvertiserInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ToutiaoAdvertiserInfoExt save(ToutiaoAdvertiserInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ToutiaoAdvertiserInfo update(ToutiaoAdvertiserInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ToutiaoAdvertiserInfo model = new ToutiaoAdvertiserInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ToutiaoAdvertiserInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ToutiaoAdvertiserInfoExt find(ToutiaoAdvertiserInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ToutiaoAdvertiserInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ToutiaoAdvertiserInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ToutiaoAdvertiserInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ToutiaoAdvertiserInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ToutiaoAdvertiserInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ToutiaoAdvertiserInfoExt> list(ToutiaoAdvertiserInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ToutiaoAdvertiserInfoExt> paginate(ToutiaoAdvertiserInfoExtExample example) {
		List<ToutiaoAdvertiserInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
