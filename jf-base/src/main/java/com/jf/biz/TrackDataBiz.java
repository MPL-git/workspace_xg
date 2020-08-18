package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.TrackDataExtMapper;
import com.jf.dao.TrackDataMapper;
import com.jf.entity.TrackData;
import com.jf.entity.TrackDataExample;
import com.jf.entity.TrackDataExt;
import com.jf.entity.TrackDataExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrackDataBiz extends BaseService<TrackData, TrackDataExample> {

	@Autowired
	private TrackDataMapper dao;
	@Autowired
	private TrackDataExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTrackDataMapper(TrackDataMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TrackDataExt save(TrackDataExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public TrackData update(TrackData model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		TrackData model = new TrackData();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TrackDataExt findById(int id){
		return extDao.findById(id);
	}

	public TrackDataExt find(TrackDataExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TrackDataExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TrackDataExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TrackDataExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TrackDataExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TrackDataExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TrackDataExt> list(TrackDataExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TrackDataExt> paginate(TrackDataExtExample example) {
		List<TrackDataExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
