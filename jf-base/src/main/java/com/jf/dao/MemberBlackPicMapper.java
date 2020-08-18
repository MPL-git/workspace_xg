package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberBlackPic;
import com.jf.entity.MemberBlackPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberBlackPicMapper extends BaseDao<MemberBlackPic, MemberBlackPicExample> {
    int countByExample(MemberBlackPicExample example);

    int deleteByExample(MemberBlackPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberBlackPic record);

    int insertSelective(MemberBlackPic record);

    List<MemberBlackPic> selectByExample(MemberBlackPicExample example);

    MemberBlackPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberBlackPic record, @Param("example") MemberBlackPicExample example);

    int updateByExample(@Param("record") MemberBlackPic record, @Param("example") MemberBlackPicExample example);

    int updateByPrimaryKeySelective(MemberBlackPic record);

    int updateByPrimaryKey(MemberBlackPic record);
}
