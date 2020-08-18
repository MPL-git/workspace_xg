package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSettleCompareCustom;
import com.jf.entity.MchtSettleCompareCustomExample;

@Repository
public interface MchtSettleCompareCustomMapper {
	
    public int countByCustomExample(MchtSettleCompareCustomExample example);

    public List<MchtSettleCompareCustom> selectByCustomExample(MchtSettleCompareCustomExample example);

}