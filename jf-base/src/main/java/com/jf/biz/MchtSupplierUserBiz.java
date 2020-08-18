package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSupplierUserExtMapper;
import com.jf.dao.MchtSupplierUserMapper;
import com.jf.entity.MchtSupplierUser;
import com.jf.entity.MchtSupplierUserExample;
import com.jf.entity.MchtSupplierUserExt;
import com.jf.entity.MchtSupplierUserExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtSupplierUserBiz extends BaseService<MchtSupplierUser, MchtSupplierUserExample> {

	@Autowired
	private MchtSupplierUserMapper dao;
	@Autowired
	private MchtSupplierUserExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtSupplierUserMapper(MchtSupplierUserMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtSupplierUserExt save(MchtSupplierUserExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtSupplierUser update(MchtSupplierUser model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtSupplierUser model = new MchtSupplierUser();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtSupplierUserExt findById(int id){
		return extDao.findById(id);
	}

	public MchtSupplierUserExt find(MchtSupplierUserExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtSupplierUserExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtSupplierUserExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtSupplierUserExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtSupplierUserExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtSupplierUserExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtSupplierUserExt> list(MchtSupplierUserExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtSupplierUserExt> paginate(MchtSupplierUserExtExample example) {
		List<MchtSupplierUserExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
