package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ToutiaoAdvertiserInfo;

@Repository
public interface ToutiaoAdvertiserInfoCustomMapper {

	/**
	 * 
	 * @Title updateByPrimaryKeySelectiveList   
	 * @Description TODO(批量修改广告主)   
	 * @author pengl
	 * @date 2018年8月20日 下午3:24:03
	 */
	public void updateByPrimaryKeySelectiveList(List<ToutiaoAdvertiserInfo> toutiaoAdvertiserInfos);
	
}
