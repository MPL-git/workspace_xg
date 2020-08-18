package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





import com.jf.dao.SourceProductTypeBannerCustomMapper;
import com.jf.dao.SourceProductTypeBannerMapper;
import com.jf.entity.SourceProductTypeBanner;
import com.jf.entity.SourceProductTypeBannerCustom;
import com.jf.entity.SourceProductTypeBannerExample;

@Service
@Transactional
public class SourceProductTypeBannerService extends BaseService<SourceProductTypeBanner, SourceProductTypeBannerExample> {

	@Autowired
	private SourceProductTypeBannerMapper sourceProductTypeBannerMapper;
	

	@Autowired
	private SourceProductTypeBannerCustomMapper sourceProductTypeBannerCustomMapper;
	
	
	@Autowired
	public void setSourceProductTypeBanneMapper(SourceProductTypeBannerMapper sourceProductTypeBannerMapper) {
		super.setDao(sourceProductTypeBannerMapper);
		this.sourceProductTypeBannerMapper = sourceProductTypeBannerMapper;
	}
	
	
	public int countByCustomExample(SourceProductTypeBannerExample example){
		return sourceProductTypeBannerCustomMapper.countByCustomExample(example);
	}

	public List<SourceProductTypeBannerCustom> selectByCustomExample(SourceProductTypeBannerExample example){
		return 	sourceProductTypeBannerCustomMapper.selectByCustomExample(example);
	}
	
}
