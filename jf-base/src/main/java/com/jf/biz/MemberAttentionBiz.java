package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberAttentionExtMapper;
import com.jf.dao.MemberAttentionMapper;
import com.jf.entity.MemberAttention;
import com.jf.entity.MemberAttentionExample;
import com.jf.entity.MemberAttentionExt;
import com.jf.entity.MemberAttentionExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberAttentionBiz extends BaseService<MemberAttention, MemberAttentionExample> {

	@Autowired
	private MemberAttentionMapper dao;
	@Autowired
	private MemberAttentionExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberAttentionMapper(MemberAttentionMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberAttentionExt save(MemberAttentionExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberAttention update(MemberAttention model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberAttention model = new MemberAttention();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberAttentionExt findById(int id){
		return extDao.findById(id);
	}

	public MemberAttentionExt find(MemberAttentionExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberAttentionExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberAttentionExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberAttentionExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberAttentionExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberAttentionExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberAttentionExt> list(MemberAttentionExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberAttentionExt> paginate(MemberAttentionExtExample example) {
		List<MemberAttentionExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
