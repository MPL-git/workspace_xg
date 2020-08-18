package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtBrandProductType;
import com.jf.entity.MchtBrandProductTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandProductTypeMapper extends BaseDao<MchtBrandProductType, MchtBrandProductTypeExample> {
    int countByExample(MchtBrandProductTypeExample example);

    int deleteByExample(MchtBrandProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandProductType record);

    int insertSelective(MchtBrandProductType record);

    List<MchtBrandProductType> selectByExample(MchtBrandProductTypeExample example);

    MchtBrandProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandProductType record, @Param("example") MchtBrandProductTypeExample example);

    int updateByExample(@Param("record") MchtBrandProductType record, @Param("example") MchtBrandProductTypeExample example);

    int updateByPrimaryKeySelective(MchtBrandProductType record);

    int updateByPrimaryKey(MchtBrandProductType record);
}
