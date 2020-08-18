package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberAccountDtlExtMapper;
import com.jf.dao.MemberAccountDtlMapper;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountDtlExample;
import com.jf.entity.MemberAccountDtlExt;
import com.jf.entity.MemberAccountDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberAccountDtlBiz extends BaseService<MemberAccountDtl, MemberAccountDtlExample> {

	@Autowired
	private MemberAccountDtlMapper dao;
	@Autowired
	private MemberAccountDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberAccountDtlMapper(MemberAccountDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberAccountDtlExt save(MemberAccountDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberAccountDtl update(MemberAccountDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberAccountDtl model = new MemberAccountDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberAccountDtlExt findById(int id){
		return extDao.findById(id);
	}

	public MemberAccountDtlExt find(MemberAccountDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberAccountDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberAccountDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberAccountDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberAccountDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberAccountDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberAccountDtlExt> list(MemberAccountDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberAccountDtlExt> paginate(MemberAccountDtlExtExample example) {
		List<MemberAccountDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
