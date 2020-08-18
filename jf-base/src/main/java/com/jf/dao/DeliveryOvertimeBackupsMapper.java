package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DeliveryOvertimeBackups;
import com.jf.entity.DeliveryOvertimeBackupsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOvertimeBackupsMapper extends BaseDao<DeliveryOvertimeBackups, DeliveryOvertimeBackupsExample> {
    int countByExample(DeliveryOvertimeBackupsExample example);

    int deleteByExample(DeliveryOvertimeBackupsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryOvertimeBackups record);

    int insertSelective(DeliveryOvertimeBackups record);

    List<DeliveryOvertimeBackups> selectByExample(DeliveryOvertimeBackupsExample example);

    DeliveryOvertimeBackups selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeliveryOvertimeBackups record, @Param("example") DeliveryOvertimeBackupsExample example);

    int updateByExample(@Param("record") DeliveryOvertimeBackups record, @Param("example") DeliveryOvertimeBackupsExample example);

    int updateByPrimaryKeySelective(DeliveryOvertimeBackups record);

    int updateByPrimaryKey(DeliveryOvertimeBackups record);
}