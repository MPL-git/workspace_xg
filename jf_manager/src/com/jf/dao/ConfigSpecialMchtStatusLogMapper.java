package com.jf.dao;

import com.jf.entity.ConfigSpecialMchtStatusLog;
import com.jf.entity.ConfigSpecialMchtStatusLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigSpecialMchtStatusLogMapper extends BaseDao<ConfigSpecialMchtStatusLog, ConfigSpecialMchtStatusLogExample>{
    int countByExample(ConfigSpecialMchtStatusLogExample example);

    int deleteByExample(ConfigSpecialMchtStatusLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigSpecialMchtStatusLog record);

    int insertSelective(ConfigSpecialMchtStatusLog record);

    List<ConfigSpecialMchtStatusLog> selectByExample(ConfigSpecialMchtStatusLogExample example);

    ConfigSpecialMchtStatusLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigSpecialMchtStatusLog record, @Param("example") ConfigSpecialMchtStatusLogExample example);

    int updateByExample(@Param("record") ConfigSpecialMchtStatusLog record, @Param("example") ConfigSpecialMchtStatusLogExample example);

    int updateByPrimaryKeySelective(ConfigSpecialMchtStatusLog record);

    int updateByPrimaryKey(ConfigSpecialMchtStatusLog record);
}