package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AdPvStatisticalExtMapper;
import com.jf.dao.AdPvStatisticalMapper;
import com.jf.entity.AdPvStatistical;
import com.jf.entity.AdPvStatisticalExample;
import com.jf.entity.AdPvStatisticalExt;
import com.jf.entity.AdPvStatisticalExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdPvStatisticalBiz extends BaseService<AdPvStatistical, AdPvStatisticalExample> {

	@Autowired
	private AdPvStatisticalMapper dao;
	@Autowired
	private AdPvStatisticalExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAdPvStatisticalMapper(AdPvStatisticalMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AdPvStatisticalExt save(AdPvStatisticalExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AdPvStatistical update(AdPvStatistical model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AdPvStatistical model = new AdPvStatistical();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AdPvStatisticalExt findById(int id){
		return extDao.findById(id);
	}

	public AdPvStatisticalExt find(AdPvStatisticalExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AdPvStatisticalExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AdPvStatisticalExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AdPvStatisticalExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AdPvStatisticalExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AdPvStatisticalExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AdPvStatisticalExt> list(AdPvStatisticalExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AdPvStatisticalExt> paginate(AdPvStatisticalExtExample example) {
		List<AdPvStatisticalExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
