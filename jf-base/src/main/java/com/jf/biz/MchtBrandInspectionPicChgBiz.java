package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandInspectionPicChgExtMapper;
import com.jf.dao.MchtBrandInspectionPicChgMapper;
import com.jf.entity.MchtBrandInspectionPicChg;
import com.jf.entity.MchtBrandInspectionPicChgExample;
import com.jf.entity.MchtBrandInspectionPicChgExt;
import com.jf.entity.MchtBrandInspectionPicChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandInspectionPicChgBiz extends BaseService<MchtBrandInspectionPicChg, MchtBrandInspectionPicChgExample> {

	@Autowired
	private MchtBrandInspectionPicChgMapper dao;
	@Autowired
	private MchtBrandInspectionPicChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandInspectionPicChgMapper(MchtBrandInspectionPicChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandInspectionPicChgExt save(MchtBrandInspectionPicChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandInspectionPicChg update(MchtBrandInspectionPicChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandInspectionPicChg model = new MchtBrandInspectionPicChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandInspectionPicChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandInspectionPicChgExt find(MchtBrandInspectionPicChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandInspectionPicChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandInspectionPicChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandInspectionPicChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandInspectionPicChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandInspectionPicChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandInspectionPicChgExt> list(MchtBrandInspectionPicChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandInspectionPicChgExt> paginate(MchtBrandInspectionPicChgExtExample example) {
		List<MchtBrandInspectionPicChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
