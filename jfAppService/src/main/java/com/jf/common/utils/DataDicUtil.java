package com.jf.common.utils;

import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;
import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;
import com.jf.service.MemberRemindService;
import com.jf.service.ProductService;
import com.jf.service.SysParamCfgService;
import com.jf.service.SysStatusService;
import com.jf.service.ViolateWordService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@Lazy(false)
public class DataDicUtil {
	
	private static SysStatusService sysStatusService;
	
	private static MemberRemindService memberRemindService;
	
	private static SysParamCfgService sysParamCfgService;
	
	private static ViolateWordService violateWordService;
	
	private static ProductService productService;
	
	private static Map<String, SysStatus> sysStatusMap=new HashMap<String, SysStatus>();
	
	private static Map<String, SysStatus> sysStatusValueMap=new HashMap<String, SysStatus>();
	
	private static List<String> violateWordList = new ArrayList<String>();
	
	private static Map<String, Map<String, Object>> productMap = new HashMap<>();
	
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
		if(CollectionUtil.isNotEmpty(sysStatusList)){
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
	
	@Resource
	public synchronized void setViolateWordService(ViolateWordService violateWordService) {
		DataDicUtil.violateWordService = violateWordService;
	}
	
	@Resource
	public synchronized void setProductService(ProductService productService) {
		DataDicUtil.productService = productService;
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
		if(CollectionUtil.isNotEmpty(sysParamCfgs)){
			sysParamCfg = sysParamCfgs.get(0);
		}
		return sysParamCfg;
	}
	
	/**
	 * 获取库表的违禁词
	 * @return
	 */
	public static List<String> getViolateWordList(){
		if (CollectionUtils.isEmpty(violateWordList)) {
			//2标识用户端
			ViolateWordExample violateWordExample = new ViolateWordExample();
			violateWordExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2").andWordIsNotNull();
			violateWordExample.setOrderByClause("LENGTH(word) desc");
			List<ViolateWord> words = violateWordService.selectByExample(violateWordExample);
			if(CollectionUtils.isNotEmpty(words)){
				for (ViolateWord violateWord : words) {
					violateWordList.add(violateWord.getWord());
				}
			}
		}
		return violateWordList;
	}
	
	/**
	 * 每日获取会员折扣力度大的，好评的前10个商品
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getSvipProductList(){
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Integer> productIdList = new ArrayList<>();
		Map<String, Object> map = productMap.get("product");
		Date currentDate = new Date();
		String currentDateStr = DateUtil.getFormatDate(currentDate, "yyyyMMdd");
		if (map == null || map.isEmpty()) {
			map = new HashMap<>();
			Map<String, Object> productListMap = productService.getSvipProductList(productIdList);
			dataList = (List<Map<String, Object>>) productListMap.get("dataList");
			productIdList = (List<Integer>) productListMap.get("productIdList");
			map.put("date", currentDateStr);
			map.put("productIdList", productIdList);
			productMap.put("product", map);
		}else{
			String date = map.get("date").toString();
			if(date.equals(currentDateStr)){
				productIdList = (List<Integer>) map.get("productIdList");
				Map<String, Object> productListMap = productService.getSvipProductList(productIdList);
				dataList = (List<Map<String, Object>>) productListMap.get("dataList");
			}else{
				map = new HashMap<>();
				Map<String, Object> productListMap = productService.getSvipProductList(productIdList);
				dataList = (List<Map<String, Object>>) productListMap.get("dataList");
				productIdList = (List<Integer>) productListMap.get("productIdList");
				map.put("date", currentDateStr);
				map.put("productIdList", productIdList);
				productMap.put("product", map);
			}
		}
		return dataList;
	}
}
