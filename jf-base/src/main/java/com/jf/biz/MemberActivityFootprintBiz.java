package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberActivityFootprintExtMapper;
import com.jf.dao.MemberActivityFootprintMapper;
import com.jf.entity.MemberActivityFootprint;
import com.jf.entity.MemberActivityFootprintExample;
import com.jf.entity.MemberActivityFootprintExt;
import com.jf.entity.MemberActivityFootprintExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberActivityFootprintBiz extends BaseService<MemberActivityFootprint, MemberActivityFootprintExample> {

	@Autowired
	private MemberActivityFootprintMapper dao;
	@Autowired
	private MemberActivityFootprintExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberActivityFootprintMapper(MemberActivityFootprintMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberActivityFootprintExt save(MemberActivityFootprintExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberActivityFootprint update(MemberActivityFootprint model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberActivityFootprint model = new MemberActivityFootprint();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberActivityFootprintExt findById(int id){
		return extDao.findById(id);
	}

	public MemberActivityFootprintExt find(MemberActivityFootprintExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberActivityFootprintExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberActivityFootprintExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberActivityFootprintExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberActivityFootprintExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberActivityFootprintExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberActivityFootprintExt> list(MemberActivityFootprintExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberActivityFootprintExt> paginate(MemberActivityFootprintExtExample example) {
		List<MemberActivityFootprintExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
