package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductStatistics;
import com.jf.entity.ProductStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatisticsMapper extends BaseDao<ProductStatistics, ProductStatisticsExample> {
    int countByExample(ProductStatisticsExample example);

    int deleteByExample(ProductStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductStatistics record);

    int insertSelective(ProductStatistics record);

    List<ProductStatistics> selectByExample(ProductStatisticsExample example);

    ProductStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductStatistics record, @Param("example") ProductStatisticsExample example);

    int updateByExample(@Param("record") ProductStatistics record, @Param("example") ProductStatisticsExample example);

    int updateByPrimaryKeySelective(ProductStatistics record);

    int updateByPrimaryKey(ProductStatistics record);
}