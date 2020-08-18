package com.jf.dao;

import com.jf.entity.KeywordHomoionym;
import com.jf.entity.KeywordHomoionymExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface KeywordHomoionymMapper extends BaseDao<KeywordHomoionym, KeywordHomoionymExample>{
    int countByExample(KeywordHomoionymExample example);

    int deleteByExample(KeywordHomoionymExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KeywordHomoionym record);

    int insertSelective(KeywordHomoionym record);

    List<KeywordHomoionym> selectByExample(KeywordHomoionymExample example);

    KeywordHomoionym selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KeywordHomoionym record, @Param("example") KeywordHomoionymExample example);

    int updateByExample(@Param("record") KeywordHomoionym record, @Param("example") KeywordHomoionymExample example);

    int updateByPrimaryKeySelective(KeywordHomoionym record);

    int updateByPrimaryKey(KeywordHomoionym record);
}