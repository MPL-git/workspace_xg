package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProvinceFreightExtMapper;
import com.jf.dao.ProvinceFreightMapper;
import com.jf.entity.ProvinceFreight;
import com.jf.entity.ProvinceFreightExample;
import com.jf.entity.ProvinceFreightExt;
import com.jf.entity.ProvinceFreightExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProvinceFreightBiz extends BaseService<ProvinceFreight, ProvinceFreightExample> {

	@Autowired
	private ProvinceFreightMapper dao;
	@Autowired
	private ProvinceFreightExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProvinceFreightMapper(ProvinceFreightMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProvinceFreightExt save(ProvinceFreightExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProvinceFreight update(ProvinceFreight model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProvinceFreight model = new ProvinceFreight();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProvinceFreightExt findById(int id){
		return extDao.findById(id);
	}

	public ProvinceFreightExt find(ProvinceFreightExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProvinceFreightExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProvinceFreightExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProvinceFreightExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProvinceFreightExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProvinceFreightExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProvinceFreightExt> list(ProvinceFreightExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProvinceFreightExt> paginate(ProvinceFreightExtExample example) {
		List<ProvinceFreightExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
