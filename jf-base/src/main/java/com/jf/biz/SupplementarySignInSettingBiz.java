package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SupplementarySignInSettingExtMapper;
import com.jf.dao.SupplementarySignInSettingMapper;
import com.jf.entity.SupplementarySignInSetting;
import com.jf.entity.SupplementarySignInSettingExample;
import com.jf.entity.SupplementarySignInSettingExt;
import com.jf.entity.SupplementarySignInSettingExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplementarySignInSettingBiz extends BaseService<SupplementarySignInSetting, SupplementarySignInSettingExample> {

	@Autowired
	private SupplementarySignInSettingMapper dao;
	@Autowired
	private SupplementarySignInSettingExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSupplementarySignInSettingMapper(SupplementarySignInSettingMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SupplementarySignInSettingExt save(SupplementarySignInSettingExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SupplementarySignInSetting update(SupplementarySignInSetting model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SupplementarySignInSetting model = new SupplementarySignInSetting();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SupplementarySignInSettingExt findById(int id){
		return extDao.findById(id);
	}

	public SupplementarySignInSettingExt find(SupplementarySignInSettingExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SupplementarySignInSettingExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SupplementarySignInSettingExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SupplementarySignInSettingExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SupplementarySignInSettingExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SupplementarySignInSettingExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SupplementarySignInSettingExt> list(SupplementarySignInSettingExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SupplementarySignInSettingExt> paginate(SupplementarySignInSettingExtExample example) {
		List<SupplementarySignInSettingExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
