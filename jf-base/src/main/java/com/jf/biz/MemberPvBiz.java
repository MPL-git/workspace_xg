package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberPvExtMapper;
import com.jf.dao.MemberPvMapper;
import com.jf.entity.MemberPv;
import com.jf.entity.MemberPvExample;
import com.jf.entity.MemberPvExt;
import com.jf.entity.MemberPvExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberPvBiz extends BaseService<MemberPv, MemberPvExample> {

	@Autowired
	private MemberPvMapper dao;
	@Autowired
	private MemberPvExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberPvMapper(MemberPvMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberPvExt save(MemberPvExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberPv update(MemberPv model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(long id){
		MemberPv model = new MemberPv();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberPvExt findById(int id){
		return extDao.findById(id);
	}

	public MemberPvExt find(MemberPvExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberPvExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberPvExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberPvExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberPvExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberPvExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberPvExt> list(MemberPvExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberPvExt> paginate(MemberPvExtExample example) {
		List<MemberPvExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
