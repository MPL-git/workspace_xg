package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BrandTmkPicExtMapper;
import com.jf.dao.BrandTmkPicMapper;
import com.jf.entity.BrandTmkPic;
import com.jf.entity.BrandTmkPicExample;
import com.jf.entity.BrandTmkPicExt;
import com.jf.entity.BrandTmkPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrandTmkPicBiz extends BaseService<BrandTmkPic, BrandTmkPicExample> {

	@Autowired
	private BrandTmkPicMapper dao;
	@Autowired
	private BrandTmkPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBrandTmkPicMapper(BrandTmkPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BrandTmkPicExt save(BrandTmkPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BrandTmkPic update(BrandTmkPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BrandTmkPic model = new BrandTmkPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BrandTmkPicExt findById(int id){
		return extDao.findById(id);
	}

	public BrandTmkPicExt find(BrandTmkPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BrandTmkPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BrandTmkPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BrandTmkPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BrandTmkPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BrandTmkPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BrandTmkPicExt> list(BrandTmkPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BrandTmkPicExt> paginate(BrandTmkPicExtExample example) {
		List<BrandTmkPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
