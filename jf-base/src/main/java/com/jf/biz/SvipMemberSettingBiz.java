package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SvipMemberSettingExtMapper;
import com.jf.dao.SvipMemberSettingMapper;
import com.jf.entity.SvipMemberSetting;
import com.jf.entity.SvipMemberSettingExample;
import com.jf.entity.SvipMemberSettingExt;
import com.jf.entity.SvipMemberSettingExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SvipMemberSettingBiz extends BaseService<SvipMemberSetting, SvipMemberSettingExample> {

	@Autowired
	private SvipMemberSettingMapper dao;
	@Autowired
	private SvipMemberSettingExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSvipMemberSettingMapper(SvipMemberSettingMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SvipMemberSettingExt save(SvipMemberSettingExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SvipMemberSetting update(SvipMemberSetting model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SvipMemberSetting model = new SvipMemberSetting();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SvipMemberSettingExt findById(int id){
		return extDao.findById(id);
	}

	public SvipMemberSettingExt find(SvipMemberSettingExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SvipMemberSettingExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SvipMemberSettingExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SvipMemberSettingExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SvipMemberSettingExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SvipMemberSettingExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SvipMemberSettingExt> list(SvipMemberSettingExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SvipMemberSettingExt> paginate(SvipMemberSettingExtExample example) {
		List<SvipMemberSettingExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
