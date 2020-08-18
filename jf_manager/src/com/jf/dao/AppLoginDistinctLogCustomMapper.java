package com.jf.dao;

import com.jf.entity.AppLoginDistinctLog;
import com.jf.entity.AppLoginDistinctLogCustom;
import com.jf.entity.AppLoginDistinctLogCustomExample;
import com.jf.entity.AppLoginDistinctLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface AppLoginDistinctLogCustomMapper {
    int countByCustomExample(AppLoginDistinctLogCustomExample example);

    List<AppLoginDistinctLogCustom> selectByCustomExample(AppLoginDistinctLogCustomExample example);

    AppLoginDistinctLogCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") AppLoginDistinctLog record, @Param("example") AppLoginDistinctLogCustomExample example);

    public Set<Integer> selectMemberKeepReportList(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectMemberKeepList(Map<String, Object> paramMap);

    public Integer selectMemberKeepCount(Map<String, Object> paramMap);

    public Set<Integer> selectMemberRepurchaseReportList(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectMemberRepurchaseList(Map<String, Object> paramMap);

    public Integer selectMemberRepurchaseCount(Map<String, Object> paramMap);

}