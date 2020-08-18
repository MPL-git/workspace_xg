package com.jf.dao;

import com.jf.entity.DouyinSprChnl;
import com.jf.entity.DouyinSprChnlExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DouyinSprChnlMapper extends BaseDao<DouyinSprChnl, DouyinSprChnlExample> {
    int countByExample(DouyinSprChnlExample example);

    int deleteByExample(DouyinSprChnlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DouyinSprChnl record);

    int insertSelective(DouyinSprChnl record);

    List<DouyinSprChnl> selectByExample(DouyinSprChnlExample example);

    DouyinSprChnl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DouyinSprChnl record, @Param("example") DouyinSprChnlExample example);

    int updateByExample(@Param("record") DouyinSprChnl record, @Param("example") DouyinSprChnlExample example);

    int updateByPrimaryKeySelective(DouyinSprChnl record);

    int updateByPrimaryKey(DouyinSprChnl record);
}