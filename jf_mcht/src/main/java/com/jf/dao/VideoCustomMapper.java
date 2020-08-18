package com.jf.dao;

import com.jf.entity.dto.VideoProductDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
@Repository
public interface VideoCustomMapper {

    List<VideoProductDTO> findVideoProduct(@Param("videoIds") List<Integer> videoIds);

    List<Integer> findTipOffVideoIds(@Param("videoIds") List<Integer> videoIds);
}
