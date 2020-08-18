package com.jf.dao;

import com.jf.entity.ObligeeNotice;
import com.jf.entity.ObligeeNoticeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ObligeeNoticeMapper extends BaseDao<ObligeeNotice, ObligeeNoticeExample>{
    int countByExample(ObligeeNoticeExample example);

    int deleteByExample(ObligeeNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ObligeeNotice record);

    int insertSelective(ObligeeNotice record);

    List<ObligeeNotice> selectByExample(ObligeeNoticeExample example);

    ObligeeNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ObligeeNotice record, @Param("example") ObligeeNoticeExample example);

    int updateByExample(@Param("record") ObligeeNotice record, @Param("example") ObligeeNoticeExample example);

    int updateByPrimaryKeySelective(ObligeeNotice record);

    int updateByPrimaryKey(ObligeeNotice record);
}