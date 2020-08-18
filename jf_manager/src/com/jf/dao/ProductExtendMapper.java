package com.jf.dao;

import com.jf.entity.ProductExtend;
import com.jf.entity.ProductExtendExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductExtendMapper extends BaseDao<ProductExtend,ProductExtendExample> {
    int countByExample(ProductExtendExample example);

    int deleteByExample(ProductExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductExtend record);

    int insertSelective(ProductExtend record);

    List<ProductExtend> selectByExample(ProductExtendExample example);

    ProductExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductExtend record, @Param("example") ProductExtendExample example);

    int updateByExample(@Param("record") ProductExtend record, @Param("example") ProductExtendExample example);

    int updateByPrimaryKeySelective(ProductExtend record);

    int updateByPrimaryKey(ProductExtend record);
}