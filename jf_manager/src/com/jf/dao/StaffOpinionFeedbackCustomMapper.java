package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.StaffOpinionFeedbackCustom;
import com.jf.entity.StaffOpinionFeedbackExample;

@Repository
public interface StaffOpinionFeedbackCustomMapper {
    int staffOpinionFeedbackcountByExample(StaffOpinionFeedbackExample example);

    List<StaffOpinionFeedbackCustom> staffOpinionFeedbackselectByExample(StaffOpinionFeedbackExample example);

    StaffOpinionFeedbackCustom staffOpinionFeedbackselectByPrimaryKey(Integer id);
}