package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ShopPreferentialInfo;
import com.jf.entity.ShopPreferentialInfoExample;
@Repository
public interface ShopPreferentialInfoMapper extends BaseDao<ShopPreferentialInfo, ShopPreferentialInfoExample>{
    int countByExample(ShopPreferentialInfoExample example);

    int deleteByExample(ShopPreferentialInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopPreferentialInfo record);

    int insertSelective(ShopPreferentialInfo record);

    List<ShopPreferentialInfo> selectByExample(ShopPreferentialInfoExample example);

    ShopPreferentialInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopPreferentialInfo record, @Param("example") ShopPreferentialInfoExample example);

    int updateByExample(@Param("record") ShopPreferentialInfo record, @Param("example") ShopPreferentialInfoExample example);

    int updateByPrimaryKeySelective(ShopPreferentialInfo record);

    int updateByPrimaryKey(ShopPreferentialInfo record);
}