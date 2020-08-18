package com.jf.dao;

import com.jf.entity.VideoAuthorizedAccessories;
import com.jf.entity.VideoAuthorizedAccessoriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoAuthorizedAccessoriesMapper extends BaseDao<VideoAuthorizedAccessories,VideoAuthorizedAccessoriesExample>{
    int countByExample(VideoAuthorizedAccessoriesExample example);

    int deleteByExample(VideoAuthorizedAccessoriesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoAuthorizedAccessories record);

    int insertSelective(VideoAuthorizedAccessories record);

    List<VideoAuthorizedAccessories> selectByExample(VideoAuthorizedAccessoriesExample example);

    VideoAuthorizedAccessories selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoAuthorizedAccessories record, @Param("example") VideoAuthorizedAccessoriesExample example);

    int updateByExample(@Param("record") VideoAuthorizedAccessories record, @Param("example") VideoAuthorizedAccessoriesExample example);

    int updateByPrimaryKeySelective(VideoAuthorizedAccessories record);

    int updateByPrimaryKey(VideoAuthorizedAccessories record);
}