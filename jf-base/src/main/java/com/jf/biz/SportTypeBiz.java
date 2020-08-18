package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SportTypeExtMapper;
import com.jf.dao.SportTypeMapper;
import com.jf.entity.SportType;
import com.jf.entity.SportTypeExample;
import com.jf.entity.SportTypeExt;
import com.jf.entity.SportTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SportTypeBiz extends BaseService<SportType, SportTypeExample> {

	@Autowired
	private SportTypeMapper dao;
	@Autowired
	private SportTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSportTypeMapper(SportTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SportTypeExt save(SportTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SportType update(SportType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SportType model = new SportType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SportTypeExt findById(int id){
		return extDao.findById(id);
	}

	public SportTypeExt find(SportTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SportTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SportTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SportTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SportTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SportTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SportTypeExt> list(SportTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SportTypeExt> paginate(SportTypeExtExample example) {
		List<SportTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
