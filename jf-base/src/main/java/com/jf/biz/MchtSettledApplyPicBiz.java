package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSettledApplyPicExtMapper;
import com.jf.dao.MchtSettledApplyPicMapper;
import com.jf.entity.MchtSettledApplyPic;
import com.jf.entity.MchtSettledApplyPicExample;
import com.jf.entity.MchtSettledApplyPicExt;
import com.jf.entity.MchtSettledApplyPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtSettledApplyPicBiz extends BaseService<MchtSettledApplyPic, MchtSettledApplyPicExample> {

	@Autowired
	private MchtSettledApplyPicMapper dao;
	@Autowired
	private MchtSettledApplyPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtSettledApplyPicMapper(MchtSettledApplyPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtSettledApplyPicExt save(MchtSettledApplyPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtSettledApplyPic update(MchtSettledApplyPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtSettledApplyPic model = new MchtSettledApplyPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtSettledApplyPicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtSettledApplyPicExt find(MchtSettledApplyPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtSettledApplyPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtSettledApplyPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtSettledApplyPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtSettledApplyPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtSettledApplyPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtSettledApplyPicExt> list(MchtSettledApplyPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtSettledApplyPicExt> paginate(MchtSettledApplyPicExtExample example) {
		List<MchtSettledApplyPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
