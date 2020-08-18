package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtInfoExtMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MchtInfoExt;
import com.jf.entity.MchtInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtInfoBiz extends BaseService<MchtInfo, MchtInfoExample> {

	@Autowired
	private MchtInfoMapper dao;
	@Autowired
	private MchtInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtInfoMapper(MchtInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtInfoExt save(MchtInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtInfo update(MchtInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtInfo model = new MchtInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtInfoExt findById(int id){
		return extDao.findById(id);
	}

	public MchtInfoExt find(MchtInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtInfoExt> list(MchtInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtInfoExt> paginate(MchtInfoExtExample example) {
		List<MchtInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
