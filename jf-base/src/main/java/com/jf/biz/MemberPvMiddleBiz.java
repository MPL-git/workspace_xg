package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberPvMiddleExtMapper;
import com.jf.dao.MemberPvMiddleMapper;
import com.jf.entity.MemberPvMiddle;
import com.jf.entity.MemberPvMiddleExample;
import com.jf.entity.MemberPvMiddleExt;
import com.jf.entity.MemberPvMiddleExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberPvMiddleBiz extends BaseService<MemberPvMiddle, MemberPvMiddleExample> {

	@Autowired
	private MemberPvMiddleMapper dao;
	@Autowired
	private MemberPvMiddleExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberPvMiddleMapper(MemberPvMiddleMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberPvMiddleExt save(MemberPvMiddleExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberPvMiddle update(MemberPvMiddle model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberPvMiddle model = new MemberPvMiddle();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberPvMiddleExt findById(int id){
		return extDao.findById(id);
	}

	public MemberPvMiddleExt find(MemberPvMiddleExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberPvMiddleExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberPvMiddleExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberPvMiddleExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberPvMiddleExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberPvMiddleExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberPvMiddleExt> list(MemberPvMiddleExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberPvMiddleExt> paginate(MemberPvMiddleExtExample example) {
		List<MemberPvMiddleExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
