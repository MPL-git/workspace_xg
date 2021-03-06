package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ThirdPlatformProductInfoExtMapper;
import com.jf.dao.ThirdPlatformProductInfoMapper;
import com.jf.entity.ThirdPlatformProductInfo;
import com.jf.entity.ThirdPlatformProductInfoExample;
import com.jf.entity.ThirdPlatformProductInfoExt;
import com.jf.entity.ThirdPlatformProductInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ThirdPlatformProductInfoBiz extends BaseService<ThirdPlatformProductInfo, ThirdPlatformProductInfoExample> {

	@Autowired
	private ThirdPlatformProductInfoMapper dao;
	@Autowired
	private ThirdPlatformProductInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setThirdPlatformProductInfoMapper(ThirdPlatformProductInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ThirdPlatformProductInfoExt save(ThirdPlatformProductInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ThirdPlatformProductInfo update(ThirdPlatformProductInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ThirdPlatformProductInfo model = new ThirdPlatformProductInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ThirdPlatformProductInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ThirdPlatformProductInfoExt find(ThirdPlatformProductInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ThirdPlatformProductInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ThirdPlatformProductInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ThirdPlatformProductInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ThirdPlatformProductInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ThirdPlatformProductInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ThirdPlatformProductInfoExt> list(ThirdPlatformProductInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ThirdPlatformProductInfoExt> paginate(ThirdPlatformProductInfoExtExample example) {
		List<ThirdPlatformProductInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
