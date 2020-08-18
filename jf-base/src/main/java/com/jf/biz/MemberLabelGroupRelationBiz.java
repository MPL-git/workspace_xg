package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberLabelGroupRelationExtMapper;
import com.jf.dao.MemberLabelGroupRelationMapper;
import com.jf.entity.MemberLabelGroupRelation;
import com.jf.entity.MemberLabelGroupRelationExample;
import com.jf.entity.MemberLabelGroupRelationExt;
import com.jf.entity.MemberLabelGroupRelationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberLabelGroupRelationBiz extends BaseService<MemberLabelGroupRelation, MemberLabelGroupRelationExample> {

	@Autowired
	private MemberLabelGroupRelationMapper dao;
	@Autowired
	private MemberLabelGroupRelationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberLabelGroupRelationMapper(MemberLabelGroupRelationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberLabelGroupRelationExt save(MemberLabelGroupRelationExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberLabelGroupRelation update(MemberLabelGroupRelation model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberLabelGroupRelation model = new MemberLabelGroupRelation();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberLabelGroupRelationExt findById(int id){
		return extDao.findById(id);
	}

	public MemberLabelGroupRelationExt find(MemberLabelGroupRelationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberLabelGroupRelationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberLabelGroupRelationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberLabelGroupRelationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberLabelGroupRelationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberLabelGroupRelationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberLabelGroupRelationExt> list(MemberLabelGroupRelationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberLabelGroupRelationExt> paginate(MemberLabelGroupRelationExtExample example) {
		List<MemberLabelGroupRelationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
