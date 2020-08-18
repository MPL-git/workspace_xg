package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.StaffReplyCustom;
import com.jf.entity.StaffReplyExample;

@Repository
public interface StaffReplyCustomMapper {
    int staffReplycountByExample(StaffReplyExample example);

    List<StaffReplyCustom> staffReplyselectByExample(StaffReplyExample example);

    StaffReplyCustom staffReplyCustomselectByPrimaryKey(Integer id);
}