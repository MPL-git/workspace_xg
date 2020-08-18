package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoCommentMapper;
import com.jf.dao.VideoCommentExtMapper;
import com.jf.entity.VideoComment;
import com.jf.entity.VideoCommentExample;
import com.jf.entity.VideoCommentExt;
import com.jf.entity.VideoCommentExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoCommentBiz extends BaseService<VideoComment, VideoCommentExample> {

	@Autowired
	private VideoCommentMapper dao;
	@Autowired
	private VideoCommentExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoCommentMapper(VideoCommentMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoCommentExt save(VideoCommentExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoComment update(VideoComment model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoComment model = new VideoComment();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoCommentExt findById(int id){
		return extDao.findById(id);
	}

	public VideoCommentExt find(VideoCommentExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoCommentExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoCommentExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoCommentExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoCommentExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoCommentExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoCommentExt> list(VideoCommentExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoCommentExt> paginate(VideoCommentExtExample example) {
		List<VideoCommentExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
