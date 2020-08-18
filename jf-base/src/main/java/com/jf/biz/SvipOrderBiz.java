package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SvipOrderExtMapper;
import com.jf.dao.SvipOrderMapper;
import com.jf.entity.SvipOrder;
import com.jf.entity.SvipOrderExample;
import com.jf.entity.SvipOrderExt;
import com.jf.entity.SvipOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SvipOrderBiz extends BaseService<SvipOrder, SvipOrderExample> {

	@Autowired
	private SvipOrderMapper dao;
	@Autowired
	private SvipOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSvipOrderMapper(SvipOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SvipOrderExt save(SvipOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SvipOrder update(SvipOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SvipOrder model = new SvipOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SvipOrderExt findById(int id){
		return extDao.findById(id);
	}

	public SvipOrderExt find(SvipOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SvipOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SvipOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SvipOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SvipOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SvipOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SvipOrderExt> list(SvipOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SvipOrderExt> paginate(SvipOrderExtExample example) {
		List<SvipOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
