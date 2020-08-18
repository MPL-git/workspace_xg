package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandAptitudePicExtMapper;
import com.jf.dao.MchtBrandAptitudePicMapper;
import com.jf.entity.MchtBrandAptitudePic;
import com.jf.entity.MchtBrandAptitudePicExample;
import com.jf.entity.MchtBrandAptitudePicExt;
import com.jf.entity.MchtBrandAptitudePicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandAptitudePicBiz extends BaseService<MchtBrandAptitudePic, MchtBrandAptitudePicExample> {

	@Autowired
	private MchtBrandAptitudePicMapper dao;
	@Autowired
	private MchtBrandAptitudePicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandAptitudePicMapper(MchtBrandAptitudePicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandAptitudePicExt save(MchtBrandAptitudePicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandAptitudePic update(MchtBrandAptitudePic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandAptitudePic model = new MchtBrandAptitudePic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandAptitudePicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandAptitudePicExt find(MchtBrandAptitudePicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandAptitudePicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandAptitudePicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandAptitudePicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandAptitudePicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandAptitudePicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandAptitudePicExt> list(MchtBrandAptitudePicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandAptitudePicExt> paginate(MchtBrandAptitudePicExtExample example) {
		List<MchtBrandAptitudePicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
