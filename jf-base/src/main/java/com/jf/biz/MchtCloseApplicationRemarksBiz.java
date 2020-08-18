package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtCloseApplicationRemarksExtMapper;
import com.jf.dao.MchtCloseApplicationRemarksMapper;
import com.jf.entity.MchtCloseApplicationRemarks;
import com.jf.entity.MchtCloseApplicationRemarksExample;
import com.jf.entity.MchtCloseApplicationRemarksExt;
import com.jf.entity.MchtCloseApplicationRemarksExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtCloseApplicationRemarksBiz extends BaseService<MchtCloseApplicationRemarks, MchtCloseApplicationRemarksExample> {

	@Autowired
	private MchtCloseApplicationRemarksMapper dao;
	@Autowired
	private MchtCloseApplicationRemarksExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtCloseApplicationRemarksMapper(MchtCloseApplicationRemarksMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtCloseApplicationRemarksExt save(MchtCloseApplicationRemarksExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtCloseApplicationRemarks update(MchtCloseApplicationRemarks model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtCloseApplicationRemarks model = new MchtCloseApplicationRemarks();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtCloseApplicationRemarksExt findById(int id){
		return extDao.findById(id);
	}

	public MchtCloseApplicationRemarksExt find(MchtCloseApplicationRemarksExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtCloseApplicationRemarksExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtCloseApplicationRemarksExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtCloseApplicationRemarksExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtCloseApplicationRemarksExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtCloseApplicationRemarksExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtCloseApplicationRemarksExt> list(MchtCloseApplicationRemarksExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtCloseApplicationRemarksExt> paginate(MchtCloseApplicationRemarksExtExample example) {
		List<MchtCloseApplicationRemarksExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
