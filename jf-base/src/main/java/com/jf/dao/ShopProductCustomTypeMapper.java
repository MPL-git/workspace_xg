package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopProductCustomType;
import com.jf.entity.ShopProductCustomTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopProductCustomTypeMapper extends BaseDao<ShopProductCustomType, ShopProductCustomTypeExample> {
    int countByExample(ShopProductCustomTypeExample example);

    int deleteByExample(ShopProductCustomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopProductCustomType record);

    int insertSelective(ShopProductCustomType record);

    List<ShopProductCustomType> selectByExample(ShopProductCustomTypeExample example);

    ShopProductCustomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopProductCustomType record, @Param("example") ShopProductCustomTypeExample example);

    int updateByExample(@Param("record") ShopProductCustomType record, @Param("example") ShopProductCustomTypeExample example);

    int updateByPrimaryKeySelective(ShopProductCustomType record);

    int updateByPrimaryKey(ShopProductCustomType record);
}
