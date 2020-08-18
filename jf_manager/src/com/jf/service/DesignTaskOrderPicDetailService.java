package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jf.dao.DesignTaskOrderPicDetailCustomMapper;
import com.jf.dao.DesignTaskOrderPicDetailMapper;
import com.jf.entity.DesignTaskOrderPicDetail;
import com.jf.entity.DesignTaskOrderPicDetailCustom;
import com.jf.entity.DesignTaskOrderPicDetailCustomExample;
import com.jf.entity.DesignTaskOrderPicDetailExample;

@Service
@Transactional
public class DesignTaskOrderPicDetailService extends BaseService<DesignTaskOrderPicDetail,DesignTaskOrderPicDetailExample> {
	@Autowired
	private DesignTaskOrderPicDetailMapper designTaskOrderPicDetailMapper;
	@Autowired
	private DesignTaskOrderPicDetailCustomMapper designTaskOrderPicDetailCustomMapper;	
	@Autowired
	public void setDesignTaskOrderPicDetailMapper(DesignTaskOrderPicDetailMapper designTaskOrderPicDetailMapper) {
		super.setDao(designTaskOrderPicDetailMapper);
		this.designTaskOrderPicDetailMapper = designTaskOrderPicDetailMapper;
	}
	
	public int designTaskOrderPicDetailCountByExample(DesignTaskOrderPicDetailCustomExample example){
		return designTaskOrderPicDetailCustomMapper.designTaskOrderPicDetailCountByExample(example);
	}
    public List<DesignTaskOrderPicDetailCustom> designTaskOrderPicDetailSelectByExample(DesignTaskOrderPicDetailCustomExample example){
    	return designTaskOrderPicDetailCustomMapper.designTaskOrderPicDetailSelectByExample(example);
    }
    
}
