package com.jf.dao;

import com.jf.entity.ShopownerOrder;
import com.jf.entity.ShopownerOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ShopownerOrderMapper extends BaseDao<ShopownerOrder, ShopownerOrderExample> {
    int countByExample(ShopownerOrderExample example);

    int deleteByExample(ShopownerOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopownerOrder record);

    int insertSelective(ShopownerOrder record);

    List<ShopownerOrder> selectByExample(ShopownerOrderExample example);

    ShopownerOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopownerOrder record, @Param("example") ShopownerOrderExample example);

    int updateByExample(@Param("record") ShopownerOrder record, @Param("example") ShopownerOrderExample example);

    int updateByPrimaryKeySelective(ShopownerOrder record);

    int updateByPrimaryKey(ShopownerOrder record);
}