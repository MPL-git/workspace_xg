package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberLabelGroupExtMapper;
import com.jf.dao.MemberLabelGroupMapper;
import com.jf.entity.MemberLabelGroup;
import com.jf.entity.MemberLabelGroupExample;
import com.jf.entity.MemberLabelGroupExt;
import com.jf.entity.MemberLabelGroupExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberLabelGroupBiz extends BaseService<MemberLabelGroup, MemberLabelGroupExample> {

	@Autowired
	private MemberLabelGroupMapper dao;
	@Autowired
	private MemberLabelGroupExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberLabelGroupMapper(MemberLabelGroupMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberLabelGroupExt save(MemberLabelGroupExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberLabelGroup update(MemberLabelGroup model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberLabelGroup model = new MemberLabelGroup();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberLabelGroupExt findById(int id){
		return extDao.findById(id);
	}

	public MemberLabelGroupExt find(MemberLabelGroupExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberLabelGroupExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberLabelGroupExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberLabelGroupExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberLabelGroupExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberLabelGroupExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberLabelGroupExt> list(MemberLabelGroupExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberLabelGroupExt> paginate(MemberLabelGroupExtExample example) {
		List<MemberLabelGroupExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
