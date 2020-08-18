package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ZsProductType;
import com.jf.entity.ZsProductTypeExample;
@Repository
public interface ZsProductTypeMapper extends BaseDao<ZsProductType, ZsProductTypeExample>{
    int countByExample(ZsProductTypeExample example);

    int deleteByExample(ZsProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZsProductType record);

    int insertSelective(ZsProductType record);

    List<ZsProductType> selectByExampleWithBLOBs(ZsProductTypeExample example);

    List<ZsProductType> selectByExample(ZsProductTypeExample example);

    ZsProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZsProductType record, @Param("example") ZsProductTypeExample example);

    int updateByExampleWithBLOBs(@Param("record") ZsProductType record, @Param("example") ZsProductTypeExample example);

    int updateByExample(@Param("record") ZsProductType record, @Param("example") ZsProductTypeExample example);

    int updateByPrimaryKeySelective(ZsProductType record);

    int updateByPrimaryKeyWithBLOBs(ZsProductType record);

    int updateByPrimaryKey(ZsProductType record);
}