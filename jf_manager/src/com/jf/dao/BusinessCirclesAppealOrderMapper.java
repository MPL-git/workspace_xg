package com.jf.dao;

import com.jf.entity.BusinessCirclesAppealOrder;
import com.jf.entity.BusinessCirclesAppealOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface BusinessCirclesAppealOrderMapper extends BaseDao<BusinessCirclesAppealOrder, BusinessCirclesAppealOrderExample>{
    int countByExample(BusinessCirclesAppealOrderExample example);

    int deleteByExample(BusinessCirclesAppealOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessCirclesAppealOrder record);

    int insertSelective(BusinessCirclesAppealOrder record);

    List<BusinessCirclesAppealOrder> selectByExample(BusinessCirclesAppealOrderExample example);

    BusinessCirclesAppealOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessCirclesAppealOrder record, @Param("example") BusinessCirclesAppealOrderExample example);

    int updateByExample(@Param("record") BusinessCirclesAppealOrder record, @Param("example") BusinessCirclesAppealOrderExample example);

    int updateByPrimaryKeySelective(BusinessCirclesAppealOrder record);

    int updateByPrimaryKey(BusinessCirclesAppealOrder record);
}