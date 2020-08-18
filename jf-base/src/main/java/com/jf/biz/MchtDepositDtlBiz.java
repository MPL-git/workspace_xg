package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtDepositDtlExtMapper;
import com.jf.dao.MchtDepositDtlMapper;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositDtlExample;
import com.jf.entity.MchtDepositDtlExt;
import com.jf.entity.MchtDepositDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtDepositDtlBiz extends BaseService<MchtDepositDtl, MchtDepositDtlExample> {

	@Autowired
	private MchtDepositDtlMapper dao;
	@Autowired
	private MchtDepositDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtDepositDtlMapper(MchtDepositDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtDepositDtlExt save(MchtDepositDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtDepositDtl update(MchtDepositDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtDepositDtl model = new MchtDepositDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtDepositDtlExt findById(int id){
		return extDao.findById(id);
	}

	public MchtDepositDtlExt find(MchtDepositDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtDepositDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtDepositDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtDepositDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtDepositDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtDepositDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtDepositDtlExt> list(MchtDepositDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtDepositDtlExt> paginate(MchtDepositDtlExtExample example) {
		List<MchtDepositDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
