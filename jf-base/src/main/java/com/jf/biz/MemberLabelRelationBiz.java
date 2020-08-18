package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberLabelRelationExtMapper;
import com.jf.dao.MemberLabelRelationMapper;
import com.jf.entity.MemberLabelRelation;
import com.jf.entity.MemberLabelRelationExample;
import com.jf.entity.MemberLabelRelationExt;
import com.jf.entity.MemberLabelRelationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberLabelRelationBiz extends BaseService<MemberLabelRelation, MemberLabelRelationExample> {

	@Autowired
	private MemberLabelRelationMapper dao;
	@Autowired
	private MemberLabelRelationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberLabelRelationMapper(MemberLabelRelationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberLabelRelationExt save(MemberLabelRelationExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberLabelRelation update(MemberLabelRelation model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberLabelRelation model = new MemberLabelRelation();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberLabelRelationExt findById(int id){
		return extDao.findById(id);
	}

	public MemberLabelRelationExt find(MemberLabelRelationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberLabelRelationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberLabelRelationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberLabelRelationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberLabelRelationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberLabelRelationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberLabelRelationExt> list(MemberLabelRelationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberLabelRelationExt> paginate(MemberLabelRelationExtExample example) {
		List<MemberLabelRelationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
