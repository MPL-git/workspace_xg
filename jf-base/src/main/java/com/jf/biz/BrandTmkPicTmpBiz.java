package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.BrandTmkPicTmpExtMapper;
import com.jf.dao.BrandTmkPicTmpMapper;
import com.jf.entity.BrandTmkPicTmp;
import com.jf.entity.BrandTmkPicTmpExample;
import com.jf.entity.BrandTmkPicTmpExt;
import com.jf.entity.BrandTmkPicTmpExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrandTmkPicTmpBiz extends BaseService<BrandTmkPicTmp, BrandTmkPicTmpExample> {

	@Autowired
	private BrandTmkPicTmpMapper dao;
	@Autowired
	private BrandTmkPicTmpExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setBrandTmkPicTmpMapper(BrandTmkPicTmpMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public BrandTmkPicTmpExt save(BrandTmkPicTmpExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public BrandTmkPicTmp update(BrandTmkPicTmp model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		BrandTmkPicTmp model = new BrandTmkPicTmp();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public BrandTmkPicTmpExt findById(int id){
		return extDao.findById(id);
	}

	public BrandTmkPicTmpExt find(BrandTmkPicTmpExtExample example){
		return extDao.find(example.fill());
	}

	public int count(BrandTmkPicTmpExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, BrandTmkPicTmpExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, BrandTmkPicTmpExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, BrandTmkPicTmpExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(BrandTmkPicTmpExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<BrandTmkPicTmpExt> list(BrandTmkPicTmpExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<BrandTmkPicTmpExt> paginate(BrandTmkPicTmpExtExample example) {
		List<BrandTmkPicTmpExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
