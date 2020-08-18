package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFeedbackCustom;
import com.jf.entity.MemberFeedbackExample;
@Repository
public interface MemberFeedbackCustomMapper{
    int countByExample(MemberFeedbackExample example);
    List<MemberFeedbackCustom> selectByExample(MemberFeedbackExample example);
    MemberFeedbackCustom selectByPrimaryKey(Integer id);
    //获取100天内的处理人 
    public List<Map<String, Object>> getMemberfeedbackList();

}