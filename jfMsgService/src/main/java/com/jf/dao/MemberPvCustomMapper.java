package com.jf.dao;

import com.jf.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberPvCustomMapper {
    public int countByCustomExample(MemberPvCustomExample example);

    public List<MemberPvCustom> selectByCustomExample(MemberPvCustomExample example);

    public MemberPvCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") MemberPv record, @Param("example") MemberPvCustomExample example);
    
    public List<Map<String, Object>> getMchtPvStatisticalList(Map<String, Object> paramMap);

    public List<Map<String, Object>> getPlatformPvStatisticalList(Map<String, Object> paramMap);

    public List<Map<String, Object>> getActivityPvStatisticalList(Map<String, Object> paramMap);

    public List<Map<String, Object>> getActivityAreaPvStatisticalList(Map<String, Object> paramMap);

    public List<Map<String, Object>> getAdPvStatisticalList(Map<String, Object> paramMap);

    public Map<String, Object> getBrandTeamTypeAd(Integer id);
    public Map<String, Object> getWetaoTeamTypeAd(Integer id);

    public Map<String, Object> getPlatformPvStatisticalOrder(Map<String, Object> paramMap);
    
    public String getHomePagePosition(Map<String, Object> paramMap);
    
    public String getClassifyPagePosition(Map<String, Object> paramMap);
    
    public Integer getHomePageExposureCount(Map<String, Object> paramMap);
    
    public Integer getClassifyPageExposureCount(Map<String, Object> paramMap);
    
    
    public void insertMemberPvList(List<MemberPv> memberPvList);
    public void insertMemberPvDtlList(List<MemberPvDtl> memberPvDtlList);
    public void insertMemberActionList(List<MemberAction> memberActionList);


    public List<Map<String, Object>> getColumnPvStatisticalList(Map<String, Object> paramMap);

    
    
    
    public void deleteMemberPvMiddle();
    public void deleteMemberPv();
    public void deleteMemberPvDtl();
    public void deleteMemberAction();
    public void deleteMchtPvStatistical();
    public void deletePlatformPvStatistical();
    public void deleteActivityPvStatistical();
    public void deleteActivityAreaPvStatistical();
    public void deleteAdPvStatistical();

    List<TypeColumnPvStatisticalCustom> getActivityPvStatisticalByColumn(Map<String, Object> paramMap);
}