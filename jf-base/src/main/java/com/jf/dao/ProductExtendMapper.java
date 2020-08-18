package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductExtend;
import com.jf.entity.ProductExtendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductExtendMapper extends BaseDao<ProductExtend, ProductExtendExample> {
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