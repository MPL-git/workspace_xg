package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformContactMapper;
import com.jf.entity.MchtPlatformContact;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlatformContactService extends BaseService<PlatformContact, PlatformContactExample> {

	@Autowired
	private PlatformContactMapper dao;

	@Resource
	MchtPlatformContactService mchtPlatformContactService;

	@Autowired
	public void setPlatformContactMapper(PlatformContactMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}




	public List<PlatformContact> listByType(String contactType) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("contactType", contactType);
		queryObject.addQuery("status", Const.PLAT_CONTACT_STATUS_NORMAL);
		return list(queryObject);
	}

	/**
	 * 获取商家某个类型的平台对接人
	 */
	public PlatformContact findByMchtId(int mchtId, String contactType){
		List<MchtPlatformContact> mchtPlatformContactList = mchtPlatformContactService.listByMchtId(mchtId);
		if(mchtPlatformContactList.size() == 0)	return null;

		List<Integer> idList = new ArrayList<>();
		for(MchtPlatformContact mchtPlatformContact : mchtPlatformContactList){
			idList.add(mchtPlatformContact.getPlatformContactId());
		}

		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("contactType", contactType);
		queryObject.addQuery("status", Const.PLAT_CONTACT_STATUS_NORMAL);
		queryObject.addQuery("idIn", idList);
		List<PlatformContact> list = list(queryObject);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public PlatformContact findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<PlatformContact> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<PlatformContact> paginate(QueryObject queryObject) {
		PlatformContactExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<PlatformContact> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private PlatformContactExample builderQuery(QueryObject queryObject) {
		PlatformContactExample example = new PlatformContactExample();
		PlatformContactExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("contactType") != null){
			criteria.andContactTypeEqualTo(queryObject.getQueryToStr("contactType"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
