package com.jf.service;

import java.math.BigDecimal;
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

import com.jf.dao.MchtSettleCompareCustomMapper;
import com.jf.dao.MchtSettleCompareMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtSettleCompare;
import com.jf.entity.MchtSettleCompareExample;


@Service
@Transactional
public class MchtSettleCompareService extends BaseService<MchtSettleCompare, MchtSettleCompareExample> {
	
	@Autowired
	private MchtSettleCompareMapper mchtSettleCompareMapper;
	
	@Autowired
	private MchtSettleCompareCustomMapper mchtSettleCompareCustomMapper;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	public void setMchtSettleCompareMapper(MchtSettleCompareMapper mchtSettleCompareMapper) {
		this.setDao(mchtSettleCompareMapper);
		this.mchtSettleCompareMapper = mchtSettleCompareMapper;
	}
	
	public void generateMchtSettleCompare(Date beginDate,Date endDate){

		if(beginDate==null||beginDate==null){
			return;
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String settleMonth=sdf.format(beginDate);
		
	
		List<MchtInfo> mchtInfos=mchtSettleCompareCustomMapper.selectNoSettleCompareMcht(settleMonth);
		
		if(mchtInfos==null||mchtInfos.size()==0){
			return;
		}
		
		Map<Integer,MchtSettleCompare> mchtSettleCompareMap=new HashMap<Integer,MchtSettleCompare>();
		Date now=new Date();
		for(MchtInfo mchtInfo:mchtInfos){
			MchtSettleCompare mchtSettleCompare=new MchtSettleCompare();
			mchtSettleCompare.setCreateDate(now);
			mchtSettleCompare.setDelFlag("0");
			mchtSettleCompare.setMchtId(mchtInfo.getId());
			mchtSettleCompare.setSettleMonth(settleMonth);
			mchtSettleCompare.setBeginNotSettle(new BigDecimal(0));
			mchtSettleCompare.setCrossMonthSettle(new BigDecimal(0));
			mchtSettleCompare.setCurrentMonthNeedSettle(new BigDecimal(0));
			mchtSettleCompare.setCurrentMonthNotSettle(new BigDecimal(0));
			mchtSettleCompare.setCurrentMonthSettle(new BigDecimal(0));
			mchtSettleCompare.setCurrentRefundAmount(new BigDecimal(0));
			mchtSettleCompare.setCurrentSettleOrderAmount(new BigDecimal(0));
			mchtSettleCompare.setEndNotSettle(new BigDecimal(0));
			mchtSettleCompareMap.put(mchtInfo.getId(), mchtSettleCompare);
		}
		List<HashMap<String, Object>> crossMonthSettleList=mchtSettleCompareCustomMapper.selectCrossMonthSettle(beginDate, endDate);
		for(HashMap<String, Object> crossMonthSettle:crossMonthSettleList){
			Integer mchtId=(Integer)crossMonthSettle.get("mcht_id");
			MchtSettleCompare mchtSettleCompare=mchtSettleCompareMap.get(mchtId);
			if(mchtSettleCompare!=null){
				mchtSettleCompare.setCrossMonthSettle((BigDecimal)crossMonthSettle.get("settle_amount"));
			}
		}
		List<HashMap<String, Object>> currentMonthSettleList=mchtSettleCompareCustomMapper.selectCurrentMonthSettle(beginDate, endDate);
		for(HashMap<String, Object> currentMonthSettle:currentMonthSettleList){
			Integer mchtId=(Integer)currentMonthSettle.get("mcht_id");
			MchtSettleCompare mchtSettleCompare=mchtSettleCompareMap.get(mchtId);
			if(mchtSettleCompare!=null){
				mchtSettleCompare.setCurrentMonthSettle((BigDecimal)currentMonthSettle.get("settle_amount"));
			}
		}
		List<HashMap<String, Object>> currentMonthNeedSettleList=mchtSettleCompareCustomMapper.selectCurrentMonthNeedSettle(beginDate, endDate);
		for(HashMap<String, Object> currentMonthNeedSettle:currentMonthNeedSettleList){
			Integer mchtId=(Integer)currentMonthNeedSettle.get("mcht_id");
			MchtSettleCompare mchtSettleCompare=mchtSettleCompareMap.get(mchtId);
			if(mchtSettleCompare!=null){
				mchtSettleCompare.setCurrentMonthNeedSettle((BigDecimal)currentMonthNeedSettle.get("settle_amount"));
			}
		}
		List<HashMap<String, Object>> currentSettleOrderAmountList=mchtSettleCompareCustomMapper.selectCurrentSettleOrderAmount(beginDate, endDate);
		for(HashMap<String, Object> currentSettleOrderAmount:currentSettleOrderAmountList){
			Integer mchtId=(Integer)currentSettleOrderAmount.get("mcht_id");
			MchtSettleCompare mchtSettleCompare=mchtSettleCompareMap.get(mchtId);
			if(mchtSettleCompare!=null){
				mchtSettleCompare.setCurrentSettleOrderAmount((BigDecimal)currentSettleOrderAmount.get("settle_amount"));
			}
		}
		List<HashMap<String, Object>> currentRefundAmountList=mchtSettleCompareCustomMapper.selectCurrentRefundAmount(beginDate, endDate);
		for(HashMap<String, Object> currentRefundAmount:currentRefundAmountList){
			Integer mchtId=(Integer)currentRefundAmount.get("mcht_id");
			MchtSettleCompare mchtSettleCompare=mchtSettleCompareMap.get(mchtId);
			if(mchtSettleCompare!=null){
				mchtSettleCompare.setCurrentRefundAmount((BigDecimal)currentRefundAmount.get("amount"));
			}
		}
		
		 Calendar calendar = Calendar.getInstance(); 
		 calendar.setTime(beginDate);
	     calendar.add(Calendar.MONTH, -1);
	     String lastMonth=sdf.format(calendar.getTime());
		
		for(Integer mchtId:mchtSettleCompareMap.keySet()){
			MchtSettleCompare mchtSettleCompare=mchtSettleCompareMap.get(mchtId);
			
			//查询上个结算月的月末未结算
			MchtSettleCompareExample mchtSettleCompareExample=new MchtSettleCompareExample();
			mchtSettleCompareExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andSettleMonthEqualTo(lastMonth);
			List<MchtSettleCompare> lastMchtSettleCompare=mchtSettleCompareMapper.selectByExample(mchtSettleCompareExample);
			if(lastMchtSettleCompare!=null&&lastMchtSettleCompare.size()>0){
				mchtSettleCompare.setBeginNotSettle(lastMchtSettleCompare.get(0).getEndNotSettle());
			}else{
				mchtSettleCompare.setBeginNotSettle(new BigDecimal(0));
			}
			mchtSettleCompare.setCurrentMonthNotSettle(mchtSettleCompare.getCurrentMonthNeedSettle().subtract(mchtSettleCompare.getCurrentMonthSettle()));
			mchtSettleCompare.setEndNotSettle(mchtSettleCompare.getBeginNotSettle().subtract(mchtSettleCompare.getCrossMonthSettle()).add(mchtSettleCompare.getCurrentMonthNotSettle()));
			mchtSettleCompareMapper.insertSelective(mchtSettleCompare);
		}
		
	}

}
