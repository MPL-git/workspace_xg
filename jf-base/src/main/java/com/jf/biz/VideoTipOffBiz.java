package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoTipOffMapper;
import com.jf.dao.VideoTipOffExtMapper;
import com.jf.entity.VideoTipOff;
import com.jf.entity.VideoTipOffExample;
import com.jf.entity.VideoTipOffExt;
import com.jf.entity.VideoTipOffExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoTipOffBiz extends BaseService<VideoTipOff, VideoTipOffExample> {

	@Autowired
	private VideoTipOffMapper dao;
	@Autowired
	private VideoTipOffExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoTipOffMapper(VideoTipOffMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoTipOffExt save(VideoTipOffExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoTipOff update(VideoTipOff model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoTipOff model = new VideoTipOff();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoTipOffExt findById(int id){
		return extDao.findById(id);
	}

	public VideoTipOffExt find(VideoTipOffExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoTipOffExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoTipOffExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoTipOffExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoTipOffExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoTipOffExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoTipOffExt> list(VideoTipOffExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoTipOffExt> paginate(VideoTipOffExtExample example) {
		List<VideoTipOffExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
