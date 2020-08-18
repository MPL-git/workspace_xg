package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FullDiscountMapper;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;
import com.jf.entity.MchtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FullDiscountService {

	@Autowired
	private FullDiscountMapper dao;

	public FullDiscount save(int activityAreaId, String type, String rule, MchtInfo currentInfo){
		FullDiscount model = findByActivityAreaId(activityAreaId);
		if(model != null){
			model.setType(type);
			model.setRule(rule.trim());
			return update(model);
		}

		model = new FullDiscount();
		model.setType(type);
		model.setRule(rule.trim());

		model.setActivityAreaId(activityAreaId);
		model.setRang(Const.PREFERENTIAL_RANG_BRAND);
		// 自营spop的belong归属于平台，其它都归于商家
		if("1".equals(currentInfo.getIsManageSelf())){
			model.setBelong(Const.PREFERENTIAL_BELONG_PLAT);
		}else {
			model.setBelong(Const.PREFERENTIAL_BELONG_MCHT);
		}
		return save(model);
	}


	public FullDiscount findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public FullDiscount findByActivityAreaId(int activityAreaId){
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("activityAreaId", activityAreaId);
		List<FullDiscount> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public FullDiscount save(FullDiscount model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public FullDiscount update(FullDiscount model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		FullDiscount model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public List<FullDiscount> list(QueryObject queryObject) {

		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<FullDiscount> paginate(QueryObject queryObject) {
		FullDiscountExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<FullDiscount> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private FullDiscountExample builderQuery(QueryObject queryObject) {
		FullDiscountExample example = new FullDiscountExample();
		FullDiscountExample.Criteria criteria = example.createCriteria();

		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("activityAreaId") != null){
			criteria.andActivityAreaIdEqualTo(queryObject.getQueryToInt("activityAreaId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
