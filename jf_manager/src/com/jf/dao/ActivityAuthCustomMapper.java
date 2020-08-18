package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityAuth;
@Repository
public interface ActivityAuthCustomMapper{

    public ActivityAuth selectByActivityAuthCustomExample(Integer activityId);
    
    public Integer selectByActivityAuthSelect(Integer activityId);
    
    public Integer selectByActivityAuthProductAll(Integer activityId);

}