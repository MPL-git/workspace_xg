package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CombineOrderExtendExtMapper;
import com.jf.dao.CombineOrderExtendMapper;
import com.jf.entity.CombineOrderExtend;
import com.jf.entity.CombineOrderExtendExample;
import com.jf.entity.CombineOrderExtendExt;
import com.jf.entity.CombineOrderExtendExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CombineOrderExtendBiz extends BaseService<CombineOrderExtend, CombineOrderExtendExample> {

	@Autowired
	private CombineOrderExtendMapper dao;
	@Autowired
	private CombineOrderExtendExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCombineOrderExtendMapper(CombineOrderExtendMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CombineOrderExtendExt save(CombineOrderExtendExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CombineOrderExtend update(CombineOrderExtend model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CombineOrderExtend model = new CombineOrderExtend();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CombineOrderExtendExt findById(int id){
		return extDao.findById(id);
	}

	public CombineOrderExtendExt find(CombineOrderExtendExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CombineOrderExtendExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CombineOrderExtendExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CombineOrderExtendExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CombineOrderExtendExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CombineOrderExtendExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CombineOrderExtendExt> list(CombineOrderExtendExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CombineOrderExtendExt> paginate(CombineOrderExtendExtExample example) {
		List<CombineOrderExtendExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
