package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBlPicHisExtMapper;
import com.jf.dao.MchtBlPicHisMapper;
import com.jf.entity.MchtBlPicHis;
import com.jf.entity.MchtBlPicHisExample;
import com.jf.entity.MchtBlPicHisExt;
import com.jf.entity.MchtBlPicHisExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBlPicHisBiz extends BaseService<MchtBlPicHis, MchtBlPicHisExample> {

	@Autowired
	private MchtBlPicHisMapper dao;
	@Autowired
	private MchtBlPicHisExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBlPicHisMapper(MchtBlPicHisMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBlPicHisExt save(MchtBlPicHisExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBlPicHis update(MchtBlPicHis model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBlPicHis model = new MchtBlPicHis();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBlPicHisExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBlPicHisExt find(MchtBlPicHisExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBlPicHisExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBlPicHisExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBlPicHisExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBlPicHisExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBlPicHisExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBlPicHisExt> list(MchtBlPicHisExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBlPicHisExt> paginate(MchtBlPicHisExtExample example) {
		List<MchtBlPicHisExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
