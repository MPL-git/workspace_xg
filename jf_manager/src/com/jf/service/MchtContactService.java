package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jf.common.utils.StringUtils;
import com.jf.dao.MchtContactCustomMapper;
import com.jf.dao.MchtContactMapper;
import com.jf.dao.MchtContractCustomMapper;
import com.jf.entity.MchContactCustom;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactCustomExample;
import com.jf.entity.MchtContactCustomExample.MchtContactCustomCriteria;
import com.jf.entity.MchtContactExample;
import com.jf.entity.MchtContractCustomExample;
import com.jf.entity.MchtContractCustomExample.MchtContractCustomCriteria;
import com.jf.vo.Page;

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
	
	public List<MchContactCustom> selectByCustomExample(MchtContactExample example){
    	return mchtContactCustomMapper.selectByCustomExample(example);
    }
	
	public int countByCustomExample(MchtContactExample example){
		return mchtContactCustomMapper.countByCustomExample(example);
	}
	
	public HashMap<String, Object> mchtDockingPersonAuditList(HttpServletRequest request, Page page){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
		MchtContactCustomExample example = new MchtContactCustomExample();
		MchtContactCustomCriteria criteria = example.createCriteria();
		criteria.andMchtDockingPersonAudit();
		criteria.andDelFlagEqualTo("0");
		String mchtId = request.getParameter("mchtId");
		if(!StringUtils.isEmpty(mchtId)){
			criteria.andMchtCodeEqualTo(mchtId);
		}
		String name = request.getParameter("name");
		if(!StringUtils.isEmpty(name)){
			criteria.andNameLike(name);
		}
		String auditStatus = request.getParameter("auditStatus");
		if(!StringUtils.isEmpty(auditStatus)){
			criteria.andAuditStatusEqualTo(auditStatus);
		}
		String productType = request.getParameter("productType");
		if(!StringUtils.isEmpty(productType)){
			criteria.andProductTypeEqualTo(Integer.parseInt(productType));
		}
		String platformContact = request.getParameter("platformContact");
		if(!StringUtils.isEmpty(platformContact)){
			
			criteria.andplatformContactIn(Integer.parseInt(platformContact));				
		}
		String contactType = request.getParameter("contactType");
		if(!StringUtils.isEmpty(contactType)){
			criteria.andContactTypeEqualTo(contactType);
		}
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
		
		resMap.put("Rows", mchtContactCustomMapper.selectByCustomExample(example));
		resMap.put("Total", mchtContactCustomMapper.countByCustomExample(example));
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}


	public void updateByContactList(List<MchtContact> updateMchtContacts) {
		// TODO Auto-generated method stub
		
	}

	public List<MchtContact> selectByExampleGroupBy(List<Integer> mchtIdList) {
		// TODO Auto-generated method stub
		return mchtContactCustomMapper.selectByExampleGroupBy(mchtIdList);
	}
}
