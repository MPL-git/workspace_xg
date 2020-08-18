package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberBlackOperateLogExtMapper;
import com.jf.dao.MemberBlackOperateLogMapper;
import com.jf.entity.MemberBlackOperateLog;
import com.jf.entity.MemberBlackOperateLogExample;
import com.jf.entity.MemberBlackOperateLogExt;
import com.jf.entity.MemberBlackOperateLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberBlackOperateLogBiz extends BaseService<MemberBlackOperateLog, MemberBlackOperateLogExample> {

	@Autowired
	private MemberBlackOperateLogMapper dao;
	@Autowired
	private MemberBlackOperateLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberBlackOperateLogMapper(MemberBlackOperateLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberBlackOperateLogExt save(MemberBlackOperateLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberBlackOperateLog update(MemberBlackOperateLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberBlackOperateLog model = new MemberBlackOperateLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberBlackOperateLogExt findById(int id){
		return extDao.findById(id);
	}

	public MemberBlackOperateLogExt find(MemberBlackOperateLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberBlackOperateLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberBlackOperateLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberBlackOperateLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberBlackOperateLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberBlackOperateLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberBlackOperateLogExt> list(MemberBlackOperateLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberBlackOperateLogExt> paginate(MemberBlackOperateLogExtExample example) {
		List<MemberBlackOperateLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
