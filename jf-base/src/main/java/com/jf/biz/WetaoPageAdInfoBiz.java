package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WetaoPageAdInfoExtMapper;
import com.jf.dao.WetaoPageAdInfoMapper;
import com.jf.entity.WetaoPageAdInfo;
import com.jf.entity.WetaoPageAdInfoExample;
import com.jf.entity.WetaoPageAdInfoExt;
import com.jf.entity.WetaoPageAdInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WetaoPageAdInfoBiz extends BaseService<WetaoPageAdInfo, WetaoPageAdInfoExample> {

	@Autowired
	private WetaoPageAdInfoMapper dao;
	@Autowired
	private WetaoPageAdInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWetaoPageAdInfoMapper(WetaoPageAdInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WetaoPageAdInfoExt save(WetaoPageAdInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WetaoPageAdInfo update(WetaoPageAdInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WetaoPageAdInfo model = new WetaoPageAdInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WetaoPageAdInfoExt findById(int id){
		return extDao.findById(id);
	}

	public WetaoPageAdInfoExt find(WetaoPageAdInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WetaoPageAdInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WetaoPageAdInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WetaoPageAdInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WetaoPageAdInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WetaoPageAdInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WetaoPageAdInfoExt> list(WetaoPageAdInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WetaoPageAdInfoExt> paginate(WetaoPageAdInfoExtExample example) {
		List<WetaoPageAdInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
