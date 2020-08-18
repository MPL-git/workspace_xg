package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductAfterTemplet;
import com.jf.entity.ProductAfterTempletExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAfterTempletMapper extends BaseDao<ProductAfterTemplet, ProductAfterTempletExample> {
    int countByExample(ProductAfterTempletExample example);

    int deleteByExample(ProductAfterTempletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductAfterTemplet record);

    int insertSelective(ProductAfterTemplet record);

    List<ProductAfterTemplet> selectByExample(ProductAfterTempletExample example);

    ProductAfterTemplet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductAfterTemplet record, @Param("example") ProductAfterTempletExample example);

    int updateByExample(@Param("record") ProductAfterTemplet record, @Param("example") ProductAfterTempletExample example);

    int updateByPrimaryKeySelective(ProductAfterTemplet record);

    int updateByPrimaryKey(ProductAfterTemplet record);
}
