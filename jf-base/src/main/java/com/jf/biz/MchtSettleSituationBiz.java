package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSettleSituationExtMapper;
import com.jf.dao.MchtSettleSituationMapper;
import com.jf.entity.MchtSettleSituation;
import com.jf.entity.MchtSettleSituationExample;
import com.jf.entity.MchtSettleSituationExt;
import com.jf.entity.MchtSettleSituationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtSettleSituationBiz extends BaseService<MchtSettleSituation, MchtSettleSituationExample> {

	@Autowired
	private MchtSettleSituationMapper dao;
	@Autowired
	private MchtSettleSituationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtSettleSituationMapper(MchtSettleSituationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtSettleSituationExt save(MchtSettleSituationExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtSettleSituation update(MchtSettleSituation model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtSettleSituation model = new MchtSettleSituation();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtSettleSituationExt findById(int id){
		return extDao.findById(id);
	}

	public MchtSettleSituationExt find(MchtSettleSituationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtSettleSituationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtSettleSituationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtSettleSituationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtSettleSituationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtSettleSituationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtSettleSituationExt> list(MchtSettleSituationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtSettleSituationExt> paginate(MchtSettleSituationExtExample example) {
		List<MchtSettleSituationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
