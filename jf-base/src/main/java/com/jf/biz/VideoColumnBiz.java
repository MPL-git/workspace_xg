package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoColumnMapper;
import com.jf.dao.VideoColumnExtMapper;
import com.jf.entity.VideoColumn;
import com.jf.entity.VideoColumnExample;
import com.jf.entity.VideoColumnExt;
import com.jf.entity.VideoColumnExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoColumnBiz extends BaseService<VideoColumn, VideoColumnExample> {

	@Autowired
	private VideoColumnMapper dao;
	@Autowired
	private VideoColumnExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoColumnMapper(VideoColumnMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoColumnExt save(VideoColumnExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoColumn update(VideoColumn model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoColumn model = new VideoColumn();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoColumnExt findById(int id){
		return extDao.findById(id);
	}

	public VideoColumnExt find(VideoColumnExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoColumnExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoColumnExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoColumnExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoColumnExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoColumnExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoColumnExt> list(VideoColumnExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoColumnExt> paginate(VideoColumnExtExample example) {
		List<VideoColumnExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
