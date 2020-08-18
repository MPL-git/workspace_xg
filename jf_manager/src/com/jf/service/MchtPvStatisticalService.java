package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gzs.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.MchtPvStatisticalCustomMapper;
import com.jf.entity.MchtPvStatistical;
import com.jf.entity.MchtPvStatisticalCustom;
import com.jf.entity.MchtPvStatisticalCustomExample;
import com.jf.entity.MchtPvStatisticalExample;

@Service
public class MchtPvStatisticalService extends BaseService<MchtPvStatistical, MchtPvStatisticalExample>{

	@Autowired
	private MchtPvStatisticalCustomMapper mchtPvStatisticalCustomMapper;
	
	//商家流量统计列表数量
	public Integer countCustomByExample(
			MchtPvStatisticalCustomExample mchtPvStatisticalCustomExample) {
		// TODO Auto-generated method stub
		return mchtPvStatisticalCustomMapper.countCustomByExample(mchtPvStatisticalCustomExample);
		
	}
	
	//商家流量统计列表
	public List<MchtPvStatisticalCustom> selectMchtPvStatisticalCustomByExample(
			MchtPvStatisticalCustomExample mchtPvStatisticalCustomExample) {
		// TODO Auto-generated method stub
		return mchtPvStatisticalCustomMapper.selectMchtPvStatisticalCustomByExample(mchtPvStatisticalCustomExample);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvStatisticalSumUp
	 * @Description: (商家历史总览)
	 * @author Pengl
	 * @date 2019年6月5日 下午7:28:58
	 */
	public Map<String, Object> getMchtPvStatisticalSumUp(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtPvStatisticalSumUp(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvTotalPayAmountRank
	 * @Description: (商家店铺支付金额排名)
	 * @author Pengl
	 * @date 2019年6月5日 下午7:44:42
	 */
	public Map<String, Object> getMchtPvPayAmountRank(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtPvPayAmountRank(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvTotalPayAmountAvg
	 * @Description: (前一百家店铺支付金额平均值 )
	 * @author Pengl
	 * @date 2019年6月5日 下午7:44:58
	 */
	public Map<String, Object> getMchtPvPayAmountAvg(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtPvPayAmountAvg(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvTotalVisitorCountRank
	 * @Description: (商家店铺访客数排名)
	 * @author Pengl
	 * @date 2019年6月5日 下午8:00:35
	 */
	public Map<String, Object> getMchtPvTotalVisitorCountRank(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtPvTotalVisitorCountRank(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvTotalVisitorCountAvg
	 * @Description: (前一百家店铺访客数平均值)
	 * @author Pengl
	 * @date 2019年6月5日 下午8:00:39
	 */
	public Map<String, Object> getMchtPvTotalVisitorCountAvg(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtPvTotalVisitorCountAvg(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvPayMemberCountRank
	 * @Description: (商家店铺支付买家数排名)
	 * @author Pengl
	 * @date 2019年6月5日 下午8:00:42
	 */
	public Map<String, Object> getMchtPvPayMemberCountRank(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtPvPayMemberCountRank(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvPayMemberCountAvg
	 * @Description: (前一百家店铺支付买家数平均值)
	 * @author Pengl
	 * @date 2019年6月5日 下午8:00:45
	 */
	public Map<String, Object> getMchtPvPayMemberCountAvg(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtPvPayMemberCountAvg(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getFlowMchtProductPvMap
	 * @Description: (商家商品流量相关)
	 * @author Pengl
	 * @date 2019年6月6日 上午9:11:37
	 */
	public Map<String, Object> getFlowMchtProductPvMap(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getFlowMchtProductPvMap(paramMap);
	}

	/**
	 * 
	 * @MethodName: getMchtProductPvMap
	 * @Description: (商家商品相关)
	 * @author Pengl
	 * @date 2019年6月6日 上午9:18:19
	 */
	public Map<String, Object> getMchtProductPvMap(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtProductPvMap(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtProductPvList
	 * @Description: (商品效果明细)
	 * @author Pengl
	 * @date 2019年6月6日 上午9:24:53
	 */
	public List<Map<String, Object>> getMchtProductPvList(Map<String, Object> paramMap) {
		List<Map<String, Object>> mchtProductPvList = mchtPvStatisticalCustomMapper.getMchtProductPvList(paramMap);
		return mchtProductPvList;
	}
	
	/**
	 * 
	 * @MethodName: getMchtProductPvCount
	 * @Description: (商品效果明细)
	 * @author Pengl
	 * @date 2019年6月6日 上午9:24:57
	 */
	public Integer getMchtProductPvCount(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtProductPvCount(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtVisitorMap
	 * @Description: (历史流量看板（访问店铺，转化）)
	 * @author Pengl
	 * @date 2019年6月6日 下午2:40:10
	 */
	public Map<String, Object> getMchtVisitorMap(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtVisitorMap(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtProductVisitorMap
	 * @Description: (历史流量看板（访问商品）)
	 * @author Pengl
	 * @date 2019年6月6日 下午2:40:29
	 */
	public Map<String, Object> getMchtProductVisitorMap(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtProductVisitorMap(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtVisitorPvMap
	 * @Description: (访问店铺)
	 * @author Pengl
	 * @date 2019年6月6日 下午5:17:36
	 */
	public Map<String, Object> getMchtVisitorPvMap(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtVisitorPvMap(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvProductVisitorMap
	 * @Description: (访问商品)
	 * @author Pengl
	 * @date 2019年6月6日 下午5:18:02
	 */
	public Map<String, Object> getMchtPvProductVisitorMap(Map<String, Object> paramMap) {
		Map<String, Object> map = mchtPvStatisticalCustomMapper.getMchtPvProductVisitorMap(paramMap);
		Map<String, Object> m = mchtPvStatisticalCustomMapper.getMchtMemberRemindCount(paramMap);
		map.put("member_remind_count_a", m.get("member_remind_count_a"));
		map.put("member_remind_count_b", m.get("member_remind_count_b"));
		map.put("member_remind_count_rate", m.get("member_remind_count_rate"));
		return map;
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvVisitorMap
	 * @Description: (转化)
	 * @author Pengl
	 * @date 2019年6月6日 下午5:18:30
	 */
	public Map<String, Object> getMchtPvVisitorMap(Map<String, Object> paramMap) {
		return mchtPvStatisticalCustomMapper.getMchtPvVisitorMap(paramMap);
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvStatisticalSourceList
	 * @Description: (来源)
	 * @author Pengl
	 * @date 2019年6月10日 上午11:22:45
	 */
	public List<Map<String, Object>> getMchtPvStatisticalSourceList(Map<String, Object> paramMap) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		listMap = mchtPvStatisticalCustomMapper.getMchtPvStatisticalSourceList(paramMap);
		paramMap.put("totalCount", "totalCount");
		List<Map<String, Object>> totalCountListMap = mchtPvStatisticalCustomMapper.getMchtPvStatisticalSourceList(paramMap);
		if(totalCountListMap != null && totalCountListMap.size() > 0 ) {
			Map<String, Object> map = totalCountListMap.get(0);
			for(Map<String, Object> m : listMap) {
				m.put("total_visitor_rate", "0".equals(map.get("total_visitor_count").toString())?"0.00":new BigDecimal(m.get("total_visitor_count").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_visitor_count").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
				m.put("total_pv_rate", "0".equals(map.get("total_pv_count").toString())?"0.00":new BigDecimal(m.get("total_pv_count").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_pv_count").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
			}
		}
		return listMap;
	}
	
	
	
	
}
