package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Shopowner;
import com.jf.entity.ShopownerExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopownerMapper extends BaseDao<Shopowner, ShopownerExample> {
    int countByExample(ShopownerExample example);

    int deleteByExample(ShopownerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Shopowner record);

    int insertSelective(Shopowner record);

    List<Shopowner> selectByExample(ShopownerExample example);

    Shopowner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Shopowner record, @Param("example") ShopownerExample example);

    int updateByExample(@Param("record") Shopowner record, @Param("example") ShopownerExample example);

    int updateByPrimaryKeySelective(Shopowner record);

    int updateByPrimaryKey(Shopowner record);
}
