package com.jf.dao;

import com.jf.entity.MemberLotteryCustom;
import com.jf.entity.MemberLotteryCustomExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLotteryCustomMapper {

   Integer countMemberLotteryCustomByExample(MemberLotteryCustomExample example);

   List<MemberLotteryCustom> selectMemberLotteryCustomByExample(MemberLotteryCustomExample example);
}