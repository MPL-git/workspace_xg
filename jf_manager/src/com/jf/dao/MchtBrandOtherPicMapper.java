package com.jf.dao;

import com.jf.entity.MchtBrandOtherPic;
import com.jf.entity.MchtBrandOtherPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtBrandOtherPicMapper extends BaseDao<MchtBrandOtherPic, MchtBrandOtherPicExample>{
    int countByExample(MchtBrandOtherPicExample example);

    int deleteByExample(MchtBrandOtherPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandOtherPic record);

    int insertSelective(MchtBrandOtherPic record);

    List<MchtBrandOtherPic> selectByExample(MchtBrandOtherPicExample example);

    MchtBrandOtherPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandOtherPic record, @Param("example") MchtBrandOtherPicExample example);

    int updateByExample(@Param("record") MchtBrandOtherPic record, @Param("example") MchtBrandOtherPicExample example);

    int updateByPrimaryKeySelective(MchtBrandOtherPic record);

    int updateByPrimaryKey(MchtBrandOtherPic record);
}