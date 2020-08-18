package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtPlatformAuthPicExtMapper;
import com.jf.dao.MchtPlatformAuthPicMapper;
import com.jf.entity.MchtPlatformAuthPic;
import com.jf.entity.MchtPlatformAuthPicExample;
import com.jf.entity.MchtPlatformAuthPicExt;
import com.jf.entity.MchtPlatformAuthPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtPlatformAuthPicBiz extends BaseService<MchtPlatformAuthPic, MchtPlatformAuthPicExample> {

	@Autowired
	private MchtPlatformAuthPicMapper dao;
	@Autowired
	private MchtPlatformAuthPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtPlatformAuthPicMapper(MchtPlatformAuthPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtPlatformAuthPicExt save(MchtPlatformAuthPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtPlatformAuthPic update(MchtPlatformAuthPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtPlatformAuthPic model = new MchtPlatformAuthPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtPlatformAuthPicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtPlatformAuthPicExt find(MchtPlatformAuthPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtPlatformAuthPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtPlatformAuthPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtPlatformAuthPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtPlatformAuthPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtPlatformAuthPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtPlatformAuthPicExt> list(MchtPlatformAuthPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtPlatformAuthPicExt> paginate(MchtPlatformAuthPicExtExample example) {
		List<MchtPlatformAuthPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
