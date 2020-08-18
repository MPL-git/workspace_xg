package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ViolatePunishStandardExtMapper;
import com.jf.dao.ViolatePunishStandardMapper;
import com.jf.entity.ViolatePunishStandard;
import com.jf.entity.ViolatePunishStandardExample;
import com.jf.entity.ViolatePunishStandardExt;
import com.jf.entity.ViolatePunishStandardExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ViolatePunishStandardBiz extends BaseService<ViolatePunishStandard, ViolatePunishStandardExample> {

	@Autowired
	private ViolatePunishStandardMapper dao;
	@Autowired
	private ViolatePunishStandardExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setViolatePunishStandardMapper(ViolatePunishStandardMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ViolatePunishStandardExt save(ViolatePunishStandardExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ViolatePunishStandard update(ViolatePunishStandard model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ViolatePunishStandard model = new ViolatePunishStandard();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ViolatePunishStandardExt findById(int id){
		return extDao.findById(id);
	}

	public ViolatePunishStandardExt find(ViolatePunishStandardExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ViolatePunishStandardExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ViolatePunishStandardExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ViolatePunishStandardExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ViolatePunishStandardExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ViolatePunishStandardExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ViolatePunishStandardExt> list(ViolatePunishStandardExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ViolatePunishStandardExt> paginate(ViolatePunishStandardExtExample example) {
		List<ViolatePunishStandardExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
