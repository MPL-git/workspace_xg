package com.jf.dao;

import com.jf.entity.DouyinMemberBind;
import com.jf.entity.DouyinMemberBindExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DouyinMemberBindMapper extends BaseDao<DouyinMemberBind, DouyinMemberBindExample> {
    int countByExample(DouyinMemberBindExample example);

    int deleteByExample(DouyinMemberBindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DouyinMemberBind record);

    int insertSelective(DouyinMemberBind record);

    List<DouyinMemberBind> selectByExample(DouyinMemberBindExample example);

    DouyinMemberBind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DouyinMemberBind record, @Param("example") DouyinMemberBindExample example);

    int updateByExample(@Param("record") DouyinMemberBind record, @Param("example") DouyinMemberBindExample example);

    int updateByPrimaryKeySelective(DouyinMemberBind record);

    int updateByPrimaryKey(DouyinMemberBind record);
}