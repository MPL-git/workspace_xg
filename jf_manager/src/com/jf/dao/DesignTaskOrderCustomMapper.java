package com.jf.dao;

import com.jf.entity.DesignTaskOrder;
import com.jf.entity.DesignTaskOrderCustom;
import com.jf.entity.DesignTaskOrderCustomExample;
import com.jf.entity.DesignTaskOrderExample;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface DesignTaskOrderCustomMapper {

    public int countByCustomExample(DesignTaskOrderExample example);

    public List<DesignTaskOrderCustom> selectByCustomExample(DesignTaskOrderExample example);

    public DesignTaskOrderCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") DesignTaskOrder record, @Param("example") DesignTaskOrderExample example);

    public DesignTaskOrderCustom totalPaymentByCustomExample(DesignTaskOrderExample example);
    
    
    public List<DesignTaskOrderCustom>designTaskOrderSelectByExample(DesignTaskOrderCustomExample example);

	public int designTaskOrderCountByExample(DesignTaskOrderCustomExample example);
	
	public List<Map<String, Object>> getDesignerList();

    List<DesignTaskOrderCustom> designRefundOrderStatistics(HashMap<String, Object> paramMap);

    List<DesignTaskOrderCustom> designReceivablesStatistics(HashMap<String, Object> paramMap);
}
