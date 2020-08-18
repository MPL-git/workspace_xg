package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WeixinGzhMemberBind;
import com.jf.entity.WeixinGzhMemberBindExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinGzhMemberBindMapper extends BaseDao<WeixinGzhMemberBind, WeixinGzhMemberBindExample> {
    int countByExample(WeixinGzhMemberBindExample example);

    int deleteByExample(WeixinGzhMemberBindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinGzhMemberBind record);

    int insertSelective(WeixinGzhMemberBind record);

    List<WeixinGzhMemberBind> selectByExample(WeixinGzhMemberBindExample example);

    WeixinGzhMemberBind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinGzhMemberBind record, @Param("example") WeixinGzhMemberBindExample example);

    int updateByExample(@Param("record") WeixinGzhMemberBind record, @Param("example") WeixinGzhMemberBindExample example);

    int updateByPrimaryKeySelective(WeixinGzhMemberBind record);

    int updateByPrimaryKey(WeixinGzhMemberBind record);
}
