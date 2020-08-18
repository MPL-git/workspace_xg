package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DeliveryOvertimeSpecialCnf;
import com.jf.entity.DeliveryOvertimeSpecialCnfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOvertimeSpecialCnfMapper extends BaseDao<DeliveryOvertimeSpecialCnf, DeliveryOvertimeSpecialCnfExample> {
    int countByExample(DeliveryOvertimeSpecialCnfExample example);

    int deleteByExample(DeliveryOvertimeSpecialCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryOvertimeSpecialCnf record);

    int insertSelective(DeliveryOvertimeSpecialCnf record);

    List<DeliveryOvertimeSpecialCnf> selectByExample(DeliveryOvertimeSpecialCnfExample example);

    DeliveryOvertimeSpecialCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeliveryOvertimeSpecialCnf record, @Param("example") DeliveryOvertimeSpecialCnfExample example);

    int updateByExample(@Param("record") DeliveryOvertimeSpecialCnf record, @Param("example") DeliveryOvertimeSpecialCnfExample example);

    int updateByPrimaryKeySelective(DeliveryOvertimeSpecialCnf record);

    int updateByPrimaryKey(DeliveryOvertimeSpecialCnf record);
}