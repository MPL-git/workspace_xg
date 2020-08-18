package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicExample;
@Repository
public interface ServiceLogPicMapper extends BaseDao<ServiceLogPic, ServiceLogPicExample>{
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