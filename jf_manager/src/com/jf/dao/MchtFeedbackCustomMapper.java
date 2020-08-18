package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtFeedbackCustom;
import com.jf.entity.MchtFeedbackExample;
@Repository
public interface MchtFeedbackCustomMapper{
    int countByExample(MchtFeedbackExample example);
    List<MchtFeedbackCustom> selectByExample(MchtFeedbackExample example);
    MchtFeedbackCustom selectByPrimaryKey(Integer id);
    //获取100天内的处理人 
    public List<Map<String, Object>> getMchtfeedbackList();

}