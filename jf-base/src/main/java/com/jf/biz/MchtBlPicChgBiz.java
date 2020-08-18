package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBlPicChgExtMapper;
import com.jf.dao.MchtBlPicChgMapper;
import com.jf.entity.MchtBlPicChg;
import com.jf.entity.MchtBlPicChgExample;
import com.jf.entity.MchtBlPicChgExt;
import com.jf.entity.MchtBlPicChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBlPicChgBiz extends BaseService<MchtBlPicChg, MchtBlPicChgExample> {

	@Autowired
	private MchtBlPicChgMapper dao;
	@Autowired
	private MchtBlPicChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBlPicChgMapper(MchtBlPicChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBlPicChgExt save(MchtBlPicChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBlPicChg update(MchtBlPicChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBlPicChg model = new MchtBlPicChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBlPicChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBlPicChgExt find(MchtBlPicChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBlPicChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBlPicChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBlPicChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBlPicChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBlPicChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBlPicChgExt> list(MchtBlPicChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBlPicChgExt> paginate(MchtBlPicChgExtExample example) {
		List<MchtBlPicChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
