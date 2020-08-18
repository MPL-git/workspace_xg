package com.jf.dao;

import com.jf.entity.VideoCommentCustom;
import com.jf.vo.request.PageRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
@Repository
public interface VideoCommentCustomMapper {

    List<VideoCommentCustom> findVideoComment(@Param("videoId") Integer videoId, @Param("pageRequest") PageRequest pageRequest);
}
