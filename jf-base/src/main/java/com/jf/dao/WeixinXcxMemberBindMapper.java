package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WeixinXcxMemberBind;
import com.jf.entity.WeixinXcxMemberBindExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinXcxMemberBindMapper extends BaseDao<WeixinXcxMemberBind, WeixinXcxMemberBindExample> {
    int countByExample(WeixinXcxMemberBindExample example);

    int deleteByExample(WeixinXcxMemberBindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinXcxMemberBind record);

    int insertSelective(WeixinXcxMemberBind record);

    List<WeixinXcxMemberBind> selectByExample(WeixinXcxMemberBindExample example);

    WeixinXcxMemberBind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinXcxMemberBind record, @Param("example") WeixinXcxMemberBindExample example);

    int updateByExample(@Param("record") WeixinXcxMemberBind record, @Param("example") WeixinXcxMemberBindExample example);

    int updateByPrimaryKeySelective(WeixinXcxMemberBind record);

    int updateByPrimaryKey(WeixinXcxMemberBind record);
}
