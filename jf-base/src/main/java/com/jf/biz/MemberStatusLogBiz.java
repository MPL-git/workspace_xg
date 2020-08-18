package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberStatusLogExtMapper;
import com.jf.dao.MemberStatusLogMapper;
import com.jf.entity.MemberStatusLog;
import com.jf.entity.MemberStatusLogExample;
import com.jf.entity.MemberStatusLogExt;
import com.jf.entity.MemberStatusLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberStatusLogBiz extends BaseService<MemberStatusLog, MemberStatusLogExample> {

	@Autowired
	private MemberStatusLogMapper dao;
	@Autowired
	private MemberStatusLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberStatusLogMapper(MemberStatusLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberStatusLogExt save(MemberStatusLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberStatusLog update(MemberStatusLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberStatusLog model = new MemberStatusLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberStatusLogExt findById(int id){
		return extDao.findById(id);
	}

	public MemberStatusLogExt find(MemberStatusLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberStatusLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberStatusLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberStatusLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberStatusLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberStatusLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberStatusLogExt> list(MemberStatusLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberStatusLogExt> paginate(MemberStatusLogExtExample example) {
		List<MemberStatusLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
