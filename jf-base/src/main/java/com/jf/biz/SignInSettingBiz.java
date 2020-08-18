package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SignInSettingExtMapper;
import com.jf.dao.SignInSettingMapper;
import com.jf.entity.SignInSetting;
import com.jf.entity.SignInSettingExample;
import com.jf.entity.SignInSettingExt;
import com.jf.entity.SignInSettingExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignInSettingBiz extends BaseService<SignInSetting, SignInSettingExample> {

	@Autowired
	private SignInSettingMapper dao;
	@Autowired
	private SignInSettingExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSignInSettingMapper(SignInSettingMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SignInSettingExt save(SignInSettingExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SignInSetting update(SignInSetting model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SignInSetting model = new SignInSetting();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SignInSettingExt findById(int id){
		return extDao.findById(id);
	}

	public SignInSettingExt find(SignInSettingExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SignInSettingExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SignInSettingExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SignInSettingExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SignInSettingExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SignInSettingExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SignInSettingExt> list(SignInSettingExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SignInSettingExt> paginate(SignInSettingExtExample example) {
		List<SignInSettingExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
