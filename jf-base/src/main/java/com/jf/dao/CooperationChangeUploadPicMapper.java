package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CooperationChangeUploadPic;
import com.jf.entity.CooperationChangeUploadPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CooperationChangeUploadPicMapper extends BaseDao<CooperationChangeUploadPic,CooperationChangeUploadPicExample> {
    int countByExample(CooperationChangeUploadPicExample example);

    int deleteByExample(CooperationChangeUploadPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CooperationChangeUploadPic record);

    int insertSelective(CooperationChangeUploadPic record);

    List<CooperationChangeUploadPic> selectByExample(CooperationChangeUploadPicExample example);

    CooperationChangeUploadPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CooperationChangeUploadPic record, @Param("example") CooperationChangeUploadPicExample example);

    int updateByExample(@Param("record") CooperationChangeUploadPic record, @Param("example") CooperationChangeUploadPicExample example);

    int updateByPrimaryKeySelective(CooperationChangeUploadPic record);

    int updateByPrimaryKey(CooperationChangeUploadPic record);
}