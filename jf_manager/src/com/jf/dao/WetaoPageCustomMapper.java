package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.WetaoPageCustom;
import com.jf.entity.WetaoPageExample;
@Repository
public interface WetaoPageCustomMapper{
    int countByExample(WetaoPageExample example);
    List<WetaoPageCustom> selectByExample(WetaoPageExample example);
}