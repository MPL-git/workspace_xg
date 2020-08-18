package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandOtherPicChgExtMapper;
import com.jf.dao.MchtBrandOtherPicChgMapper;
import com.jf.entity.MchtBrandOtherPicChg;
import com.jf.entity.MchtBrandOtherPicChgExample;
import com.jf.entity.MchtBrandOtherPicChgExt;
import com.jf.entity.MchtBrandOtherPicChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandOtherPicChgBiz extends BaseService<MchtBrandOtherPicChg, MchtBrandOtherPicChgExample> {

	@Autowired
	private MchtBrandOtherPicChgMapper dao;
	@Autowired
	private MchtBrandOtherPicChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandOtherPicChgMapper(MchtBrandOtherPicChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandOtherPicChgExt save(MchtBrandOtherPicChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandOtherPicChg update(MchtBrandOtherPicChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandOtherPicChg model = new MchtBrandOtherPicChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandOtherPicChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandOtherPicChgExt find(MchtBrandOtherPicChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandOtherPicChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandOtherPicChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandOtherPicChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandOtherPicChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandOtherPicChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandOtherPicChgExt> list(MchtBrandOtherPicChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandOtherPicChgExt> paginate(MchtBrandOtherPicChgExtExample example) {
		List<MchtBrandOtherPicChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
