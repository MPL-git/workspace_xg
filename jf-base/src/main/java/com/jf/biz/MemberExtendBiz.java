package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberExtendExtMapper;
import com.jf.dao.MemberExtendMapper;
import com.jf.entity.MemberExtend;
import com.jf.entity.MemberExtendExample;
import com.jf.entity.MemberExtendExt;
import com.jf.entity.MemberExtendExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberExtendBiz extends BaseService<MemberExtend, MemberExtendExample> {

	@Autowired
	private MemberExtendMapper dao;
	@Autowired
	private MemberExtendExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberExtendMapper(MemberExtendMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberExtendExt save(MemberExtendExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberExtend update(MemberExtend model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberExtend model = new MemberExtend();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberExtendExt findById(int id){
		return extDao.findById(id);
	}

	public MemberExtendExt find(MemberExtendExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberExtendExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberExtendExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberExtendExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberExtendExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberExtendExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberExtendExt> list(MemberExtendExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberExtendExt> paginate(MemberExtendExtExample example) {
		List<MemberExtendExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
