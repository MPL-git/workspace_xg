package com.jf.dao;

import com.jf.entity.WithdrawOrderPic;
import com.jf.entity.WithdrawOrderPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawOrderPicMapper extends BaseDao<WithdrawOrderPic, WithdrawOrderPicExample>{
    int countByExample(WithdrawOrderPicExample example);

    int deleteByExample(WithdrawOrderPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawOrderPic record);

    int insertSelective(WithdrawOrderPic record);

    List<WithdrawOrderPic> selectByExample(WithdrawOrderPicExample example);

    WithdrawOrderPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WithdrawOrderPic record, @Param("example") WithdrawOrderPicExample example);

    int updateByExample(@Param("record") WithdrawOrderPic record, @Param("example") WithdrawOrderPicExample example);

    int updateByPrimaryKeySelective(WithdrawOrderPic record);

    int updateByPrimaryKey(WithdrawOrderPic record);
}