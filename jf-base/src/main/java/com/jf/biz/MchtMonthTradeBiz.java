package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtMonthTradeExtMapper;
import com.jf.dao.MchtMonthTradeMapper;
import com.jf.entity.MchtMonthTrade;
import com.jf.entity.MchtMonthTradeExample;
import com.jf.entity.MchtMonthTradeExt;
import com.jf.entity.MchtMonthTradeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtMonthTradeBiz extends BaseService<MchtMonthTrade, MchtMonthTradeExample> {

	@Autowired
	private MchtMonthTradeMapper dao;
	@Autowired
	private MchtMonthTradeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtMonthTradeMapper(MchtMonthTradeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtMonthTradeExt save(MchtMonthTradeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtMonthTrade update(MchtMonthTrade model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtMonthTrade model = new MchtMonthTrade();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtMonthTradeExt findById(int id){
		return extDao.findById(id);
	}

	public MchtMonthTradeExt find(MchtMonthTradeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtMonthTradeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtMonthTradeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtMonthTradeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtMonthTradeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtMonthTradeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtMonthTradeExt> list(MchtMonthTradeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtMonthTradeExt> paginate(MchtMonthTradeExtExample example) {
		List<MchtMonthTradeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
