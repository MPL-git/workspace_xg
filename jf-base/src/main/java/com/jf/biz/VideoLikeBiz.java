package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoLikeMapper;
import com.jf.dao.VideoLikeExtMapper;
import com.jf.entity.VideoLike;
import com.jf.entity.VideoLikeExample;
import com.jf.entity.VideoLikeExt;
import com.jf.entity.VideoLikeExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoLikeBiz extends BaseService<VideoLike, VideoLikeExample> {

	@Autowired
	private VideoLikeMapper dao;
	@Autowired
	private VideoLikeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoLikeMapper(VideoLikeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoLikeExt save(VideoLikeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoLike update(VideoLike model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoLike model = new VideoLike();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoLikeExt findById(int id){
		return extDao.findById(id);
	}

	public VideoLikeExt find(VideoLikeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoLikeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoLikeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoLikeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoLikeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoLikeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoLikeExt> list(VideoLikeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoLikeExt> paginate(VideoLikeExtExample example) {
		List<VideoLikeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
