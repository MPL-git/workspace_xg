package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AssistanceDtlCustom;
import com.jf.entity.AssistanceDtlExample;
@Repository
public interface AssistanceDtlCustomMapper{
    int countByExample(AssistanceDtlExample example);
    List<AssistanceDtlCustom> selectByExample(AssistanceDtlExample example);
}