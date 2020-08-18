package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberMobileWeixinMapExtMapper;
import com.jf.dao.MemberMobileWeixinMapMapper;
import com.jf.entity.MemberMobileWeixinMap;
import com.jf.entity.MemberMobileWeixinMapExample;
import com.jf.entity.MemberMobileWeixinMapExt;
import com.jf.entity.MemberMobileWeixinMapExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberMobileWeixinMapBiz extends BaseService<MemberMobileWeixinMap, MemberMobileWeixinMapExample> {

	@Autowired
	private MemberMobileWeixinMapMapper dao;
	@Autowired
	private MemberMobileWeixinMapExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberMobileWeixinMapMapper(MemberMobileWeixinMapMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberMobileWeixinMapExt save(MemberMobileWeixinMapExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberMobileWeixinMap update(MemberMobileWeixinMap model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberMobileWeixinMap model = new MemberMobileWeixinMap();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberMobileWeixinMapExt findById(int id){
		return extDao.findById(id);
	}

	public MemberMobileWeixinMapExt find(MemberMobileWeixinMapExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberMobileWeixinMapExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberMobileWeixinMapExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberMobileWeixinMapExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberMobileWeixinMapExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberMobileWeixinMapExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberMobileWeixinMapExt> list(MemberMobileWeixinMapExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberMobileWeixinMapExt> paginate(MemberMobileWeixinMapExtExample example) {
		List<MemberMobileWeixinMapExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
