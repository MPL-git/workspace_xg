package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtLicenseChgExtMapper;
import com.jf.dao.MchtLicenseChgMapper;
import com.jf.entity.MchtLicenseChg;
import com.jf.entity.MchtLicenseChgExample;
import com.jf.entity.MchtLicenseChgExt;
import com.jf.entity.MchtLicenseChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtLicenseChgBiz extends BaseService<MchtLicenseChg, MchtLicenseChgExample> {

	@Autowired
	private MchtLicenseChgMapper dao;
	@Autowired
	private MchtLicenseChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtLicenseChgMapper(MchtLicenseChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtLicenseChgExt save(MchtLicenseChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtLicenseChg update(MchtLicenseChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtLicenseChg model = new MchtLicenseChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtLicenseChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtLicenseChgExt find(MchtLicenseChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtLicenseChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtLicenseChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtLicenseChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtLicenseChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtLicenseChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtLicenseChgExt> list(MchtLicenseChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtLicenseChgExt> paginate(MchtLicenseChgExtExample example) {
		List<MchtLicenseChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
