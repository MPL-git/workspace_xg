package com.jf.dao;

import java.util.List;



import org.springframework.stereotype.Repository;

import com.jf.entity.SourceProductTypeBannerCustom;
import com.jf.entity.SourceProductTypeBannerExample;

@Repository
public interface SourceProductTypeBannerCustomMapper {
	
	public int countByCustomExample(SourceProductTypeBannerExample example);

	public List<SourceProductTypeBannerCustom> selectByCustomExample(SourceProductTypeBannerExample example);
}
