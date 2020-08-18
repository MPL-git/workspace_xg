package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberShopFootprintExtMapper;
import com.jf.dao.MemberShopFootprintMapper;
import com.jf.entity.MemberShopFootprint;
import com.jf.entity.MemberShopFootprintExample;
import com.jf.entity.MemberShopFootprintExt;
import com.jf.entity.MemberShopFootprintExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberShopFootprintBiz extends BaseService<MemberShopFootprint, MemberShopFootprintExample> {

	@Autowired
	private MemberShopFootprintMapper dao;
	@Autowired
	private MemberShopFootprintExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberShopFootprintMapper(MemberShopFootprintMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberShopFootprintExt save(MemberShopFootprintExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberShopFootprint update(MemberShopFootprint model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberShopFootprint model = new MemberShopFootprint();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberShopFootprintExt findById(int id){
		return extDao.findById(id);
	}

	public MemberShopFootprintExt find(MemberShopFootprintExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberShopFootprintExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberShopFootprintExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberShopFootprintExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberShopFootprintExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberShopFootprintExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberShopFootprintExt> list(MemberShopFootprintExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberShopFootprintExt> paginate(MemberShopFootprintExtExample example) {
		List<MemberShopFootprintExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
