package com.jf.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntegralDailyStatisticsCustomMapper;
import com.jf.dao.IntegralDailyStatisticsMapper;
import com.jf.entity.IntegralDailyStatistics;
import com.jf.entity.IntegralDailyStatisticsExample;

@Service
@Transactional
public class IntegralDailyStatisticsService extends BaseService<IntegralDailyStatistics,IntegralDailyStatisticsExample> {
	@Autowired
	private IntegralDailyStatisticsMapper integralDailyStatisticsMapper;
	
	@Autowired
	private IntegralDailyStatisticsCustomMapper integralDailyStatisticsCustomMapper;
	
	@Autowired
	public void setIntegralDailyStatisticsMapper(IntegralDailyStatisticsMapper integralDailyStatisticsMapper) {
		super.setDao(integralDailyStatisticsMapper);
		this.integralDailyStatisticsMapper = integralDailyStatisticsMapper;
	}
	
	public synchronized void dailyStatistic(String statisticDate){
		String statisticBeginDate = statisticDate+" 00:00:00";
		String statisticEndDate = statisticDate+" 23:59:59";
		//查询是否已经统计过
		IntegralDailyStatisticsExample integralDailyStatisticsExample=new IntegralDailyStatisticsExample();
		integralDailyStatisticsExample.createCriteria().andDelFlagEqualTo("0").andStatisticDateEqualTo(statisticDate);
		int count=integralDailyStatisticsMapper.countByExample(integralDailyStatisticsExample);
		if(count>0){//当天已经统计过
			return;
		}
		
		IntegralDailyStatistics integralDailyStatistics=new IntegralDailyStatistics();
		
		//查询起初余额
		Calendar calendar=Calendar.getInstance(); 
		calendar.set(Integer.valueOf(statisticDate.substring(0, 4)),Integer.valueOf(statisticDate.substring(5, 7))-1,Integer.valueOf(statisticDate.substring(8, 10)));
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		integralDailyStatisticsExample=new IntegralDailyStatisticsExample();
		integralDailyStatisticsExample.createCriteria().andDelFlagEqualTo("0").andStatisticDateEqualTo(sdf.format(calendar.getTime()));
		List<IntegralDailyStatistics> integralDailyStatisticsList=integralDailyStatisticsMapper.selectByExample(integralDailyStatisticsExample);
		if(integralDailyStatisticsList==null||integralDailyStatisticsList.size()==0){
			integralDailyStatistics.setBeginIntegral(0);
		}else{
			integralDailyStatistics.setBeginIntegral(integralDailyStatisticsList.get(0).getEndIntegral());
		}
		
		//统计积分超时失效(目前没有使用到，默认为0)
		integralDailyStatistics.setInvalidIntegral(0);
		
		//统计购物赠送
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 1);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType1(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//统计手机认证
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 2);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType2(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//统计完善资料
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 3);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType3(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//统计购物抵扣
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "2");
		paramMap.put("type", 4);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType4(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//统计积分兑换
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "2");
		paramMap.put("type", 5);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType5(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//统计系统赠送
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 6);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType6(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//统计抵扣返还
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 7);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType7(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//统计违规补偿
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 8);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType8(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//竞猜扣除
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "2");
		paramMap.put("type", 9);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType9(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//竞猜奖励
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 10);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType10(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		//统计总增加
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		int totalAdd=integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap);
		
		//统计总扣除
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "2");
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		int totalSub=integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap);
		
		//输入邀请码
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 17);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType17(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		
		//查看新星攻略
		paramMap = new HashMap<String, Object>();
		paramMap.put("tallyMode", "1");
		paramMap.put("type", 18);
		paramMap.put("statisticBeginDate", statisticBeginDate);
		paramMap.put("statisticEndDate", statisticEndDate);
		integralDailyStatistics.setIntegralType18(integralDailyStatisticsCustomMapper.statisticsIntegral(paramMap));
		
		
		
		
		
		
		/**                    。。。。。。。。。。。。PS:每增加一种类型都要修改以下代码。。。。。。。。。。。                                      **/
		//其他赠送=总增加-已列出增加总和
		integralDailyStatistics.setOtherGive(totalAdd - integralDailyStatistics.getIntegralType1() - integralDailyStatistics.getIntegralType2() - integralDailyStatistics.getIntegralType3() - integralDailyStatistics.getIntegralType6() - integralDailyStatistics.getIntegralType7() - integralDailyStatistics.getIntegralType8() - integralDailyStatistics.getIntegralType10()-integralDailyStatistics.getIntegralType17()-integralDailyStatistics.getIntegralType18());

		//其它消费=总增加-已列出扣除总和
		integralDailyStatistics.setOtherUse(totalSub-integralDailyStatistics.getIntegralType4()- integralDailyStatistics.getIntegralType5()-integralDailyStatistics.getInvalidIntegral() - integralDailyStatistics.getIntegralType9());
		
		
		
		
		
		
		//统计期末余额
		integralDailyStatistics.setEndIntegral(integralDailyStatistics.getBeginIntegral()+totalAdd-totalSub);
		
		integralDailyStatistics.setStatisticDate(statisticDate);
		integralDailyStatistics.setDelFlag("0");
		integralDailyStatistics.setCreateDate(new Date());
		insertSelective(integralDailyStatistics);
	}
}
