package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ComplainOrderPicExtMapper;
import com.jf.dao.ComplainOrderPicMapper;
import com.jf.entity.ComplainOrderPic;
import com.jf.entity.ComplainOrderPicExample;
import com.jf.entity.ComplainOrderPicExt;
import com.jf.entity.ComplainOrderPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplainOrderPicBiz extends BaseService<ComplainOrderPic, ComplainOrderPicExample> {

	@Autowired
	private ComplainOrderPicMapper dao;
	@Autowired
	private ComplainOrderPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setComplainOrderPicMapper(ComplainOrderPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ComplainOrderPicExt save(ComplainOrderPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ComplainOrderPic update(ComplainOrderPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ComplainOrderPic model = new ComplainOrderPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ComplainOrderPicExt findById(int id){
		return extDao.findById(id);
	}

	public ComplainOrderPicExt find(ComplainOrderPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ComplainOrderPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ComplainOrderPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ComplainOrderPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ComplainOrderPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ComplainOrderPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ComplainOrderPicExt> list(ComplainOrderPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ComplainOrderPicExt> paginate(ComplainOrderPicExtExample example) {
		List<ComplainOrderPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
