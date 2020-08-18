package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberControlExtMapper;
import com.jf.dao.MemberControlMapper;
import com.jf.entity.MemberControl;
import com.jf.entity.MemberControlExample;
import com.jf.entity.MemberControlExt;
import com.jf.entity.MemberControlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberControlBiz extends BaseService<MemberControl, MemberControlExample> {

	@Autowired
	private MemberControlMapper dao;
	@Autowired
	private MemberControlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberControlMapper(MemberControlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberControlExt save(MemberControlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberControl update(MemberControl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberControl model = new MemberControl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberControlExt findById(int id){
		return extDao.findById(id);
	}

	public MemberControlExt find(MemberControlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberControlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberControlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberControlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberControlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberControlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberControlExt> list(MemberControlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberControlExt> paginate(MemberControlExtExample example) {
		List<MemberControlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
