package com.jf.dao;

import com.jf.entity.VideoCustom;
import com.jf.entity.dto.ProductLevelTypeDTO;
import com.jf.entity.dto.VideoProductDTO;
import com.jf.vo.request.PageRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
@Repository
public interface VideoCustomMapper {

    List<VideoCustom> findMyAttentionMchtVideo(@Param("memberId") Integer memberId, @Param("page") PageRequest page);

    List<VideoCustom> findRecommendVideo(@Param("pageRequest") PageRequest pageRequest);

    List<VideoCustom> findByProductType1(@Param("productType1Id") Integer productType1Id, @Param("page") PageRequest page);

    VideoCustom getDetail(Integer videoId);

    List<VideoProductDTO> findVideoProduct(@Param("videoIds") List<Integer> videoIds);

    ProductLevelTypeDTO getVideoProductLevelType(Integer videoId);

    List<VideoCustom> findByRelativeProductType(Map<String, Object> params);

    List<VideoCustom> findVideoCollect(@Param("memberId") Integer memberId, @Param("page") PageRequest page);

    List<VideoCustom> findUserVideoAttentionSituation(@Param("memberId") Integer memberId, @Param("videoIds") List<Integer> videoIds);
}
