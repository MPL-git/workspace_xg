package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtSettledApplyPic;
import com.jf.entity.MchtSettledApplyPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSettledApplyPicMapper extends BaseDao<MchtSettledApplyPic, MchtSettledApplyPicExample> {
    int countByExample(MchtSettledApplyPicExample example);

    int deleteByExample(MchtSettledApplyPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtSettledApplyPic record);

    int insertSelective(MchtSettledApplyPic record);

    List<MchtSettledApplyPic> selectByExample(MchtSettledApplyPicExample example);

    MchtSettledApplyPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtSettledApplyPic record, @Param("example") MchtSettledApplyPicExample example);

    int updateByExample(@Param("record") MchtSettledApplyPic record, @Param("example") MchtSettledApplyPicExample example);

    int updateByPrimaryKeySelective(MchtSettledApplyPic record);

    int updateByPrimaryKey(MchtSettledApplyPic record);
}
