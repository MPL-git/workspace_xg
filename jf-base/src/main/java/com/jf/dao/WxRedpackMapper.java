package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WxRedpack;
import com.jf.entity.WxRedpackExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WxRedpackMapper extends BaseDao<WxRedpack, WxRedpackExample> {
    int countByExample(WxRedpackExample example);

    int deleteByExample(WxRedpackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxRedpack record);

    int insertSelective(WxRedpack record);

    List<WxRedpack> selectByExample(WxRedpackExample example);

    WxRedpack selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxRedpack record, @Param("example") WxRedpackExample example);

    int updateByExample(@Param("record") WxRedpack record, @Param("example") WxRedpackExample example);

    int updateByPrimaryKeySelective(WxRedpack record);

    int updateByPrimaryKey(WxRedpack record);
}
