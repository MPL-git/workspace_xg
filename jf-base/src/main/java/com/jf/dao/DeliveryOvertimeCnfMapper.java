package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DeliveryOvertimeCnf;
import com.jf.entity.DeliveryOvertimeCnfExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryOvertimeCnfMapper extends BaseDao<DeliveryOvertimeCnf, DeliveryOvertimeCnfExample> {
    int countByExample(DeliveryOvertimeCnfExample example);

    int deleteByExample(DeliveryOvertimeCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryOvertimeCnf record);

    int insertSelective(DeliveryOvertimeCnf record);

    List<DeliveryOvertimeCnf> selectByExample(DeliveryOvertimeCnfExample example);

    DeliveryOvertimeCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeliveryOvertimeCnf record, @Param("example") DeliveryOvertimeCnfExample example);

    int updateByExample(@Param("record") DeliveryOvertimeCnf record, @Param("example") DeliveryOvertimeCnfExample example);

    int updateByPrimaryKeySelective(DeliveryOvertimeCnf record);

    int updateByPrimaryKey(DeliveryOvertimeCnf record);
}
