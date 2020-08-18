package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SpUserExtMapper;
import com.jf.dao.SpUserMapper;
import com.jf.entity.SpUser;
import com.jf.entity.SpUserExample;
import com.jf.entity.SpUserExt;
import com.jf.entity.SpUserExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpUserBiz extends BaseService<SpUser, SpUserExample> {

	@Autowired
	private SpUserMapper dao;
	@Autowired
	private SpUserExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSpUserMapper(SpUserMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SpUserExt save(SpUserExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SpUser update(SpUser model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SpUser model = new SpUser();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SpUserExt findById(int id){
		return extDao.findById(id);
	}

	public SpUserExt find(SpUserExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SpUserExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SpUserExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SpUserExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SpUserExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SpUserExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SpUserExt> list(SpUserExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SpUserExt> paginate(SpUserExtExample example) {
		List<SpUserExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
