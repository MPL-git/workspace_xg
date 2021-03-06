package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberJgRelationExtMapper;
import com.jf.dao.MemberJgRelationMapper;
import com.jf.entity.MemberJgRelation;
import com.jf.entity.MemberJgRelationExample;
import com.jf.entity.MemberJgRelationExt;
import com.jf.entity.MemberJgRelationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberJgRelationBiz extends BaseService<MemberJgRelation, MemberJgRelationExample> {

	@Autowired
	private MemberJgRelationMapper dao;
	@Autowired
	private MemberJgRelationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberJgRelationMapper(MemberJgRelationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberJgRelationExt save(MemberJgRelationExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberJgRelation update(MemberJgRelation model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberJgRelation model = new MemberJgRelation();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberJgRelationExt findById(int id){
		return extDao.findById(id);
	}

	public MemberJgRelationExt find(MemberJgRelationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberJgRelationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberJgRelationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberJgRelationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberJgRelationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberJgRelationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberJgRelationExt> list(MemberJgRelationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberJgRelationExt> paginate(MemberJgRelationExtExample example) {
		List<MemberJgRelationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
