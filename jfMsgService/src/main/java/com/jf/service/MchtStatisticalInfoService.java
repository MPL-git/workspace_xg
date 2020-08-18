package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtStatisticalInfoCustomMapper;
import com.jf.dao.MchtStatisticalInfoMapper;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtStatisticalInfo;
import com.jf.entity.MchtStatisticalInfoExample;

@Service
@Transactional
public class MchtStatisticalInfoService extends BaseService<MchtStatisticalInfo,MchtStatisticalInfoExample> {
	
	
	private static final Log logger = LogFactory.getLog(MchtStatisticalInfoService.class);
	
	@Autowired
	private MchtStatisticalInfoMapper mchtStatisticalInfoMapper;
	
	@Autowired
	private MchtStatisticalInfoCustomMapper mchtStatisticalInfoCustomMapper;

	@Autowired
	public void setMchtStatisticalInfoMapper(MchtStatisticalInfoMapper mchtStatisticalInfoMapper) {
		super.setDao(mchtStatisticalInfoMapper);
		this.mchtStatisticalInfoMapper = mchtStatisticalInfoMapper;
	}
   
	public void addMchtStatisticalInfo(MchtInfoCustom mchtInfoCustom){
		 List<MchtStatisticalInfo> mchtStatisticalInfos=mchtStatisticalInfoCustomMapper.selectMchtIdByExample(mchtInfoCustom.getId());
		 if (mchtStatisticalInfos.size()<=0) {
			 MchtStatisticalInfo mchtStatisticalInfo=new MchtStatisticalInfo();
			 mchtStatisticalInfo.setMchtId(mchtInfoCustom.getId());
/*			 mchtStatisticalInfo.setActivityCount7Days(mchtInfoCustom.getActivityCount7Days());
			 mchtStatisticalInfo.setPayAmountSum7Days(mchtInfoCustom.getPayamountSum7Days());
			 mchtStatisticalInfo.setSubOrderCount7Days(mchtInfoCustom.getSuborderCount7Days());
			 mchtStatisticalInfo.setReturnRate7Days(mchtInfoCustom.getReturnRate7Days());
			 mchtStatisticalInfo.setAppealRate7Days(mchtInfoCustom.getAppealRate7Days());
			 mchtStatisticalInfo.setInterventionRate7Days(mchtInfoCustom.getInterventionRate7Days());
			 mchtStatisticalInfo.setActivityCount30Days(mchtInfoCustom.getActivityCount30Days());
			 mchtStatisticalInfo.setPayAmountSum30Days(mchtInfoCustom.getPayamountSum30Days());
			 mchtStatisticalInfo.setSubOrderCount30Days(mchtInfoCustom.getSuborderCount30Days());
			 mchtStatisticalInfo.setReturnRate30Days(mchtInfoCustom.getReturnRate30Days());
			 mchtStatisticalInfo.setAppealRate30Days(mchtInfoCustom.getAppealRate30Days());
			 mchtStatisticalInfo.setInterventionRate30Days(mchtInfoCustom.getInterventionRate30Days());
			 mchtStatisticalInfo.setActivityCount90Days(mchtInfoCustom.getActivityCount90Days());
			 mchtStatisticalInfo.setPayAmountSum90Days(mchtInfoCustom.getPayamountSum90Days());
			 mchtStatisticalInfo.setSubOrderCount90Days(mchtInfoCustom.getSuborderCount90Days());
			 mchtStatisticalInfo.setReturnRate90Days(mchtInfoCustom.getReturnRate90Days());
			 mchtStatisticalInfo.setAppealRate90Days(mchtInfoCustom.getAppealRate90Days());
			 mchtStatisticalInfo.setInterventionRate90Days(mchtInfoCustom.getInterventionRate90Days());
			 mchtStatisticalInfo.setProductScoreAvg(mchtInfoCustom.getProductScoreAvg());
			 mchtStatisticalInfo.setProductApplauseRate(mchtInfoCustom.getProductApplauseRate());*/
			 modifyMchtStatisticalInfo(mchtInfoCustom, mchtStatisticalInfo);
			 mchtStatisticalInfo.setDelFlag("0");
			 mchtStatisticalInfo.setCreateDate(new Date());
			 mchtStatisticalInfoMapper.insert(mchtStatisticalInfo);	
		}else {
			     MchtStatisticalInfo mchtStatisticalInfo=mchtStatisticalInfoCustomMapper.selectMchtIdKey(mchtInfoCustom.getId());
			     modifyMchtStatisticalInfo(mchtInfoCustom, mchtStatisticalInfo);
			     mchtStatisticalInfo.setUpdateDate(new Date());
			    mchtStatisticalInfoMapper.updateByPrimaryKey(mchtStatisticalInfo);
			
		}
		
	}
	
	public void modifyMchtStatisticalInfo(MchtInfoCustom mchtInfoCustom,MchtStatisticalInfo mchtStatisticalInfo){
	     mchtStatisticalInfo.setActivityCount7Days(mchtInfoCustom.getActivityCount7Days());
		 mchtStatisticalInfo.setPayAmountSum7Days(mchtInfoCustom.getPayamountSum7Days());
		 mchtStatisticalInfo.setSubOrderCount7Days(mchtInfoCustom.getSuborderCount7Days());
		 mchtStatisticalInfo.setReturnRate7Days(mchtInfoCustom.getReturnRate7Days());
		 mchtStatisticalInfo.setAppealRate7Days(mchtInfoCustom.getAppealRate7Days());
		 mchtStatisticalInfo.setInterventionRate7Days(mchtInfoCustom.getInterventionRate7Days());
		 mchtStatisticalInfo.setActivityCount30Days(mchtInfoCustom.getActivityCount30Days());
		 mchtStatisticalInfo.setPayAmountSum30Days(mchtInfoCustom.getPayamountSum30Days());
		 mchtStatisticalInfo.setSubOrderCount30Days(mchtInfoCustom.getSuborderCount30Days());
		 mchtStatisticalInfo.setReturnRate30Days(mchtInfoCustom.getReturnRate30Days());
		 mchtStatisticalInfo.setAppealRate30Days(mchtInfoCustom.getAppealRate30Days());
		 mchtStatisticalInfo.setInterventionRate30Days(mchtInfoCustom.getInterventionRate30Days());
		 mchtStatisticalInfo.setActivityCount90Days(mchtInfoCustom.getActivityCount90Days());
		 mchtStatisticalInfo.setPayAmountSum90Days(mchtInfoCustom.getPayamountSum90Days());
		 mchtStatisticalInfo.setSubOrderCount90Days(mchtInfoCustom.getSuborderCount90Days());
		 mchtStatisticalInfo.setReturnRate90Days(mchtInfoCustom.getReturnRate90Days());
		 mchtStatisticalInfo.setAppealRate90Days(mchtInfoCustom.getAppealRate90Days());
		 mchtStatisticalInfo.setInterventionRate90Days(mchtInfoCustom.getInterventionRate90Days());
		 mchtStatisticalInfo.setProductScoreAvg(mchtInfoCustom.getProductScoreAvg());
		 mchtStatisticalInfo.setProductApplauseRate(mchtInfoCustom.getProductApplauseRate());
	}
	
}
