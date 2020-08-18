package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopStory;
import com.jf.entity.ShopStoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopStoryMapper extends BaseDao<ShopStory, ShopStoryExample> {
    int countByExample(ShopStoryExample example);

    int deleteByExample(ShopStoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopStory record);

    int insertSelective(ShopStory record);

    List<ShopStory> selectByExample(ShopStoryExample example);

    ShopStory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopStory record, @Param("example") ShopStoryExample example);

    int updateByExample(@Param("record") ShopStory record, @Param("example") ShopStoryExample example);

    int updateByPrimaryKeySelective(ShopStory record);

    int updateByPrimaryKey(ShopStory record);

    List<ShopStory> selectByExampleWithBLOBs(ShopStoryExample example);
    int updateByPrimaryKeyWithBLOBs(ShopStory record);
    int updateByExampleWithBLOBs(@Param("record") ShopStory record, @Param("example") ShopStoryExample example);
}
