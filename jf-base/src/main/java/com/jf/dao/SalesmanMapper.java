package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Salesman;
import com.jf.entity.SalesmanExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesmanMapper extends BaseDao<Salesman, SalesmanExample> {
    int countByExample(SalesmanExample example);

    int deleteByExample(SalesmanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Salesman record);

    int insertSelective(Salesman record);

    List<Salesman> selectByExample(SalesmanExample example);

    Salesman selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Salesman record, @Param("example") SalesmanExample example);

    int updateByExample(@Param("record") Salesman record, @Param("example") SalesmanExample example);

    int updateByPrimaryKeySelective(Salesman record);

    int updateByPrimaryKey(Salesman record);
}
