package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DecorateInfoExtMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateInfoExample;
import com.jf.entity.DecorateInfoExt;
import com.jf.entity.DecorateInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DecorateInfoBiz extends BaseService<DecorateInfo, DecorateInfoExample> {

	@Autowired
	private DecorateInfoMapper dao;
	@Autowired
	private DecorateInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDecorateInfoMapper(DecorateInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DecorateInfoExt save(DecorateInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DecorateInfo update(DecorateInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DecorateInfo model = new DecorateInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DecorateInfoExt findById(int id){
		return extDao.findById(id);
	}

	public DecorateInfoExt find(DecorateInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DecorateInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DecorateInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DecorateInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DecorateInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DecorateInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DecorateInfoExt> list(DecorateInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DecorateInfoExt> paginate(DecorateInfoExtExample example) {
		List<DecorateInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
