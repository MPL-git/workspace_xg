package com.jf.service;

import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.AppLoginDistinctLogCustomMapper;
import com.jf.dao.AppLoginDistinctLogMapper;
import com.jf.entity.AppLoginDistinctLog;
import com.jf.entity.AppLoginDistinctLogCustom;
import com.jf.entity.AppLoginDistinctLogCustomExample;
import com.jf.entity.AppLoginDistinctLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Pengl
 * @create 2019-11-22 上午 9:14
 */
@Service
@Transactional
public class AppLoginDistinctLogService extends BaseService<AppLoginDistinctLog, AppLoginDistinctLogExample> {

	@Autowired
	private AppLoginDistinctLogMapper appLoginDistinctLogMapper;

	@Autowired
	private AppLoginDistinctLogCustomMapper appLoginDistinctLogCustomMapper;

	@Autowired
	private void setAppLoginDistinctLogMapper(AppLoginDistinctLogMapper appLoginDistinctLogMapper) {
		super.setDao(appLoginDistinctLogMapper);
		this.appLoginDistinctLogMapper = appLoginDistinctLogMapper;
	}

	public int countByCustomExample(AppLoginDistinctLogCustomExample example) {
		return appLoginDistinctLogCustomMapper.countByCustomExample(example);
	}

	public List<AppLoginDistinctLogCustom> selectByCustomExample(AppLoginDistinctLogCustomExample example) {
		return appLoginDistinctLogCustomMapper.selectByCustomExample(example);
	}

