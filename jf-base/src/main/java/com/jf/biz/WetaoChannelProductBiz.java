package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WetaoChannelProductExtMapper;
import com.jf.dao.WetaoChannelProductMapper;
import com.jf.entity.WetaoChannelProduct;
import com.jf.entity.WetaoChannelProductExample;
import com.jf.entity.WetaoChannelProductExt;
import com.jf.entity.WetaoChannelProductExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WetaoChannelProductBiz extends BaseService<WetaoChannelProduct, WetaoChannelProductExample> {

	@Autowired
	private WetaoChannelProductMapper dao;
	@Autowired
	private WetaoChannelProductExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWetaoChannelProductMapper(WetaoChannelProductMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WetaoChannelProductExt save(WetaoChannelProductExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WetaoChannelProduct update(WetaoChannelProduct model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WetaoChannelProduct model = new WetaoChannelProduct();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WetaoChannelProductExt findById(int id){
		return extDao.findById(id);
	}

	public WetaoChannelProductExt find(WetaoChannelProductExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WetaoChannelProductExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WetaoChannelProductExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WetaoChannelProductExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WetaoChannelProductExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WetaoChannelProductExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WetaoChannelProductExt> list(WetaoChannelProductExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WetaoChannelProductExt> paginate(WetaoChannelProductExtExample example) {
		List<WetaoChannelProductExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
