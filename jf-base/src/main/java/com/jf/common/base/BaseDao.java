package com.jf.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 把常用的方法抽象到此接口中，避免在多个接口中重复定义
 * @author ghq
 *
 * @param <T>
 */
public interface BaseDao<T,E> {
    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
 
}
