package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopDecorateArea;
import com.jf.entity.ShopDecorateAreaExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecorateAreaMapper extends BaseDao<ShopDecorateArea, ShopDecorateAreaExample> {
    int countByExample(ShopDecorateAreaExample example);

    int deleteByExample(ShopDecorateAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDecorateArea record);

    int insertSelective(ShopDecorateArea record);

    List<ShopDecorateArea> selectByExample(ShopDecorateAreaExample example);

    ShopDecorateArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDecorateArea record, @Param("example") ShopDecorateAreaExample example);

    int updateByExample(@Param("record") ShopDecorateArea record, @Param("example") ShopDecorateAreaExample example);

    int updateByPrimaryKeySelective(ShopDecorateArea record);

    int updateByPrimaryKey(ShopDecorateArea record);
}
