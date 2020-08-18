package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberPvDtlExtMapper;
import com.jf.dao.MemberPvDtlMapper;
import com.jf.entity.MemberPvDtl;
import com.jf.entity.MemberPvDtlExample;
import com.jf.entity.MemberPvDtlExt;
import com.jf.entity.MemberPvDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberPvDtlBiz extends BaseService<MemberPvDtl, MemberPvDtlExample> {

	@Autowired
	private MemberPvDtlMapper dao;
	@Autowired
	private MemberPvDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberPvDtlMapper(MemberPvDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberPvDtlExt save(MemberPvDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberPvDtl update(MemberPvDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(long id){
		MemberPvDtl model = new MemberPvDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberPvDtlExt findById(int id){
		return extDao.findById(id);
	}

	public MemberPvDtlExt find(MemberPvDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberPvDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberPvDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberPvDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberPvDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberPvDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberPvDtlExt> list(MemberPvDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberPvDtlExt> paginate(MemberPvDtlExtExample example) {
		List<MemberPvDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
