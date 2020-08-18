package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SobotCustomerService;
import com.jf.entity.SobotCustomerServiceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SobotCustomerServiceMapper extends BaseDao<SobotCustomerService, SobotCustomerServiceExample> {
    int countByExample(SobotCustomerServiceExample example);

    int deleteByExample(SobotCustomerServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SobotCustomerService record);

    int insertSelective(SobotCustomerService record);

    List<SobotCustomerService> selectByExample(SobotCustomerServiceExample example);

    SobotCustomerService selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SobotCustomerService record, @Param("example") SobotCustomerServiceExample example);

    int updateByExample(@Param("record") SobotCustomerService record, @Param("example") SobotCustomerServiceExample example);

    int updateByPrimaryKeySelective(SobotCustomerService record);

    int updateByPrimaryKey(SobotCustomerService record);
}
