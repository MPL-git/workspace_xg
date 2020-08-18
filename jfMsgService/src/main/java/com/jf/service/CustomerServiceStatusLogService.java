package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CustomerServiceStatusLogMapper;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.CustomerServiceStatusLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CustomerServiceStatusLogService {

	@Autowired
	private CustomerServiceStatusLogMapper dao;


	public CustomerServiceStatusLog save(int serviceOrderId, String status, String proStatus, String remark){
		CustomerServiceStatusLog model = new CustomerServiceStatusLog();
		model.setServiceOrderId(serviceOrderId);
		model.setStatus(status);
		model.setProStatus(proStatus);
		model.setRemarks(remark);
		return save(model);
	}

	public CustomerServiceStatusLog findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public CustomerServiceStatusLog save(CustomerServiceStatusLog model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public CustomerServiceStatusLog update(CustomerServiceStatusLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		CustomerServiceStatusLog model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<CustomerServiceStatusLog> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<CustomerServiceStatusLog> paginate(QueryObject queryObject) {
		CustomerServiceStatusLogExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<CustomerServiceStatusLog> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private CustomerServiceStatusLogExample builderQuery(QueryObject queryObject) {
		CustomerServiceStatusLogExample example = new CustomerServiceStatusLogExample();
		CustomerServiceStatusLogExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public int insertSelective(CustomerServiceStatusLog customerServiceStatusLog){
		return dao.insertSelective(customerServiceStatusLog);
	}
}
