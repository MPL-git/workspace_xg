package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandChangeAgreementPicExtMapper;
import com.jf.dao.MchtBrandChangeAgreementPicMapper;
import com.jf.entity.MchtBrandChangeAgreementPic;
import com.jf.entity.MchtBrandChangeAgreementPicExample;
import com.jf.entity.MchtBrandChangeAgreementPicExt;
import com.jf.entity.MchtBrandChangeAgreementPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandChangeAgreementPicBiz extends BaseService<MchtBrandChangeAgreementPic, MchtBrandChangeAgreementPicExample> {

	@Autowired
	private MchtBrandChangeAgreementPicMapper dao;
	@Autowired
	private MchtBrandChangeAgreementPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandChangeAgreementPicMapper(MchtBrandChangeAgreementPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandChangeAgreementPicExt save(MchtBrandChangeAgreementPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandChangeAgreementPic update(MchtBrandChangeAgreementPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandChangeAgreementPic model = new MchtBrandChangeAgreementPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandChangeAgreementPicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandChangeAgreementPicExt find(MchtBrandChangeAgreementPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandChangeAgreementPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandChangeAgreementPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandChangeAgreementPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandChangeAgreementPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandChangeAgreementPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandChangeAgreementPicExt> list(MchtBrandChangeAgreementPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandChangeAgreementPicExt> paginate(MchtBrandChangeAgreementPicExtExample example) {
		List<MchtBrandChangeAgreementPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
