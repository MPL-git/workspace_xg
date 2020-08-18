package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntellectualPropertyRightPicExtMapper;
import com.jf.dao.IntellectualPropertyRightPicMapper;
import com.jf.entity.IntellectualPropertyRightPic;
import com.jf.entity.IntellectualPropertyRightPicExample;
import com.jf.entity.IntellectualPropertyRightPicExt;
import com.jf.entity.IntellectualPropertyRightPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntellectualPropertyRightPicBiz extends BaseService<IntellectualPropertyRightPic, IntellectualPropertyRightPicExample> {

	@Autowired
	private IntellectualPropertyRightPicMapper dao;
	@Autowired
	private IntellectualPropertyRightPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIntellectualPropertyRightPicMapper(IntellectualPropertyRightPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IntellectualPropertyRightPicExt save(IntellectualPropertyRightPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IntellectualPropertyRightPic update(IntellectualPropertyRightPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IntellectualPropertyRightPic model = new IntellectualPropertyRightPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IntellectualPropertyRightPicExt findById(int id){
		return extDao.findById(id);
	}

	public IntellectualPropertyRightPicExt find(IntellectualPropertyRightPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IntellectualPropertyRightPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IntellectualPropertyRightPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IntellectualPropertyRightPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IntellectualPropertyRightPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IntellectualPropertyRightPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IntellectualPropertyRightPicExt> list(IntellectualPropertyRightPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IntellectualPropertyRightPicExt> paginate(IntellectualPropertyRightPicExtExample example) {
		List<IntellectualPropertyRightPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
