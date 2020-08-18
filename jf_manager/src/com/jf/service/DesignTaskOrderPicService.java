package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DesignTaskOrderPicCustomMapper;
import com.jf.dao.DesignTaskOrderPicMapper;
import com.jf.entity.DesignTaskOrderPic;
import com.jf.entity.DesignTaskOrderPicCustom;
import com.jf.entity.DesignTaskOrderPicCustomExample;
import com.jf.entity.DesignTaskOrderPicExample;

@Service
@Transactional
public class DesignTaskOrderPicService extends BaseService<DesignTaskOrderPic,DesignTaskOrderPicExample> {
	@Autowired
	private DesignTaskOrderPicMapper designTaskOrderPicMapper;
	@Autowired
	private DesignTaskOrderPicCustomMapper designTaskOrderPicCustomMapper;	
	@Autowired
	public void setDesignTaskOrderPicMapper(DesignTaskOrderPicMapper designTaskOrderPicMapper) {
		super.setDao(designTaskOrderPicMapper);
		this.designTaskOrderPicMapper = designTaskOrderPicMapper;
	}
	
	public int designTaskOrderPicCountByExample(DesignTaskOrderPicCustomExample example){
		return designTaskOrderPicCustomMapper.designTaskOrderPicCountByExample(example);
	}
    public List<DesignTaskOrderPicCustom> designTaskOrderPicSelectByExample(DesignTaskOrderPicCustomExample example){
    	return designTaskOrderPicCustomMapper.designTaskOrderPicSelectByExample(example);
    }
    
}
