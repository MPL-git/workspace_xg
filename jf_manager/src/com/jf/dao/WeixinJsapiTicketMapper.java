package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.WeixinJsapiTicket;
import com.jf.entity.WeixinJsapiTicketExample;
@Repository
public interface WeixinJsapiTicketMapper extends BaseDao<WeixinJsapiTicket, WeixinJsapiTicketExample>{
    int countByExample(WeixinJsapiTicketExample example);

    int deleteByExample(WeixinJsapiTicketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinJsapiTicket record);

    int insertSelective(WeixinJsapiTicket record);

    List<WeixinJsapiTicket> selectByExample(WeixinJsapiTicketExample example);

    WeixinJsapiTicket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinJsapiTicket record, @Param("example") WeixinJsapiTicketExample example);

    int updateByExample(@Param("record") WeixinJsapiTicket record, @Param("example") WeixinJsapiTicketExample example);

    int updateByPrimaryKeySelective(WeixinJsapiTicket record);

    int updateByPrimaryKey(WeixinJsapiTicket record);
}