package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinXcxSprDtlExtMapper;
import com.jf.dao.WeixinXcxSprDtlMapper;
import com.jf.entity.WeixinXcxSprDtl;
import com.jf.entity.WeixinXcxSprDtlExample;
import com.jf.entity.WeixinXcxSprDtlExt;
import com.jf.entity.WeixinXcxSprDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinXcxSprDtlBiz extends BaseService<WeixinXcxSprDtl, WeixinXcxSprDtlExample> {

	@Autowired
	private WeixinXcxSprDtlMapper dao;
	@Autowired
	private WeixinXcxSprDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinXcxSprDtlMapper(WeixinXcxSprDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinXcxSprDtlExt save(WeixinXcxSprDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinXcxSprDtl update(WeixinXcxSprDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinXcxSprDtl model = new WeixinXcxSprDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinXcxSprDtlExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinXcxSprDtlExt find(WeixinXcxSprDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinXcxSprDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinXcxSprDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinXcxSprDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinXcxSprDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinXcxSprDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinXcxSprDtlExt> list(WeixinXcxSprDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinXcxSprDtlExt> paginate(WeixinXcxSprDtlExtExample example) {
		List<WeixinXcxSprDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
