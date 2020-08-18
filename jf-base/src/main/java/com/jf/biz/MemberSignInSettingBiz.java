package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberSignInSettingExtMapper;
import com.jf.dao.MemberSignInSettingMapper;
import com.jf.entity.MemberSignInSetting;
import com.jf.entity.MemberSignInSettingExample;
import com.jf.entity.MemberSignInSettingExt;
import com.jf.entity.MemberSignInSettingExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberSignInSettingBiz extends BaseService<MemberSignInSetting, MemberSignInSettingExample> {

	@Autowired
	private MemberSignInSettingMapper dao;
	@Autowired
	private MemberSignInSettingExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberSignInSettingMapper(MemberSignInSettingMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberSignInSettingExt save(MemberSignInSettingExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberSignInSetting update(MemberSignInSetting model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberSignInSetting model = new MemberSignInSetting();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberSignInSettingExt findById(int id){
		return extDao.findById(id);
	}

	public MemberSignInSettingExt find(MemberSignInSettingExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberSignInSettingExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberSignInSettingExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberSignInSettingExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberSignInSettingExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberSignInSettingExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberSignInSettingExt> list(MemberSignInSettingExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberSignInSettingExt> paginate(MemberSignInSettingExtExample example) {
		List<MemberSignInSettingExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
