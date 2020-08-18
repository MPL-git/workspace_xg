package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SourceNicheProduct;
import com.jf.entity.SourceNicheProductExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceNicheProductMapper extends BaseDao<SourceNicheProduct, SourceNicheProductExample> {
    int countByExample(SourceNicheProductExample example);

    int deleteByExample(SourceNicheProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SourceNicheProduct record);

    int insertSelective(SourceNicheProduct record);

    List<SourceNicheProduct> selectByExample(SourceNicheProductExample example);

    SourceNicheProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SourceNicheProduct record, @Param("example") SourceNicheProductExample example);

    int updateByExample(@Param("record") SourceNicheProduct record, @Param("example") SourceNicheProductExample example);

    int updateByPrimaryKeySelective(SourceNicheProduct record);

    int updateByPrimaryKey(SourceNicheProduct record);
}
