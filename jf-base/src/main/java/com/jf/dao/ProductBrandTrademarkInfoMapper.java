package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductBrandTrademarkInfo;
import com.jf.entity.ProductBrandTrademarkInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandTrademarkInfoMapper extends BaseDao<ProductBrandTrademarkInfo, ProductBrandTrademarkInfoExample> {
    int countByExample(ProductBrandTrademarkInfoExample example);

    int deleteByExample(ProductBrandTrademarkInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductBrandTrademarkInfo record);

    int insertSelective(ProductBrandTrademarkInfo record);

    List<ProductBrandTrademarkInfo> selectByExample(ProductBrandTrademarkInfoExample example);

    ProductBrandTrademarkInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductBrandTrademarkInfo record, @Param("example") ProductBrandTrademarkInfoExample example);

    int updateByExample(@Param("record") ProductBrandTrademarkInfo record, @Param("example") ProductBrandTrademarkInfoExample example);

    int updateByPrimaryKeySelective(ProductBrandTrademarkInfo record);

    int updateByPrimaryKey(ProductBrandTrademarkInfo record);
}
