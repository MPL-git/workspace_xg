package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PlatformContactCustomMapper;
import com.jf.dao.PlatformContactMapper;
import com.jf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PlatformContactService extends BaseService<PlatformContact, PlatformContactExample>{
	@Resource
	PlatformContactMapper platformContactMapper;
	
	@Resource
	PlatformContactCustomMapper platformContactCustomMapper;

	@Resource
	MchtPlatformContactService mchtPlatformContactService;

	@Autowired
	public void setProductBrandMapper(PlatformContactMapper PlatformContactMapper) {
		super.setDao(PlatformContactMapper);
		this.platformContactMapper = PlatformContactMapper;
	}
	
	public int countPlatformContactCustomByExample(PlatformContactExample example){
		return platformContactCustomMapper.countByExample(example);
	}
    public List<PlatformContactCustom> selectPlatformContactCustomByExample(PlatformContactExample example){
    	return platformContactCustomMapper.selectByExample(example);
    }
    public PlatformContactCustom selectPlatformContactCustomByPrimaryKey(Integer id){
    	return platformContactCustomMapper.selectByPrimaryKey(id);
    }
    	
	public List<MchtInfo> selectMchtInfosByPlatContactId(Integer id){
		return platformContactCustomMapper.selectMchtInfosByPlatContactId(id);
	}
	
	public List<PlatformContact> selectPlatformContByMchId(HashMap<String, Object> paramMap){
		return  platformContactCustomMapper.selectPlatformContByMchId(paramMap);
	}
	
	public Integer getStaffById(Integer staffId){
		return platformContactCustomMapper.getStaffById(staffId);
	}

	public String getContactNameByMchtIdAndContactType(Integer mchtId, Integer contactType) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("mchtId", mchtId);
		map.put("contactType", contactType);
		return platformContactCustomMapper.getContactNameByMchtIdAndContactType(map);
	}


	/**
	 * 获取当前用户某个类型的平台对接人
     */
	public PlatformContact findByStaffId(int staffId, String contactType){
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("staffId", staffId);
		queryObject.addQuery("contactType", contactType);
		queryObject.addQuery("status", Const.PLAT_CONTACT_STATUS_NORMAL);
		List<PlatformContact> list = list(queryObject);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取商家某个类型的平台对接人
	 */
	public PlatformContact findByMchtId(int mchtId, String contactType){
		List<MchtPlatformContact> mchtPlatformContactList = mchtPlatformContactService.listByMchtId(mchtId);
		if(mchtPlatformContactList.size() == 0)	return null;

		List<Integer> idList = new ArrayList<Integer>();
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
		PlatformContactExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<PlatformContact> paginate(QueryObject queryObject) {
		PlatformContactExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<PlatformContact> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<PlatformContact>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
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
		if(queryObject.getQuery("idIn") != null){
			List<Integer> list = queryObject.getQuery("idIn");
			criteria.andIdIn(list);
		}
		if(queryObject.getQuery("staffId") != null){
			criteria.andStaffIdEqualTo(queryObject.getQueryToInt("staffId"));
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

