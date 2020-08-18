package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.BusinessCirclesAppealOrderPic;
import com.jf.entity.BusinessCirclesAppealOrderPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessCirclesAppealOrderPicMapper extends BaseDao<BusinessCirclesAppealOrderPic, BusinessCirclesAppealOrderPicExample> {
    int countByExample(BusinessCirclesAppealOrderPicExample example);

    int deleteByExample(BusinessCirclesAppealOrderPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessCirclesAppealOrderPic record);

    int insertSelective(BusinessCirclesAppealOrderPic record);

    List<BusinessCirclesAppealOrderPic> selectByExample(BusinessCirclesAppealOrderPicExample example);

    BusinessCirclesAppealOrderPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessCirclesAppealOrderPic record, @Param("example") BusinessCirclesAppealOrderPicExample example);

    int updateByExample(@Param("record") BusinessCirclesAppealOrderPic record, @Param("example") BusinessCirclesAppealOrderPicExample example);

    int updateByPrimaryKeySelective(BusinessCirclesAppealOrderPic record);

    int updateByPrimaryKey(BusinessCirclesAppealOrderPic record);
}
