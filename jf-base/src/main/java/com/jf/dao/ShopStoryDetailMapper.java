package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopStoryDetail;
import com.jf.entity.ShopStoryDetailExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopStoryDetailMapper extends BaseDao<ShopStoryDetail, ShopStoryDetailExample> {
    int countByExample(ShopStoryDetailExample example);

    int deleteByExample(ShopStoryDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopStoryDetail record);

    int insertSelective(ShopStoryDetail record);

    List<ShopStoryDetail> selectByExample(ShopStoryDetailExample example);

    ShopStoryDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopStoryDetail record, @Param("example") ShopStoryDetailExample example);

    int updateByExample(@Param("record") ShopStoryDetail record, @Param("example") ShopStoryDetailExample example);

    int updateByPrimaryKeySelective(ShopStoryDetail record);

    int updateByPrimaryKey(ShopStoryDetail record);
}
