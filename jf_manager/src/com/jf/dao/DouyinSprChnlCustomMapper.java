package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.DouyinSprChnl;
import com.jf.entity.DouyinSprChnlCustom;
import com.jf.entity.DouyinSprChnlCustomExample;

@Repository
public interface DouyinSprChnlCustomMapper {
    int countByCustomExample(DouyinSprChnlCustomExample example);

    List<DouyinSprChnlCustom> selectByCustomExample(DouyinSprChnlCustomExample example);

    DouyinSprChnlCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") DouyinSprChnl record, @Param("example") DouyinSprChnlCustomExample example);

}