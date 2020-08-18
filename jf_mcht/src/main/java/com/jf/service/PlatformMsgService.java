package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformMsgCustomMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.entity.PlatformMsg;
import com.jf.entity.PlatformMsgCustom;
import com.jf.entity.PlatformMsgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class PlatformMsgService extends BaseService<PlatformMsg,PlatformMsgExample> {

	@Autowired
	private PlatformMsgMapper dao;
	@Autowired
	private PlatformMsgCustomMapper platformMsgCustomMapper;
	
	@Autowired
	public void setPlatformMsgMapper(PlatformMsgMapper platformMsgMapper) {
		super.setDao(platformMsgMapper);
		this.dao = platformMsgMapper;
	}
	
	public void read(int id){
		PlatformMsg model = findById(id);
		model.setStatus(Const.FLAG_TRUE);
		update(model);
	}


	public PlatformMsg findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public PlatformMsg update(PlatformMsg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		PlatformMsg model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int countUnread(int mchtId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("status", Const.FLAG_FALSE);
		queryObject.addQuery("delFlag", Const.FLAG_FALSE);
		return count(queryObject);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<PlatformMsg> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<PlatformMsgCustom> paginate(QueryObject queryObject) {
		PlatformMsgExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());
		
		List<PlatformMsgCustom> list = platformMsgCustomMapper.selectByExample(example);
		int totalCount = platformMsgCustomMapper.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private PlatformMsgExample builderQuery(QueryObject queryObject) {
		PlatformMsgExample example = new PlatformMsgExample();
		PlatformMsgExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("msgType") != null){
			criteria.andMsgTypeEqualTo(queryObject.getQueryToStr("msgType"));
		}
		if(queryObject.getQuery("title") != null){
			criteria.andTitleLike("%" + queryObject.getQueryToStr("title") + "%");
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}


	public List<HashMap<String, Object>> getMsgTypesByMchtId(Integer mchtId) {
		return platformMsgCustomMapper.getMsgTypesByMchtId(mchtId);
	}
}
