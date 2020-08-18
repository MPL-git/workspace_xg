package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandAptitudePicChgExtMapper;
import com.jf.dao.MchtBrandAptitudePicChgMapper;
import com.jf.entity.MchtBrandAptitudePicChg;
import com.jf.entity.MchtBrandAptitudePicChgExample;
import com.jf.entity.MchtBrandAptitudePicChgExt;
import com.jf.entity.MchtBrandAptitudePicChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandAptitudePicChgBiz extends BaseService<MchtBrandAptitudePicChg, MchtBrandAptitudePicChgExample> {

	@Autowired
	private MchtBrandAptitudePicChgMapper dao;
	@Autowired
	private MchtBrandAptitudePicChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandAptitudePicChgMapper(MchtBrandAptitudePicChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandAptitudePicChgExt save(MchtBrandAptitudePicChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandAptitudePicChg update(MchtBrandAptitudePicChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandAptitudePicChg model = new MchtBrandAptitudePicChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandAptitudePicChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandAptitudePicChgExt find(MchtBrandAptitudePicChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandAptitudePicChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandAptitudePicChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandAptitudePicChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandAptitudePicChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandAptitudePicChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandAptitudePicChgExt> list(MchtBrandAptitudePicChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandAptitudePicChgExt> paginate(MchtBrandAptitudePicChgExtExample example) {
		List<MchtBrandAptitudePicChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
