package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductTypeStatistics;
import com.jf.entity.ProductTypeStatisticsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeStatisticsMapper extends BaseDao<ProductTypeStatistics, ProductTypeStatisticsExample> {
    int countByExample(ProductTypeStatisticsExample example);

    int deleteByExample(ProductTypeStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductTypeStatistics record);

    int insertSelective(ProductTypeStatistics record);

    List<ProductTypeStatistics> selectByExample(ProductTypeStatisticsExample example);

    ProductTypeStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductTypeStatistics record, @Param("example") ProductTypeStatisticsExample example);

    int updateByExample(@Param("record") ProductTypeStatistics record, @Param("example") ProductTypeStatisticsExample example);

    int updateByPrimaryKeySelective(ProductTypeStatistics record);

    int updateByPrimaryKey(ProductTypeStatistics record);
}