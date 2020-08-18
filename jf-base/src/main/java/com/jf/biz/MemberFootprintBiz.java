package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberFootprintExtMapper;
import com.jf.dao.MemberFootprintMapper;
import com.jf.entity.MemberFootprint;
import com.jf.entity.MemberFootprintExample;
import com.jf.entity.MemberFootprintExt;
import com.jf.entity.MemberFootprintExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberFootprintBiz extends BaseService<MemberFootprint, MemberFootprintExample> {

	@Autowired
	private MemberFootprintMapper dao;
	@Autowired
	private MemberFootprintExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberFootprintMapper(MemberFootprintMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberFootprintExt save(MemberFootprintExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberFootprint update(MemberFootprint model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberFootprint model = new MemberFootprint();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberFootprintExt findById(int id){
		return extDao.findById(id);
	}

	public MemberFootprintExt find(MemberFootprintExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberFootprintExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberFootprintExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberFootprintExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberFootprintExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberFootprintExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberFootprintExt> list(MemberFootprintExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberFootprintExt> paginate(MemberFootprintExtExample example) {
		List<MemberFootprintExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
