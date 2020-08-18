package com.jf.dao;

import com.jf.entity.DesignTaskRefundOrderCustom;
import com.jf.entity.DesignTaskRefundOrderCustomExample;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DesignTaskRefundOrderCustomMapper{
	
	public List<DesignTaskRefundOrderCustom>designTaskRefundOrderSelectByExample(DesignTaskRefundOrderCustomExample example);

	public int designTaskRefundOrderCountByExample(DesignTaskRefundOrderCustomExample example);

	public List<DesignTaskRefundOrderCustom> selectRefByCustomExample(DesignTaskRefundOrderCustomExample example);

	public DesignTaskRefundOrderCustom totalRefundByCustomExample(DesignTaskRefundOrderCustomExample example);
}