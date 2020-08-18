package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.DouyinMemberBind;
import com.jf.entity.DouyinMemberBindCustom;
import com.jf.entity.DouyinMemberBindCustomExample;

@Repository
public interface DouyinMemberBindCustomMapper {
    int countByCustomExample(DouyinMemberBindCustomExample example);

    List<DouyinMemberBindCustom> selectByCustomExample(DouyinMemberBindCustomExample example);

    DouyinMemberBindCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") DouyinMemberBind record, @Param("example") DouyinMemberBindCustomExample example);

}