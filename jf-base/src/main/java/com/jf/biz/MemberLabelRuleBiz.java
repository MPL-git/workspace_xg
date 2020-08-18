package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberLabelRuleExtMapper;
import com.jf.dao.MemberLabelRuleMapper;
import com.jf.entity.MemberLabelRule;
import com.jf.entity.MemberLabelRuleExample;
import com.jf.entity.MemberLabelRuleExt;
import com.jf.entity.MemberLabelRuleExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberLabelRuleBiz extends BaseService<MemberLabelRule, MemberLabelRuleExample> {

	@Autowired
	private MemberLabelRuleMapper dao;
	@Autowired
	private MemberLabelRuleExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberLabelRuleMapper(MemberLabelRuleMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberLabelRuleExt save(MemberLabelRuleExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberLabelRule update(MemberLabelRule model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberLabelRule model = new MemberLabelRule();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberLabelRuleExt findById(int id){
		return extDao.findById(id);
	}

	public MemberLabelRuleExt find(MemberLabelRuleExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberLabelRuleExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberLabelRuleExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberLabelRuleExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberLabelRuleExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberLabelRuleExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberLabelRuleExt> list(MemberLabelRuleExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberLabelRuleExt> paginate(MemberLabelRuleExtExample example) {
		List<MemberLabelRuleExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
