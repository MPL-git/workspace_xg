package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CumulativeSignInSettingExtMapper;
import com.jf.dao.CumulativeSignInSettingMapper;
import com.jf.entity.CumulativeSignInSetting;
import com.jf.entity.CumulativeSignInSettingExample;
import com.jf.entity.CumulativeSignInSettingExt;
import com.jf.entity.CumulativeSignInSettingExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CumulativeSignInSettingBiz extends BaseService<CumulativeSignInSetting, CumulativeSignInSettingExample> {

	@Autowired
	private CumulativeSignInSettingMapper dao;
	@Autowired
	private CumulativeSignInSettingExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCumulativeSignInSettingMapper(CumulativeSignInSettingMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CumulativeSignInSettingExt save(CumulativeSignInSettingExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CumulativeSignInSetting update(CumulativeSignInSetting model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CumulativeSignInSetting model = new CumulativeSignInSetting();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CumulativeSignInSettingExt findById(int id){
		return extDao.findById(id);
	}

	public CumulativeSignInSettingExt find(CumulativeSignInSettingExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CumulativeSignInSettingExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CumulativeSignInSettingExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CumulativeSignInSettingExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CumulativeSignInSettingExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CumulativeSignInSettingExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CumulativeSignInSettingExt> list(CumulativeSignInSettingExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CumulativeSignInSettingExt> paginate(CumulativeSignInSettingExtExample example) {
		List<CumulativeSignInSettingExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
