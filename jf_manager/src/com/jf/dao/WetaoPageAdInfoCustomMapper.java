package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.WetaoPageAdInfoCustom;
import com.jf.entity.WetaoPageAdInfoExample;
@Repository
public interface WetaoPageAdInfoCustomMapper{
    int countByExample(WetaoPageAdInfoExample example);
    List<WetaoPageAdInfoCustom> selectByExample(WetaoPageAdInfoExample example);
}