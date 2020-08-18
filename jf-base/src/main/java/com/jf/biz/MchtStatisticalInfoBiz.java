package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtStatisticalInfoExtMapper;
import com.jf.dao.MchtStatisticalInfoMapper;
import com.jf.entity.MchtStatisticalInfo;
import com.jf.entity.MchtStatisticalInfoExample;
import com.jf.entity.MchtStatisticalInfoExt;
import com.jf.entity.MchtStatisticalInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtStatisticalInfoBiz extends BaseService<MchtStatisticalInfo, MchtStatisticalInfoExample> {

	@Autowired
	private MchtStatisticalInfoMapper dao;
	@Autowired
	private MchtStatisticalInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtStatisticalInfoMapper(MchtStatisticalInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtStatisticalInfoExt save(MchtStatisticalInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtStatisticalInfo update(MchtStatisticalInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtStatisticalInfo model = new MchtStatisticalInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtStatisticalInfoExt findById(int id){
		return extDao.findById(id);
	}

	public MchtStatisticalInfoExt find(MchtStatisticalInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtStatisticalInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtStatisticalInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtStatisticalInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtStatisticalInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtStatisticalInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtStatisticalInfoExt> list(MchtStatisticalInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtStatisticalInfoExt> paginate(MchtStatisticalInfoExtExample example) {
		List<MchtStatisticalInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
