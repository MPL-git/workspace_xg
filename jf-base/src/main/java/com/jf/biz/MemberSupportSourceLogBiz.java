package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberSupportSourceLogExtMapper;
import com.jf.dao.MemberSupportSourceLogMapper;
import com.jf.entity.MemberSupportSourceLog;
import com.jf.entity.MemberSupportSourceLogExample;
import com.jf.entity.MemberSupportSourceLogExt;
import com.jf.entity.MemberSupportSourceLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberSupportSourceLogBiz extends BaseService<MemberSupportSourceLog, MemberSupportSourceLogExample> {

	@Autowired
	private MemberSupportSourceLogMapper dao;
	@Autowired
	private MemberSupportSourceLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberSupportSourceLogMapper(MemberSupportSourceLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberSupportSourceLogExt save(MemberSupportSourceLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberSupportSourceLog update(MemberSupportSourceLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberSupportSourceLog model = new MemberSupportSourceLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberSupportSourceLogExt findById(int id){
		return extDao.findById(id);
	}

	public MemberSupportSourceLogExt find(MemberSupportSourceLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberSupportSourceLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberSupportSourceLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberSupportSourceLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberSupportSourceLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberSupportSourceLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberSupportSourceLogExt> list(MemberSupportSourceLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberSupportSourceLogExt> paginate(MemberSupportSourceLogExtExample example) {
		List<MemberSupportSourceLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
