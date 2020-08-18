package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopDecoratePage;
import com.jf.entity.ShopDecoratePageExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecoratePageMapper extends BaseDao<ShopDecoratePage, ShopDecoratePageExample> {
    int countByExample(ShopDecoratePageExample example);

    int deleteByExample(ShopDecoratePageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDecoratePage record);

    int insertSelective(ShopDecoratePage record);

    List<ShopDecoratePage> selectByExample(ShopDecoratePageExample example);

    ShopDecoratePage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDecoratePage record, @Param("example") ShopDecoratePageExample example);

    int updateByExample(@Param("record") ShopDecoratePage record, @Param("example") ShopDecoratePageExample example);

    int updateByPrimaryKeySelective(ShopDecoratePage record);

    int updateByPrimaryKey(ShopDecoratePage record);
}
