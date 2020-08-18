package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberLabelTypeExtMapper;
import com.jf.dao.MemberLabelTypeMapper;
import com.jf.entity.MemberLabelType;
import com.jf.entity.MemberLabelTypeExample;
import com.jf.entity.MemberLabelTypeExt;
import com.jf.entity.MemberLabelTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberLabelTypeBiz extends BaseService<MemberLabelType, MemberLabelTypeExample> {

	@Autowired
	private MemberLabelTypeMapper dao;
	@Autowired
	private MemberLabelTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberLabelTypeMapper(MemberLabelTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberLabelTypeExt save(MemberLabelTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberLabelType update(MemberLabelType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberLabelType model = new MemberLabelType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberLabelTypeExt findById(int id){
		return extDao.findById(id);
	}

	public MemberLabelTypeExt find(MemberLabelTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberLabelTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberLabelTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberLabelTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberLabelTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberLabelTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberLabelTypeExt> list(MemberLabelTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberLabelTypeExt> paginate(MemberLabelTypeExtExample example) {
		List<MemberLabelTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
