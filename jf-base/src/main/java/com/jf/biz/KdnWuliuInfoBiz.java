package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.KdnWuliuInfoExtMapper;
import com.jf.dao.KdnWuliuInfoMapper;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoExample;
import com.jf.entity.KdnWuliuInfoExt;
import com.jf.entity.KdnWuliuInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KdnWuliuInfoBiz extends BaseService<KdnWuliuInfo, KdnWuliuInfoExample> {

	@Autowired
	private KdnWuliuInfoMapper dao;
	@Autowired
	private KdnWuliuInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setKdnWuliuInfoMapper(KdnWuliuInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public KdnWuliuInfoExt save(KdnWuliuInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public KdnWuliuInfo update(KdnWuliuInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		KdnWuliuInfo model = new KdnWuliuInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public KdnWuliuInfoExt findById(int id){
		return extDao.findById(id);
	}

	public KdnWuliuInfoExt find(KdnWuliuInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(KdnWuliuInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, KdnWuliuInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, KdnWuliuInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, KdnWuliuInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(KdnWuliuInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<KdnWuliuInfoExt> list(KdnWuliuInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<KdnWuliuInfoExt> paginate(KdnWuliuInfoExtExample example) {
		List<KdnWuliuInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
