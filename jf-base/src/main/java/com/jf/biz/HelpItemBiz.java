package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.HelpItemExtMapper;
import com.jf.dao.HelpItemMapper;
import com.jf.entity.HelpItem;
import com.jf.entity.HelpItemExample;
import com.jf.entity.HelpItemExt;
import com.jf.entity.HelpItemExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HelpItemBiz extends BaseService<HelpItem, HelpItemExample> {

	@Autowired
	private HelpItemMapper dao;
	@Autowired
	private HelpItemExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setHelpItemMapper(HelpItemMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public HelpItemExt save(HelpItemExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public HelpItem update(HelpItem model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		HelpItem model = new HelpItem();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public HelpItemExt findById(int id){
		return extDao.findById(id);
	}

	public HelpItemExt find(HelpItemExtExample example){
		return extDao.find(example.fill());
	}

	public int count(HelpItemExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, HelpItemExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, HelpItemExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, HelpItemExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(HelpItemExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<HelpItemExt> list(HelpItemExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<HelpItemExt> paginate(HelpItemExtExample example) {
		List<HelpItemExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
