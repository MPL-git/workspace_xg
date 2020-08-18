package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ToutiaoAdInfoExtMapper;
import com.jf.dao.ToutiaoAdInfoMapper;
import com.jf.entity.ToutiaoAdInfo;
import com.jf.entity.ToutiaoAdInfoExample;
import com.jf.entity.ToutiaoAdInfoExt;
import com.jf.entity.ToutiaoAdInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ToutiaoAdInfoBiz extends BaseService<ToutiaoAdInfo, ToutiaoAdInfoExample> {

	@Autowired
	private ToutiaoAdInfoMapper dao;
	@Autowired
	private ToutiaoAdInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setToutiaoAdInfoMapper(ToutiaoAdInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ToutiaoAdInfoExt save(ToutiaoAdInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ToutiaoAdInfo update(ToutiaoAdInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ToutiaoAdInfo model = new ToutiaoAdInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ToutiaoAdInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ToutiaoAdInfoExt find(ToutiaoAdInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ToutiaoAdInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ToutiaoAdInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ToutiaoAdInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ToutiaoAdInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ToutiaoAdInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ToutiaoAdInfoExt> list(ToutiaoAdInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ToutiaoAdInfoExt> paginate(ToutiaoAdInfoExtExample example) {
		List<ToutiaoAdInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
