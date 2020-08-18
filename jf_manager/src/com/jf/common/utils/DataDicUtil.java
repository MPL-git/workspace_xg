package com.jf.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;
import com.jf.service.SysStatusService;
@Component
public class DataDicUtil {
	
	private static SysStatusService sysStatusService;
	private static Map<String, SysStatus> sysStatusMap=new HashMap<String, SysStatus>();
	
	public static String getStatusDesc(String tableName,String colName,String statusValue){
		SysStatus sysStatus=sysStatusMap.get(tableName+"_"+colName+"_"+statusValue);
		if(sysStatus==null){
			SysStatusExample sysStatusExample=new SysStatusExample();
			sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName).andStatusValueEqualTo(statusValue);
			List<SysStatus> sysStatusLst=sysStatusService.selectByExample(sysStatusExample);
			if(sysStatusLst!=null&&sysStatusLst.size()>0){
				sysStatus=sysStatusLst.get(0);
				sysStatusMap.put(tableName+"_"+colName+"_"+statusValue, sysStatus);
			}else{
				sysStatus=new SysStatus();
			}
		}
		return sysStatus.getStatusDesc();
		
	}
	
	public static List<SysStatus> getStatusList(String tableName,String colName){
		SysStatusExample sysStatusExample=new SysStatusExample();
		sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName);
		sysStatusExample.setOrderByClause("status_order");
		List<SysStatus> sysStatusLst=sysStatusService.selectByExample(sysStatusExample);
		return sysStatusLst;
	}

	@Resource
	public synchronized void setSysStatusService(SysStatusService sysStatusService) {
		DataDicUtil.sysStatusService = sysStatusService;
		
	}
	
	
	//获取表中所有的状态
	public static List<SysStatus> getTableStatus(String tableName,String colName){
		List<SysStatus> sysStatusLst = null;
		SysStatusExample sysStatusExample=new SysStatusExample();
		sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName);
		sysStatusLst = sysStatusService.selectByExample(sysStatusExample);
		return sysStatusLst;
	}
	
}
