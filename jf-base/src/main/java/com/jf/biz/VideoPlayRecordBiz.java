package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoPlayRecordMapper;
import com.jf.dao.VideoPlayRecordExtMapper;
import com.jf.entity.VideoPlayRecord;
import com.jf.entity.VideoPlayRecordExample;
import com.jf.entity.VideoPlayRecordExt;
import com.jf.entity.VideoPlayRecordExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoPlayRecordBiz extends BaseService<VideoPlayRecord, VideoPlayRecordExample> {

	@Autowired
	private VideoPlayRecordMapper dao;
	@Autowired
	private VideoPlayRecordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoPlayRecordMapper(VideoPlayRecordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoPlayRecordExt save(VideoPlayRecordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoPlayRecord update(VideoPlayRecord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoPlayRecord model = new VideoPlayRecord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoPlayRecordExt findById(int id){
		return extDao.findById(id);
	}

	public VideoPlayRecordExt find(VideoPlayRecordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoPlayRecordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoPlayRecordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoPlayRecordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoPlayRecordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoPlayRecordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoPlayRecordExt> list(VideoPlayRecordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoPlayRecordExt> paginate(VideoPlayRecordExtExample example) {
		List<VideoPlayRecordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
