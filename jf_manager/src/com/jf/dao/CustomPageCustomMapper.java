package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomPageCustom;
import com.jf.entity.CustomPageExample;
@Repository
public interface CustomPageCustomMapper{
    int countByExample(CustomPageExample example);
    List<CustomPageCustom> selectByExample(CustomPageExample example);
}