package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtContactCustomMapper;
import com.jf.dao.MchtContactMapper;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactCustom;
import com.jf.entity.MchtContactExample;
import com.jf.entity.MchtContactExample.Criteria;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtContactService extends BaseService<MchtContact,MchtContactExample> {
	@Autowired
	private MchtContactMapper mchtContactMapper;
	@Autowired
	private MchtContactCustomMapper mchtContactCustomMapper;
	@Autowired
	public void setMchtContactMapper(MchtContactMapper mchtContactMapper) {
		super.setDao(mchtContactMapper);
		this.mchtContactMapper = mchtContactMapper;
	}
	
    public int countMchtContactCustomByExample(MchtContactExample example){
    	return mchtContactCustomMapper.countByExample(example);
    }

    public List<MchtContactCustom> selectMchtContactCustomByExample(MchtContactExample example){
    	return mchtContactCustomMapper.selectByExample(example);
    }

    public MchtContactCustom selectMchtContactCustomByPrimaryKey(Integer id){
    	return mchtContactCustomMapper.selectByPrimaryKey(id);
    }
    
    
    public ResponseMsg mchtContactSave(MchtContact mchtContact) throws ArgException{
    	
    	if(StringUtil.isEmpty(mchtContact.getContactName())){
    		throw new ArgException("姓名不能为空");
    	}
    	if(StringUtil.isEmpty(mchtContact.getMobile())){
    		throw new ArgException("手机号不能为空");
    	}
    	
    	if(mchtContact.getId()==null){
		//同一对接人类型有存在一个非通过的对接人都不能创建新的联系人
    	Integer mchtId = mchtContact.getMchtId();
    	String contactType = mchtContact.getContactType();
    	MchtContactExample mchtContactExample = new MchtContactExample();
    	Criteria criteria = mchtContactExample.createCriteria();
    	criteria.andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andContactTypeEqualTo(contactType);
    	List<MchtContact> list = mchtContactMapper.selectByExample(mchtContactExample);
    	for (MchtContact contact : list) {
			if(StringUtils.isBlank(contact.getAuditStatus()) || StringUtils.equals(contact.getAuditStatus(), "0") || StringUtils.equals(contact.getAuditStatus(), "2")){
				return new ResponseMsg(ResponseMsg.ERROR, "存在同一对接人类型且非通过的对接人信息，无法创建新对接人");
			}
		}
    	mchtContactMapper.insertSelective(mchtContact);
    	}else{
    		mchtContactMapper.updateByPrimaryKeySelective(mchtContact);
    	}
    	return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
    }
    
    public void setDefaultMchtContact(Integer id) throws ArgException{
    	MchtContact mchtContact=mchtContactMapper.selectByPrimaryKey(id);
    	if(mchtContact.getMchtId()==null||StringUtil.isEmpty(mchtContact.getContactType())){
    		throw new ArgException("设置出错");
    	}
    	
    	MchtContactExample mchtContactExample=new MchtContactExample();
    	mchtContactExample.createCriteria().andMchtIdEqualTo(mchtContact.getMchtId()).andContactTypeEqualTo(mchtContact.getContactType());
    	MchtContact mchtContact4Update=new MchtContact();
		mchtContact4Update.setIsPrimary("0");
		mchtContactMapper.updateByExampleSelective(mchtContact4Update, mchtContactExample);
		
		mchtContact4Update.setId(id);
		mchtContact4Update.setIsPrimary("1");
		mchtContactMapper.updateByPrimaryKeySelective(mchtContact4Update);
    	
    }


	/**
	 * 获取主联系人
     */
	public MchtContact findByMchtId(int mchtId, String contactType){
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("contactType", contactType);
		queryObject.addQuery("isPrimary", Const.FLAG_TRUE);
		queryObject.setLimitSize(1);
		List<MchtContact> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	public List<MchtContact> list(QueryObject queryObject) {
		MchtContactExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	private MchtContactExample builderQuery(QueryObject queryObject) {
		MchtContactExample example = new MchtContactExample();
		MchtContactExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("contactType") != null){
			criteria.andContactTypeEqualTo(queryObject.getQueryToStr("contactType"));
		}
		if(queryObject.getQuery("isPrimary") != null){
			criteria.andIsPrimaryEqualTo(queryObject.getQueryToStr("isPrimary"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public List<MchtContactCustom> selectCustomByExample(MchtContactExample mchtContactExample) {
		return mchtContactCustomMapper.selectCustomByExample(mchtContactExample);
	}
}
