package com.jf.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DateUtil;
import com.jf.dao.ActivityAreaMapper;
import com.jf.dao.ActivityMapper;
import com.jf.dao.CombineOrderMapper;
import com.jf.dao.MemberPvMapper;
import com.jf.dao.PlatformPvStatisticalCustomMapper;
import com.jf.dao.PlatformPvStatisticalMapper;
import com.jf.dao.ProductMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.CombineOrderExample;
import com.jf.entity.MemberPvExample;
import com.jf.entity.PlatformPvStatistical;
import com.jf.entity.PlatformPvStatisticalExample;
import com.jf.entity.Product;

@Service
@Transactional
public class PlatformPvStatisticalService extends BaseService<PlatformPvStatistical, PlatformPvStatisticalExample> {

	@Autowired
	private PlatformPvStatisticalMapper platformPvStatisticalMapper;
	
	@Autowired
	private PlatformPvStatisticalCustomMapper platformPvStatisticalCustomMapper;
	
	@Autowired
	private  ActivityMapper activityMapper;
	
	@Autowired
	private ActivityAreaMapper activityAreaMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CombineOrderMapper combineOrderMapper;
	
	@Autowired
	private MemberPvMapper memberPvMapper;
	
	@Autowired
	public void setPlatformPvStatisticalMapper(PlatformPvStatisticalMapper platformPvStatisticalMapper) {
		super.setDao(platformPvStatisticalMapper);
		this.platformPvStatisticalMapper = platformPvStatisticalMapper;
	}
	
	public Map<String, Object> getPlatformPvStatisticalSumUp(Map<String, Object> paramMap) {
		return platformPvStatisticalCustomMapper.getPlatformPvStatisticalSumUp(paramMap);
	}
	
	public List<Map<String, Object>> getPlatformPvStatisticalMSAList(Map<String, Object> paramMap) throws ParseException {
		List<Map<String, Object>> mapList = platformPvStatisticalCustomMapper.getPlatformPvStatisticalMSAList(paramMap);
		List<Map<String, Object>> payMemberCountList = platformPvStatisticalCustomMapper.getPlatformPvMemberIdCountList(paramMap);
		List<Map<String, Object>> memberPvCountList = platformPvStatisticalCustomMapper.getPlatformPvPvIdCountList(paramMap);
		for(Map<String, Object> map : mapList ) {
			String pageClassify = map.get("page_classify").toString();
			for(Map<String, Object> payMemberCountMap : payMemberCountList ) {
				if(pageClassify.equals(payMemberCountMap.get("page_classify").toString()) ) {
					map.put("pay_member_count", payMemberCountMap.get("count"));
				}
			}
			for(Map<String, Object> memberPvCountMap : memberPvCountList ) {
				if(pageClassify.equals(memberPvCountMap.get("page_classify").toString()) ) {
					map.put("member_pv_count", memberPvCountMap.get("count"));
				}
			}
		}
		return mapList;
	}
	
