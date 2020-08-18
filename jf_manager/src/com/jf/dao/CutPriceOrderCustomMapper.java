package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderCustom;
import com.jf.entity.CutPriceOrderCustomExample;

@Repository
public interface CutPriceOrderCustomMapper {
    
	public int countByCustomExample(CutPriceOrderCustomExample example);

	public List<CutPriceOrderCustom> selectByCustomExample(CutPriceOrderCustomExample example);

	public CutPriceOrderCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") CutPriceOrder record, @Param("example") CutPriceOrderCustomExample example);

	/**
     * 
     * @Title cutPriceOrderStatisticsList   
     * @Description TODO(免费砍价拿数据统计)   
     * @author pengl
     * @date 2018年6月13日 上午11:08:31
     */
    public Map<String, Object> cutPriceOrderStatisticsList(Map<String, String> mapParam);
	
    /**
     * 
     * @Title updateCutPriceOrderList   
     * @Description TODO(批量审核修改---有限制条件)   
     * @author pengl
     * @date 2018年8月3日 下午6:00:47
     */
    public void updateCutPriceOrderListSelective(List<CutPriceOrder> cutPriceOrderList);
    
    /**
     * 
     * @Title cutPriceOrderAssistStatisticsList   
     * @Description TODO(助力减价数据分析)   
     * @author pengl
     * @date 2019年2月15日 上午10:36:18
     */
    public List<Map<String, Object>> cutPriceOrderAssistStatisticsList(Map<String, Object> mapParam);
    
    /**
     * 
     * @MethodName: cutPriceOrderNewStatisticsList
     * @Description: (邀请免费拿数据统计（STORY#1328优化）)
     * @author Pengl
     * @date 2019年4月22日 上午10:38:49
     */
    public Map<String, Object> cutPriceOrderNewStatisticsList(Map<String, String> mapParam);
    
    /**
     * 
     * @MethodName: getCutLinkClickLogMemberCount
     * @Description: (点击助力人数)
     * @author Pengl
     * @date 2019年6月11日 上午11:27:45
     */
    public Integer getCutLinkClickLogMemberCount(Map<String, Object> mapParam);
    
}