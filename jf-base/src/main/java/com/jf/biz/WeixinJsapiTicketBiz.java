package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinJsapiTicketExtMapper;
import com.jf.dao.WeixinJsapiTicketMapper;
import com.jf.entity.WeixinJsapiTicket;
import com.jf.entity.WeixinJsapiTicketExample;
import com.jf.entity.WeixinJsapiTicketExt;
import com.jf.entity.WeixinJsapiTicketExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinJsapiTicketBiz extends BaseService<WeixinJsapiTicket, WeixinJsapiTicketExample> {

	@Autowired
	private WeixinJsapiTicketMapper dao;
	@Autowired
	private WeixinJsapiTicketExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinJsapiTicketMapper(WeixinJsapiTicketMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinJsapiTicketExt save(WeixinJsapiTicketExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinJsapiTicket update(WeixinJsapiTicket model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinJsapiTicket model = new WeixinJsapiTicket();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinJsapiTicketExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinJsapiTicketExt find(WeixinJsapiTicketExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinJsapiTicketExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinJsapiTicketExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinJsapiTicketExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinJsapiTicketExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinJsapiTicketExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinJsapiTicketExt> list(WeixinJsapiTicketExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinJsapiTicketExt> paginate(WeixinJsapiTicketExtExample example) {
		List<WeixinJsapiTicketExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