	public AppLoginDistinctLogCustom selectByCustomPrimaryKey(Integer id) {
		return appLoginDistinctLogCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") AppLoginDistinctLog record, @Param("example") AppLoginDistinctLogCustomExample example) {
		return appLoginDistinctLogCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public Map<String, Object> memberKeepReport(Map<String, Object> paramMap) {
		Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		List<String> dataXList = new ArrayList<>();
		List<List<String>> memberList = new ArrayList<>();
		List<List<String>> rateList = new ArrayList<>();
		String resultNum = paramMap.get("resultNum").toString();
		String keepStatus = paramMap.get("keepStatus").toString();
		Date distinctDate = DateUtil.getDateByFormat(paramMap.get("distinctDate").toString());
		if(!StringUtils.isEmpty(resultNum) && !StringUtils.isEmpty(keepStatus) ) {
			Date distinctNow = null;
			Set<Integer> baseMemberSet = null;
			Set<Integer> memberSet = null;
			int setCount = 0;
			if("day".equals(keepStatus) ) {
				Map<String, Object> baseMap = new HashMap<String, Object>();
				for (int i = 0; i <= Integer.parseInt(resultNum); i++) {
					distinctNow = DateUtil.getDateAfter(distinctDate, i);
					baseMap.put("distinct_date_param_"+i, DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					baseMap.put("distinct_date_"+i, DateUtil.formatDateByFormat(distinctNow, "MM-dd"));
					paramMap.put("distinctDate", DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					dataXList.add(DateUtil.formatDateByFormat(distinctNow, "MM-dd"));
					baseMemberSet = appLoginDistinctLogCustomMapper.selectMemberKeepReportList(paramMap);
					setCount = baseMemberSet.size();
					baseMap.put("base_count_"+i, setCount);
					baseMap.put("base_set_"+i, baseMemberSet);
				}
				for (int i = 0; i <= Integer.parseInt(resultNum); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					baseMemberSet = (Set<Integer>)baseMap.get("base_set_"+i);
					map.put("distinct_date_param", baseMap.get("distinct_date_param_"+i));
					map.put("distinct_date", baseMap.get("distinct_date_"+i));
					setCount = Integer.parseInt(baseMap.get("base_count_"+i).toString());
					map.put("base_count", setCount);
					if(setCount > 0 ) {
						for (int j = i+1; j <= Integer.parseInt(resultNum); j++) {
							memberSet = (Set<Integer>)baseMap.get("base_set_"+j);
							int count = 0;
							for(Integer memberId : memberSet ) {
								if(baseMemberSet.contains(memberId) ) {
									count++;
								}
							}
							map.put("wd_count_"+(j-i), count);
							map.put("wd_count_rate_"+(j-i), new BigDecimal(count).divide(new BigDecimal(setCount), 4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN).toString());
						}
					}else {
						for (int j = i+1; j <= Integer.parseInt(resultNum); j++) {
							map.put("wd_count_"+(j-i), 0);
							map.put("wd_count_rate_"+(j-i), "0.00");
						}
					}
					list.add(map);
				}
			}
			if("week".equals(keepStatus) ) {
				Map<String, Object> baseMap = new HashMap<String, Object>();
				for (int i = 0; i <= Integer.parseInt(resultNum); i++) {
					distinctNow = DateUtil.getDateAfter(distinctDate, i*7);
					baseMap.put("distinct_date_param_"+i, DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					baseMap.put("distinct_date_"+i, DateUtil.formatDateByFormat(distinctNow, "MM-dd")+"~"+DateUtil.formatDateByFormat(DateUtil.getDateAfter(distinctNow, 6), "MM-dd"));
					paramMap.put("distinctDate", DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					dataXList.add(DateUtil.formatDateByFormat(distinctNow, "MM-dd"));
					baseMemberSet = appLoginDistinctLogCustomMapper.selectMemberKeepReportList(paramMap);
					setCount = baseMemberSet.size();
					baseMap.put("base_count_"+i, setCount);
					baseMap.put("base_set_"+i, baseMemberSet);
				}
				for (int i = 0; i <= Integer.parseInt(resultNum); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					baseMemberSet = (Set<Integer>)baseMap.get("base_set_"+i);
					map.put("distinct_date_param", baseMap.get("distinct_date_param_"+i));
					map.put("distinct_date", baseMap.get("distinct_date_"+i));
					setCount = Integer.parseInt(baseMap.get("base_count_"+i).toString());
					map.put("base_count", setCount);
					if(setCount > 0 ) {
						for (int j = i+1; j <= Integer.parseInt(resultNum); j++) {
							memberSet = (Set<Integer>)baseMap.get("base_set_"+j);
							int count = 0;
							for(Integer memberId : memberSet ) {
								if(baseMemberSet.contains(memberId) ) {
									count++;
								}
							}
							map.put("wd_count_"+(j-i), count);
							map.put("wd_count_rate_"+(j-i), new BigDecimal(count).divide(new BigDecimal(setCount), 4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN).toString());
						}
					}else {
						for (int j = i+1; j <= Integer.parseInt(resultNum); j++) {
							map.put("wd_count_"+(j-i), 0);
							map.put("wd_count_rate_"+(j-i), "0.00");
						}
					}
					list.add(map);
				}
			}
			for (int i = 1; i <= Integer.parseInt(resultNum); i++) {
				List<String> members= new ArrayList<>();
				List<String> rates = new ArrayList<>();
				for(Map<String, Object> map : list ) {
					if(!map.containsKey("wd_count_"+i) ) {
						continue;
					}
					members.add(map.get("wd_count_"+i).toString());
					rates.add(map.get("wd_count_rate_"+i).toString());
				}
				memberList.add(members);
				rateList.add(rates);
			}
			returnMap.put("list", list);
			returnMap.put("dataXList", dataXList);
			returnMap.put("memberList", memberList);
			returnMap.put("rateList", rateList);
		}
		return returnMap;
	}

	public List<Map<String, Object>> selectMemberKeepList(Map<String, Object> paramMap) {
		return appLoginDistinctLogCustomMapper.selectMemberKeepList(paramMap);
	}

	public Integer selectMemberKeepCount(Map<String, Object> paramMap) {
		return appLoginDistinctLogCustomMapper.selectMemberKeepCount(paramMap);
	}

	public Map<String, Object> memberRepurchaseReport(Map<String, Object> paramMap) {
		Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		List<String> dataXList = new ArrayList<>();
		List<List<String>> memberList = new ArrayList<>();
		List<List<String>> rateList = new ArrayList<>();
		String resultNum = paramMap.get("resultNum").toString();
		String keepStatus = paramMap.get("keepStatus").toString();
		Date payDate = DateUtil.getDateByFormat(paramMap.get("payDate").toString());
		if(!StringUtils.isEmpty(resultNum) && !StringUtils.isEmpty(keepStatus) ) {
			Date distinctNow = null;
			Set<Integer> baseMemberSet = null;
			Set<Integer> memberSet = null;
			int setCount = 0;
			if("week".equals(keepStatus) ) {
				Map<String, Object> baseMap = new HashMap<String, Object>();
				for (int i = 0; i <= Integer.parseInt(resultNum); i++) {
					distinctNow = DateUtil.getDateAfter(payDate, i*7);
					baseMap.put("pay_date_param_"+i, DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					baseMap.put("pay_date_"+i, DateUtil.formatDateByFormat(distinctNow, "MM-dd")+"~"+DateUtil.formatDateByFormat(DateUtil.getDateAfter(distinctNow, 6), "MM-dd"));
					paramMap.put("payDate", DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					dataXList.add(DateUtil.formatDateByFormat(distinctNow, "MM-dd"));
					baseMemberSet = appLoginDistinctLogCustomMapper.selectMemberRepurchaseReportList(paramMap);
					setCount = baseMemberSet.size();
					baseMap.put("base_count_"+i, setCount);
					baseMap.put("base_set_"+i, baseMemberSet);
				}
				for (int i = 0; i <= Integer.parseInt(resultNum); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					baseMemberSet = (Set<Integer>)baseMap.get("base_set_"+i);
					map.put("pay_date_param", baseMap.get("pay_date_param_"+i));
					map.put("pay_date", baseMap.get("pay_date_"+i));
					setCount = Integer.parseInt(baseMap.get("base_count_"+i).toString());
					map.put("base_count", setCount);
					if(setCount > 0 ) {
						for (int j = i+1; j <= Integer.parseInt(resultNum); j++) {
							memberSet = (Set<Integer>)baseMap.get("base_set_"+j);
							int count = 0;
							for(Integer memberId : memberSet ) {
								if(baseMemberSet.contains(memberId) ) {
									count++;
								}
							}
							map.put("wm_count_"+(j-i), count);
							map.put("wm_count_rate_"+(j-i), new BigDecimal(count).divide(new BigDecimal(setCount), 4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN).toString());
						}
					}else {
						for (int j = i+1; j <= Integer.parseInt(resultNum); j++) {
							map.put("wm_count_"+(j-i), 0);
							map.put("wm_count_rate_"+(j-i), "0.00");
						}
					}
					list.add(map);
				}
			}
			if("month".equals(keepStatus) ) {
				Map<String, Object> baseMap = new HashMap<String, Object>();
				for (int i = 0; i <= Integer.parseInt(resultNum); i++) {
					distinctNow = DateUtil.getMonthsAgo(payDate, i);
					baseMap.put("pay_date_param_" + i, DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					baseMap.put("pay_date_" + i, DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd") + "~" + DateUtil.formatDateByFormat(DateUtil.getDateAfter(DateUtil.getMonthsAgo(distinctNow, 1), -1), "yyyy-MM-dd"));
					paramMap.put("payDate", DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					dataXList.add(DateUtil.formatDateByFormat(distinctNow, "yyyy-MM-dd"));
					baseMemberSet = appLoginDistinctLogCustomMapper.selectMemberRepurchaseReportList(paramMap);
					setCount = baseMemberSet.size();
					baseMap.put("base_count_" + i, setCount);
					baseMap.put("base_set_" + i, baseMemberSet);
				}
				for (int i = 0; i <= Integer.parseInt(resultNum); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					baseMemberSet = (Set<Integer>)baseMap.get("base_set_"+i);
					map.put("pay_date_param", baseMap.get("pay_date_param_"+i));
					map.put("pay_date", baseMap.get("pay_date_"+i));
					setCount = Integer.parseInt(baseMap.get("base_count_"+i).toString());
					map.put("base_count", setCount);
					if(setCount > 0 ) {
						for (int j = i+1; j <= Integer.parseInt(resultNum); j++) {
							memberSet = (Set<Integer>)baseMap.get("base_set_"+j);
							int count = 0;
							for(Integer memberId : memberSet ) {
								if(baseMemberSet.contains(memberId) ) {
									count++;
								}
							}
							map.put("wm_count_"+(j-i), count);
							map.put("wm_count_rate_"+(j-i), new BigDecimal(count).divide(new BigDecimal(setCount), 4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN).toString());
						}
					}else {
						for (int j = i+1; j <= Integer.parseInt(resultNum); j++) {
							map.put("wm_count_"+(j-i), 0);
							map.put("wm_count_rate_"+(j-i), "0.00");
						}
					}
					list.add(map);
				}
			}
			for (int i = 1; i <= Integer.parseInt(resultNum); i++) {
				List<String> members= new ArrayList<>();
				List<String> rates = new ArrayList<>();
				for(Map<String, Object> map : list ) {
					if(!map.containsKey("wm_count_"+i) ) {
						continue;
					}
					members.add(map.get("wm_count_"+i).toString());
					rates.add(map.get("wm_count_rate_"+i).toString());
				}
				memberList.add(members);
				rateList.add(rates);
			}
			returnMap.put("list", list);
			returnMap.put("dataXList", dataXList);
			returnMap.put("memberList", memberList);
			returnMap.put("rateList", rateList);
		}
		return returnMap;
	}

	public List<Map<String, Object>> selectMemberRepurchaseList(Map<String, Object> paramMap) {
		return appLoginDistinctLogCustomMapper.selectMemberRepurchaseList(paramMap);
	}

	public Integer selectMemberRepurchaseCount(Map<String, Object> paramMap) {
		return appLoginDistinctLogCustomMapper.selectMemberRepurchaseCount(paramMap);
	}


}
