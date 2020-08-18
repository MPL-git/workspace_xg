package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.InterventionOrderMapper;
import com.jf.dao.InterventionOrderStatusLogMapper;
import com.jf.dao.InterventionProcessCustomMapper;
import com.jf.dao.InterventionProcessMapper;
import com.jf.dao.InterventionProcessPicMapper;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderStatusLog;
import com.jf.entity.InterventionProcess;
import com.jf.entity.InterventionProcessCustom;
import com.jf.entity.InterventionProcessExample;
import com.jf.entity.InterventionProcessPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:24:39 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class InterventionProcessService extends BaseService<InterventionProcess, InterventionProcessExample> {
	
	@Autowired
	private InterventionProcessMapper interventionProcessMapper;
	
	@Autowired
	private InterventionProcessCustomMapper interventionProcessCustomMapper;
	
	@Autowired
	private InterventionOrderMapper interventionOrderMapper;
	
	@Autowired
	private InterventionProcessPicMapper interventionProcessPicMapper;
	
	@Autowired
	private InterventionOrderStatusLogMapper interventionOrderStatusLogMapper;

	@Autowired
	public void setInterventionProcessMapper(InterventionProcessMapper interventionProcessMapper) {
		this.setDao(interventionProcessMapper);
		this.interventionProcessMapper = interventionProcessMapper;
	}

	public List<InterventionProcessCustom> selectInterventionProcessCustomByExample(InterventionProcessExample example) {
		return interventionProcessCustomMapper.selectByExample(example);
	}

	public void save(InterventionOrder interventionOrder,InterventionProcess interventionProcess,
			List<InterventionProcessPic> interventionProcessPics,InterventionOrderStatusLog iosl) {
		interventionOrderMapper.updateByPrimaryKey(interventionOrder);
		this.insertSelective(interventionProcess);
		for(InterventionProcessPic interventionProcessPic:interventionProcessPics){
			interventionProcessPic.setInterventionProcessId(interventionProcess.getId());
			interventionProcessPicMapper.insertSelective(interventionProcessPic);
		}
		if(iosl!=null){
			interventionOrderStatusLogMapper.insertSelective(iosl);
		}
	}
	
}
