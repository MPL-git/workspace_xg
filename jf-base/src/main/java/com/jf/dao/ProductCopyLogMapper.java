package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductCopyLog;
import com.jf.entity.ProductCopyLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCopyLogMapper extends BaseDao<ProductCopyLog, ProductCopyLogExample> {
    int countByExample(ProductCopyLogExample example);

    int deleteByExample(ProductCopyLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductCopyLog record);

    int insertSelective(ProductCopyLog record);

    List<ProductCopyLog> selectByExample(ProductCopyLogExample example);

    ProductCopyLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductCopyLog record, @Param("example") ProductCopyLogExample example);

    int updateByExample(@Param("record") ProductCopyLog record, @Param("example") ProductCopyLogExample example);

    int updateByPrimaryKeySelective(ProductCopyLog record);

    int updateByPrimaryKey(ProductCopyLog record);
}
