package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SourceProductTypeBannerExtMapper;
import com.jf.dao.SourceProductTypeBannerMapper;
import com.jf.entity.SourceProductTypeBanner;
import com.jf.entity.SourceProductTypeBannerExample;
import com.jf.entity.SourceProductTypeBannerExt;
import com.jf.entity.SourceProductTypeBannerExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SourceProductTypeBannerBiz extends BaseService<SourceProductTypeBanner, SourceProductTypeBannerExample> {

	@Autowired
	private SourceProductTypeBannerMapper dao;
	@Autowired
	private SourceProductTypeBannerExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSourceProductTypeBannerMapper(SourceProductTypeBannerMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SourceProductTypeBannerExt save(SourceProductTypeBannerExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SourceProductTypeBanner update(SourceProductTypeBanner model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SourceProductTypeBanner model = new SourceProductTypeBanner();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SourceProductTypeBannerExt findById(int id){
		return extDao.findById(id);
	}

	public SourceProductTypeBannerExt find(SourceProductTypeBannerExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SourceProductTypeBannerExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SourceProductTypeBannerExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SourceProductTypeBannerExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SourceProductTypeBannerExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SourceProductTypeBannerExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SourceProductTypeBannerExt> list(SourceProductTypeBannerExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SourceProductTypeBannerExt> paginate(SourceProductTypeBannerExtExample example) {
		List<SourceProductTypeBannerExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
