package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandInspectionPicExtMapper;
import com.jf.dao.MchtBrandInspectionPicMapper;
import com.jf.entity.MchtBrandInspectionPic;
import com.jf.entity.MchtBrandInspectionPicExample;
import com.jf.entity.MchtBrandInspectionPicExt;
import com.jf.entity.MchtBrandInspectionPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandInspectionPicBiz extends BaseService<MchtBrandInspectionPic, MchtBrandInspectionPicExample> {

	@Autowired
	private MchtBrandInspectionPicMapper dao;
	@Autowired
	private MchtBrandInspectionPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandInspectionPicMapper(MchtBrandInspectionPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandInspectionPicExt save(MchtBrandInspectionPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandInspectionPic update(MchtBrandInspectionPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandInspectionPic model = new MchtBrandInspectionPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandInspectionPicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandInspectionPicExt find(MchtBrandInspectionPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandInspectionPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandInspectionPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandInspectionPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandInspectionPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandInspectionPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandInspectionPicExt> list(MchtBrandInspectionPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandInspectionPicExt> paginate(MchtBrandInspectionPicExtExample example) {
		List<MchtBrandInspectionPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
