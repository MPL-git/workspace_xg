package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandOtherPicExtMapper;
import com.jf.dao.MchtBrandOtherPicMapper;
import com.jf.entity.MchtBrandOtherPic;
import com.jf.entity.MchtBrandOtherPicExample;
import com.jf.entity.MchtBrandOtherPicExt;
import com.jf.entity.MchtBrandOtherPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandOtherPicBiz extends BaseService<MchtBrandOtherPic, MchtBrandOtherPicExample> {

	@Autowired
	private MchtBrandOtherPicMapper dao;
	@Autowired
	private MchtBrandOtherPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandOtherPicMapper(MchtBrandOtherPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandOtherPicExt save(MchtBrandOtherPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandOtherPic update(MchtBrandOtherPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandOtherPic model = new MchtBrandOtherPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandOtherPicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandOtherPicExt find(MchtBrandOtherPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandOtherPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandOtherPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandOtherPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandOtherPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandOtherPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandOtherPicExt> list(MchtBrandOtherPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandOtherPicExt> paginate(MchtBrandOtherPicExtExample example) {
		List<MchtBrandOtherPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
