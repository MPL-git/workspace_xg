package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductVideo;
import com.jf.entity.ProductVideoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVideoMapper extends BaseDao<ProductVideo, ProductVideoExample> {
    int countByExample(ProductVideoExample example);

    int deleteByExample(ProductVideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductVideo record);

    int insertSelective(ProductVideo record);

    List<ProductVideo> selectByExample(ProductVideoExample example);

    ProductVideo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductVideo record, @Param("example") ProductVideoExample example);

    int updateByExample(@Param("record") ProductVideo record, @Param("example") ProductVideoExample example);

    int updateByPrimaryKeySelective(ProductVideo record);

    int updateByPrimaryKey(ProductVideo record);
}
