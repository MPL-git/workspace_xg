package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CooperationChangeApplyCustom;
import com.jf.entity.CooperationChangeApplyCustomExample;
@Repository
public interface CooperationChangeApplyCustomMapper{
    int countByExample(CooperationChangeApplyCustomExample example);
    List<CooperationChangeApplyCustom> selectByExample(CooperationChangeApplyCustomExample example);
}