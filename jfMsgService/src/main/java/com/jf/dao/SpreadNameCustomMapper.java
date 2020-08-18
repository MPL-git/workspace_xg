package com.jf.dao;

import org.springframework.stereotype.Repository;
@Repository
public interface SpreadNameCustomMapper{


    int insertFromTrackData();
    int batchUpdateSeqNo();

}