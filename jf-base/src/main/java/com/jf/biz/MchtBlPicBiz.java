package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBlPicExtMapper;
import com.jf.dao.MchtBlPicMapper;
import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;
import com.jf.entity.MchtBlPicExt;
import com.jf.entity.MchtBlPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBlPicBiz extends BaseService<MchtBlPic, MchtBlPicExample> {

	@Autowired
	private MchtBlPicMapper dao;
	@Autowired
	private MchtBlPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBlPicMapper(MchtBlPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBlPicExt save(MchtBlPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBlPic update(MchtBlPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBlPic model = new MchtBlPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBlPicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBlPicExt find(MchtBlPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBlPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBlPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBlPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBlPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBlPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBlPicExt> list(MchtBlPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBlPicExt> paginate(MchtBlPicExtExample example) {
		List<MchtBlPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
