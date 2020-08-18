package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductVideoMapper;
import com.jf.entity.ProductVideo;
import com.jf.entity.ProductVideoExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductVideoService extends BaseService<ProductVideo, ProductVideoExample> {
	@Autowired
	private ProductVideoMapper productVideoMapper;
	@Autowired
	public void setProductVideoMapper(ProductVideoMapper productVideoMapper) {
		this.setDao(productVideoMapper);
		this.productVideoMapper = productVideoMapper;
	}
	
	public List<Map<String, Object>> getProductVideos(Integer productId, String videoType) {
		List<Map<String, Object>> productVideos = new ArrayList<>();
		ProductVideoExample productVideoExample = new ProductVideoExample();
		productVideoExample.createCriteria().andProductIdEqualTo(productId).andVideoTypeEqualTo(videoType).andDelFlagEqualTo("0");
		List<ProductVideo> videos = selectByExample(productVideoExample);
		if(CollectionUtils.isNotEmpty(videos)){
            Map<String, Object> map;
			for (ProductVideo productVideo : videos) {
				//String videoUrl = StringUtil.getVideo(productVideo.getVideoUrl());
				//videoUrl = "rtmp:" + "121.196.208.202:1935" + "/vod/" + productVideo.getVideoUrl();
                map = new HashMap<>();
                map.put("videoUrl", StringUtil.getVideo(productVideo.getVideoUrl()));
                map.put("videoCover", StringUtil.getPic(productVideo.getVideoCover(), ""));
				productVideos.add(map);
				break;
			}
		}
		return productVideos;
	}
}
