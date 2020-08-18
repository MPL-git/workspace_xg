package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtPlatformContactCustomMapper;
import com.jf.dao.MchtPlatformContactMapper;
import com.jf.entity.MchtPlatformContact;
import com.jf.entity.MchtPlatformContactCustom;
import com.jf.entity.MchtPlatformContactCustomExample;
import com.jf.entity.MchtPlatformContactExample;

@Service
@Transactional
public class MchtPlatformContactService extends BaseService<MchtPlatformContact,MchtPlatformContactExample> {
	@Resource
	private MchtPlatformContactMapper mchtPlatformContactMapper;
	@Autowired
	private MchtPlatformContactCustomMapper mchtPlatformContactCustomMapper;
	@Autowired
	public void setMchtContactMapper(MchtPlatformContactMapper MchtPlatformContactMapper) {
		super.setDao(MchtPlatformContactMapper);
		this.mchtPlatformContactMapper = MchtPlatformContactMapper;
	}
	
	public int countMchtPlatformContactCustomByExample(MchtPlatformContactExample example){
		return mchtPlatformContactCustomMapper.countByExample(example);
	}
    public List<MchtPlatformContactCustom> selectMchtPlatformContactCustomByExample(MchtPlatformContactExample example){
    	return mchtPlatformContactCustomMapper.selectByExample(example);
    }
    public MchtPlatformContactCustom selectMchtPlatformContactCustomByPrimaryKey(Integer id){
    	return mchtPlatformContactCustomMapper.selectByPrimaryKey(id);
    }
    
    
    public String findMobile(int mchtId, String contactType){
    	String mobile = null;
		MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
		mchtPlatformContactExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId)
				.andStatusEqualTo("1");
		List<MchtPlatformContactCustom> mchtPlatformContacts = selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
		if (mchtPlatformContacts != null && mchtPlatformContacts.size() > 0) {
			for (MchtPlatformContactCustom mchtPlatformContact : mchtPlatformContacts) {
				if (contactType.equals(mchtPlatformContact.getContactType())) {
					mobile = mchtPlatformContact.getMobile();
					break;
				}
			}
		}
		
		return mobile;
    }
    
	/**
	 * 获取平台对接人对接的商家
     */
	public List<MchtPlatformContact> listByPlatformContactId(List<Integer> platformContactIdList) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("platformContactIdIn", platformContactIdList);
		queryObject.addQuery("status", Const.MCHT_CONTACT_STATUS_NORMAL);
		return list(queryObject);
	}

	/**
	 * 获取商家的平台对接人
	 */
	public List<MchtPlatformContact> listByMchtId(int mchtId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("status", Const.MCHT_CONTACT_STATUS_NORMAL);
		return list(queryObject);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtPlatformContact> list(QueryObject queryObject) {
		MchtPlatformContactExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<MchtPlatformContact> paginate(QueryObject queryObject) {
		MchtPlatformContactExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtPlatformContact> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtPlatformContact>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtPlatformContactExample builderQuery(QueryObject queryObject) {
		MchtPlatformContactExample example = new MchtPlatformContactExample();
		MchtPlatformContactExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("platformContactId") != null){
			criteria.andPlatformContactIdEqualTo(queryObject.getQueryToInt("platformContactId"));
		}
		if(queryObject.getQuery("platformContactIdIn") != null){
			List<Integer> list = queryObject.getQuery("platformContactIdIn");
			criteria.andPlatformContactIdIn(list);
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	/**
	 * 
	 * @Title updatePlatformContactList   
	 * @Description TODO(批量更改商家平台对接人)   
	 * @author pengl
	 * @date 2017年9月27日 下午5:06:31
	 */
	public Boolean updatePlatformContactList(HttpServletRequest request, 
			String ids, String contactType, String platformContactId, Integer userId) {
		try {
			String[] strs = ids.split(",");
			List<Integer> idList = new ArrayList<Integer>();
			for (int i = 0; i < strs.length; i++) {
				idList.add(Integer.parseInt(strs[i]));
			}
			//修改商家平台对接人
			MchtPlatformContactCustomExample mchtPlatformContactCustomExample = new MchtPlatformContactCustomExample();
			MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria criteria = mchtPlatformContactCustomExample.createCriteria();
			MchtPlatformContact mchtPlatformContact = new MchtPlatformContact();
			mchtPlatformContact.setStatus("2");
			mchtPlatformContact.setUpdateBy(userId);
			mchtPlatformContact.setUpdateDate(new Date());
			if(!platformContactId.equals("status")) {
					criteria.andDelFlagEqualTo("0")
					.andStatusEqualTo("1")
					.andMchtIdIn(idList);
				criteria.andPlatformContactTypeEqualTo(contactType);
				this.updateByExampleSelective(mchtPlatformContact, mchtPlatformContactCustomExample);
				
				//新增商家平台对接人
				for(Integer mchtId : idList) {
					MchtPlatformContact mchContact = new MchtPlatformContact();
					mchContact.setMchtId(mchtId);
					mchContact.setPlatformContactId(Integer.valueOf(platformContactId));
					mchContact.setStatus("1");
					mchContact.setCreateBy(userId);
					mchContact.setCreateDate(new Date());
					mchContact.setUpdateBy(userId);
					mchContact.setUpdateDate(new Date());
					mchContact.setDelFlag("0");
					this.insertSelective(mchContact);
				}
			}else {
				//取消此对接人
				criteria.andDelFlagEqualTo("0")
					.andStatusEqualTo("1")
					.andMchtIdIn(idList);
				criteria.andPlatformContactTypeEqualTo(contactType);
				this.updateByExampleSelective(mchtPlatformContact, mchtPlatformContactCustomExample);
			}
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public List<Integer> getMchtIdsByStaffId(int staffId) {
		return mchtPlatformContactCustomMapper.getMchtIdsByStaffId(staffId);
	}
}
