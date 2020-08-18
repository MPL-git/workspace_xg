package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtDepositDtlCustom;
import com.jf.entity.MchtDepositDtlCustomExample;
@Repository
public interface MchtDepositDtlCustomMapper{
    int countByExample(MchtDepositDtlCustomExample example);

    List<MchtDepositDtlCustom> selectByExample(MchtDepositDtlCustomExample example);

}