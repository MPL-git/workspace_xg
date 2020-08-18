package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.XiaonengCustomerService;
import com.jf.entity.XiaonengCustomerServiceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XiaonengCustomerServiceMapper extends BaseDao<XiaonengCustomerService, XiaonengCustomerServiceExample> {
    int countByExample(XiaonengCustomerServiceExample example);

    int deleteByExample(XiaonengCustomerServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XiaonengCustomerService record);

    int insertSelective(XiaonengCustomerService record);

    List<XiaonengCustomerService> selectByExample(XiaonengCustomerServiceExample example);

    XiaonengCustomerService selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XiaonengCustomerService record, @Param("example") XiaonengCustomerServiceExample example);

    int updateByExample(@Param("record") XiaonengCustomerService record, @Param("example") XiaonengCustomerServiceExample example);

    int updateByPrimaryKeySelective(XiaonengCustomerService record);

    int updateByPrimaryKey(XiaonengCustomerService record);
}
