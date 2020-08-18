package com.jf.common.utils;

import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindExample;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;
import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;
import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfExample;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberRemindService;
import com.jf.service.SingleProductActivityService;
import com.jf.service.SysParamCfgService;
import com.jf.service.SysStatusService;
import com.jf.service.ViolateWordService;
import com.jf.service.WithdrawCnfService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
@Component
@Lazy(false)
public class DataDicUtil {
	
	private static SysStatusService sysStatusService;
	
	private static MemberRemindService memberRemindService;
	
	private static SysParamCfgService sysParamCfgService;
	
	private static MemberInfoService memberInfoService;
	
	private static WithdrawCnfService withdrawCnfService;
	
	private static SingleProductActivityService singleProductActivityService;
	
	private static ViolateWordService violateWordService;
	
	private static Map<String, SysStatus> sysStatusMap=new HashMap<String, SysStatus>();
	
	private static Map<String, List> memberInfoMap = new HashMap<String, List>();
	
	private static Map<String, Date> memberInfoDateMap = new HashMap<String, Date>();
	
	private static Map<String, List> memberCutMap = new HashMap<String, List>();
	
	private static Map<String, Date> memberCutDateMap = new HashMap<String, Date>();
	
	private static List<String> violateWordList = new ArrayList<String>();
	
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
	
	@Resource
	public synchronized void setMemberInfoService(MemberInfoService memberInfoService) {
		DataDicUtil.memberInfoService = memberInfoService;
	}
	
	@Resource
	public synchronized void setWithdrawCnfService(WithdrawCnfService withdrawCnfService) {
		DataDicUtil.withdrawCnfService = withdrawCnfService;
	}
	
	@Resource
	public synchronized void setSingleProductActivityService(SingleProductActivityService singleProductActivityService) {
		DataDicUtil.singleProductActivityService = singleProductActivityService;
	}
	
	@Resource
	public synchronized void setViolateWordService(ViolateWordService violateWordService) {
		DataDicUtil.violateWordService = violateWordService;
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
		.andRemindTypeEqualTo(remindType);;
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
	 * 签到轮播
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getMemberWithDrawInfo(){
		List<String> list = new ArrayList<String>();
		Date currentDate = new Date();
		Date date = memberInfoDateMap.get("date");
		if(date == null){
			list = getMemberSignInWithDrawInfo();
			memberInfoDateMap.put("date", currentDate);
			memberInfoMap.put("memberInfo", list);
		}else{
			if(currentDate.after(DateUtil.addMinute(date, 10))){
				list = getMemberSignInWithDrawInfo();
				memberInfoDateMap.put("date", currentDate);
				memberInfoMap.put("memberInfo", list);
			}else{
				list = memberInfoMap.get("memberInfo");
			}
		}
		return list;
	}

	private static List<String> getMemberSignInWithDrawInfo() {
		List<String> list = new ArrayList<String>();
		List<String> typeList = new ArrayList<String>();
		typeList.add("1");
		typeList.add("2");
		List<Integer> mIdList = new ArrayList<Integer>();;
		Random rand = new Random();
		for (int i = 0; i < 300; i++) {
			mIdList.add(rand.nextInt(100000)+1);
		}
		WithdrawCnfExample cnfExample = new WithdrawCnfExample();
		cnfExample.createCriteria().andIsEffectEqualTo("1").andDelFlagEqualTo("0");
		cnfExample.setLimitStart(0);
		cnfExample.setLimitSize(10);
		List<WithdrawCnf> cnfs = withdrawCnfService.selectByExample(cnfExample);
		MemberInfoExample memberInfoExample = new MemberInfoExample();
		memberInfoExample.createCriteria().andIdIn(mIdList).andTypeIn(typeList);
		List<MemberInfo> memberInfos = memberInfoService.selectByExample(memberInfoExample);
		if(CollectionUtils.isNotEmpty(memberInfos) && CollectionUtil.isNotEmpty(cnfs)){
			for (MemberInfo memberInfo : memberInfos) {
				String nk = "";
				String amount = "";
				String content = "";
				try {
					if(!StringUtil.isBlank(memberInfo.getNick())){
						nk = StringUtil.getNickStar(memberInfo.getNick(),null);
					}else if(!StringUtil.isBlank(memberInfo.getNick())){
						nk = StringUtil.getNickStar(null,memberInfo.getMobile());
					}else{
						continue;
					}
					WithdrawCnf cnf = cnfs.get(rand.nextInt(cnfs.size()));
					if(cnf.getWithdrawType().equals("1")){
						amount = cnf.getAmount()+"元直抵券";
					}else{
						amount = cnf.getAmount()+"元微信红包";
					}
					content = nk+"成功提现"+amount;
					list.add(content);
				} catch (Exception e) {
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * 砍价轮播
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getMemberCutSuccessInfo(){
		List<String> list = new ArrayList<String>();
		Date currentDate = new Date();
		Date date = memberCutDateMap.get("date");
		if(date == null){
			list = getMemberCutInfo();
			memberCutDateMap.put("date", currentDate);
			memberCutMap.put("memberInfo", list);
		}else{
			list = getMemberCutInfo();
			memberCutDateMap.put("date", currentDate);
			memberCutMap.put("memberInfo", list);
			if(currentDate.after(DateUtil.addMinute(date, 10))){
			}else{
				list = memberCutMap.get("memberInfo");
			}
		}
		return list;
	}

	private static List<String> getMemberCutInfo() {
		List<String> list = new ArrayList<String>();
		List<String> typeList = new ArrayList<String>();
		typeList.add("1");
		typeList.add("2");
		List<Integer> mIdList = new ArrayList<Integer>();;
		Random rand = new Random();
		for (int i = 0; i < 300; i++) {
			mIdList.add(rand.nextInt(1000)+1);
		}
		MemberInfoExample memberInfoExample = new MemberInfoExample();
		memberInfoExample.createCriteria().andIdIn(mIdList).andTypeIn(typeList);
		List<MemberInfo> memberInfos = memberInfoService.selectByExample(memberInfoExample);
		
		//获取砍价商品名称
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("currentPage", 0);
		paramsMap.put("pageSize", 500);
		paramsMap.put("type", "7");
		List<SingleProductActivityCustom> customs = singleProductActivityService.getSingleCutProductInfo(paramsMap);
		if(CollectionUtils.isNotEmpty(memberInfos) && CollectionUtil.isNotEmpty(customs)){
			for (MemberInfo memberInfo : memberInfos) {
				String nk = "";
				String content = "";
				String productName = "";
				try {
					if(!StringUtil.isBlank(memberInfo.getNick())){
						nk = StringUtil.getNickStar(memberInfo.getNick(),null);
					}else if(!StringUtil.isBlank(memberInfo.getNick())){
						nk = StringUtil.getNickStar(null,memberInfo.getMobile());
					}else{
						continue;
					}
					SingleProductActivityCustom custom = customs.get(rand.nextInt(customs.size()));
					productName = custom.getProductName();
					content = "用户"+nk+"免费领取了"+productName+"商品";
					list.add(content);
				} catch (Exception e) {
					continue;
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取库表的违禁词
	 * @return
	 */
	public static List<String> getViolateWordList(){
		if (CollectionUtils.isEmpty(violateWordList)) {
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
}
