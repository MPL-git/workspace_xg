package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ToutiaoTokenInfoExtMapper;
import com.jf.dao.ToutiaoTokenInfoMapper;
import com.jf.entity.ToutiaoTokenInfo;
import com.jf.entity.ToutiaoTokenInfoExample;
import com.jf.entity.ToutiaoTokenInfoExt;
import com.jf.entity.ToutiaoTokenInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ToutiaoTokenInfoBiz extends BaseService<ToutiaoTokenInfo, ToutiaoTokenInfoExample> {

	@Autowired
	private ToutiaoTokenInfoMapper dao;
	@Autowired
	private ToutiaoTokenInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setToutiaoTokenInfoMapper(ToutiaoTokenInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ToutiaoTokenInfoExt save(ToutiaoTokenInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ToutiaoTokenInfo update(ToutiaoTokenInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ToutiaoTokenInfo model = new ToutiaoTokenInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ToutiaoTokenInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ToutiaoTokenInfoExt find(ToutiaoTokenInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ToutiaoTokenInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ToutiaoTokenInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ToutiaoTokenInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ToutiaoTokenInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ToutiaoTokenInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ToutiaoTokenInfoExt> list(ToutiaoTokenInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ToutiaoTokenInfoExt> paginate(ToutiaoTokenInfoExtExample example) {
		List<ToutiaoTokenInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
