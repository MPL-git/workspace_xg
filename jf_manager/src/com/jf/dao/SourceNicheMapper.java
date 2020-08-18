package com.jf.dao;

import com.jf.entity.SourceNiche;
import com.jf.entity.SourceNicheExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SourceNicheMapper extends BaseDao<SourceNiche, SourceNicheExample> {
    int countByExample(SourceNicheExample example);

    int deleteByExample(SourceNicheExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SourceNiche record);

    int insertSelective(SourceNiche record);

    List<SourceNiche> selectByExample(SourceNicheExample example);

    SourceNiche selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SourceNiche record, @Param("example") SourceNicheExample example);

    int updateByExample(@Param("record") SourceNiche record, @Param("example") SourceNicheExample example);

    int updateByPrimaryKeySelective(SourceNiche record);

    int updateByPrimaryKey(SourceNiche record);
}