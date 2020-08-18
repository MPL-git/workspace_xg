package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtContractPicExtMapper;
import com.jf.dao.MchtContractPicMapper;
import com.jf.entity.MchtContractPic;
import com.jf.entity.MchtContractPicExample;
import com.jf.entity.MchtContractPicExt;
import com.jf.entity.MchtContractPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtContractPicBiz extends BaseService<MchtContractPic, MchtContractPicExample> {

	@Autowired
	private MchtContractPicMapper dao;
	@Autowired
	private MchtContractPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtContractPicMapper(MchtContractPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtContractPicExt save(MchtContractPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtContractPic update(MchtContractPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtContractPic model = new MchtContractPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtContractPicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtContractPicExt find(MchtContractPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtContractPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtContractPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtContractPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtContractPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtContractPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtContractPicExt> list(MchtContractPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtContractPicExt> paginate(MchtContractPicExtExample example) {
		List<MchtContractPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
