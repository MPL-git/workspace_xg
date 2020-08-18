package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WxRedpackExtMapper;
import com.jf.dao.WxRedpackMapper;
import com.jf.entity.WxRedpack;
import com.jf.entity.WxRedpackExample;
import com.jf.entity.WxRedpackExt;
import com.jf.entity.WxRedpackExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WxRedpackBiz extends BaseService<WxRedpack, WxRedpackExample> {

	@Autowired
	private WxRedpackMapper dao;
	@Autowired
	private WxRedpackExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWxRedpackMapper(WxRedpackMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WxRedpackExt save(WxRedpackExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WxRedpack update(WxRedpack model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WxRedpack model = new WxRedpack();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WxRedpackExt findById(int id){
		return extDao.findById(id);
	}

	public WxRedpackExt find(WxRedpackExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WxRedpackExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WxRedpackExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WxRedpackExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WxRedpackExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WxRedpackExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WxRedpackExt> list(WxRedpackExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WxRedpackExt> paginate(WxRedpackExtExample example) {
		List<WxRedpackExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
