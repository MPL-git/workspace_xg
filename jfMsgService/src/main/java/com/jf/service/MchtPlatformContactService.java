package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtPlatformContactMapper;
import com.jf.entity.MchtPlatformContact;
import com.jf.entity.MchtPlatformContactExample;
import com.jf.entity.PlatformContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MchtPlatformContactService extends BaseService<MchtPlatformContact,MchtPlatformContactExample> {
	@Resource
	private MchtPlatformContactMapper dao;
	@Resource
	private PlatformContactService platformContactService;

	@Autowired
	public void setMchtContactMapper(MchtPlatformContactMapper MchtPlatformContactMapper) {
		super.setDao(MchtPlatformContactMapper);
		this.dao = MchtPlatformContactMapper;
	}

	/**
	 * 获取商家的平台对接人的手机号
	 */
	public String findMobile(int mchtId, String contactType){
		String mobile = null;
		List<MchtPlatformContact> list = listByMchtId(mchtId);
		PlatformContact platformContact;
		for(MchtPlatformContact mchtPlatformContact : list){
			platformContact = platformContactService.findById(mchtPlatformContact.getPlatformContactId());
			if (contactType.equals(platformContact.getContactType())) {
				mobile = platformContact.getMobile();
				break;
			}
		}
		return mobile;
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


}
