package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberGroupExtMapper;
import com.jf.dao.MemberGroupMapper;
import com.jf.entity.MemberGroup;
import com.jf.entity.MemberGroupExample;
import com.jf.entity.MemberGroupExt;
import com.jf.entity.MemberGroupExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberGroupBiz extends BaseService<MemberGroup, MemberGroupExample> {

	@Autowired
	private MemberGroupMapper dao;
	@Autowired
	private MemberGroupExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberGroupMapper(MemberGroupMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberGroupExt save(MemberGroupExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberGroup update(MemberGroup model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberGroup model = new MemberGroup();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberGroupExt findById(int id){
		return extDao.findById(id);
	}

	public MemberGroupExt find(MemberGroupExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberGroupExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberGroupExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberGroupExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberGroupExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberGroupExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberGroupExt> list(MemberGroupExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberGroupExt> paginate(MemberGroupExtExample example) {
		List<MemberGroupExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
