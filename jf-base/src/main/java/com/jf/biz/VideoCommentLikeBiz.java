package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoCommentLikeMapper;
import com.jf.dao.VideoCommentLikeExtMapper;
import com.jf.entity.VideoCommentLike;
import com.jf.entity.VideoCommentLikeExample;
import com.jf.entity.VideoCommentLikeExt;
import com.jf.entity.VideoCommentLikeExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoCommentLikeBiz extends BaseService<VideoCommentLike, VideoCommentLikeExample> {

	@Autowired
	private VideoCommentLikeMapper dao;
	@Autowired
	private VideoCommentLikeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoCommentLikeMapper(VideoCommentLikeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoCommentLikeExt save(VideoCommentLikeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoCommentLike update(VideoCommentLike model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoCommentLike model = new VideoCommentLike();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoCommentLikeExt findById(int id){
		return extDao.findById(id);
	}

	public VideoCommentLikeExt find(VideoCommentLikeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoCommentLikeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoCommentLikeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoCommentLikeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoCommentLikeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoCommentLikeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoCommentLikeExt> list(VideoCommentLikeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoCommentLikeExt> paginate(VideoCommentLikeExtExample example) {
		List<VideoCommentLikeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
