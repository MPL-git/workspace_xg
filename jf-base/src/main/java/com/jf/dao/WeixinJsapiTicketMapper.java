package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WeixinJsapiTicket;
import com.jf.entity.WeixinJsapiTicketExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinJsapiTicketMapper extends BaseDao<WeixinJsapiTicket, WeixinJsapiTicketExample> {
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
