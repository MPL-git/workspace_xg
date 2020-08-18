package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinXcxSprChnlExtMapper;
import com.jf.dao.WeixinXcxSprChnlMapper;
import com.jf.entity.WeixinXcxSprChnl;
import com.jf.entity.WeixinXcxSprChnlExample;
import com.jf.entity.WeixinXcxSprChnlExt;
import com.jf.entity.WeixinXcxSprChnlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinXcxSprChnlBiz extends BaseService<WeixinXcxSprChnl, WeixinXcxSprChnlExample> {

	@Autowired
	private WeixinXcxSprChnlMapper dao;
	@Autowired
	private WeixinXcxSprChnlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinXcxSprChnlMapper(WeixinXcxSprChnlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinXcxSprChnlExt save(WeixinXcxSprChnlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinXcxSprChnl update(WeixinXcxSprChnl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinXcxSprChnl model = new WeixinXcxSprChnl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinXcxSprChnlExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinXcxSprChnlExt find(WeixinXcxSprChnlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinXcxSprChnlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinXcxSprChnlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinXcxSprChnlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinXcxSprChnlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinXcxSprChnlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinXcxSprChnlExt> list(WeixinXcxSprChnlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinXcxSprChnlExt> paginate(WeixinXcxSprChnlExtExample example) {
		List<WeixinXcxSprChnlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
