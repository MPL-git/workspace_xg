package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositCustom;
import com.jf.entity.MchtDepositExample;
@Repository
public interface MchtDepositCustomMapper{
    int countByExample(MchtDepositExample example);

    List<MchtDepositCustom> selectByExample(MchtDepositExample example);

	MchtDeposit getMchtDepositByMchtId(Integer mchtId);

}