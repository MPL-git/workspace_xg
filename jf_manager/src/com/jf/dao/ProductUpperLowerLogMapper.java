package com.jf.dao;

import com.jf.entity.ProductUpperLowerLog;
import com.jf.entity.ProductUpperLowerLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUpperLowerLogMapper extends BaseDao<ProductUpperLowerLog,ProductUpperLowerLogExample>{
    int countByExample(ProductUpperLowerLogExample example);

    int deleteByExample(ProductUpperLowerLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductUpperLowerLog record);

    int insertSelective(ProductUpperLowerLog record);

    List<ProductUpperLowerLog> selectByExample(ProductUpperLowerLogExample example);

    ProductUpperLowerLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductUpperLowerLog record, @Param("example") ProductUpperLowerLogExample example);

    int updateByExample(@Param("record") ProductUpperLowerLog record, @Param("example") ProductUpperLowerLogExample example);

    int updateByPrimaryKeySelective(ProductUpperLowerLog record);

    int updateByPrimaryKey(ProductUpperLowerLog record);
}