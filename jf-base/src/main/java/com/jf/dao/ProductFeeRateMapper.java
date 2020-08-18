package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductFeeRate;
import com.jf.entity.ProductFeeRateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFeeRateMapper extends BaseDao<ProductFeeRate, ProductFeeRateExample> {
    int countByExample(ProductFeeRateExample example);

    int deleteByExample(ProductFeeRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductFeeRate record);

    int insertSelective(ProductFeeRate record);

    List<ProductFeeRate> selectByExample(ProductFeeRateExample example);

    ProductFeeRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductFeeRate record, @Param("example") ProductFeeRateExample example);

    int updateByExample(@Param("record") ProductFeeRate record, @Param("example") ProductFeeRateExample example);

    int updateByPrimaryKeySelective(ProductFeeRate record);

    int updateByPrimaryKey(ProductFeeRate record);
}
