package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtDepositCustomMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositCustom;
import com.jf.entity.MchtDepositExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MchtDepositService extends BaseService<MchtDeposit,MchtDepositExample> {
	@Autowired
	private MchtDepositMapper mchtDepositMapper;
	
	@Resource
	private MchtDepositCustomMapper mchtDepositCustomMapper;
	
	@Autowired
	public void setMchtDepositMapper(MchtDepositMapper mchtDepositMapper) {
		super.setDao(mchtDepositMapper);
		this.mchtDepositMapper = mchtDepositMapper;
	}
	
	public List<MchtDepositCustom> selectMchtDepositList(HashMap<String, Object> paramMap)
	{
		List<MchtDepositCustom> list = mchtDepositCustomMapper.selectMchtDepositList(paramMap);
		return list;
	}
	
	public int mchtDepositListCount(HashMap<String, Object> paramMap)
	{
		return mchtDepositCustomMapper.mchtDepositListCount(paramMap);
	}



	public MchtDeposit findByMchtId(int mchtId){
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.setLimitSize(1);
		List<MchtDeposit> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtDeposit> list(QueryObject queryObject) {
		MchtDepositExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<MchtDeposit> paginate(QueryObject queryObject) {
		MchtDepositExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtDeposit> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtDeposit>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtDepositExample builderQuery(QueryObject queryObject) {
		MchtDepositExample example = new MchtDepositExample();
		MchtDepositExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
	
	public List<MchtDepositCustom> selectMchtDepositMchtInfoList(Map<String,Object> map) {
		return mchtDepositCustomMapper.selectMchtDepositMchtInfoList(map);
	}
	public int mchtDepositMchtInfoListCount(Map<String,Object> map) {
		return mchtDepositCustomMapper.mchtDepositMchtInfoListCount(map);
	}
	
}
