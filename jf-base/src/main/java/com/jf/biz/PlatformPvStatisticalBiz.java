package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformPvStatisticalExtMapper;
import com.jf.dao.PlatformPvStatisticalMapper;
import com.jf.entity.PlatformPvStatistical;
import com.jf.entity.PlatformPvStatisticalExample;
import com.jf.entity.PlatformPvStatisticalExt;
import com.jf.entity.PlatformPvStatisticalExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlatformPvStatisticalBiz extends BaseService<PlatformPvStatistical, PlatformPvStatisticalExample> {

	@Autowired
	private PlatformPvStatisticalMapper dao;
	@Autowired
	private PlatformPvStatisticalExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPlatformPvStatisticalMapper(PlatformPvStatisticalMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PlatformPvStatisticalExt save(PlatformPvStatisticalExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PlatformPvStatistical update(PlatformPvStatistical model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PlatformPvStatistical model = new PlatformPvStatistical();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PlatformPvStatisticalExt findById(int id){
		return extDao.findById(id);
	}

	public PlatformPvStatisticalExt find(PlatformPvStatisticalExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PlatformPvStatisticalExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PlatformPvStatisticalExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PlatformPvStatisticalExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PlatformPvStatisticalExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PlatformPvStatisticalExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PlatformPvStatisticalExt> list(PlatformPvStatisticalExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PlatformPvStatisticalExt> paginate(PlatformPvStatisticalExtExample example) {
		List<PlatformPvStatisticalExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
