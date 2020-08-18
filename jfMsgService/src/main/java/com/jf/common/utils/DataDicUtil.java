package com.jf.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;
import com.jf.service.MemberRemindService;
import com.jf.service.SysParamCfgService;
import com.jf.service.SysStatusService;
@Component
@Lazy(false)
public class DataDicUtil {
	
	private static SysStatusService sysStatusService;
	
	private static MemberRemindService memberRemindService;
	
	private static SysParamCfgService sysParamCfgService;
	
	private static Map<String, SysStatus> sysStatusMap=new HashMap<String, SysStatus>();
	
	private static Map<String, SysStatus> sysStatusValueMap=new HashMap<String, SysStatus>();
	
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
	
	/**
	 * 获取渠道标识
	 * 方法描述 ：
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月24日 下午2:19:24 
	 * @version
	 * @param tableName
	 * @param colName
	 * @param statusDesc
	 * @return
	 */
	public static String getSprChnl(String statusDesc){
		String tableName = "BU_MEMBER_INFO";
		String colName = "SPR_CHNL";
		String sprChnl = "";
		statusDesc = statusDesc == null ? "9999" : statusDesc;
		if(statusDesc.equals("XG_xinggou")){
			sprChnl = "1001";
		}else if(statusDesc.equals("1002")){//ios
			sprChnl = "1002";
		}else{
			SysStatus sysStatus=sysStatusValueMap.get(tableName+"_"+colName+"_"+statusDesc);
			if(sysStatus==null){
				SysStatusExample sysStatusExample=new SysStatusExample();
				sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName).andStatusDescEqualTo(statusDesc);
				List<SysStatus> sysStatusLst=sysStatusService.selectByExample(sysStatusExample);
				if(sysStatusLst!=null&&sysStatusLst.size()>0){
					sysStatus=sysStatusLst.get(0);
					sprChnl = sysStatus.getStatusValue();
					sysStatusValueMap.put(tableName+"_"+colName+"_"+statusDesc, sysStatus);
				}else{
					sprChnl = "9999";
				}
			}else{
				sprChnl = sysStatus.getStatusValue();
			}
		}
		return sprChnl;
		
	}
	
	public static List<SysStatus> getStatusList(String tableName,String colName){
		SysStatusExample sysStatusExample=new SysStatusExample();
		sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName);
		sysStatusExample.setOrderByClause("status_order");
		List<SysStatus> sysStatusLst=sysStatusService.selectByExample(sysStatusExample);
		return sysStatusLst;
	}
	public static SysStatus getStatus(String tableName,String colName,String statusValue){
		SysStatusExample sysStatusExample=new SysStatusExample();
		sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName)
		.andStatusValueEqualTo(statusValue);
		sysStatusExample.setOrderByClause("status_order");
		List<SysStatus> sysStatusList=sysStatusService.selectByExample(sysStatusExample);
		if(CollectionUtils.isNotEmpty(sysStatusList)){
			return sysStatusList.get(0);
		}
		return null;
	}
	
	public static List<SysStatus> getStatusListByType(String tableName,String colName,String type){
		SysStatusExample sysStatusExample=new SysStatusExample();
		if(StringUtil.isBlank(type)){
			sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName);
		}else{
			sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName).andStatusValueLike("%"+type+"%");
		}
		sysStatusExample.setOrderByClause("status_order");
		List<SysStatus> sysStatusLst=sysStatusService.selectByExample(sysStatusExample);
		return sysStatusLst;
	}

	@Resource
	public synchronized void setSysStatusService(SysStatusService sysStatusService) {
		DataDicUtil.sysStatusService = sysStatusService;
		
	}
	
	@Resource
	public synchronized void setMemberRemindService(MemberRemindService memberRemindService) {
		DataDicUtil.memberRemindService = memberRemindService;
	}
	
	@Resource
	public synchronized void setSysParamCfgService(SysParamCfgService sysParamCfgService) {
		DataDicUtil.sysParamCfgService = sysParamCfgService;
	}
	
	
	//获取表中所有的状态
	public static List<SysStatus> getTableStatus(String tableName,String colName){
		List<SysStatus> sysStatusLst = null;
		SysStatusExample sysStatusExample=new SysStatusExample();
		sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName);
		sysStatusLst = sysStatusService.selectByExample(sysStatusExample);
		return sysStatusLst;
	}
	
	/**
	 * 
	 * 方法描述 ：判断开售提醒是否存在
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月11日 下午5:42:50 
	 * @version
	 * @param tableName
	 * @param colName
	 * @return
	 */
	public static boolean getRemindExists(Integer remindId,Integer memberId,String remindType){
		MemberRemindExample memberRemindExample = new MemberRemindExample();
		memberRemindExample.createCriteria().andRemindIdEqualTo(remindId).andMemberIdEqualTo(memberId)
		.andRemindTypeEqualTo(remindType);
		List<MemberRemind> memberReminds = memberRemindService.selectByExample(memberRemindExample);
		if(memberReminds != null && memberReminds.size() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 方法描述 ：获取配置信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月18日 上午11:24:13 
	 * @version
	 * @param code
	 * @return
	 */
	public static List<SysParamCfg> getSysParamCfg(String code){
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo(code);
		sysParamCfgExample.setOrderByClause("param_order");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		return sysParamCfgs;
	}
	
	/**
	 * 
	 * 方法描述 ：获取配置信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月18日 上午11:24:13 
	 * @version
	 * @param code
	 * @return
	 */
	public static SysParamCfg getSysParamCfgModel(String code,String paramValue){
		SysParamCfg sysParamCfg = null;
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		SysParamCfgExample.Criteria criteria = sysParamCfgExample.createCriteria();
		criteria.andParamCodeEqualTo(code);
		if(!StringUtil.isBlank(paramValue)){
			criteria.andParamValueEqualTo(paramValue);
		}
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			sysParamCfg = sysParamCfgs.get(0);
		}
		return sysParamCfg;
	}
	
}
