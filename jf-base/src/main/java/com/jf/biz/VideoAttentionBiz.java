package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.VideoAttentionExtMapper;
import com.jf.dao.VideoAttentionMapper;
import com.jf.entity.VideoAttention;
import com.jf.entity.VideoAttentionExample;
import com.jf.entity.VideoAttentionExt;
import com.jf.entity.VideoAttentionExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoAttentionBiz extends BaseService<VideoAttention, VideoAttentionExample> {

	@Autowired
	private VideoAttentionMapper dao;
	@Autowired
	private VideoAttentionExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoAttentionMapper(VideoAttentionMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoAttentionExt save(VideoAttentionExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoAttention update(VideoAttention model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoAttention model = new VideoAttention();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoAttentionExt findById(int id){
		return extDao.findById(id);
	}

	public VideoAttentionExt find(VideoAttentionExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoAttentionExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoAttentionExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoAttentionExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoAttentionExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoAttentionExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoAttentionExt> list(VideoAttentionExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoAttentionExt> paginate(VideoAttentionExtExample example) {
		List<VideoAttentionExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
