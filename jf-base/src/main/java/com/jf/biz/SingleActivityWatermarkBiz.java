package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SingleActivityWatermarkExtMapper;
import com.jf.dao.SingleActivityWatermarkMapper;
import com.jf.entity.SingleActivityWatermark;
import com.jf.entity.SingleActivityWatermarkExample;
import com.jf.entity.SingleActivityWatermarkExt;
import com.jf.entity.SingleActivityWatermarkExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SingleActivityWatermarkBiz extends BaseService<SingleActivityWatermark, SingleActivityWatermarkExample> {

	@Autowired
	private SingleActivityWatermarkMapper dao;
	@Autowired
	private SingleActivityWatermarkExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSingleActivityWatermarkMapper(SingleActivityWatermarkMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SingleActivityWatermarkExt save(SingleActivityWatermarkExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SingleActivityWatermark update(SingleActivityWatermark model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SingleActivityWatermark model = new SingleActivityWatermark();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SingleActivityWatermarkExt findById(int id){
		return extDao.findById(id);
	}

	public SingleActivityWatermarkExt find(SingleActivityWatermarkExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SingleActivityWatermarkExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SingleActivityWatermarkExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SingleActivityWatermarkExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SingleActivityWatermarkExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SingleActivityWatermarkExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SingleActivityWatermarkExt> list(SingleActivityWatermarkExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SingleActivityWatermarkExt> paginate(SingleActivityWatermarkExtExample example) {
		List<SingleActivityWatermarkExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
