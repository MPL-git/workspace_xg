package com.jf.dao;

import com.jf.entity.ShopStory;
import com.jf.entity.ShopStoryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ShopStoryMapper  extends BaseDao <ShopStory,ShopStoryExample> {
    int countByExample(ShopStoryExample example);

    int deleteByExample(ShopStoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopStory record);

    int insertSelective(ShopStory record);

    List<ShopStory> selectByExampleWithBLOBs(ShopStoryExample example);

    List<ShopStory> selectByExample(ShopStoryExample example);

    ShopStory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopStory record, @Param("example") ShopStoryExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopStory record, @Param("example") ShopStoryExample example);

    int updateByExample(@Param("record") ShopStory record, @Param("example") ShopStoryExample example);

    int updateByPrimaryKeySelective(ShopStory record);

    int updateByPrimaryKeyWithBLOBs(ShopStory record);

    int updateByPrimaryKey(ShopStory record);
}