package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberNovaRecordExtMapper;
import com.jf.dao.MemberNovaRecordMapper;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.MemberNovaRecordExample;
import com.jf.entity.MemberNovaRecordExt;
import com.jf.entity.MemberNovaRecordExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberNovaRecordBiz extends BaseService<MemberNovaRecord, MemberNovaRecordExample> {

	@Autowired
	private MemberNovaRecordMapper dao;
	@Autowired
	private MemberNovaRecordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberNovaRecordMapper(MemberNovaRecordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberNovaRecordExt save(MemberNovaRecordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberNovaRecord update(MemberNovaRecord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberNovaRecord model = new MemberNovaRecord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberNovaRecordExt findById(int id){
		return extDao.findById(id);
	}

	public MemberNovaRecordExt find(MemberNovaRecordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberNovaRecordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberNovaRecordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberNovaRecordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberNovaRecordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberNovaRecordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberNovaRecordExt> list(MemberNovaRecordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberNovaRecordExt> paginate(MemberNovaRecordExtExample example) {
		List<MemberNovaRecordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
