package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtShopDynamicMapper extends BaseDao<MchtShopDynamic, MchtShopDynamicExample> {
    int countByExample(MchtShopDynamicExample example);

    int deleteByExample(MchtShopDynamicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtShopDynamic record);

    int insertSelective(MchtShopDynamic record);

    List<MchtShopDynamic> selectByExample(MchtShopDynamicExample example);

    MchtShopDynamic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtShopDynamic record, @Param("example") MchtShopDynamicExample example);

    int updateByExample(@Param("record") MchtShopDynamic record, @Param("example") MchtShopDynamicExample example);

    int updateByPrimaryKeySelective(MchtShopDynamic record);

    int updateByPrimaryKey(MchtShopDynamic record);
}
