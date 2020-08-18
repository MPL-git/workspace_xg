package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SmsBlackIpMapper;
import com.jf.dao.SmsBlackIpExtMapper;
import com.jf.entity.SmsBlackIp;
import com.jf.entity.SmsBlackIpExample;
import com.jf.entity.SmsBlackIpExt;
import com.jf.entity.SmsBlackIpExtExample;
import com.jf.common.base.BaseService;

@Service
public class SmsBlackIpBiz extends BaseService<SmsBlackIp, SmsBlackIpExample> {

	@Autowired
	private SmsBlackIpMapper dao;
	@Autowired
	private SmsBlackIpExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSmsBlackIpMapper(SmsBlackIpMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SmsBlackIpExt save(SmsBlackIpExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SmsBlackIp update(SmsBlackIp model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SmsBlackIp model = new SmsBlackIp();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SmsBlackIpExt findById(int id){
		return extDao.findById(id);
	}

	public SmsBlackIpExt find(SmsBlackIpExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SmsBlackIpExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SmsBlackIpExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SmsBlackIpExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SmsBlackIpExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SmsBlackIpExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SmsBlackIpExt> list(SmsBlackIpExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SmsBlackIpExt> paginate(SmsBlackIpExtExample example) {
		List<SmsBlackIpExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
