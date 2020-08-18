package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtils;
import com.jf.dao.AppVersionCustomMapper;
import com.jf.dao.AppVersionMapper;
import com.jf.entity.AppVersion;
import com.jf.entity.AppVersionCustom;
import com.jf.entity.AppVersionExample;

@Service
@Transactional
public class AppVersionService extends BaseService<AppVersion,AppVersionExample> {
	@Autowired
	private AppVersionMapper appVersionMapper;
	@Autowired
	private AppVersionCustomMapper appVersionCustomMapper;
	
	@Autowired
	public void setAppVersionMapper(AppVersionMapper appVersionMapper) {
		super.setDao(appVersionMapper);
		this.appVersionMapper = appVersionMapper;
	}
	
	public int countCustomByExample(AppVersionExample example){
		return appVersionCustomMapper.countByExample(example);
	}
    public List<AppVersionCustom> selectCustomByExample(AppVersionExample example){
    	return appVersionCustomMapper.selectByExample(example);
    }
    public AppVersionCustom selectCustomByPrimaryKey(Integer id){
    	return appVersionCustomMapper.selectByPrimaryKey(id);
    }

	public List<String> getSprChnlsByAppVersion(int appVersion) {
		return appVersionCustomMapper.getSprChnlsByAppVersion(appVersion);
	}

	public void batchInsert(List<AppVersion> appVersions) {
		appVersionCustomMapper.batchInsert(appVersions);
	}
	
	/**
	 * 批量删除
	 * 
	 * @param staffId
	 * @param ids
	 */
	public void bathcDelete(Integer staffId, String ids) {
		AppVersion appVersion=new AppVersion();
		appVersion.setDelFlag("1");
		appVersion.setUpdateBy(staffId);
		appVersion.setUpdateDate(new Date());
		
		if (StringUtils.isEmpty(ids)) {
			return;
		}
		
		String[] idArr = ids.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i <idArr.length; i++) {
			idList.add(Integer.parseInt(idArr[i]));
		}
		AppVersionExample appVersionExample = new AppVersionExample();
		AppVersionExample.Criteria appCriteria = appVersionExample.createCriteria();
		appCriteria.andIdIn(idList);
		
		appVersionMapper.updateByExampleSelective(appVersion, appVersionExample);
	}
}
