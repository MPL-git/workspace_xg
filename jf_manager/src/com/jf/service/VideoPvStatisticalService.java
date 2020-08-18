package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoPvStatisticalCustomMapper;
import com.jf.dao.VideoPvStatisticalMapper;
import com.jf.entity.VideoPvStatistical;
import com.jf.entity.VideoPvStatisticalCustom;
import com.jf.entity.VideoPvStatisticalCustomExample;
import com.jf.entity.VideoPvStatisticalExample;

@Service
@Transactional
public class VideoPvStatisticalService extends BaseService<VideoPvStatistical,VideoPvStatisticalExample> {
	@Autowired
	private VideoPvStatisticalMapper videoPvStatisticalMapper;
	@Autowired 
	private VideoPvStatisticalCustomMapper videoPvStatisticalCustomMapper;
	
	@Autowired
	public void setVideoPvStatisticalMapper(VideoPvStatisticalMapper videoPvStatisticalMapper) {
		super.setDao(videoPvStatisticalMapper);
		this.videoPvStatisticalMapper = videoPvStatisticalMapper;
	}
	
	public int countByVideoPvStatisticalCustomExample(VideoPvStatisticalCustomExample example){
		return videoPvStatisticalCustomMapper.countByVideoPvStatisticalCustomExample(example);
	}
    public List<VideoPvStatisticalCustom> selectByVideoPvStatisticalCustomExample(VideoPvStatisticalCustomExample example){
    	return videoPvStatisticalCustomMapper.selectByVideoPvStatisticalCustomExample(example);
    }
    public VideoPvStatisticalCustom selectByVideoPvStatisticalCustomPrimaryKey(Integer id){
    	return videoPvStatisticalCustomMapper.selectByVideoPvStatisticalCustomPrimaryKey(id);
    }

}
