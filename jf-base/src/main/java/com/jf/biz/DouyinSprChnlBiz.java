package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DouyinSprChnlExtMapper;
import com.jf.dao.DouyinSprChnlMapper;
import com.jf.entity.DouyinSprChnl;
import com.jf.entity.DouyinSprChnlExample;
import com.jf.entity.DouyinSprChnlExt;
import com.jf.entity.DouyinSprChnlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DouyinSprChnlBiz extends BaseService<DouyinSprChnl, DouyinSprChnlExample> {

	@Autowired
	private DouyinSprChnlMapper dao;
	@Autowired
	private DouyinSprChnlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDouyinSprChnlMapper(DouyinSprChnlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DouyinSprChnlExt save(DouyinSprChnlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DouyinSprChnl update(DouyinSprChnl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DouyinSprChnl model = new DouyinSprChnl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DouyinSprChnlExt findById(int id){
		return extDao.findById(id);
	}

	public DouyinSprChnlExt find(DouyinSprChnlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DouyinSprChnlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DouyinSprChnlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DouyinSprChnlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DouyinSprChnlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DouyinSprChnlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DouyinSprChnlExt> list(DouyinSprChnlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DouyinSprChnlExt> paginate(DouyinSprChnlExtExample example) {
		List<DouyinSprChnlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
