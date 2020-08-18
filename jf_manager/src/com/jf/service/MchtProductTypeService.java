package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.DataDicUtil;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.dao.MchtProductTypeCustomMapper;
import com.jf.dao.MchtProductTypeMapper;
import com.jf.entity.MchtInfoChangeLog;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeCustom;
import com.jf.entity.MchtProductTypeCustomExample;
import com.jf.entity.MchtProductTypeExample;
import com.jf.vo.Page;

@Service
@Transactional
public class MchtProductTypeService extends BaseService<MchtProductType, MchtProductTypeExample> {
	@Autowired
	private MchtProductTypeMapper mchtProductTypeMapper;
	
	@Autowired
	private MchtProductTypeCustomMapper mchtProductTypeCustomMapper;
	
	@Autowired
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;

	
	@Autowired
	public void setMchtProductTypeMapper(MchtProductTypeMapper mchtProductTypeMapper) {
		super.setDao(mchtProductTypeMapper);
		this.mchtProductTypeMapper = mchtProductTypeMapper;
	}
	
	public List<MchtProductTypeCustom> getMchtProductTypeCustomsByMchtId(Integer mchtId,Page page){
		return mchtProductTypeCustomMapper.getMchtProductTypeCustomsByMchtId(mchtId,page);
	}
	
  	public int countMchtProductTypeCustomByExample(MchtProductTypeCustomExample example){
  		return mchtProductTypeCustomMapper.countByExample(example);
  	}

  	public List<MchtProductTypeCustom> selectMchtProductTypeCustomByExample(MchtProductTypeCustomExample example){
  		return mchtProductTypeCustomMapper.selectByExample(example);
  	}

	public List<MchtProductType> findList(QueryObject queryObject) {
		return mchtProductTypeMapper.selectByExample(builderQuery(queryObject));
	}

	private MchtProductTypeExample builderQuery(QueryObject queryObject) {
		MchtProductTypeExample example = new MchtProductTypeExample();
		MchtProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("statusIn") != null){
			List<String> statusIn = queryObject.getQuery("statusIn");
			criteria.andStatusIn(statusIn);
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
	
	public void updateMchtProductType(MchtProductType mchtProductType) {
		MchtProductTypeCustom mchtProductTypeCustom = mchtProductTypeCustomMapper.selectByPrimaryKey(mchtProductType.getId());
		
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_PRODUCT_TYPE", "STATUS", mchtProductType.getStatus()));
		mchtInfoChangeLog.setBeforeChange(DataDicUtil.getStatusDesc("BU_MCHT_PRODUCT_TYPE", "STATUS", mchtProductTypeCustom.getStatus()));
		mchtInfoChangeLog.setCreateBy(mchtProductType.getUpdateBy());
		mchtInfoChangeLog.setCreateDate(mchtProductType.getUpdateDate());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(mchtProductTypeCustom.getProductTypeName());
		mchtInfoChangeLog.setLogType("类目审核");
		mchtInfoChangeLog.setMchtId(mchtProductTypeCustom.getMchtId());
		mchtInfoChangeLog.setRemarks(mchtProductTypeCustom.getRemarks());
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
		mchtProductTypeMapper.updateByPrimaryKeySelective(mchtProductType);
	}

	public List<HashMap<String, Object>> countByProductType(HashMap<String, Object> paramMap) {
		return mchtProductTypeCustomMapper.countByProductType(paramMap);
	}
	
}
