package com.jf.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtInfo;
@Repository
public interface MchtSettleCompareCustomMapper{
	List<MchtInfo> selectNoSettleCompareMcht(@Param("settleMonth") String settleMonth);
	List<HashMap<String, Object>> selectCrossMonthSettle(@Param("settleBeginDate") Date settleBeginDate, @Param("settleEndDate") Date settleEndDate);
	List<HashMap<String, Object>> selectCurrentMonthSettle(@Param("settleBeginDate") Date settleBeginDate, @Param("settleEndDate") Date settleEndDate);
	List<HashMap<String, Object>> selectCurrentMonthNeedSettle(@Param("settleBeginDate") Date settleBeginDate, @Param("settleEndDate") Date settleEndDate);
	List<HashMap<String, Object>> selectCurrentSettleOrderAmount(@Param("settleBeginDate") Date settleBeginDate, @Param("settleEndDate") Date settleEndDate);
	List<HashMap<String, Object>> selectCurrentRefundAmount(@Param("settleBeginDate") Date settleBeginDate, @Param("settleEndDate") Date settleEndDate);
}