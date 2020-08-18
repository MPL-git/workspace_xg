package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AppealPlatformRemarksPicExtMapper;
import com.jf.dao.AppealPlatformRemarksPicMapper;
import com.jf.entity.AppealPlatformRemarksPic;
import com.jf.entity.AppealPlatformRemarksPicExample;
import com.jf.entity.AppealPlatformRemarksPicExt;
import com.jf.entity.AppealPlatformRemarksPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppealPlatformRemarksPicBiz extends BaseService<AppealPlatformRemarksPic, AppealPlatformRemarksPicExample> {

	@Autowired
	private AppealPlatformRemarksPicMapper dao;
	@Autowired
	private AppealPlatformRemarksPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAppealPlatformRemarksPicMapper(AppealPlatformRemarksPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AppealPlatformRemarksPicExt save(AppealPlatformRemarksPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AppealPlatformRemarksPic update(AppealPlatformRemarksPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AppealPlatformRemarksPic model = new AppealPlatformRemarksPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AppealPlatformRemarksPicExt findById(int id){
		return extDao.findById(id);
	}

	public AppealPlatformRemarksPicExt find(AppealPlatformRemarksPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AppealPlatformRemarksPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AppealPlatformRemarksPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AppealPlatformRemarksPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AppealPlatformRemarksPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AppealPlatformRemarksPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AppealPlatformRemarksPicExt> list(AppealPlatformRemarksPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AppealPlatformRemarksPicExt> paginate(AppealPlatformRemarksPicExtExample example) {
		List<AppealPlatformRemarksPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
