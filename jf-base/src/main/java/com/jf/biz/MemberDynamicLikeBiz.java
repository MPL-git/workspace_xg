package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberDynamicLikeExtMapper;
import com.jf.dao.MemberDynamicLikeMapper;
import com.jf.entity.MemberDynamicLike;
import com.jf.entity.MemberDynamicLikeExample;
import com.jf.entity.MemberDynamicLikeExt;
import com.jf.entity.MemberDynamicLikeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberDynamicLikeBiz extends BaseService<MemberDynamicLike, MemberDynamicLikeExample> {

	@Autowired
	private MemberDynamicLikeMapper dao;
	@Autowired
	private MemberDynamicLikeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberDynamicLikeMapper(MemberDynamicLikeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberDynamicLikeExt save(MemberDynamicLikeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberDynamicLike update(MemberDynamicLike model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberDynamicLike model = new MemberDynamicLike();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberDynamicLikeExt findById(int id){
		return extDao.findById(id);
	}

	public MemberDynamicLikeExt find(MemberDynamicLikeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberDynamicLikeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberDynamicLikeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberDynamicLikeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberDynamicLikeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberDynamicLikeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberDynamicLikeExt> list(MemberDynamicLikeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberDynamicLikeExt> paginate(MemberDynamicLikeExtExample example) {
		List<MemberDynamicLikeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
