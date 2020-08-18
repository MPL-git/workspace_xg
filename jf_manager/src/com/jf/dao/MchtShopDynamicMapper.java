package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicExample;
@Repository
public interface MchtShopDynamicMapper extends BaseDao<MchtShopDynamic, MchtShopDynamicExample>{
    int countByExample(MchtShopDynamicExample example);

    int deleteByExample(MchtShopDynamicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtShopDynamic record);

    int insertSelective(MchtShopDynamic record);

    List<MchtShopDynamic> selectByExampleWithBLOBs(MchtShopDynamicExample example);

    List<MchtShopDynamic> selectByExample(MchtShopDynamicExample example);

    MchtShopDynamic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtShopDynamic record, @Param("example") MchtShopDynamicExample example);

    int updateByExampleWithBLOBs(@Param("record") MchtShopDynamic record, @Param("example") MchtShopDynamicExample example);

    int updateByExample(@Param("record") MchtShopDynamic record, @Param("example") MchtShopDynamicExample example);

    int updateByPrimaryKeySelective(MchtShopDynamic record);

    int updateByPrimaryKeyWithBLOBs(MchtShopDynamic record);

    int updateByPrimaryKey(MchtShopDynamic record);
}