package com.jf.dao;

import com.jf.entity.MchtInformation;
import com.jf.entity.MchtInformationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtInformationMapper extends BaseDao<MchtInformation,MchtInformationExample>{
    int countByExample(MchtInformationExample example);

    int deleteByExample(MchtInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtInformation record);

    int insertSelective(MchtInformation record);

    List<MchtInformation> selectByExample(MchtInformationExample example);

    MchtInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtInformation record, @Param("example") MchtInformationExample example);

    int updateByExample(@Param("record") MchtInformation record, @Param("example") MchtInformationExample example);

    int updateByPrimaryKeySelective(MchtInformation record);

    int updateByPrimaryKey(MchtInformation record);
}