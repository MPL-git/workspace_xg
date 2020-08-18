package com.jf.dao;

import com.jf.entity.MemberLottery;
import com.jf.entity.MemberLotteryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLotteryMapper extends BaseDao<MemberLottery, MemberLotteryExample>{
    int countByExample(MemberLotteryExample example);

    int deleteByExample(MemberLotteryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLottery record);

    int insertSelective(MemberLottery record);

    List<MemberLottery> selectByExample(MemberLotteryExample example);

    MemberLottery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLottery record, @Param("example") MemberLotteryExample example);

    int updateByExample(@Param("record") MemberLottery record, @Param("example") MemberLotteryExample example);

    int updateByPrimaryKeySelective(MemberLottery record);

    int updateByPrimaryKey(MemberLottery record);
}