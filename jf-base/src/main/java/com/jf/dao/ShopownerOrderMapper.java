package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopownerOrder;
import com.jf.entity.ShopownerOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
