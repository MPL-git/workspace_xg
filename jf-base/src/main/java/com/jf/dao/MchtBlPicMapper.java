package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
