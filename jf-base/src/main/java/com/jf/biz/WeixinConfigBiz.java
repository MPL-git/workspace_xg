package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinConfigExtMapper;
import com.jf.dao.WeixinConfigMapper;
import com.jf.entity.WeixinConfig;
import com.jf.entity.WeixinConfigExample;
import com.jf.entity.WeixinConfigExt;
import com.jf.entity.WeixinConfigExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinConfigBiz extends BaseService<WeixinConfig, WeixinConfigExample> {

	@Autowired
	private WeixinConfigMapper dao;
	@Autowired
	private WeixinConfigExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinConfigMapper(WeixinConfigMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinConfigExt save(WeixinConfigExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinConfig update(WeixinConfig model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinConfig model = new WeixinConfig();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinConfigExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinConfigExt find(WeixinConfigExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinConfigExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinConfigExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinConfigExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinConfigExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinConfigExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinConfigExt> list(WeixinConfigExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinConfigExt> paginate(WeixinConfigExtExample example) {
		List<WeixinConfigExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
