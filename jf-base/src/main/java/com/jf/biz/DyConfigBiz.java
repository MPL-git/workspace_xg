package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DyConfigExtMapper;
import com.jf.dao.DyConfigMapper;
import com.jf.entity.DyConfig;
import com.jf.entity.DyConfigExample;
import com.jf.entity.DyConfigExt;
import com.jf.entity.DyConfigExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DyConfigBiz extends BaseService<DyConfig, DyConfigExample> {

	@Autowired
	private DyConfigMapper dao;
	@Autowired
	private DyConfigExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDyConfigMapper(DyConfigMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DyConfigExt save(DyConfigExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DyConfig update(DyConfig model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DyConfig model = new DyConfig();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DyConfigExt findById(int id){
		return extDao.findById(id);
	}

	public DyConfigExt find(DyConfigExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DyConfigExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DyConfigExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DyConfigExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DyConfigExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DyConfigExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DyConfigExt> list(DyConfigExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DyConfigExt> paginate(DyConfigExtExample example) {
		List<DyConfigExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
