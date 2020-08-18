package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ObligeePicExtMapper;
import com.jf.dao.ObligeePicMapper;
import com.jf.entity.ObligeePic;
import com.jf.entity.ObligeePicExample;
import com.jf.entity.ObligeePicExt;
import com.jf.entity.ObligeePicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ObligeePicBiz extends BaseService<ObligeePic, ObligeePicExample> {

	@Autowired
	private ObligeePicMapper dao;
	@Autowired
	private ObligeePicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setObligeePicMapper(ObligeePicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ObligeePicExt save(ObligeePicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ObligeePic update(ObligeePic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ObligeePic model = new ObligeePic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ObligeePicExt findById(int id){
		return extDao.findById(id);
	}

	public ObligeePicExt find(ObligeePicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ObligeePicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ObligeePicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ObligeePicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ObligeePicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ObligeePicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ObligeePicExt> list(ObligeePicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ObligeePicExt> paginate(ObligeePicExtExample example) {
		List<ObligeePicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
