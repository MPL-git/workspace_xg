package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoCommentReplyMapper;
import com.jf.dao.VideoCommentReplyExtMapper;
import com.jf.entity.VideoCommentReply;
import com.jf.entity.VideoCommentReplyExample;
import com.jf.entity.VideoCommentReplyExt;
import com.jf.entity.VideoCommentReplyExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoCommentReplyBiz extends BaseService<VideoCommentReply, VideoCommentReplyExample> {

	@Autowired
	private VideoCommentReplyMapper dao;
	@Autowired
	private VideoCommentReplyExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoCommentReplyMapper(VideoCommentReplyMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoCommentReplyExt save(VideoCommentReplyExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoCommentReply update(VideoCommentReply model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoCommentReply model = new VideoCommentReply();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoCommentReplyExt findById(int id){
		return extDao.findById(id);
	}

	public VideoCommentReplyExt find(VideoCommentReplyExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoCommentReplyExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoCommentReplyExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoCommentReplyExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoCommentReplyExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoCommentReplyExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoCommentReplyExt> list(VideoCommentReplyExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoCommentReplyExt> paginate(VideoCommentReplyExtExample example) {
		List<VideoCommentReplyExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
