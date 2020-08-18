package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberDynamicExtMapper;
import com.jf.dao.MemberDynamicMapper;
import com.jf.entity.MemberDynamic;
import com.jf.entity.MemberDynamicExample;
import com.jf.entity.MemberDynamicExt;
import com.jf.entity.MemberDynamicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberDynamicBiz extends BaseService<MemberDynamic, MemberDynamicExample> {

	@Autowired
	private MemberDynamicMapper dao;
	@Autowired
	private MemberDynamicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberDynamicMapper(MemberDynamicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberDynamicExt save(MemberDynamicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberDynamic update(MemberDynamic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberDynamic model = new MemberDynamic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberDynamicExt findById(int id){
		return extDao.findById(id);
	}

	public MemberDynamicExt find(MemberDynamicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberDynamicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberDynamicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberDynamicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberDynamicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberDynamicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberDynamicExt> list(MemberDynamicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberDynamicExt> paginate(MemberDynamicExtExample example) {
		List<MemberDynamicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
