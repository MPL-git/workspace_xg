package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderCustom;
import com.jf.entity.SubDepositOrderCustomExample;

@Repository
public interface SubDepositOrderCustomMapper {
	public int countByCustomExample(SubDepositOrderCustomExample example);

    public List<SubDepositOrderCustom> selectByCustomExample(SubDepositOrderCustomExample example);

    public SubDepositOrderCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") SubDepositOrder record, @Param("example") SubDepositOrderCustomExample example);

    public List<SubDepositOrderCustom> getSubDepositOrderList(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title countGroupByCombineDepositOrder   
     * @Description TODO(根据总定单ID GroupBy 统计)   
     * @author pengl
     * @date 2018年11月15日 下午4:12:56
     */
    public List<SubDepositOrderCustom> selectGroupByCombineDepositOrder(SubDepositOrderCustomExample example);
    
}