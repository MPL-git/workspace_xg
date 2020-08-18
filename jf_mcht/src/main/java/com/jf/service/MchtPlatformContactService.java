package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtPlatformContactCustomMapper;
import com.jf.dao.MchtPlatformContactMapper;
import com.jf.entity.MchtPlatformContact;
import com.jf.entity.MchtPlatformContactCustom;
import com.jf.entity.MchtPlatformContactExample;
import com.jf.entity.PlatformContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MchtPlatformContactService extends BaseService<MchtPlatformContact,MchtPlatformContactExample> {
	@Resource
	private MchtPlatformContactMapper mchtPlatformContactMapper;
	@Autowired
	private MchtPlatformContactCustomMapper mchtPlatformContactCustomMapper;
	@Autowired
	private PlatformContactService platformContactService;

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


	/**
	 * 获取商家的平台对接人
	 */
	public List<MchtPlatformContact> listByMchtId(int mchtId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("status", Const.MCHT_CONTACT_STATUS_NORMAL);
		return list(queryObject);
	}

	public MchtPlatformContact findByMchtId(int mchtId, String platContactType) {
		List<PlatformContact> platformContactList = platformContactService.listByType(platContactType);
		List<Integer> platformContactIdList = new ArrayList<>();
		for(PlatformContact platformContact : platformContactList){
			platformContactIdList.add(platformContact.getId());
		}

		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("platformContactIdIn", platformContactIdList);
		queryObject.addQuery("status", Const.MCHT_CONTACT_STATUS_NORMAL);
		List<MchtPlatformContact> list = list(queryObject);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtPlatformContact> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtPlatformContact> paginate(QueryObject queryObject) {
		MchtPlatformContactExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtPlatformContact> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
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
