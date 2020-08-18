package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.beans.Menu;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ZsProductTypeCustomMapper;
import com.jf.dao.ZsProductTypeMapper;
import com.jf.entity.ZsProductType;
import com.jf.entity.ZsProductTypeCustom;
import com.jf.entity.ZsProductTypeExample;

@Service
@Transactional
public class ZsProductTypeService extends BaseService<ZsProductType,ZsProductTypeExample> {
	
	@Autowired
	private ZsProductTypeMapper zsProductTypeMapper;
	
	@Resource
	private ZsProductTypeCustomMapper zsProductTypeCustomMapper;
	
	@Autowired
	public void setProductTypeMapper(ZsProductTypeMapper zsProductTypeMapper) {
		super.setDao(zsProductTypeMapper);
		this.zsProductTypeMapper = zsProductTypeMapper;
	}
	
	public List<Menu> selectZsProductType(Map<String, Object> paramMap){
		
		List<Menu> list = zsProductTypeCustomMapper.selectZsProductType(paramMap);
		return Menu.buildTree(list, 0);
	}
	public List<?> selectProductTypeList(Map<String, Object> paramMap){
		List<?> list = zsProductTypeCustomMapper.selectProductTypeList(paramMap);
		return list;
	}
    public List<?> zsselectProductTypeList(Map<String, Object> paramMap){
		List<?> list = zsProductTypeCustomMapper.zsselectProductTypeList(paramMap);
		return list;
	}
	public String countdata(HashMap<String, Object> paramMap){
		String tatalNum=zsProductTypeCustomMapper.countdata(paramMap);
		return tatalNum;
	}
	public String queryDataCount(HashMap<String, Object> paramMap){
		String tatalNum=zsProductTypeCustomMapper.queryDataCount(paramMap);
		return tatalNum;
	}
	public List<?> getprarentList(HashMap<String, Object> paramMap){
		List<?> getprarentList=zsProductTypeCustomMapper.getprarentList(paramMap);
		return getprarentList;
	}
	public String getprarentId(HashMap<String, Object> paramMap){
		String getprarentId=zsProductTypeCustomMapper.getprarentId(paramMap);
		return getprarentId;
	}
	
	public String getPrarentF(HashMap<String, Object> paramMap){
		String getPrarentF=zsProductTypeCustomMapper.getPrarentF(paramMap);
		return getPrarentF;
	}
	
	public int getIsNotType(HashMap<String, Object> paramMap){
		int getIsNotType=zsProductTypeCustomMapper.getIsNotType(paramMap);
		return getIsNotType;
	}

	public String getProdTypeName(HashMap<String, Object> paramMap){
		String getProdTypeName=zsProductTypeCustomMapper.getProdTypeName(paramMap);
		return getProdTypeName;
	}
	 
	public int gettlevvel(Map<String, Object> paramMap) {
		return zsProductTypeCustomMapper.gettlevvel(paramMap);
	}

	public List<?> selectProductTypeListByid(HashMap<String, Object> map) {
		List<?> list =zsProductTypeCustomMapper.selectProductTypeListByid(map);
		return list;
	}
	public List<ZsProductTypeCustom> selectZsProductTypeCustomByExample(ZsProductTypeExample example){
    	return zsProductTypeCustomMapper.zsselectByExample(example);
    }
	public int countZsProductTypeCustomByExample(ZsProductTypeExample example){
		return zsProductTypeCustomMapper.zscountByExample(example);
	}



	public List<ZsProductType> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private ZsProductTypeExample builderQuery(QueryObject queryObject) {
		ZsProductTypeExample example = new ZsProductTypeExample();
		ZsProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("productTypeIds") != null){
			List<Integer> ids = queryObject.getQuery("productTypeIds");
			criteria.andIdIn(ids);
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public String getNamesByIds(String ids) {
		return zsProductTypeCustomMapper.getNamesByIds(ids);
	}
	
	/**
	 * 
	 * @Title addInsertSelective   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月3日 下午2:04:03
	 */
	public Map<String, Object> addInsertSelective(List<ZsProductType> zsProductTypeList) {
		Map<String, Object> nameIdMap = new HashMap<String, Object>();
		StringBuffer ids = new StringBuffer();
		StringBuffer names = new StringBuffer();
		for(ZsProductType zsProductType : zsProductTypeList) {
			zsProductTypeMapper.insertSelective(zsProductType);
			int seqNo = zsProductType.getId()*100;
			zsProductType.setSeqNo(seqNo);
			zsProductTypeMapper.updateByPrimaryKeySelective(zsProductType);
			if(ids.length() == 0) {
				ids.append(zsProductType.getId());
			}else {
				ids.append(","+zsProductType.getId());
			}
			if(names.length() == 0) {
				names.append(zsProductType.getName());
			}else {
				names.append(","+zsProductType.getName());
			}
		}
		nameIdMap.put("ids", ids);
		nameIdMap.put("names", names);
		return nameIdMap;
	}

	public List<String> getZsProductTypeDetails(List<Integer> zsProductTypeIdList) {
		return zsProductTypeCustomMapper.getZsProductTypeDetails(zsProductTypeIdList);
	}
	
}
