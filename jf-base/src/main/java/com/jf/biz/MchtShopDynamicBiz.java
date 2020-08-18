package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtShopDynamicExtMapper;
import com.jf.dao.MchtShopDynamicMapper;
import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicExample;
import com.jf.entity.MchtShopDynamicExt;
import com.jf.entity.MchtShopDynamicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtShopDynamicBiz extends BaseService<MchtShopDynamic, MchtShopDynamicExample> {

	@Autowired
	private MchtShopDynamicMapper dao;
	@Autowired
	private MchtShopDynamicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtShopDynamicMapper(MchtShopDynamicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtShopDynamicExt save(MchtShopDynamicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtShopDynamic update(MchtShopDynamic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtShopDynamic model = new MchtShopDynamic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtShopDynamicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtShopDynamicExt find(MchtShopDynamicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtShopDynamicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtShopDynamicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtShopDynamicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtShopDynamicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtShopDynamicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtShopDynamicExt> list(MchtShopDynamicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtShopDynamicExt> paginate(MchtShopDynamicExtExample example) {
		List<MchtShopDynamicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
