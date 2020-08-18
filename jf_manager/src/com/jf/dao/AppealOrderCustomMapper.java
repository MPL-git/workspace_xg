package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.AppealOrderCustom;
import com.jf.entity.AppealOrderCustomExample;
@Repository
public interface AppealOrderCustomMapper{
	public List<AppealOrderCustom>selectByExample(AppealOrderCustomExample example);

	public int countByExample(AppealOrderCustomExample example);
	
	/**
	 * 
	 * @Title getPlatformProcessorList   
	 * @Description TODO(获取90天内的处理人)   
	 * @author pengl
	 * @date 2018年3月30日 下午2:20:30
	 */
	public List<Map<String, Object>> getPlatformProcessorList();

}