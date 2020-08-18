package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FullGiveMapper;
import com.jf.entity.FullGive;
import com.jf.entity.FullGiveExample;
import com.jf.entity.MchtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FullGiveService {

	@Autowired
	private FullGiveMapper dao;

	public FullGive saveFullGive(int activityAreaId, BigDecimal minimum, boolean sumFlag, int productId, int productNum, MchtInfo currentInfo){
		FullGive model = findByActivityAreaId(activityAreaId);
		if(model != null){
			model.setType(Const.FULL_GIVE_TYPE_FULL);
			model.setMinimum(minimum);
			model.setSumFlag(sumFlag ? "1" : "0");

			model.setProductFlag(Const.FLAG_TRUE);
			model.setProductId(productId);
			model.setProductNum(productNum);
			return update(model);
		}

		model = new FullGive();
		model.setType(Const.FULL_GIVE_TYPE_FULL);
		model.setMinimum(minimum);
		model.setSumFlag(sumFlag ? "1" : "0");

		model.setProductFlag(Const.FLAG_TRUE);
		model.setProductId(productId);
		model.setProductNum(productNum);

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

	public FullGive saveBuyGive(int activityAreaId, int productId, int productNum, MchtInfo currentInfo){
		FullGive model = findByActivityAreaId(activityAreaId);
		if(model != null){
			model.setType(Const.FULL_GIVE_TYPE_BUY);
			model.setProductFlag(Const.FLAG_TRUE);
			model.setProductId(productId);
			model.setProductNum(productNum);

			model.setMinimum(null);
			model.setSumFlag(null);
			return update(model);
		}

		model = new FullGive();
		model.setType(Const.FULL_GIVE_TYPE_BUY);
		model.setProductFlag(Const.FLAG_TRUE);
		model.setProductId(productId);
		model.setProductNum(productNum);

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

	public FullGive findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public FullGive findByActivityAreaId(int activityAreaId){
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("activityAreaId", activityAreaId);
		List<FullGive> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public FullGive save(FullGive model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public FullGive update(FullGive model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		FullGive model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public List<FullGive> list(QueryObject queryObject) {

		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<FullGive> paginate(QueryObject queryObject) {
		FullGiveExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<FullGive> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private FullGiveExample builderQuery(QueryObject queryObject) {
		FullGiveExample example = new FullGiveExample();
		FullGiveExample.Criteria criteria = example.createCriteria();

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
