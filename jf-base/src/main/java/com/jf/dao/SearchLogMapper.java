package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SearchLog;
import com.jf.entity.SearchLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogMapper extends BaseDao<SearchLog, SearchLogExample> {
    int countByExample(SearchLogExample example);

    int deleteByExample(SearchLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SearchLog record);

    int insertSelective(SearchLog record);

    List<SearchLog> selectByExample(SearchLogExample example);

    SearchLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SearchLog record, @Param("example") SearchLogExample example);

    int updateByExample(@Param("record") SearchLog record, @Param("example") SearchLogExample example);

    int updateByPrimaryKeySelective(SearchLog record);

    int updateByPrimaryKey(SearchLog record);
}
