package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Catalog;
import com.jf.entity.CatalogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogMapper extends BaseDao<Catalog, CatalogExample> {
    int countByExample(CatalogExample example);

    int deleteByExample(CatalogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Catalog record);

    int insertSelective(Catalog record);

    List<Catalog> selectByExample(CatalogExample example);

    Catalog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Catalog record, @Param("example") CatalogExample example);

    int updateByExample(@Param("record") Catalog record, @Param("example") CatalogExample example);

    int updateByPrimaryKeySelective(Catalog record);

    int updateByPrimaryKey(Catalog record);
}
