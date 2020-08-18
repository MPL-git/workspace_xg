package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AppealLogPicExtMapper;
import com.jf.dao.AppealLogPicMapper;
import com.jf.entity.AppealLogPic;
import com.jf.entity.AppealLogPicExample;
import com.jf.entity.AppealLogPicExt;
import com.jf.entity.AppealLogPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppealLogPicBiz extends BaseService<AppealLogPic, AppealLogPicExample> {

	@Autowired
	private AppealLogPicMapper dao;
	@Autowired
	private AppealLogPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAppealLogPicMapper(AppealLogPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AppealLogPicExt save(AppealLogPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AppealLogPic update(AppealLogPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AppealLogPic model = new AppealLogPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AppealLogPicExt findById(int id){
		return extDao.findById(id);
	}

	public AppealLogPicExt find(AppealLogPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AppealLogPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AppealLogPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AppealLogPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AppealLogPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AppealLogPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AppealLogPicExt> list(AppealLogPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AppealLogPicExt> paginate(AppealLogPicExtExample example) {
		List<AppealLogPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
