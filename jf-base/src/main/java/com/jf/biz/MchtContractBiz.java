package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtContractExtMapper;
import com.jf.dao.MchtContractMapper;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractExample;
import com.jf.entity.MchtContractExt;
import com.jf.entity.MchtContractExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtContractBiz extends BaseService<MchtContract, MchtContractExample> {

	@Autowired
	private MchtContractMapper dao;
	@Autowired
	private MchtContractExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtContractMapper(MchtContractMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtContractExt save(MchtContractExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtContract update(MchtContract model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtContract model = new MchtContract();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtContractExt findById(int id){
		return extDao.findById(id);
	}

	public MchtContractExt find(MchtContractExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtContractExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtContractExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtContractExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtContractExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtContractExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtContractExt> list(MchtContractExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtContractExt> paginate(MchtContractExtExample example) {
		List<MchtContractExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
