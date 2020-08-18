package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ViolatePlatformRemarksPicExtMapper;
import com.jf.dao.ViolatePlatformRemarksPicMapper;
import com.jf.entity.ViolatePlatformRemarksPic;
import com.jf.entity.ViolatePlatformRemarksPicExample;
import com.jf.entity.ViolatePlatformRemarksPicExt;
import com.jf.entity.ViolatePlatformRemarksPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ViolatePlatformRemarksPicBiz extends BaseService<ViolatePlatformRemarksPic, ViolatePlatformRemarksPicExample> {

	@Autowired
	private ViolatePlatformRemarksPicMapper dao;
	@Autowired
	private ViolatePlatformRemarksPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setViolatePlatformRemarksPicMapper(ViolatePlatformRemarksPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ViolatePlatformRemarksPicExt save(ViolatePlatformRemarksPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ViolatePlatformRemarksPic update(ViolatePlatformRemarksPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ViolatePlatformRemarksPic model = new ViolatePlatformRemarksPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ViolatePlatformRemarksPicExt findById(int id){
		return extDao.findById(id);
	}

	public ViolatePlatformRemarksPicExt find(ViolatePlatformRemarksPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ViolatePlatformRemarksPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ViolatePlatformRemarksPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ViolatePlatformRemarksPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ViolatePlatformRemarksPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ViolatePlatformRemarksPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ViolatePlatformRemarksPicExt> list(ViolatePlatformRemarksPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ViolatePlatformRemarksPicExt> paginate(ViolatePlatformRemarksPicExtExample example) {
		List<ViolatePlatformRemarksPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
