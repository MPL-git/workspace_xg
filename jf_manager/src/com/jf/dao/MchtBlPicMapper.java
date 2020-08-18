package com.jf.dao;

import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtBlPicMapper extends BaseDao<MchtBlPic, MchtBlPicExample> {
    int countByExample(MchtBlPicExample example);

    int deleteByExample(MchtBlPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBlPic record);

    int insertSelective(MchtBlPic record);

    List<MchtBlPic> selectByExample(MchtBlPicExample example);

    MchtBlPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBlPic record, @Param("example") MchtBlPicExample example);

    int updateByExample(@Param("record") MchtBlPic record, @Param("example") MchtBlPicExample example);

    int updateByPrimaryKeySelective(MchtBlPic record);

    int updateByPrimaryKey(MchtBlPic record);
}