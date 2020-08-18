package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WetaoChannelExtMapper;
import com.jf.dao.WetaoChannelMapper;
import com.jf.entity.WetaoChannel;
import com.jf.entity.WetaoChannelExample;
import com.jf.entity.WetaoChannelExt;
import com.jf.entity.WetaoChannelExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WetaoChannelBiz extends BaseService<WetaoChannel, WetaoChannelExample> {

	@Autowired
	private WetaoChannelMapper dao;
	@Autowired
	private WetaoChannelExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWetaoChannelMapper(WetaoChannelMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WetaoChannelExt save(WetaoChannelExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WetaoChannel update(WetaoChannel model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WetaoChannel model = new WetaoChannel();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WetaoChannelExt findById(int id){
		return extDao.findById(id);
	}

	public WetaoChannelExt find(WetaoChannelExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WetaoChannelExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WetaoChannelExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WetaoChannelExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WetaoChannelExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WetaoChannelExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WetaoChannelExt> list(WetaoChannelExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WetaoChannelExt> paginate(WetaoChannelExtExample example) {
		List<WetaoChannelExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
