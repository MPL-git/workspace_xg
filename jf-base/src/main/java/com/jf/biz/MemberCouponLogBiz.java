package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberCouponLogExtMapper;
import com.jf.dao.MemberCouponLogMapper;
import com.jf.entity.MemberCouponLog;
import com.jf.entity.MemberCouponLogExample;
import com.jf.entity.MemberCouponLogExt;
import com.jf.entity.MemberCouponLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberCouponLogBiz extends BaseService<MemberCouponLog, MemberCouponLogExample> {

	@Autowired
	private MemberCouponLogMapper dao;
	@Autowired
	private MemberCouponLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberCouponLogMapper(MemberCouponLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberCouponLogExt save(MemberCouponLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberCouponLog update(MemberCouponLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberCouponLog model = new MemberCouponLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberCouponLogExt findById(int id){
		return extDao.findById(id);
	}

	public MemberCouponLogExt find(MemberCouponLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberCouponLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberCouponLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberCouponLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberCouponLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberCouponLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberCouponLogExt> list(MemberCouponLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberCouponLogExt> paginate(MemberCouponLogExtExample example) {
		List<MemberCouponLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
