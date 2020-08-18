package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoMapper;
import com.jf.dao.VideoExtMapper;
import com.jf.entity.Video;
import com.jf.entity.VideoExample;
import com.jf.entity.VideoExt;
import com.jf.entity.VideoExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoBiz extends BaseService<Video, VideoExample> {

	@Autowired
	private VideoMapper dao;
	@Autowired
	private VideoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoMapper(VideoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoExt save(VideoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Video update(Video model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Video model = new Video();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoExt findById(int id){
		return extDao.findById(id);
	}

	public VideoExt find(VideoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoExt> list(VideoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoExt> paginate(VideoExtExample example) {
		List<VideoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
