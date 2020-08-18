package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AndroidChannelGroupExtMapper;
import com.jf.dao.AndroidChannelGroupMapper;
import com.jf.entity.AndroidChannelGroup;
import com.jf.entity.AndroidChannelGroupExample;
import com.jf.entity.AndroidChannelGroupExt;
import com.jf.entity.AndroidChannelGroupExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AndroidChannelGroupBiz extends BaseService<AndroidChannelGroup, AndroidChannelGroupExample> {

	@Autowired
	private AndroidChannelGroupMapper dao;
	@Autowired
	private AndroidChannelGroupExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAndroidChannelGroupMapper(AndroidChannelGroupMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AndroidChannelGroupExt save(AndroidChannelGroupExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AndroidChannelGroup update(AndroidChannelGroup model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AndroidChannelGroup model = new AndroidChannelGroup();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AndroidChannelGroupExt findById(int id){
		return extDao.findById(id);
	}

	public AndroidChannelGroupExt find(AndroidChannelGroupExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AndroidChannelGroupExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AndroidChannelGroupExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AndroidChannelGroupExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AndroidChannelGroupExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AndroidChannelGroupExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AndroidChannelGroupExt> list(AndroidChannelGroupExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AndroidChannelGroupExt> paginate(AndroidChannelGroupExtExample example) {
		List<AndroidChannelGroupExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
