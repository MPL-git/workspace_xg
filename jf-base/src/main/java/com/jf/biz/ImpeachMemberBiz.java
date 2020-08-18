package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ImpeachMemberExtMapper;
import com.jf.dao.ImpeachMemberMapper;
import com.jf.entity.ImpeachMember;
import com.jf.entity.ImpeachMemberExample;
import com.jf.entity.ImpeachMemberExt;
import com.jf.entity.ImpeachMemberExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImpeachMemberBiz extends BaseService<ImpeachMember, ImpeachMemberExample> {

	@Autowired
	private ImpeachMemberMapper dao;
	@Autowired
	private ImpeachMemberExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setImpeachMemberMapper(ImpeachMemberMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ImpeachMemberExt save(ImpeachMemberExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ImpeachMember update(ImpeachMember model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ImpeachMember model = new ImpeachMember();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ImpeachMemberExt findById(int id){
		return extDao.findById(id);
	}

	public ImpeachMemberExt find(ImpeachMemberExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ImpeachMemberExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ImpeachMemberExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ImpeachMemberExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ImpeachMemberExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ImpeachMemberExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ImpeachMemberExt> list(ImpeachMemberExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ImpeachMemberExt> paginate(ImpeachMemberExtExample example) {
		List<ImpeachMemberExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
