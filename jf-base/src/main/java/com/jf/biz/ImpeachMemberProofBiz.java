package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ImpeachMemberProofExtMapper;
import com.jf.dao.ImpeachMemberProofMapper;
import com.jf.entity.ImpeachMemberProof;
import com.jf.entity.ImpeachMemberProofExample;
import com.jf.entity.ImpeachMemberProofExt;
import com.jf.entity.ImpeachMemberProofExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImpeachMemberProofBiz extends BaseService<ImpeachMemberProof, ImpeachMemberProofExample> {

	@Autowired
	private ImpeachMemberProofMapper dao;
	@Autowired
	private ImpeachMemberProofExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setImpeachMemberProofMapper(ImpeachMemberProofMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ImpeachMemberProofExt save(ImpeachMemberProofExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ImpeachMemberProof update(ImpeachMemberProof model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ImpeachMemberProof model = new ImpeachMemberProof();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ImpeachMemberProofExt findById(int id){
		return extDao.findById(id);
	}

	public ImpeachMemberProofExt find(ImpeachMemberProofExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ImpeachMemberProofExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ImpeachMemberProofExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ImpeachMemberProofExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ImpeachMemberProofExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ImpeachMemberProofExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ImpeachMemberProofExt> list(ImpeachMemberProofExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ImpeachMemberProofExt> paginate(ImpeachMemberProofExtExample example) {
		List<ImpeachMemberProofExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
