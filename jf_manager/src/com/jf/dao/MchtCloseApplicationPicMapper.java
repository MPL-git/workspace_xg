package com.jf.dao;

import com.jf.entity.MchtCloseApplicationPic;
import com.jf.entity.MchtCloseApplicationPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtCloseApplicationPicMapper extends BaseDao<MchtCloseApplicationPic, MchtCloseApplicationPicExample> {
    int countByExample(MchtCloseApplicationPicExample example);

    int deleteByExample(MchtCloseApplicationPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtCloseApplicationPic record);

    int insertSelective(MchtCloseApplicationPic record);

    List<MchtCloseApplicationPic> selectByExample(MchtCloseApplicationPicExample example);

    MchtCloseApplicationPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtCloseApplicationPic record, @Param("example") MchtCloseApplicationPicExample example);

    int updateByExample(@Param("record") MchtCloseApplicationPic record, @Param("example") MchtCloseApplicationPicExample example);

    int updateByPrimaryKeySelective(MchtCloseApplicationPic record);

    int updateByPrimaryKey(MchtCloseApplicationPic record);
      
}