package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtPlatformAuthPicChgExtMapper;
import com.jf.dao.MchtPlatformAuthPicChgMapper;
import com.jf.entity.MchtPlatformAuthPicChg;
import com.jf.entity.MchtPlatformAuthPicChgExample;
import com.jf.entity.MchtPlatformAuthPicChgExt;
import com.jf.entity.MchtPlatformAuthPicChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtPlatformAuthPicChgBiz extends BaseService<MchtPlatformAuthPicChg, MchtPlatformAuthPicChgExample> {

	@Autowired
	private MchtPlatformAuthPicChgMapper dao;
	@Autowired
	private MchtPlatformAuthPicChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtPlatformAuthPicChgMapper(MchtPlatformAuthPicChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtPlatformAuthPicChgExt save(MchtPlatformAuthPicChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtPlatformAuthPicChg update(MchtPlatformAuthPicChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtPlatformAuthPicChg model = new MchtPlatformAuthPicChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtPlatformAuthPicChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtPlatformAuthPicChgExt find(MchtPlatformAuthPicChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtPlatformAuthPicChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtPlatformAuthPicChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtPlatformAuthPicChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtPlatformAuthPicChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtPlatformAuthPicChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtPlatformAuthPicChgExt> list(MchtPlatformAuthPicChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtPlatformAuthPicChgExt> paginate(MchtPlatformAuthPicChgExtExample example) {
		List<MchtPlatformAuthPicChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
