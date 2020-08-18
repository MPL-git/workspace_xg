package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.VideoPvStatisticalMapper;
import com.jf.dao.VideoPvStatisticalExtMapper;
import com.jf.entity.VideoPvStatistical;
import com.jf.entity.VideoPvStatisticalExample;
import com.jf.entity.VideoPvStatisticalExt;
import com.jf.entity.VideoPvStatisticalExtExample;
import com.jf.common.base.BaseService;

@Service
public class VideoPvStatisticalBiz extends BaseService<VideoPvStatistical, VideoPvStatisticalExample> {

	@Autowired
	private VideoPvStatisticalMapper dao;
	@Autowired
	private VideoPvStatisticalExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setVideoPvStatisticalMapper(VideoPvStatisticalMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public VideoPvStatisticalExt save(VideoPvStatisticalExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public VideoPvStatistical update(VideoPvStatistical model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		VideoPvStatistical model = new VideoPvStatistical();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public VideoPvStatisticalExt findById(int id){
		return extDao.findById(id);
	}

	public VideoPvStatisticalExt find(VideoPvStatisticalExtExample example){
		return extDao.find(example.fill());
	}

	public int count(VideoPvStatisticalExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, VideoPvStatisticalExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, VideoPvStatisticalExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, VideoPvStatisticalExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(VideoPvStatisticalExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<VideoPvStatisticalExt> list(VideoPvStatisticalExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<VideoPvStatisticalExt> paginate(VideoPvStatisticalExtExample example) {
		List<VideoPvStatisticalExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