	public List<Map<String, Object>> getPlatformPvStatisticalUpstreamList(Map<String, Object> paramMap) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		listMap = platformPvStatisticalCustomMapper.getPlatformPvStatisticalUpstreamList(paramMap);
		paramMap.put("totalCount", "totalCount");
		List<Map<String, Object>> totalCountListMap = platformPvStatisticalCustomMapper.getPlatformPvStatisticalUpstreamList(paramMap);
		if(totalCountListMap != null && totalCountListMap.size() > 0 ) {
			Map<String, Object> map = totalCountListMap.get(0);
			for(Map<String, Object> m : listMap) {
				m.put("total_visitor_rate", "0".equals(map.get("total_visitor_count").toString())?"0.00%":new BigDecimal(m.get("total_visitor_count").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_visitor_count").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
				m.put("total_pv_rate", "0".equals(map.get("total_pv_count").toString())?"0.00%":new BigDecimal(m.get("total_pv_count").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_pv_count").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
				m.put("total_visitor_rate_tourist", "0".equals(map.get("total_visitor_count_tourist").toString())?"0.00%":new BigDecimal(m.get("total_visitor_count_tourist").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_visitor_count_tourist").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
				m.put("total_pv_rate_tourist", "0".equals(map.get("total_pv_count_tourist").toString())?"0.00%":new BigDecimal(m.get("total_pv_count_tourist").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_pv_count_tourist").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
			}
		}
		return listMap;
	}
	
	public List<Map<String, Object>> getPlatformPvStatisticalDownstreamList(Map<String, Object> paramMap) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		listMap = platformPvStatisticalCustomMapper.getPlatformPvStatisticalDownstreamList(paramMap);
		paramMap.put("totalCount", "totalCount");
		List<Map<String, Object>> totalCountListMap = platformPvStatisticalCustomMapper.getPlatformPvStatisticalDownstreamList(paramMap);
		if(totalCountListMap != null && totalCountListMap.size() > 0 ) {
			Map<String, Object> map = totalCountListMap.get(0);
			for(Map<String, Object> m : listMap) {
				m.put("total_visitor_rate", "0".equals(map.get("total_visitor_count").toString())?"0.00%":new BigDecimal(m.get("total_visitor_count").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_visitor_count").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
				m.put("total_pv_rate", "0".equals(map.get("total_pv_count").toString())?"0.00%":new BigDecimal(m.get("total_pv_count").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_pv_count").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
				m.put("total_visitor_rate_tourist", "0".equals(map.get("total_visitor_count_tourist").toString())?"0.00%":new BigDecimal(m.get("total_visitor_count_tourist").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_visitor_count_tourist").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
				m.put("total_pv_rate_tourist", "0".equals(map.get("total_pv_count_tourist").toString())?"0.00%":new BigDecimal(m.get("total_pv_count_tourist").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal(map.get("total_pv_count_tourist").toString()), 2, BigDecimal.ROUND_DOWN)+"%");
			}
		}
		return listMap;
	}
	
	public List<Map<String, Object>> getPlatformPvStatisticalDtlList(Map<String, Object> paramMap) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		listMap = platformPvStatisticalCustomMapper.getPlatformPvStatisticalDtlList(paramMap);
		if(paramMap.get("totalPvCount") != null && !"0".equals(paramMap.get("totalPvCount").toString()) ) {
			for(Map<String, Object> m : listMap) {
				m.put("total_pv_rate", (new BigDecimal(m.get("total_pv_count").toString()).add(new BigDecimal(m.get("total_pv_count_tourist").toString())))
						.multiply(new BigDecimal(100))
						.divide((new BigDecimal(paramMap.get("totalPvCount").toString()).add(new BigDecimal(paramMap.get("totalPvCountTourist").toString()))), 2, BigDecimal.ROUND_DOWN)+"%");
			}
		}
		if("9".equals(paramMap.get("pageClassify").toString()) ) { //特卖
			for(Map<String, Object> m : listMap) {
				if(m.get("value_id") != null ) {
					Integer valueId = Integer.parseInt(m.get("value_id").toString());
					Activity activity = activityMapper.selectByPrimaryKey(valueId);
					if(activity != null ) {
						m.put("page_type_desc", activity.getId()+"<br/>"+activity.getName());
					}
				}
			}
		}else if("10".equals(paramMap.get("pageClassify").toString()) ) { //商品
			for(Map<String, Object> m : listMap) {
				if(m.get("value_id") != null ) {
					Integer valueId = Integer.parseInt(m.get("value_id").toString());
					Product product = productMapper.selectByPrimaryKey(valueId);
					if(product != null) {
						m.put("page_type_desc", product.getCode()+"<br/>"+product.getName());
					}
				}
			}
		}else if("11".equals(paramMap.get("pageClassify").toString()) ) { //会场
			for(Map<String, Object> m : listMap) {
				if(m.get("value_id") != null ) {
					Integer valueId = Integer.parseInt(m.get("value_id").toString());
					ActivityArea activityArea = activityAreaMapper.selectByPrimaryKey(valueId);
					if(activityArea != null) {
						m.put("page_type_desc", activityArea.getId()+"<br/>"+activityArea.getName());
					}
				}
			}
		}
		return listMap;
	}
	
	public int getPlatformPvStatisticalDtlCount(Map<String, Object> paramMap) {
		return platformPvStatisticalCustomMapper.getPlatformPvStatisticalDtlCount(paramMap);
	}
	
	public Map<String, Object> getFlowProductPvMap(Map<String, Object> paramMap) {
		return platformPvStatisticalCustomMapper.getFlowProductPvMap(paramMap);
	}
    
    public Map<String, Object> getProductPvMap(Map<String, Object> paramMap) {
    	return platformPvStatisticalCustomMapper.getProductPvMap(paramMap);
    }
    
    public List<Map<String, Object>> getProductPvList(Map<String, Object> paramMap) {
    	return platformPvStatisticalCustomMapper.getProductPvList(paramMap);
    }
    
    public Integer getProductPvCount(Map<String, Object> paramMap) {
    	return platformPvStatisticalCustomMapper.getProductPvCount(paramMap);
    }

	public Integer getShoppingCartId(Integer orderDtlId) {
		return platformPvStatisticalCustomMapper.getShoppingCartId(orderDtlId);
	}

	public String getColumnType(Integer shoppingCartId) {
		return platformPvStatisticalCustomMapper.getColumnType(shoppingCartId);
	}
	
}
