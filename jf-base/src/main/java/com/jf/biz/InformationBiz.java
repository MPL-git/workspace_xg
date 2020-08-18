package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.InformationExtMapper;
import com.jf.dao.InformationMapper;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;
import com.jf.entity.InformationExt;
import com.jf.entity.InformationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InformationBiz extends BaseService<Information, InformationExample> {

	@Autowired
	private InformationMapper dao;
	@Autowired
	private InformationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setInformationMapper(InformationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public InformationExt save(InformationExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Information update(Information model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Information model = new Information();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public InformationExt findById(int id){
		return extDao.findById(id);
	}

	public InformationExt find(InformationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(InformationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, InformationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, InformationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, InformationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(InformationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<InformationExt> list(InformationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<InformationExt> paginate(InformationExtExample example) {
		List<InformationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
