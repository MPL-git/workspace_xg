package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SingleProductActivityProductType;
import com.jf.entity.SingleProductActivityProductTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleProductActivityProductTypeMapper extends BaseDao<SingleProductActivityProductType, SingleProductActivityProductTypeExample> {
    int countByExample(SingleProductActivityProductTypeExample example);

    int deleteByExample(SingleProductActivityProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleProductActivityProductType record);

    int insertSelective(SingleProductActivityProductType record);

    List<SingleProductActivityProductType> selectByExample(SingleProductActivityProductTypeExample example);

    SingleProductActivityProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleProductActivityProductType record, @Param("example") SingleProductActivityProductTypeExample example);

    int updateByExample(@Param("record") SingleProductActivityProductType record, @Param("example") SingleProductActivityProductTypeExample example);

    int updateByPrimaryKeySelective(SingleProductActivityProductType record);

    int updateByPrimaryKey(SingleProductActivityProductType record);
}