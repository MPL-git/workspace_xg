package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DesignTaskOrderCustomMapper;
import com.jf.dao.DesignTaskOrderMapper;
import com.jf.entity.DesignTaskOrder;
import com.jf.entity.DesignTaskOrderCustom;
import com.jf.entity.DesignTaskOrderCustomExample;
import com.jf.entity.DesignTaskOrderExample;

@Service
@Transactional
public class DesignTaskOrderService extends BaseService<DesignTaskOrder,DesignTaskOrderExample> {
	@Autowired
	private DesignTaskOrderMapper designTaskOrderMapper;
	@Autowired
	private DesignTaskOrderCustomMapper designTaskOrderCustomMapper;	
	@Autowired
	public void setDesignTaskOrderMapper(DesignTaskOrderMapper designTaskOrderMapper) {
		super.setDao(designTaskOrderMapper);
		this.designTaskOrderMapper = designTaskOrderMapper;
	}
	
	public int designTaskOrderCountByExample(DesignTaskOrderCustomExample example){
		return designTaskOrderCustomMapper.designTaskOrderCountByExample(example);
	}
    public List<DesignTaskOrderCustom> designTaskOrderSelectByExample(DesignTaskOrderCustomExample example){
    	return designTaskOrderCustomMapper.designTaskOrderSelectByExample(example);
    }
    
    public List<Map<String, Object>> getDesignerList(){//获取领取人
		return designTaskOrderCustomMapper.getDesignerList();
	}
    
}
