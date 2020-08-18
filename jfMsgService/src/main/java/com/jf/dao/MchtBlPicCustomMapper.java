package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBlPic;
@Repository
public interface MchtBlPicCustomMapper{
    List<MchtBlPic> selectNoWatermarkBlPic();
}