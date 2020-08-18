package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AppVersion;
import com.jf.entity.AppVersionCustom;
import com.jf.entity.AppVersionExample;
@Repository
public interface AppVersionCustomMapper{
    int countByExample(AppVersionExample example);
    List<AppVersionCustom> selectByExample(AppVersionExample example);
    AppVersionCustom selectByPrimaryKey(Integer id);
	List<String> getSprChnlsByAppVersion(int appVersion);
	void batchInsert(List<AppVersion> appVersions);
}