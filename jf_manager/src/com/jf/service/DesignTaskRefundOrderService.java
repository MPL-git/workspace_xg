package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jf.dao.DesignTaskRefundOrderCustomMapper;
import com.jf.dao.DesignTaskRefundOrderMapper;
import com.jf.entity.DesignTaskRefundOrder;
import com.jf.entity.DesignTaskRefundOrderCustom;
import com.jf.entity.DesignTaskRefundOrderCustomExample;
import com.jf.entity.DesignTaskRefundOrderExample;

@Service
@Transactional
public class DesignTaskRefundOrderService extends BaseService<DesignTaskRefundOrder,DesignTaskRefundOrderExample> {
	@Autowired
	private DesignTaskRefundOrderMapper designTaskRefundOrderMapper;
	@Autowired
	private DesignTaskRefundOrderCustomMapper designTaskRefundOrderCustomMapper;	
	@Autowired
	public void setDesignTaskRefundOrderMapper(DesignTaskRefundOrderMapper designTaskRefundOrderMapper) {
		super.setDao(designTaskRefundOrderMapper);
		this.designTaskRefundOrderMapper = designTaskRefundOrderMapper;
	}
	
	public int designTaskRefundOrderCountByExample(DesignTaskRefundOrderCustomExample example){
		return designTaskRefundOrderCustomMapper.designTaskRefundOrderCountByExample(example);
	}
    public List<DesignTaskRefundOrderCustom> designTaskRefundOrderSelectByExample(DesignTaskRefundOrderCustomExample example){
    	return designTaskRefundOrderCustomMapper.designTaskRefundOrderSelectByExample(example);
    }
    
}
