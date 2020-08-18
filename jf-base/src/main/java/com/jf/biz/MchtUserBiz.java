package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtUserExtMapper;
import com.jf.dao.MchtUserMapper;
import com.jf.entity.MchtUser;
import com.jf.entity.MchtUserExample;
import com.jf.entity.MchtUserExt;
import com.jf.entity.MchtUserExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtUserBiz extends BaseService<MchtUser, MchtUserExample> {

	@Autowired
	private MchtUserMapper dao;
	@Autowired
	private MchtUserExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtUserMapper(MchtUserMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtUserExt save(MchtUserExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtUser update(MchtUser model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtUser model = new MchtUser();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtUserExt findById(int id){
		return extDao.findById(id);
	}

	public MchtUserExt find(MchtUserExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtUserExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtUserExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtUserExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtUserExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtUserExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtUserExt> list(MchtUserExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtUserExt> paginate(MchtUserExtExample example) {
		List<MchtUserExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
