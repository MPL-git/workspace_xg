package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.BusinessCirclesAppealOrder;
import com.jf.entity.BusinessCirclesAppealOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessCirclesAppealOrderMapper extends BaseDao<BusinessCirclesAppealOrder, BusinessCirclesAppealOrderExample> {
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
