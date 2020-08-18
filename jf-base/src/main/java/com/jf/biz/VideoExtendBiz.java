package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoExtendMapper;
import com.jf.dao.VideoExtendExtMapper;
import com.jf.entity.VideoExtend;
import com.jf.entity.VideoExtendExample;
import com.jf.entity.VideoExtendExt;
import com.jf.entity.VideoExtendExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoExtendBiz extends BaseService<VideoExtend, VideoExtendExample> {

	@Autowired
	private VideoExtendMapper dao;
	@Autowired
	private VideoExtendExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoExtendMapper(VideoExtendMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoExtendExt save(VideoExtendExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoExtend update(VideoExtend model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoExtend model = new VideoExtend();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoExtendExt findById(int id){
		return extDao.findById(id);
	}

	public VideoExtendExt find(VideoExtendExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoExtendExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoExtendExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoExtendExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoExtendExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoExtendExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoExtendExt> list(VideoExtendExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoExtendExt> paginate(VideoExtendExtExample example) {
		List<VideoExtendExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
