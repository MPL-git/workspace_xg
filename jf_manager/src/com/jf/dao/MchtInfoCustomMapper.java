package com.jf.dao;

import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtInfoExample;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface MchtInfoCustomMapper {
	public List<?> selectMchtInfoList(Map<String,Object> map);
	public String queryMchtCount(HashMap<String, Object> paramMap);
	public MchtInfoCustom selectMchtInfoCustomById(Integer id);
	public List<Map<String, Object>> selectMchtInfoAndPicCustomById(HashMap<String, Object> paramMap);
	public int selectCountMchtInfoAndPicCustomById(HashMap<String, Object> paramMap);
	public List<MchtInfoCustom> selectMchtInfoAllList(Map<String, Object> map);
	public Integer selectMchtInfoListCount(Map<String, Object> map);

	List<MchtInfoCustom> selectByExample(MchtInfoCustomExample example);
	int countByExample(MchtInfoCustomExample example);
	List<MchtInfoCustom> mchtCoum(HashMap<String, Object> paramMap);//商家统计
	List<MchtInfoCustom> totalmchtCoum(HashMap<String, Object> paramMap);
	
	public List<MchtInfoCustom> totalmchtinfoCoum(MchtInfoCustomExample example);
	
	public List<Map<String, Object>> selectMchtInfoShopCustomList(Map<String, Object> paramMap);//店铺报表统计
	
	List<MchtInfoCustom> totalmchtInfoMonthlyCustom(HashMap<String, Object> paramMap);//每月商家漏斗统计
	List<MchtInfoCustom> mchtinfoMonthlyCoum(HashMap<String, Object> paramMap);//每月商家数数
	
	/**
	 * 
	 * @Title settlementAmountList   
	 * @Description TODO(结算金额情况)   
	 * @author pengl
	 * @date 2018年3月15日 下午2:57:50
	 */
	public List<Map<String, Object>> selectSettlementAmount(Map<String, Object> map); 
	
	public Integer selectSettlementAmountCount(Map<String, Object> map);
	
	/**
	 * 
	 * @Title selectMchtInfoAudit   
	 * @Description TODO(招商入驻进度)   
	 * @author pengl
	 * @date 2018年5月10日 上午9:17:32
	 */
	public List<Map<String, Object>> selectMchtInfoAudit(MchtInfoCustomExample example);
	
	public int countMchtInfoAudit(MchtInfoCustomExample example);
	
	public List<HashMap<String, Object>> selectMchtShopManagerList(MchtInfoCustomExample example); 
	
	public Integer countMchtShopManagerList(MchtInfoCustomExample example);
	public List<HashMap<String, Object>> getMchtInfoByMchtCode(String mchtCode);
	
	/**
	 * 
	 * @Title selectMchtInfoCustomExample   
	 * @Description TODO(评价数据--->商家信息)   
	 * @author pengl
	 * @date 2018年7月30日 下午5:54:18
	 */
	public List<Map<String, Object>> selectMchtInfoCustomExample(MchtInfoCustomExample example);
	
	/**
	 * 
	 * @Title selectMchtInfoCustomList   
	 * @Description TODO(店铺优化列表)   
	 * @author pengl
	 * @date 2018年10月18日 下午3:37:54
	 */
	public List<Map<String, Object>> selectMchtInfoCustomList(Map<String, Object> paramMap);
	public Integer selectMchtInfoCustomCount(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title selectExpressName   
	 * @Description TODO(该商家的100天以内订单，且有快递名称的，这些快递有哪些？调取数量多的前三个快递名称（多的在前面），快递名称之间由顿号隔开)   
	 * @author pengl
	 * @date 2018年10月18日 下午8:11:18
	 */
	public String selectExpressNames(Map<String, Object> paramMap);
	
	/**
	 * 通过商家序号查找
	 * 
	 * @param mchtCode
	 * @return
	 */
	public Map<String, Object> selectByMchtCode(String mchtCode);
	
	/**
	 * 通过商家id查找商家开通活动信息
	 * 
	 * @param mchtId
	 * @return
	 */
	public Map<String, Object> shopActivityInfo(Integer mchtId);
	
	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月22日 下午1:47:23
	 */
	public Integer countByCustomExample(
			MchtInfoCustomExample mchtInfoCustomExample);
	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月22日 下午1:47:25
	 */
	public List<MchtInfoCustom> selectByCustomExample(
			MchtInfoCustomExample mchtInfoCustomExample);

	/**
	 * @MethodName selectZSRenewalDetailByExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月1日 下午2:47:45
	 */
	public Map<String, Object> selectZSRenewalDetailByExample(
			MchtInfoExample mchtInfoExample);


	/**
	 * 获取商家流量报表栏目数据
	 */
    public  List<MchtInfoCustom> getShopFlowReportData(Map<String,Object> map);

	/**
	 * 根据传入搜索栏的参数查询对应的法务商品(菜单:商品管理->法务商品抽查)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectLegalSpotCheck(Map<String, Object> map);
}