package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberLabelExtMapper;
import com.jf.dao.MemberLabelMapper;
import com.jf.entity.MemberLabel;
import com.jf.entity.MemberLabelExample;
import com.jf.entity.MemberLabelExt;
import com.jf.entity.MemberLabelExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberLabelBiz extends BaseService<MemberLabel, MemberLabelExample> {

	@Autowired
	private MemberLabelMapper dao;
	@Autowired
	private MemberLabelExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberLabelMapper(MemberLabelMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberLabelExt save(MemberLabelExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberLabel update(MemberLabel model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberLabel model = new MemberLabel();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberLabelExt findById(int id){
		return extDao.findById(id);
	}

	public MemberLabelExt find(MemberLabelExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberLabelExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberLabelExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberLabelExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberLabelExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberLabelExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberLabelExt> list(MemberLabelExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberLabelExt> paginate(MemberLabelExtExample example) {
		List<MemberLabelExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
