package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DataDicUtil;
import com.jf.dao.IndexTopTabMapper;
import com.jf.entity.IndexTopTab;
import com.jf.entity.IndexTopTabExample;
import com.jf.entity.SysParamCfg;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IndexTopTabService extends BaseService<IndexTopTab, IndexTopTabExample> {
	@Autowired
	private IndexTopTabMapper indexTopTabMapper;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	public void setIndexTopTabMapper(IndexTopTabMapper indexTopTabMapper) {
		this.setDao(indexTopTabMapper);
		this.indexTopTabMapper = indexTopTabMapper;
	}
	public List<Map<String, Object>> getTopTabs() {
		List<Map<String,Object>> categoryList = new ArrayList<Map<String,Object>>();
		IndexTopTabExample indexTopTabExample = new IndexTopTabExample();
		indexTopTabExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		indexTopTabExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		List<IndexTopTab> tabs = selectByExample(indexTopTabExample);
		if(CollectionUtils.isNotEmpty(tabs)){
			Map<String,Object> dataMap = new HashMap<String,Object>();
			String jumpType = "0";//0首页 1特卖 2商城搜索 3预告
			dataMap.put("id", "");
			dataMap.put("name", "分类:");
			dataMap.put("adCatalog", "");
			dataMap.put("jumpType", jumpType);
			categoryList.add(dataMap);
			for (IndexTopTab tab : tabs) {
				dataMap = new HashMap<String,Object>();
				jumpType = tab.getType();
				Integer productTypeId = tab.getProductTypeId();
				String adCatalog = tab.getCatalog() == null ? "" : tab.getCatalog();
				String name = tab.getKeyword();
				if("1".equals(jumpType)){
					SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", productTypeId.toString());
					if(sysParamCfg == null){
						continue;
					}
					name = sysParamCfg.getParamName();
					adCatalog = sysParamCfg.getParamOrder();
				}else if("3".equals(jumpType)){
					name = "预告";
				}
				dataMap.put("id", productTypeId == null ? "" : productTypeId);
				dataMap.put("name", name);
				dataMap.put("adCatalog", adCatalog);
				dataMap.put("jumpType", jumpType);
				categoryList.add(dataMap);
			}
		}
		return categoryList;
	}
	
	
}
