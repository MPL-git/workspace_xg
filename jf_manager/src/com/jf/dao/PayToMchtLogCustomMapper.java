package com.jf.dao;

import com.jf.entity.PayToMchtLogCustom;
import com.jf.entity.PayToMchtLogCustomExample;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PayToMchtLogCustomMapper{
    int countByExample(PayToMchtLogCustomExample example);
    List<PayToMchtLogCustom> selectByExample(PayToMchtLogCustomExample example);
}