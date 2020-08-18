package com.jf.dao;

import com.jf.entity.Xiaonengcustomerservice;
import com.jf.entity.XiaonengcustomerserviceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XiaonengcustomerserviceMapper extends BaseDao<Xiaonengcustomerservice, XiaonengcustomerserviceExample>{
    int countByExample(XiaonengcustomerserviceExample example);

    int deleteByExample(XiaonengcustomerserviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Xiaonengcustomerservice record);

    int insertSelective(Xiaonengcustomerservice record);

    List<Xiaonengcustomerservice> selectByExample(XiaonengcustomerserviceExample example);

    Xiaonengcustomerservice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Xiaonengcustomerservice record, @Param("example") XiaonengcustomerserviceExample example);

    int updateByExample(@Param("record") Xiaonengcustomerservice record, @Param("example") XiaonengcustomerserviceExample example);

    int updateByPrimaryKeySelective(Xiaonengcustomerservice record);

    int updateByPrimaryKey(Xiaonengcustomerservice record);
}