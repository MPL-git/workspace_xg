package com.jf.dao;

import com.jf.entity.DeliveryOvertimeSpecialCnfArea;
import com.jf.entity.DeliveryOvertimeSpecialCnfAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOvertimeSpecialCnfAreaMapper extends BaseDao<DeliveryOvertimeSpecialCnfArea, DeliveryOvertimeSpecialCnfAreaExample> {
    int countByExample(DeliveryOvertimeSpecialCnfAreaExample example);

    int deleteByExample(DeliveryOvertimeSpecialCnfAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryOvertimeSpecialCnfArea record);

    int insertSelective(DeliveryOvertimeSpecialCnfArea record);

    List<DeliveryOvertimeSpecialCnfArea> selectByExample(DeliveryOvertimeSpecialCnfAreaExample example);

    DeliveryOvertimeSpecialCnfArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeliveryOvertimeSpecialCnfArea record, @Param("example") DeliveryOvertimeSpecialCnfAreaExample example);

    int updateByExample(@Param("record") DeliveryOvertimeSpecialCnfArea record, @Param("example") DeliveryOvertimeSpecialCnfAreaExample example);

    int updateByPrimaryKeySelective(DeliveryOvertimeSpecialCnfArea record);

    int updateByPrimaryKey(DeliveryOvertimeSpecialCnfArea record);
}