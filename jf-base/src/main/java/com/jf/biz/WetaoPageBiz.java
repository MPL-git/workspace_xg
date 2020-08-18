package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WetaoPageExtMapper;
import com.jf.dao.WetaoPageMapper;
import com.jf.entity.WetaoPage;
import com.jf.entity.WetaoPageExample;
import com.jf.entity.WetaoPageExt;
import com.jf.entity.WetaoPageExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WetaoPageBiz extends BaseService<WetaoPage, WetaoPageExample> {

	@Autowired
	private WetaoPageMapper dao;
	@Autowired
	private WetaoPageExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWetaoPageMapper(WetaoPageMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WetaoPageExt save(WetaoPageExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WetaoPage update(WetaoPage model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WetaoPage model = new WetaoPage();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WetaoPageExt findById(int id){
		return extDao.findById(id);
	}

	public WetaoPageExt find(WetaoPageExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WetaoPageExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WetaoPageExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WetaoPageExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WetaoPageExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WetaoPageExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WetaoPageExt> list(WetaoPageExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WetaoPageExt> paginate(WetaoPageExtExample example) {
		List<WetaoPageExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
