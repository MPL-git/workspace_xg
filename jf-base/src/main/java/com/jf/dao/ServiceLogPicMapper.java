package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceLogPicMapper extends BaseDao<ServiceLogPic, ServiceLogPicExample> {
    int countByExample(ServiceLogPicExample example);

    int deleteByExample(ServiceLogPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceLogPic record);

    int insertSelective(ServiceLogPic record);

    List<ServiceLogPic> selectByExample(ServiceLogPicExample example);

    ServiceLogPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ServiceLogPic record, @Param("example") ServiceLogPicExample example);

    int updateByExample(@Param("record") ServiceLogPic record, @Param("example") ServiceLogPicExample example);

    int updateByPrimaryKeySelective(ServiceLogPic record);

    int updateByPrimaryKey(ServiceLogPic record);
}
