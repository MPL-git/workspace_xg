package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtPvStatisticalExtMapper;
import com.jf.dao.MchtPvStatisticalMapper;
import com.jf.entity.MchtPvStatistical;
import com.jf.entity.MchtPvStatisticalExample;
import com.jf.entity.MchtPvStatisticalExt;
import com.jf.entity.MchtPvStatisticalExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtPvStatisticalBiz extends BaseService<MchtPvStatistical, MchtPvStatisticalExample> {

	@Autowired
	private MchtPvStatisticalMapper dao;
	@Autowired
	private MchtPvStatisticalExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtPvStatisticalMapper(MchtPvStatisticalMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtPvStatisticalExt save(MchtPvStatisticalExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtPvStatistical update(MchtPvStatistical model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtPvStatistical model = new MchtPvStatistical();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtPvStatisticalExt findById(int id){
		return extDao.findById(id);
	}

	public MchtPvStatisticalExt find(MchtPvStatisticalExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtPvStatisticalExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtPvStatisticalExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtPvStatisticalExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtPvStatisticalExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtPvStatisticalExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtPvStatisticalExt> list(MchtPvStatisticalExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtPvStatisticalExt> paginate(MchtPvStatisticalExtExample example) {
		List<MchtPvStatisticalExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
